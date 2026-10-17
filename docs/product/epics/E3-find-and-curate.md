# Epic E3: Find and curate

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Does not approve child stories. Shipped-epic DoD is not claimed.

## Background & Value Proposition

A pile of saved items is not a knowledge store until Miguel can scan it, search it, and correct or remove what he saved. He asked for a fast full-text search over title, body, extracted text, tags, and notes, with ranked highlighted results and filters for type and tag.

No separate success metric was given. "Fast" was not given a millisecond budget. This draft does not invent one.

## Target Audience

Miguel, looking back through what he kept.

## Scope & Phasing

- **In Scope (MVP):**
  - Home list, most recent first.
  - Item detail.
  - Edit, including the personal note and tags.
  - Delete with undo.
  - Tag management.
  - Room + FTS search across title, body, extracted text, tags, and notes.
  - Ranked results and visible highlighting of the match.
  - Filter by type and by tag.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A numeric latency SLO. Sharing an item back out to other apps. OCR so that image pixels become searchable text.

## Core Workflows

1. **Browse and open**
   - He opens the app. Items are listed newest first.
   - He opens one and reads what was stored.
2. **Change or remove**
   - He edits fields, including why it matters, or deletes the item and undoes that delete.
   - He adds and removes tags.
3. **Search**
   - He types a query. Matching items appear with the match highlighted, in relevance order, and he can narrow them by type and tag.

## Risks & Dependencies

- Depends on E1 for items and on E2 for extracted page text and preview images to be present when enrichment worked.
- Ranking was requested without a formula. Stories US-15 state the smallest order that can be tested and leave the full score as Q-rank in that story, not a silent formula.
- "Tag management" is ambiguous on rename (Q4).

## Open Questions

- [ ] Q4. Rename tags in v1? (@mjbvilhena)
- [ ] Q9. Note and tags may be filled at edit time. (@mjbvilhena)

## Draft stories

- US-10 Browse items, newest first
- US-11 Read an item
- US-12 Edit an item, including why it matters
- US-13 Delete an item and undo it
- US-14 Manage tags
- US-15 Search with ranked, highlighted matches
- US-16 Filter by type and by tag
