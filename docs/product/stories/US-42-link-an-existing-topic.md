# US-42 Link an item to a topic the vault already has

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Link an item to a topic the vault already has.

## User story

**As a** person named Miguel
**I want** a saved item to join a topic I already keep, and that topic to gain a dated note
**So that** the subject I already have grows instead of starting over

## Background

- The four privacy questions are US-76 and US-75. This story does not repeat them.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: creating a topic (US-43) and merging two saved topics (US-48).

## Acceptance criteria

- **Scenario: a known topic** — Must
  - Given a topic named "Tax" exists and the result names " tax "
  - When the names are compared after trim, collapsed spaces, NFC, and case folding
  - Then the item is linked, a note is appended under `YYYY-MM-DD`, and the note points at this item
- **Scenario: older notes stay** — Must
  - Given the topic already has notes
  - When the new note is added
  - Then the older notes remain
- **Scenario: more than one known topic** — Must
  - Given the result names two topics that already exist
  - When linking runs
  - Then the item is linked to both
- **Scenario: a different name** — Must
  - Given the name matches no topic
  - When linking runs
  - Then this story does not link and does not create
- **Scenario: undo the link** — Must
  - Given an automatic link just happened
  - When he unlinks that item
  - Then the link is gone and the note this link added is removed

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on a stored result from US-41.

## Implementation notes

Not a design spec.
