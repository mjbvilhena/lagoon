"""US-89 gate. Fail the CodeQL job on a high security SARIF result.

There is no exception path. A rule id, a result location, and
``docs/sdlc/vulnerability-exceptions.md`` are not read. A missing SARIF
file fails the job.
"""

from __future__ import annotations

import json
import sys
from pathlib import Path

FAIL_AT = 7.0


class GateError(Exception):
    """Analysis output cannot be treated as a clean scan."""


def main(argv: list[str]) -> int:
    if len(argv) != 2:
        print("usage: codeql_gate.py <sarif-file-or-directory>", file=sys.stderr)
        return 1
    try:
        reasons = evaluate(Path(argv[1]))
    except GateError as exc:
        print(f"CodeQL failed closed: {exc}", file=sys.stderr)
        print("this is not a clean scan", file=sys.stderr)
        return 1
    if reasons:
        print("CodeQL failed on a high security result:", file=sys.stderr)
        for reason in reasons:
            print(f"- {reason}", file=sys.stderr)
        print("this is not a clean scan", file=sys.stderr)
        return 1
    print("CodeQL: no high security result")
    return 0


def evaluate(path: Path) -> list[str]:
    documents = load_sarif(path)
    reasons = []
    for document in documents:
        for run in document.get("runs") or []:
            if not isinstance(run, dict):
                raise GateError("SARIF run is not an object")
            rules = rules_in(run)
            for result in run.get("results") or []:
                if not isinstance(result, dict):
                    raise GateError("SARIF result is not an object")
                rule = rule_for(result, rules)
                if not tagged_security(result, rule):
                    continue
                score = security_severity(result, rule)
                if score is None:
                    continue
                if score >= FAIL_AT:
                    rule_id = result.get("ruleId") or (rule or {}).get("id") or "unknown-rule"
                    reasons.append(f"{rule_id} security-severity {score}")
    return reasons


def load_sarif(path: Path) -> list[dict]:
    files = sarif_files(path)
    if not files:
        raise GateError(f"SARIF file is missing under {path}")
    documents = []
    for item in files:
        try:
            document = json.loads(item.read_text(encoding="utf-8"))
        except json.JSONDecodeError as exc:
            raise GateError(f"SARIF is not JSON ({item.name}): {exc}") from exc
        if not isinstance(document, dict) or "runs" not in document:
            raise GateError(f"SARIF has no runs: {item.name}")
        documents.append(document)
    return documents


def sarif_files(path: Path) -> list[Path]:
    if path.is_file():
        return [path]
    if not path.is_dir():
        return []
    return sorted(item for item in path.rglob("*") if item.suffix == ".sarif" and item.is_file())


def rules_in(run: dict) -> dict:
    found = {}
    tool = run.get("tool") if isinstance(run.get("tool"), dict) else {}
    driver = tool.get("driver") if isinstance(tool.get("driver"), dict) else {}
    for index, rule in enumerate(driver.get("rules") or []):
        if isinstance(rule, dict):
            found[index] = rule
            if isinstance(rule.get("id"), str):
                found[rule["id"]] = rule
    for extension in tool.get("extensions") or []:
        if not isinstance(extension, dict):
            continue
        for rule in extension.get("rules") or []:
            if isinstance(rule, dict) and isinstance(rule.get("id"), str):
                found[rule["id"]] = rule
    return found


def rule_for(result: dict, rules: dict) -> dict | None:
    rule_id = result.get("ruleId")
    if isinstance(rule_id, str) and rule_id in rules:
        return rules[rule_id]
    rule_index = result.get("ruleIndex")
    if isinstance(rule_index, int) and rule_index in rules:
        return rules[rule_index]
    inline = result.get("rule")
    if isinstance(inline, dict):
        return inline
    return None


def tagged_security(result: dict, rule: dict | None) -> bool:
    return "security" in tags_of(result) or "security" in tags_of(rule)


def tags_of(node: dict | None) -> set[str]:
    if not isinstance(node, dict):
        return set()
    properties = node.get("properties")
    if not isinstance(properties, dict):
        return set()
    tags = properties.get("tags")
    if not isinstance(tags, list):
        return set()
    return {tag for tag in tags if isinstance(tag, str)}


def security_severity(result: dict, rule: dict | None) -> float | None:
    for node in (result, rule):
        if not isinstance(node, dict):
            continue
        properties = node.get("properties")
        if not isinstance(properties, dict) or "security-severity" not in properties:
            continue
        return parse_severity(properties.get("security-severity"), result.get("ruleId"))
    return None


def parse_severity(raw, rule_id) -> float:
    if isinstance(raw, (int, float)) and not isinstance(raw, bool):
        return float(raw)
    if isinstance(raw, str):
        try:
            return float(raw.strip())
        except ValueError as exc:
            raise GateError(f"security-severity is not a number on {rule_id}") from exc
    raise GateError(f"security-severity is not a number on {rule_id}")


if __name__ == "__main__":
    raise SystemExit(main(sys.argv))
