# US-93 Finish a delete after the process dies

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. Split from the unmet process-death clause on US-13.

## Title

Finish a delete after the process dies.

## User story

**As a** person named Miguel
**I want** a finished undo window to move the item to trash even if the app was killed
**So that** leaving the confirmation is not undone by a restart

## Background

- US-13 says the deferred hard delete is stored, so process death after the confirmation ends still completes the delete.
- The data layer forbids plaintext vault bytes on disk. The vault key is US-35, which is not in this slice, so the running vault stays in memory.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: choosing a key scheme, and writing a tombstone. Trash is not skipped.

## Acceptance criteria

- **Scenario: the process dies after the confirmation ends** — Must
  - Given he has dismissed the undo snackbar, left that screen, or the snackbar has timed out
  - When the process is killed before the trash write finishes, and the app opens again
  - Then the item is in trash
  - And the write is encrypted with the vault key

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences.

## Dependencies and risks

- Blocked by the vault key in US-35. A plaintext file is not a substitute.

## Implementation notes

Not a design spec.
