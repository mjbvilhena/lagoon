"""US-88 severity gate over osv-scanner JSON.

A finding fails when its CVSS v3 base score is 7.0 or higher. When CVSS v3
is absent, database severity HIGH or CRITICAL fails, and so does a finding
with neither a score nor a database severity. An exception matches one OSV
id and that exact resolved ``group:name:version`` only.
"""

from __future__ import annotations

import json
import sys
from datetime import datetime
from pathlib import Path

from cvss import CvssError, base_score

FAIL_AT = 7.0
APPROVAL = "Exception approved by mjbvilhena on"
HIGH_SEVERITIES = {"HIGH", "CRITICAL"}
LOW_SEVERITIES = {"LOW", "MEDIUM", "MODERATE"}
KNOWN_SEVERITIES = HIGH_SEVERITIES | LOW_SEVERITIES


class GateError(Exception):
    """The scan cannot be treated as clean."""


def main(argv: list[str]) -> int:
    if len(argv) != 4:
        print(
            "usage: dependency_gate.py <osv.json> <gradle.lockfile> <exceptions.md>",
            file=sys.stderr,
        )
        return 1
    try:
        reasons = evaluate(Path(argv[1]), Path(argv[2]), Path(argv[3]))
    except GateError as exc:
        print(f"dependency scan failed closed: {exc}", file=sys.stderr)
        print("this is not a clean scan", file=sys.stderr)
        return 1
    if reasons:
        print("dependency scan failed:", file=sys.stderr)
        for reason in reasons:
            print(f"- {reason}", file=sys.stderr)
        print("this is not a clean scan", file=sys.stderr)
        return 1
    print("dependency scan: no failing finding")
    return 0


def evaluate(osv_path: Path, lock_path: Path, exceptions_path: Path) -> list[str]:
    coordinates = lock_coordinates(lock_path)
    document = load_json(osv_path)
    seen = packages_in(document)
    missing = sorted(coordinates - seen)
    extra = sorted(seen - coordinates)
    if missing or extra:
        raise GateError(
            "scanner package set does not match the resolved graph "
            f"missing={missing[:8]} extra={extra[:8]}"
        )
    exceptions = exception_lines(exceptions_path)
    reasons = []
    for package in package_entries(document):
        coordinate = coordinate_of(package)
        for vulnerability in package.get("vulnerabilities") or []:
            identifier = vulnerability.get("id")
            if not isinstance(identifier, str) or identifier == "":
                raise GateError(f"vulnerability on {coordinate} has no OSV id")
            if should_fail(vulnerability):
                if excepted(identifier, coordinate, exceptions):
                    continue
                reasons.append(f"{identifier} {coordinate} {failure_label(vulnerability)}")
    return reasons


def lock_coordinates(path: Path) -> set[str]:
    if not path.is_file():
        raise GateError(f"resolved graph is missing: {path}")
    found = set()
    for raw in path.read_text(encoding="utf-8").splitlines():
        line = raw.strip()
        if line == "" or line.startswith("#") or line == "empty=":
            continue
        coordinate = line.split("=", 1)[0].strip()
        if coordinate.count(":") < 2:
            raise GateError(f"lock line is not group:name:version: {line}")
        found.add(coordinate)
    if not found:
        raise GateError("resolver produced no graph")
    return found


def load_json(path: Path) -> dict:
    if not path.is_file():
        raise GateError(f"scanner output is missing: {path}")
    try:
        document = json.loads(path.read_text(encoding="utf-8"))
    except json.JSONDecodeError as exc:
        raise GateError(f"scanner output is not JSON: {exc}") from exc
    if not isinstance(document, dict) or not document.get("results"):
        raise GateError("scanner output has no results")
    return document


def packages_in(document: dict) -> set[str]:
    return {coordinate_of(package) for package in package_entries(document)}


def package_entries(document: dict):
    for result in document.get("results") or []:
        if not isinstance(result, dict):
            raise GateError("scanner result is not an object")
        for package in result.get("packages") or []:
            if not isinstance(package, dict):
                raise GateError("scanner package is not an object")
            yield package


def coordinate_of(package: dict) -> str:
    info = package.get("package") or {}
    name = info.get("name")
    version = info.get("version")
    if not isinstance(name, str) or not isinstance(version, str):
        raise GateError("scanner package has no name and version")
    return f"{name}:{version}"


def should_fail(vulnerability: dict) -> bool:
    score = cvss_v3_score(vulnerability)
    if score is not None:
        return score >= FAIL_AT
    severity = database_severity(vulnerability)
    if severity is None:
        return True
    return severity in HIGH_SEVERITIES


def failure_label(vulnerability: dict) -> str:
    score = cvss_v3_score(vulnerability)
    if score is not None:
        return f"CVSS v3 {score}"
    severity = database_severity(vulnerability)
    if severity is None:
        return "no CVSS v3 score and no database severity"
    return f"database severity {severity}"


def cvss_v3_score(vulnerability: dict) -> float | None:
    scores = []
    for entry in vulnerability.get("severity") or []:
        if not isinstance(entry, dict):
            continue
        if entry.get("type") != "CVSS_V3":
            continue
        raw = entry.get("score")
        if isinstance(raw, (int, float)) and not isinstance(raw, bool):
            scores.append(float(raw))
            continue
        if not isinstance(raw, str):
            raise GateError(f"CVSS v3 score is not readable on {vulnerability.get('id')}")
        text = raw.strip()
        if text.startswith("CVSS:3."):
            try:
                scores.append(base_score(text))
            except CvssError as exc:
                raise GateError(str(exc)) from exc
            continue
        try:
            scores.append(float(text))
        except ValueError as exc:
            raise GateError(f"CVSS v3 score is not a number on {vulnerability.get('id')}") from exc
    if not scores:
        return None
    return max(scores)


def database_severity(vulnerability: dict) -> str | None:
    specific = vulnerability.get("database_specific")
    if not isinstance(specific, dict):
        return None
    raw = specific.get("severity")
    if not isinstance(raw, str) or raw.strip() == "":
        return None
    severity = raw.strip().upper()
    if severity not in KNOWN_SEVERITIES:
        return None
    return severity


def exception_lines(path: Path) -> list[str]:
    if not path.exists():
        return []
    if not path.is_file():
        raise GateError(f"exceptions path is not a file: {path}")
    return path.read_text(encoding="utf-8").splitlines()


def excepted(identifier: str, coordinate: str, lines: list[str]) -> bool:
    for line in lines:
        if line_excepts(line, identifier, coordinate):
            return True
    return False


def line_excepts(line: str, identifier: str, coordinate: str) -> bool:
    if not _has_token(line, identifier):
        return False
    if not _has_coordinate(line, coordinate):
        return False
    if not _has_approval_date(line):
        return False
    return _has_reason(line, identifier, coordinate)


def _has_token(line: str, token: str) -> bool:
    start = 0
    while True:
        found = line.find(token, start)
        if found < 0:
            return False
        before = line[found - 1] if found else ""
        after = line[found + len(token) : found + len(token) + 1]
        if (before == "" or not _id_char(before)) and (after == "" or not _id_char(after)):
            return True
        start = found + 1


def _has_coordinate(line: str, coordinate: str) -> bool:
    start = 0
    while True:
        found = line.find(coordinate, start)
        if found < 0:
            return False
        before = line[found - 1] if found else ""
        after_index = found + len(coordinate)
        after = line[after_index] if after_index < len(line) else ""
        if (before == "" or not _version_char(before)) and (
            after == "" or not _version_char(after)
        ):
            return True
        start = found + 1


def _has_approval_date(line: str) -> bool:
    marker = f"{APPROVAL} "
    start = line.find(marker)
    if start < 0:
        return False
    rest = line[start + len(marker) :].lstrip()
    date = rest.split()[0] if rest.split() else ""
    if len(date) < 10:
        return False
    try:
        datetime.strptime(date[:10], "%Y-%m-%d")
    except ValueError:
        return False
    return True


def _has_reason(line: str, identifier: str, coordinate: str) -> bool:
    remainder = line.replace(identifier, " ", 1).replace(coordinate, " ", 1)
    marker = remainder.find(APPROVAL)
    if marker >= 0:
        remainder = remainder[:marker] + " " + remainder[marker + len(APPROVAL) :]
    words = [word.strip(".,;:()[]") for word in remainder.split()]
    return any(len(word) >= 3 and any(char.isalpha() for char in word) for word in words)


def _id_char(char: str) -> bool:
    return char.isalnum() or char == "-"


def _version_char(char: str) -> bool:
    return char.isalnum() or char in "._+-:"


if __name__ == "__main__":
    sys.path.insert(0, str(Path(__file__).resolve().parent))
    raise SystemExit(main(sys.argv))
