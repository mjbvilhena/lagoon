# US-20 Restore from Drive

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Restore the vault from Drive.

## User story

**As a** person named Miguel
**I want to** restore a Drive backup into the app
**So that** I can get my items and files back after they are gone from the phone

## Background

- He asked for Restore from Drive. The backup holds the database and stored files.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: merging with items that exist only on the device (Q5, unanswered). Browsing ordinary Drive folders.

## Acceptance criteria

- **Scenario: restore onto an empty vault** — Must
  - Given he is signed in, a backup exists in app data, and the local vault has no items
  - When he restores
  - Then the items, personal notes, tags, and stored images and PDFs from that backup are present on the device and usable offline afterward
- **Scenario: no backup** — Must
  - Given he is signed in and no backup exists
  - When he chooses Restore
  - Then nothing local is wiped and he can see that there was nothing to restore
- **Scenario: not configured or not signed in** — Must
  - Given no client ID, or he is not signed in
  - When he looks for restore
  - Then restore does not run and does not crash. The unconfigured case uses the US-17 explanation.
- **Scenario: restore while local items exist** — Must
  - Given the local vault is not empty
  - When he chooses Restore
  - Then the app does not pick a merge-or-replace outcome that this story invents. The action waits until Q5 is answered, or it asks him in the way Q5's answer requires. Until Q5 is answered, this scenario is an open question, not a guessed wipe.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Restore is a deliberate action with a text confirmation once Q5 says what it will do.
- No placeholder backup date.

## Dependencies and risks

- Blocked by US-18 having produced a backup, and by Q5.
- The open Q5 scenario means this story cannot be marked implementation-Done until Q5 is closed or split into a follow-up story. Do not soft-pass it.

## Implementation notes

Read app-data only. Do not implement a silent replace while Q5 is open. Not a design spec.
