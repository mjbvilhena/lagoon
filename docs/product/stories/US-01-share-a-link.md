# US-01 Share a link into the vault

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> The duplicate-URL behavior is a consensus recommendation from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot under that delegation. It is not approval.

## Title

Share a link into the vault.

## User story

**As a** person named Miguel
**I want to** share a link from another app into the vault
**So that** a page I care about is kept on my phone without me copying the address by hand

## Background

- Today the vault does not exist. A link he sees in a browser, YouTube, or X stays in that app.
- Domain constraints: `get_domain_consultant("capture")` found no `DOMAIN.md`. No MUST/NEVER rules are implied by a consultant. Do not invent any.
- Out of scope: fetching the page (US-08, US-09), search, backup.
- A second share of the same page updates the existing item. The match uses a normalised URL. The stored source URL stays the one from the first save.

## Acceptance criteria

- **Scenario: share a link** — Must
  - Given the vault is installed and Miguel is viewing a link in another app
  - When he opens the system share sheet and chooses the vault
  - Then one tap stores one item of type `link` with that URL as the source URL, a created date of the save, and the same instant as the last-saved date. Tags stay empty unless he taps a last-used tag afterwards (US-28)
- **Scenario: the share has no URL** — Must
  - Given he tries to share content that has no URL and is not plain text, an image, or a PDF
  - When the vault receives it
  - Then no item is stored and he can see that nothing was saved
- **Scenario: the same page is shared again** — Must
  - Given a link item already exists
  - When he shares a URL that matches it after normalisation
  - Then the vault updates that item, keeps the original source URL, refreshes the preview and the snapshot, unions the tags, and does not overwrite existing text
  - And a new note is appended as its own dated entry, the last-saved date moves to this save, and the created date stays the first save
  - And he sees the snackbar "Saved before, added to existing" with Undo and "Keep separate"
- **Scenario: normalisation** — Must
  - Given the stored URL and the newly shared URL differ only by scheme (`http` or `https`), a leading `www.`, a trailing slash, a `#fragment`, `utm_*` parameters, or other tracking parameters, and the host compared in lowercase
  - When he shares the new URL
  - Then they count as the same item, and the stored source URL is still the original one
- **Scenario: a different page** — Must
  - Given the newly shared URL still differs after that normalisation
  - When he shares it
  - Then a new item is stored and the snackbar above is not shown
- **Scenario: text is kept** — Must
  - Given the existing item has a title, body, personal note, or extracted text
  - When the same page is shared again
  - Then those texts are still there, unchanged, and any note on this share is an additional dated entry
- **Scenario: Undo** — Must
  - Given the snackbar is showing after an update
  - When he chooses Undo
  - Then the item returns to the fields it had before this share, including tags, preview, snapshot, notes, and last-saved date, and no second item remains
- **Scenario: Keep separate** — Must
  - Given the snackbar is showing after an update
  - When he chooses "Keep separate"
  - Then the original item returns to the fields it had before this share, and a second item holds this share

## Analytics and observability (optional)

Not applicable. The brief forbids analytics. No events are proposed.

## Design and a11y notes

- The share sheet lists the vault as Lagoon. That name is the joint Q1 recommendation of Scout and Mikey's Vault App Bot under Miguel's delegation. It is not his approval of the spec.
- The snackbar text is exactly "Saved before, added to existing". Undo and "Keep separate" are named actions.
- No placeholder sentences in the shipped UI.
- The share target has a name a screen reader can announce. Exact layout is for the later UX artefact, which does not exist yet.

## Dependencies and risks

- Blocks US-08 and US-09.
- Q9 consensus, not approval: one tap saves. Last-used tag chips are US-28. Title auto-fill is US-40. Editing later is US-12.
- A URL added by hand (US-06) uses this same match.
- Last-saved is the date the home list and the date sort use (US-10, US-39). Created date does not move on a re-save.
- Preview and snapshot refresh only when a fetch can supply them (US-08, US-33). A failed fetch does not invent either one and does not clear the ones already stored.
- No feature flag was requested.

## Implementation notes

Likely an Android share intent for text/URLs in the app module. Not a design spec. No `LAYER.md` exists. This recommendation is not approval.
