# US-03 Share an image into the vault

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Share an image into the vault.

## User story

**As a** person named Miguel
**I want to** share an image into the vault
**So that** a picture I care about is stored on the device with my other items

## Background

- Images are one of the four share types he named. Stored image files are part of sync and the zip. OCR of the image is US-31, in v1, and is not this story.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: performing OCR in this story (US-31 does that), cropping, and drawing on the image.

## Acceptance criteria

- **Scenario: share an image** — Must
  - Given an image file he can share from another app
  - When he shares it to the vault
  - Then one item of type `image` is stored, the image bytes are kept in the app's private storage, a created date is set, and he can open the item later and see that image without a network
- **Scenario: the image cannot be read** — Must
  - Given the share refers to an image the vault cannot read
  - When he completes the share
  - Then no broken item is left in the list and he can see that the image was not saved
- **Scenario: optional words** — Must
  - Given he shares only an image
  - When it is stored
  - Then the personal note and tags may be empty, and the source URL may be empty

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The image in the item needs a text alternative that is the item title, not a lorem string.
- No placeholder text in the shipped UI.

## Dependencies and risks

- Q9 consensus, not approval: one tap saves the image. Tag chips are US-28. The file name fills an empty title (US-40).
- The stored file is in scope for sync (US-18) and the zip (US-21). OCR is US-31.
- No size limit was requested. A very large image is a risk, not a silent rejection rule.

## Implementation notes

Share intent for image MIME types. File bytes stay in app-private storage. Not a design spec.
