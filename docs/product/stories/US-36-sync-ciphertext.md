# US-36 Sync only ciphertext

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> End-to-end encryption of the Drive copy is his Q10 decision. Not approval. Not an ADR.

## Title

Send only ciphertext to Drive.

## User story

**As a** person named Miguel
**I want** everything synced to Google Drive to be encrypted on the phone first
**So that** Drive only ever holds ciphertext

## Background

- He decided client-side end-to-end encryption of everything synced. Drive holds ciphertext.
- How keys reach a second device is his open question. This story does not derive a key from the Google account and does not invent a passphrase.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: the algorithm. Android Auto Backup. A Drive file he can open in Docs.

## Acceptance criteria

- **Scenario: the upload is ciphertext** — Must
  - Given a signed-in vault with a note and an attachment
  - When background sync uploads to `appDataFolder`
  - Then the database contents and the attachment bytes in that upload are ciphertext produced on the device
- **Scenario: Drive is not the key holder** — Must
  - Given that upload
  - When the Drive API has the file
  - Then the app has not sent the Keystore key to Drive
- **Scenario: second device, keys unanswered** — Must
  - Given another install has downloaded the ciphertext and he has not answered how keys move
  - When that install tries to show the vault
  - Then it does not pretend the items are readable and it does not invent a key-transfer method

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- If the second device cannot read the copy yet, the words say so. No fake empty library presented as the truth.

## Dependencies and risks

- Depends on US-18 for the upload and US-35 for keys on this device.
- His open questions on key transport and loss block calling the second device "done".
- Threat model required before implementation. Not started.

## Implementation notes

Encrypt before upload. Do not specify the cipher. Not a design spec.
