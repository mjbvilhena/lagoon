# Chat decisions, 2026-10-17

> **Status: Recorded. Not a specification approval.**
> These are decisions Miguel (`mjbvilhena`) made in chat after the specification was approved. They are not a new approval of `docs/product/specification.md`. They do not approve any story. Story status stays Draft.
> Skill: `sdlc-product-owner`. Template: `get_sdlc_template("product spec")`, used only to keep this addendum beside the approved specification. Definition of Done: `get_definition_of_done("product spec")` and `get_definition_of_done("epic")`, artefact shape only. Nothing here is built.
> The approved specification text is unchanged by this file. The "Chat decisions" section was removed from that specification so the approved text stays the approved text.

## 2026-10-17 at 15:59 IST

Decided by Miguel (`mjbvilhena`) in chat. The message that recorded the decision stated the decision. It did not add a separate verbatim sentence beyond that decision.

- A lock-screen reminder for a sensitive item shows only a generic "Lagoon reminder", with no title and no codes. Details appear after unlock. US-75. Ordinary items still follow his 14:56 IST decision.
- A token-looking URL is not auto-fetched. A foggy placeholder image stands in for the preview. He is asked whether to enable the preview for that link. A fetch runs only after he says yes. US-79.

## 2026-10-17 at 16:00 IST

Decided by Miguel (`mjbvilhena`) in chat. His words: "Requirement will be to let the user choose to disable (if possible), otherwise will have to disclose and accept".

Official Google ML Kit docs do not document a disable switch. US-07 records disclosure and acceptance before the first OCR use. The same disclosure is readable in Settings (US-59). The research note is `docs/product/research/2026-10-17-ml-kit-telemetry.md`.

## 2026-10-17 at 18:05 IST

Decided by Miguel (`mjbvilhena`) in chat, answering whether topic extraction should be on by default for new items. His words: "Topics on by default, with an off switch".

US-41 and US-59. The topic switch starts on. He can turn it off in Settings.

This quote does not say that a sensitive item stays excluded. That exclusion is the amendment approved on 2026-10-17 at 15:55 IST, recorded on US-75. Epic E11 cites that amendment for the exclusion, and cites this addendum for the default.

## What these decisions do not close

Still open for Miguel. Not decided here:

- Sync conflicts and how a deletion propagates. US-73.
- How the vault key reaches a new device. US-74.
- Topic provider, retention and training, credential supply, and payload. US-76.
- Exact alarms. US-77.
- Whether 09:00 should follow a fixed zone.
- Full-screen QR display.
- US-28 "Add date".
- US-68 scope, and the scope and priority of US-82, US-83, and US-84.
- Whether the vault key is auth-bound. US-35.
- The merchant weight of 5 on US-50.
- A per-item "Include in topics" opt-in. It goes beyond the 18:05 IST quote.
- Whether a sensitive notification's private version is also only "Lagoon reminder". US-75.
