# US-56 Keep a stable identity for each item

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1. Sync and edits need a stable item id.

## Title

Keep a stable identity for each item.

## User story

**As a** person named Miguel
**I want** every saved item to keep a stable id and an updated time
**So that** an edit or a second device does not fork the item or lose it

## Background

- The user-story Definition of Done allows a chore when the request asked for one. This is that chore.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: restating the conflict rule. That rule is accepted ADR-0002. Approved by mjbvilhena on 2026-10-20. Placeholder US-73 points there.

## Acceptance criteria

- **Scenario: a stable id** — Must
  - Given he saves an item
  - When he edits it, syncs it, or shares the same link again and it updates the existing item (US-49)
  - Then the id does not change
- **Scenario: ids are not reused** — Must
  - Given two installs each save an item before any sync, or an item was deleted and another item is then saved
  - When the ids are compared
  - Then the ids differ. An id is never reused for a different item, and a deleted item's id is not given to a new item
- **Scenario: a user edit** — Must
  - Given an item
  - When he edits the title, the body, the personal notes, a tag on that item, the reminder, the expiry, the attachments, or the sensitive mark and saves
  - Then `userEditedAt` and `updatedAt` are set to the same instant, the later of now and one millisecond after the previous `updatedAt`, and `createdAt` does not move
- **Scenario: a system write** — Must
  - Given an item
  - When a system write saves
  - Then `updatedAt` is set and `userEditedAt` is not
  - And a system write is a US-29 rename or merge, a link-preview or snapshot refresh, OCR, topic links, an automatic reminder reschedule, or any other background write (ADR-0002)
- **Scenario: trash fields** — Must
  - Given US-66 has stored an item in trash
  - When that record is stored
  - Then the item has `inTrash`, `trashedAt`, and `trashChangedAt` as accepted ADR-0002 defines them
- **Scenario: the tombstone** — Must
  - Given the permanent delete has been written
  - When that record is stored
  - Then the tombstone is the permanent-delete record accepted ADR-0002 already defines. This story points at that record and does not restate the conflict rule
- **Scenario: last-saved** — Must
  - Amendment approved by mjbvilhena on 2026-11-16.
  - Given an item is saved for the first time, shared again as a repeat, or edited
  - When that write is stored
  - Then last-saved, the US-10 sort key, is stored as its own field. It is set on first save and on a repeat share, and not on an edit. Its cross-phone merge rule is the later value
- **Scenario: synced fired state** — Must
  - Amendment approved by mjbvilhena on 2026-11-16.
  - Given US-66 cites a once-only reminder fire or a once-only expiry notice
  - When the installs merge
  - Then the synced fired state that US-66 cites for the once-only reminder fire and the once-only expiry notice is stored on the item in this story. Its merge rule is sticky per occurrence: once any install has recorded that fire or that notice, that occurrence stays fired, so another phone does not fire it again. A later reminder or a later expiry notice on the same item still fires
- **Scenario: a migration** — Must
  - Given a vault already has items and the schema gains these fields
  - When the migration runs
  - Then the items are still present, and each has an id, `createdAt`, `updatedAt`, `userEditedAt`, `inTrash`, `trashedAt`, `trashChangedAt`, last-saved, and the synced fired state
  - And a row keeps any createdAt, updatedAt and last-saved it already has. A missing createdAt or updatedAt is set to the migration instant. A missing last-saved is set to createdAt. `userEditedAt` starts unset, because the migration is a system write and a system write does not set `userEditedAt` (ADR-0002). `inTrash` starts clear, and `trashedAt` and `trashChangedAt` start unset, unless the row was already in trash. Amendment approved by mjbvilhena on 2026-11-16. A reminder occurrence or an expiry-notice occurrence whose time is already past at migration starts fired. A future occurrence starts unfired

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31).

## Dependencies and risks

- US-20 uses this id for one-sided merge.
- Accepted ADR-0002. Approved by mjbvilhena on 2026-10-20. `userEditedAt`, `trashChangedAt`, `inTrash`, `trashedAt`, the user-write and system-write rule, and the tombstone are defined there. Amendment approved by mjbvilhena on 2026-11-16. Last-saved and the synced fired state are defined there too.

## Implementation notes

Room. Not an ADR. Not a design spec.
