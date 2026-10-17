# US-15 Search with ranked, highlighted matches

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> The ranking below is a consensus recommendation from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot under that delegation. It is not approval. The Room and SQLCipher spike is a design-gate risk, not a decision.

## Title

Search the vault and see ranked, highlighted matches.

## User story

**As a** person named Miguel
**I want to** search title, body, extracted text, tags, and notes, and see the matches highlighted in a ranked order
**So that** I can find something I saved without scrolling the whole list

## Background

- He asked for fast full-text search using Room and FTS, across those fields, with results ranked and highlighted.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a millisecond budget (none was given). Performing OCR (US-31). Filters (US-16) and date filters (US-39).
- Title, body, extracted text, tags, and the personal note are all in the index. Extracted text includes text taken from a text-based PDF (US-04) and OCR text once US-31 has stored it.
- Ranking is weighted BM25. Starting weights, highest to lowest: title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. Exact phrase matches rank above scattered terms. Recency is only a light tie-break. Prefixes match as he types. Accents are folded with `unicode61 remove_diacritics 2`.

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
- **Scenario: field weights** — Must
  - Given items whose only match is in title, tags, the personal note, or body or extracted text, using the same term and the same last-saved time
  - When he searches
  - Then the order is title, then tags, then the personal note, then body or extracted text, at the starting weights title 10, tags 5, note 3, body 1
  - And an OCR-only match of that term uses weight 1, the same as body, so it does not outrank a body match except by the tie-break below
- **Scenario: exact phrase** — Must
  - Given one item contains the query as a contiguous phrase and another contains the same words scattered
  - When he searches for that phrase
  - Then the exact phrase ranks above the scattered match
- **Scenario: recency tie-break** — Must
  - Given two items with the same weighted score and the same phrase-or-scattered class
  - When he searches
  - Then the more recently saved item ranks above the older one, and that bump is lighter than a difference in field weight or phrase match
  - And if the last-saved times are also equal, the lower stable item id ranks first, so the order is deterministic
- **Scenario: prefix and accents** — Must
  - Given an item titled with an accented word
  - When he types a prefix of that word without the accent
  - Then the item matches before he finishes the word
- **Scenario: weights are tested** — Must
  - Given the repository tests from US-25
  - When they run
  - Then they assert this order, the phrase rule, and the tie-break, and the same inputs always produce the same order
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
- Technical risk for the design gate, not decided in this story: FTS5 needs Room 3 with the bundled SQLite driver. Stock SQLite on `minSdk` 26 lacks FTS5, and Room 2 only has FTS4. It is not yet verified whether that works with an encrypted database (SQLCipher). Spike it early. The fallback is Room 2 FTS4 plus app-side ranking.
- Extracted text comes from US-04 and US-08 when those fields exist. OCR text comes from US-31.

## Implementation notes

Search belongs in the repository layer that US-25 will test. Not a design spec. Not an ADR. The spike outcome is not chosen here.
