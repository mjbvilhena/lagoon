# US-34 Set a reminder

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E9.

**MoSCoW:** Must. v1. Inexact alarms.

## Title

Set a reminder.

## User story

**As a** person named Miguel
**I want** to set an optional reminder and be notified
**So that** I do not forget the item

## Background

- A reminder is one optional date-time. An expiry is a different kind of date and is US-54. An item has at most one of them, matching the specification's "expiry or reminder".
- Default time is 09:00 in the phone's current local zone, with a per-item override. Reminders follow that zone, so a 09:00 reminder fires at 09:00 wherever the phone is. A time-zone change is US-55.
- A lock-screen notification for an ordinary item may show the item title and codes. A code is text already in that title. There is no separate code field. US-63 is removed, so there is no mark-as-code action. A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code (US-75).
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: exact alarms, expiry lead times (US-54), reschedule triggers (US-55). Sensitive lock-screen text is decided on US-75.

## Acceptance criteria

- **Scenario: set a reminder** — Must
  - Given an ordinary item
  - When he sets a reminder and does not pick a time
  - Then the time is 09:00 in the device local zone
- **Scenario: he picks a time** — Must
  - Given he picks a time on that item
  - When he saves
  - Then that time is kept for this item and the Settings default is unchanged
- **Scenario: a later default** — Must
  - Given an item already has a reminder time
  - When he changes the Settings default
  - Then that item's time stays as it was
- **Scenario: inexact alarms** — Must
  - Given the reminder is set
  - When the alarm is registered
  - Then it is inexact (`setWindow` or `setAndAllowWhileIdle`) and the app does not request the exact-alarm permission
- **Scenario: the notification** — Must
  - Given the time arrives for an ordinary item
  - When the notification is posted, including on the lock screen
  - Then it shows the item title
- **Scenario: open it** — Must
  - Given he taps the notification
  - When the item is not sensitive
  - Then the item opens
- **Scenario: permission timing** — Must
  - Given API 33 or newer and he has never set a reminder
  - When he sets the first one
  - Then the app asks for `POST_NOTIFICATIONS` then, not at launch
  - And if he denies it, Settings says notifications are off and the item still keeps the date
- **Scenario: no date** — Must
  - Given he does not set a date
  - When time passes
  - Then no notification is posted
- **Scenario: clear the date** — Must
  - Given a reminder is set
  - When he clears it
  - Then the alarm is cancelled

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.
- Proposed amendment. Needs approval by mjbvilhena. Original: "Exact layout waits for the UX artefact, which does not exist." Replacement: "Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31)."

## Dependencies and risks

- Sensitive items: the body stays behind US-37. Lock-screen content for a sensitive item is US-75. The permission set beyond this notification is US-80.

## Implementation notes

Persist the reminder. Not a design spec. Not an approval of the story.
