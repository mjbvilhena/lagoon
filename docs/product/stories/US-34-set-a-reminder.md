# US-34 Set a reminder

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E9. Inexact alarms, per E9. Exact alarms are US-77 and are not decided.

## Title

Set a reminder.

## User story

**As a** person named Miguel
**I want** to set an optional reminder and be notified
**So that** I do not forget the item

## Background

- A reminder is one optional date-time. An expiry is a different kind of date and is US-54. An item has at most one of them, matching the specification's "expiry or reminder".
- Default time is 09:00 in the device's local zone, with a per-item override. Whether 09:00 should instead mean a fixed zone such as Europe/Dublin is still an open question for Miguel.
- On 2026-10-17 at 14:56 IST, Miguel (`mjbvilhena`) decided that a lock-screen notification for an ordinary item may show the item title and codes. On 2026-10-17 at 15:59 IST he narrowed that for a sensitive item only: US-75 shows a generic "Lagoon reminder" and no title or code until unlock.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
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
  - Then it shows the item title. It shows a code only when US-63 is built and he has marked a code on that item
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

## Dependencies and risks

- Sensitive items: the body stays behind US-37. Lock-screen content for a sensitive item is US-75. The permission set beyond this notification is US-80.

## Implementation notes

Persist the reminder. Not a design spec. Not an approval of the story.
