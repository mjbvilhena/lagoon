# US-24 Install a signed release APK without the Play Store

> **Approval status: Draft. Not approved.**

**Epic:** E6.

**MoSCoW:** Must. v1.

## Title

Install a signed release APK without the Play Store.

## User story

**As a** person named Miguel
**I want** a signed release APK I can sideload
**So that** I can install Lagoon without a store listing

## Background

- The approved specification sets `minSdk` 26 and `compileSdk` and `targetSdk` 37. The application id is `com.mjbvilhena.lagoon`.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: Play Store listing, and what to do about 2027 sideload developer verification. That question is in `docs/product/stories/REVIEW-PACK.md`.

## Acceptance criteria

- **Scenario: the build config** — Must
  - Given the Gradle build
  - When its SDK fields are read
  - Then `minSdk` is 26, and `compileSdk` and `targetSdk` are 37
- **Scenario: ABI** — Must
  - Given the release build
  - When the packaged ABIs are inspected
  - Then it uses the `arm64` filter from US-31, or it produces per-ABI APKs
- **Scenario: signature** — Must
  - Given the release APK
  - When `apksigner verify` runs
  - Then the APK verifies with v2 or v3
- **Scenario: secrets stay out of git** — Must
  - Given the repository
  - When the tree is inspected
  - Then the keystore, its passwords, and OAuth secrets are not committed
- **Scenario: sideload** — Must
  - Given that APK
  - When he installs it on a device
  - Then Lagoon opens

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a UI story. No placeholder sentences in the app that this APK contains.

## Dependencies and risks

- Signing properties are local and gitignored.

## Implementation notes

Test approach: `apksigner verify` and the Gradle field check run in CI. Not a design spec.
