# US-66 Keep a trash beyond undo

> **Approval status: Proposed for story review. Needs approval by mjbvilhena.**

**Epic:** E3.

**MoSCoW:** Should. Delete with undo is US-13. Proposed amendment. Needs approval by mjbvilhena. This story is in v1. It is not approved.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-13, which remains the undo path.
- Proposed amendment. Needs approval by mjbvilhena. Trash is in v1 and keeps the item for 30 days. During those 30 days the delete stays on this phone. He can restore it. A later edit on another phone is not lost, and the item comes back. When the 30 days end, the delete becomes a tombstone and spreads. An id with a tombstone is not a one-sided item the other phone keeps.

## Acceptance criteria

- **Scenario: trash** — Should
  - Given this story is in the build
  - When the undo window ends
  - Then the item is in trash for 30 days, it is absent from search, and its reminders are paused
- **Scenario: the delete stays local** — Should
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash and the 30 days have not ended
  - When this phone syncs
  - Then no tombstone is uploaded
  - And the other phone still has the item
- **Scenario: restore** — Should
  - Given an item is in trash
  - When he restores it
  - Then it returns to search, and its reminders are scheduled again
  - And Proposed amendment. Needs approval by mjbvilhena. It comes back, and no tombstone is written
- **Scenario: a later edit on another phone** — Should
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash on this phone
  - When the other phone has edited it during those 30 days
  - Then that edit is not lost
  - And the item comes back
- **Scenario: the 30 days end** — Should
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is still in trash
  - When the 30 days end
  - Then the delete becomes a tombstone and spreads to the other phones
  - And the other phone does not keep it as a one-sided item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Proposed amendment. Needs approval by mjbvilhena. Build this in v1. The 30-day rule and the tombstone are ADR-0002, which is still Proposed.

## Implementation notes

Not a design spec.
