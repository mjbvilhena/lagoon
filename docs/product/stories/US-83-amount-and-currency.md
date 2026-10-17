# US-83 Record an amount and a currency

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Split from US-68 in the round-2 revision. This draft was not in the review at `40bb526`.
> Story approval has not been recorded. Epic approval is not story approval.

**MoSCoW:** Should. Not in the approved specification. Not a v1 gate.

## Title

Record an amount and a currency.

## User story

**As a** person named Miguel
**I want** to type an amount with a currency
**So that** the figure is stored as money, not as a bare number

## Background

- He types both. OCR does not pick them out.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: converting between currencies, and US-68 dates.

## Acceptance criteria

- **Scenario: amount and currency** — Should
  - Given an item
  - When he enters an amount and a currency
  - Then both are stored and shown together, and the currency is an ISO 4217 code such as `EUR`
- **Scenario: one without the other** — Should
  - Given he enters an amount and no currency, or a currency and no amount
  - When he tries to save that pair
  - Then the pair is not stored and he sees that both are required
- **Scenario: neither** — Should
  - Given he enters neither
  - When he saves the item
  - Then the item is valid

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-31.

## Implementation notes

Not a design spec.
