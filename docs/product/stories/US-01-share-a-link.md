# US-01 Share a link into the vault

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Story approval is separate from epic E1 approval. Drafted by `sdlc-user-story-refiner` on 2026-10-17 because that run explicitly asked for stories before the approval gates. That request is not approval.

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

## Acceptance criteria

- **Scenario: share a link** — Must
  - Given the vault is installed and Miguel is viewing a link in another app
  - When he opens the system share sheet and chooses the vault
  - Then one tap stores one item of type `link` with that URL as the source URL and a created date of the save. Tags stay empty unless he taps a last-used tag afterwards (US-28)
- **Scenario: the share has no URL** — Must
  - Given he tries to share content that has no URL and is not plain text, an image, or a PDF
  - When the vault receives it
  - Then no item is stored and he can see that nothing was saved
- **Scenario: the same URL is shared twice** — Must
  - Given a link item with that URL already exists
  - When he shares the same URL again
  - Then the vault does not silently delete the earlier item's personal note. Whether the second share updates the existing item or adds another one is left open in Dependencies. The note on the already stored item remains readable.

## Analytics and observability (optional)

Not applicable. The brief forbids analytics. No events are proposed.

## Design and a11y notes

- The share sheet lists the vault as Folio. That name is the Q1 recommendation from Mikey's Dev Bot under delegation. It is not Miguel's approval of the spec.
- No placeholder sentences in the shipped UI.
- The share target has a name a screen reader can announce. Exact layout is for the later UX artefact, which does not exist yet.

## Dependencies and risks

- Blocks US-08 and US-09.
- Q9 consensus, not approval: one tap saves. Last-used tag chips are US-28. Title auto-fill is US-40. Editing later is US-12.
- Open: a second share of the same URL. Not answered with a merge rule here.
- No feature flag was requested.

## Implementation notes

Likely an Android share intent for text/URLs in the app module. Not a design spec. No `LAYER.md` exists.
