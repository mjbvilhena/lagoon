# US-03 Share an image into the vault

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Share an image into the vault.

## User story

**As a** person named Miguel
**I want** to share an image into the vault
**So that** the picture and any words I attached are kept on the phone

## Background

- The bytes have to be copied during the share, because the sending app may delete its copy.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: the in-app camera (US-65), image editing, and cloud vision. On API 26 or 27, HEIC or HEIF bytes are kept and the item has no preview.

## Acceptance criteria

- **Scenario: share one image** — Must
  - Given a share of one `image/jpeg`, `image/png`, `image/webp`, `image/gif`, `image/heic`, or `image/heif`
  - When he chooses Lagoon
  - Then the vault copies the bytes into its own storage, stores one item, and shows "Saved to Lagoon". The sending app is recorded only when the platform reports it
- **Scenario: several images** — Must
  - Given `ACTION_SEND_MULTIPLE` shares N of those images
  - When he chooses Lagoon
  - Then one item holds N attachments, in the order the share listed them
- **Scenario: HEIC on API 26 or 27** — Must
  - Given the device is API 26 or 27 and the image is HEIC or HEIF
  - When the item is stored
  - Then the bytes are kept, the item has no preview, and he can still open the item
- **Scenario: words with the image** — Must
  - Given the share includes optional words
  - When the item is stored
  - Then those words are the note
- **Scenario: the file cannot be read** — Must
  - Given the shared bytes cannot be read
  - When the vault receives them
  - Then no item is stored and he sees that nothing was saved
- **Scenario: OCR follows the save** — Must
  - Given the image was stored
  - When the save confirmation is already visible
  - Then US-31 may start. The item is visible before OCR finishes

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No preview is an observable state, not an empty unlabeled box.
- No placeholder sentences.

## Dependencies and risks

- More than one attachment, including a later add, is US-32.
- OCR failure does not remove the item (US-31).

## Implementation notes

Copy the stream before returning from the share. Not a design spec.
