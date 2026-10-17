# US-22 Choose light, dark, or the system theme

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> On 2026-10-17 at 13:15 IST, during epic review, he said: "Look & feel should allow for light or dark theme, or follow the system setting." That clarification is not approval of this story.
> Story approval has not been recorded.

**Epic:** E5. Not an approval.

**MoSCoW:** Must. v1, from approved epic E5. Palette, dynamic color, and motion are US-53.

## Title

Choose light, dark, or the system theme.

## User story

**As a** person named Miguel
**I want** to choose Light, Dark, or System, and have that choice stick
**So that** the vault follows my phone unless I choose otherwise

## Background

- System is the default and follows Android. The choice applies immediately and survives a restart.
- Domain constraints: no `DOMAIN.md`. No `LAYER.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: accent colors, dynamic color, and motion (US-53). A later UX check of the accent is not an acceptance criterion of this story.

## Acceptance criteria

- **Scenario: System is the default** — Must
  - Given a fresh install
  - When he has not chosen a theme
  - Then the theme is System and it follows the phone's light or dark setting
- **Scenario: he chooses Light or Dark** — Must
  - Given he chooses Light, or he chooses Dark
  - When the choice is made
  - Then the UI switches to that appearance without a restart
- **Scenario: the choice survives restart** — Must
  - Given he chose Light or Dark
  - When the process is killed and he opens the app again
  - Then that choice is still in effect
- **Scenario: back to System** — Must
  - Given he had chosen Light or Dark
  - When he chooses System
  - Then the app follows the phone again, immediately

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The three choices are named Light, Dark, and System. No placeholder sentences.

## Dependencies and risks

- Settings hosts this control (US-59).

## Implementation notes

Material 3. Default is System. Not a design spec.
