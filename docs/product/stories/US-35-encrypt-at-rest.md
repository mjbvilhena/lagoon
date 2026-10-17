# US-35 Encrypt the vault at rest

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Must. v1.

## Title

Encrypt the vault at rest.

## User story

**As a** person named Miguel
**I want** the database, the files, and the copies derived from them to be encrypted, and to be warned that a lost key cannot be recovered
**So that** a copy of the phone's storage does not contain the vault in the clear, and I am not promised a recovery that does not exist

## Background

- If the vault key is lost, the content is lost. There is no recovery.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the algorithm, mode, and key size (an ADR). How a key reaches a second device (US-74). Whether the Keystore key is bound to device authentication stays open. This story does not choose it. It does set `setInvalidatedByBiometricEnrollment(false)`.

## Acceptance criteria

- **Scenario: the warning** — Must
  - Given the vault key has not been created
  - When he is asked to set up the vault, including when the first share arrives before he has accepted
  - Then he sees a clear warning that if the key is lost the content is lost and there is no recovery, that uninstall or clear storage destroys the key, and that losing the signing key forces a reinstall that destroys it too
  - And the key is not created until he accepts that warning
  - And the first shared item is held pending until he accepts. If he declines, he is told that nothing was saved. The share is not dropped in silence
  - And that pending share stays in memory only. Nothing is written to disk in plaintext before the key exists
- **Scenario: what "key gone" means** — Must
  - Given the Keystore entry is missing, or the vault cannot be unwrapped with that key
  - When he opens the app
  - Then the key is treated as gone
- **Scenario: no recovery path** — Must
  - Given the key was created and is then gone
  - When he opens the app
  - Then he sees that the content is lost, and there is no restore, reset, or "forgot key" flow
  - And he can start a new empty vault. The app is not left unable to open
- **Scenario: a new biometric does not drop the key** — Must
  - Given the key exists
  - When he enrolls a new biometric
  - Then the key stays valid. The Keystore entry is created with `setInvalidatedByBiometricEnrollment(false)`
- **Scenario: the OS authentication state** — open
  - Given the key exists
  - When the OS authentication state changes
  - Then this story does not say whether the key stays valid. Whether the key is auth-bound stays open
- **Scenario: data at rest** — Must
  - Given the key exists
  - When the database and attachment files are stored
  - Then those bytes are encrypted with the vault key
- **Scenario: derived files** — Must
  - Given thumbnails, previews, snapshots, the OCR cache, the WebView cache, the image cache, WorkManager inputs, logs, or export temp copies exist
  - When they are on disk
  - Then they are encrypted with the vault key as well
- **Scenario: backup of app data is off** — Must
  - Given the manifest
  - When it is read
  - Then `allowBackup` is false and `dataExtractionRules` exclude the vault
- **Scenario: a share temp copy another app must read** — Must
  - Given a temp copy has to be readable by another app
  - When it is created
  - Then encrypting that copy is not this story. US-64 holds it
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
