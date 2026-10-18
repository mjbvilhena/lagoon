# US-86 Change the passphrase

> **Approval status: Draft. Not approved.**
> Security criteria approved by mjbvilhena on 2026-10-18.

**Epic:** E8.

**MoSCoW:** Should.

## Title

Change the passphrase.

## User story

**As a** person named Miguel
**I want** to change the passphrase that the vault key is derived from
**So that** a passphrase I no longer want to use stops opening the current vault. Old zips and old Drive revisions may still open with the old passphrase

## Background

- The vault key is derived from the passphrase (US-35). Changing it re-encrypts the vault.
- If he forgets the passphrase while a phone still holds the vault key, that phone can set a new passphrase after the US-37 biometric or device-PIN unlock, without the old passphrase. This re-encrypts as in this story. Amendment approved by mjbvilhena on 2026-10-18.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the derivation method, the parameters, and where the non-secret salt lives (US-74).

## Acceptance criteria

- **Scenario: the change** — Must if the story is built.
  - Given the correct current passphrase and a new one entered twice
  - When the change completes
  - Then the phone vault and the Drive copy are re-encrypted under the new derived key
  - And the new passphrase re-creates the key on another device
  - And the old passphrase no longer re-creates it for the current Drive copy
  - And the current and new passphrase fields follow US-35 "the passphrase field", and the US-35 strong-passphrase guidance is shown.
- **Scenario: the two new passphrases differ** — Must if the story is built.
  - Given he enters two new passphrases
  - When the two new-passphrase entries differ
  - Then he is told, and no change starts
- **Scenario: the current passphrase** — Must if the story is built.
  - Given the vault exists
  - When he starts a passphrase change from Settings, other than through "a forgotten passphrase on a phone that still holds the key"
  - Then he authenticates with the current passphrase first
- **Scenario: a wrong current passphrase** — Must if the story is built.
  - Given he enters the current passphrase
  - When that passphrase is wrong
  - Then it is refused and nothing changes
- **Scenario: a crash during re-encryption** — Must if the story is built.
  - Given re-encryption is interrupted by a crash
  - When the app starts again
  - Then the phone vault and the Drive copy are each readable under exactly one of the old or new passphrase, and neither is left in a mixed state
- **Scenario: another install** — Must if the story is built.
  - Given he changed the passphrase on this phone
  - When another signed-in install syncs
  - Then that install asks for the new passphrase
  - And it does not overwrite the re-encrypted vault
- **Scenario: old zips** — Should
  - Given zips were exported before the change
  - When the change completes
  - Then he is told those zips still open only with the old passphrase
- **Scenario: a forgotten passphrase on a phone that still holds the key** — Should.
  - Given this phone holds the vault key
  - When he chooses "I've forgotten my passphrase" and completes the US-37 biometric or device-PIN unlock
  - Then he enters a new passphrase twice without the old one
  - And this re-encrypts as in 'the change', with the US-37 unlock in place of the current passphrase.
- **Scenario: a cancelled or failed unlock** — Should.
  - Given this phone holds the vault key
  - When he chooses "I've forgotten my passphrase" and the US-37 biometric or device-PIN unlock is cancelled or fails
  - Then nothing changes

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-35 holds setup and re-create. This story stays Draft.
- Drive keeps old revisions the old passphrase may open until the US-74 ADR rules.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
