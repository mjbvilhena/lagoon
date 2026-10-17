# US-21 Export and import a zip

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Kept as the fallback beside sync. Q5's replace-versus-merge question is resolved: import merges. Not approval.

## Title

Export and import a zip on the device.

## User story

**As a** person named Miguel
**I want to** export the vault to a zip in device storage and import a zip the app exported
**So that** I have a copy that does not depend on Google

## Background

- The original request asked for a zip fallback. His sync decision did not remove it.
- Import merges. An item that exists only in the zip or only on the device is kept.
- Concurrent edits and deletions stay his open questions, same as sync.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a zip made by another program. Uploading the zip to Drive as the sync method.

## Acceptance criteria

- **Scenario: export** — Must
  - Given the vault has a note and an attachment
  - When he exports
  - Then a zip is written to device storage that he can find, containing the database and those files
- **Scenario: import merges onto a device that already has items** — Must
  - Given the device has one item and the zip has a different item
  - When he imports
  - Then both items are in the vault. The import does not wipe the device
- **Scenario: import onto an empty vault** — Must
  - Given the vault has no items
  - When he imports a zip this app exported
  - Then the items and files from the zip are in the vault
- **Scenario: empty export** — Must
  - Given there are no items
  - When he exports
  - Then he gets a zip that imports as an empty vault, or he is told there is nothing to export. He is not shown a success that produces no file and no explanation
- **Scenario: not a vault zip** — Must
  - Given he picks a file this app did not export
  - When he imports it
  - Then the local vault is unchanged and he can see that the file was not imported
- **Scenario: the zip is not a plaintext hole** — Must
  - Given the vault is encrypted at rest (US-35)
  - When he exports
  - Then the zip is not a casually readable copy of the database and files sitting beside the encrypted store. How it is protected is the same at-rest outcome, not a new cipher designed in this story

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Export and import are labeled actions. They are not named Backup or Restore.
- No placeholder path presented as a real file.

## Dependencies and risks

- Works without an OAuth client ID (US-17).
- Conflicting copies of the same item inside a zip and on the device wait on his conflict question. Do not invent a field-level merge.

## Implementation notes

Zip of the vault plus attachments. Not a design spec. Not the Drive sync format.
