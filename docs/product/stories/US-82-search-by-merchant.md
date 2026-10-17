# US-82 Search and filter by merchant

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Split from US-68 in the round-2 revision. This draft was not in the review at `40bb526`.
> Story approval has not been recorded. Epic approval is not story approval.

**MoSCoW:** Should. Not in the approved specification. Not a v1 gate.

## Title

Search and filter by merchant.

## User story

**As a** person named Miguel
**I want** to type a merchant and later search or filter by that name
**So that** I can find a shop without reading every item

## Background

- The merchant is text he types. OCR does not pick it out.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-68 dates, the amount (US-83), and the purchase date (US-84).

## Acceptance criteria

- **Scenario: search** — Should
  - Given he typed a merchant on an item
  - When he searches that name
  - Then the item can match
- **Scenario: filter** — Should
  - Given he filters by one merchant
  - When the filter is applied
  - Then only items with that merchant are shown
- **Scenario: empty** — Should
  - Given he sets no merchant
  - When the item is saved
  - Then the item is valid

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-15 or US-31.

## Implementation notes

Not a design spec.
