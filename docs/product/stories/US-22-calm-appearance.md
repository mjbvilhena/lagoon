# US-22 Use a calm light and dark appearance

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> On 2026-10-17 at 13:15 IST, during epic review, he said: "Look & feel should allow for light or dark theme, or follow the system setting." That clarification is not approval of this story or of epic E5.
> Visual specifics beyond his words wait for the UX artefact. This story does not approve that artefact and does not invent screens.

## Title

Choose light, dark, or the system theme.

## User story

**As a** person named Miguel
**I want** the vault to look quiet and finished in light or dark, and to follow my phone unless I choose otherwise
**So that** it feels like a personal tool, not a demo, and I can read it at night

## Background

- He asked for a polished, professional, minimalist Material 3 app: calm, lots of whitespace, refined typography, a restrained neutral palette with a single accent, smooth subtle animations, light and dark, and no reliance on Material You dynamic color. No clutter. No placeholder text in the shipped UI.
- Domain constraints: none. No `DOMAIN.md`. No `LAYER.md`.
- The theme setting has three options: Light, Dark, and System. System follows the Android light/dark setting and is the default. This is separate from the dynamic-color toggle.
- Out of scope: a second brand accent, drawing wireframes in this story, a schedule that switches theme by clock time, and a fourth theme. Dynamic color is an optional toggle, not the default. The accent values below are part of the specification approved by `mjbvilhena` on 2026-10-17. This story is still Draft. The UX gate can still check the accent. That check has not been done.

## Acceptance criteria

- **Scenario: System is the default** — Must
  - Given a new install and he has not opened the theme setting
  - When the Android system is in dark, and then he switches the system to light
  - Then the vault follows dark, and then follows light
- **Scenario: he chooses Light or Dark** — Must
  - Given the theme setting
  - When he chooses Light while the system is dark
  - Then the vault stays light until he chooses Dark or System
  - And when he chooses Dark while the system is light, the vault stays dark until he chooses Light or System
- **Scenario: back to System** — Must
  - Given he had chosen Light or Dark
  - When he chooses System
  - Then the vault follows the Android light/dark setting again, including a later change to that setting
- **Scenario: light and dark palettes** — Must
  - Given items on the home list and an open item
  - When the active appearance is light, and when it is dark, whether he chose it or System followed the phone
  - Then, with dynamic color off, both use the neutral palette and the teal accent, and neither takes that accent from the wallpaper
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
  - Given these hex values are part of the approved specification, and this story is still Draft
  - When a later UX artefact is agreed
  - Then that artefact may keep or adjust the accent, and this story follows the agreed values. Until then the recommendation stands in the draft, and implementation has not started.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Contrast has to be enough to read body text in both modes. The later accessibility audit checks WCAG. This story does not claim a conformance level.
- Type size follows the system text scale so he can enlarge it.
- Whitespace, type choice, and motion curves are the UX artefact. His adjectives are the constraint. This story does not pick a font or a duration in milliseconds.

## Dependencies and risks

- Q3 is part of the specification approved by `mjbvilhena` on 2026-10-17. This story is still Draft. The UX gate can still check the accent. UX design has not started.
- The Light / Dark / System setting is his epic-review clarification of 2026-10-17 at 13:15 IST. It is not approval of this story.
- US-10, US-11, and the settings stories are the surfaces. This story is the visual constraint on them, not a second information architecture.

## Implementation notes

Material 3. The theme setting defaults to System. Dynamic color stays off until he turns that separate toggle on. Not a design spec and not a `LAYER.md`.
