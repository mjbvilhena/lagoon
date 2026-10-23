# US-17 See that sync is not configured

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

See that sync is not configured.

## User story

**As a** person named Miguel
**I want** Settings to say that sync is not configured when the OAuth client ID is missing
**So that** the app stays usable and does not pretend it can sync

## Background

- There is no Back up now and no Restore. The screen explains that background sync is not configured.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: sign-in (US-18) and the sync worker (US-51).

## Acceptance criteria

- **Scenario: no client ID** — Must
  - Given the build has no OAuth client ID
  - When he opens Settings
  - Then he sees that sync is not configured, and the app does not crash
- **Scenario: the vault still works** — Must
  - Given that build
  - When he saves and opens an item
  - Then the item is stored locally
- **Scenario: no manual sync controls** — Must
  - Given he looks through Settings and the home screen
  - When the screens are shown
  - Then there is no Back up now, no Restore, and no last-backup time
- **Scenario: zip still available** — Must
  - Given sync is not configured
  - When he looks for the zip actions
  - Then export and import remain available (US-21, US-52)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The not-configured sentence is readable by a screen reader. No placeholder sentences.

## Dependencies and risks

- US-30 repeats the absence of those controls as its own check so a later screen cannot add them back.

## Implementation notes

Do not call Drive when the client ID is absent. Not a design spec.
