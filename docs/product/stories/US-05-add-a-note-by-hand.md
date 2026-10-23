# US-05 Add a note by hand

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Add a note by hand.

## User story

**As a** person named Miguel
**I want** to type a note inside the vault
**So that** I can keep something that did not come from a share

## Background

- Creating a note may fill a title. Editing later is US-12 and refuses an empty title. Those rules do not clash: auto-title runs only when he saves a new note without typing a title.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: changing an existing title to empty (US-12 refuses that).

## Acceptance criteria

- **Scenario: save a note** — Must
  - Given he types a body and a title
  - When he saves
  - Then one note item is stored with that title and body, and he sees "Saved to Lagoon"
- **Scenario: body only** — Must
  - Given he types a body and leaves the title empty
  - When he saves
  - Then the title follows the US-02 rule: the first line, at most 120 characters, and the full body stays in the body
- **Scenario: empty note** — Must
  - Given the body is empty
  - When he tries to save
  - Then no item is stored and he sees that the note was not saved
- **Scenario: no personal why** — Must
  - Given he does not fill "why it matters"
  - When he saves a note that has a body
  - Then the item is stored and that field stays empty

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The empty-note message is specific. No placeholder sentences.

## Dependencies and risks

- Topic processing after the save is US-41, in the order US-58 defines.

## Implementation notes

In-app create path. Not a design spec.
