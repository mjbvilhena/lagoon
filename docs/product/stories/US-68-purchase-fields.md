# US-68 Record a return date, a warranty, and a purchase

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Could. The specification leaves merchants and bookings as later.

## Title

Record a return date, a warranty, and a purchase.

## User story

**As a** person named Miguel
**I want** optional fields for a return-by date, a warranty end, a merchant, an amount, and a purchase date
**So that** I can type them when I want them

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: OCR that picks merchants or amounts out of a receipt.

## Acceptance criteria

- **Scenario: optional fields** — Could
  - Given an item
  - When he fills any of those fields
  - Then they are stored and the item is still valid if he fills none

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-31.

## Implementation notes

Not a design spec.
