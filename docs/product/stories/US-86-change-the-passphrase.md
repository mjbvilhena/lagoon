# US-86 Change the passphrase

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
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
- Proposed amendment. Needs approval by mjbvilhena. On that forgotten-passphrase reset, the Drive copy keeps the old passphrase for 24 hours before it is re-keyed. During those 24 hours every other phone that holds the key shows a notice and can cancel. A normal passphrase change, where he enters the current passphrase, re-encrypts the phone and the Drive copy without that wait. The phone vault and the Drive copy each stay readable under exactly one passphrase. The scenarios whose title line carries this same marker are not approved. The approved scenarios stay as written.
- Proposed amendment. Needs approval by mjbvilhena. A new passphrase of fewer than 12 characters is refused, and a new passphrase on the bundled common-password list is refused. There is no strength meter. This is not approved.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
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
- Proposed amendment. Needs approval by mjbvilhena. "another install" asks for the new passphrase after a normal change, and after the 24 hours of a forgotten-passphrase reset end with no cancel. During those 24 hours the Drive copy still opens with the old passphrase. This is not approved.
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
- **Scenario: the Drive copy waits** — Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone holds the vault key and he finishes the forgotten-passphrase reset
  - When the local re-encrypt completes
  - Then the phone vault is under the new passphrase
  - And the Drive copy keeps the old passphrase for 24 hours
  - And every other phone that holds the key shows a notice and can cancel
  - And the notice has no passphrase and no item content
- **Scenario: he has only one phone** — Proposed amendment. Needs approval by mjbvilhena.
  - Given no other phone holds the key
  - When the forgotten-passphrase reset finishes
  - Then nobody is shown the notice
  - And the Drive copy still keeps the old passphrase for 24 hours
- **Scenario: the resetting phone goes offline** — Proposed amendment. Needs approval by mjbvilhena.
  - Given the forgotten-passphrase reset has re-encrypted the phone
  - When that phone is offline before the pending-reset marker is on Drive
  - Then the Drive copy stays on the old passphrase and the 24 hours have not started
- **Scenario: cancelling the reset** — Proposed amendment. Needs approval by mjbvilhena.
  - Given another phone that holds the key cancels during the 24 hours
  - When the resetting phone reads that cancel
  - Then the Drive copy stays on the old passphrase
  - And items saved on the resetting phone during those 24 hours are copied into the restored vault under the old passphrase
  - Proposed amendment. Needs approval by mjbvilhena. A trash or a restore made on that phone during the wait is a record change merged by `trashChangedAt`, copied into that restored vault by the ordinary field rules, and is not dropped as a delete made only in the new snapshot
  - And the new local generation is discarded only after that restored vault is readable under the old passphrase
  - And the new passphrase opens nothing
- **Scenario: a normal change skips the wait** — Proposed amendment. Needs approval by mjbvilhena.
  - Given he enters the current passphrase
  - When the change completes
  - Then the phone vault and the Drive copy are re-encrypted without the 24-hour wait
- **Scenario: crash safety during the wait** — Proposed amendment. Needs approval by mjbvilhena.
  - Given a crash during the forgotten-passphrase reset, while Drive is re-keyed, or while a cancel is applied
  - When the app starts again
  - Then the phone vault and the Drive copy are each readable under exactly one passphrase, and neither is mixed
- **Scenario: a new passphrase under the minimum** — Proposed amendment. Needs approval by mjbvilhena.
  - Given he enters a new passphrase, on a normal change or on a forgotten-passphrase reset
  - When that passphrase is shorter than 12 Unicode code points after NFC, longer than 128, or on the bundled common-password list
  - Then the passphrase is refused and no change starts
  - And there is no strength meter

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-35 holds setup and re-create.
- Drive keeps old revisions the old passphrase may open until the US-74 ADR rules.

## Implementation notes

Not a design spec. Do not implement from this file.
