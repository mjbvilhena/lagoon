# US-16 Filter items by type and by tag

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

Filter items by type and by tag.

## User story

**As a** person named Miguel
**I want** to limit the list to a type and to tags
**So that** I can look at one kind of item without reading the rest

## Background

- Several tags combine as AND. Source filters are US-61.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: saved searches.

## Acceptance criteria

- **Scenario: filter by type** — Must
  - Given items of more than one type
  - When he filters the home list to one type
  - Then only that type is shown
- **Scenario: several tags** — Must
  - Given he selects two tags
  - When the filter is applied
  - Then an item is shown only when it has both tags
- **Scenario: filter that matches nothing** — Must
  - Given no item has the selected type and tags
  - When the filter is applied
  - Then he sees that nothing matches
- **Scenario: clear filters** — Must
  - Given a filter is on
  - When he clears it
  - Then the unfiltered list returns

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Selected filters are visible. No placeholder sentences.

## Dependencies and risks

- Filters compose with US-15. Date filters are US-39.

## Implementation notes

Not a design spec.
