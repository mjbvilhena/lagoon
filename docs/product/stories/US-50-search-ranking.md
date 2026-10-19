# US-50 Rank search matches

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-18.

**Epic:** E3.

**MoSCoW:** Must. v1. Depends on US-15.

## Title

Rank search matches.

## User story

**As a** person named Miguel
**I want** the closer match to appear first
**So that** the item I meant is not buried under a weak match

## Background

- Starting weights, as consensus already recorded in the specification: title 10, tags 5, personal note 3, body 1, extracted text 1, OCR text 1. Snapshot text, source domain, and sending app are weight 1, the same as body.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: changing these weights in Settings. The FTS engine spike is US-57.

## Acceptance criteria

- **Scenario: field weights** — Must
  - Given three ordinary items that match only in the title, only in a tag, and only in the body, each with the same term frequency and the same field length
  - When he searches that word
  - Then the title match ranks above the tag match, and the tag match ranks above the body match
- **Scenario: OCR equals body** — Must
  - Given one item matches only in the OCR column and another matches only in the body, with the same term frequency and the same field length
  - When he searches
  - Then neither outranks the other on weight. Equal lengths keep BM25 length normalisation from deciding the tie
- **Scenario: exact phrase** — Must
  - Given one item contains the words together as a phrase and another contains the same words apart
  - When he searches the phrase
  - Then the phrase match ranks above the scattered match
- **Scenario: recency is only a tie-break** — Must
  - Given two items score the same
  - When the results are ordered
  - Then the newer last-saved item comes first, and a newer item does not beat a higher score
- **Scenario: prefix and accents** — Must
  - Given an item contains "café"
  - When he types "cafe" or the prefix "caf"
  - Then that item matches. Accent folding uses `unicode61 remove_diacritics 2`
- **Scenario: the order is testable** — Must
  - Given a fixed set of items
  - When the same query runs twice
  - Then the order is the same

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Rank is visible as order. No placeholder sentences.

## Dependencies and risks

- US-39 can replace this order with a date sort. Relevance stays the default search order.

## Implementation notes

Deterministic tests with seeded items. Not a design spec.
