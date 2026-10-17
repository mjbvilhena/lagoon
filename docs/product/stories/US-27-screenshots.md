# US-27 Review screenshots of the main screens

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Review screenshots of the main screens.

## User story

**As a** person named Miguel
**I want** screenshots of home, search results, item detail, share-to-save, and settings/backup
**So that** I can see those screens without installing a build first

## Background

- He asked for those five captures, from an emulator when possible, saved with the delivery artifacts.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a marketing site, device frames, and fake images drawn to look like the app.

## Acceptance criteria

- **Scenario: emulator available** — Must
  - Given an emulator can run the app
  - When the screenshots are taken
  - Then there is one image each for home, search results, item detail, the share sheet offering the vault, and settings/backup, and they show the real app, not a mock
- **Scenario: search and backup states** — Must
  - Given those shots
  - When he looks at search
  - Then the results are a real query against saved items, with a visible highlight
  - And the settings shot shows either a real backup status or the real "not configured" explanation from US-17
- **Scenario: no emulator** — Must
  - Given no emulator is available in the environment
  - When the delivery is reported
  - Then the report says why the shots are missing and does not include invented images

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Shots are evidence of the UX artefact once that artefact has been implemented. They are not a substitute for the UX artefact.
- Captures should include the empty home list only if that is the honest state. The home shot he asked for can show saved items. Do not seed lorem to fill the screen.

## Dependencies and risks

- Blocked by an implemented app and by the UX artefact. Neither exists.
- Share-sheet captures depend on the emulator image including a share UI.

## Implementation notes

Capture during a later implementation pass. Not started now. Not a design spec.
