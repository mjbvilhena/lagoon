# US-73 Decide sync conflicts and deletions

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E4. Not an approval.

**MoSCoW:** Not set. Blocked. Not scheduled until the question it holds is answered.

## Title

Decide sync conflicts and deletions.

## User story

**As a** person named Miguel
**I want** a rule for two edits of one item, and for a deletion, once I have decided it
**So that** two phones do not invent a winner

## Background

- The approved specification leaves both questions open for Miguel. Scout's option is recorded and not chosen: tombstones, per-field last-writer-wins, and merged notes. "A deleted item must not come back" is the same kind of option. It is not a decision.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: one-sided merge, which US-20 already states.

## Acceptance criteria

No Must criterion. A Must would decide the question. This artefact fails the user-story Definition of Done until the question is closed or moved.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Open question for Miguel in `docs/product/stories/REVIEW-PACK.md`.

## Implementation notes

Not a design spec. Do not implement from this file.
