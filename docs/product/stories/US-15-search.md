# US-15 Search with ranked, highlighted matches

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Search the vault and see ranked, highlighted matches.

## User story

**As a** person named Miguel
**I want to** search title, body, extracted text, tags, and notes, and see the matches highlighted in a ranked order
**So that** I can find something I saved without scrolling the whole list

## Background

- He asked for fast full-text search using Room and FTS, across those fields, with results ranked and highlighted.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a millisecond budget (none was given). Searching image pixels or scanned PDF pages that have no extracted text (Q7). Filters (US-16).

## Acceptance criteria

- **Scenario: match in each field** — Must
  - Given five items, each containing the query in only one of: title, body, extracted text, a tag, or the personal note
  - When he searches for that query
  - Then all five items are returned
- **Scenario: no match** — Must
  - Given no item contains the query
  - When he searches
  - Then the result list is empty and the screen says so in real words, not lorem
- **Scenario: highlight** — Must
  - Given a result whose body contains the query
  - When the result is shown
  - Then the matching text is visibly highlighted
- **Scenario: rank** — Must
  - Given one item whose title contains the query and another whose only match is in the body
  - When he searches
  - Then the title match is listed before the body-only match
- **Scenario: empty query** — Must
  - Given he clears the search box
  - When the query is empty
  - Then he is back to the home list (newest first), not an empty result set

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Highlight is not color alone. The matched words are also identifiable as text.
- No placeholder query such as "Search..." is required by this story. If the UX artefact uses a label, it must be a real label, not sample content.
- "Fast" has no numeric SLO here. Do not invent one.

## Dependencies and risks

- Room + FTS is a constraint he already set. An approved technical design still has to record the schema. This story does not pretend that design exists.
- Open: any ranking beyond "a title match outranks a body-only match of the same query." Do not invent a full scoring formula in implementation notes. Further ranking is a technical-design question, tracked as Q-rank for `mjbvilhena`, and is not decided.
- Extracted text comes from US-04 and US-08 when those fields exist.

## Implementation notes

Search belongs in the repository layer that US-25 will test. Not a design spec.
