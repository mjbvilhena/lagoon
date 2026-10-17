# US-20 Receive the vault on another install

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> The file was renamed from `US-20-restore-from-drive.md`. This is not a Restore button.

**Epic:** E4. Not an approval.

**MoSCoW:** Must. v1, from approved epic E4. Q5 is resolved as merge.

## Title

Receive the vault on another install.

## User story

**As a** person named Miguel
**I want** a new install to receive items that exist only on the other side
**So that** the vault follows me without a restore button

## Background

- The specification says an item that exists on only one side is kept. The merge basis for that case is a stable item id and `updatedAt` (US-56).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the same item edited on both sides, and how a deletion propagates. Those are US-73. Scout's option (tombstones, per-field last-writer-wins, merged notes) is recorded there and is not chosen. Keys on a new device are US-74.

## Acceptance criteria

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
