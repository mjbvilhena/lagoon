# US-07 Use the saved vault with no network and no analytics

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E1 and the specification.

## Title

Use the saved vault with no network and no analytics.

## User story

**As a** person named Miguel
**I want** the vault I already saved to keep working with no network, and to send no analytics
**So that** I can read and search it offline, and the app does not report my use

## Background

- The phone is the primary copy. Analytics, usage telemetry, and a crash-reporting product are out of the specification.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a page fetch while offline (US-09) and the topic call (US-44). Those wait. They do not block reading what is already stored.

## Acceptance criteria

- **Scenario: airplane mode** — Must
  - Given items already exist, including a PDF whose text was extracted, a link with a stored snapshot, and a search match that occurs only in that PDF text
  - When the phone has no network
  - Then he can open the PDF text, open the snapshot, and see that search hit
- **Scenario: a fresh install with no network and no OAuth client ID** — Must
  - Given a new install, no network, and no OAuth client ID
  - When he opens the app
  - Then it does not crash, he can save and read local items, and Settings explains that sync is not configured (US-17)
- **Scenario: no analytics libraries** — Must
  - Given the release classpath and manifest
  - When CI inspects them
  - Then Firebase Analytics, Crashlytics, and any other usage-analytics dependency are absent

## Analytics and observability (optional)

The specification forbids analytics. This story's Must is that absence.

## Design and a11y notes

- Offline use has no extra screen. No placeholder sentences.

## Dependencies and risks

- Search behavior is US-15 and US-50. This story only requires a hit that is already indexed.

## Implementation notes

Test approach: the analytics check is an automated CI rule on dependencies and the manifest, not a hand look. Not a design spec.
