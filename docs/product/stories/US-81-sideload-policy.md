# US-81 Publish a sideload signing policy

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-18.
> Security criteria approved by mjbvilhena on 2026-10-18.

**Epic:** E6.

**MoSCoW:** Must.

## Title

Publish a sideload signing policy.

## User story

**As a** person named Miguel
**I want** a written sideload policy for the release key, the upgrade, and developer verification
**So that** a later install can replace the app without losing the vault

## Background

- A later install uses the same application id and the same release key. The criteria below are the requirements.
- Items not yet synced to Drive are lost with the phone's vault on uninstall.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a self-updater. There is none. Play Store listing. Exact keystore passwords, which are never written in the repo.

## Acceptance criteria

- **Scenario: the release key** — Must
  - Given the release signing key
  - When the policy is followed
  - Then the key is kept offline, two encrypted backups of it exist outside the repo, and the published record is the SHA-256 of the certificate and the SHA-256 of the release APK
- **Scenario: the APK signatures** — Must
  - Given a release APK
  - When CI runs `apksigner verify --print-certs` on the release APK
  - Then that APK is the artefact CI verifies, it is signed with v2 and v3, the printed certificate matches the published certificate SHA-256, and the APK file's SHA-256 matches the published APK SHA-256
  - And the policy says whether a v3 rotation proof is kept with the offline key. If no rotation has happened, the published certificate is the one that command prints
- **Scenario: an upgrade keeps the vault** — Must
  - Given an installed release build with a vault
  - When a newer APK is installed with the same application id, the same signing key, and a higher `versionCode`
  - Then the vault is still there after the upgrade
  - And a signing-key mismatch makes Android refuse the update. It does not remove the key.
  - And only uninstall or clear storage removes that phone's key copy.
  - And the Drive copy stays readable with the passphrase or from another phone that still holds the key.
  - And each zip opens with the passphrase it was made under.
  - And uninstall is not a way to fix or upgrade
- **Scenario: uninstall and the passphrase** — Must.
  - Given the vault key was derived from his passphrase
  - When he uninstalls Lagoon or clears storage
  - Then that phone's key copy is removed
  - And the Drive copy stays readable with the passphrase or from another phone that still holds the key
  - And each zip opens with the passphrase it was made under
- **Scenario: debug builds** — Must
  - Given a debug build
  - When it is installed beside a release build
  - Then its application id ends in `.debug`
- **Scenario: developer verification** — Must
  - Given the policy page
  - When it is read
  - Then it follows [Register for limited distribution](https://developer.android.com/developer-verification/guides/limited-distribution), last updated 2026-09-30 UTC: the account is free, it does not ask for a government ID, and it shares with up to 20 devices
  - And it follows the [verification overview](https://developer.android.com/developer-verification/guides), last updated 2026-08-18 UTC: enforcement named there starts on 2026-09-30 in Brazil, Indonesia, Singapore, and Thailand, and then those protections expand globally to all certified Android devices in 2027, which covers a device in Ireland. The ADB workflow stays the same
  - And before 2027 the package `com.mjbvilhena.lagoon` is registered with the release key under a limited-distribution account. ADB stays the fallback
  - And two-step verification and a payments profile are optional on that account. They are not required by the scenarios above
- **Scenario: no self-updater** — Must
  - Given the app
  - When he looks for an update control
  - Then the app does not download or install its own update

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.

## Dependencies and risks

- The criteria above are the sideload signing policy.

## Implementation notes

Not a design spec. Do not implement from this file. Do not commit key material.
