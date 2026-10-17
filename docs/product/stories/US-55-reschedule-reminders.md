# US-55 Reschedule reminders after a restart or a clock change

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E9. Split from US-34.

## Title

Reschedule reminders after a restart or a clock change.

## User story

**As a** person named Miguel
**I want** reminders and expiries to be registered again after a reboot, a clock change, an update, or a permission change
**So that** a notification is not lost because the phone restarted

## Background

- Alarms stay inexact. The time is the device local zone, which is what the specification means by local. A fixed home zone is an open question for Miguel, not a Must.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77).

## Acceptance criteria

- **Scenario: boot, clock, update, permission** — Must
  - Given a reminder or expiry is stored
  - When the device boots, the clock or time zone changes, the app is updated, or notification permission changes
  - Then the inexact alarms are registered again from the stored local time
- **Scenario: delete and undo** — Must
  - Given he deletes the item
  - When the delete is pending
  - Then the notification is cancelled, and Undo restores it
- **Scenario: no duplicate across devices** — Must
  - Given the same reminder id has synced to two devices
  - When the time arrives
  - Then only one device posts the notification

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-13 also restores reminders on Undo.

## Implementation notes

Not a design spec.
