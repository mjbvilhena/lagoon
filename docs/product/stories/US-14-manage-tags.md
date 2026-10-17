# US-14 Manage tags

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Manage tags on saved items.

## User story

**As a** person named Miguel
**I want to** add and remove tags and see the tags I use
**So that** I can group what I saved in my own words

## Background

- He asked for tags on items and for tag management.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: merging tags. Tag hierarchies. Sharing tags with anyone else. Renaming is US-29, from the Q4 recommendation, and is not part of this story's Must criteria.

## Acceptance criteria

- **Scenario: add a tag to an item** — Must
  - Given a saved item
  - When he adds a tag and confirms
  - Then the item shows that tag, and the tag appears among the tags he can filter by (US-16)
- **Scenario: remove a tag from an item** — Must
  - Given an item with a tag
  - When he removes that tag from the item
  - Then the item no longer shows it. Other items that still use it keep it.
- **Scenario: remove a tag everywhere** — Must
  - Given a tag that is on two items
  - When he removes the tag itself
  - Then it is gone from both items and from the tag list
- **Scenario: blank tag** — Must
  - Given he tries to add a tag whose name is empty or only whitespace
  - When he confirms
  - Then the tag is not created

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Tag names are his words. The UI does not ship sample tags.
- Tag controls have names, not color alone.

## Dependencies and risks

- Q4 is the bots' consensus, not approval: rename is in v1 and applies to every item. That is US-29. A case-insensitive clash asks for confirmation and then merges. There is no other merge path.
- US-12 also writes tags on one item. The rules above apply there too.

## Implementation notes

Tags are part of the local item record and of FTS (US-15). Not a design spec.
