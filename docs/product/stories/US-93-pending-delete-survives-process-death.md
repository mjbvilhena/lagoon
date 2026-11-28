# US-93 Finish a delete after the process dies

> **Approval status: Approved.**
> Approved on 2026-11-28 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant under Miguel's delegation of 2026-10-17.

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
- The data layer forbids plaintext vault bytes on disk. The vault key is US-35.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules. Trash and restore set `updatedAt` and `trashChangedAt` and do not set `userEditedAt` (ADR-0002).
- Out of scope: choosing a key scheme, and writing a tombstone. Trash is not skipped. The trash fields, once written, still sync as ADR-0002 and US-66 say. The pending delete itself is local and does not sync.

## Decisions

mjbvilhena decided on 2026-11-28 at 07:11 GMT. He typed "1-5 approved as recommended". For this story that is item 5. If the app is killed while the undo snackbar is still showing, the delete still completes on the next launch. This is a decision. It is not whole-story approval. US-93 stays Draft. Not approved. Item 1 of that same sentence is the security criterion in the scenario below. It is not whole-story approval.

Decision (mjbvilhena, 2026-11-28): the pending delete is local and does not sync; after a kill during the undo snackbar, the 30-day trash clock starts when the delete completes on the next launch. He typed "I'm ok with your recommendation" on 2026-11-28 at 07:22 GMT. This is a decision. It is not whole-story approval. US-93 stays Draft. Not approved.

## Acceptance criteria

- **Scenario: the process dies after the confirmation ends** — Must
  - Given the vault key exists
  - And he has dismissed the undo snackbar, left that screen, or the snackbar has timed out
  - When the process is killed before the trash write finishes, and the app opens again
  - Then the item is in trash
  - And `inTrash` is set
  - And `trashedAt` and `trashChangedAt` are the instant the confirmation ended (ADR-0002, US-66)
  - And `updatedAt` is set as for any trash write (ADR-0002, US-66)
  - And `userEditedAt` is unchanged
- **Scenario: the app is killed while the undo snackbar is showing** — Must
  - Given the vault key exists
  - And the undo snackbar is still showing
  - When the app is killed, and the app opens again
  - Then the delete still completes
  - And the item is in trash
  - And `inTrash` is set
  - And `trashedAt` and `trashChangedAt` are the instant the delete completes on that launch (ADR-0002, US-66)
  - And `updatedAt` is set as for any trash write (ADR-0002, US-66)
  - And `userEditedAt` is unchanged
- **Scenario: where the pending delete is held** — Must
  - Security criteria approved by mjbvilhena on 2026-11-28.
  - Given the vault key exists
  - When a pending delete is stored, or the trash write runs
  - Then the stored pending delete and the trash write are held only in storage US-35 already encrypts, with no plaintext copy and no new key purpose
- **Scenario: the pending delete does not sync** — Must
  - Given a pending delete is stored on this phone and the trash write has not run
  - When another phone syncs
  - Then that other phone does not receive the pending delete

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences.

## Dependencies and risks

- Needs the encrypted on-disk vault: US-35, ADR-0006, US-56, and US-66. US-35, US-56, and US-66 are approved. ADR-0006 is accepted. A plaintext file is not a substitute.
- The pending delete is local and does not sync.

## Implementation notes

Not a design spec.
