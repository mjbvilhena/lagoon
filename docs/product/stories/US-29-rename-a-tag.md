# US-29 Rename a tag, merging only after he confirms a clash

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E3.

## Title

Rename a tag, merging only after he confirms a clash.

## User story

**As a** person named Miguel
**I want** to rename a tag everywhere, and to merge only when the new name already exists
**So that** I do not have to retag each item, and a clash does not happen by accident

## Background

- A case-only rename sets the display name. It does not merge and it does not ask.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a separate merge button. This confirmed rename is the only tag merge.

## Acceptance criteria

- **Scenario: rename everywhere** — Must
  - Given a tag is on several items
  - When he renames it to a name that does not already exist
  - Then every item shows the new name
- **Scenario: case only** — Must
  - Given the tag is "Tax"
  - When he renames it to "tax"
  - Then the display name becomes the letters he typed, no merge is offered, and the items still carry that one tag
- **Scenario: clash** — Must
  - Given the tag "Bills" is being renamed to "receipts" while a different tag "Receipts" already exists
  - When the clash is shown
  - Then he sees both names, and the name he typed is the one that survives if he confirms
  - And if he confirms, the items carry one tag
  - And if he does not confirm, nothing changes
- **Scenario: blank name** — Must
  - Given the new name is blank
  - When he tries to rename
  - Then the tag is unchanged
- **Scenario: search follows** — Must
  - Given he renamed a tag
  - When he searches the new name
  - Then the items match, and the old name does not

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on US-14 and US-15.

## Implementation notes

Not a design spec.
