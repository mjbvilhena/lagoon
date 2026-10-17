# US-12 Edit a saved item

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**Epic:** E3. Not an approval.

**MoSCoW:** Must. v1, from approved epic E3.

## Title

Edit a saved item.

## User story

**As a** person named Miguel
**I want** to change a saved item's title, body, note, and tags
**So that** I can correct it and say why it matters

## Background

- Create-time auto-title (US-05) does not run again on edit. An edit cannot leave the title empty.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: merging two items, and the topic-call privacy questions (US-76).

## Acceptance criteria

- **Scenario: edit title, body, and note** — Must
  - Given an existing item
  - When he changes the title, the body, and the personal note and saves
  - Then those fields are the new values, created date and last-saved stay, and only `updatedAt` moves to this edit
- **Scenario: edit tags** — Must
  - Given an item with tags
  - When he adds one tag and removes another
  - Then the item has the new set, and search follows it after US-15 re-indexes
- **Scenario: empty title** — Must
  - Given he clears the title
  - When he tries to save
  - Then the edit is refused, the stored title is unchanged, and he sees that the title is required
- **Scenario: dated notes** — Must
  - Given notes sit under `YYYY-MM-DD` separators
  - When he edits the text of an existing entry
  - Then that entry's separator date stays the day it was written
  - And adding a note creates a new entry under today's date
- **Scenario: search and topics follow the edit** — Must
  - Given the body changed
  - When the edit is saved
  - Then FTS is re-indexed from the new text, and topic processing is re-queued for this latest version (US-44, US-58)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The empty-title refusal is a visible error. No placeholder sentences.

## Dependencies and risks

- `updatedAt` is defined in US-56.

## Implementation notes

Do not change `createdAt` or last-saved on edit. Last-saved moves on a new save or a repeat share (US-49). Not a design spec.
