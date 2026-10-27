# US-32 Keep more than one attachment on an item

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Keep more than one attachment on an item.

## User story

**As a** person named Miguel
**I want** one item to hold several files, in a defined order, including a file I add later
**So that** a receipt and a photo of the same thing stay together

## Background

- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: the in-app camera and multi-page scan (US-65, Could).

## Acceptance criteria

- **Scenario: a multi-share** — Must
  - Given `ACTION_SEND_MULTIPLE` lists N files
  - When he shares them to Lagoon
  - Then one item holds N attachments, in that share order
- **Scenario: add from inside the item** — Must
  - Given an item is open
  - When he picks a file with the document picker
  - Then that file is another attachment on the same item
- **Scenario: mixed image and PDF** — Must
  - Given the attachments are an image and a PDF
  - When he opens the item
  - Then both are listed, each with its own type, and the item type shown is the type of the first attachment
- **Scenario: remove one file** — Must
  - Given an attachment has OCR text
  - When he removes that attachment
  - Then the file and its OCR text leave the index, and the other attachments stay
- **Scenario: OCR per attachment** — Must
  - Given several images
  - When US-31 runs
  - Then each image has its own OCR result

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- A single share still uses US-03 or US-04.

## Implementation notes

Not a design spec.
