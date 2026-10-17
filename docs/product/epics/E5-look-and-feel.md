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
  - Dynamic color is an optional toggle. Teal is the fallback when it is off. The UX gate can still check the teal values.
  - Light and dark mode.
  - Dynamic color is not how the palette is chosen.
  - Calm layout, generous whitespace, refined typography, subtle motion.
  - No clutter and no placeholder text in the shipped UI.
  - A tasteful launcher icon and the short name Lagoon.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A component library other than Material 3. A second brand accent beside teal. Dynamic color as the default. Wireframes in this epic. Treating Lagoon or the teal values as Miguel's approval of the spec. A Play Store listing.

## Core Workflows

1. **Use it in either mode**
   - He runs the app in light mode and in dark mode.
   - Both use the same neutral palette and the same single accent, not colors taken from the wallpaper.
2. **Recognize the app**
   - The launcher shows an icon made for this app and the short name Lagoon.

## Risks & Dependencies

- Q1 is the joint recommendation of Scout and Mikey's Vault App Bot: the short name is Lagoon. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done. Q3 is the earlier bots' consensus (the teal, dark on-accent text, optional dynamic color). Neither is Miguel's approval. The UX gate can still check the accent. UX design has not started.
- "Tasteful" and "refined" are his words. This epic does not pick a typeface. The accent values are the recommendation, not a finished visual design.

## Open Questions

- Q1 and Q3 are recommendations, not open questions and not approval. The UX gate may still adjust the accent.
- Q5 and Q10 are Miguel's decisions (sync merges; extra encryption). They are not questions for this epic.

## Draft stories

- US-22 Use a calm light and dark appearance
- US-23 Show a launcher icon and the name Lagoon
