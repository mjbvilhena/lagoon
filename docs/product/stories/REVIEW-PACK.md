# Story review pack

> **Approval status: Draft. Not approved.**
> Listing a story here is not a sign-off. No story is Approved.

Stories stay Draft. The map is `docs/product/stories/TRACEABILITY.md`. The index is `docs/product/stories/INDEX.md`.

## Requirements

- If the vault key is lost, the content is lost. There is no recovery. App lock is optional. The device PIN is the fallback when there are no biometrics.
- An ordinary lock-screen reminder may show the item title and codes. A code is text already in that title. There is no separate code field. A sensitive lock-screen reminder's public text is only "Lagoon reminder". Whether its private version is also only that text stays open on US-75.
- The zip export is encrypted with the vault key.
- A token-looking URL is not auto-fetched. A foggy placeholder is shown until that preview is enabled.
- Before the first OCR use, he accepts a disclosure: the image and the recognized text stay on the device, and ML Kit still sends usage and diagnostic metrics to Google. Decline, or no answer, means OCR does not run and ML Kit is not started. He can withdraw that acceptance in Settings. There is no telemetry off switch. The research note is `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`.
- Topic extraction starts on for a new ordinary item. He can turn it off in Settings. Sensitive items are excluded from the topic call. There is no cap on topic suggestions. The topic job has its own unmetered setting.
- An empty title fills from the page title or the file name, including a screenshot, image, or scan.
- Camera capture uses the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`.
- The Upcoming list is the one reminder or the one expiry. It does not include a return-by date or a warranty end.
- Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.

## Open questions

- What happens when the same item was edited on two devices, and how does a deletion propagate? US-73. Options on that story are not chosen.
- How does the vault key reach a new device? US-74.
- Which topic provider is used, what it retains and whether it trains, how the credential is supplied, and what the call sends? US-76. The APK must not contain the key in plaintext.
- Whether 09:00 follows a fixed zone instead of the device local zone. Stories use the device local zone.
- Full-screen QR and barcode display. No story.
- Whether a per-item "Include in topics" control exists. US-75. The default is that sensitive items are excluded and ordinary extraction starts on.
- Whether a sensitive notification's private version is also only "Lagoon reminder". US-75.
- Whether the vault key is bound to device authentication. US-35 does not choose it.

## Removed

US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Those numbers are not reused. Picking merchants out of a receipt stays later, with no story.

## Shape

Artefact shape is the user-story Definition of Done: As a / I want / So that, and Given / When / Then. An open question is not answered here.

US-73, US-74, and US-76 have no asserting Must criterion. They stay. US-77 has asserting criteria and stays Draft. No story is Done. There is no app.

## Threat note

`docs/technical_design/2026-10-17-proposed-threat-note.md` is unapproved hypotheses. It is not a threat model. The design band is closed.
