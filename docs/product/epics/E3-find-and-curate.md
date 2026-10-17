# Epic E3: Find and curate

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.

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
  - Tag management, including rename. A rename applies to every item. A case-insensitive clash asks for confirmation and then merges. No other merge path.
  - An Untagged / Inbox view. Sort and filter by date saved.
  - Room + FTS search across title, body, extracted text, OCR text, tags, and notes.
  - Ranked results and visible highlighting of the match.
  - Filter by type and by tag.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A numeric latency SLO. Sharing an item back out to other apps. A merge that is not the confirmed rename in US-29. OCR itself (E7). Trip grouping (E10, not v1).

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
- Ranking is the US-15 rule in the specification approved by `mjbvilhena` on 2026-10-17: weighted BM25, including OCR text. This epic is Approved. Its stories stay Draft. The Room 3 / FTS5 / SQLCipher spike is a design-gate risk.
- Q4 is part of that approved specification: rename applies to every item. A case-insensitive name clash asks for confirmation and then merges. See US-29. This epic is Approved. Its stories stay Draft.

## Open Questions

- Q4 and Q9 are part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is Approved. Its stories stay Draft. Q5 and Q10 were decided by him and are not open here. Conflicts and deletions on sync are his questions on E4, not this epic.

## Draft stories

- US-10 Browse items, newest first
- US-11 Read an item
- US-12 Edit an item, including why it matters
- US-13 Delete an item and undo it
- US-14 Manage tags
- US-15 Search with ranked, highlighted matches
- US-16 Filter by type and by tag
- US-29 Rename a tag, and merge when the name exists
- US-38 See untagged items in an inbox
- US-39 Sort and filter by the date saved
