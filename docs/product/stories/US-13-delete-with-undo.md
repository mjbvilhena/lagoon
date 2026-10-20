# US-13 Delete an item and undo that delete

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

Proposed amendment. Needs approval by mjbvilhena. US-66, the trash beyond this undo, is Must for v1. v1 does not ship without it. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved.

## Title

Delete an item and undo that delete.

## User story

**As a** person named Miguel
**I want** to delete an item and undo that delete until I leave the confirmation
**So that** a mistake does not destroy the item, and a finished delete really removes it

## Background

- "Leaves that confirmation" means he dismisses the undo snackbar, navigates away from the screen that shows it, or the snackbar times out. A deferred hard delete is stored, so process death after that point still completes the delete.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: a trash folder (US-66), and how a deletion propagates to Drive or another device (US-73). This story does not delete the Drive copy.

## Acceptance criteria

- **Scenario: delete** — Must
  - Given an item is visible
  - When he deletes it
  - Then it leaves the home list and an undo snackbar is shown
- **Scenario: undo** — Must
  - Given the snackbar is showing
  - When he chooses Undo
  - Then the item returns with its text, attachments, FTS row, reminders, topic links, queued topic job, and pending suggestions
- **Scenario: the confirmation ends** — Must
  - Given he dismisses the snackbar, leaves that screen, or the snackbar times out
  - When the deferred delete runs, including after the process was killed in between
  - Then the item moves into trash. It is not gone
  - And its FTS row, reminders, topic links, queued topic job, pending suggestions, and the AI topic notes written from this item are removed when the tombstone is written, not when undo ends
  - And the Drive copy is left for US-73. This story does not delete it
- Proposed amendment. Needs approval by mjbvilhena. When undo ends, the hard delete in "the confirmation ends" moves the item into the synced trash. The cleanup in that scenario runs when the tombstone is written. v1 does not ship without US-66. A delete does not become a tombstone by skipping trash. This is not approved.
- **Scenario: a topic note from this item** — Must
  - Given a topic note was written from this item
  - When the tombstone is written
  - Then that note is removed. It is not kept as "item deleted"
  - And until that tombstone the note stays with the item in trash

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Undo is a named action. No placeholder sentences.

## Dependencies and risks

- US-45 reads the topic after this delete and no longer shows that note.
- Reminder cancel and restore are also US-55.
- Proposed amendment. Needs approval by mjbvilhena. US-66 depends on the cleanup in "the confirmation ends". That cleanup runs when the tombstone is written. US-66 is the trash beyond this undo.

## Implementation notes

Persist the pending hard delete. Not a design spec.
