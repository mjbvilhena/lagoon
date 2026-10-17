# US-26 Follow a README to build, sideload, and configure OAuth

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Follow written steps to build, sideload, and create the OAuth client.

## User story

**As a** person named Miguel
**I want** a README that tells me how to build the app, install the APK, and create the Google OAuth client
**So that** I can do those steps myself, including where the client ID goes

## Background

- He asked for a README covering the build, installing on a phone (allow unknown sources), and Google Drive setup. The OAuth section must be exact steps: package name, SHA-1 of the signing key that was used, and where to put the client ID.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: creating the Cloud project for him. Putting a real client ID in the repo.

## Acceptance criteria

- **Scenario: build and sideload steps exist** — Must
  - Given the README in the repo root once the app exists
  - When he follows it on a machine with the Android SDK
  - Then it tells him the Gradle command that produces the signed release APK and how to allow installs from unknown sources and install that APK
- **Scenario: OAuth steps exist** — Must
  - Given that README
  - When he follows the Drive section
  - Then it names the application id `com.mjbvilhena.folio`, the SHA-1 of the release signing certificate used for that APK, the Google Cloud steps to create an Android OAuth client with that package name and SHA-1, the `drive.appdata` scope the app requests, and the file or property where he puts the client ID at build time
- **Scenario: missing client ID is described** — Must
  - Given he has not set the client ID
  - When he reads the README
  - Then it says the app still builds, installs, and works offline, and that Settings will explain that sync is not configured. It does not document a Back up now or a Restore button
- **Scenario: no secret in the README** — Must
  - Given the README and the repo
  - When they are read
  - Then they do not contain a keystore password, a private key, or a real client secret

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The README is prose, not a screenshot substitute. Screenshots are US-27.

## Dependencies and risks

- Q2 is a delegated recommendation, not approval: the application id in those steps is `com.mjbvilhena.folio`. The SHA-1 still comes from US-24's keystore and must not be invented before that keystore exists.
- The current root `README.md` points at these drafts and says there is no app yet. Replacing it with build steps happens when this story is implemented, not in this drafting pass.

## Implementation notes

Document the build-time config key in the same place the Gradle build reads it. Not a design spec.
