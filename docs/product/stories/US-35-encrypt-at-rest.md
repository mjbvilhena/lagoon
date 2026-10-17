# US-35 Encrypt the vault at rest

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E8.

## Title

Encrypt the vault at rest.

## User story

**As a** person named Miguel
**I want** the database, the files, and the copies derived from them to be encrypted, and to be warned that a lost key cannot be recovered
**So that** a copy of the phone's storage does not contain the vault in the clear, and I am not promised a recovery that does not exist

## Background

- On 2026-10-17 at 14:56 IST, Miguel (`mjbvilhena`) decided: if the vault key is lost, the content is lost. There is no recovery.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the algorithm, mode, and key size (an ADR). How a key reaches a second device (US-74).

## Acceptance criteria

- **Scenario: the warning** — Must
  - Given the vault key has not been created
  - When he is asked to set up the vault
  - Then he sees a clear warning that if the key is lost the content is lost and there is no recovery, and the key is not created until he accepts that warning
- **Scenario: no recovery path** — Must
  - Given the key was created and is then gone
  - When he opens the app
  - Then he sees that the content is lost, and there is no restore, reset, or "forgot key" flow
- **Scenario: data at rest** — Must
  - Given the key exists
  - When the database and attachment files are stored
  - Then those bytes are encrypted with the vault key
- **Scenario: derived files** — Must
  - Given thumbnails, previews, snapshots, the OCR cache, share temp copies, or export temp copies exist
  - When they are on disk
  - Then they are encrypted with the vault key as well
- **Scenario: he can still use the app** — Must
  - Given the key is present
  - When he saves and opens an ordinary item
  - Then the item works without a second passphrase prompt for that ordinary item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on the US-57 spike before FTS and SQLCipher are combined. This story does not choose the cipher.

## Implementation notes

Android Keystore holds the key. Not a design spec.
