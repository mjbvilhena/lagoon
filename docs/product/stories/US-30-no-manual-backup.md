# US-30 Do not offer backup or restore controls

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> The file was renamed from `US-30-backup-size-warning.md`. Kept as its own story so the absence stays testable. US-17 states the same absence on the not-configured screen.

**MoSCoW:** Must. v1, from approved epic E4.

## Title

Do not offer backup or restore controls.

## User story

**As a** person named Miguel
**I want** the app to have no backup or restore controls
**So that** sync stays in the background and I am not asked to run a backup

## Background

- The approved specification forbids Back up now, Restore, a last-backup time, a manual size warning, and a roaming warning tied to a manual backup.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the zip fallback (US-21, US-52), which is not a backup button.

## Acceptance criteria

- **Scenario: settings** — Must
  - Given any sync state, including not configured and signed in
  - When he opens Settings
  - Then he does not see Back up now, Restore, a last-backup time, a backup-size warning, or a roaming warning for a manual backup
- **Scenario: he never confirms an upload** — Must
  - Given he is signed in and the network policy allows sync
  - When the worker runs
  - Then the upload starts without a confirmation button
- **Scenario: the vault is still usable** — Must
  - Given those controls are absent
  - When he saves an item
  - Then the save succeeds

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Do not add the buttons and hide them. No placeholder sentences.

## Dependencies and risks

- US-51 shows sync errors in plain words. Those errors are not a backup log.

## Implementation notes

Not a design spec.
