# US-13 Delete an item and undo it

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Delete an item and undo that delete.

## User story

**As a** person named Miguel
**I want to** delete an item and immediately undo that if I made a mistake
**So that** I can remove something without being afraid of a mis-tap

## Background

- He asked for delete with undo.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a trash folder that keeps items for days. He did not ask for one. How long undo stays available was not specified. This story does not invent a timer.

## Acceptance criteria

- **Scenario: delete** — Must
  - Given a saved item on the list
  - When he deletes it
  - Then it disappears from the list and from search, and an undo action is available
- **Scenario: undo** — Must
  - Given he just deleted an item and has not dismissed the undo action
  - When he undoes
  - Then the item is back, including its personal note, tags, and any stored image or PDF
- **Scenario: undo is not taken** — Must
  - Given he deleted an item
  - When he dismisses the undo action, or leaves that confirmation without undoing
  - Then the item stays deleted, including its stored file
- **Scenario: nothing to delete** — Must
  - Given the list is empty
  - When he looks for a delete action on an item
  - Then there is no item to delete

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Undo is a named action, not a gesture with no alternative.
- The UX artefact chooses snackbar, banner, or dialog. This story does not.
- Delete and undo are available to a screen reader and to a keyboard or switch where the platform provides one.

## Dependencies and risks

- Open: a timeout was not requested. "Dismiss or leave" is the end of undo in this story. If he wants a fixed number of seconds, that is a change to this criterion, not an assumption.
- Stored files must come back on undo. A delete that orphans the file and an undo that loses the file both fail this story.

## Implementation notes

Undo has to restore the row and the file. Not a design spec.
