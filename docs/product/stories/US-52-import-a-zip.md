# US-52 Import a zip and merge it

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

Import a zip and merge it.

## User story

**As a** person named Miguel
**I want** to import a vault zip and keep items that exist on only one side
**So that** a file fallback can fill this phone without wiping it

## Background

- Import merges the way US-20 merges one-sided items. It is not a restore button.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: a conflicting id (US-73) and an import when this device has no key (US-74).

## Acceptance criteria

- **Scenario: import onto an empty vault** — Must
  - Given this vault is empty, the key for the file is available, and he picks a version-1 zip
  - When import finishes
  - Then the items, attachments, topics, and reminders from the zip are present
- **Scenario: merge** — Must
  - Given this vault already has other items and no id is in conflict
  - When he imports
  - Then both the local-only and zip-only items are kept
- **Scenario: trash state is kept** — Must
  - Proposed amendment. Needs approval by mjbvilhena.
  - Given the zip includes an item with `inTrash`, `trashedAt`, and `trashChangedAt`, and 30 × 24 hours after `trashedAt` have not ended on this phone's clock
  - When he imports it
  - Then that item stays in trash
  - And carrying those fields does not, by itself, clear trash
- **Scenario: the trash time has already ended** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given the zip includes an item with `inTrash` set, and 30 × 24 hours after `trashedAt` have already ended on this phone's clock
  - When he imports it
  - Then that item is deleted for good straight away
  - And the import writes the tombstone, the permanent-delete record, for that id
  - And the item is not left in trash
- **Scenario: not a vault zip** — Must
  - Given he picks a file that is not a versioned vault zip
  - When import runs
  - Then the local vault is unchanged and he sees that the file was not a vault export

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The rejection text is specific. No placeholder sentences.

## Dependencies and risks

- The sentinel rule from US-21 still applies to the file he imports. A plaintext database is not a successful import.

## Implementation notes

Not a design spec.
