# Epic E9: Reminders and expiry

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17 at 20:02 IST.

## Background & Value Proposition

Some things he saves matter on a date: a ticket, a trial, a thing he wants to reread. He can put an optional date on an item and be notified. Items without a date behave as they do today.

No success metric was given.

## Target Audience

Miguel.

## Scope & Phasing

- **In Scope (MVP):**
  - An optional expiry or reminder date on an item.
  - He picks the time. The Settings default is 09:00 local. Each item can override that time.
  - An expiry notifies 7 days before the date and again on the day, at the item's time.
  - Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). No exact-alarm permission. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.
  - A reminder or an expiry is registered again after a reboot, a clock change, an update, or a notification-permission change (A-E9-1, US-55). When the same date is on more than one install, only one install posts it. Acceptance: a notification is not lost because the phone restarted, and two installs do not both post it.
  - A list shows items whose one reminder or one expiry is coming due (A-E9-2, US-62, Should). Acceptance: he can see what is due without opening each item. The list does not add a return-by date or a warranty end.
  - Reminders are stored and re-registered on `BOOT_COMPLETED`.
  - `POST_NOTIFICATIONS` is requested when the first reminder is set, not at launch.
  - Clearing the date clears the notification.
- **Future Scope (v2+):** Trip or collection grouping is E10, not this epic.
- **Out of Scope / Non-Goals:** A default expiry on every item. Recurring reminders. A calendar sync he did not ask for.

## Core Workflows

1. **He sets a date**
   - He edits an item and sets a reminder or expiry date.
   - The item shows that date.
   - He is notified when it arrives.
2. **He does not**
   - An item with no date has no notification from this epic.

## Risks & Dependencies

- Depends on E1 items and on edit (US-12).
- Notification permission is the platform's. If he denies it, the date is still stored and he can see it on the item. Do not invent a second channel.
- The schedule above is the US-34 rule in the specification. Stories stay Draft.

## Open Questions

- The time, the 7-day expiry warning, and inexact alarms are in the specification (US-34). Exact alarms are not an open question. Stories stay Draft.

## Draft stories

- US-34 Set a reminder or expiry and get notified
- US-54 Notify before an expiry
- US-55 Reschedule reminders after a restart or a clock change
- US-62 See what is coming due. Should.
- US-77 Keep reminders on inexact alarms.
