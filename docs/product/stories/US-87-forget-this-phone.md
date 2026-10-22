# US-87 Forget this phone

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

Forget this phone.

## User story

**As a** person named Miguel
**I want** a Settings button that retires a lost phone's Drive sync file, and never does that on its own
**So that** a phone I no longer have stops holding a records file, and a silent phone is not retired for me

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
- **Scenario: one other install** — Must
  - Given the US-37 unlock succeeded
  - When he names one other install and "Forget this phone" runs
  - Then that install's records file is deleted from Drive
  - And the install he is holding is not retired
  - And the passphrase does not change
  - And no key version is minted
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

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The control is named "Forget this phone". No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-37 is the unlock. US-59 hosts the control. The behavior is this story.
- US-78 is a different control. US-86 is the re-key. This action is neither.
- Open question, not a rule. A phone forgotten with "Forget this phone" comes back after the tombstone was garbage-collected. ADR-0002 says that phone uploads a new records file and does not bring back a collected tombstone. It does not say what happens to attachment bytes that phone still holds.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
