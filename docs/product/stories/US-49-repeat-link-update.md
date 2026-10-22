# US-49 Update a link that was saved before, and undo that update

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Update a link that was saved before, and undo that update.

## User story

**As a** person named Miguel
**I want** a second share of the same page to update the item I already have, unless I keep it separate
**So that** one page does not become two items, and a mistake is reversible

## Background

- US-01 stores a new link. This story is only the repeat.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: choosing which query parameters count as tracking beyond the list below. Order ids and booking ids are not tracking. Conflict sync is US-73.

## Acceptance criteria

- **Scenario: normalisation** — Must
  - Given two URLs differ only by scheme (`http` or `https`), a leading `www.`, a trailing slash, a `#fragment`, host case, or one of these tracking parameters: `utm_*`, `fbclid`, `gclid`, `mc_eid`, `igshid`, or YouTube `si`
  - When he shares the second URL
  - Then they are the same item and the stored source URL stays the first one
- **Scenario: other query parameters stay distinct** — Must
  - Given the URLs differ by a query parameter that is not in that tracking list, including an order id or a booking id
  - When he shares the second URL
  - Then a new item is stored
- **Scenario: the existing item is updated** — Must
  - Amendment approved by mjbvilhena on 2026-10-22. A match that is in trash is "the match is in trash", not this scenario. Two live matches are "two live items match", not this scenario.
  - Given the normalised URL matches one item that is not in trash
  - When the share is stored
  - Then the preview and snapshot refresh only when a fetch supplies them, tags are unioned, existing text is not overwritten, and the new note is appended under a date separator `YYYY-MM-DD`
  - And last-saved moves to this save, created stays the first save, and he sees "Saved before, added to existing" with Undo and "Keep separate"
- **Scenario: two live items match** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given the normalised URL matches two items that are not in trash
  - When he shares that URL again
  - Then Lagoon asks him which item to update
  - And the update does not run until he chooses
  - And the item he chooses is updated as in "the existing item is updated"
  - Proposed amendment. Needs approval by mjbvilhena. If either matching item is sensitive, that item in the picker is neutral (no title or preview) until the US-37 unlock, then it shows the title. This is not approved.
- **Scenario: the previous preview stays while the snackbar is open** — Must
  - Given that snackbar is showing
  - When he has not chosen Undo or "Keep separate"
  - Then the item still shows the previous preview
- **Scenario: Undo** — Must
  - Given that snackbar is showing
  - When he chooses Undo
  - Then the item returns to the fields it had before this share, including the previous preview, and no second item remains
- **Scenario: Keep separate** — Must
  - Given that snackbar is showing
  - When he chooses "Keep separate"
  - Then the original item is restored and a second item holds this share
- **Scenario: the match is in trash** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given the normalised URL matches an item that is in trash
  - When he shares that URL again
  - Then the match finds that item and offers to restore it
  - And the ordinary update of that item does not run yet
  - Security criteria approved by mjbvilhena on 2026-10-22. When a re-shared link matches a sensitive item in Trash, the restore offer is neutral (no title or preview) before unlock and shows the title only after the US-37 unlock.
- **Scenario: he accepts the restore** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given that offer is showing
  - When he accepts
  - Then that accept is a restore of the matched item, as in US-66
  - And the ordinary update does not run
  - And the restored item's other fields are unchanged, apart from the new share's note and tags
  - And a second item is not created
  - And `trashChangedAt` is the restore time, and that write does not set `userEditedAt`
- **Scenario: the accepted restore keeps the new note and tags** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given the restore offer is showing, and the new share has a note or tags
  - When he accepts
  - Then that note and those tags are added to the restored item the same way a live update adds them: the note is appended under a date separator `YYYY-MM-DD`, and the tags are unioned
  - And the ordinary update does not run
  - And the restored item's other fields are unchanged
  - And that accept does not set `userEditedAt`
- **Scenario: he declines the restore** — Must
  - Amendment approved by mjbvilhena on 2026-10-22.
  - Given that offer is showing
  - When he declines
  - Then the share is saved as a new, separate item
  - And the trashed item stays in trash
- **Scenario: snackbar closes** — Must
  - Given he does not choose Undo or "Keep separate"
  - When the snackbar closes
  - Then the update remains, including a newly fetched preview

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Snackbar text is exactly "Saved before, added to existing".
- Undo and "Keep separate" have accessible names.
- No placeholder sentences.

## Dependencies and risks

- Depends on US-01. A URL typed in US-06 uses this match.
- Two live items that share one URL, including after he declined a trashed match and later restored it, are "two live items match".
- Preview and snapshot refresh only when US-08 and US-33 can supply them. A failed fetch does not clear what is already stored.
- Re-index and topic re-queue on this update are US-58.

## Implementation notes

Not a design spec. No `LAYER.md` exists.
