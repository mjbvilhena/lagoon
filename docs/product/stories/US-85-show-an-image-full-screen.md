# US-85 Show an image or PDF page full-screen and bright

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E1.

**MoSCoW:** Should.

## Title

Show an image or PDF page full-screen and bright.

## User story

**As a** person named Miguel
**I want** to open an image or a PDF page full-screen at maximum brightness
**So that** I can show a code, voucher, or boarding pass from the phone without another app

## Background

- One tap opens an image or a rendered PDF page, for example a boarding pass PDF.
- Brightness uses only this window's override: `WindowManager.LayoutParams.screenBrightness = 1.0f`. Lagoon does not request `WRITE_SETTINGS`. The system brightness setting is unchanged. The US-80 permission list is unchanged.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Proposed amendment. Needs approval by mjbvilhena. A password-protected PDF means only a PDF that needs a password to open. A PDF restricted only by an owner password opens, renders, and is searchable as normal. The scenario "a password-protected PDF" stays as written for a PDF that needs a password to open. This is not approved.
- Out of scope: editing the image or the page. The system camera (US-65).

## Acceptance criteria

- **Scenario: full screen** — Must if the story is built
  - Given an ordinary item has an image or a PDF page
  - When he taps that image or PDF page once
  - Then it opens full-screen
  - And the window brightness override is `WindowManager.LayoutParams.screenBrightness = 1.0f`
  - And the system brightness setting is unchanged
  - And Lagoon does not request `WRITE_SETTINGS`
  - And when he closes the view, brightness goes back to normal
- **Scenario: a PDF page** — Must if the story is built
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
  - Then the US-37 prompt is shown first
  - And on success the image or PDF page opens full-screen
  - And on cancel nothing is shown
  - And `FLAG_SECURE` applies for that view, as US-75 says
- **Scenario: swipe to the next or previous page or image** — Should
  - Given the full-screen view is open on a PDF page or an image attachment, for example a family boarding-pass PDF with one page per passenger
  - When he swipes to the next or the previous
  - Then he moves to the next or previous PDF page, or to the item's next or previous image attachment
  - And the order across several PDFs and images follows the item's attachment order, including every page of each PDF
  - And a swipe does not wrap at either end
  - And he does not leave the full-screen view
  - And brightness and keep-screen-on stay in effect
- **Scenario: a password-protected PDF** — Could
  - Given the PDF is password-protected
  - When he taps it
  - Then the full-screen view does not open
  - And he is told the PDF is password-protected
  - And he is offered to open it in another app (US-64)
  - And no password is asked for or stored
- **Scenario: an owner password only** — Proposed amendment. Needs approval by mjbvilhena.
  - Given the PDF does not need a password to open, and it is restricted only by an owner password
  - When he taps a page
  - Then it opens, renders, and is searchable as a normal PDF
  - And the password-protected scenario does not apply
  - And no password is asked for or stored
- **Scenario: nothing written in the clear** — Must if the story is built
  - Given he opens an image or a PDF page full-screen
  - When the page is rendered
  - Then no plaintext decoded image or rendered page is written to disk
- **Scenario: close** — Must if the story is built
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

- The E1 wording for this story is approved. Amendment approved by mjbvilhena on 2026-10-18.
- Android `PdfRenderer` needs a file descriptor. On API 26–29, rendering a decrypted PDF without a plaintext file needs a bundled renderer. `memfd` is an option on API 30+. That choice is decided at the design gate. It also affects US-04 and US-11.

## Implementation notes

Not a design spec. Do not implement from this file.
