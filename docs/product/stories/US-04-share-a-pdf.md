# US-04 Share a PDF into the vault

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1. Text-layer extraction is not OCR.

## Title

Share a PDF into the vault.

## User story

**As a** person named Miguel
**I want** to share a PDF into the vault
**So that** the file and the text it already contains are searchable later

## Background

- The file is part of the vault that background sync and the zip carry. It is not a separate backup.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: a PDF editor, cloud OCR, and asking him for a PDF password. v1 stores no PDF passwords.
- Proposed amendment. Needs approval by mjbvilhena. A password-protected PDF means only a PDF that needs a password to open. A PDF restricted only by an owner password is stored, its text is extracted, and it is searchable as a normal PDF. It is not marked "text not extracted" because of that owner password. This is not approved.

## Acceptance criteria

- **Scenario: share a PDF** — Must
  - Given a readable PDF share
  - When he chooses Lagoon
  - Then one item stores a copy of the file and he sees "Saved to Lagoon". The sending app is recorded only when the platform reports it
- **Scenario: text the PDF already contains** — Must
  - Given the PDF has a text layer and the device is API 26 or newer
  - When extraction runs with a library that works on `minSdk` 26, such as PdfBox-Android
  - Then that text is stored as extracted text, and those pages are not sent through OCR
- **Scenario: a locked PDF** — Must
  - Given the PDF is password-protected
  - When it is stored
  - Then v1 stores no PDF password, the file is still stored and openable, and the item is marked "text not extracted"
- **Scenario: an owner password only** — Proposed amendment. Needs approval by mjbvilhena.
  - Given the PDF does not need a password to open, and it is restricted only by an owner password
  - When it is stored
  - Then it is stored, rendered, and searchable as a normal PDF
  - And a text layer is extracted as in "text the PDF already contains"
  - And no password is asked for or stored
- **Scenario: mixed pages** — Must
  - Given some pages have a text layer and some do not
  - When extraction finishes
  - Then text-layer pages are extracted, and pages with no text layer are queued for on-device OCR (US-31)
- **Scenario: not a PDF** — Must
  - Given the bytes are not a PDF
  - When the vault receives them
  - Then no item is stored and he sees that nothing was saved

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- "text not extracted" is visible on the item.
- No placeholder sentences.

## Dependencies and risks

- OCR of scanned pages is US-31. The pipeline order is US-58.

## Implementation notes

Not a design spec. The library name is an example that satisfies `minSdk` 26, not an ADR.
