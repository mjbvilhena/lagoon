# US-09 Keep the URL when enrichment does not run

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Keep the link when the page cannot be fetched.

## User story

**As a** person named Miguel
**I want** a saved link to remain, with its URL, when the phone is offline or the fetch fails
**So that** a bad network does not throw away the thing I just saved

## Background

- He said enrichment is best effort and must fail gracefully offline, keeping the URL.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: retry policy beyond "the item survives". A retry schedule was not requested.

## Acceptance criteria

- **Scenario: save while offline** — Must
  - Given the device has no network
  - When he shares or types a valid URL
  - Then a `link` item exists with that URL, and the app does not crash or discard the item because enrichment could not start
- **Scenario: the server errors or times out** — Must
  - Given the device has a network and the fetch fails
  - When enrichment ends
  - Then the link item and its URL are still there, empty fields stay empty, and he can see that details were not added
- **Scenario: a later success can fill gaps** — Nice
  - Given a link item that previously failed enrichment
  - When a later fetch succeeds
  - Then empty title, description, preview, and readable text can be filled without removing the URL or his personal note

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The failure is visible as text on the item, not only as a color.
- No placeholder text pretending a description was fetched.

## Dependencies and risks

- Blocked by US-01 or US-06 for the link item.
- The Nice criterion is not required for the story's implementation DoD.
- Timeout length is not specified. Do not invent a number in this story. Technical design can set it later.

## Implementation notes

Failure must not roll back the insert of the URL. Not a design spec.
