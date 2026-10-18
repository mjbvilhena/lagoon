# US-86 Change the passphrase

> **Approval status: Draft. Not approved.**
> Proposed security criteria. Needs approval by mjbvilhena.

**Epic:** E8.

**MoSCoW:** Should.

## Title

Change the passphrase.

## User story

**As a** person named Miguel
**I want** to change the passphrase that the vault key is derived from
**So that** a passphrase I no longer want to use stops opening the vault

## Background

- The vault key is derived from the passphrase (US-35). Changing it re-encrypts the vault.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the derivation method, the parameters, and where the non-secret salt lives (US-74). What happens to old Drive revisions and old zips is decided at the design gate in that ADR.

## Acceptance criteria

- **Scenario: the current passphrase** — Should. Proposed security criterion. Needs approval by mjbvilhena.
  - Given the vault exists
  - When he starts a passphrase change
  - Then he authenticates with the current passphrase first
- **Scenario: a crash during re-encryption** — Should. Proposed security criterion. Needs approval by mjbvilhena.
  - Given re-encryption is interrupted by a crash
  - When the app starts again
  - Then the vault is readable under the old passphrase or the new passphrase
  - And it is never readable under neither
- **Scenario: another install** — Should. Proposed security criterion. Needs approval by mjbvilhena.
  - Given he changed the passphrase on this phone
  - When another signed-in install syncs
  - Then that install asks for the new passphrase
  - And it does not overwrite the re-encrypted vault
- **Scenario: old copies** — Should. Proposed security criterion. Needs approval by mjbvilhena.
  - Given old Drive revisions and old zips exist
  - When the passphrase has changed
  - Then what happens to those copies is decided at the design gate (US-74 ADR)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-35 holds setup and re-create. This story stays Draft.
- Proposed security criteria. Needs approval by mjbvilhena.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
