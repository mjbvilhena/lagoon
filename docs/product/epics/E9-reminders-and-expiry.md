# Epic E9: Reminders and expiry

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> Miguel decided optional expiry or reminder dates, with notifications, are in v1. That decision is part of the specification approved by `mjbvilhena` on 2026-10-17.
> Approved amendment, 2026-10-17 at 20:02 IST. Approver: `mjbvilhena`. His words: "Yes, I approve all 25 proposed epic amendments as written". A-E9-1 and A-E9-2 are folded into the scope below from the text at commit `2fe0422`. This does not approve the child stories. Stories stay Draft.
> Clash fixes, 2026-10-17 at 20:03 IST. Approver: `mjbvilhena`. C-E9-1: the Upcoming list shows only the one reminder or the one expiry. Return-by and warranty stay out. C-E9-2: alarms stay inexact. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`. That closes the US-77 question. It is not story approval.

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
  - Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). No exact-alarm permission. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`. That is the 20:03 IST close of C-E9-2 and US-77.
  - A reminder or an expiry is registered again after a reboot, a clock change, an update, or a notification-permission change (A-E9-1, US-55). When the same date is on more than one install, only one install posts it. Acceptance: a notification is not lost because the phone restarted, and two installs do not both post it. Which install wins is this approved amendment.
  - A list shows items whose one reminder or one expiry is coming due (A-E9-2, US-62, Should). US-62 is kept. Acceptance: he can see what is due without opening each item. The list does not add a return-by date or a warranty end. That limit is the 20:03 IST fix of C-E9-1.
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
- The schedule above is the US-34 rule in the specification approved by `mjbvilhena` on 2026-10-17. This epic is Approved. Its stories stay Draft.

## Open Questions

- The time, the 7-day expiry warning, and inexact alarms are in the approved specification (US-34). Exact alarms were closed on 2026-10-17 at 20:03 IST. They are not an open product question. This epic is Approved. Its stories stay Draft.

## Draft stories

- US-34 Set a reminder or expiry and get notified
- US-54 Notify before an expiry
- US-55 Reschedule reminders after a restart or a clock change
- US-62 See what is coming due. Should. Kept 2026-10-17 at 20:02 IST. Not story approval.
- US-77 Keep reminders on inexact alarms. The question is closed. Not story approval.

## Amendments applied 2026-10-17 at 20:02 IST

A-E9-1 and A-E9-2 were approved as written at `2fe0422` and folded into Scope above. US-62 is kept. C-E9-1 and C-E9-2 were closed at 20:03 IST, as named in the header. Stories stay Draft.
