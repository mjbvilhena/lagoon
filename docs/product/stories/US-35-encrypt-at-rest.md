# US-35 Encrypt the vault at rest

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
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
- If he forgets the passphrase while a phone still holds the vault key, that phone can set a new passphrase after the US-37 biometric or device-PIN unlock, without the old passphrase. This re-encrypts as in US-86. Amendment approved by mjbvilhena on 2026-10-18.
- Proposed amendment. Needs approval by mjbvilhena. On that reset, the Drive copy keeps the old passphrase for 24 hours before it is re-keyed. During those 24 hours every other phone that holds the key shows a notice and can cancel. A phone that holds no key still follows "I've forgotten my passphrase" and does not use this reset. A normal passphrase change, where he enters the current passphrase, does not wait. This is not approved.
- Proposed amendment. Needs approval by mjbvilhena. A character is one Unicode code point after NFC. A passphrase shorter than 12 or longer than 128 is refused, and a passphrase on the bundled common-password list is refused, before the KDF runs. There is no strength meter. The strong-passphrase guidance still shows. A phone that cannot meet the Argon2id memory floor refuses to set up a vault and refuses to re-create one from Drive. It does not lower memory, iterations, or parallelism. The test is a failed native allocation of 65536 KiB, not `isLowRamDevice()`. The scenarios whose title line carries this same marker are not approved. This is not approved.
- Proposed amendment. Needs approval by mjbvilhena. There is no passphrase recall check. Settings has no "Check my passphrase" control, and there is no periodic reminder to re-enter the passphrase. A forgotten passphrase, together with losing every phone that holds the key, means the content is lost. There is no in-app aid to remember it. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved.
- Proposed amendment. Needs approval by mjbvilhena. A phone whose key is not hardware-backed can still set up a vault. Before setup it shows: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." He has to accept that warning, or no vault is created. The same warning stays in Settings on that phone for as long as the key is not hardware-backed. Settings does not ask him to accept it again. It is not shown as a gate on later opens. A phone whose key is hardware-backed does not show it. This is not approved.
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
- **Scenario: a passphrase under the minimum** — Proposed amendment. Needs approval by mjbvilhena.
  - Given he is setting up the vault
  - When the passphrase is shorter than 12 Unicode code points after NFC, longer than 128, or on the bundled common-password list
  - Then the passphrase is refused
  - And the KDF does not run
  - And there is no strength meter
  - And the strong-passphrase guidance still shows
- **Scenario: the phone cannot meet the memory floor** — Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone cannot meet the Argon2id memory floor
  - When he tries to set up a vault
  - Then setup is refused
  - And memory, iterations, and parallelism are not lowered
- **Scenario: what "key gone" means** — Must
  - Given the Keystore entry is missing, or the vault cannot be unwrapped with that key
  - When he opens the app
  - Then he can re-create the same key by entering the passphrase
- **Scenario: re-create from the passphrase** — Must.
  - Given the Keystore copy is gone and he still knows the passphrase
  - When he enters it
  - Then the same key is derived again, using the method, minimum parameters and salt named in the US-74 ADR
  - And no copy of the key, wrapped or otherwise, is read from Drive or from anywhere else
- **Scenario: a new biometric does not drop the key** — Must
  - Given the vault key exists and does not use biometric authentication
  - When he enrolls a new biometric
  - Then the vault key stays valid
- **Scenario: no user authentication on the key** — Must.
  - Given the vault key is created
  - When `KeyInfo` is read for the Keystore key that holds or wraps the derived key
  - Then on API 31+ `KeyInfo.getSecurityLevel()` is StrongBox or TEE, and on API 26–30 `KeyInfo.isInsideSecureHardware()` is true
  - And `isUserAuthenticationRequired` is false
  - And background sync and reminders can decrypt while the phone is locked, after the first unlock since boot
  - And sensitive content still needs the US-37 unlock
  - And normal use does not ask for the passphrase
- Proposed amendment. Needs approval by mjbvilhena. The hardware check in "no user authentication on the key" has an exception when this phone has no secure hardware and he has accepted the warning in "a phone without secure hardware". `isUserAuthenticationRequired` stays false. This is not approved.
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
- **Scenario: I've forgotten my passphrase** — Must.
  - Given this phone holds no key copy
  - When he chooses "I've forgotten my passphrase"
  - Then he is told the vault can't be opened on this phone without the passphrase
  - And he is offered a new empty vault, subject to the "a new empty vault" scenario
- **Scenario: after the first unlock since boot** — Must.
  - Given he has unlocked the phone once since boot
  - When background sync or a reminder needs the vault
  - Then those jobs can decrypt while the phone is locked
  - And on API 28+ the Keystore key is created with `setUnlockedDeviceRequired(false)`
  - And on API 26–27 the key is never unlock-gated
- **Scenario: which key is hardware-backed** — Must.
  - Given the derived key is held or wrapped
  - When `KeyInfo` is read
  - Then the check reads the Keystore key that holds or wraps the derived key
  - And on API 31+ `KeyInfo.getSecurityLevel()` is StrongBox or TEE
  - And on API 26–30 `KeyInfo.isInsideSecureHardware()` is true
- Proposed amendment. Needs approval by mjbvilhena. The hardware check in "which key is hardware-backed" has the same exception. A phone without secure hardware, after that warning, may report a software-backed key. This is not approved.
- **Scenario: a phone without secure hardware** — Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone's Keystore cannot keep the vault key in secure hardware
  - When he sets up a vault
  - Then he sees "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key."
  - And the vault is not created until he accepts that warning
  - And if he declines, no vault is written
  - And if he accepts, the vault is created and the key is not hardware-backed
  - And the same warning stays in Settings on that phone for as long as the key is not hardware-backed
  - And Settings does not ask him to accept it again
  - And it is not shown as a gate on later opens
  - And a phone whose key is hardware-backed does not show it

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on the US-57 spike before FTS and SQLCipher are combined. This story does not choose the cipher.
- Amendment approved by mjbvilhena on 2026-10-18.
- Changing the passphrase is US-86. It is not a criterion of this story.
- When the device has no hardware-backed Keystore, the fallback is decided at the design gate.
- Proposed amendment. Needs approval by mjbvilhena. On that phone a vault can be created after he accepts the warning in "a phone without secure hardware". This is not approved.

## Implementation notes

Android Keystore holds the derived key, or a hardware-backed Keystore key wraps it. Not a design spec.
