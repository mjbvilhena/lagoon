# US-40 Fill the title from the page or the file

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Bots' consensus on Q9. Not approval.

## Title

Auto-fill a title when he did not type one.

## User story

**As a** person named Miguel
**I want** the title to fill from the page title or the file name
**So that** a one-tap save is not called "Untitled"

## Background

- Consensus: auto-fill the title from the page title or the file name.
- If he types a title, that title wins (US-08 already says a typed title is kept).
- A plain-text share with no separate title still uses the start of the text (US-02).
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: rewriting a title he already set. OCR as the source of the title. That can happen to supply text, but this story's sources are the page title and the file name.

## Acceptance criteria

- **Scenario: a link whose page has a title** — Must
  - Given he saves a link in one tap and did not type a title
  - And the fetch returns a page title
  - When enrichment finishes
  - Then the item's title is that page title, and the URL is unchanged
- **Scenario: a file** — Must
  - Given he saves an image or a PDF and did not type a title
  - When the item is stored
  - Then the title is the file name
- **Scenario: he typed a title** — Must
  - Given he set a title before auto-fill would run
  - When the page title or file name arrives
  - Then his title stays
- **Scenario: no page title and no file name** — Must
  - Given a link whose fetch returns no title
  - When the item is stored
  - Then it still has some non-placeholder title he can read, and the URL is kept

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Auto-filled titles are real strings from the page or the file. Never lorem.
- He can change the title later (US-12).

## Dependencies and risks

- Page title depends on US-08. Failure to fetch is US-09: the URL remains, and this story does not block the save.
- US-02 covers plain text.

## Implementation notes

Fill an empty title only. Not a design spec.
