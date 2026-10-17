# US-38 File new captures from an Untagged inbox

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

File new captures from an Untagged inbox.

## User story

**As a** person named Miguel
**I want** untagged items to sit in an inbox until I file them or mark them filed
**So that** new captures do not disappear into the full list before I tag them

## Background

- A note is not a tag. The inbox is a view, not a stored tag.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: multi-select triage (US-71, Could).

## Acceptance criteria

- **Scenario: a new untagged item** — Must
  - Given he saves an item and does not tag it
  - When he opens the inbox
  - Then the item is listed, including when it has no note
- **Scenario: the count** — Must
  - Given three untagged items
  - When the inbox is shown
  - Then the count is 3
- **Scenario: he tags it** — Must
  - Given an inbox item
  - When he adds a tag
  - Then it leaves the inbox
- **Scenario: mark filed** — Must
  - Given an inbox item
  - When he marks it filed
  - Then it leaves the inbox and no tag is added
- **Scenario: empty** — Must
  - Given every item is tagged or marked filed
  - When he opens the inbox
  - Then he sees that the inbox is empty

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Tags are US-14.

## Implementation notes

A query, not a stored Inbox tag. Not a design spec.
