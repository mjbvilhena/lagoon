# US-23 Show a launcher icon and the name Lagoon

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> The name Lagoon is the joint recommendation recorded in the approved specification. This story does not treat that recommendation as its own approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E5.

## Title

Show a launcher icon and the name Lagoon.

## User story

**As a** person named Miguel
**I want** the launcher and the share sheet to show Lagoon and its icon
**So that** I can find the app and share into it

## Background

- The approved specification names the app Lagoon.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a Play Store listing, and a second check that the recommendation "is not approval".

## Acceptance criteria

- **Scenario: launcher** — Must
  - Given the app is installed
  - When he opens the launcher
  - Then the label is Lagoon and an icon is shown
- **Scenario: adaptive icon** — Must
  - Given API 33 or newer
  - When the themed icon is requested
  - Then the adaptive icon includes a monochrome layer
- **Scenario: share sheet** — Must
  - Given he shares from another app
  - When the share sheet is shown
  - Then the target name is Lagoon

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The label is text, not only an image. No placeholder sentences.

## Dependencies and risks

- None beyond the app existing.

## Implementation notes

Test approach: CI checks the adaptive-icon XML for a monochrome layer. Launcher and share-sheet labels are instrumented checks. Not a design spec.
