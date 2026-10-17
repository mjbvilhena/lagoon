# US-11 Read a saved item

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**Epic:** E3. Not an approval.

**MoSCoW:** Must. v1, from approved epic E3.

## Title

Read a saved item.

## User story

**As a** person named Miguel
**I want** to open an item and read what was saved
**So that** I can use it without going back to the app it came from

## Background

- Detail shows the stored fields. It does not invent missing ones.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: what a locked sensitive row shows in the list (US-75). Editing is US-12.

## Acceptance criteria

- **Scenario: open an ordinary item** — Must
  - Given an item that is not marked sensitive
  - When he opens it
  - Then he sees the title, body, note, all attachments, last-saved date, source domain and sending app when present, and the original URL when the item is a link
  - And when the stored preview is the foggy placeholder from US-79, he sees that image and opening the item does not fetch a preview
- **Scenario: OCR text** — Must
  - Given OCR text exists
  - When he opens the item
  - Then that text is shown, and he can select it and copy it
- **Scenario: snapshot and dates** — Must
  - Given a snapshot or a reminder or expiry is stored
  - When he opens the item
  - Then he can read the snapshot, and he sees the reminder or expiry
- **Scenario: a sensitive item** — Must
  - Given the item is marked sensitive
  - When he opens it before US-37 succeeds
  - Then the body, OCR text, attachments, and snapshot are not shown
- **Scenario: optional fields are empty** — Must
  - Given an item has no note and no reminder
  - When he opens it
  - Then those areas are absent, and the title and body that do exist are still shown

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Copy is a named action. No placeholder sentences. The foggy preview image is the US-79 placeholder, not a sentence of filler copy.

## Dependencies and risks

- Unlock is US-37. The original URL opens in another app through US-64 only if that story is in the build. This story's Must is that the URL is visible and can be opened with the platform.

## Implementation notes

Read the local item. Not a design spec.
