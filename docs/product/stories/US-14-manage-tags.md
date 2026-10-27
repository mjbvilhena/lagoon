# US-14 Manage tags on saved items

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

Manage tags on saved items.

## User story

**As a** person named Miguel
**I want** to add and remove tags, and see the tags I already use
**So that** I can file items without inventing a second spelling of the same tag

## Background

- Tags are not topics. Topics are AI-generated and are marked as such (US-41).
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: a separate merge action. The only tag merge is the confirmed rename in US-29.

## Acceptance criteria

- **Scenario: add a tag** — Must
  - Given an item
  - When he adds a tag
  - Then the item has that tag and search can match it after re-index
- **Scenario: the same tag in another case** — Must
  - Given the tag "Tax" exists
  - When he types "tax" on an item
  - Then the item uses the existing tag "Tax" and no second tag is created
- **Scenario: autocomplete and counts** — Must
  - Given several tags exist
  - When he opens the tag list or starts typing a tag
  - Then he sees existing tags with their item counts, and typing filters that list
- **Scenario: remove a tag from one item** — Must
  - Given an item has a tag
  - When he removes it from that item
  - Then the other items keep the tag
- **Scenario: remove a tag everywhere** — Must
  - Given a tag is on more than one item
  - When he chooses remove everywhere and confirms
  - Then every item loses that tag, and Undo restores it
  - And if he does not confirm, the tag stays
- **Scenario: blank tag** — Must
  - Given the name is blank
  - When he tries to add it
  - Then no tag is created

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Confirm and Undo for "remove everywhere" are named actions. No placeholder sentences.

## Dependencies and risks

- Rename-and-merge is US-29 only.

## Implementation notes

Not a design spec.
