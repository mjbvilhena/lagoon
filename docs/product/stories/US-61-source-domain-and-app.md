# US-61 Filter or search by where an item came from

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Should.

## Title

Filter or search by where an item came from.

## User story

**As a** person named Miguel
**I want** to filter or search by the source domain or the sending app
**So that** I can find what I saved from one site or one app

## Background

- US-01, US-02, US-03, and US-04 record the fields. The sending app is stored only when the platform reports it. An empty field does not block the save.
- Showing the values is US-10 and US-11. Epic E1 names this story for source domain and sending app, a Should. This story is the filter and the search of those fields. US-15's Must search does not include them. Epic E3's search list does not include them.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a map of every app package. Showing the fields.

## Acceptance criteria

- **Scenario: filter** — Should
  - Given he filters by one domain or one sending app
  - When the filter is applied
  - Then only those items are shown
- **Scenario: source domain and sending app** — Should
  - Given ordinary items whose only match is in the source domain or the sending app
  - When he searches that word
  - Then each of those items is returned

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Weights are US-50.

## Implementation notes

Not a design spec.
