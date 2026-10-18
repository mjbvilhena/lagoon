# US-73 Decide sync conflicts and deletions

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Not set. Criteria follow from the ADR at the design gate.

## Title

Decide sync conflicts and deletions.

## User story

**As a** person named Miguel
**I want** a rule for two edits of one item, and for a deletion, once I have decided it
**So that** two phones do not invent a winner

## Background

- Criteria follow from the ADR at the design gate. Options recorded here, and not chosen in this story, are tombstones, per-field last-writer-wins, and merged notes. "A deleted item must not come back" is the same kind of option.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: one-sided merge, which US-20 already states.

## Acceptance criteria

Criteria follow from the ADR at the design gate.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Criteria follow from the ADR at the design gate.

## Implementation notes

Not a design spec. Do not implement from this file.
