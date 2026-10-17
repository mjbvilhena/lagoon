# US-65 Add a photo from the camera

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Should.

## Title

Add a photo from the camera.

## User story

**As a** person named Miguel
**I want** to start a new item from the camera
**So that** I can keep a page without a share from another app

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- The photo comes from the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`. The US-80 allowlist stays as it is.
- Out of scope: the share sheet and the document picker (US-03, US-32). Declaring `CAMERA` in the Lagoon manifest.

## Acceptance criteria

- **Scenario: a new item** — Should
  - Given he starts a capture with `ACTION_IMAGE_CAPTURE`
  - When the system camera returns a photo and he confirms
  - Then a new item is stored with that photo as its attachment
  - And the Lagoon manifest does not declare `CAMERA`
- **Scenario: multi-page** — Should
  - Given he takes several photos that way into that new item
  - When he saves them
  - Then they are attachments in that order
  - And Lagoon still does not declare `CAMERA`

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-32.

## Implementation notes

Not a design spec.
