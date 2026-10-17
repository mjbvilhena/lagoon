# US-19 Run a daily backup and see when it last succeeded

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Turn on a daily automatic backup.

## User story

**As a** person named Miguel
**I want** a daily automatic backup I can limit to Wi-Fi, and I want to see the last backup time
**So that** I do not have to remember to tap Back up now, and I do not have to upload on mobile data unless I choose to

## Background

- He asked for automatic backup, with daily via WorkManager as the example, a Wi-Fi only toggle, and the last backup time.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: choosing the hour of day. He did not name one. Back up now is US-18.

## Acceptance criteria

- **Scenario: turn automatic backup on** — Must
  - Given he is signed in on a configured build
  - When he turns automatic backup on
  - Then a periodic backup is scheduled about once a day using WorkManager
- **Scenario: Wi-Fi only on** — Must
  - Given automatic backup is on and the Wi-Fi only toggle is on
  - When the device has only mobile data
  - Then the automatic backup does not upload
  - And when the device is later on Wi-Fi, the scheduled backup may run
- **Scenario: Wi-Fi only off** — Must
  - Given automatic backup is on and the Wi-Fi only toggle is off
  - When the scheduled time arrives on mobile data
  - Then the automatic backup is allowed to run on that network
- **Scenario: last backup time** — Must
  - Given a backup has succeeded, whether manual or automatic
  - When he opens Settings
  - Then he sees the time of that last success
- **Scenario: automatic backup off** — Must
  - Given he turns automatic backup off
  - When a day passes
  - Then no automatic backup runs. Back up now still works.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Both toggles have text labels and state a screen reader can hear.
- The last backup time is text. If there has never been one, the screen says that, not a fake timestamp.

## Dependencies and risks

- Blocked by US-18 for a signed-in, configured build.
- "About once a day" follows WorkManager's periodic work. An exact clock time is not required.
- Q6 is assumed as written in the spec: the toggle governs automatic backup, not Back up now.

## Implementation notes

WorkManager periodic work with a network constraint when the toggle is on. Not a design spec.
