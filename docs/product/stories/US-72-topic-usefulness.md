# US-72 Score whether topics are useful

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Could. The score is computed on the phone.

## Title

Score whether topics are useful.

## User story

**As a** person named Miguel
**I want** a local check that scores topic names against a fixture
**So that** usefulness can be judged without me hand-labeling items and without sending counts off the phone

## Background

- Two separate scores: kept-unchanged rate, and match against a fixture. Near-duplicate means two names are equal after the normalisation in US-42, including a trailing singular or plural `s`, or the edit distance is length-relative: at most 1 when the shorter name has fewer than 12 characters, and at most 2 when it has 12 or more. Counts stay on the phone. He is not asked to hand-label.
- The fixture is built in the test source. It is 20 items: 8 notes, 6 links, 4 images, and 2 PDFs. Each title and each body is at least 40 characters. It includes 4 name pairs that are near-duplicates under the rule above and 4 names that are not. It runs as a local unit test. It does not call the cloud and it does not use the vault on the phone.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: analytics upload (US-07).

## Acceptance criteria

- **Scenario: near-duplicates** — Could
  - Given two topic names
  - When the check runs
  - Then they count as near-duplicates if they are equal after US-42 normalisation, including a trailing singular or plural `s`, or if the edit distance is at most 1 when the shorter name has fewer than 12 characters and at most 2 when it has 12 or more
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
- Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31).

## Dependencies and risks

- Does not block US-41.

## Implementation notes

Not a design spec.
