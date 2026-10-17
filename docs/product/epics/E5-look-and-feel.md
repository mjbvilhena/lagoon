# Epic E5: Look and feel

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Does not approve child stories. Shipped-epic DoD is not claimed.
> This epic records visual constraints he already stated. It is not a UX artefact. Screens, layout, and copy beyond his words belong to `sdlc-ux-designer` after story approval. This draft does not invent screens.

## Background & Value Proposition

He asked for a vault that looks polished and professional, and also quiet: whitespace, type, one accent, light and dark, no clutter, no placeholder text. The app should look finished without Material You dynamic color.

No separate success metric was given.

## Target Audience

Miguel, using the app every time he saves or looks something up.

## Scope & Phasing

- **In Scope (MVP):**
  - Material 3. Light accent about `#0F766E`. Dark accent about `#2DD4BF`. In dark mode, text on the accent is dark: black or `#003731`. Never put `#0F766E` on a dark surface. White on `#2DD4BF` is not allowed.
  - Dynamic color is an optional toggle. Teal is the fallback when it is off. The UX gate can still check the teal values. Dynamic color is not how light or dark is chosen.
  - An in-app theme setting with three options: Light, Dark, and System. System follows the Android system light/dark setting. System is the default.
  - Light uses the light palette. Dark uses the dark palette. Those rules apply whether he chose the mode or System followed the phone.
  - Calm layout, generous whitespace, refined typography, subtle motion.
  - No clutter and no placeholder text in the shipped UI.
  - A tasteful launcher icon and the short name Lagoon.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A component library other than Material 3. A second brand accent beside teal. Dynamic color as the default. Wireframes in this epic. Treating this draft as epic approval. A Play Store listing.

## Core Workflows

1. **Choose light, dark, or the system**
   - A new install uses System. The vault matches the Android light/dark setting, and it changes when that setting changes.
   - He can choose Light or Dark. That choice stays until he picks another option, including System again.
   - With dynamic color off, the active mode uses the neutral palette and the teal accent, not colors taken from the wallpaper. If he turns the optional toggle on, the vault may follow the wallpaper. Teal is the fallback when the toggle is off.
2. **Recognize the app**
   - The launcher shows an icon made for this app and the short name Lagoon.

## Risks & Dependencies

- Q1 (the name Lagoon) and Q3 (the teal, dark on-accent text, optional dynamic color) are part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is still Draft and awaits his approval. Play Store and trademark checks for Lagoon were not done. The UX gate can still check the accent. UX design has not started.
- "Tasteful" and "refined" are his words. This epic does not pick a typeface. The accent values are the recommendation, not a finished visual design.

## Open Questions

- Q1 and Q3 are part of the approved specification, not open questions. This epic is still Draft and awaits his approval. The UX gate may still adjust the accent.
- On 2026-10-17 at 13:15 IST, during epic review, he said: "Look & feel should allow for light or dark theme, or follow the system setting." The three-way setting above is that clarification. It is not approval of this epic.
- Q5 and Q10 are Miguel's decisions (sync merges; extra encryption). They are not questions for this epic.

## Draft stories

- US-22 Choose light, dark, or the system theme
- US-23 Show a launcher icon and the name Lagoon
