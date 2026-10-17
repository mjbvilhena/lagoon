# US-10 Browse items, newest first

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Browse saved items, newest first.

## User story

**As a** person named Miguel
**I want** the home list to show what I saved, most recent first
**So that** the thing I just kept is the first thing I see

## Background

- He asked for a clean home list, most recent first.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: search ranking (US-15). Filters (US-16). Editing.

## Acceptance criteria

- **Scenario: several items** — Must
  - Given three saved items with different created dates
  - When he opens the home list
  - Then they appear with the newest last-saved date first and the oldest last. On a first save, last-saved is the created date. A repeat URL bumps last-saved (US-01) and that item moves up
- **Scenario: nothing saved** — Must
  - Given the vault has no items
  - When he opens the home list
  - Then he sees that the vault is empty, in real words, with no lorem placeholder and no fake sample item
- **Scenario: a new save** — Must
  - Given he is looking at the list
  - When he saves another item
  - Then that item appears at the top

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Empty copy is real and short. The UX artefact will write it. This story forbids placeholder text.
- The list is readable in light and dark (US-22) once that story exists. This story does not define the palette.
- List rows expose their title to assistive tech.

## Dependencies and risks

- Needs at least one capture story so items can exist. The empty case does not.
- "Clean" and "whitespace" are E5 constraints, not extra features in this story.

## Implementation notes

Order by last-saved date descending. Not a design spec. The US-01 recommendation is not approval of this story.
