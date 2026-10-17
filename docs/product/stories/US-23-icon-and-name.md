# US-23 Install an icon and the approved short name

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Blocked on Q1. Do not treat the suggestion "Folio" as the approved name.

## Title

Show a launcher icon and the approved short name.

## User story

**As a** person named Miguel
**I want** the app to have its own icon and a short name I have accepted
**So that** I can recognize it in the launcher and in the share sheet

## Background

- He asked for a tasteful app icon and a short name, and asked for a suggestion.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a wordmark animation, adaptive-icon marketing frames beyond a proper launcher icon, and renaming after release.

## Acceptance criteria

- **Scenario: launcher** — Must
  - Given Q1 has an accepted short name
  - When he looks at the Android launcher
  - Then this app shows that name and an icon drawn for it, not the default Android system icon
- **Scenario: share sheet** — Must
  - Given the same accepted name
  - When he opens the system share sheet
  - Then the vault is listed under that name
- **Scenario: name not accepted** — Must
  - Given Q1 is still open
  - When this story is picked up for implementation
  - Then it is not implemented under a guessed name, including the suggestion Folio

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The icon reads at launcher size. The UX or visual pass draws it. This story does not describe the glyph.
- The name is short enough for a launcher label. He decides the word (Q1).

## Dependencies and risks

- Blocked by Q1.
- Q2 (application id) is related but not this story. US-26 needs the application id.

## Implementation notes

Launcher label and adaptive icon resources. Not a design spec.
