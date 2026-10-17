# US-48 Manage a topic that is already saved

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1. Needed so E11 topics can be renamed, removed, unlinked, or merged after they exist.

## Title

Manage a topic that is already saved.

## User story

**As a** person named Miguel
**I want** to rename, delete, unlink, or merge a topic that is already saved
**So that** I can correct a subject after I have confirmed it

## Background

- This is not the suggestion flow (US-46). Topics are not tags.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: creating the topic (US-43).

## Acceptance criteria

- **Scenario: rename** — Must
  - Given a saved topic
  - When he renames it
  - Then the topic, its notes, and its links use the new name
- **Scenario: delete** — Must
  - Given a saved topic
  - When he deletes it
  - Then the topic and its links are gone and the items remain
- **Scenario: unlink** — Must
  - Given an item is linked
  - When he unlinks it
  - Then the link is gone and the note from that item is removed. The topic stays
- **Scenario: merge** — Must
  - Given two saved topics
  - When he confirms a merge into one name
  - Then the items and notes are on the kept topic, and the other topic is gone

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Added after the review at `9e7de63`. It was not in that review.

## Implementation notes

Not a design spec.
