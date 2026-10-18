# US-85 Show an image or PDF page full-screen and bright

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Should.

## Title

Show an image or PDF page full-screen and bright.

## User story

**As a** person named Miguel
**I want** to open an image or a PDF page full-screen at maximum brightness
**So that** I can read an image or PDF page from the phone without another app

## Background

- One tap opens an image or a rendered PDF page, for example a boarding pass PDF.
- Brightness uses only this window's override: `WindowManager.LayoutParams.screenBrightness = 1.0f`. Lagoon does not request `WRITE_SETTINGS`. The system brightness setting is unchanged. The US-80 permission list is unchanged.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: editing the image or the page. The system camera (US-65).

## Acceptance criteria

- **Scenario: full screen** — Should
  - Given an ordinary item has an image or a PDF page
  - When he taps that image or PDF page once
  - Then it opens full-screen
  - And the window brightness override is `WindowManager.LayoutParams.screenBrightness = 1.0f`
  - And the system brightness setting is unchanged
  - And Lagoon does not request `WRITE_SETTINGS`
  - And when he closes the view, brightness goes back to normal
- **Scenario: a PDF page** — Should
  - Given an ordinary item has a PDF, for example a boarding pass
  - When he taps a page
  - Then that page is rendered full-screen
- **Scenario: keep the screen on** — Should
  - Given the full-screen view is open
  - When it is showing
  - Then the screen stays on
  - And when he closes the view, that keep-on ends
- **Scenario: pinch-zoom** — Should
  - Given the full-screen view is open
  - When he pinches
  - Then the image or PDF page zooms
- **Scenario: offline** — Should
  - Given the phone has no network
  - When he taps that image or PDF page
  - Then the full-screen view still opens
- **Scenario: a sensitive item** — Must if the story is built
  - Given the image or PDF page is on a sensitive item and he has not completed the US-37 unlock
  - When he taps it
  - Then the full-screen view does not open
  - And after the US-37 unlock, the same tap opens it
  - And `FLAG_SECURE` applies for that view, as US-75 says
- **Scenario: nothing written in the clear** — Should
  - Given he opens an image or a PDF page full-screen
  - When the page is rendered
  - Then no plaintext decoded image or rendered page is written to disk
- **Scenario: close** — Should
  - Given the full-screen view is open
  - When he uses the Close control
  - Then the view closes and brightness goes back to normal
  - And the Close control has a name a screen reader can announce

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- An accessible Close control.
- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The E1 wording for this story is approved. Amendment approved by mjbvilhena on 2026-10-18. This story stays Draft.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
