# US-53 Use the teal palette, optional dynamic color, and reduced motion

> **Approval status: Draft. Not approved.**

**Epic:** E5.

**MoSCoW:** Must. v1.

## Title

Use the teal palette, optional dynamic color, and reduced motion.

## User story

**As a** person named Miguel
**I want** the accent to stay readable, dynamic color to stay off until I turn it on, and motion not to be required
**So that** I can read the app in light and dark and with reduced motion

## Background

- The specification's Q3 consensus says the light accent is about `#0F766E` and the dark accent is about `#2DD4BF`. The UX gate can still check the values. In dark mode the color on the accent is dark (black or `#003731`). Never use `#0F766E` on dark surfaces.
- Domain constraints: no `DOMAIN.md`. No `LAYER.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: choosing a different accent. Wallpaper color is not the default.

## Acceptance criteria

- **Scenario: light and dark accents** — Must
  - Given Light mode, or Dark mode
  - When a primary action is shown
  - Then Light uses the accent about `#0F766E` and Dark uses the accent about `#2DD4BF`, the values the UX gate records
- **Scenario: contrast on the dark accent** — Must
  - Given Dark mode
  - When text sits on the accent
  - Then that text is black or `#003731`, and `#0F766E` is not used as a dark-surface accent
- **Scenario: dynamic color is off and hidden on old APIs** — Must
  - Given a fresh install
  - When he has not used the toggle
  - Then dynamic color is off and teal is used
  - And on API 30 and below the toggle is not shown
- **Scenario: he turns dynamic color on** — Must
  - Given API 31 or newer and he turns the toggle on
  - When the system can supply dynamic colors
  - Then those system dynamic colors are used
  - And when dynamic color is unavailable, teal is used
- **Scenario: reduced motion** — Must
  - Given the system animator duration scale is 0
  - When a screen changes
  - Then he can complete the action with no motion, and no information is conveyed only by motion
- **Scenario: no placeholder copy** — Must
  - Given any of these screens
  - When they are shown
  - Then they do not contain lorem ipsum or "TODO" placeholder sentences

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Contrast follows the colors above. Layout waits for the UX artefact.

## Dependencies and risks

- The toggle lives in Settings (US-59).

## Implementation notes

Not a design spec.
