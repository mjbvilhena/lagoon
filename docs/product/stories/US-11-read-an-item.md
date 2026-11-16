# US-11 Read a saved item

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

Read a saved item.

## User story

**As a** person named Miguel
**I want** to open an item and read what was saved
**So that** I can use it without going back to the app it came from

## Background

- Detail shows the stored fields. It does not invent missing ones.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: what a locked sensitive row shows in the list (US-75). Editing is US-12.

## Acceptance criteria

- **Scenario: open an ordinary item** — Must
  - Given an item that is not marked sensitive
  - When he opens it
  - Then he sees the title, body, note, tags, all attachments, last-saved date, and the original URL when the item is a link
  - And when the item is a link, he can open that original URL with the platform
  - And when the stored preview is the foggy placeholder from US-79, he sees that image and opening the item does not fetch a preview
- **Scenario: source on the detail** — Should
  - Amendment approved by mjbvilhena on 2026-11-16.
  - Given an item that is not marked sensitive has a source domain or a sending app
  - When he opens it
  - Then he sees the source domain and sending app when present
- **Scenario: OCR text** — Must
  - Given OCR text exists
  - When he opens the item
  - Then that text is shown, and he can select it and copy it
- **Scenario: a snapshot** — Must
  - Given a snapshot is stored
  - When he opens the item
  - Then he can read the snapshot
- **Scenario: a reminder or expiry** — Must
  - Given a reminder or an expiry is stored
  - When he opens the item
  - Then he sees that reminder or expiry
- **Scenario: a sensitive item** — Must
  - Security criteria approved by mjbvilhena on 2026-11-16.
  - Given the item is marked sensitive
  - When he opens it before US-37 unlock succeeds
  - Then, before US-37 unlock succeeds, a sensitive item's detail screen shows none of its title, body, personal note, URL, source, tags, OCR text, attachments or snapshot. This matches US-75's "details appear only after US-37". List-row behaviour stays with US-75
- **Scenario: optional fields are empty** — Must
  - Given an ordinary item has no note and no reminder
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
