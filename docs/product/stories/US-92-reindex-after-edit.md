# US-92 Re-index an item after an edit

> **Approval status: Approved.**
> Approved on 2026-11-28 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant under Miguel's delegation of 2026-10-17.

**Epic:** E3.

**MoSCoW:** Must. Split from the unmet FTS re-index on US-12.

## Title

Re-index an item after an edit.

## User story

**As a** person named Miguel
**I want** an edit to update the search index
**So that** a later search sees the text I just saved

## Background

- US-12 says FTS is re-indexed from the new text, and that re-index is a Must (US-15). US-12 line 35 names tags, and the same edit also changes the title, the body, and personal notes.
- The US-57 spike result is pass. This story does not choose a cipher, and it does not choose FTS4 or FTS5.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Search covers title, body, extracted text, OCR text, tags, and the personal note. Snapshot text is searchable. Do not invent further rules.
- Out of scope: choosing the search engine, and building search (US-15). What a locked search shows is US-75.

## Decisions

mjbvilhena decided on 2026-11-28 at 07:11 GMT. He typed "1-5 approved as recommended". For this story that is item 2. Sensitive items are re-indexed after an edit too. US-75 governs what a locked search shows. This is a decision. It is not whole-story approval. US-92 stays Draft. Not approved.

## Acceptance criteria

- **Scenario: an edited indexed field is re-indexed** — Must
  - Given the US-15 search index is in the build
  - And the item, including a sensitive item, has an indexed field that the edit changes
  - And that field is any indexed field, including the title, the body, a personal note, or a tag
  - And the new text adds a word, removes a word, and keeps a word
  - When the edit is saved
  - Then that field is re-indexed from the new text
  - And, for an ordinary item, or for a sensitive item after the US-37 unlock, a search for the added word finds the item
  - And, for an ordinary item, or for a sensitive item after the US-37 unlock, a search for the removed word no longer finds the item because of that word
  - And, for an ordinary item, or for a sensitive item after the US-37 unlock, a search for the kept word still finds the item
  - And what a locked search shows for a sensitive item stays with US-75

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences.

## Dependencies and risks

- Needs the US-15 search index in the build. US-15 is approved. What a locked search shows is US-75, which is approved.
- The US-57 note records pass. It does not choose a cipher. This story does not choose the search engine.

## Implementation notes

Do not pick FTS4 or FTS5 in this story. Say re-indexed. Not a design spec.
