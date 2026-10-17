# US-61 Filter by where an item came from

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E1. Not an approval.

**MoSCoW:** Should. Source domain and sending app are in epic E1 because Miguel approved `docs/product/amendments/2026-10-17-security-delegation.md` on 2026-10-17 at 15:55 IST. This story stays Draft. That approval is not story approval.

## Title

Filter by where an item came from.

## User story

**As a** person named Miguel
**I want** to filter by the source domain or the sending app
**So that** I can find what I saved from one site or one app

## Background

- US-01, US-02, US-03, and US-04 record the fields. The sending app is stored only when the platform reports it. An empty field does not block the save.
- Showing the values is US-10 and US-11. Searching them is US-15. This story is the filter only.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a map of every app package. Showing the fields. Searching the fields.

## Acceptance criteria

- **Scenario: filter** — Should
  - Given he filters by one domain or one sending app
  - When the filter is applied
  - Then only those items are shown

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Weights are US-50.

## Implementation notes

Not a design spec.
