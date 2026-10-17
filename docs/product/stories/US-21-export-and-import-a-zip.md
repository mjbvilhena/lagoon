# US-21 Export and import a zip

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Export and import a zip on the device.

## User story

**As a** person named Miguel
**I want to** export the vault to a zip in device storage and import a zip the app exported
**So that** I have a copy that does not depend on Google Drive

## Background

- He asked for local export/import (zip to device storage) as a fallback.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: importing a zip made by another program. Drive upload of the zip.

## Acceptance criteria

- **Scenario: export** — Must
  - Given the vault has a note and an image or a PDF
  - When he exports
  - Then a zip is written to device storage that he can find, containing the database and those stored files
- **Scenario: import onto an empty vault** — Must
  - Given he has that zip and the local vault has no items
  - When he imports it
  - Then the items and files from the zip are in the vault
- **Scenario: export of an empty vault** — Must
  - Given there are no items
  - When he exports
  - Then he gets a zip that imports as an empty vault, or he is told there is nothing to export. Either is acceptable. He is not shown a success that produces no file and no explanation.
- **Scenario: the file is not a vault zip** — Must
  - Given he picks a file that is not a zip this app exported
  - When he imports it
  - Then the local vault is unchanged and he can see that the file was not imported
- **Scenario: import while local items exist** — Must
  - Given the vault is not empty
  - When he imports
  - Then the same open question as restore applies (Q5). Do not invent a wipe. Until Q5 is answered, do not destroy local-only items as an unspecified side effect.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Export and import are labeled actions. System file pickers keep their platform names.
- No placeholder path presented as a real file.

## Dependencies and risks

- Works without an OAuth client ID (US-17).
- Q5 blocks the non-empty import the same way it blocks restore.
- The packaging format is what US-25's backup-packaging tests cover. The on-disk layout is technical design, not fixed here.

## Implementation notes

Zip of database plus stored files. Not a design spec.
