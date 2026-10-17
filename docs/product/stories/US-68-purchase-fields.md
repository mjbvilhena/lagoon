# US-68 Record a return-by date and a warranty end

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. The specification leaves merchants and bookings as later. Dates are split from merchant, amount, and purchase date (US-82, US-83, US-84).

## Title

Record a return-by date and a warranty end.

## User story

**As a** person named Miguel
**I want** optional return-by and warranty dates that can notify me
**So that** those dates behave like an expiry when I type them

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: OCR that picks merchants or amounts out of a receipt. Merchant, amount, and purchase date are US-82, US-83, and US-84.

## Acceptance criteria

- **Scenario: the dates** — Should
  - Given an item
  - When he sets a return-by date or a warranty end
  - Then that date is stored, and notices are scheduled the same way as an expiry in US-54
  - And the item is still valid if he sets neither

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-31.

## Implementation notes

Not a design spec.
