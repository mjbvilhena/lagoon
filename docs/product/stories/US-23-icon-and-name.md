# US-23 Show a launcher icon and the name Lagoon

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Lagoon is the joint Q1 recommendation of Scout and Mikey's Vault App Bot under Miguel's delegation. It is not his approval.

## Title

Show a launcher icon and the name Lagoon.

## User story

**As a** person named Miguel
**I want** the app to have its own icon and the short name Lagoon
**So that** I can recognize it in the launcher and in the share sheet

## Background

- He asked for a tasteful app icon and a short name. Q1, the joint recommendation of Scout and Mikey's Vault App Bot under his delegation, names it Lagoon. That is not his approval of the spec.
- Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a wordmark animation, and drawing the glyph in this story.

## Acceptance criteria

- **Scenario: launcher** — Must
  - Given the draft recommendation stands
  - When he looks at the Android launcher
  - Then this app shows the name Lagoon and an icon drawn for it, not the default Android system icon
- **Scenario: share sheet** — Must
  - Given the same recommendation
  - When he opens the system share sheet
  - Then the vault is listed as Lagoon
- **Scenario: recommendation is not approval** — Must
  - Given Miguel has not approved the specification
  - When this story is only a draft
  - Then it is not treated as accepted, and implementation has not started

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The icon reads at launcher size. The UX or visual pass draws it. This story does not describe the glyph.
- Lagoon is short enough for a launcher label. The glyph is still for the later visual pass.

## Dependencies and risks

- Q1 is no longer open. The recommendation is not spec approval. Play Store and trademark checks were not done.
- Q2 (application id `com.mjbvilhena.lagoon`) is the same joint recommendation. US-26 carries it. It is not this story.

## Implementation notes

Launcher label and adaptive icon resources. Not a design spec.
