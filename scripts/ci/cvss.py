"""CVSS v3.0 and v3.1 base scores.

Temporal and environmental metrics are ignored. The round-up is the
CVSS v3.1 reference rule.
"""

from __future__ import annotations

import math

_AV = {"N": 0.85, "A": 0.62, "L": 0.55, "P": 0.2}
_AC = {"L": 0.77, "H": 0.44}
_UI = {"N": 0.85, "R": 0.62}
_CIA = {"N": 0.0, "L": 0.22, "H": 0.56}
_PR_UNCHANGED = {"N": 0.85, "L": 0.62, "H": 0.27}
_PR_CHANGED = {"N": 0.85, "L": 0.68, "H": 0.5}


class CvssError(Exception):
    """The vector is not a CVSS v3 base vector this gate can score."""


def round_up(value: float) -> float:
    scaled = int(round(value * 100000))
    if scaled % 10000 == 0:
        return scaled / 100000.0
    return (math.floor(scaled / 10000) + 1) / 10.0


def base_score(vector: str) -> float:
    """Return the CVSS v3 base score for ``vector``."""
    text = vector.strip()
    if text.startswith("CVSS:3.0/"):
        body = text[len("CVSS:3.0/") :]
    elif text.startswith("CVSS:3.1/"):
        body = text[len("CVSS:3.1/") :]
    else:
        raise CvssError(f"not a CVSS v3 vector: {vector}")
    metrics = {}
    for part in body.split("/"):
        if ":" not in part:
            raise CvssError(f"bad CVSS metric: {part}")
        key, value = part.split(":", 1)
        metrics[key] = value
    try:
        attack = _AV[metrics["AV"]]
        complexity = _AC[metrics["AC"]]
        user = _UI[metrics["UI"]]
        scope = metrics["S"]
        confidentiality = _CIA[metrics["C"]]
        integrity = _CIA[metrics["I"]]
        availability = _CIA[metrics["A"]]
    except KeyError as exc:
        raise CvssError(f"missing or unknown CVSS metric: {exc}") from exc
    if scope == "U":
        privileges = _PR_UNCHANGED[metrics["PR"]]
    elif scope == "C":
        privileges = _PR_CHANGED[metrics["PR"]]
    else:
        raise CvssError(f"unknown scope: {scope}")
    exploitability = 8.22 * attack * complexity * privileges * user
    impact_sub = 1 - ((1 - confidentiality) * (1 - integrity) * (1 - availability))
    if scope == "U":
        impact = 6.42 * impact_sub
    else:
        impact = 7.52 * (impact_sub - 0.029) - 3.25 * ((impact_sub - 0.02) ** 15)
    if impact <= 0:
        return 0.0
    if scope == "U":
        raw = min(impact + exploitability, 10)
    else:
        raw = min(1.08 * (impact + exploitability), 10)
    return round_up(raw)
