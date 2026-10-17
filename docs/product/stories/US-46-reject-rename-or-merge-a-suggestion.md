# US-46 Reject, rename, or merge a suggested topic

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Reject, rename, or merge a suggested topic.

## User story

**As a** person named Miguel
**I want** to reject a suggested topic, rename it, or fold it into one I already have
**So that** the subjects stay the ones I mean

## Background

- A suggestion is not a topic until he confirms it.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: merging two topics that are already saved (US-48). That is not a Must of this story.

## Acceptance criteria

- **Scenario: reject** — Must
  - Given a pending suggestion
  - When he rejects it
  - Then no topic is created
- **Scenario: rename, then confirm** — Must
  - Given a pending suggestion
  - When he renames it and confirms
  - Then the topic is created under his name, marked AI-generated, and the item is linked
- **Scenario: the new name already exists** — Must
  - Given he renames the suggestion to a topic that already exists
  - When he confirms
  - Then the item links to that topic, no second topic is created, and the existing topic's name stays

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Decline-the-same-name is US-43.

## Implementation notes

Not a design spec.
