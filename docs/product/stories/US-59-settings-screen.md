# US-59 Use one Settings screen

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

Use one Settings screen.

## User story

**As a** person named Miguel
**I want** theme, sync, the reminder default, app lock, the topic controls, and the ML Kit disclosure to live on one Settings screen
**So that** I do not hunt through the app for those controls

## Background

- This screen hosts controls defined by other stories. It does not add Back up now.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: the sign-out behavior. The row is only a slot. The behavior is US-78.
- The topic job's own "Unmetered only" control (US-44) is separate from the sync preference.
- The ML Kit disclosure is a slot. The behavior is US-07. It is not a telemetry off switch. Google's docs do not state a switch. Settings can withdraw the acceptance.
- The topic switch starts on. He can turn it off. The behavior is US-41. The topic "Unmetered only" setting stays separate (US-44).
- Amendment approved by mjbvilhena on 2026-10-22. Trash is opened from this screen. The behavior is US-66. The layout waits for the UX artefact.

## Acceptance criteria

- **Scenario: the controls** — Must
  - Given he opens Settings
  - When the screen is shown
  - Then he sees the theme (US-22), dynamic color where US-53 shows it, sync state (US-17), the sync unmetered preference (US-19), sign-in (US-18), the reminder default time (US-34), the app-lock toggle (US-37), the notification-permission state (US-34), a sign-out slot whose behavior is US-78, the zip export and import entry (US-21, US-52), the topic switch (US-41, on until he turns it off), and the topic "Unmetered only" setting (US-44)
- **Scenario: no backup controls** — Must
  - Given that screen
  - When he reads it
  - Then there is no Back up now, no Restore, and no last-backup time
- **Scenario: the topic switch** — Must
  - Given a fresh install
  - When he opens Settings
  - Then the topic switch is on, and he can turn it off
- **Scenario: ML Kit disclosure** — Must
  - Given he opens Settings
  - When the screen is shown
  - Then he can read the same disclosure US-07 requires before the first OCR use, he can see whether he has accepted it, and he can withdraw that acceptance. There is no control that disables ML Kit usage or diagnostic telemetry inside the SDK
- **Scenario: forget this phone** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Amendment approved by mjbvilhena on 2026-10-22. This scenario is Must. The US-37 unlock is required first. A cancelled or failed unlock changes nothing, as in US-87.
  - Given another install has a records file
  - When he chooses "Forget this phone" and names that other install
  - Then that install's records file is deleted from Drive
  - And this phone's own file is not deleted
  - And the passphrase does not change
  - And no job retires a file because of age, silence, or a missed check-in
  - And the behavior is US-87
  - And this phone writes that install's id into this phone's forgotten-installs file and reads that file back before that records file is deleted
  - And if that write or that read-back fails, nothing is deleted and he is told
  - Amendment approved by mjbvilhena on 2026-10-22. Each phone that runs Forget this phone writes only its own forgotten-installs file, and no other phone writes that file, except a re-key, which rewrites every forgotten-installs file under the new key version and never deletes one. Forget this phone writes that other install's id into this phone's forgotten-installs file and reads that file back before it deletes the other install's records file. If that write or that read-back fails, nothing is deleted and he is told.
  - Amendment approved by mjbvilhena on 2026-10-22. A returning phone reads every forgotten-installs file. It treats itself as forgotten only when every one of those files was read successfully and one of them contains its install id. If any read fails, or the listing fails, it keeps the local vault, does not upload, and retries at a later sync. The trigger is the next sync that finds the entry. After rebuilding, the phone rejoins with a new install id. It uploads a new records file under its new install id and the current key version, and the old id stays in the forgotten-installs file.
- **Scenario: the topic key and the call count** — Should
  - Security criteria approved by mjbvilhena on 2026-10-22.
  - Given this phone has no topic key stored
  - When he opens Settings
  - Then Settings says the key is not copied from the other phone and must be typed again
  - And Settings shows how many topic calls this install has made this month
  - And that count is not a cap and is not synced
- **Scenario: Trash** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given an item is in trash
  - When he opens Settings
  - Then he can open Trash from this screen
  - And the Trash behavior is US-66
  - And this is the entry to the Must trash view
- **Scenario: no passphrase recall check** — Should
  - Security criteria approved by mjbvilhena on 2026-10-22.
  - Given he opens Settings
  - When the screen is shown
  - Then there is no "Check my passphrase" control
  - And the app shows no periodic reminder to re-enter the passphrase

## Analytics and observability (optional)

This screen does not add an analytics library. The ML Kit disclosure is US-07.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Error text from US-51 appears on this screen.

## Implementation notes

Not a design spec.
