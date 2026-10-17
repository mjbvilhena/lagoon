# US-13 Delete an item and undo that delete

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E3. Trash beyond this undo is Could (US-66).

## Title

Delete an item and undo that delete.

## User story

**As a** person named Miguel
**I want** to delete an item and undo that delete until I leave the confirmation
**So that** a mistake does not destroy the item, and a finished delete really removes it

## Background

- "Leaves that confirmation" means he dismisses the undo snackbar, navigates away from the screen that shows it, or the snackbar times out. A deferred hard delete is stored, so process death after that point still completes the delete.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a trash folder (US-66), and how a deletion propagates to another device (US-73).

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
  - Then the item, its FTS row, reminders, topic links, queued topic job, and pending suggestions are gone
- **Scenario: a topic note from this item** — Must
  - Given a topic note was written from this item
  - When the hard delete finishes
  - Then the note stays on the topic, still shows its date and source, and is marked "item deleted"
- **Scenario: nothing to delete** — Must
  - Given he is not viewing an item
  - When no delete is available
  - Then no snackbar appears

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Undo is a named action. No placeholder sentences.

## Dependencies and risks

- US-45 shows the same "item deleted" note. US-48 can remove that note.
- Reminder cancel and restore are also US-55.

## Implementation notes

Persist the pending hard delete. Not a design spec.
