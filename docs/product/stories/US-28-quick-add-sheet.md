# US-28 Optionally tag a save from the last-used tags

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Optionally tag a save from the last-used tags.

## User story

**As a** person named Miguel
**I want** a non-blocking row of my last-used tags after a save, and an optional note
**So that** I can file the item without a second form

## Background

- The save already finished in one tap. Last-used tags are the tags he applied most recently, up to 8, newest first.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: blocking the save on this row.

## Acceptance criteria

- **Scenario: one tap already saved** — Must
  - Given he shared or added an item
  - When the row appears
  - Then the item is already stored and he has already seen "Saved to Lagoon"
- **Scenario: several chips** — Must
  - Given he has used more than one tag
  - When the row is shown
  - Then up to 8 last-used tags appear, newest first, and he can tap more than one
- **Scenario: add a note** — Must
  - Given the row is showing
  - When he uses Add note and types
  - Then that text is stored as a note on the item
- **Scenario: add a date** — Must
  - Given the row is showing
  - When he uses Add date
  - Then he can set a reminder or an expiry on that item (US-34, US-54), and skipping Add date leaves the item without one
- **Scenario: dismiss** — Must
  - Given the row is showing
  - When he taps Back or outside the row
  - Then the item stays saved and the row closes
- **Scenario: no previous tags** — Must
  - Given he has never used a tag
  - When the row is shown
  - Then no chips appear and the item stays saved
- **Scenario: he ignores the row** — Must
  - Given he does not tap a chip
  - When he leaves
  - Then the item has no new tag

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Chips and Add note have names. The row does not trap focus. No placeholder sentences.

## Dependencies and risks

- Later tag edits are US-14.

## Implementation notes

Not a design spec.
