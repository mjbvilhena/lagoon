# US-80 Decide the permission set

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Not set. Blocked. Not scheduled until the question it holds is answered.

## Title

Decide the permission set.

## User story

**As a** person named Miguel
**I want** the app to ask only for the permissions my bots confirm
**So that** the install does not request a permission nobody chose

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated permissions to his bots. He did not decide the set. US-34 still asks for `POST_NOTIFICATIONS` when the first reminder is set, because that timing is the recorded specification text. This story does not add or remove permissions.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: inventing a permission list here.

## Acceptance criteria

No Must criterion. A Must would decide the question. This artefact fails the user-story Definition of Done until the question is closed or moved.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Open question in `docs/product/stories/REVIEW-PACK.md`, marked delegated to bots.

## Implementation notes

Not a design spec. Do not implement from this file.
