# US-66 Keep a trash beyond undo

> **Approval status: Proposed for story review. Needs approval by mjbvilhena.**

**Epic:** E3.

**MoSCoW:** Must. v1. Delete with undo is US-13. Proposed amendment. Needs approval by mjbvilhena. v1 does not ship without this story. It stays Proposed for story review. It is not approved. Decided by mjbvilhena; this record is Proposed until he approves it.

## Title

Keep a trash beyond undo.

## User story

**As a** person named Miguel
**I want** a deleted item to sit in trash before it is gone for good
**So that** I can recover it after the undo snackbar has gone

## Background

- Domain constraints: Proposed `docs/domain/vault/DOMAIN.md` describes the same trash rule. No MUST is in force while that file and ADR-0002 are unapproved. Do not invent a second rule.
- Out of scope: US-13, which remains the undo path.
- Proposed amendment. Needs approval by mjbvilhena. Trash is Must for v1. v1 does not ship without it. When undo ends, this phone sets `inTrash` and `trashedAt`. Those fields sync, and the later edit wins. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. A later edit to the item's content, with `updatedAt` after `trashedAt`, brings it back. The tombstone, the permanent delete, spreads only after 30 × 24 hours from `trashedAt`. A delete does not become a tombstone by skipping trash. An id with a tombstone is not a one-sided item the other phone keeps. Decided by mjbvilhena; this record is Proposed until he approves it. The precise field rule is ADR-0002.

## Acceptance criteria

- **Scenario: trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item has been deleted and the undo window is open
  - When the undo window ends
  - Then the item is in trash, `trashedAt` is that instant, and no tombstone is written
  - And 30 days means 30 × 24 hours after `trashedAt`
- **Scenario: hidden on every phone** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given this phone has merged `inTrash` and the 30 × 24 hours have not ended
  - When he looks at the home list, search, Upcoming, a tag view, the inbox, or a topic view
  - Then the item is absent
  - And its reminders are silenced, including on the phone US-55 would pick
  - And its expiry notices are paused
  - And a queued topic job for it is not sent
- **Scenario: trash view** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given an item is in trash
  - When he opens Trash
  - Then he sees its type, its title, the days left, and Restore
  - And the exact layout waits for the UX artefact
- **Scenario: restore from any phone** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash on a phone that has merged `inTrash`
  - When he restores it on any phone
  - Then `inTrash` and `trashedAt` are cleared and no tombstone is written
  - And the item returns on every phone that merges that record
  - And its reminders are scheduled again
- **Scenario: a later content edit** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash
  - When another phone edits its content, and that edit's `updatedAt` is later than `trashedAt`
  - Then the edit is kept
  - And `inTrash` and `trashedAt` are cleared
  - And the item leaves trash on every phone that merges it
  - And a content edit is a change to the title, body, personal notes, tags, reminder, expiry, attachments, or the sensitive mark
  - And a write that only sets or clears `inTrash` is not that edit
- **Scenario: an older edit does not clear trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is in trash
  - When a content edit arrives whose `updatedAt` is earlier than `trashedAt`
  - Then the item stays in trash
- **Scenario: a sensitive item in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given a sensitive item is in trash
  - When Trash is shown before the US-37 unlock
  - Then that row uses the US-75 neutral form
  - And its title is not shown until that unlock
- **Scenario: the 30 days end** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the item is still in trash
  - When 30 × 24 hours after `trashedAt` end
  - Then this phone removes it as US-13 removes a finished delete
  - And a tombstone is written and spreads
  - And the other phone does not keep that id as a one-sided item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Proposed amendment. Needs approval by mjbvilhena. This story is Must. v1 does not ship without it. The field rule and the tombstone are ADR-0002, which is still Proposed. A delete does not become a tombstone by skipping trash.

## Implementation notes

Not a design spec.
