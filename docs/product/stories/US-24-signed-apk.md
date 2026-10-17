# US-24 Install a signed release APK

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Install a signed release APK without the Play Store.

## User story

**As a** person named Miguel
**I want** a release APK I can sideload, signed with a release keystore that is not in the repo
**So that** I can put the vault on my phone without publishing it

## Background

- He asked for a working, installable APK, signed with a self-generated release keystore, secrets not committed, `minSdk` 26, latest stable target SDK, built with Gradle from the command line.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: Play Store signing, Play App Signing, and a debug build as the thing he installs.

## Acceptance criteria

- **Scenario: command-line release build** — Must
  - Given a checkout with no keystore committed and with the documented local signing config present on that machine
  - When the release Gradle task is run from the command line
  - Then it produces a release APK signed with that release keystore
- **Scenario: SDK levels** — Must
  - Given the built manifest
  - When it is inspected
  - Then `minSdk` is 26 and `targetSdk` is the latest stable Android SDK recorded for that build (Q8)
- **Scenario: secrets stay out of git** — Must
  - Given the keystore file and its passwords
  - When the repo is checked
  - Then those files are not tracked, and `.gitignore` ignores keystore files
- **Scenario: sideload** — Must
  - Given the signed APK and a phone that allows installs from unknown sources
  - When he installs it
  - Then the app installs and opens without a Play Store account

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- None beyond the app that opens. Install UI is the Android system installer.

## Dependencies and risks

- The SHA-1 of this cert is an output US-26 must document. It does not exist at draft time. Do not invent a fingerprint in the docs.
- Q8 must be answered with the actual latest stable level at implementation, not a guess in this story.
- Implementation of this story is forbidden until the product spec, epics, stories, and technical design/ADRs are approved. Drafting this story is not that approval.

## Implementation notes

Release signing config reads local, gitignored properties. Not a design spec.
