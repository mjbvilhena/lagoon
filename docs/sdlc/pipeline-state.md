# Pipeline state

Inspection date: 2026-10-18. Conductor: `sdlc-conductor`. Runtime graph: `get_sdlc_template("lifecycle pipeline")` from harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. No `DOMAIN.md` or `LAYER.md` was found. No MUST/NEVER rules were invented.

## Legal state

| State | Evidence | Status |
| --- | --- | --- |
| Vision | `docs/product/vision.md` | Approved by mjbvilhena on 2026-10-17. |
| Product spec | `docs/product/specification.md` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18. |
| Epic approval | `docs/product/epics/` | Approved by mjbvilhena on 2026-10-17. Where an epic file has an amendment line, that line is also approved by mjbvilhena on 2026-10-17. E1 has one proposed full-screen sentence for US-85. That sentence needs approval by mjbvilhena. It is not approved. E1–E9 and E11 are v1. E10 is Later, not in v1, and has no stories. |
| Story refine | `docs/product/stories/` and `INDEX.md` | Draft. Not approved. US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Numbers are not reused. |
| Story approval | No story signed | Not given. |
| Technical design / ADRs | No ADR | Not started. ML Kit research input: `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`. |
| UX | No UX artefact | Not started. |
| Test strategy / threat model | `docs/technical_design/2026-10-17-proposed-threat-note.md` | Not started as a gate. The note is unapproved hypotheses. It does not open the design band. |
| Domain / layer | No `DOMAIN.md` or `LAYER.md` | Not started. |
| Repository checks | `.github/workflows/qa.yml` | In place. Not a product approval. The workflow runs seven jobs. The Protect master ruleset requires all seven. See `docs/sdlc/repository-setup.md`. |
| Implement | No application code | Not started. |

## Stop

**Epic gate passed. Stopped at story review.** Stories stay Draft. Implementation has not started.

The public repository is `github.com/mjbvilhena/lagoon`. The default branch is `master`. Changes go through a pull request. `.github/workflows/qa.yml` runs Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, and CodeQL. The Protect master ruleset requires those seven checks, and the branch must be up to date. There are no bypass actors. The Android jobs no-op until a Gradle project exists.

## Requirements in force

- Lost key: the content is lost. No recovery. App lock is optional. The device PIN is the fallback when there are no biometrics.
- Ordinary lock-screen reminders may show the title and codes. A code is text already in the title. There is no separate code field. A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- There is no per-item topics control in v1. Sensitive items are always excluded, and the Settings switch covers everything else.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- A token-looking URL is not auto-fetched.
- ML Kit: disclose the metrics and require acceptance before the first OCR use. There is no telemetry off switch. The bundled model is used, and ML Kit starts only after acceptance.
- Topic extraction starts on for a new ordinary item, with an off switch. There is no per-item topics control in v1. Sensitive items are always excluded, and the Settings switch covers everything else. There is no cap.
- An empty title fills from the page title or the file name. Camera capture uses the system camera. Lagoon does not request `CAMERA`.
- Upcoming is the one reminder or the one expiry. Alarms are inexact. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.
- v1 is epics E1–E9 and E11. E10 is later.

## Open questions

- Sync conflicts and how a deletion propagates (US-73). Criteria follow from the ADR at the design gate.
- How the vault key reaches a new device (US-74). Criteria follow from the ADR at the design gate.
- Topic provider, retention, credential supply, and payload (US-76). Criteria follow from the ADR at the design gate.
- Full-screen image view is US-85. It is pending approval of the E1 amendment.

## History

The specification and epics E1–E11 were approved on 2026-10-17. The specification amendment of 2026-10-18 is approved. Stories were drafted and remain Draft. US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Security criteria on US-64, US-75, US-78, US-79, US-80, and US-81 were approved by mjbvilhena on 2026-10-18. Those stories stay Draft. US-85 is Draft. Its E1 wording is a proposed amendment and needs approval by mjbvilhena. An empty title fills from the file name. Camera capture uses the system camera and does not request `CAMERA`. Upcoming is the one reminder or the one expiry. Alarms are inexact. Topic suggestions have no cap.

## Memory

| Check | State |
| --- | --- |
| Vision ↔ spec | The vision states the current product. The specification is approved. |
| User-story index | `docs/product/stories/INDEX.md`. Review pack: `docs/product/stories/REVIEW-PACK.md`. Traceability: `docs/product/stories/TRACEABILITY.md`. |
| Threat note | `docs/technical_design/2026-10-17-proposed-threat-note.md`. Not an approved threat model. |
| ML Kit input | `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`. Not an ADR. |

UX, an approved threat model, and technical design stay closed until story approval, or until a skip is recorded here. Implementation stays closed.
