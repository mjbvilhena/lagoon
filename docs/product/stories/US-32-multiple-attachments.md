# US-32 Keep more than one attachment on an item

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His decision: multiple attachments per item are in v1. Not approval.

## Title

Attach more than one file to an item.

## User story

**As a** person named Miguel
**I want** an item to hold more than one image or PDF
**So that** pages that belong together stay on one item

## Background

- He decided multiple attachments per item are in v1.
- A share of a single file still creates or fills an item (US-03, US-04).
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: an image editor, a PDF editor, and a limit he did not set. Do not invent a maximum count.

## Acceptance criteria

- **Scenario: add a second file** — Must
  - Given an item that already has one image or PDF
  - When he adds another image or PDF to that item
  - Then both files are on the item and he can open each of them offline
- **Scenario: remove one file** — Must
  - Given an item with two attachments
  - When he removes one
  - Then the other remains, and the removed file is not left behind as a broken entry
- **Scenario: a single share still works** — Must
  - Given he shares one image
  - When the vault stores it
  - Then he gets an item with that one attachment, as in US-03

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Each attachment has a name a screen reader can use, taken from the file name or the item title. No lorem.
- Layout of several files is for the UX artefact.

## Dependencies and risks

- Sync (US-18) and zip (US-21) include every attachment on the item.
- Encryption (US-35) covers every attachment file.

## Implementation notes

More than one file per item. Not a design spec.
