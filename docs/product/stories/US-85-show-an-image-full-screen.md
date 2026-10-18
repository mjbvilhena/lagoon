# US-85 Show an image full-screen and bright

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Should.

## Title

Show an image full-screen and bright.

## User story

**As a** person named Miguel
**I want** to open an image attachment full-screen at maximum brightness
**So that** I can read a code or a pass from the phone without another app

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: editing the image. A PDF page. The system camera (US-65).

## Acceptance criteria

- **Scenario: full screen** — Should
  - Given an ordinary item has an image attachment
  - When he taps that image once
  - Then the image opens full-screen
  - And the screen brightness is at maximum
  - And when he leaves that view, the brightness returns to what it was
- **Scenario: offline** — Should
  - Given the phone has no network
  - When he taps that image
  - Then the full-screen view still opens
- **Scenario: a sensitive item** — Should
  - Given the image is on a sensitive item and he has not completed the US-37 unlock
  - When he taps that image
  - Then the full-screen view does not open
  - And after the US-37 unlock, the same tap opens it
  - And `FLAG_SECURE` applies for that view, as US-75 says

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The E1 wording for this story is a proposed amendment. It needs approval by mjbvilhena. This story stays Draft.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
