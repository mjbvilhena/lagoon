# US-12 Edit an item, including why it matters

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Edit a saved item.

## User story

**As a** person named Miguel
**I want to** change an item's title, body, tags, and the note about why it matters
**So that** I can correct what was captured and record why I kept it

## Background

- He asked to edit an item, and for an optional personal note on why it matters.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: replacing the image or PDF bytes, and changing the created date.

## Acceptance criteria

- **Scenario: edit the personal note and title** — Must
  - Given a saved item
  - When he changes the title and writes a personal note and confirms
  - Then the item shows the new title and the new personal note, and the created date is unchanged
- **Scenario: clear the personal note** — Must
  - Given an item that has a personal note
  - When he clears it and confirms
  - Then the personal note is empty and the rest of the item remains
- **Scenario: edit with an empty title** — Must
  - Given he clears the title and leaves no title
  - When he tries to save the edit
  - Then the change is not stored and he can see why

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The personal note field is labeled as why it matters, in words the UX artefact will finalize. Not lorem.
- Validation is text, not color alone.

## Dependencies and risks

- Tags attached here are the same tags US-14 manages.
- Q9 consensus, not approval: the personal note and tags can be edited on this detail screen at any time after save. The tag chips (US-28) do not replace this. A reminder date is US-34. Marking sensitive is US-37.

## Implementation notes

Update the local row. Do not change `createdAt` on edit. Not a design spec.
