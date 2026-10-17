# US-67 Notice a duplicate image or PDF

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E1. Pending Miguel's keep/remove decision. Not an approval.

**MoSCoW:** Could. Not in the approved specification.

## Title

Notice a duplicate image or PDF.

## User story

**As a** person named Miguel
**I want** the vault to tell me when an image or PDF is already saved
**So that** I do not keep two copies of the same file

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: URL duplicates, which are US-49.

## Acceptance criteria

- **Scenario: same bytes** — Could
  - Given an image or PDF with the same content hash is already stored
  - When he shares it again
  - Then he is offered to add it to the existing item, and refusing still saves a new item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block capture.

## Implementation notes

Not a design spec.
