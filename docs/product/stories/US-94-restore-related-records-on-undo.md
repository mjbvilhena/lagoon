# US-94 Put related records back when a delete is undone

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. Split from the unmet restore list on US-13.

## Title

Put related records back when a delete is undone.

## User story

**As a** person named Miguel
**I want** undo to bring back the item's search row, reminders, and topic records
**So that** a mistaken delete does not drop work that belongs to the item

## Background

- US-13 says undo returns the item with its text, attachments, FTS row, reminders, topic links, queued topic job, and pending suggestions.
- This slice stores the item text. It does not have an FTS index, a reminder scheduler, or topic records.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: creating those records. US-15, US-34, and US-41 own them. Cleanup of topic notes still waits for the tombstone, as US-13 says.

## Acceptance criteria

- **Scenario: undo restores the related records** — Must
  - Given the item had an FTS row, a reminder, topic links, a queued topic job, and pending suggestions
  - When he chooses Undo before the confirmation ends
  - Then those records are back with the item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Undo stays a named action.

## Dependencies and risks

- Needs the stores from US-15, US-34, and US-41.

## Implementation notes

Not a design spec.
