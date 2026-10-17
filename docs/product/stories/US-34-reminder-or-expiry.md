# US-34 Set a reminder or expiry and get notified

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> His decision that reminders are in v1 is not approval of this story.
> The schedule below is a consensus recommendation from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot under that delegation. Not approval.

## Title

Set an optional date and be notified.

## User story

**As a** person named Miguel
**I want to** put an optional reminder or expiry date on an item and be notified
**So that** something I saved can come back on the day it matters

## Background

- He decided this is in v1. The date is optional.
- He picks the time. Settings hold a default of 09:00 local. Each item can override that time.
- An expiry notifies 7 days before, and again on the day, both at the item's time.
- Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). No exact-alarm permission.
- Reminders are persisted and re-registered on `BOOT_COMPLETED`.
- `POST_NOTIFICATIONS` is requested when the first reminder is set, not at launch.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a date on every item by default, repeating reminders, exact alarms, and trip grouping (E10, not v1).

## Acceptance criteria

- **Scenario: set a reminder** — Must
  - Given a saved item and a Settings default of 09:00 local
  - When he sets a reminder date and does not pick a time
  - Then the item shows that date at 09:00 local
- **Scenario: he picks a time** — Must
  - Given he sets a reminder
  - When he overrides the time on that item
  - Then the item uses his time, and the Settings default is unchanged
- **Scenario: change the default** — Must
  - Given he changes the Settings default
  - When he later sets a reminder and does not override the time
  - Then the new default is used. Items that already have a time keep theirs
- **Scenario: expiry** — Must
  - Given he sets an expiry date
  - When the schedule is stored
  - Then a notification is due 7 days before that date and another on the day, each at the item's time (the override, or 09:00 local)
- **Scenario: the time arrives** — Must
  - Given the date and time are set and notification permission is granted
  - When that time arrives
  - Then he receives a notification that names the item
- **Scenario: inexact alarms** — Must
  - Given a reminder is scheduled
  - When the app registers it
  - Then it uses `setWindow` or `setAndAllowWhileIdle`, and it does not request the exact-alarm permission
- **Scenario: reboot** — Must
  - Given reminders are stored
  - When the device sends `BOOT_COMPLETED`
  - Then those reminders are re-registered
- **Scenario: permission timing** — Must
  - Given he has not set a reminder in this install
  - When he opens the app
  - Then it does not ask for `POST_NOTIFICATIONS`
  - And when he sets the first reminder, it asks then
- **Scenario: no date** — Must
  - Given an item with no reminder or expiry
  - When days pass
  - Then this feature does not notify him about that item
- **Scenario: clear the date** — Must
  - Given an item with a date
  - When he clears it
  - Then the date is gone and no later notification fires for it
- **Scenario: notifications denied** — Must
  - Given he has denied notification permission
  - When he sets a date
  - Then the date is still stored on the item, and he can see it there

## Analytics and observability (optional)

Not applicable. No analytics. A local notification is not product analytics.

## Design and a11y notes

- The date control and the time control have text labels. The notification's text includes the item title.
- 09:00 is local time. The Settings label says that.

## Dependencies and risks

- Uses the platform notification permission. Do not add a second messaging channel.
- Not a calendar integration.
- Inexact delivery means the notification can land inside the platform window, not on the exact minute. This story does not ask for exact alarms to close that gap.

## Implementation notes

Persist the reminder records. Re-register on boot. Not a design spec. Not approval.
