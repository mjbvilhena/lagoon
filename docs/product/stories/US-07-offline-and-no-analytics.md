# US-07 Use the vault offline, with no analytics

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Use the saved vault with no network and no analytics.

## User story

**As a** person named Miguel
**I want to** open, read, and add notes with no network, and I want the app to keep no analytics
**So that** the vault is actually on my phone and does not report how I use it

## Background

- He said the primary store is on-device, the app works fully offline, and there are no analytics.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: making Drive backup work offline. Backup needs a network and is E4. Link enrichment needs a network and is E2.

## Acceptance criteria

- **Scenario: airplane mode after items exist** — Must
  - Given he has already saved at least one note and one image
  - When the device has no network and he opens the app
  - Then he can read those items, including the image bytes, and he can add another note
- **Scenario: a fresh install with no network and no OAuth client ID** — Must
  - Given a build with no OAuth client ID and no network
  - When he installs and opens the app
  - Then the app opens, he can add a note, and it does not crash on the missing client ID
- **Scenario: analytics** — Must
  - Given the app build
  - When its dependency list and outbound behavior are reviewed
  - Then it does not include an analytics or usage-telemetry SDK and it does not send usage events

## Analytics and observability (optional)

The brief forbids analytics. This story's Must criterion is the absence of that, not a list of events.

## Design and a11y notes

- Offline is not an error banner that blocks the library.
- No placeholder text in the shipped UI.

## Dependencies and risks

- US-05 and US-03 (or any saved image) provide the fixtures. This story is still the one that requires offline use.
- "No analytics" includes not adding a crash-reporting product. He said no analytics. Crash reporting was not requested and is not added here.

## Implementation notes

Room (or the on-device store chosen in an approved design) is the primary store. Do not add Firebase Analytics or an equivalent. Not a design spec.
