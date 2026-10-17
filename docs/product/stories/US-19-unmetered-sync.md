# US-19 Prefer an unmetered network for background sync

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> The file was renamed from `US-19-automatic-backup.md`.

**Epic:** E4. Not an approval.

**MoSCoW:** Must. v1, from approved epic E4.

## Title

Prefer an unmetered network for background sync.

## User story

**As a** person named Miguel
**I want** background sync to wait for an unmetered network unless I turn that preference off
**So that** sync does not use mobile data by default

## Background

- The specification sets the preference to Unmetered only, default on, via WorkManager `UNMETERED`. There is no roaming warning, because that warning belonged to Back up now.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a manual "sync now" action.

## Acceptance criteria

- **Scenario: default** — Must
  - Given a fresh install
  - When he has not changed the preference
  - Then unmetered-only is on
- **Scenario: unmetered only** — Must
  - Given the preference is on
  - When the network is metered, including a metered roaming network
  - Then the sync worker does not run
  - And when the network is unmetered, the worker may run
- **Scenario: preference off** — Must
  - Given he turns unmetered-only off
  - When the network is metered or roaming
  - Then the sync worker may run, and the app shows no roaming warning
- **Scenario: offline** — Must
  - Given there is no network
  - When the worker is due
  - Then it does not crash and it does not mark local items deleted

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The preference has a visible on and off state. No placeholder sentences.

## Dependencies and risks

- The topic queue does not follow this preference. It has its own "Unmetered only" setting (US-44).

## Implementation notes

WorkManager network constraint. Not a design spec.
