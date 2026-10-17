# US-38 See untagged items in an inbox

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Bots' consensus on Q9. Not approval.

## Title

File new captures from an Untagged inbox.

## User story

**As a** person named Miguel
**I want** an Untagged / Inbox view of items that have no tags
**So that** new captures are easy to file

## Background

- Consensus: an Untagged / Inbox view so new captures get filed.
- Inbox here is a view of items with no tags. It is not a tag named Inbox that the app inserts.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: an automatic tag. A second inbox for read items.

## Acceptance criteria

- **Scenario: a new untagged item** — Must
  - Given he saves an item and adds no tag
  - When he opens Untagged / Inbox
  - Then that item is in the view
- **Scenario: he tags it** — Must
  - Given an item in that view
  - When he adds a tag
  - Then it leaves the Untagged / Inbox view and remains in the vault
- **Scenario: empty** — Must
  - Given every item has at least one tag, or the vault is empty
  - When he opens the view
  - Then he sees that nothing is waiting, in real words, with no sample item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The view's name is text: Untagged or Inbox, as the UX artefact settles. Not lorem.
- It is a filter, and a screen reader can tell that it is selected.

## Dependencies and risks

- US-28 is one way to add a tag immediately. This view catches what he skipped.
- Do not create a stored tag called Inbox.

## Implementation notes

A query for items with no tags. Not a design spec.
