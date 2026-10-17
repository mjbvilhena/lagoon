# US-72 Score whether topics are useful

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Could. A dev chore, not a v1 ship gate, and not a task for Miguel to hand-label.

## Title

Score whether topics are useful.

## User story

**As a** person named Miguel
**I want** a local check that scores topic names against a fixture
**So that** usefulness can be judged without me hand-labeling items and without sending counts off the phone

## Background

- Two separate scores: kept-unchanged rate, and match against a fixture. Near-duplicate means two names are equal after the normalisation in US-42, including singular and plural, or their edit distance is 2 or less. Counts stay on the phone. Miguel is not asked to hand-label.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: analytics upload (US-07).

## Acceptance criteria

- **Scenario: near-duplicates** — Could
  - Given two topic names
  - When the check runs
  - Then they count as near-duplicates if they are equal after US-42 normalisation, including singular and plural, or if their edit distance is 2 or less
- **Scenario: the two rates** — Could
  - Given a fixture of items, not a set Miguel must label
  - When the check runs
  - Then the kept-unchanged rate is reported apart from the fixture-match rate
- **Scenario: on the phone** — Could
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
