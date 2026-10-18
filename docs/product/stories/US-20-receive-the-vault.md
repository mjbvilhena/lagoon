# US-20 Receive the vault on another install

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1. One-sided items are kept.

## Title

Receive the vault on another install.

## User story

**As a** person named Miguel
**I want** a new install to receive items that exist only on the other side
**So that** the vault follows me without a restore button

## Background

- The specification says an item that exists on only one side is kept. The merge basis for that case is a stable item id and `updatedAt` (US-56).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the same item edited on both sides, and how a deletion propagates (US-73). The options on that story are not chosen. Keys on a new device are US-74.
- A new install needs the passphrase to re-create the key before the vault, and its reminders, are available (US-35, US-55).

## Acceptance criteria

- **Scenario: passphrase not entered** — Must
  - Given a new install has not entered the passphrase
  - When sync would receive the vault
  - Then the vault is not available until the passphrase re-creates the key
- **Scenario: empty phone, existing Drive copy** — Must
  - Given this install has no items, he is signed in, and the key question in US-74 is already satisfied for this test
  - When sync receives the remote vault
  - Then the local vault contains those items and there was no Restore button
- **Scenario: one-sided items** — Must
  - Given each side has items the other lacks, and no id exists on both sides with two different `updatedAt` values
  - When sync merges
  - Then both sets of items are kept
- **Scenario: no remote vault** — Must
  - Given he is signed in and Drive has no vault
  - When sync runs
  - Then local items stay and nothing is wiped
- **Scenario: not signed in** — Must
  - Given he is not signed in
  - When the app opens
  - Then no remote vault is applied

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No Restore control. No placeholder sentences.

## Dependencies and risks

- If the key is not available, readability is US-74, not this story.
- A conflicting id is left untouched by this story and is US-73.

## Implementation notes

Read and write `appDataFolder` only. Not a design spec.
