# US-65 Add a photo from the camera

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Could. The review asked for this as later than the v1 picker.

## Title

Add a photo from the camera.

## User story

**As a** person named Miguel
**I want** to take a photo or a multi-page scan inside the vault
**So that** I can add a page without leaving the item

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: v1 capture, which already shares images and uses the document picker (US-32).

## Acceptance criteria

- **Scenario: camera** — Could
  - Given an item is open
  - When he takes a photo
  - Then the photo is another attachment
- **Scenario: multi-page** — Could
  - Given he scans several pages
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
