# US-22 Use a calm light and dark appearance

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Visual specifics beyond his words wait for the UX artefact. This story does not approve that artefact and does not invent screens.

## Title

Use a calm light and dark appearance.

## User story

**As a** person named Miguel
**I want** the vault to look quiet and finished in both light and dark
**So that** it feels like a personal tool, not a demo, and I can read it at night

## Background

- He asked for a polished, professional, minimalist Material 3 app: calm, lots of whitespace, refined typography, a restrained neutral palette with a single accent, smooth subtle animations, light and dark, and no reliance on Material You dynamic color. No clutter. No placeholder text in the shipped UI.
- Domain constraints: none. No `DOMAIN.md`. No `LAYER.md`.
- Out of scope: choosing the accent color (Q3). Drawing wireframes. Dynamic color as an option he can turn on. A second theme pack.

## Acceptance criteria

- **Scenario: light and dark** — Must
  - Given items on the home list and an open item
  - When he uses the app in light mode and in dark mode
  - Then both modes use the same neutral palette and the same single accent, and neither mode takes that accent from the wallpaper
- **Scenario: dynamic color off** — Must
  - Given the device has a wallpaper that would normally tint Material You apps
  - When he opens the vault
  - Then the vault's colors do not follow that wallpaper
- **Scenario: no placeholder copy** — Must
  - Given any shipped screen in this story's surfaces (home, detail, search, settings)
  - When he reads it
  - Then he does not see lorem or other fake body text standing in for his content
- **Scenario: motion** — Must
  - Given he opens an item from the list and goes back
  - When the screen changes
  - Then the change is animated, the animation finishes and leaves him on the destination, and it does not block the back action
- **Scenario: accent not chosen yet** — Must
  - Given Q3 is still open
  - When this story is implemented
  - Then implementation waits for the accent decided at the UX gate. This story does not name a hex color.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Contrast has to be enough to read body text in both modes. The later accessibility audit checks WCAG. This story does not claim a conformance level.
- Type size follows the system text scale so he can enlarge it.
- Whitespace, type choice, and motion curves are the UX artefact. His adjectives are the constraint. This story does not pick a font or a duration in milliseconds.

## Dependencies and risks

- Blocked on Q3 for the actual accent.
- US-10, US-11, and the settings stories are the surfaces. This story is the visual constraint on them, not a second information architecture.

## Implementation notes

Material 3 with dynamic color disabled. Not a design spec and not a `LAYER.md`.
