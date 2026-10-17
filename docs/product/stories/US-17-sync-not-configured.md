# US-17 See that sync is not configured

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> The file was renamed from `US-17-backup-not-configured.md` so the name matches seamless sync.

**MoSCoW:** Must. v1, from approved epic E4.

## Title

See that sync is not configured.

## User story

**As a** person named Miguel
**I want** Settings to say that sync is not configured when the OAuth client ID is missing
**So that** the app stays usable and does not pretend it can sync

## Background

- There is no Back up now and no Restore. The screen explains that background sync is not configured.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
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
