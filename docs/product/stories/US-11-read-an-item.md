# US-11 Read an item

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Read a saved item.

## User story

**As a** person named Miguel
**I want to** open an item and see what was stored
**So that** I can read it again without going back to the app it came from

## Background

- He asked for an item detail view.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: editing (US-12) and delete (US-13).

## Acceptance criteria

- **Scenario: open a note or a link** — Must
  - Given a saved item
  - When he opens it from the list
  - Then he can read its title, body or extracted text, source URL when one exists, type, tags, created date, and personal note when one exists
- **Scenario: open an image or a PDF** — Must
  - Given an image or PDF item
  - When he opens it with no network
  - Then he can see the image or read the stored PDF from the device
- **Scenario: optional fields are empty** — Must
  - Given an item with no source URL, no tags, and no personal note
  - When he opens it
  - Then those absences are quiet. The detail view does not fill them with placeholder sentences.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The URL, when present, is text he can read, not only a tap target.
- Empty optional fields are not labeled with lorem.
- Exact layout is for the UX artefact.

## Dependencies and risks

- Depends on items from E1 and, for enriched text, on E2 when enrichment ran.
- PDF rendering of hostile files is a later threat-model topic. This story does not specify a sandbox design.

## Implementation notes

Detail screen reads the local item and local files. Not a design spec.
