# US-23 Show a launcher icon and the name Folio

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Folio is the bots' consensus on Q1, including the note that the name is crowded on Play and should be renamed before any public release. It is not Miguel's approval. This sideload build may still be called Folio.

## Title

Show a launcher icon and the name Folio.

## User story

**As a** person named Miguel
**I want** the app to have its own icon and the short name Folio
**So that** I can recognize it in the launcher and in the share sheet

## Background

- He asked for a tasteful app icon and a short name. Q1, recommended by Mikey's Dev Bot under delegation, names it Folio. That is not his approval of the spec.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a wordmark animation, and renaming Folio inside this v1 sideload. A public release should use a different name. That rename is not this story.

## Acceptance criteria

- **Scenario: launcher** — Must
  - Given the draft recommendation stands
  - When he looks at the Android launcher
  - Then this app shows the name Folio and an icon drawn for it, not the default Android system icon
- **Scenario: share sheet** — Must
  - Given the same recommendation
  - When he opens the system share sheet
  - Then the vault is listed as Folio
- **Scenario: recommendation is not approval** — Must
  - Given Miguel has not approved the specification
  - When this story is only a draft
  - Then it is not treated as accepted, and implementation has not started

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The icon reads at launcher size. The UX or visual pass draws it. This story does not describe the glyph.
- Folio is short enough for a launcher label. The glyph is still for the later visual pass.

## Dependencies and risks

- Q1 is no longer open. The recommendation is not spec approval.
- Q2 (application id `com.mjbvilhena.folio`) is a separate delegated recommendation. US-26 carries it. It is not this story.

## Implementation notes

Launcher label and adaptive icon resources. Not a design spec.
