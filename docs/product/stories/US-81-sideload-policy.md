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
  - When `apksigner verify --print-certs` is run
  - Then the APK is signed with v2 and v3, and the printed certificate matches the published SHA-256
- **Scenario: an upgrade keeps the vault** — Must
  - Given an installed release build with a vault
  - When a newer APK is installed with the same application id, the same signing key, and a higher `versionCode`
  - Then the vault is still there after the upgrade
- **Scenario: debug builds** — Must
  - Given a debug build
  - When it is installed beside a release build
  - Then its application id ends in `.debug`
- **Scenario: developer verification** — Must
  - Given the policy page
  - When it is read
  - Then it says to register under the free limited-distribution developer verification before the 2027 enforcement, and that ADB install remains the fallback
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
