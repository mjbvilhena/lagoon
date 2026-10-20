# US-27 Review screenshots of the main screens

> **Approval status: Draft. Not approved.**

**Epic:** E6.

**MoSCoW:** Must. v1. The images exist only when an emulator exists.

## Title

Review screenshots of the main screens.

## User story

**As a** person named Miguel
**I want** screenshots of home, search results, item detail, share-to-save, and settings
**So that** I can see those screens without installing a build first

## Background

- No images are fabricated. Settings shows sync state, not a backup button.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: a marketing page, and a Back up now control in the settings shot.

## Acceptance criteria

- **Scenario: emulator available** — Must
  - Given an emulator can run the app
  - When screenshots are taken
  - Then home, search results, item detail, share-to-save, and settings are captured from that emulator
- **Scenario: search and settings** — Must
  - Given those shots
  - When he looks at settings
  - Then it shows the real sync state, including the not-configured explanation from US-17 when that is the build, and it does not show a Back up now button
- **Scenario: no emulator** — Must
  - Given no emulator is available
  - When this story is reviewed
  - Then no screenshot file is invented, and the gap is stated

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Shots use the real UI copy. No placeholder sentences.

## Dependencies and risks

- The app does not exist yet, so no shots exist. That is the third scenario, not a failure of this draft.

## Implementation notes

Test approach: capture is manual on an emulator during implementation. Do not commit generated stand-in images now. Not a design spec.
