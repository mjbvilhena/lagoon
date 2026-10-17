# US-30 No manual backup or restore

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> This story used to be a size warning on Back up now. That warning is removed with the button. His decision forbids the manual flow. Not approval.

## Title

Do not offer backup or restore controls.

## User story

**As a** person named Miguel
**I want** the app to have no backup button, no restore button, and no last-backup time
**So that** keeping a copy stays in the background

## Background

- He said there should be no explicit backup and restore.
- The bots' size warning and roaming warning were tied to Back up now. They go away with that action.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: hiding sync itself. Sign-in and the unmetered preference remain (US-18, US-19). Zip remains (US-21).

## Acceptance criteria

- **Scenario: settings** — Must
  - Given any build, configured or not
  - When he opens Settings
  - Then he does not see Back up now, Restore, a last-backup time, a backup-size warning, or a roaming warning for a manual backup
- **Scenario: he never confirms an upload** — Must
  - Given he is signed in and a network is allowed
  - When sync runs
  - Then it does not stop to ask him to confirm a large upload
- **Scenario: the vault is still usable** — Must
  - Given those controls are absent
  - When he saves and searches
  - Then those actions do not depend on a backup button

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Do not leave a disabled backup button as a placeholder.
- Sync being unconfigured is US-17, in words, not a dead control.

## Dependencies and risks

- US-18 and US-19 own the sync that remains.

## Implementation notes

Do not add a backup screen and hide it. Not a design spec.
