# US-87 Forget this phone

> **Approval status: Proposed for story review.**

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

Forget this phone.

## User story

**As a** person named Miguel
**I want** a Settings button that retires a lost phone's Drive sync file, and never does that on its own
**So that** Drive no longer keeps that phone's records file, and a silent phone is not retired for me

## Background

- The control is named "Forget this phone". It lives in Settings (US-59). It retires one other install's records file. It does not retire the install he is holding.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- The retire rules are the ones ADR-0002 already states. This story does not add a rule that record does not state.
- Out of scope: "Disconnect and delete Lagoon data from Drive" (US-78). That is a different control.
- Out of scope: changing the passphrase (US-86). Forgetting does not re-key and does not mint a key version.

## Acceptance criteria

- **Scenario: the unlock** — Must
  - Given he is on a phone that still holds the vault, and another install has a records file
  - When he chooses "Forget this phone"
  - Then the US-37 unlock is required first
  - And a cancelled or failed unlock changes nothing
  - Security criteria approved by mjbvilhena on 2026-10-22. "Forget this phone" requires the US-37 unlock first. A cancelled or failed unlock changes nothing.
- **Scenario: which phone** — Must
  - Given the US-37 unlock succeeded, and at least one other install has a records file
  - When the list is shown
  - Then he sees each other install, with its device name, its model, and its last check-in date
  - And this install is not listed
  - And when he chooses one, Lagoon asks him to confirm, naming that install
  - And cancelling changes nothing
- **Scenario: no other phone** — Must
  - Given no other install has a records file
  - When the US-37 unlock has succeeded
  - Then it says there is no other phone to forget
  - And it deletes nothing
- **Scenario: one other install** — Must
  - Given the US-37 unlock succeeded
  - When he names one other install and "Forget this phone" runs
  - Then that install's records file is deleted from Drive
  - And the install he is holding is not retired
  - And the passphrase does not change
  - And no key version is minted
  - And US-55 stops counting that install's check-in
  - Security criteria approved by mjbvilhena on 2026-10-22. Forget this phone does not revoke a stolen phone. Revoking a stolen phone is a passphrase change (US-86) and Google sign-out.
  - Security criteria approved by mjbvilhena on 2026-10-22. During a pending passphrase reset, Forget this phone requires the current passphrase, the same as US-78.
  - Amendment approved by mjbvilhena on 2026-10-22. Forget this phone first merges that install's latest records file. If the read fails, nothing is deleted and the user is told.
- **Scenario: Drive does not delete** — Must
  - Given he has confirmed one other install
  - When the phone is offline, or Drive returns an error
  - Then nothing is deleted
  - And he sees an error
  - And he can retry
- **Scenario: never automatic** — Must
  - Given an install has a records file
  - When that phone is old, silent, or misses a check-in
  - Then the file is not retired
  - And it is retired only when he chooses "Forget this phone" in Settings on a phone that still holds the vault
- **Scenario: attachments** — Must
  - Given that records file is deleted
  - When an attachment object is referenced by no remaining records file
  - Then it becomes eligible for the same collection as any other unreferenced attachment
  - And it is not deleted in that step while another file still points at it
- **Scenario: tombstones** — Must
  - Given the forgotten install has no records file
  - When tombstones are collected
  - Then they no longer wait for that install to merge a file
  - And collection still waits for every install that still has a file, and for the 90 days after the tombstone timestamp
- **Scenario: the phone returns** — Must
  - Given that records file was deleted
  - When the forgotten phone returns and he enters the passphrase
  - Then it uploads a new records file under the current key version
  - And it does not restore the deleted file
  - And it does not bring back a tombstone that has already been collected
  - Amendment approved by mjbvilhena on 2026-10-22. A returning forgotten phone discards its local copies and rebuilds from Drive. Items permanently deleted stay gone, and that phone's unsynced local edits are lost.
  - Amendment approved by mjbvilhena on 2026-10-22. A returning phone knows it was forgotten when its own records file is missing from Drive. After rebuilding, it rejoins with a new install id.
  - Security criteria approved by mjbvilhena on 2026-10-22. The returning phone's orphan-attachment cleanup deletes local copies only, never a Drive object.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The control is named "Forget this phone". No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-37 is the unlock. US-59 hosts the control. The behavior is this story.
- ADR-0001, ADR-0002, and US-55. US-55 stops counting the forgotten install's check-in.
- US-78 is a different control. US-86 is the re-key. This action is neither.
- A forgotten phone that returns after the tombstone was garbage-collected still uploads a new records file and does not bring back a collected tombstone. Attachment files that belong to no live item are US-66.

## Implementation notes

Not a design spec. The story is Proposed for story review. Do not implement from this file.
