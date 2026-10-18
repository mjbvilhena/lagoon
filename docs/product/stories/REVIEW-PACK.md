# Story review pack

> **Approval status: Draft. Not approved.**
> Listing a story here is not a sign-off.

US-15, US-31, US-34, US-40, US-41, US-50, US-61, US-62, US-64, US-65, US-75, US-78, US-79, and US-80 are approved. The other stories stay Draft. The map is `docs/product/stories/TRACEABILITY.md`. The index is `docs/product/stories/INDEX.md`.

## Requirements

- App lock is optional. The device PIN is the fallback when there are no biometrics. The vault key does not require user authentication.
- Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses, so entering it on any device re-creates the same key. No copy of the key is stored on Drive or anywhere else. Proposed amendment. Needs approval by mjbvilhena. 'Stored on Drive or anywhere else' changes to 'stored anywhere off the phone.' Forgetting the passphrase and losing the device still means the content is lost. Security criteria approved by mjbvilhena on 2026-10-18. US-35 stays Draft. Proposed additions on US-35 and US-81 need approval by mjbvilhena. Changing the passphrase is US-86. US-86 stays Draft. Proposed security criteria. Needs approval by mjbvilhena.
- An ordinary lock-screen reminder may show the item title and codes. A code is text already in that title. There is no separate code field. A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication, so background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- The zip export is encrypted with the vault key.
- A token-looking URL is not auto-fetched. A foggy placeholder is shown until that preview is enabled.
- Before the first OCR use, he accepts a disclosure: the image and the recognized text stay on the device, and ML Kit still sends usage and diagnostic metrics to Google. Decline, or no answer, means OCR does not run and ML Kit is not started. He can withdraw that acceptance in Settings. There is no telemetry off switch. The research note is `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`.
- Topic extraction starts on for a new ordinary item. He can turn it off in Settings. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. There is no cap on topic suggestions. The topic job has its own unmetered setting.
- An empty title fills from the page title or the file name, including a screenshot, image, or scan.
- Camera capture uses the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`.
- The Upcoming list is the one reminder or the one expiry. It does not include a return-by date or a warranty end.
- Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.

## Open questions

- What happens when the same item was edited on two devices, and how does a deletion propagate? US-73. Criteria follow from the ADR at the design gate.
- How does the vault key reach a new device? US-74. Criteria follow from the ADR at the design gate. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase.
- Which topic provider is used, what it retains and whether it trains, how the credential is supplied, and what the call sends? US-76. Criteria follow from the ADR at the design gate. The APK must not contain the key in plaintext.

## Removed

US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Those numbers are not reused. Picking merchants out of a receipt stays later, with no story.

## Shape

Artefact shape is the user-story Definition of Done: As a / I want / So that, and Given / When / Then. An open question is not answered here.

US-15, US-31, US-34, US-40, US-41, US-50, US-61, US-62, US-64, US-65, US-75, US-78, US-79, and US-80 are approved. US-73, US-74, and US-76 have no Given/When/Then and no MoSCoW yet. They do not meet the user-story Definition of Done. They are placeholders waiting on the design-gate ADR. They are not ready for review. US-85 stays Draft. Amendment approved by mjbvilhena on 2026-10-18 for its E1 wording. US-86 stays Draft. Proposed security criteria. Needs approval by mjbvilhena. No story is Done. There is no app.

## Threat note

`docs/technical_design/2026-10-17-proposed-threat-note.md` is unapproved hypotheses. It is not a threat model. The design band is closed.
