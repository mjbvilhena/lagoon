# US-10 Browse saved items, newest first

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E3.

## Title

Browse saved items, newest first.

## User story

**As a** person named Miguel
**I want** the home list to show what I saved, newest first
**So that** the thing I just kept is the first row

## Background

- The sort key is last-saved, not created. A repeat update (US-49) and an edit (US-12) move last-saved and therefore move the row.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: date filters and other sort directions (US-39). What a sensitive row reveals before unlock is US-75.

## Acceptance criteria

- **Scenario: several items** — Must
  - Given items with different last-saved times
  - When he opens the home list
  - Then they are ordered by last-saved, newest first, and created date is not the sort key
- **Scenario: what a row shows** — Must
  - Given an ordinary item with a title, a type, a last-saved date, and a source domain or sending app
  - When the row is shown
  - Then he sees the title, the type, the saved date, and that source. A reminder or expiry, when set, shows a marker
- **Scenario: nothing saved** — Must
  - Given the vault has no items
  - When he opens the home list
  - Then he sees that nothing is saved yet
- **Scenario: a new save** — Must
  - Given he saves a new item
  - When he returns to the home list
  - Then that item is the first row

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Each row has an accessible name that includes the title.
- No placeholder sentences.

## Dependencies and risks

- Source fields come from US-61. The marker comes from US-34 or US-54.

## Implementation notes

Order by last-saved descending. Not a design spec.
