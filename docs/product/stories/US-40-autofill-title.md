# US-40 Fill an empty title from the page or the file

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from the approved specification.

## Title

Fill an empty title from the page or the file.

## User story

**As a** person named Miguel
**I want** an empty title to fill from the page or from a readable file name
**So that** a one-tap save is not called Untitled

## Background

- He typed a title: it stays (US-08, US-12).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: using the first OCR line as the title.
- The title order, shared with US-01 and US-02, is: share subject, then a title he typed, then the page title or the file name, then leftover text, then host and path.

## Acceptance criteria

- **Scenario: a page title** — Must
  - Given a link whose page has a title and he did not type one
  - When enrichment returns it
  - Then the item title becomes that page title
- **Scenario: no page title** — Must
  - Given the page has no title
  - When the title is filled
  - Then it is the host and path of the normalised URL
- **Scenario: a camera-like file name** — Must
  - Given the only name is `IMG_`, `SCAN_`, or `DSC_` plus digits
  - When the title is filled
  - Then it is the type plus the saved date, such as "Image 17 Oct 2026", and not that camera name
- **Scenario: another file name** — Must
  - Given the file name is not one of those patterns
  - When the title is filled
  - Then the file name is the title, capped at 120 characters (US-02)
- **Scenario: he typed a title** — Must
  - Given he typed a title, or the share had a subject
  - When enrichment or a file name arrives
  - Then that title stays
- **Scenario: leftover text yields to a page title** — Must
  - Given the only title so far is leftover share text
  - When a page title or a readable file name arrives
  - Then that page title or file name becomes the title, and the leftover text stays in the note

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Body-only notes use US-05, not this story.

## Implementation notes

Fill an empty title only. Not a design spec.
