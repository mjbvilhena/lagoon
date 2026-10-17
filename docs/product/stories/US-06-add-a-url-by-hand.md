# US-06 Add a URL by hand

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E1.

## Title

Add a URL by hand.

## User story

**As a** person named Miguel
**I want** to type a URL inside the vault
**So that** I can keep a page when I am not in the app that showed it

## Background

- A typed URL is a link item. Enrichment is not required for the save.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the page fetch (US-08). The schemeless rule below is a draft so the scenario is testable. It is not Miguel's approval.

## Acceptance criteria

- **Scenario: an http or https URL** — Must
  - Given he types a URL whose scheme is `http` or `https`
  - When he saves
  - Then one link item is stored, the source URL is what he typed, and he sees "Saved to Lagoon" unless US-49 updates an existing item
- **Scenario: no scheme** — Must
  - Given the text has no scheme, no spaces, and at least one dot
  - When he saves
  - Then the vault stores it as `https://` plus that text
- **Scenario: not a URL** — Must
  - Given the text has spaces, or has no dot and no http(s) scheme
  - When he tries to save
  - Then no item is stored and he sees that it was not saved as a URL
- **Scenario: a repeat** — Must
  - Given the typed URL matches an existing link after US-49 normalisation
  - When he saves
  - Then US-49 updates that item and this story does not create a second one

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The rejection message says it was not saved as a URL.
- No placeholder sentences.

## Dependencies and risks

- Repeat behavior is entirely US-49.

## Implementation notes

In-app create path. Fetching the page is E2. Not a design spec.
