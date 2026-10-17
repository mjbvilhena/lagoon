# US-04 Share a PDF into the vault

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Share a PDF into the vault.

## User story

**As a** person named Miguel
**I want to** share a PDF into the vault
**So that** a document I care about stays with the rest of what I have saved

## Background

- PDFs are a named share type. The file is part of the vault that background sync and the zip carry. It is not a separate backup.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a PDF editor, annotating the file, and using OCR on a PDF that already has a text layer. Scanned pages are US-31, which is in v1 by Miguel's decision. That decision is not approval of this story.

## Acceptance criteria

- **Scenario: share a PDF** — Must
  - Given a PDF he can share from another app
  - When he shares it to the vault
  - Then one item of type `PDF` is stored, the file is kept in the app's private storage, a created date is set, and he can open the item later and read the stored PDF without a network
- **Scenario: the file is not a readable PDF** — Must
  - Given the share is labeled as a PDF but the vault cannot read it as one
  - When he completes the share
  - Then no broken item is left in the list and he can see that it was not saved
- **Scenario: text the PDF already contains** — Must
  - Given the PDF contains extractable text
  - When it is stored
  - Then that text is kept as extracted text so search (US-15) can see it. This is extraction, not OCR
  - And a PDF with no extractable text is still stored, for US-31 to recognize later

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The PDF item has a title that is not placeholder copy. If the file has no title, the file name is enough for this story.
- No placeholder text in the shipped UI.

## Dependencies and risks

- Extracted text is an input to US-15. This story does not implement search.
- Scanned pages are stored here and recognized in US-31. This story does not OCR them.
- Q9 consensus, not approval: one tap saves the PDF. Last-used tag chips are US-28. An empty title uses the file name (US-40).

## Implementation notes

Share intent for `application/pdf`. Not a design spec.
