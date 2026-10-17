# US-30 Do not offer backup or restore controls

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1.

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
