# US-73 Decide sync conflicts and deletions

> **Approval status: Placeholder. Not approved.**

**Epic:** E4.

**MoSCoW:** Not set. This story has no MoSCoW yet. Criteria follow from the ADR at the design gate.

## Title

Decide sync conflicts and deletions.

## User story

**As a** person named Miguel
**I want** a rule for two edits of one item, and for a deletion, once I have decided it
**So that** two phones do not invent a winner

## Background

- Criteria follow from the ADR at the design gate. Options recorded here, and not chosen in this story, are tombstones, per-field last-writer-wins, and merged notes. "A deleted item must not come back" is the same kind of option.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: one-sided merge, which US-20 already states.

## Acceptance criteria

This story has no Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. It is a placeholder waiting on the design-gate ADR. It is not ready for review.

Criteria follow from the ADR at the design gate.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31).

## Dependencies and risks

- The ADR is accepted. This story stays a placeholder.
- Accepted record: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. Status: Accepted. Approved by mjbvilhena on 2026-10-20. This story stays a placeholder. It does not gain acceptance criteria from that record.

## Implementation notes

Not a design spec. Do not implement from this file.
