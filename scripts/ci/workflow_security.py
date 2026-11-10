"""US-90 checker for workflow permissions, pull-request input, and pins.

The checker reads workflow files. It does not execute ``run`` steps.
A remote ``uses`` value must be the commit of a published release tag.
"""

from __future__ import annotations

import json
import os
import re
import subprocess
import sys
import urllib.error
import urllib.request
from pathlib import Path

from gha_yaml import ParseError, parse_workflow

CONTENTS_READ = {"contents": "read"}
CODEQL = {
    "actions": "read",
    "contents": "read",
    "packages": "read",
    "security-events": "write",
}
PAGES_DEPLOY = {"contents": "read", "pages": "write", "id-token": "write"}
DISPATCH = {"actions": "write", "contents": "read"}
NAMED = {
    "qa.yml": "qa",
    "pages.yml": "pages",
    "dispatch-pages.yml": "dispatch",
}
HEAD_REF_MARKERS = (
    "github.event.pull_request.title",
    "github.event.pull_request.body",
    "github.event.pull_request.head.ref",
    "github.event.pull_request.head.sha",
    "github.head_ref",
    "github.event.inputs.wip_sha",
)
HEX_GUARD = re.compile(r"\[0-9a-fA-F\]\{40\}|\[0-9a-f\]\{40\}")
SHA = re.compile(r"^[0-9a-fA-F]{40}$")
USES = re.compile(r"^([^/]+)/([^@/]+)((?:/[^@]+)?)@(.+)$")
_COMMIT_CACHE: dict[str, set[str]] = {}


class CheckError(Exception):
    """The workflow check cannot pass."""


def main(argv: list[str]) -> int:
    if len(argv) != 2:
        print("usage: workflow_security.py <repository>", file=sys.stderr)
        return 1
    try:
        check_repository(Path(argv[1]))
    except CheckError as exc:
        print(f"workflow security failed: {exc}", file=sys.stderr)
        return 1
    print("workflow security: permissions, pull-request input, and pins match")
    return 0


def check_repository(root: Path, commits_for=None) -> None:
    if commits_for is None:
        commits_for = commits_from_environment()
    workflow_dir = root / ".github" / "workflows"
    if not workflow_dir.is_dir():
        raise CheckError(f"cannot read {workflow_dir}")
    files = sorted(
        item
        for item in workflow_dir.iterdir()
        if item.is_file() and item.suffix in {".yml", ".yaml"}
    )
    if not files:
        raise CheckError("no workflow file to read")
    for path in files:
        try:
            text = path.read_text(encoding="utf-8")
        except OSError as exc:
            raise CheckError(f"cannot read {path.name}: {exc}") from exc
        try:
            document = parse_workflow(text)
        except ParseError as exc:
            raise CheckError(f"cannot read {path.name}: {exc}") from exc
        check_document(root, path.name, document, commits_for)


def check_document(root: Path, filename: str, document: dict, commits_for) -> None:
    kind = NAMED.get(filename)
    if kind is None:
        raise CheckError(f"no named permission set for {filename}")
    triggers = trigger_names(document.get("on"), filename)
    if "pull_request_target" in triggers:
        raise CheckError(f"{filename} triggers pull_request_target")
    jobs = document.get("jobs")
    if not isinstance(jobs, dict) or not jobs:
        raise CheckError(f"{filename} has no jobs")
    workflow_permissions = document.get("permissions", _MISSING)
    if kind == "qa":
        check_qa(filename, jobs, workflow_permissions)
    elif kind == "pages":
        check_pages(filename, jobs, workflow_permissions, triggers)
    else:
        check_dispatch(filename, jobs, workflow_permissions, document.get("on"), triggers)
    for job_id, job in jobs.items():
        if not isinstance(job, dict):
            raise CheckError(f"{filename} job {job_id} is not a mapping")
        check_steps(filename, job_id, job)
        check_uses(root, filename, job, commits_for)


def check_qa(filename: str, jobs: dict, workflow_permissions) -> None:
    if permission_map(workflow_permissions, f"{filename} workflow") != CONTENTS_READ:
        raise CheckError(f"{filename} workflow permissions are not exactly contents: read")
    saw_codeql = False
    for job_id, job in jobs.items():
        if not isinstance(job, dict):
            raise CheckError(f"{filename} job {job_id} is not a mapping")
        name = job.get("name")
        if "permissions" in job:
            if name != "CodeQL" or permission_map(job.get("permissions"), name) != CODEQL:
                raise CheckError(f"{filename} job {name or job_id} permissions are not the CodeQL set")
            saw_codeql = True
            continue
        if permission_map(workflow_permissions, job_id) != CONTENTS_READ:
            raise CheckError(f"{filename} job {name or job_id} does not inherit contents: read")
    names = {job.get("name") for job in jobs.values() if isinstance(job, dict)}
    for required in ("Dependency scan", "Workflow security"):
        if required not in names:
            raise CheckError(f"{filename} is missing the {required} job")
        job = next(item for item in jobs.values() if isinstance(item, dict) and item.get("name") == required)
        if "permissions" in job:
            raise CheckError(f"{filename} {required} adds a permissions block")
    if not saw_codeql:
        raise CheckError(f"{filename} has no CodeQL job with its named permissions")


def check_pages(filename: str, jobs: dict, workflow_permissions, triggers: set[str]) -> None:
    if permission_map(workflow_permissions, f"{filename} workflow") != CONTENTS_READ:
        raise CheckError(f"{filename} workflow permissions are not exactly contents: read")
    if "pull_request" in triggers or "pull_request_target" in triggers:
        raise CheckError(f"{filename} grants Pages permissions on a pull request trigger")
    saw_deploy = False
    for job_id, job in jobs.items():
        if not isinstance(job, dict):
            raise CheckError(f"{filename} job {job_id} is not a mapping")
        name = job.get("name")
        if "permissions" in job:
            if name != "Deploy mockups" or permission_map(job.get("permissions"), name) != PAGES_DEPLOY:
                raise CheckError(
                    f"{filename} job {name or job_id} permissions are outside the named set"
                )
            saw_deploy = True
        elif permission_map(workflow_permissions, job_id) != CONTENTS_READ:
            raise CheckError(f"{filename} job {name or job_id} does not inherit contents: read")
    if not saw_deploy:
        raise CheckError(f"{filename} has no Deploy mockups job with its named permissions")


def check_dispatch(filename: str, jobs: dict, workflow_permissions, on_value, triggers: set[str]) -> None:
    if "permissions" in {key for job in jobs.values() if isinstance(job, dict) for key in job}:
        for job in jobs.values():
            if isinstance(job, dict) and "permissions" in job:
                raise CheckError(f"{filename} must inherit permissions from the workflow block")
    if permission_map(workflow_permissions, f"{filename} workflow") != DISPATCH:
        raise CheckError(f"{filename} permissions are not exactly actions: write and contents: read")
    if triggers != {"push"} or push_branches(on_value) != ["gh-pages"]:
        raise CheckError(f"{filename} trigger is not only push to gh-pages")
    for job in jobs.values():
        if isinstance(job, dict) and "permissions" in job:
            raise CheckError(f"{filename} job replaces the workflow permissions")


def check_steps(filename: str, job_id: str, job: dict) -> None:
    steps = job.get("steps") or []
    if steps is None:
        return
    if not isinstance(steps, list):
        raise CheckError(f"{filename} job {job_id} steps are not a list")
    for index, step in enumerate(steps, start=1):
        if not isinstance(step, dict):
            raise CheckError(f"{filename} job {job_id} step {index} is not a mapping")
        run = step.get("run")
        run_text = run if isinstance(run, str) else ""
        for marker in HEAD_REF_MARKERS:
            if marker in run_text:
                raise CheckError(
                    f"{filename} job {job_id} step {index} run text contains {marker}"
                )
        if isinstance(step.get("with"), dict):
            for key, value in step["with"].items():
                if isinstance(value, str) and "github.event.inputs.wip_sha" in value:
                    raise CheckError(
                        f"{filename} job {job_id} step {index} passes wip_sha as {key}"
                    )
        env = step.get("env") if isinstance(step.get("env"), dict) else {}
        passed = any(
            isinstance(value, str) and "github.event.inputs.wip_sha" in value for value in env.values()
        )
        if passed and not (HEX_GUARD.search(run_text) and re.search(r"\bexit\b", run_text)):
            raise CheckError(
                f"{filename} job {job_id} step {index} does not reject a non-40-hex wip_sha "
                "before that value is a checkout ref"
            )


def check_uses(root: Path, filename: str, job: dict, commits_for) -> None:
    for uses in collect_uses(job):
        if uses.startswith("./"):
            check_local_uses(root, uses, commits_for)
            continue
        if uses.startswith("docker://"):
            raise CheckError(f"{filename} docker uses is not a release SHA: {uses}")
        match = USES.fullmatch(uses.strip())
        if match is None:
            raise CheckError(f"{filename} cannot read uses {uses}")
        owner, repo, _subpath, ref = match.groups()
        if not SHA.fullmatch(ref):
            raise CheckError(f"{filename} uses a tag or branch: {uses}")
        commits = commits_for(f"{owner}/{repo}")
        if ref.lower() not in {item.lower() for item in commits}:
            raise CheckError(f"{filename} SHA is not a published release commit of {owner}/{repo}: {ref}")


def check_local_uses(root: Path, uses: str, commits_for) -> None:
    path = (root / uses).resolve()
    if root.resolve() not in path.parents and path != root.resolve():
        raise CheckError(f"local uses escapes the repository: {uses}")
    if not path.is_file():
        raise CheckError(f"local uses is missing: {uses}")
    try:
        document = parse_workflow(path.read_text(encoding="utf-8"))
    except (OSError, ParseError) as exc:
        raise CheckError(f"cannot read local uses {uses}: {exc}") from exc
    if "jobs" not in document:
        raise CheckError(f"local uses has no workflow permission set: {uses}")
    check_document(root, path.name, document, commits_for)


def collect_uses(node) -> list[str]:
    found = []
    if isinstance(node, dict):
        for key, value in node.items():
            if key == "uses" and isinstance(value, str):
                found.append(value.strip())
            else:
                found.extend(collect_uses(value))
    elif isinstance(node, list):
        for item in node:
            found.extend(collect_uses(item))
    return found


_MISSING = object()


def permission_map(value, label: str) -> dict[str, str]:
    if value is _MISSING or value is None:
        raise CheckError(f"{label} has no permissions")
    if not isinstance(value, dict) or not value:
        raise CheckError(f"{label} permissions are not an explicit set")
    mapped = {}
    for key, raw in value.items():
        if not isinstance(raw, str):
            raise CheckError(f"{label} permission {key} is not a string")
        mapped[str(key)] = raw.strip()
    return mapped


def trigger_names(on_value, filename: str) -> set[str]:
    if isinstance(on_value, dict):
        return set(on_value)
    if isinstance(on_value, list):
        return {str(item) for item in on_value}
    if isinstance(on_value, str):
        return {on_value}
    raise CheckError(f"{filename} triggers cannot be read")


def push_branches(on_value) -> list[str] | None:
    if not isinstance(on_value, dict):
        return None
    push = on_value.get("push")
    if not isinstance(push, dict):
        return None
    branches = push.get("branches")
    if isinstance(branches, str):
        return [branches]
    if isinstance(branches, list):
        return [str(item) for item in branches]
    return None


def commits_from_environment():
    index = os.environ.get("WORKFLOW_RELEASE_INDEX")
    if index:
        data = json.loads(Path(index).read_text(encoding="utf-8"))

        def from_index(repository: str) -> set[str]:
            if repository not in data:
                raise CheckError(f"release index has no entry for {repository}")
            return set(data[repository])

        return from_index
    return published_commits


def published_commits(repository: str) -> set[str]:
    if repository in _COMMIT_CACHE:
        return _COMMIT_CACHE[repository]
    owner, repo = repository.split("/", 1)
    try:
        peeled = peeled_tags(owner, repo)
        tags = published_release_tags(owner, repo)
    except CheckError:
        raise
    except Exception as exc:
        raise CheckError(f"cannot resolve published releases for {repository}: {exc}") from exc
    missing = [tag for tag in tags if tag not in peeled]
    if missing:
        raise CheckError(
            f"cannot resolve {len(missing)} published release tags of {repository}"
        )
    commits = {peeled[tag] for tag in tags}
    if not commits:
        raise CheckError(f"{repository} has no published release commit")
    _COMMIT_CACHE[repository] = commits
    return commits


def peeled_tags(owner: str, repo: str) -> dict[str, str]:
    url = f"https://github.com/{owner}/{repo}.git"
    try:
        completed = subprocess.run(
            ["git", "ls-remote", "--tags", url],
            check=False,
            capture_output=True,
            text=True,
            timeout=120,
        )
    except subprocess.TimeoutExpired as exc:
        raise CheckError(f"timed out reading tags of {owner}/{repo}") from exc
    if completed.returncode != 0:
        detail = (completed.stderr or "").strip().splitlines()
        tail = detail[-1] if detail else f"exit {completed.returncode}"
        raise CheckError(f"cannot read tags of {owner}/{repo}: {tail}")
    lightweight = {}
    peeled = {}
    for line in completed.stdout.splitlines():
        sha, ref = line.split()
        if not ref.startswith("refs/tags/"):
            continue
        name = ref[len("refs/tags/") :]
        if name.endswith("^{}"):
            peeled[name[:-3]] = sha
        else:
            lightweight[name] = sha
    lightweight.update(peeled)
    return lightweight


def published_release_tags(owner: str, repo: str) -> list[str]:
    tags = []
    url = f"https://api.github.com/repos/{owner}/{repo}/releases?per_page=100"
    while url:
        payload, link = github_json(url)
        if not isinstance(payload, list):
            raise CheckError(f"release list for {owner}/{repo} is not a list")
        for release in payload:
            if not isinstance(release, dict):
                raise CheckError(f"release entry for {owner}/{repo} is not an object")
            if release.get("draft"):
                continue
            tag = release.get("tag_name")
            if not isinstance(tag, str) or tag == "":
                raise CheckError(f"published release of {owner}/{repo} has no tag")
            tags.append(tag)
        url = next_link(link)
    return tags


def github_json(url: str):
    request = urllib.request.Request(
        url,
        headers={
            "Accept": "application/vnd.github+json",
            "User-Agent": "lagoon-workflow-security",
            "X-GitHub-Api-Version": "2022-11-28",
        },
    )
    token = os.environ.get("GITHUB_TOKEN") or os.environ.get("GH_TOKEN")
    if token:
        request.add_header("Authorization", f"Bearer {token}")
    try:
        with urllib.request.urlopen(request, timeout=30) as response:
            body = response.read().decode("utf-8")
            return json.loads(body), response.headers.get("Link")
    except urllib.error.HTTPError as exc:
        if exc.code in {401, 403} and token:
            return github_json_anonymous(url)
        raise CheckError(f"GitHub API {exc.code} for {url}") from exc
    except urllib.error.URLError as exc:
        raise CheckError(f"GitHub API failed for {url}") from exc
    except TimeoutError as exc:
        raise CheckError(f"GitHub API timed out for {url}") from exc


def github_json_anonymous(url: str):
    request = urllib.request.Request(
        url,
        headers={
            "Accept": "application/vnd.github+json",
            "User-Agent": "lagoon-workflow-security",
            "X-GitHub-Api-Version": "2022-11-28",
        },
    )
    try:
        with urllib.request.urlopen(request, timeout=30) as response:
            body = response.read().decode("utf-8")
            return json.loads(body), response.headers.get("Link")
    except urllib.error.HTTPError as exc:
        raise CheckError(f"GitHub API {exc.code} for {url}") from exc


def next_link(header: str | None) -> str | None:
    if not header:
        return None
    for part in header.split(","):
        if 'rel="next"' not in part:
            continue
        url = part.split(";", 1)[0].strip()
        if url.startswith("<") and url.endswith(">"):
            return url[1:-1]
    return None


if __name__ == "__main__":
    sys.path.insert(0, str(Path(__file__).resolve().parent))
    raise SystemExit(main(sys.argv))
