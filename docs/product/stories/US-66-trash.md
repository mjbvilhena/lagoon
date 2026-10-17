# US-66 Keep a trash beyond undo

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Should. Delete with undo is US-13.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-13, which remains the undo path. How long trash keeps an item is not decided. Scenarios use thirty days until it is.

## Acceptance criteria

- **Scenario: trash** — Should
  - Given this story is in the build
  - When the undo window ends
  - Then the item is in trash for 30 days, it is absent from search, and its reminders are paused
  - And after 30 days it is removed as US-13 removes it
- **Scenario: restore** — Should
  - Given an item is in trash
  - When he restores it
  - Then it returns to search, and its reminders are scheduled again

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Do not build this in v1 unless he asks.

## Implementation notes

Not a design spec.
