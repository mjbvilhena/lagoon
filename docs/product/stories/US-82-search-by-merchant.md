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
- Out of scope: US-68 dates, the amount (US-83), and the purchase date (US-84). Priority stays Should. Miguel has not changed the scope or the priority. The specification lists picking out merchants as Later, and it says no story.

## Acceptance criteria

- **Scenario: search** — Should
  - Given he typed a merchant on an item
  - When he searches that name
  - Then the item can match
- **Scenario: filter** — Should
  - Given he filters by one merchant
  - When the filter is applied
  - Then only items with that merchant are shown
- **Scenario: case and accents** — Should
  - Given he typed "Café" on one item and "cafe" is the query
  - When he searches or filters
  - Then that item matches. Case and accents both fold
- **Scenario: an existing merchant** — Should
  - Given "Cafe" is already stored on another item
  - When he types a merchant
  - Then "Cafe" is offered, and choosing it stores that same spelling
- **Scenario: empty** — Should
  - Given he sets no merchant
  - When the item is saved
  - Then the item is valid
- **Scenario: its own column** — Should
  - Given he typed a merchant
  - When US-15 indexes the item
  - Then the merchant is in its own column, and US-15's merchant column ships with this story
  - And the weight of that column is the US-50 draft, pending Miguel
- **Scenario: a sensitive item** — Should
  - Given the item is sensitive and still locked
  - When he searches the merchant word
  - Then it does not match. US-75 hides that word until unlock

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-15's merchant column ships with this story. It does not block US-31. Priority stays Should.

## Implementation notes

Not a design spec.
