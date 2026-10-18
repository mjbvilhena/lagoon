# US-31 Recognize English text on the device

> **Approval status: Draft. Not approved.**

**Epic:** E7.

**MoSCoW:** Must. v1. English only.

## Title

Recognize English text on the device.

## User story

**As a** person named Miguel
**I want** text in a screenshot, image, or scanned PDF page to be recognized on the phone
**So that** I can search that text without sending the image to a cloud OCR service

## Background

- v1 OCR is English only. Other languages are out of scope.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: merchants, booking references, and cloud OCR. The page cap below makes the job testable.

## Acceptance criteria

- **Scenario: the item is visible first** — Must
  - Given he saved an image or a PDF
  - When the save confirmation is already showing
  - Then a WorkManager job may run OCR, and the item is already in the list
- **Scenario: each attachment and each scanned page** — Must
  - Given an item has images and PDF pages with no text layer
  - When OCR runs
  - Then each attachment is processed, and each such page is processed, up to 50 pages and 20 MB of image bytes. The rest is marked not recognized
- **Scenario: English** — Must
  - Given an English sample
  - When OCR finishes
  - Then the text is stored in the OCR column, and he can view it and copy it
- **Scenario: a text-based PDF** — Must
  - Given US-04 already extracted a text layer
  - When OCR runs
  - Then those pages are not recognized again
- **Scenario: pending, failed, and retry** — Must
  - Given the job has not finished, or it failed, including after an out-of-memory kill
  - When he opens the item
  - Then he sees pending or failed, the item is still there, and he can retry
- **Scenario: nothing to read** — Must
  - Given the image has no text
  - When OCR finishes
  - Then the item remains and no text is invented
- **Scenario: the bundled model** — Must
  - Given the release dependencies
  - When they are inspected
  - Then `com.google.mlkit:text-recognition` is present and `com.google.android.gms:play-services-mlkit-text-recognition` is absent

## Analytics and observability (optional)

The image and the recognized text stay on the device. ML Kit's own usage and diagnostic metrics are the disclosure in US-07. This story does not add an analytics library.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Non-English OCR is out of scope. The pipeline order is US-58. ABI packaging is US-24.
- Before the first OCR use he accepts the US-07 disclosure. This story does not choose that disclosure.

## Implementation notes

On-device Latin model from `com.google.mlkit:text-recognition`, bundled in the install. The Play services text-recognition artifact is absent because that thin variant collects a device identifier. Source: `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`, which cites the Android data-disclosure page: thin features collect a device identifier, and `com.google.android.gms:play-services-mlkit-text-recognition` is the thin artifact. Not a design spec.
