# US-06 Add a URL by hand

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Add a URL by hand.

## User story

**As a** person named Miguel
**I want to** type or paste a URL inside the vault
**So that** I can keep a link when sharing from the other app is awkward

## Background

- He asked for a manual add of a URL as well as share-sheet links.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: the page fetch itself (US-08 and US-09 cover that for any saved link, including this one).

## Acceptance criteria

- **Scenario: save a URL** — Must
  - Given he is in the vault
  - When he enters a URL and confirms
  - Then one item of type `link` is stored with that source URL and a created date
- **Scenario: the text is not a URL** — Must
  - Given he enters text that is not a URL
  - When he tries to save it as a URL
  - Then no link item is stored and he can see that it was not accepted as a URL
- **Scenario: enrichment is not required to save** — Must
  - Given the device has no network
  - When he saves a valid URL
  - Then the link item is still stored with that URL

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The URL field has a visible label and the validation message is text.
- No placeholder text shipped as his content.

## Dependencies and risks

- Blocks nothing that US-01 does not also block. US-08 applies to links from this story too.
- A duplicate URL uses the US-01 recommendation (normalised match, update in place, snackbar). That recommendation is not approval.

## Implementation notes

In-app create path for a link item. Fetching the page is E2. Not a design spec.
