# US-44 Queue topic processing while offline

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E11.

## Title

Queue topic processing while offline.

## User story

**As a** person named Miguel
**I want** a save to finish with no network, and the topic job to run later, once
**So that** capturing something never waits on the cloud

## Background

- One WorkManager job per item id. The job uses the same worker family as US-41.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: what the call is allowed to send (US-76).
- Applying a network preference to the topic call is not in approved epic E11. This story gives the topic job its own "Unmetered only" setting. That gap is listed for Miguel in `docs/product/amendments/2026-10-17-security-delegation.md`. The epic file is not edited.

## Acceptance criteria

- **Scenario: save with no network** — Must
  - Given there is no network
  - When he saves
  - Then the item is stored, he can read it, and no topic call is made
- **Scenario: one job** — Must
  - Given the item id already has a queued job
  - When another trigger arrives
  - Then there is still one job for that id
- **Scenario: unmetered** — Must
  - Given the topic setting "Unmetered only" is on
  - When the job is queued
  - Then its constraint is `UNMETERED`, and it does not run on a metered or roaming network
  - And when that setting is off, the constraint is `CONNECTED`
- **Scenario: unchanged** — Must
  - Given the content hash matches a completed call
  - When the job runs
  - Then no second call is made
- **Scenario: deleted or edited while queued** — Must
  - Given the item is deleted before the job runs
  - When the job reaches it
  - Then nothing is sent
  - And if he edited it while it was queued, one call uses the latest version
- **Scenario: two devices** — Must
  - Given both devices queue the same item id and the same content hash
  - When one call completes
  - Then the other does not call again for that hash

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Status values shared with US-41 and US-47: queued, failed, no topics.

## Implementation notes

Do not block the save on the network. Not a design spec.
