# US-33 Keep an offline snapshot of a saved page

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His decision: offline snapshots of saved web pages, keeping the original URL, are in v1. Not approval.

## Title

Read a saved page offline, and keep its URL.

## User story

**As a** person named Miguel
**I want** a saved web page to keep a copy I can read offline, and to keep the original URL
**So that** the page is still there when the network is not, and I can still open the source

## Background

- He decided v1 stores an offline snapshot of a saved web page and keeps the original URL.
- US-08 still stores title, description, preview image, and readable text when the fetch works. This story is the snapshot in addition to that.
- US-09 still keeps the URL when the fetch fails. No snapshot is invented in that case.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a logged-in or paywalled page. A pixel-perfect browser archive. The completeness of the snapshot beyond a readable copy is a story-level question.

## Acceptance criteria

- **Scenario: fetch works** — Must
  - Given he saves a link and the page can be fetched
  - When the save finishes
  - Then the item stores the original URL and a snapshot he can read with no network
- **Scenario: fetch fails** — Must
  - Given the device is offline or the fetch fails
  - When he saves the link
  - Then the URL is kept, no fake snapshot is stored, and the app does not crash
- **Scenario: the URL stays** — Must
  - Given a snapshot was stored
  - When he opens the item
  - Then he can still see the original URL

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The snapshot is readable text or a saved page he can scroll. The UX artefact chooses the presentation.
- The URL is text, not only a button.

## Dependencies and risks

- Depends on a link item from US-01 or US-06.
- How complete the snapshot is, beyond a readable copy plus the URL, is open and story-level. Do not require a full interactive site.

## Implementation notes

Store the snapshot with the item. Do not drop the URL. Not a design spec.
