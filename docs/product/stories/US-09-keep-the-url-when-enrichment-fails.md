# US-09 Keep the link when the page cannot be fetched

> **Approval status: Draft. Not approved.**

**Epic:** E2.

**MoSCoW:** Must. v1. One scenario is Should.

## Title

Keep the link when the page cannot be fetched.

## User story

**As a** person named Miguel
**I want** the URL to stay saved when the page cannot be fetched
**So that** a failed fetch does not lose the link

## Background

- The item already exists before the fetch.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: retry policy beyond the Should scenario. Topic queueing is US-44.

## Acceptance criteria

- **Scenario: save while offline** — Must
  - Given he saves a URL with no network
  - When the save completes
  - Then the item exists, the source URL is intact, and no preview or readable text is invented
- **Scenario: the server errors or times out** — Must
  - Given the fetch returns an error or times out
  - When enrichment stops
  - Then the item and its URL remain, and he can see that the page details are not available
- **Scenario: a later success can fill gaps** — Should
  - Given an earlier fetch failed and a later fetch succeeds
  - When that later fetch returns details the item does not have
  - Then those gaps fill and text he already has is not overwritten

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- "Page details are not available" is visible. No placeholder sentences.

## Dependencies and risks

- The Should scenario does not block this story.
- A manual snapshot retry is US-33.

## Implementation notes

Failure must not roll back the URL. Not a design spec.
