# US-36 Sync only ciphertext

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Must. v1.

## Title

Sync only ciphertext.

## User story

**As a** person named Miguel
**I want** Drive to receive ciphertext only
**So that** Google cannot read the vault

## Background

- Everything synced is client-side ciphertext. The zip (US-21) is encrypted with the vault key.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: key transport (US-74). What a sensitive item reveals in an export is US-75. The topic call is not a Drive upload (US-41).
- A new install needs the passphrase to re-create the key before the vault, and its reminders, are available (US-35, US-55).

## Acceptance criteria

- **Scenario: a new install** — Must
  - Given a new install has the Drive ciphertext and has not entered the passphrase
  - When he opens the app
  - Then the vault is not available until the passphrase re-creates the key
- **Scenario: the upload is ciphertext** — Must
  - Given a seeded sentinel is in an item
  - When the sync payload is built
  - Then the payload does not contain the sentinel and does not contain the header `SQLite format 3`
- **Scenario: Drive is not the key holder** — Must
  - Given the payload is uploaded
  - When Drive is inspected
  - Then the vault key is not in the uploaded bytes
- **Scenario: Drive names stay opaque** — Must
  - Given an item has a title and tags
  - When the Drive file is created
  - Then the Drive file name and its metadata carry no title and no tag. They use an opaque id

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Second-device readability waits on US-74. This story does not derive a key from the Google account.

## Implementation notes

Encrypt before upload. Do not specify the cipher. Not a design spec.
