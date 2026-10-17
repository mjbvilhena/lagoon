# US-31 Recognize text on the device

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> He decided OCR is in v1. That decision is not approval of this story.
> Language scope is Mikey's Dev Bot's revised recommendation under the 2026-10-17 delegation. The Dev Bot reports that Miguel told it in chat that English is enough for v1 OCR. That report is not his approval of this story. Booking references are not a story.

## Title

Recognize text in a screenshot, image, or scanned PDF.

## User story

**As a** person named Miguel
**I want** the vault to read text from a screenshot, a photo, or a scanned PDF on the phone
**So that** I can search a page that was only a picture

## Background

- His v1 decision: on-device text recognition for screenshots, images, and scanned PDFs, so their text is searchable.
- A text-based PDF is extracted by US-04. That is not OCR and is not optional.
- v1 OCR scope is English. The bundled ML Kit on-device Latin-script model still fits for implementation, and it works offline from install. Acceptance tests use English samples only. Other languages are a later backlog option, not this story.
- Bots said picking out booking references and merchants stays proposed and later, unless it falls out of this work for free. This story does not require that extraction.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a cloud recognizer, a downloadable language pack, any language other than English in v1, translation, and a guarantee about accuracy. Accent folding in search stays in US-15.

## Acceptance criteria

- **Scenario: an image or screenshot** — Must
  - Given a saved image or screenshot that shows English text
  - When recognition runs on the device, with no network
  - Then the recognized text is stored as OCR text, search can find it offline, and the image file was not uploaded for recognition
- **Scenario: a scanned PDF** — Must
  - Given a PDF with no text layer and English text on the page
  - When recognition runs
  - Then its recognized text is searchable the same way
- **Scenario: English samples** — Must
  - Given English samples
  - When the acceptance tests run
  - Then they produce searchable text from the bundled model, with no language download, and the tests do not require another language
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

- Languages other than English are a later backlog option, not this story. US-15 still folds accents in search. That search rule is unchanged.
- Depends on US-15 for search. OCR text uses the OCR weight in that ranking. Encryption of the extracted text follows E8.
- Build note for the design gate: sideloaded APKs do not get per-device splits, so the package uses `arm64` `abiFilters` or per-ABI APKs. This story does not choose which of those two the design adopts.

## Implementation notes

On-device recognition only, model bundled in the install. Not a design spec. Not an ADR for the recognizer.
