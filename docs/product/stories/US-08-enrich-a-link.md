# US-08 Enrich a link when the page can be fetched

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Enrich a saved link from the page.

## User story

**As a** person named Miguel
**I want** the vault to store the page title, description, preview image, and readable text when it can fetch the page
**So that** a saved link is more than a bare address

## Background

- He asked for a best-effort fetch of those four things for shared links. This story also covers a URL he added by hand, because that item is the same type.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: failure and offline behavior (US-09). Login-walled or paywalled bodies. YouTube or X APIs.

## Acceptance criteria

- **Scenario: the page exposes all four** — Must
  - Given a saved link item and a network
  - And the URL returns a page with a title, a description, a preview image, and readable text
  - When enrichment finishes
  - Then the item stores that title, that description, that preview image, and that readable text, and it still stores the original URL
- **Scenario: the page exposes only some of them** — Must
  - Given the page has a title and readable text and no description or preview image
  - When enrichment finishes
  - Then the title and readable text are stored, the missing fields stay empty, the URL remains, and the app does not crash
- **Scenario: he already typed a title** — Must
  - Given he set a title on the link before enrichment
  - When enrichment would overwrite that title
  - Then his title is kept. Fetched text may still fill empty fields.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- A missing preview image is an empty state, not a broken image icon with placeholder copy.
- The preview image's text alternative is the item title.

## Dependencies and risks

- Blocked by a link item from US-01 or US-06.
- Which HTML signals are tried (title element, Open Graph, and so on) is technical design. This story only requires the four outcomes when the page provides them.
- His typed title wins. That is specified so enrichment does not destroy a title he entered. Description and the personal note are different fields. The personal note is never replaced by the page.

## Implementation notes

Best-effort HTTP fetch plus a reader. Not a design spec. No layer consultant exists.
