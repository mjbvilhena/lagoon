# US-25 Run the tests he named

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E6.

## Title

Cover search, sync and zip packaging, and a basic screen with automated tests.

## User story

**As a** person named Miguel
**I want** automated tests on search, on how the sync payload and the zip are packaged, and on at least one Compose screen
**So that** those parts cannot change silently before I install a build

## Background

- Packaging tests cover ciphertext rules that are testable without a live Drive call.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a live Drive call, and an emulator for this story. Screenshots stay on an emulator in US-27. Kotlin style is in `.github/workflows/qa.yml` and is in scope for the build, not out of scope.

## Acceptance criteria

- **Scenario: repository and search** — Must
  - Given seeded items
  - When the search tests run
  - Then they cover a match, a miss, and the US-50 order
- **Scenario: sync and zip packaging** — Must
  - Given a payload seeded with a sentinel string
  - When the sync payload and the zip are built
  - Then the zip is encrypted with the vault key, so the sentinel cannot be read without that key, and neither payload contains the header `SQLite format 3`
- **Scenario: Compose UI** — Must
  - Given the named screen
  - When the UI test runs on the JVM with Robolectric
  - Then it finds that screen's main control
- **Scenario: failures are failures** — Must
  - Given a test assertion fails
  - When CI runs
  - Then the job fails

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The Compose test uses the real control name. No placeholder sentences.

## Dependencies and risks

- US-15, US-21, and US-36 define the behavior these tests lock.

## Implementation notes

Local JVM tests plus Robolectric. The `Kotlin style` job in `qa.yml` runs `ktlintCheck` or `detekt` once Gradle exists. Not a design spec.
