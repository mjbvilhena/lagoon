# US-35 Encrypt the vault at rest

> **Approval status: Draft. Not approved.**
> Security criteria approved by mjbvilhena on 2026-10-18.

**Epic:** E8.

**MoSCoW:** Must. v1.

## Title

Encrypt the vault at rest.

## User story

**As a** person named Miguel
**I want** the database, the files, and the copies derived from them to be encrypted, and to be able to re-create the vault key from a passphrase I choose
**So that** a copy of the phone's storage does not contain the vault in the clear, and another device can re-create the same key

## Background

- Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses, with a slow memory-hard key derivation and a salt, so entering the passphrase on any device re-creates the same key. No copy of the key, wrapped or otherwise, is stored anywhere off the phone. On the phone the derived key is held in the Android Keystore, or wrapped by a hardware-backed Keystore key, without user authentication, so normal use, sync, and reminders need no passphrase. Changing the passphrase means re-encrypting the vault (US-86). Forgetting the passphrase and losing the device still means the content is lost. Setup gives strong-passphrase guidance. The exact method, parameters, and where the non-secret salt lives are settled in the US-74 ADR at the design gate.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the algorithm, mode, and key size. The derivation method, parameters, and where the non-secret salt lives (US-74). A share temp copy another app must read (US-64). The vault key is not bound to device authentication.

## Acceptance criteria

- **Scenario: the warning** — Must
  - Given the vault key has not been created
  - When he is asked to set up the vault, including when the first share arrives before he has accepted
  - Then he sees strong-passphrase guidance, and a clear warning that the key is derived from the passphrase, that forgetting the passphrase and losing the device means the content is lost, that uninstall or clear storage destroys the Keystore copy, and that losing the signing key forces a reinstall that destroys that copy too
  - And the key is not created until he accepts that warning
  - And the first shared item is held pending until he accepts. If he declines, he is told that nothing was saved. The share is not dropped in silence
  - And that pending share stays in memory only. Nothing is written to disk in plaintext before the key exists
- **Scenario: what "key gone" means** — Must
  - Given the Keystore entry is missing, or the vault cannot be unwrapped with that key
  - When he opens the app
  - Then he can re-create the same key by entering the passphrase
  - And if he has forgotten the passphrase and that device copy is gone, the content is lost
- **Scenario: re-create from the passphrase** — Must
  - Given the Keystore copy is gone and he still knows the passphrase
  - When he enters it
  - Then the same key is derived again, with the slow memory-hard derivation and the salt
  - And no copy of the key, wrapped or otherwise, is read from Drive or from anywhere else
- **Scenario: passphrase forgotten and device gone** — Must
  - Given he has forgotten the passphrase and the device copy is gone
  - When he opens the app
  - Then he sees that the content is lost
  - And he can start a new empty vault. The app is not left unable to open
- **Scenario: a new biometric does not drop the key** — Must
  - Given the vault key exists and does not use biometric authentication
  - When he enrolls a new biometric
  - Then the vault key stays valid
- **Scenario: no user authentication on the key** — Must
  - Given the vault key is created
  - When `KeyInfo` is read for that key
  - Then it reports a hardware-backed security level, StrongBox or TEE
  - And `isUserAuthenticationRequired` is false
  - And background sync and reminders can decrypt while the phone is locked
  - And sensitive content still needs the US-37 unlock
  - And normal use does not ask for the passphrase
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
- **Scenario: he can still use the app** — Must
  - Given the key is present
  - When he saves and opens an ordinary item
  - Then the item works, and normal use, sync, and reminders do not ask for the passphrase
- **Scenario: a wrong passphrase** — Must
  - Given he enters a passphrase
  - When that passphrase is wrong
  - Then he is told it is wrong
  - And nothing is changed or deleted
- **Scenario: the passphrase field** — Must
  - Given he types the passphrase
  - When the field is shown
  - Then keyboard learning and suggestions are off (`IME_FLAG_NO_PERSONALIZED_LEARNING`) and the input type is a password
  - And the screen uses `FLAG_SECURE`
  - And the passphrase is never logged or written to disk
- **Scenario: a new empty vault** — Must
  - Given a Drive copy of the vault already exists
  - When he starts a new empty vault
  - Then that Drive copy is not overwritten or deleted until he confirms explicitly after a warning
- **Scenario: I've forgotten my passphrase** — Must
  - Given no device holds the key copy
  - When he chooses "I've forgotten my passphrase"
  - Then the content is treated as lost
- **Scenario: after the first unlock since boot** — Must
  - Given he has unlocked the phone once since boot
  - When background sync or a reminder needs the vault
  - Then those jobs can decrypt while the phone is locked
  - And the Keystore key is created with `setUnlockedDeviceRequired(false)`
- **Scenario: which key is hardware-backed** — Must
  - Given the derived key is held or wrapped
  - When `KeyInfo` is read
  - Then the check reads the Keystore key that holds or wraps the derived key
  - And when StrongBox or TEE is not available on API 26–30, the fallback is decided at the design gate

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on the US-57 spike before FTS and SQLCipher are combined. This story does not choose the cipher.
- Amendment approved by mjbvilhena on 2026-10-18. Security criteria approved by mjbvilhena on 2026-10-18. This story stays Draft.
- Changing the passphrase is US-86. It is not a criterion of this story.

## Implementation notes

Android Keystore holds the derived key, or a hardware-backed Keystore key wraps it. Not a design spec.
