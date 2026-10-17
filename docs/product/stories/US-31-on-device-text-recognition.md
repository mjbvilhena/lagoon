# US-31 Recognize text on the device

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> He decided OCR is in v1 for screenshots, images, and scanned PDFs. That decision is not approval of this story. Booking references are not a story.

## Title

Recognize text in a screenshot, image, or scanned PDF.

## User story

**As a** person named Miguel
**I want** the vault to read text from a screenshot, a photo, or a scanned PDF on the phone
**So that** I can search a page that was only a picture

## Background

- His v1 decision: on-device text recognition for screenshots, images, and scanned PDFs, so their text is searchable.
- A text-based PDF is extracted by US-04. That is not OCR and is not optional.
- Bots said picking out booking references and merchants stays proposed and later, unless it falls out of this work for free. This story does not require that extraction.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a cloud recognizer, translation, and a guarantee about accuracy.

## Acceptance criteria

- **Scenario: an image or screenshot** — Must
  - Given a saved image or screenshot that shows text
  - When recognition runs on the device
  - Then the recognized text is stored as extracted text, search can find it offline, and the image file was not uploaded for recognition
- **Scenario: a scanned PDF** — Must
  - Given a PDF with no text layer
  - When recognition runs
  - Then its recognized text is searchable the same way
- **Scenario: a text-based PDF is not sent through OCR** — Must
  - Given a PDF whose text US-04 already extracted
  - When the item is stored
  - Then that extracted text is searchable without OCR
- **Scenario: nothing to read** — Must
  - Given an image with no text
  - When recognition finishes
  - Then the item remains, and no invented paragraph is stored
- **Scenario: merchants and bookings** — Must
  - Given recognized text happens to contain a booking code
  - When he looks at the item
  - Then the vault is not required to label a merchant or a booking reference. Doing so for free is allowed. A dedicated extractor is out of this story

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Recognized text is real text, not a placeholder.
- He can tell OCR text from text he typed. The UX artefact chooses how. This story does not draw it.

## Dependencies and risks

- Languages are open and story-level. Do not pick them here.
- Depends on US-15 for search. Encryption of the extracted text follows E8.

## Implementation notes

On-device recognition only. Not a design spec. Not an ADR for the recognizer.
