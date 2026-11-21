# US-92 Re-index an item after an edit

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. Split from the unmet FTS re-index on US-12.

## Title

Re-index an item after an edit.

## User story

**As a** person named Miguel
**I want** an edit to update the search index
**So that** a later search sees the text I just saved

## Background

- US-12 says FTS is re-indexed from the new text, and that re-index is a Must (US-15).
- The US-57 spike result is blocked. No engine was chosen. The data layer says not to treat FTS5 plus SQLCipher as proven before that spike, and the fallback is not chosen in the spike.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: choosing the search engine, and building search (US-15).

## Acceptance criteria

- **Scenario: the edited text is indexed** — Must
  - Given an ordinary item whose body changed and an approved search engine is in the build
  - When the edit is saved
  - Then the index used by US-15 is rebuilt from the new text
  - And a search for a word that was removed no longer returns the item because of that word

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences.

## Dependencies and risks

- Blocked until a search engine is chosen. The US-57 note records blocked.

## Implementation notes

Do not pick FTS4 or FTS5 in this story. Not a design spec.
