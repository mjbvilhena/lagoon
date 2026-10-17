# Epic E9: Reminders and expiry

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> Miguel decided optional expiry or reminder dates, with notifications, are in v1. That decision is part of the specification approved by `mjbvilhena` on 2026-10-17.

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
  - Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). No exact-alarm permission.
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

- The time, the 7-day expiry warning, and inexact alarms are in the approved specification (US-34). They are not an open product question. This epic is Approved. Its stories stay Draft.

## Draft stories

- US-34 Set a reminder or expiry and get notified

## Proposed amendment 2026-10-17 (awaiting mjbvilhena approval)

> Not approved. This section does not change the approved text above. Epic changes need Miguel's own named approval. Stories stay Draft.

### A-E9-1 One install posts, and the schedule survives more than a reboot

- **Story:** US-55.
- **MoSCoW:** Must. Not approval of this epic text.
- **Scope:** A reminder or an expiry is registered again after a reboot, a clock change, an update, or a notification-permission change. When the same date is on more than one install, only one install posts it.
- **Acceptance outcome:** A notification is not lost because the phone restarted, and two installs do not both post it. The approved text already covers `BOOT_COMPLETED`. Which install wins is this amendment, not an approved rule.

### A-E9-2 Upcoming list

- **Story:** US-62. Pending Miguel's keep/remove decision.
- **MoSCoW:** Should. Not approval of this epic text, and not a decision to keep the story.
- **Scope:** A list shows items whose one reminder or one expiry is coming due.
- **Acceptance outcome:** He can see what is due without opening each item. The list does not add a return-by date or a warranty end.

### Conflicts, not amended

#### C-E9-1 Return-by and warranty still named on US-62

US-62 still says a future reminder, expiry, return-by date, or warranty end. Return-by and warranty were removed with US-68 on 2026-10-17 at 19:00 IST. The approved epic allows one reminder or one expiry, not those extra dates. A-E9-2 does not copy them. The story was left in place because the keep/remove decision is still open.

#### C-E9-2 Exact alarms on US-77

The approved epic and the approved specification say alarms are inexact and the app does not request the exact-alarm permission. US-77 asks whether that may change. An amendment that allowed exact alarms would contradict both. No such amendment is drafted. US-77 stays the open question, parented to E9.
