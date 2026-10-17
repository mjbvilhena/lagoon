# US-16 Filter by type and by tag

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Filter items by type and by tag.

## User story

**As a** person named Miguel
**I want to** narrow the list or the search results by type and by tag
**So that** I can look only at PDFs, or only at items I tagged a certain way

## Background

- He asked to filter by type and by tag, in the same breath as search.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: saved searches, boolean query language.

## Acceptance criteria

- **Scenario: filter the home list by type** — Must
  - Given items of more than one type
  - When he filters to `PDF`
  - Then only PDF items are shown, still newest first
- **Scenario: filter search by tag** — Must
  - Given a search that matches two items and only one of them has the chosen tag
  - When he applies that tag
  - Then only the tagged match remains, still highlighted
- **Scenario: filter that matches nothing** — Must
  - Given no item has the chosen tag
  - When he applies it
  - Then the list is empty and he can see that the filter, not a crash, produced the empty list
- **Scenario: clear filters** — Must
  - Given a type or tag filter is on
  - When he clears it
  - Then the items that were hidden come back

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The active filter is visible as text, not only as a colored chip.
- Type names are the four he named: link, note, image, PDF.

## Dependencies and risks

- Uses tags from US-14 and search from US-15.
- Combining type and tag: both apply together (intersection). That is the reading of "filter by type and tag." If he wanted either/or, that would change this criterion.

## Implementation notes

Filters compose with FTS. Not a design spec.
