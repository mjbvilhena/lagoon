# US-02 Share plain text into the vault

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Share plain text into the vault.

## User story

**As a** person named Miguel
**I want to** share a passage of plain text from another app
**So that** words I want to keep become a note in the vault

## Background

- He asked to receive plain text from the share sheet, separate from links.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: links (US-01), images, PDFs, and rich-text editing.

## Acceptance criteria

- **Scenario: share text** — Must
  - Given he selects plain text in another app
  - When he shares it to the vault
  - Then one item of type `note` is stored, the text is the body, the created date is set, the source URL is empty, and the personal note and tags are empty unless he supplied them
- **Scenario: share text that is only whitespace** — Must
  - Given the shared text has no non-whitespace characters
  - When the vault receives it
  - Then no item is stored and he can see that nothing was saved
- **Scenario: a title is not part of the share** — Must
  - Given the shared text has a body and no separate title
  - When the item is stored
  - Then the item still has a title he can read, taken from the start of the text, and the full text remains the body

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder text in the shipped UI.
- How much of the passage becomes the title is a display rule for the UX artefact. This story only requires that a title exists and the full body is kept.

## Dependencies and risks

- Independent of link enrichment.
- Q9 applies: note and tags are optional at capture.

## Implementation notes

Share intent for `text/plain` when the payload is not a URL. Not a design spec.
