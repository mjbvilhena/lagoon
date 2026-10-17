# US-05 Add a note by hand

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Add a note by hand.

## User story

**As a** person named Miguel
**I want to** write a note inside the vault
**So that** I can keep something that did not come from another app's share sheet

## Background

- He asked for a manual add of a note, not only share-sheet capture.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: sharing text in (US-02), checklists, and rich text.

## Acceptance criteria

- **Scenario: save a note** — Must
  - Given he is in the vault
  - When he adds a note with a title and a body and confirms
  - Then one item of type `note` is stored with that title, that body, a created date, no source URL, and whatever personal note and tags he entered, including none
- **Scenario: empty note** — Must
  - Given the title and the body are both empty
  - When he tries to save
  - Then no item is stored and he can see why
- **Scenario: note with no personal "why"** — Must
  - Given he enters a title and a body and leaves the personal note blank
  - When he saves
  - Then the item is stored and the personal note stays empty

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Labels for title, body, and the optional why-it-matters note come from real words, not placeholder lorem. The UX artefact will set the exact strings.
- Errors are text, not color alone.
- No placeholder text shipped as if it were his content.

## Dependencies and risks

- Q9 is about sharing, not this in-app form. The personal note on this form stays optional. Sharing's one tap and tag chips are US-01 and US-28.
- Independent value: a note does not need a share intent.

## Implementation notes

In-app create path. Not a design spec.
