# US-59 Use one Settings screen

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1. The review asked for one Settings screen for the controls that already exist.

## Title

Use one Settings screen.

## User story

**As a** person named Miguel
**I want** theme, sync, the reminder default, app lock, the topic controls, and the ML Kit disclosure to live on one Settings screen
**So that** I do not hunt through the app for those controls

## Background

- This screen hosts controls defined by other stories. It does not add Back up now.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: deciding sign-out. The row is only a slot. The behavior is the proposed US-78, pending Miguel's approval.
- The topic job's own "Unmetered only" control (US-44) is separate from the sync preference. Epic E11 includes that setting after the amendment approved on 2026-10-17 at 15:55 IST.
- The ML Kit disclosure is a slot. The behavior is US-07, decided by Miguel (`mjbvilhena`) on 2026-10-17 at 16:00 IST. It is not a telemetry off switch. Google's docs do not state a default, because they do not state a switch. Settings can withdraw the acceptance.
- The topic off switch stays default off. Whether that default should be on is an open question for Miguel. This story does not change it.

## Acceptance criteria

- **Scenario: the controls** — Must
  - Given he opens Settings
  - When the screen is shown
  - Then he sees the theme (US-22), dynamic color where US-53 shows it, sync state (US-17), the sync unmetered preference (US-19), sign-in (US-18), the reminder default time (US-34), the app-lock toggle (US-37), the notification-permission state (US-34), a sign-out slot whose behavior is US-78, the zip export and import entry (US-21, US-52), the topic off switch (US-41, default off), and the topic "Unmetered only" setting (US-44)
- **Scenario: no backup controls** — Must
  - Given that screen
  - When he reads it
  - Then there is no Back up now, no Restore, and no last-backup time
- **Scenario: ML Kit disclosure** — Must
  - Given he opens Settings
  - When the screen is shown
  - Then he can read the same disclosure US-07 requires before the first OCR use, he can see whether he has accepted it, and he can withdraw that acceptance. There is no control that disables ML Kit usage or diagnostic telemetry inside the SDK

## Analytics and observability (optional)

This screen does not add an analytics library. The ML Kit disclosure is US-07.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Error text from US-51 appears on this screen.

## Implementation notes

Not a design spec.
