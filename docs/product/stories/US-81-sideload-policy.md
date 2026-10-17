# US-81 Publish a sideload signing policy

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> US-24 still requires a signed APK because epic E6 does. This story does not replace that.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Publish a sideload signing policy.

## User story

**As a** person named Miguel
**I want** a written sideload policy for the release key, the upgrade, and developer verification
**So that** a later install can replace the app without losing the vault

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated sideloading to his bots. He did not decide it.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a self-updater. There is none. Play Store listing. Exact keystore passwords, which are never written in the repo.

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: the release key** — Must
  - Given the release signing key
  - When the policy is followed
  - Then the key is kept offline, two encrypted backups of it exist outside the repo, and the published record is the SHA-256 of the certificate
- **Scenario: the APK signatures** — Must
  - Given a release APK
  - When CI runs `apksigner verify --print-certs`
  - Then the APK is signed with v2 and v3, and the printed certificate matches the published SHA-256
  - And the policy says whether a v3 rotation proof is kept with the offline key. If no rotation has happened, the published certificate is the one that command prints
- **Scenario: an upgrade keeps the vault** — Must
  - Given an installed release build with a vault
  - When a newer APK is installed with the same application id, the same signing key, and a higher `versionCode`
  - Then the vault is still there after the upgrade
  - And the policy says, with a pointer to US-35, that uninstall, clear storage, or a signing-key mismatch destroys the Keystore key, so the vault and every zip become unreadable. Uninstall is not a way to fix or upgrade
- **Scenario: debug builds** — Must
  - Given a debug build
  - When it is installed beside a release build
  - Then its application id ends in `.debug`
- **Scenario: developer verification** — Must
  - Given the policy page
  - When it is read
  - Then it follows [Register for limited distribution](https://developer.android.com/developer-verification/guides/limited-distribution), last updated 2026-09-30 UTC: the account is free, it does not ask for a government ID, and it shares with up to 20 devices
  - And it follows the [verification overview](https://developer.android.com/developer-verification/guides): enforcement named there starts on 2026-09-30 in Brazil, Indonesia, Singapore, and Thailand for participating stores, and the ADB workflow stays the same
  - And it does not repeat an older "global 2027" sentence that this page does not use
- **Scenario: no self-updater** — Must
  - Given the app
  - When he looks for an update control
  - Then the app does not download or install its own update

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.

## Dependencies and risks

- The specification now records that sideload policy was delegated, after the amendment approved on 2026-10-17 at 15:55 IST. These criteria stay proposed.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal. Do not commit key material.
