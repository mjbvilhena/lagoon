# US-29 Rename a tag, and merge when the name exists

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Bots' consensus on Q4. Not Miguel's approval.

## Title

Rename a tag, merging only after he confirms a clash.

## User story

**As a** person named Miguel
**I want to** rename a tag for every item, and to confirm before two tags become one
**So that** I can fix a name without a silent merge

## Background

- Consensus: a rename applies to every item. If the new name already exists, compared without case, he is asked to confirm, and only then the two tags merge. Merging is in v1 only through that path.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a separate merge button, tag hierarchies, merging without confirmation.

## Acceptance criteria

- **Scenario: rename everywhere** — Must
  - Given two items carry the tag "reads" and no tag is already named "reading"
  - When he renames "reads" to "reading"
  - Then both items show "reading", neither shows "reads", and other items are unchanged
- **Scenario: search follows the new name** — Must
  - Given he renamed a tag
  - When he filters or searches for the new name
  - Then those items match, and the old name does not
- **Scenario: blank name** — Must
  - Given he tries to rename a tag to empty text
  - When he confirms
  - Then the rename does not happen and the old tag remains
- **Scenario: name already exists** — Must
  - Given a tag "Trips" exists and another tag is "trips" or "Trips" under a different spelling only by case, or any existing tag whose name matches case-insensitively
  - When he renames one tag to that existing name
  - Then he is asked to confirm before anything merges
  - And if he cancels, both tags stay as they were
  - And if he confirms, every item that had either tag has the surviving name, and the other name is gone
- **Scenario: no other merge** — Must
  - Given two tags with different names
  - When he looks for a merge action that is not this rename
  - Then there is none

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The confirmation is text that names both tags. Confirm and cancel have names.
- No sample tags.

## Dependencies and risks

- Builds on US-14. US-15 and US-16 use the name that remains after a merge.
- "Surviving name" is the name he typed. Do not invent a different canonical form.

## Implementation notes

One rename updates every item with that tag. A confirmed clash rewrites both tags to the typed name. Not a design spec.
