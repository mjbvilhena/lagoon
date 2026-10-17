# US-28 File a capture with last-used tags

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> The bots' consensus refined Q9: one tap, then an optional non-blocking chip row. The earlier blocking quick-add sheet is not the recommendation. Not approval.

## Title

Optionally tag a save from the last-used tags.

## User story

**As a** person named Miguel
**I want** a save to finish in one tap, and then a quiet row of my recent tags
**So that** I can file the item without a form in the way

## Background

- Consensus on Q9: saving is one tap. Afterwards, show an optional, non-blocking chip row of the last-used tags.
- Note and tags can still be edited on the detail screen (US-12).
- Untagged items are listed in US-38.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a modal sheet he must dismiss before the item exists. Manual note and URL forms (US-05, US-06).

## Acceptance criteria

- **Scenario: one tap saves** — Must
  - Given he is sharing a link, plain text, an image, or a PDF
  - When he chooses the vault
  - Then the item is stored without waiting for tags
- **Scenario: chips afterwards** — Must
  - Given he has used tags before
  - When the save completes
  - Then he sees a non-blocking row of those last-used tags and can tap one to add it to the new item
- **Scenario: he ignores the row** — Must
  - Given the row is showing
  - When he does nothing with it
  - Then the item stays saved and, if he added no tag, it stays untagged
- **Scenario: no previous tags** — Must
  - Given he has never used a tag
  - When he saves
  - Then the item is saved and the row is empty or absent, with no sample tags
- **Scenario: later edit** — Must
  - Given the item
  - When he opens it
  - Then he can change the note and tags on the detail screen (US-12)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The row does not block the save or the next thing he does.
- Chips have text names. No placeholder tags.
- How many chips appear is not specified. Do not invent a cap in this story. A UX pass may set one later.

## Dependencies and risks

- Applies to shares in US-01 through US-04. US-38 shows what he did not tag.
- Blank tags are still rejected (US-14).

## Implementation notes

Save, then offer tags. Not a design spec.
