# US-45 Read a topic

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Read a topic.

## User story

**As a** person named Miguel
**I want** to open a topic and see its items and the notes listed together
**So that** I can look across what I saved on that subject

## Background

- The summary is the notes. It is not a new AI call.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: editing an item (US-12) and search ranking (US-50).

## Acceptance criteria

- **Scenario: open a topic** — Must
  - Given a topic has linked items
  - When he opens it from the topic list (US-60)
  - Then he sees the name, the linked items, and each note with its source item, date, and AI label
- **Scenario: the summary is the notes** — Must
  - Given those notes exist
  - When the page is shown
  - Then they are listed together and no new cloud call runs
- **Scenario: an item that is not linked** — Must
  - Given an item is not linked to this topic
  - When he opens the topic
  - Then that item is absent
- **Scenario: a deleted item** — Must
  - Given US-13 hard-deleted the source item
  - When he opens the topic
  - Then the note that came from that item is gone with it
- **Scenario: empty** — Must
  - Given a topic has no items and no notes
  - When he opens it
  - Then he sees the name and that nothing is linked

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- He reaches this page from US-60.

## Implementation notes

Read local data. Not a design spec.
