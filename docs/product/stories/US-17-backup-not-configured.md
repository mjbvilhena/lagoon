# US-17 See that sync is not configured

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Reworked after Miguel's decision that there is no explicit backup. That decision is not approval.

## Title

See that sync is not configured.

## User story

**As a** person named Miguel
**I want** Settings to explain that sync is not configured when the OAuth client ID is missing
**So that** I can still use the vault, and the app does not crash, before I create a Google Cloud client

## Background

- The client ID is build-time config. Without it the app must build, install, and work offline.
- There is no Back up now and no Restore to disable. The screen explains that background sync is not configured.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: creating the Cloud client (US-26). Performing sync (US-18).

## Acceptance criteria

- **Scenario: no client ID** — Must
  - Given a build with no OAuth client ID
  - When he opens Settings
  - Then he can read that sync is not configured yet, and the app does not crash
- **Scenario: the vault still works** — Must
  - Given that same build, including with no network
  - When he leaves Settings
  - Then he can add a note and search
- **Scenario: no backup controls** — Must
  - Given no client ID
  - When he looks through Settings
  - Then there is no Back up now, no Restore, and no last-backup time
- **Scenario: zip still available** — Must
  - Given no client ID
  - When he looks for the local export
  - Then zip export is still available (US-21)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The explanation is ordinary prose a screen reader can read. No placeholder text.
- Exact sentences belong to the UX artefact. This story requires the meaning: sync is not configured yet.

## Dependencies and risks

- Do not ship a real client ID in the repo to hide this state.

## Implementation notes

Do not call Drive when the client ID is absent. Not a design spec.
