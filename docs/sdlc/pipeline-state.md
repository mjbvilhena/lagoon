# Pipeline state

Inspection date: 2026-10-18. Conductor: `sdlc-conductor`. Runtime graph: `get_sdlc_template("lifecycle pipeline")` from harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`.

Templates and Definition of Done checks were read with `get_sdlc_template` and `get_definition_of_done` from harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604` (`lifecycle pipeline`, `domain`, `layer`, `adr`, `threat model`, `security change`). `get_domain_consultant("vault")` and `get_layer_consultant` for `ui`, `domain`, and `data` now resolve to the Proposed files below. Capture and knowledge are not separate domains.

## Legal state

| State | Evidence | Status |
| --- | --- | --- |
| Vision | `docs/product/vision.md` | Approved by mjbvilhena on 2026-10-17. |
| Product spec | `docs/product/specification.md` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18. The passphrase amendment is included in that approval. |
| Epic approval | `docs/product/epics/` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18 for E1, E3, E4, E8, E9, and E11. The E1 full-screen sentence for US-85 is approved. The passphrase amendment is included in that approval. E1–E9 and E11 are v1. E10 is Later, not in v1, and has no stories. |
| Story refine | `docs/product/stories/` and `INDEX.md` | US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Numbers are not reused. |
| Story approval | US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, US-86 | Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The other stories stay Draft, except US-66, which is Proposed for story review and is not approved. US-73, US-74, and US-76 are placeholders and are not ready for review. Security criteria approved by mjbvilhena on 2026-10-18. |
| Technical design / ADRs | `docs/adr/ADR-0001-argon2id-vault-key.md`, `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`, `docs/adr/ADR-0003-topic-call.md`, `docs/adr/ADR-0004-ml-kit-telemetry.md`, `docs/adr/ADR-0005-pdf-renderer-api-26.md`, `docs/adr/ADR-0006-cipher-and-container.md` | Proposed. Needs approval by mjbvilhena. Not accepted. ML Kit research input remains `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`. ADR-0006 proposes the cipher and container. The specification still says the cipher waits for an ADR. That sentence is not edited. The ADRs themselves are not accepted. |
| UX | No UX artefact | Not started. |
| Test strategy / threat model | `docs/technical_design/threat-model.md` | Threat model Proposed. Needs approval by mjbvilhena. Not accepted. Test strategy is not started. `docs/technical_design/2026-10-17-proposed-threat-note.md` stays an earlier hypothesis list. |
| Domain / layer | `docs/domain/vault/DOMAIN.md`, `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, `docs/layers/data/LAYER.md` | Proposed. Needs approval by mjbvilhena. Not accepted. |
| Repository checks | `.github/workflows/qa.yml` | In place. Not a product approval. The workflow runs seven jobs. The Protect master ruleset requires all seven. See `docs/sdlc/repository-setup.md`. |
| Implement | No application code | Not started. |

## Stop

**Epic gate passed.** US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. The other stories stay Draft, except US-66, which is Proposed for story review and is not approved. Domain, layer, ADRs, and the threat model are Proposed and are not accepted. Implementation has not started.

The public repository is `github.com/mjbvilhena/lagoon`. The default branch is `master`. Changes go through a pull request. `.github/workflows/qa.yml` runs Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, and CodeQL. The Protect master ruleset requires those seven checks, and the branch must be up to date. There are no bypass actors. The Android jobs no-op until a Gradle project exists.

## Requirements in force

- App lock is optional. The device PIN is the fallback when there are no biometrics.
- One tap on an image or PDF page opens it full-screen at maximum brightness, with pinch-zoom and the screen kept on. Brightness goes back to normal when you close it. It works offline. A sensitive item needs unlocking first and is protected from screenshots (US-85). Amendment approved by mjbvilhena on 2026-10-18.
- Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses. Forgetting the passphrase and losing the device still means the content is lost. The method stays in the US-74 ADR.
- Ordinary lock-screen reminders may show the title and codes. A code is text already in the title. There is no separate code field. A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked, after the first unlock since boot. Sensitive content still needs the US-37 unlock.
- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- A token-looking URL is not auto-fetched.
- ML Kit: disclose the metrics and require acceptance before the first OCR use. There is no telemetry off switch. The bundled model is used, and ML Kit starts only after acceptance.
- Topic extraction starts on for a new ordinary item, with an off switch. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. There is no cap.
- An empty title fills from the page title or the file name. Camera capture uses the system camera. Lagoon does not request `CAMERA`.
- Upcoming is the one reminder or the one expiry. Alarms are inexact. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.
- v1 is epics E1–E9 and E11. E10 is later.

## Open questions

- Sync conflicts and how a deletion propagates (US-73). Proposed answer: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. Not accepted. The story stays a placeholder. Proposed amendment. Needs approval by mjbvilhena. The in-trash field syncs, and the tombstone spreads only after 30 × 24 hours. A lost phone's file is retired only through "Forget this phone". This is not approved.
- How the vault key reaches a new device (US-74). Proposed answer: `docs/adr/ADR-0001-argon2id-vault-key.md`. Not accepted. The story stays a placeholder. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase. The forgotten-passphrase reset delay is decided in that ADR, and so is the unlock it accepts: the US-37 biometric or device PIN. The Drive copy keeps the old passphrase for 24 hours, and another phone that holds the key can cancel. A cancel keeps items saved on the resetting phone during those 24 hours. The spec amendment is not approved. The passphrase is 12 to 128 Unicode code points after NFC, the bundled common-password list is blocked, and there is no strength meter. A phone that cannot meet the Argon2id memory floor refuses to set up a vault and does not lower the parameters. A phone without secure hardware can still create a vault after he accepts the warning that someone with the phone could extract the key. That warning stays in Settings and is not a second gate.
- Topic provider, retention, credential supply, and payload (US-76). Proposed answer: `docs/adr/ADR-0003-topic-call.md`. Not accepted. The story stays a placeholder. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not confirm `x-zero-data-retention` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once, "Send N earlier items for topics?", and the default is No. N is the number of earlier ordinary items on this install that have no topic provenance. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies.

## History

The specification and epics E1–E11 were approved on 2026-10-17. The specification amendment of 2026-10-18 is approved. Amendments to E1, E3, E4, E8, E9, and E11 were approved on 2026-10-18. US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. The other stories stay Draft. US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Security criteria on US-35, US-64, US-75, US-78, US-79, US-80, US-81, and US-86 were approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-18 for the US-85 E1 wording. Amendment approved by mjbvilhena on 2026-10-18 for the passphrase-derived vault key. An empty title fills from the file name. Camera capture uses the system camera and does not request `CAMERA`. Upcoming is the one reminder or the one expiry. Alarms are inexact. Topic suggestions have no cap.

## Memory

| Check | State |
| --- | --- |
| Vision ↔ spec | The vision states the current product. The specification is approved. |
| User-story index | `docs/product/stories/INDEX.md`. Review pack: `docs/product/stories/REVIEW-PACK.md`. Traceability: `docs/product/stories/TRACEABILITY.md`. |
| Threat model | `docs/technical_design/threat-model.md`. Proposed. Needs approval by mjbvilhena. The 2026-10-17 note is not this model. |
| Domain and layers | `docs/domain/vault/DOMAIN.md` and the three `docs/layers/*/LAYER.md` files. Proposed. Needs approval by mjbvilhena. |
| ML Kit input | `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`. Input only. The decision record is ADR-0004, still Proposed. |

UX and the test strategy are not started. Technical design and the threat model are drafted and not accepted. Implementation stays closed.
