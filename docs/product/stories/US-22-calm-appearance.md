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
- Out of scope: a second brand accent, drawing wireframes in this story, and a second theme pack. Dynamic color is an optional toggle, not the default. The accent values below are the bots' consensus on Q3. The UX gate can still check them. That check has not been done. Not approval.

## Acceptance criteria

- **Scenario: light and dark** — Must
  - Given items on the home list and an open item
  - When he uses the app in light mode and in dark mode
  - Then, with dynamic color off, both modes use the neutral palette and the teal accent, and neither mode takes that accent from the wallpaper
  - And the light accent is about `#0F766E` and the dark accent is about `#2DD4BF`, unless the later UX gate records a different pair
- **Scenario: contrast on the dark accent** — Must
  - Given dark mode and the accent about `#2DD4BF`
  - When text is drawn on that accent
  - Then that text is dark: black or `#003731`. It is not white
  - And `#0F766E` is not used as a color on dark surfaces
- **Scenario: dynamic color toggle** — Must
  - Given dynamic color is available on the device
  - When the toggle is off
  - Then the vault uses the teal fallback
  - And when he turns the toggle on, the vault may follow the system dynamic colors
  - And the default is off
- **Scenario: dynamic color off by default** — Must
  - Given the device has a wallpaper that would normally tint Material You apps, and he has not turned the toggle on
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
- **Scenario: UX gate may still check the accent** — Must
  - Given these hex values are a delegated recommendation and not Miguel's approval
  - When a later UX artefact is agreed
  - Then that artefact may keep or adjust the accent, and this story follows the agreed values. Until then the recommendation stands in the draft, and implementation has not started.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Contrast has to be enough to read body text in both modes. The later accessibility audit checks WCAG. This story does not claim a conformance level.
- Type size follows the system text scale so he can enlarge it.
- Whitespace, type choice, and motion curves are the UX artefact. His adjectives are the constraint. This story does not pick a font or a duration in milliseconds.

## Dependencies and risks

- Q3 is the bots' consensus, not an open question and not approval. The UX gate can still check the accent. UX design has not started.
- US-10, US-11, and the settings stories are the surfaces. This story is the visual constraint on them, not a second information architecture.

## Implementation notes

Material 3 with dynamic color disabled. Not a design spec and not a `LAYER.md`.
