# US-61 See and filter by where an item came from

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. Source domain and sending app are not in the approved specification or epic E1. This story is noted for Miguel in the proposed amendment. It is not an epic edit.

## Title

See and filter by where an item came from.

## User story

**As a** person named Miguel
**I want** to see the source domain and the sending app, and to search and filter by them
**So that** I can find what I saved from one site or one app

## Background

- US-01, US-02, US-03, and US-04 record the fields. The sending app is stored only when the platform reports it. An empty field does not block the save.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a map of every app package.

## Acceptance criteria

- **Scenario: show** — Must
  - Given an item has a source domain or a sending app
  - When he sees the row or the detail
  - Then those values are shown
- **Scenario: search** — Must
  - Given the value is in its US-15 column
  - When he searches it
  - Then the item can match
- **Scenario: filter** — Must
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
