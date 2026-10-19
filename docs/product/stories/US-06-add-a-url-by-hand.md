# US-06 Add a URL by hand

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Add a URL by hand.

## User story

**As a** person named Miguel
**I want** to type a URL inside the vault
**So that** I can keep a page when I am not in the app that showed it

## Background

- A typed URL is a link item. Enrichment is not required for the save.
- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: the page fetch (US-08). A URL typed without a scheme is treated as https so the scenario is testable.

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
