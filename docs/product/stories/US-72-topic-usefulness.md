# US-72 Score whether topics are useful

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. Scout asked for this evaluation. It is not a v1 ship gate.

## Title

Score whether topics are useful.

## User story

**As a** person named Miguel
**I want** to hand-label 30 to 50 items and see whether the topics matched
**So that** I can judge the topic feature without sending those counts off the phone

## Background

- Scores: kept-unchanged rate, near-duplicate topics, and traceability (the quote is in the sent text). Counts stay on the phone.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: analytics upload (US-07).

## Acceptance criteria

- **Scenario: the scores** — Should
  - Given he has hand-labeled 30 to 50 items
  - When he opens the evaluation
  - Then he sees the kept-unchanged rate, a near-duplicate count, and how many ideas have a word-for-word quote
- **Scenario: on the phone** — Should
  - Given those counts exist
  - When the network is up
  - Then they are not uploaded

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-41.

## Implementation notes

Not a design spec.
