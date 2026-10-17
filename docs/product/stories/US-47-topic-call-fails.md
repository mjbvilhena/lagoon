# US-47 Keep the item when the topic call fails

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Keep the item when the topic call fails.

## User story

**As a** person named Miguel
**I want** a failed topic call to leave the item in place, with a status I can retry
**So that** a cloud problem does not lose what I saved or invent a topic

## Background

- Status values are shared with US-41 and US-44: queued, failed, no topics.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: an automatic retry schedule and a cost cap. Neither was set.

## Acceptance criteria

- **Scenario: the call fails** — Must
  - Given the call errors
  - When the item is still stored
  - Then the item remains, no topic is created, and the status is failed
- **Scenario: retryable** — Must
  - Given the failure is a timeout or an HTTP 429 or 5xx
  - When he looks at the item
  - Then he sees "Try again"
- **Scenario: not retryable yet** — Must
  - Given the failure is HTTP 401 or 403
  - When he looks at the item
  - Then he sees that the call is blocked, and "Try again" is not offered until the credential state changes. How the credential is stored is US-76
- **Scenario: try again** — Must
  - Given the status is failed and retryable
  - When he chooses "Try again" and the call succeeds
  - Then US-42 or US-43 applies
- **Scenario: a second failure** — Must
  - Given he tried again and it failed
  - When the result is stored
  - Then the item remains and no topic is created
- **Scenario: empty is not a failure** — Must
  - Given the call succeeded with no topics
  - When the status is shown
  - Then it says no topics, not failed

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Credential supply is US-76.

## Implementation notes

Do not create a topic from an error body. Not a design spec.
