# US-18 Sign in and let the vault sync

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Replaces Back up now. His decision: seamless sync, no explicit backup. Not approval.

## Title

Sign in and let the vault sync in the background.

## User story

**As a** person named Miguel
**I want to** sign in with Google and have the vault sync itself to the app's private Drive space
**So that** a copy exists off the phone without me running a backup

## Background

- He rejected an explicit backup. Sync uses Google Sign-In / Credential Manager and the Drive API `appDataFolder`. Not Android Auto Backup.
- The copy includes the database and attachments. Those bytes are ciphertext (US-36).
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a Back up now button, a last-backup clock, restore (US-20 is the other device, not a restore button), zip (US-21), the unmetered preference (US-19).

## Acceptance criteria

- **Scenario: sync after sign-in** — Must
  - Given a build whose OAuth client ID is set, he is signed in, the device may sync (US-19), and the vault has a note plus an attachment
  - When the app is running and the network is allowed
  - Then the vault is uploaded to Drive `appDataFolder` without him choosing Back up now
- **Scenario: not signed in** — Must
  - Given a configured client ID and he is not signed in
  - When he opens Settings
  - Then sync does not pretend to have run, and he can start Google sign-in
- **Scenario: sign-in fails or he cancels** — Must
  - Given he cancels sign-in or Google returns an error
  - When control returns to the app
  - Then his local items are unchanged
- **Scenario: the copy is app-data** — Must
  - Given a successful sync
  - When its Drive location is checked
  - Then it is in `appDataFolder`, not in a folder of ordinary My Drive, and it is not an Android Auto Backup set
- **Scenario: hidden in Drive** — Must
  - Given that copy
  - When he browses Drive in the Drive app
  - Then the vault files are not listed as normal Drive files

## Analytics and observability (optional)

Not applicable. No analytics. Sign-in is authentication he asked for, not product analytics.

## Design and a11y notes

- Sign-in is visible as text. There is no backup button and no last-backup timestamp.
- No placeholder account name.

## Dependencies and risks

- Application id `com.mjbvilhena.folio` is the bots' consensus, not his spec approval. The release SHA-1 must not be invented.
- Which of Credential Manager and the older Google Sign-In API performs sign-in is an ADR. He named both. This story does not pick.
- Ciphertext is US-36. This story does not choose an algorithm.

## Implementation notes

Do not log tokens. Do not commit the client ID. Not a design spec.
