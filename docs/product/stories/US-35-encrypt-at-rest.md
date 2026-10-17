# US-35 Encrypt the vault at rest

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His words: "Yes, extra encryption would be good." Not approval. The cipher waits for an ADR.

## Title

Encrypt the database and the attachment files on the device.

## User story

**As a** person named Miguel
**I want** the vault's database and files encrypted on the phone, with keys in Android Keystore
**So that** app-private storage is not the only thing standing between the files and someone who has the device storage

## Background

- He decided encryption at rest, beyond app-private storage: an encrypted database and encrypted attachment files, keys in Android Keystore.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: naming the algorithm, mode, or key size. That is the ADR gate. What happens if the key is lost is his open question. This story does not invent a recovery story.

## Acceptance criteria

- **Scenario: data at rest** — Must
  - Given he has saved a note and an attachment
  - When the app's stored database and attachment files are inspected outside the app
  - Then they are encrypted, not readable as plain SQLite and raw files
- **Scenario: keys** — Must
  - Given that encryption
  - When the key material is located
  - Then it is in Android Keystore, not in the repository and not in a world-readable file
- **Scenario: he can still use the app** — Must
  - Given the vault is encrypted
  - When he opens the app and reads an ordinary item
  - Then he can read it. Encryption is not a lock screen by itself
- **Scenario: lost key** — Must
  - Given the key or passphrase is lost
  - When someone asks what the app does
  - Then this story does not define the recovery. That waits on his open question

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Ordinary reading does not show a cipher lecture. No placeholder about encryption on every row.

## Dependencies and risks

- Threat model is required before implementation. Not started.
- US-37 is the extra lock for sensitive items. This story is at rest for the whole vault.
- US-21 must not leave a plaintext zip beside this store.

## Implementation notes

Android Keystore. Do not choose AES mode or a library in this story. Not a design spec.
