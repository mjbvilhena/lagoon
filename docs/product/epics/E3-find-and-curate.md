# Epic E3: Find and curate

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-18.
> Amendment approved by mjbvilhena on 2026-10-20.

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
  - Sharing an item back out to other apps, as US-64 (Should). The acceptance criteria are on that story.
  - Room FTS5 search is proved against the encrypted database before search and at-rest encryption are built on that pair (A-E3-1, US-57). Acceptance: search and encryption are not built on a combination that cannot run.
  - After the undo snackbar is gone, a deleted item can sit in trash and be restored (A-E3-2, US-66, Must). Acceptance: delete with undo (US-13) still exists. Trash keeps an item for 30 × 24 hours. Amendment approved by mjbvilhena on 2026-10-20. This amendment is not approval of the story. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The security parts stay Proposed for mjbvilhena. They are what a sensitive Trash row shows, the scenario "a sensitive item in trash", and the items in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". This story is not approved until he approves those parts.
  - Amendment approved by mjbvilhena on 2026-10-20. US-66 is Must for v1. v1 does not ship without it. The in-trash state merges by the later `trashChangedAt`, not by `updatedAt`. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. Only a user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. A system write does not. An older user edit is kept. A reminder that fell due in trash fires once on restore. A restore made before the deadline that arrives after a tombstone brings the item back. When 30 × 24 hours after that instant end, the delete becomes a tombstone and spreads. A delete does not become a tombstone by skipping trash. An id with a tombstone is not a one-sided item that the other phone keeps. Decided by mjbvilhena; this record is Proposed until he approves it. This amendment is not approval of the story. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The security parts stay Proposed for mjbvilhena. They are what a sensitive Trash row shows, the scenario "a sensitive item in trash", and the items in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". This story is not approved until he approves those parts.
  - The Untagged inbox can apply one filing action to several items at once (A-E3-3, US-71, Could). Acceptance: filing does not require opening each row. Filing one item at a time still works.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A numeric latency SLO. A merge that is not the confirmed rename in US-29. OCR itself (E7). Trip grouping (E10, not v1). Sharing an item back out is in scope above as US-64, a Should.

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
- Ranking is the US-15 rule in the specification: weighted BM25, including OCR text. US-15 and US-50 are approved. The Room 3 / FTS5 / SQLCipher spike is a design-gate risk.
- Rename applies to every item. A case-insensitive name clash asks for confirmation and then merges. See US-29. US-15, US-50, and US-64 are approved. US-66 is Proposed for story review and is not approved. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The security parts stay Proposed for mjbvilhena. They are what a sensitive Trash row shows, the scenario "a sensitive item in trash", and the items in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". This story is not approved until he approves those parts. The other stories in this epic stay Draft.

## Open Questions

- Tag rename-and-merge, the inbox, and date sort are in the specification. US-15, US-50, and US-64 are approved. US-66 is Proposed for story review and is not approved. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The security parts stay Proposed for mjbvilhena. They are what a sensitive Trash row shows, the scenario "a sensitive item in trash", and the items in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". This story is not approved until he approves those parts. The other stories in this epic stay Draft. Sync merge and encryption are resolved on E4 and E8. Conflicts and deletions are US-73. Criteria follow from the ADR at the design gate.

## Draft stories

- US-10 Browse items, newest first
- US-11 Read an item
- US-12 Edit an item, including why it matters
- US-13 Delete an item and undo it
- US-14 Manage tags
- US-15 Search with ranked, highlighted matches. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- US-50 Rank search matches. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- US-16 Filter by type and by tag
- US-29 Rename a tag, and merge when the name exists
- US-38 See untagged items in an inbox
- US-39 Sort and filter by the date saved
- US-57 Prove search on an encrypted database
- US-64 Share or open an item in another app. Should. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- US-66 Keep a trash beyond undo. Must. Amendment approved by mjbvilhena on 2026-10-20.
- Amendment approved by mjbvilhena on 2026-10-20. US-66 is Must for v1. v1 does not ship without it.
- Proposed for story review. Not approved. Decided by mjbvilhena; this record is Proposed until he approves it.
- The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The security parts stay Proposed for mjbvilhena. They are what a sensitive Trash row shows, the scenario "a sensitive item in trash", and the items in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". This story is not approved until he approves those parts.
- US-71 Triage the inbox in a batch. Could.
