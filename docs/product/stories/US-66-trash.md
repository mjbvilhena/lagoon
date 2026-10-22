# US-66 Keep a trash beyond undo

> **Approval status: Proposed for story review. Needs approval by mjbvilhena.**
> The non-security parts are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
> Security criteria approved by mjbvilhena on 2026-10-22. The written security criteria in this story carry that mark. This story is not approved. What is still open is named in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena".

**Epic:** E3.

**MoSCoW:** Must. v1. Delete with undo is US-13. v1 does not ship without this story. Decided by mjbvilhena; this record is Proposed until he approves it.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: `docs/domain/vault/DOMAIN.md` describes the same trash rule. That file is approved, and ADR-0002 is accepted. Approved by mjbvilhena on 2026-10-20. Do not invent a second rule.
- Out of scope: US-13, which remains the undo path.
- Proposed amendment. Needs approval by mjbvilhena. v1 has no Delete forever, no Delete now, and no Empty trash. The only permanent delete is the tombstone after 30 × 24 hours.
- Proposed amendment. Needs approval by mjbvilhena. Trash is opened from Settings (US-59). The layout of that screen waits for the UX artefact.
- Trash is Must for v1. v1 does not ship without it. When undo ends, this phone sets `inTrash`, `trashedAt`, and `trashChangedAt` to that instant. `trashChangedAt` changes only on a trash write or a restore write. A background write does not set it. In-trash state merges by the later `trashChangedAt`, and an equal `trashChangedAt` uses the lower install id. `updatedAt` does not decide trash. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. A user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. A system write does not. An older user edit is kept while the item stays in trash. A reminder that fell due in trash fires once on restore, and once when a later user edit brings the item out. An expiry notice that came due while the item was in trash is shown once, right after restore, and once when a later user edit brings the item out. That show is the exception to US-54's rule that a passed time schedules no notice. If he declines the offer to restore a trashed URL, the share is saved as a new, separate item and the trashed item stays in trash. If he accepts, that accept is a restore. A zip import of an item whose 30 × 24 hours after `trashedAt` have already ended deletes it for good and writes the tombstone at import only when this phone has no record of that id with a later `trashChangedAt` and no user edit with a later `userEditedAt`. A later restore or a later user edit means the zip's trash state does not apply and no tombstone is written. A topic job held back while the item was in trash is not sent when the item leaves trash, on restore or by a later user edit. It waits until he taps "Get topics". An edit from an offline phone that arrives for an item already permanently deleted is dropped, except the late restore or the late user edit from the trash window, and the phone that held the edit shows a short in-app notice, once, that the edit couldn't be kept. The exact text waits for the UX artefact. A restore made before the deadline that arrives after a tombstone brings the item back. That comparison uses the earliest tombstone timestamp when several phones wrote one, and the `trashedAt` of the trash that the tombstone ended. The tombstone, the permanent delete, spreads only after 30 × 24 hours from `trashedAt`. A delete does not become a tombstone by skipping trash. An id with a tombstone is not a one-sided item the other phone keeps. Decided by mjbvilhena; this record is Proposed until he approves it. The precise field rule is ADR-0002.
- `trashedAt`, `trashChangedAt`, `updatedAt`, and `userEditedAt` are UTC instants, epoch milliseconds. Each phone checks the 30 × 24 hour deadline on its own clock. A clock set ahead can write `trashChangedAt` or the tombstone early. That residual is T-28. No guard is chosen.

## Acceptance criteria

- **Scenario: trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item has been deleted and the undo window is open
  - When the undo window ends
  - Then the item is in trash, `trashedAt` and `trashChangedAt` are that instant, and no tombstone is written
  - And that write does not set `userEditedAt`
  - And 30 days means 30 × 24 hours after `trashedAt`
  - And `trashedAt`, `trashChangedAt`, and `updatedAt` are UTC instants, epoch milliseconds
  - And `updatedAt` does not decide trash
  - And each phone checks that deadline on its own clock
- **Scenario: hidden on every phone** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone has merged `inTrash` and the 30 × 24 hours have not ended
  - When he looks at the home list, search, Upcoming, a tag view, the inbox, or a topic view
  - Then the item is absent
  - And its reminders are silenced, including on the phone US-55 would pick
  - And its expiry notices are paused
  - And a queued topic job for it is not sent (US-44)
  - And topic notes written from it are hidden while it is in trash
- **Scenario: trash view** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item is in trash and the item is not sensitive
  - When he opens Trash from Settings
  - Then he sees its type, its title, the days left, and Restore
  - And days left is the whole days until 30 × 24 hours after `trashedAt`, rounded up on this phone's clock, so the last day shows 1
  - And after that deadline, before this phone has removed the item, the row shows no day count. It does not show 0
  - And once this phone has removed the item, the row is gone
  - And there is no Delete forever, no Delete now, and no Empty trash
  - And Trash offers Restore and does not offer editing
  - Proposed amendment. Needs approval by mjbvilhena.
  - And opening that row shows the item read-only
  - Proposed amendment. Needs approval by mjbvilhena.
  - Security criteria approved by mjbvilhena on 2026-10-22. In Trash, a sensitive item's row is neutral (no title or preview) until he unlocks; after unlock it shows the title, and screenshots of Trash are blocked while it is shown.
  - And the exact layout waits for the UX artefact
- **Scenario: restore from any phone** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the item is in trash on a phone that has merged `inTrash`
  - When he restores it on any phone
  - Then `inTrash` and `trashedAt` are cleared, `trashChangedAt` is the restore time, and no tombstone is written
  - And that write does not set `userEditedAt`
  - And the item returns on every phone that merges that record
  - And its other fields are unchanged, including the sensitive mark
  - And its reminders are scheduled again
  - And a reminder whose time passed while it was in trash fires once, as soon as possible, on the install US-55 picks, and it is not fired twice
  - And that once-only fire is a synced fired state (US-56), so another phone does not fire it again
  - And its expiry notices resume
  - And an expiry notice whose time passed while the item was in trash is shown once, right after this restore, on the install US-55 picks
  - And that once-only show is a synced fired state (US-56), so another phone does not show it again
  - And topic notes written from it show again
  - And a topic job that was held back while the item was in trash is not sent (US-44)
  - And that job waits until he taps "Get topics" on the item
  - Security criteria approved by mjbvilhena on 2026-10-22. A sensitive item restored from Trash, or rebuilt on another phone after a late restore, follows US-75 and US-37 exactly as before it was trashed, including its text/OCR kept out of search until unlock.
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the item is in trash, and the edit comes from a phone that has not synced that trash, or from a zip import
  - When that edit's `userEditedAt` is later than `trashChangedAt`
  - Then the edit is kept
  - And the merge keeps that edit's user fields because its `userEditedAt` is later, then clears trash because that `userEditedAt` is later than `trashChangedAt`
  - And `trashChangedAt` is unchanged
  - And `inTrash` and `trashedAt` are cleared
  - And the item leaves trash on every phone that merges it
  - And its reminders are scheduled again
  - And a reminder whose time passed while it was in trash fires once, as soon as possible, on the install US-55 picks, and it is not fired twice
  - And that once-only fire is the same synced fired state as restore
  - And its expiry notices resume
  - And an expiry notice whose time passed while the item was in trash is shown once, right after the item leaves trash, on the install US-55 picks
  - And that once-only show is the same synced fired state as restore
  - And topic notes written from it show again
  - And a topic job that was held back while the item was in trash is not sent (US-44)
  - And that job waits until he taps "Get topics" on the item
  - And a user edit is a change he makes to the title, body, personal notes, tags on that item, reminder, expiry, attachments, or the sensitive mark
  - Security criteria approved by mjbvilhena on 2026-10-22. Toggling the sensitive mark on or off counts as a user edit, so it sets `userEditedAt` and brings a trashed item out of trash.
  - And that edit sets `userEditedAt`
  - And a write that only sets or clears `inTrash` is not that edit
  - And Trash offers Restore and does not offer editing, so this edit is not made from the Trash row
  - And when `userEditedAt` equals `trashChangedAt`, the lower install id wins
  - And if that id made the user edit, the item comes back out of trash
  - And if that id set `trashChangedAt`, the item stays in trash
- **Scenario: a system write stays in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the item is in trash
  - When a US-29 rename or merge, a link-preview or snapshot refresh, OCR, a topic link, an automatic reminder reschedule, or another background write touches it
  - Then `inTrash`, `trashedAt`, and `trashChangedAt` are unchanged
  - And `userEditedAt` does not move
  - And the item stays in trash
- **Scenario: an older edit stays, and is kept** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the item is in trash
  - When a user edit arrives whose `userEditedAt` is earlier than `trashChangedAt`
  - Then the item stays in trash
  - And that edit's fields are kept
  - And if he restores the item, that edit is present
- **Scenario: a reminder that fell due in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given a reminder's time passed while the item was in trash
  - When he restores it, or a later user edit brings it out of trash
  - Then that reminder fires once, as soon as possible, on the install US-55 picks
  - And it is not fired twice
  - And that once-only fire is a synced fired state, so another phone does not fire it again
  - Security criteria approved by mjbvilhena on 2026-10-22. A missed reminder or expiry notice that fires once on restore of a sensitive item uses the US-34/US-75 public neutral notification form.
- **Scenario: a restore that arrives after the tombstone** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given he restored the item before 30 × 24 hours after `trashedAt`
  - When a tombstone for that id is already written, and the restore arrives later
  - And the restore's `trashChangedAt` is after the `trashedAt` of the trash that the tombstone ended, and before the earliest tombstone timestamp when several phones wrote one
  - Then the item comes back on every phone, out of trash
  - And the tombstone is dropped
  - And a phone that had already removed the item rebuilds what US-13 removes when the tombstone is written: the search index, reminders, with the one missed fire, topic links, the queued topic job rebuilt as held, pending suggestions, and the AI topic notes, and it re-fetches attachments from Drive. No topic call is sent
  - Security criteria approved by mjbvilhena on 2026-10-22. A sensitive item restored from Trash, or rebuilt on another phone after a late restore, follows US-75 and US-37 exactly as before it was trashed, including its text/OCR kept out of search until unlock.
  - And a user edit made before that deadline, arriving after the tombstone, does the same when its `userEditedAt` is after `trashChangedAt` and before the earliest tombstone timestamp when several phones wrote one
  - And a system write does not
- **Scenario: a background write has not seen the trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone has not synced the trash
  - When OCR, a preview refresh, or another background write bumps `updatedAt`
  - Then that write does not set `trashChangedAt`
  - And when the trash record is merged, the item is in trash
- **Scenario: a stale background write after restore** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item was restored, and another phone still has the older trash
  - When that other phone's background write bumps `updatedAt` and does not set a newer `trashChangedAt`
  - Then the restore's `trashChangedAt` stands
  - And the item stays restored
  - And the stale `inTrash` is not applied
- **Scenario: restore and re-trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given one phone restores the item and another phone trashes it again
  - When both records are merged
  - Then the later `trashChangedAt` wins
  - And an equal `trashChangedAt` uses the lower install id
  - And a winning restore clears `inTrash` and `trashedAt`, and the item is out of trash
  - And a winning re-trash sets `inTrash`, and sets `trashedAt` and `trashChangedAt` to that re-trash, so the item is in trash and the 30 × 24 hours start again from that instant
  - And `updatedAt` does not choose either outcome
- **Scenario: a shared URL whose item is in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the repeat-link match (US-49) finds an item that is in trash
  - When he shares that URL again
  - Then the match offers to restore that item
  - And the ordinary update of that item does not run
  - Security criteria approved by mjbvilhena on 2026-10-22. When a re-shared link matches a sensitive item in Trash, the restore offer is neutral (no title or preview) before unlock and shows the title only after the US-37 unlock.
- **Scenario: he accepts the restore of a shared URL** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given that offer is showing
  - When he accepts
  - Then that accept is a restore of the matched item
  - And `inTrash` and `trashedAt` are cleared, `trashChangedAt` is the restore time, and that write does not set `userEditedAt`
  - And the restored item's other fields are unchanged, apart from the new share's note and tags
  - And the ordinary update does not run
  - And a second item is not created
  - And the restore in "restore from any phone" applies, including reminders, the one missed-reminder fire, the one expiry notice that came due in trash, and topic notes shown again
- **Scenario: the accepted restore keeps the new note and tags** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given the restore offer for a shared URL is showing, and the new share has a note or tags
  - When he accepts
  - Then that note and those tags are added to the restored item the same way US-49 adds them on a live update: the note is appended under a date separator `YYYY-MM-DD`, and the tags are unioned
  - And the ordinary update does not run
  - And the restored item's other fields are unchanged
  - And that accept does not set `userEditedAt`
- **Scenario: he declines the restore of a shared URL** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given that offer is showing
  - When he declines
  - Then the share is saved as a new, separate item
  - And the trashed item stays in trash
- **Scenario: a later share matches two live items** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given two live items share one URL, including after he declined the restore and later restored the trashed item
  - When he shares that URL again
  - Then Lagoon asks him which item to update
  - And that choice is US-49
  - Proposed amendment. Needs approval by mjbvilhena. If either matching item is sensitive, that item in the picker is neutral (no title or preview) until the US-37 unlock, then it shows the title. This is not approved.
- **Scenario: a zip keeps the trash state** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item is in trash, and 30 × 24 hours after `trashedAt` have not ended, and this phone has no record of that id with a later `trashChangedAt` and no later `userEditedAt`
  - When he exports a zip and later imports that zip
  - Then the zip includes that item with `inTrash`, `trashedAt`, and `trashChangedAt`
  - And the import keeps the item in trash
  - Security criteria approved by mjbvilhena on 2026-10-22. Zip export of sensitive items that are in Trash follows exactly the same US-75 rules as live sensitive items.
- **Scenario: a zip whose trash time has already ended** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the zip includes an item with `inTrash` set, and 30 × 24 hours after `trashedAt` have already ended on the importing phone's clock, and this phone has no record of that id with a later `trashChangedAt` and no user edit with a later `userEditedAt`
  - When he imports that zip
  - Then that item is deleted for good straight away
  - And the import writes the tombstone, the permanent-delete record, for that id
  - And the item is not left in trash
  - Security criteria approved by mjbvilhena on 2026-10-22. The permanent delete does not promise the item is gone for good. Encrypted copies may remain in Drive's old file versions for a while. After a permanent delete, Lagoon also deletes the older Drive revisions of its sync files.
- **Scenario: a zip after a later user edit** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone holds a user edit of that id made after the export, so its `userEditedAt` is later than the zip's `trashChangedAt`
  - When he imports that zip
  - Then the zip's trash state does not apply
  - And no tombstone is written
  - And the trash state does not change and no tombstone is written; user fields still merge by `userEditedAt`
- **Scenario: a zip does not re-trash a later change** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone's record of that id has a `trashChangedAt` later than the zip's, including a restore after the export
  - When he imports that zip
  - Then the zip does not re-trash the item
  - And the zip's trash state does not apply
  - And the trash state does not change and no tombstone is written; user fields still merge by `userEditedAt`
- **Scenario: trash during a cancelled reset** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given a forgotten-passphrase reset is in its 24 hours on this phone, and this phone does not upload
  - When he trashes an item or restores one during that wait, and another phone cancels the reset
  - Then that trash or restore is a record change merged by `trashChangedAt`, and it does not set `userEditedAt`
  - And the cancel does not drop it as a delete made only in the new snapshot
  - And it is copied into the restored vault under the old passphrase
  - And the later `trashChangedAt` wins, and an equal `trashChangedAt` uses the lower install id
  - Security criteria approved by mjbvilhena on 2026-10-22. A trash or restore made during a cancelled forgotten-passphrase reset wait merges by `trashChangedAt` as usual, and each phone and the Drive copy stays readable under exactly one passphrase.
- **Scenario: a sensitive item in trash** — Must
  - Security criteria approved by mjbvilhena on 2026-10-22.
  - Given a sensitive item is in trash
  - When he opens Trash
  - Then that row is neutral (no title or preview) until he unlocks
  - And after unlock it shows the title
  - And screenshots of Trash are blocked while it is shown
- **Scenario: open or restore a sensitive item from Trash** — Must
  - Security criteria approved by mjbvilhena on 2026-10-22.
  - Given a sensitive item is in trash
  - When he opens it from Trash, or restores it from Trash
  - Then the US-37 unlock is required first
  - And a cancelled or failed unlock changes nothing
- **Scenario: the 30 days end** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone has merged `inTrash` and the item is still in trash
  - When 30 × 24 hours after `trashedAt` end on this phone's clock
  - Then this phone removes it as US-13 removes a finished delete, and writes a tombstone for that id
  - And a tombstone for the same id written by another phone is the same delete
  - And the other phone does not keep that id as a one-sided item
  - Security criteria approved by mjbvilhena on 2026-10-22. The permanent delete does not promise the item is gone for good. Encrypted copies may remain in Drive's old file versions for a while. After a permanent delete, Lagoon also deletes the older Drive revisions of its sync files.
- **Scenario: a phone that never merged the trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone never merged `inTrash` for that item
  - When a tombstone for that id arrives
  - Then Unless the user-edit case below applies, this phone drops the item and does not keep the id as a one-sided item
  - And this phone does not start the 30 × 24 hours, because it never merged the trash
  - And if this phone holds a user edit whose `userEditedAt` is after `trashChangedAt` and before the earliest tombstone timestamp when several phones wrote one, it keeps the item, drops the tombstone, and that edit brings the item back on every phone
  - And it shows no couldn't-be-kept notice
- **Scenario: an expiry notice that came due in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given an expiry notice's time passed while the item was in trash
  - When he restores the item, or a later user edit brings it out of trash
  - Then that notice is shown once, right after the item leaves trash, on the install US-55 picks
  - And it is not shown twice
  - And the 7-day notice and the day-of notice (US-54) are each shown once if that notice came due while the item was in trash
  - And that once-only show is a synced fired state (US-56), the same kind as the missed reminder
  - Security criteria approved by mjbvilhena on 2026-10-22. A missed reminder or expiry notice that fires once on restore of a sensitive item uses the US-34/US-75 public neutral notification form.
  - And this is the exception to US-54 named in that story. Setting an expiry whose time has already passed still schedules no notice
- **Scenario: a held topic job** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given a topic job for the item was held back while it was in trash (US-44)
  - When he restores the item, or a later user edit brings it out of trash
  - Then that job is not sent
  - And it is not sent later on its own
  - And it waits until he taps "Get topics" on that item
- **Scenario: an offline edit after the permanent delete** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Amendment approved by mjbvilhena on 2026-10-22.
  - Given the item is already permanently deleted
  - And the arriving edit is not a restore whose `trashChangedAt` is after the `trashedAt` of the trash that the tombstone ended, and before the earliest tombstone timestamp when several phones wrote one
  - And it is not a user edit whose `userEditedAt` is after `trashChangedAt` and before the earliest tombstone timestamp when several phones wrote one
  - When an edit from a phone that was offline arrives for that id
  - Then the edit is dropped
  - And the item stays deleted
  - And the phone that held the dropped user edit shows a short notice that the edit couldn't be kept
  - And that notice is in the app, not a system notification
  - Security criteria approved by mjbvilhena on 2026-10-22. The in-app dropped-edit notice for a sensitive item is neutral (no title) until the US-37 unlock, then shows the title.
  - And it appears once, when the dropped user edit is merged, and not again after a restart or a later sync
  - And it fires only for a dropped user edit, never for OCR, a preview, or another system write
  - And the exact text waits for the UX artefact
- **Scenario: a forgotten phone returns after tombstone cleanup** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given a phone was forgotten with "Forget this phone", and it returns after the tombstone was garbage-collected
  - When that phone still holds attachment files that belong to no live item
  - Then it deletes those attachment files
  - And it shows a short in-app notice
  - And that notice uses neutral wording and names no item title
  - And the exact text waits for the UX artefact
  - And it uploads a new records file and does not bring back a collected tombstone

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on US-13. The removal when the 30 × 24 hours end uses the cleanup US-13 runs when the tombstone is written. US-13 is Draft.
- A forgotten phone that returns after tombstone cleanup is "a forgotten phone returns after tombstone cleanup".
- Two live items that share one URL are "a later share matches two live items". The choice is US-49.
- Proposed amendment. Needs approval by mjbvilhena. This story is Must. v1 does not ship without it. The field rule and the tombstone are accepted ADR-0002. Approved by mjbvilhena on 2026-10-20. A delete does not become a tombstone by skipping trash. v1 has no Delete forever, no Delete now, and no Empty trash. A clock set ahead can write the tombstone early. That residual is T-28.

## Implementation notes

Not a design spec.
