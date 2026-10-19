# US-40 Fill an empty title from the page or the file

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Fill an empty title from the page or the file.

## User story

**As a** person named Miguel
**I want** an empty title to fill from the page or from a file name
**So that** a one-tap save is not called Untitled

## Background

- He typed a title: it stays (US-08, US-12).
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: using the first OCR line as the title.
- The title order, shared with US-01 and US-02, is: share subject, then a title he typed, then the page title or the file name, then leftover text, then host and path (host only when the link is token-looking).

## Acceptance criteria

- **Scenario: a page title** — Must
  - Given a link whose page has a title and he did not type one
  - When enrichment returns it
  - Then the item title becomes that page title
- **Scenario: no page title** — Must
  - Given the page has no title and the link is not token-looking
  - When the title is filled
  - Then it is the host and path of the normalised URL
- **Scenario: a token-looking link** — Must
  - Given the link is token-looking under US-79, he did not type a title, and no page title is available
  - When the title is filled
  - Then it is the host only, not the path
- **Scenario: a file name** — Must
  - Given the only title source is a file name, including a screenshot, an image, or a scan
  - When the title is filled
  - Then the title is the file's display name as the platform reports it, including its final extension, capped at 120 characters (US-02)
- **Scenario: he typed a title** — Must
  - Given he typed a title, or the share had a subject
  - When enrichment or a file name arrives
  - Then that title stays
- **Scenario: leftover text yields to a page title** — Must
  - Given the only title so far is leftover share text
  - When a page title or a file name arrives
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
