# US-84 Filter by purchase date

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Split from US-68 in the round-2 revision. This draft was not in the review at `40bb526`.
> Story approval has not been recorded. Epic approval is not story approval.

**MoSCoW:** Should. Not in the approved specification. Not a v1 gate.

## Title

Filter by purchase date.

## User story

**As a** person named Miguel
**I want** to type a purchase date and filter the list by it
**So that** I can see what I bought in a stretch of days

## Background

- The purchase date is not the last-saved date and not an expiry.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-39, which filters by last-saved. Notices for this date. US-68 covers return-by and warranty notices.

## Acceptance criteria

- **Scenario: stored** — Should
  - Given an item
  - When he sets a purchase date
  - Then that date is stored and last-saved is unchanged
- **Scenario: filter** — Should
  - Given items have purchase dates
  - When he filters by a purchase-date range
  - Then only items whose purchase date is inside that range are shown
- **Scenario: none** — Should
  - Given he sets no purchase date
  - When he saves
  - Then the item is valid and it is absent from a purchase-date filter

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-39.

## Implementation notes

Not a design spec.
