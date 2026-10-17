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

- He asked for unit tests for the repository/search layer and the backup-packaging layer, and at least a basic Compose UI test. The Gradle build must be clean.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a full screenshot test suite (US-27 is capture for a human, not this story). A Kotlin CI workflow, which the repository-setup mapping did not include.

## Acceptance criteria

- **Scenario: repository and search** — Must
  - Given the repository tests
  - When they run
  - Then they cover saving an item and a full-text query that matches title, body, extracted text, tags, and the personal note, including the title-before-body order from US-15
- **Scenario: backup packaging** — Must
  - Given the packaging tests, with no live Google account
  - When they run
  - Then they cover building a backup payload of the database plus an image or PDF file, and reading that payload back, for both the Drive backup assembly and the zip
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
