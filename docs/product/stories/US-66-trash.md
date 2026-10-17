# US-66 Keep a trash beyond undo

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Could. v1 delete-with-undo is US-13.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-13, which remains the v1 path. Thirty days is a draft rule, not Miguel's approval.

## Acceptance criteria

- **Scenario: trash** — Could
  - Given this story is in the build
  - When the undo window ends
  - Then the item is in trash for 30 days and then removed as US-13 removes it
- **Scenario: restore** — Could
  - Given an item is in trash
  - When he restores it
  - Then it returns with its links, reminders, and index row

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Do not build this in v1 unless he asks.

## Implementation notes

Not a design spec.
