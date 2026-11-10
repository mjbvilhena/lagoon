"""Fixture tests for the US-88, US-89, and US-90 gates. Not a product test runner."""

from __future__ import annotations

import json
import shutil
import subprocess
import sys
import tempfile
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(Path(__file__).resolve().parent))

import codeql_gate  # noqa: E402
import cvss  # noqa: E402
import dependency_gate  # noqa: E402
from workflow_security import CheckError, check_repository  # noqa: E402

RELEASES = {
    "actions/checkout": ["d23441a48e516b6c34aea4fa41551a30e30af803"],
    "DavidAnson/markdownlint-cli2-action": ["21c1be1b93ad9ed58fa840aacc3f279cde2a72ff"],
    "actions/setup-java": ["b6effb05e454b25005698d916606bdc6ffcbf961"],
    "gradle/actions": ["ed408507eac070d1f99cc633dbcf757c94c7933a"],
    "github/codeql-action": ["9418214df1f2c4b8128c770156c409013b07d6b9"],
    "actions/configure-pages": ["45bfe0192ca1faeb007ade9deae92b16b8254a0d"],
    "actions/upload-pages-artifact": ["fc324d3547104276b827a68afc52ff2a11cc49c9"],
    "actions/deploy-pages": ["368f82528645a54fb793d4d04e342629a3f51346"],
}
CHECKOUT = RELEASES["actions/checkout"][0]


def commits_for(repository: str) -> set[str]:
    if repository not in RELEASES:
        raise CheckError(f"release index has no entry for {repository}")
    return set(RELEASES[repository])


def expect_fail(reasons: list[str], fragment: str) -> None:
    if not any(fragment in reason for reason in reasons):
        raise AssertionError(f"expected {fragment!r} in {reasons}")


def test_cvss_known_vectors() -> None:
    cases = {
        "CVSS:3.0/AV:N/AC:L/PR:N/UI:N/S:U/C:H/I:H/A:H": 9.8,
        "CVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:C/C:H/I:H/A:H": 10.0,
        "CVSS:3.1/AV:N/AC:H/PR:N/UI:N/S:C/C:H/I:H/A:H": 9.0,
        "CVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:C/C:N/I:N/A:H": 8.6,
        "CVSS:3.1/AV:N/AC:H/PR:H/UI:N/S:U/C:H/I:H/A:H": 6.6,
        "CVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:C/C:H/I:H/A:H/E:H": 10.0,
    }
    for vector, score in cases.items():
        got = cvss.base_score(vector)
        if got != score:
            raise AssertionError(f"{vector} scored {got}, expected {score}")


def _vuln(identifier: str, score=None, database=None, score_type="CVSS_V3") -> dict:
    entry = {"id": identifier, "severity": []}
    if score is not None:
        entry["severity"].append({"type": score_type, "score": score})
    if database is not None:
        entry["database_specific"] = {"severity": database}
    return entry


def _document(coordinate: str, vulnerabilities: list[dict]) -> dict:
    name, version = coordinate.rsplit(":", 1)
    return {
        "results": [
            {
                "packages": [
                    {
                        "package": {"name": name, "version": version},
                        "vulnerabilities": vulnerabilities,
                    }
                ]
            }
        ]
    }


def _write_case(tmp: Path, document: dict, lines: list[str] | None) -> tuple[Path, Path, Path]:
    lock = tmp / "gradle.lockfile"
    lock.write_text("com.example:sample:1.2.3=resolved\nempty=\n", encoding="utf-8")
    osv = tmp / "osv.json"
    osv.write_text(json.dumps(document), encoding="utf-8")
    exceptions = tmp / "missing-exceptions.md"
    if lines is not None:
        exceptions = tmp / "vulnerability-exceptions.md"
        exceptions.write_text("\n".join(lines) + "\n", encoding="utf-8")
    return osv, lock, exceptions


def _gate(document: dict, lines: list[str] | None) -> list[str]:
    with tempfile.TemporaryDirectory() as directory:
        osv, lock, exceptions = _write_case(Path(directory), document, lines)
        return dependency_gate.evaluate(osv, lock, exceptions)


COORD = "com.example:sample:1.2.3"


def test_high_cvss_fails() -> None:
    reasons = _gate(_document(COORD, [_vuln("GHSA-aaaa-bbbb-cccc", "CVSS:3.1/AV:N/AC:L/PR:N/UI:N/S:U/C:H/I:N/A:N")]), None)
    # 7.5 vector: AV:N/AC:L/PR:N/UI:N/S:U/C:H/I:N/A:N is 7.5
    expect_fail(reasons, "GHSA-aaaa-bbbb-cccc")


def test_score_at_seven_fails_and_below_does_not() -> None:
    high = _gate(_document(COORD, [_vuln("OSV-2020-1", 7.0)]), None)
    expect_fail(high, "OSV-2020-1")
    low = _gate(
        _document(COORD, [_vuln("OSV-2020-2", 6.9, database="CRITICAL")]),
        None,
    )
    if low:
        raise AssertionError(f"score below 7.0 must not fail: {low}")


def test_database_severity_is_only_a_fallback() -> None:
    high = _gate(_document(COORD, [_vuln("OSV-2020-3", database="HIGH")]), None)
    expect_fail(high, "database severity HIGH")
    critical = _gate(_document(COORD, [_vuln("OSV-2020-4", database="critical")]), None)
    expect_fail(critical, "CRITICAL")
    moderate = _gate(_document(COORD, [_vuln("OSV-2020-5", database="MODERATE")]), None)
    if moderate:
        raise AssertionError(moderate)
    medium = _gate(_document(COORD, [_vuln("OSV-2020-6", database="MEDIUM")]), None)
    if medium:
        raise AssertionError(medium)


def test_cvss_v4_is_not_cvss_v3() -> None:
    moderate = _gate(
        _document(COORD, [_vuln("OSV-2020-7", "CVSS:4.0/AV:N/AC:L/AT:N/PR:N/UI:N/VC:N/VI:N/VA:N/SC:N/SI:L/SA:N", "MODERATE", "CVSS_V4")]),
        None,
    )
    if moderate:
        raise AssertionError(moderate)
    high = _gate(
        _document(COORD, [_vuln("OSV-2020-8", "CVSS:4.0/AV:N", "HIGH", "CVSS_V4")]),
        None,
    )
    expect_fail(high, "HIGH")
    neither = _gate(
        _document(COORD, [_vuln("OSV-2020-9", "CVSS:4.0/AV:N", None, "CVSS_V4")]),
        None,
    )
    expect_fail(neither, "no CVSS v3 score and no database severity")


def test_no_score_and_no_severity_fails() -> None:
    reasons = _gate(_document(COORD, [_vuln("OSV-2020-10")]), None)
    expect_fail(reasons, "no CVSS v3 score and no database severity")
    unknown = _gate(_document(COORD, [_vuln("OSV-2020-11", database="IMPORTANT")]), None)
    expect_fail(unknown, "no CVSS v3 score and no database severity")


def test_exception_matches_one_id_and_version() -> None:
    approved = (
        "GHSA-aaaa-bbbb-cccc com.example:sample:1.2.3 cannot fix in this change. "
        "Exception approved by mjbvilhena on 2026-11-10"
    )
    document = _document(
        COORD,
        [
            _vuln("GHSA-aaaa-bbbb-cccc", 9.8),
            _vuln("GHSA-dddd-eeee-ffff", 9.8),
        ],
    )
    reasons = _gate(document, [approved])
    if any("GHSA-aaaa-bbbb-cccc" in reason for reason in reasons):
        raise AssertionError(reasons)
    expect_fail(reasons, "GHSA-dddd-eeee-ffff")
    wrong_version = approved.replace("1.2.3", "1.2.4")
    expect_fail(_gate(document, [wrong_version]), "GHSA-aaaa-bbbb-cccc")
    wrong_id = approved.replace("GHSA-aaaa-bbbb-cccc", "GHSA-zzzz-yyyy-xxxx")
    expect_fail(_gate(document, [wrong_id]), "GHSA-aaaa-bbbb-cccc")
    no_sentence = "GHSA-aaaa-bbbb-cccc com.example:sample:1.2.3 cannot fix in this change."
    expect_fail(_gate(document, [no_sentence]), "GHSA-aaaa-bbbb-cccc")
    no_date = (
        "GHSA-aaaa-bbbb-cccc com.example:sample:1.2.3 cannot fix in this change. "
        "Exception approved by mjbvilhena on"
    )
    expect_fail(_gate(document, [no_date]), "GHSA-aaaa-bbbb-cccc")
    bad_date = approved.replace("2026-11-10", "2026-13-40")
    expect_fail(_gate(document, [bad_date]), "GHSA-aaaa-bbbb-cccc")
    prefix = approved.replace("1.2.3", "1.2")
    expect_fail(_gate(document, [prefix]), "GHSA-aaaa-bbbb-cccc")
    group_only = "com.example Exception approved by mjbvilhena on 2026-11-10 because postponed"
    expect_fail(_gate(document, [group_only]), "GHSA-aaaa-bbbb-cccc")
    configuration = (
        "releaseRuntimeClasspath Exception approved by mjbvilhena on 2026-11-10 skip the graph"
    )
    expect_fail(_gate(document, [configuration]), "GHSA-aaaa-bbbb-cccc")
    no_reason = "GHSA-aaaa-bbbb-cccc com.example:sample:1.2.3 Exception approved by mjbvilhena on 2026-11-10"
    expect_fail(_gate(document, [no_reason]), "GHSA-aaaa-bbbb-cccc")


def test_missing_exception_file_skips_nothing() -> None:
    reasons = _gate(_document(COORD, [_vuln("OSV-2020-12", 9.0)]), None)
    expect_fail(reasons, "OSV-2020-12")


def test_graph_errors_fail_closed() -> None:
    with tempfile.TemporaryDirectory() as directory:
        tmp = Path(directory)
        document = _document(COORD, [])
        osv, lock, exceptions = _write_case(tmp, document, None)
        lock.write_text("com.example:other:9.9.9=resolved\nempty=\n", encoding="utf-8")
        try:
            dependency_gate.evaluate(osv, lock, exceptions)
        except dependency_gate.GateError as exc:
            if "does not match" not in str(exc):
                raise
        else:
            raise AssertionError("mismatched graph was accepted")
        lock.write_text("empty=\n", encoding="utf-8")
        try:
            dependency_gate.evaluate(osv, lock, exceptions)
        except dependency_gate.GateError as exc:
            if "no graph" not in str(exc):
                raise
        else:
            raise AssertionError("empty graph was accepted")
        missing = tmp / "absent.json"
        try:
            dependency_gate.evaluate(missing, lock, exceptions)
        except dependency_gate.GateError:
            pass
        else:
            raise AssertionError("missing scanner output was accepted")


def _sarif(rule_id: str, severity, tags: list[str]) -> dict:
    rule = {"id": rule_id, "properties": {"tags": tags}}
    if severity is not None:
        rule["properties"]["security-severity"] = severity
    return {
        "runs": [
            {
                "tool": {"driver": {"rules": [rule]}},
                "results": [{"ruleId": rule_id, "ruleIndex": 0, "locations": [{"physicalLocation": {"artifactLocation": {"uri": "A.kt"}}}]}],
            }
        ]
    }


def _codeql(document: dict) -> list[str]:
    with tempfile.TemporaryDirectory() as directory:
        path = Path(directory) / "result.sarif"
        path.write_text(json.dumps(document), encoding="utf-8")
        return codeql_gate.evaluate(path)


def test_codeql_high_security_result_fails() -> None:
    expect_fail(_codeql(_sarif("java/example", "7.0", ["security"])), "java/example")
    expect_fail(_codeql(_sarif("java/example", 9.8, ["security", "external/cwe/cwe-089"])), "9.8")
    if _codeql(_sarif("java/example", "6.9", ["security"])):
        raise AssertionError("score below 7.0 failed")
    if _codeql(_sarif("java/quality", "9.0", ["quality", "correctness"])):
        raise AssertionError("a query that is not tagged security failed")
    if _codeql(_sarif("java/example", None, ["security"])):
        raise AssertionError("missing security-severity failed")


def test_codeql_has_no_exception_path() -> None:
    with tempfile.TemporaryDirectory() as directory:
        tmp = Path(directory)
        sarif = tmp / "result.sarif"
        sarif.write_text(json.dumps(_sarif("java/example", "8.0", ["security"])), encoding="utf-8")
        (tmp / "vulnerability-exceptions.md").write_text(
            "java/example A.kt Exception approved by mjbvilhena on 2026-11-10 cannot fix\n",
            encoding="utf-8",
        )
        reasons = codeql_gate.evaluate(sarif)
        expect_fail(reasons, "java/example")
        try:
            codeql_gate.evaluate(tmp / "missing.sarif")
        except codeql_gate.GateError as exc:
            if "missing" not in str(exc):
                raise
        else:
            raise AssertionError("missing SARIF was accepted")


def test_codeql_job_has_no_baseline_or_noop_success() -> None:
    text = (ROOT / ".github/workflows/qa.yml").read_text(encoding="utf-8")
    codeql = text.split("  codeql:", 1)[1].split("  dependency-scan:", 1)[0]
    if "baseline" in codeql.lower():
        raise AssertionError("CodeQL job names a baseline")
    if "CodeQL succeeds" in text:
        raise AssertionError("stale CodeQL success no-op is still in the workflow")
    if "vulnerability-exceptions" in codeql:
        raise AssertionError("CodeQL job reads the exception file")
    empty = Path(tempfile.mkdtemp())
    try:
        completed = subprocess.run(
            ["bash", str(ROOT / "scripts/ci/require_gradle_project.sh"), str(empty)],
            capture_output=True,
            text=True,
            check=False,
        )
        if completed.returncode == 0 or "no SARIF" not in completed.stderr:
            raise AssertionError(completed.stderr)
        if "succeeds" in completed.stderr.lower():
            raise AssertionError(completed.stderr)
        present = empty / "settings.gradle.kts"
        present.write_text("rootProject.name = \"fixture\"\n", encoding="utf-8")
        ok = subprocess.run(
            ["bash", str(ROOT / "scripts/ci/require_gradle_project.sh"), str(empty)],
            capture_output=True,
            text=True,
            check=False,
        )
        if ok.returncode != 0:
            raise AssertionError(ok.stderr)
    finally:
        shutil.rmtree(empty)


def _workflow(directory: Path, name: str, text: str) -> None:
    path = directory / ".github" / "workflows"
    path.mkdir(parents=True, exist_ok=True)
    (path / name).write_text(text, encoding="utf-8")


def _qa(body: str) -> str:
    return f"""
name: QA
on:
  pull_request:
permissions:
  contents: read
jobs:
  codeql:
    name: CodeQL
    permissions:
      actions: read
      contents: read
      packages: read
      security-events: write
    steps:
      - uses: actions/checkout@{CHECKOUT}
  dependency-scan:
    name: Dependency scan
    steps:
      - uses: actions/checkout@{CHECKOUT}
  workflow-security:
    name: Workflow security
    steps:
      - uses: actions/checkout@{CHECKOUT}
{body}
"""


def test_real_workflows_match_the_named_sets() -> None:
    check_repository(ROOT, commits_for)


def test_workflow_rejects_bad_pins_and_input() -> None:
    with tempfile.TemporaryDirectory() as directory:
        root = Path(directory)
        _workflow(
            root,
            "qa.yml",
            _qa(
                f"""
  extra:
    name: Extra
    steps:
      - uses: actions/checkout@v6
"""
            ),
        )
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "tag or branch" not in str(exc):
                raise
        else:
            raise AssertionError("tag pin was accepted")

        _workflow(
            root,
            "qa.yml",
            _qa(
                f"""
  injected:
    name: Injected
    steps:
      - run: echo ${{{{ github.event.pull_request.title }}}}
"""
            ),
        )
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "pull_request.title" not in str(exc):
                raise
        else:
            raise AssertionError("pull request title was accepted")

        _workflow(
            root,
            "qa.yml",
            """
name: QA
on:
  pull_request_target:
permissions:
  contents: read
jobs:
  codeql:
    name: CodeQL
    permissions:
      actions: read
      contents: read
      packages: read
      security-events: write
    steps:
      - uses: actions/checkout@%s
  dependency-scan:
    name: Dependency scan
    steps:
      - uses: actions/checkout@%s
  workflow-security:
    name: Workflow security
    steps:
      - uses: actions/checkout@%s
"""
            % (CHECKOUT, CHECKOUT, CHECKOUT),
        )
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "pull_request_target" not in str(exc):
                raise
        else:
            raise AssertionError("pull_request_target was accepted")

        _workflow(
            root,
            "qa.yml",
            """
name: QA
on: push
jobs:
  codeql:
    name: CodeQL
    steps:
      - uses: actions/checkout@%s
"""
            % CHECKOUT,
        )
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "permissions" not in str(exc):
                raise
        else:
            raise AssertionError("missing permissions were accepted")


def test_pages_and_dispatch_rules() -> None:
    with tempfile.TemporaryDirectory() as directory:
        root = Path(directory)
        _workflow(
            root,
            "pages.yml",
            f"""
name: Pages
on:
  pull_request:
permissions:
  contents: read
jobs:
  deploy:
    name: Deploy mockups
    permissions:
      contents: read
      pages: write
      id-token: write
    steps:
      - uses: actions/checkout@{CHECKOUT}
""",
        )
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "pull request" not in str(exc):
                raise
        else:
            raise AssertionError("Pages on pull_request was accepted")

    with tempfile.TemporaryDirectory() as directory:
        root = Path(directory)
        _workflow(
            root,
            "dispatch-pages.yml",
            """
name: Dispatch
on:
  push:
    branches: [gh-pages]
permissions:
  actions: write
  contents: read
jobs:
  dispatch:
    name: Dispatch pages
    steps:
      - name: Post
        run: echo dispatch
""",
        )
        check_repository(root, commits_for)
        text = (root / ".github/workflows/dispatch-pages.yml").read_text(encoding="utf-8")
        text = text.replace("branches: [gh-pages]", "branches: [gh-pages, master]")
        (root / ".github/workflows/dispatch-pages.yml").write_text(text, encoding="utf-8")
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "gh-pages" not in str(exc):
                raise
        else:
            raise AssertionError("extra dispatch branch was accepted")


def test_wip_sha_must_be_rejected_before_it_is_a_ref() -> None:
    with tempfile.TemporaryDirectory() as directory:
        root = Path(directory)
        bare = f"""
name: Pages
on:
  workflow_dispatch:
permissions:
  contents: read
jobs:
  deploy:
    name: Deploy mockups
    permissions:
      contents: read
      pages: write
      id-token: write
    steps:
      - name: Choose ref
        env:
          WIP_SHA: ${{{{ github.event.inputs.wip_sha }}}}
        run: echo "$WIP_SHA"
      - uses: actions/checkout@{CHECKOUT}
"""
        _workflow(root, "pages.yml", bare)
        try:
            check_repository(root, commits_for)
        except CheckError as exc:
            if "wip_sha" not in str(exc):
                raise
        else:
            raise AssertionError("unchecked wip_sha was accepted")
        guarded = bare.replace(
            'run: echo "$WIP_SHA"',
            """run: |
          if [[ -n "$WIP_SHA" && ! "$WIP_SHA" =~ ^[0-9a-fA-F]{40}$ ]]; then
            exit 1
          fi
          echo ok""",
        )
        _workflow(root, "pages.yml", guarded)
        check_repository(root, commits_for)


def test_dependency_scan_script_fails_closed_on_resolver_error() -> None:
    text = (ROOT / "scripts/ci/dependency_scan.sh").read_text(encoding="utf-8")
    if "bfe2f84abd2f09fe0b83ba7f6dad40d296762f40" not in text:
        raise AssertionError("osv-scanner is not pinned to the v2.7.0 commit")
    if "timeout" not in text or "|| true" in text:
        raise AssertionError("resolver failure can be recorded as a clean scan")


TESTS = [
    test_cvss_known_vectors,
    test_high_cvss_fails,
    test_score_at_seven_fails_and_below_does_not,
    test_database_severity_is_only_a_fallback,
    test_cvss_v4_is_not_cvss_v3,
    test_no_score_and_no_severity_fails,
    test_exception_matches_one_id_and_version,
    test_missing_exception_file_skips_nothing,
    test_graph_errors_fail_closed,
    test_codeql_high_security_result_fails,
    test_codeql_has_no_exception_path,
    test_codeql_job_has_no_baseline_or_noop_success,
    test_real_workflows_match_the_named_sets,
    test_workflow_rejects_bad_pins_and_input,
    test_pages_and_dispatch_rules,
    test_wip_sha_must_be_rejected_before_it_is_a_ref,
    test_dependency_scan_script_fails_closed_on_resolver_error,
]


def main() -> int:
    failed = []
    for test in TESTS:
        try:
            test()
        except Exception as exc:  # noqa: BLE001 - report every case, then fail.
            failed.append(f"{test.__name__}: {exc}")
    if failed:
        print("\n".join(failed), file=sys.stderr)
        return 1
    print(f"{len(TESTS)} gate cases passed")
    return 0


if __name__ == "__main__":
    raise SystemExit(main())
