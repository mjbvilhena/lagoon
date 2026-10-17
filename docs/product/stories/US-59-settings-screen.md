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
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the sign-out behavior. The row is only a slot. The behavior is US-78.
- The topic job's own "Unmetered only" control (US-44) is separate from the sync preference.
- The ML Kit disclosure is a slot. The behavior is US-07. It is not a telemetry off switch. Google's docs do not state a switch. Settings can withdraw the acceptance.
- The topic switch starts on. He can turn it off. The behavior is US-41. The topic "Unmetered only" setting stays separate (US-44).

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

## Analytics and observability (optional)

This screen does not add an analytics library. The ML Kit disclosure is US-07.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Error text from US-51 appears on this screen.

## Implementation notes

Not a design spec.
