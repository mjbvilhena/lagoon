# US-17 See that Drive backup is not configured

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

See that Drive backup is not configured.

## User story

**As a** person named Miguel
**I want** Settings to explain that backup is not configured when the OAuth client ID is missing
**So that** I can still use the vault, and the app does not crash, before I create a Google Cloud client

## Background

- He said the client ID is build-time config. Without it, the app must build, install, and work offline. The backup screen explains that backup is not configured rather than crashing.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: creating the Google Cloud client (US-26 documents the steps). Performing a real Drive upload (US-18).

## Acceptance criteria

- **Scenario: no client ID** — Must
  - Given a build with no OAuth client ID configured
  - When he opens Settings and the backup section
  - Then he can read that Drive backup is not configured yet, and the app does not crash
- **Scenario: the vault still works** — Must
  - Given that same build
  - When he leaves Settings
  - Then he can add a note and search, including with no network
- **Scenario: sign-in is not offered as if it would work** — Must
  - Given no client ID
  - When he looks at the backup actions
  - Then Back up now, automatic backup, and Restore from Drive are not presented as working actions. The explanation is the primary content.
- **Scenario: zip still available** — Must
  - Given no client ID
  - When he looks for the local export
  - Then zip export is still available (US-21). Drive being unconfigured does not remove it.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The explanation is ordinary prose he can read with a screen reader. No placeholder text.
- Exact sentences belong to the UX artefact. This story requires the meaning: backup is not configured yet.

## Dependencies and risks

- The client ID's file or Gradle property name is technical design and the README (US-26). This story only requires that "unset" is detectable and safe.
- Do not ship a real client ID in the repo to make this screen disappear.

## Implementation notes

Guard Drive calls on the presence of the build-time client ID. Not a design spec.
