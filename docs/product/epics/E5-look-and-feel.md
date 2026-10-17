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
  - Material 3, with a restrained neutral palette and a single accent.
  - Light and dark mode.
  - Dynamic color is not how the palette is chosen.
  - Calm layout, generous whitespace, refined typography, subtle motion.
  - No clutter and no placeholder text in the shipped UI.
  - A tasteful launcher icon and a short name he accepts.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** A component library other than Material 3. A named accent color in this epic (Q3). A final name in this epic (Q1). Wireframes.

## Core Workflows

1. **Use it in either mode**
   - He runs the app in light mode and in dark mode.
   - Both use the same neutral palette and the same single accent, not colors taken from the wallpaper.
2. **Recognize the app**
   - The launcher shows an icon made for this app and the short name he approved.

## Risks & Dependencies

- Q1 blocks the launcher label. Q3 blocks the accent. UX design is a later gate and is not started.
- "Tasteful" and "refined" are his words. This epic does not translate them into hex values or a typeface.

## Open Questions

- [ ] Q1. Short name. Suggestion only: Folio. (@mjbvilhena)
- [ ] Q3. Accent color, at the UX gate. (@mjbvilhena)

## Draft stories

- US-22 Use a calm light and dark appearance
- US-23 Install an icon and the approved short name
