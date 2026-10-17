# US-33 Keep an offline snapshot of a saved page

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E2.

## Title

Keep an offline snapshot of a saved page.

## User story

**As a** person named Miguel
**I want** a saved page to leave a sanitised offline copy, without blocking the save
**So that** I can read the page when the network is gone, and still keep the original URL

## Background

- One fetch with US-08. Cap about 10 MB in the specification. This story uses 10 MiB.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: MHTML. Whether link previews are skipped is delegated to Miguel's bots and is not decided here.

## Acceptance criteria

- **Scenario: the save is not blocked** — Must
  - Given he saved a link
  - When the snapshot has not finished
  - Then the item is already stored and readable as a URL
- **Scenario: one sanitised file** — Must
  - Given the fetch returns a page
  - When the snapshot is stored
  - Then it is one HTML file, images are downscaled `data:` URLs, and script, iframe, forms, event handlers, and remote URLs are removed
- **Scenario: the viewer** — Must
  - Given he opens the snapshot
  - When it is shown
  - Then the WebView has JavaScript off and network off
- **Scenario: the cap** — Must
  - Given the page is over 10 MiB
  - When the snapshot is built
  - Then images are dropped before text, the file stays within 10 MiB, and it is marked partial
- **Scenario: searchable** — Must
  - Given the snapshot has text
  - When US-15 indexes it
  - Then that text is in the snapshot column
- **Scenario: encrypted and synced** — Must
  - Given the snapshot is stored
  - When the vault is encrypted and synced
  - Then the snapshot is inside that encrypted store and is part of the vault payload
- **Scenario: failure and retry** — Must
  - Given the fetch fails
  - When he chooses retry
  - Then a new attempt runs, and the original URL is still on the item
- **Scenario: under the cap** — Must
  - Given the page fits
  - When the snapshot is stored
  - Then it is not marked partial

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The URL always stays (US-09).

## Implementation notes

Not a design spec. MHTML is later.
