# US-56 Keep a stable identity for each item

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E4. Not an approval.

**MoSCoW:** Must. v1 enabler. The review asked for this chore so sync and edits have a basis.

## Title

Keep a stable identity for each item.

## User story

**As a** person named Miguel
**I want** every saved item to keep a stable id and an updated time
**So that** an edit or a second device does not fork the item or lose it

## Background

- The user-story Definition of Done allows a chore when the request asked for one. This is that chore.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the conflict rule when two `updatedAt` values exist for one id (US-73).

## Acceptance criteria

- **Scenario: a stable id** — Must
  - Given he saves an item
  - When he edits it, syncs it, or shares the same link again
  - Then the id does not change
- **Scenario: updatedAt** — Must
  - Given he edits the item
  - When the edit is saved
  - Then `updatedAt` moves to that save and `createdAt` does not
- **Scenario: a migration** — Must
  - Given a vault already has items and the schema gains these fields
  - When the migration runs
  - Then the items are still present and each has an id

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-20 uses this id for one-sided merge.

## Implementation notes

Room. Not an ADR. Not a design spec.
