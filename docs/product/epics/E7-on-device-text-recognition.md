# Epic E7: On-device text recognition

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Miguel decided this is in v1. That decision is not approval of this epic or of its stories. Shipped-epic DoD is not claimed.

## Background & Value Proposition

Screenshots, photos, and scanned PDFs are pictures of text. He decided v1 should recognize that text on the device so search can find it. A PDF that already has a text layer is extracted (US-04). That extraction is not OCR.

No accuracy target was given. Do not invent one.

## Target Audience

Miguel, searching something he saved as an image or a scan.

## Scope & Phasing

- **In Scope (MVP):**
  - On-device text recognition for screenshots, images, and scanned PDFs.
  - Recognized text is stored as extracted text and is searchable.
  - Recognition does not upload the file.
  - Text-based PDF extraction stays in US-04 and is not this epic's method.
- **Future Scope (v2+):** OCR for languages other than English. Booking references and merchants, only if they fall out of this work for free. Otherwise they stay proposed and later. No story either way.
- **Out of Scope / Non-Goals:** A cloud vision API. Translating the text. Editing the image or PDF. Treating text-layer extraction as OCR.

## Core Workflows

1. **Read text that is only in the pixels**
   - He has saved a screenshot, an image, or a scanned PDF.
   - The vault recognizes the text on the device.
   - Search finds that text.

## Risks & Dependencies

- Depends on E1 for the file and E3 for search.
- v1 language scope is English, Mikey's Dev Bot's revised recommendation. The Dev Bot reports that Miguel told it in chat that English is enough. Not approval. The bundled Latin-script model still fits. Other languages are later. The sideload ABI note is a design-gate build choice.
- Recognized text is vault data. It is covered by E8 encryption once that epic exists. This epic does not design the cipher.

## Open Questions

- v1 OCR language is English in US-31. Other languages are a later backlog option. The `arm64` filter versus per-ABI APKs choice waits for design. Not approval.

## Draft stories

- US-31 Recognize text on the device
