# US-65 Add a photo from the camera

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. Later than the v1 picker. Not a v1 gate.

## Title

Add a photo from the camera.

## User story

**As a** person named Miguel
**I want** to start a new item from the camera
**So that** I can keep a page without a share from another app

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: v1 capture, which already shares images and uses the document picker (US-32).

## Acceptance criteria

- **Scenario: a new item** — Should
  - Given he starts from the camera intent
  - When he takes a photo and confirms
  - Then a new item is stored with that photo as its attachment
- **Scenario: multi-page** — Should
  - Given he scans several pages into that new item
  - When he saves the scan
  - Then they are attachments in that order

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-32.

## Implementation notes

Not a design spec.
