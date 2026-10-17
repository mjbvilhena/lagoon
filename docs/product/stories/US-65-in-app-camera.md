# US-65 Add a photo from the camera

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E1. Kept by Miguel on 2026-10-17 at 20:02 IST. Not a story approval.

**MoSCoW:** Should. In E1 by the amendment approved on 2026-10-17 at 20:02 IST. Not a story approval.

## Title

Add a photo from the camera.

## User story

**As a** person named Miguel
**I want** to start a new item from the camera
**So that** I can keep a page without a share from another app

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- The photo comes from the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`. That is Miguel's (`mjbvilhena`) fix of C-E1-2 on 2026-10-17 at 20:03 IST. The US-80 allowlist stays as it is.
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
