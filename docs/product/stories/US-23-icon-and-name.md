# US-23 Show a launcher icon and the name Lagoon

> **Approval status: Draft. Not approved.**

**Epic:** E5.

**MoSCoW:** Must. v1.

## Title

Show a launcher icon and the name Lagoon.

## User story

**As a** person named Miguel
**I want** the launcher and the share sheet to show Lagoon and its icon
**So that** I can find the app and share into it

## Background

- The approved specification names the app Lagoon.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
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
