# US-15 Search the vault and see highlighted matches

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E3.

**MoSCoW:** Must. v1. Ranking weights are US-50. The FTS5 spike is US-57.

## Title

Search the vault and see highlighted matches.

## User story

**As a** person named Miguel
**I want** to search saved text and see the matching words
**So that** I can find an item and see why it matched

## Background

- The specification says search covers title, body, extracted text (including text-based PDF text and OCR text once recognized), tags, and the personal note. This story also indexes snapshot text. OCR text has its own column. Source domain and the sending app are US-61, a Should in epic E1, and are not in this Must field list. Epic E3's search list does not include them. Weights are US-50. OCR weight equals body, per the specification.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: topic names. The specification's search list does not include them. Whether a sensitive item appears in results is US-75. Ranking rules are US-50.

## Acceptance criteria

- **Scenario: match in each field** — Must
  - Given ordinary items whose only match is in one of: title, body, personal note, tags, extracted text, the OCR column, or snapshot text
  - When he searches that word
  - Then each of those items is returned
- **Scenario: highlight** — Must
  - Given a result matched a word
  - When the result row is shown
  - Then that word is highlighted in the snippet
- **Scenario: punctuation in the query** — Must
  - Given he types a query that contains `"`, `-`, `*`, `AND`, or `NEAR`
  - When the search runs
  - Then those tokens are treated as plain words and the search does not crash
- **Scenario: no match** — Must
  - Given no ordinary item contains the word
  - When he searches
  - Then he sees that nothing matched
- **Scenario: empty query** — Must
  - Given the query is empty
  - When he is on the search screen
  - Then the home list rules apply and no error is shown

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The highlight is not the only signal. The snippet text is available to a screen reader.
- No placeholder sentences.

## Dependencies and risks

- Blocked by the US-57 spike before implementation chooses FTS5 with SQLCipher. This story does not choose the engine.
- Sensitive-item results wait on US-75. This story's scenarios use ordinary items.

## Implementation notes

Not a design spec and not an ADR. The spike outcome is not chosen here.
