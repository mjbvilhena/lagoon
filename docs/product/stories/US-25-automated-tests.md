# US-25 Run the tests he named

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Cover search, backup packaging, and a basic screen with automated tests.

## User story

**As a** person named Miguel
**I want** automated tests on search, on how a backup is packaged, and on at least one Compose screen
**So that** those parts cannot change silently before I install a build

## Background

- He asked for unit tests for the repository/search layer and the backup-packaging layer, and at least a basic Compose UI test. Packaging now means the sync payload and the zip, not a manual backup. The Gradle build must be clean.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a full screenshot test suite (US-27 is capture for a human, not this story). A Kotlin CI workflow, which the repository-setup mapping did not include.

## Acceptance criteria

- **Scenario: repository and search** — Must
  - Given the repository tests
  - When they run
  - Then they cover saving an item and a full-text query that matches title, body, extracted text, tags, the personal note, and OCR text, including the US-15 weights, the exact-phrase rule, and the deterministic tie-break
- **Scenario: sync and zip packaging** — Must
  - Given the packaging tests, with no live Google account
  - When they run
  - Then they cover assembling the vault (database plus attachments) and reading it back, for both the Drive sync payload and the zip
  - And the Drive payload they assemble is ciphertext, not a plaintext database (US-36). The test does not need a live Drive account and does not choose a cipher beyond "not plaintext"
- **Scenario: Compose UI** — Must
  - Given the UI tests
  - When they run
  - Then at least one test opens a Compose screen and checks a state he can see, such as the empty home list
- **Scenario: failures are failures** — Must
  - Given any of those tests fails
  - When the Gradle test task finishes
  - Then the task exits non-zero

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The Compose test uses real labels from the UX artefact once that artefact exists. It must not assert on lorem.

## Dependencies and risks

- Blocked by the implementation of US-15, US-18/US-21 packaging, and US-10's empty state, which themselves wait on approvals.
- Live Drive is not required for the packaging unit test. US-18 still needs a real Drive check later. This story does not replace that.

## Implementation notes

Local JVM tests for repository and packaging. One `androidTest` or Compose test for UI. Not a design spec.
