# US-66 Keep a trash beyond undo

> **Approval status: Proposed for story review. Needs approval by mjbvilhena.**

**Epic:** E3.

**MoSCoW:** Must. v1. Delete with undo is US-13. v1 does not ship without this story. Decided by mjbvilhena; Proposed until he approves it.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: Proposed `docs/domain/vault/DOMAIN.md` describes the same trash rule. No MUST is in force while that file and ADR-0002 are unapproved. Do not invent a second rule.
- Out of scope: US-13, which remains the undo path.
- Proposed amendment. Needs approval by mjbvilhena. v1 has no Delete forever, no Delete now, and no Empty trash. The only permanent delete is the tombstone after 30 × 24 hours.
- Proposed amendment. Needs approval by mjbvilhena. Trash is opened from Settings (US-59). The layout of that screen waits for the UX artefact.
- Trash is Must for v1. v1 does not ship without it. When undo ends, this phone sets `inTrash`, `trashedAt`, and `trashChangedAt` to that instant. `trashChangedAt` changes only on a trash write or a restore write. A background write does not set it. In-trash state merges by the later `trashChangedAt`, and an equal `trashChangedAt` uses the lower install id. `updatedAt` does not decide trash. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. A user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. A system write does not. An older user edit is kept while the item stays in trash. A reminder that fell due in trash fires once on restore, and once when a later user edit brings the item out. A restore made before the deadline that arrives after a tombstone brings the item back. The tombstone, the permanent delete, spreads only after 30 × 24 hours from `trashedAt`. A delete does not become a tombstone by skipping trash. An id with a tombstone is not a one-sided item the other phone keeps. Decided by mjbvilhena; this record is Proposed until he approves it. The precise field rule is ADR-0002.
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
  - And a queued topic job for it is not sent
  - And topic notes written from it are hidden while it is in trash
- **Scenario: trash view** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item is in trash
  - When he opens Trash from Settings
  - Then he sees its type, its title, the days left, and Restore
  - And days left is the whole days until 30 × 24 hours after `trashedAt`, rounded up on this phone's clock, so the last day shows 1
  - And after that deadline, before this phone has removed the item, the row shows no day count. It does not show 0
  - And once this phone has removed the item, the row is gone
  - And there is no Delete forever, no Delete now, and no Empty trash
  - And Trash offers Restore and does not offer editing
  - And Proposed amendment. Needs approval by mjbvilhena. Opening that row shows the item read-only
  - And the exact layout waits for the UX artefact
- **Scenario: restore from any phone** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash on a phone that has merged `inTrash`
  - When he restores it on any phone
  - Then `inTrash` and `trashedAt` are cleared, `trashChangedAt` is the restore time, and no tombstone is written
  - And that write does not set `userEditedAt`
  - And the item returns on every phone that merges that record
  - And its other fields are unchanged, including the sensitive mark
  - And its reminders are scheduled again
  - And a reminder whose time passed while it was in trash fires once, as soon as possible, on the install US-55 picks, and it is not fired twice
  - And that once-only fire is a synced fired state, so another phone does not fire it again
  - And its expiry notices resume
  - And topic notes written from it show again
- **Scenario: a later user edit** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given the item is in trash, and the edit comes from a phone that has not synced that trash, or from a zip import
  - When that edit's `userEditedAt` is later than `trashChangedAt`
  - Then the edit is kept
  - And `inTrash` and `trashedAt` are cleared
  - And the item leaves trash on every phone that merges it
  - And its reminders are scheduled again
  - And a reminder whose time passed while it was in trash fires once, as soon as possible, on the install US-55 picks, and it is not fired twice
  - And that once-only fire is the same synced fired state as restore
  - And its expiry notices resume
  - And topic notes written from it show again
  - And a user edit is a change he makes to the title, body, personal notes, tags on that item, reminder, expiry, attachments, or the sensitive mark
  - And that edit sets `userEditedAt`
  - And a write that only sets or clears `inTrash` is not that edit
  - And Trash offers Restore and does not offer editing, so this edit is not made from the Trash row
  - And when `userEditedAt` equals `trashChangedAt`, the lower install id wins
  - And if that id made the user edit, the item comes back out of trash
  - And if that id set `trashChangedAt`, the item stays in trash
- **Scenario: a system write stays in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given the item is in trash
  - When a US-29 rename or merge, a link-preview or snapshot refresh, OCR, a topic link, an automatic reminder reschedule, or another background write touches it
  - Then `inTrash`, `trashedAt`, and `trashChangedAt` are unchanged
  - And `userEditedAt` does not move
  - And the item stays in trash
- **Scenario: an older edit stays, and is kept** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given the item is in trash
  - When a user edit arrives whose `userEditedAt` is earlier than `trashChangedAt`
  - Then the item stays in trash
  - And that edit's fields are kept
  - And if he restores the item, that edit is present
- **Scenario: a reminder that fell due in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given a reminder's time passed while the item was in trash
  - When he restores it
  - Then that reminder fires once, as soon as possible, on the install US-55 picks
  - And it is not fired twice
  - And that once-only fire is a synced fired state, so another phone does not fire it again
- **Scenario: a restore that arrives after the tombstone** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given he restored the item before 30 × 24 hours after `trashedAt`
  - When a tombstone for that id is already written, and the restore arrives later
  - And the restore's `trashChangedAt` is after `trashedAt` and before the tombstone timestamp
  - Then the item comes back on every phone, out of trash
  - And the tombstone is dropped
  - And a phone that had already removed the item rebuilds it, including its search index, its reminders, and its topics
  - And a user edit made before that deadline, arriving after the tombstone, does the same when its `userEditedAt` is after `trashChangedAt` and before the tombstone timestamp
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
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the repeat-link match (US-49) finds an item that is in trash
  - When he shares that URL again
  - Then the match offers to restore that item
  - And a duplicate item is not created
- **Scenario: a zip keeps the trash state** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item is in trash
  - When he exports a zip and later imports that zip
  - Then the zip includes that item with `inTrash`, `trashedAt`, and `trashChangedAt`
  - And the import keeps the item in trash
- **Scenario: trash during a cancelled reset** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given a forgotten-passphrase reset is in its 24 hours on this phone, and this phone does not upload
  - When he trashes an item or restores one during that wait, and another phone cancels the reset
  - Then that trash or restore is a record change merged by `trashChangedAt`, and it does not set `userEditedAt`
  - And the cancel does not drop it as a delete made only in the new snapshot
  - And it is copied into the restored vault under the old passphrase
  - And the later `trashChangedAt` wins, and an equal `trashChangedAt` uses the lower install id
- **Scenario: a sensitive item in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given a sensitive item is in trash
  - When Trash is shown before the US-37 unlock
  - Then that row uses the US-75 neutral form
  - And its title is not shown until that unlock
- **Scenario: the 30 days end** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone has merged `inTrash` and the item is still in trash
  - When 30 × 24 hours after `trashedAt` end on this phone's clock
  - Then this phone removes it as US-13 removes a finished delete, and writes a tombstone for that id
  - And a tombstone for the same id written by another phone is the same delete
  - And the other phone does not keep that id as a one-sided item
- **Scenario: a phone that never merged the trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone never merged `inTrash` for that item
  - When a tombstone for that id arrives
  - Then this phone drops the item and does not keep the id as a one-sided item
  - And this phone does not start the 30 × 24 hours, because it never merged the trash

## Open questions

These are not decided. Proposed amendment. Needs approval by mjbvilhena.

- An expiry notice whose time passed while the item was in trash. Whether it fires once when the item leaves trash, the way a missed reminder does, is not decided.
- Declining the offer to restore when he shares a trashed URL again. Whether a new note or new tags from that share are kept if he accepts the restore is not decided either.
- A zip import of an item whose 30 × 24 hours after `trashedAt` have already ended.
- A topic job that was held back while the item was in trash, after restore or after a later user edit brings the item out. Both paths take the same answer. It is not decided here.
- A user edit from a phone that has not merged the tombstone, when that edit is not a restore or a user edit made before the deadline. Whether that edit is dropped is not decided.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on US-13. The removal when the 30 × 24 hours end uses the cleanup US-13 runs when the tombstone is written. US-13 is Draft.
- Proposed amendment. Needs approval by mjbvilhena. This story is Must. v1 does not ship without it. The field rule and the tombstone are ADR-0002, which is still Proposed. A delete does not become a tombstone by skipping trash. v1 has no Delete forever, no Delete now, and no Empty trash. A clock set ahead can write the tombstone early. That residual is T-28.

## Implementation notes

Not a design spec.
