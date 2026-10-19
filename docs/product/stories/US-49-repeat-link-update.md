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
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
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
  - Given the normalised URL matches an item
  - When the share is stored
  - Then the preview and snapshot refresh only when a fetch supplies them, tags are unioned, existing text is not overwritten, and the new note is appended under a date separator `YYYY-MM-DD`
  - And last-saved moves to this save, created stays the first save, and he sees "Saved before, added to existing" with Undo and "Keep separate"
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
  - Proposed.
  - Given the normalised URL matches an item that is in trash
  - When he shares that URL again
  - Then the match finds that item and offers to restore it
  - And a duplicate item is not created
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
- Preview and snapshot refresh only when US-08 and US-33 can supply them. A failed fetch does not clear what is already stored.
- Re-index and topic re-queue on this update are US-58.

## Implementation notes

Not a design spec. No `LAYER.md` exists.
