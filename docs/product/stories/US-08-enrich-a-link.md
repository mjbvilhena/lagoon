# US-08 Enrich a saved link from the page

> **Approval status: Draft. Not approved.**

**Epic:** E2.

**MoSCoW:** Must. v1.

## Title

Enrich a saved link from the page.

## User story

**As a** person named Miguel
**I want** a saved link to gain the page's title, description, preview, and readable text when a fetch works
**So that** I can recognise the page later without opening it

## Background

- The save in US-01 or US-06 already finished. This fetch does not block that save.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: the offline snapshot file (US-33) except that its readable text comes from this same fetch. A failed fetch is US-09. Which fetches are skipped is US-79.

## Acceptance criteria

- **Scenario: the page exposes all four** — Must
  - Given a stored link and a fetch that returns a title, a description, a preview image, and readable text
  - When enrichment finishes
  - Then the item shows those four, and the readable text is stored once
- **Scenario: the same fetch as the snapshot** — Must
  - Given US-33 also runs for this link
  - When both finish
  - Then they used one fetch, and the readable text is indexed once, not as two copies
- **Scenario: the page exposes only some of them** — Must
  - Given the fetch returns only a subset
  - When enrichment finishes
  - Then the item gains that subset and the missing ones stay empty
- **Scenario: he already typed a title** — Must
  - Given the item title was typed by him
  - When the page title arrives
  - Then his title stays and the page title is not written over it
- **Scenario: the quick-add row** — Must
  - Given he is still on the quick-add row
  - When enrichment would start
  - Then it waits until that row has closed
- **Scenario: a token-looking link** — Must
  - Given the link is token-looking under US-79 and he has not confirmed that fetch
  - When enrichment would run
  - Then no fetch is made
  - And when he says yes, one fetch runs
- **Scenario: a sensitive item** — Must
  - Given the item is marked sensitive
  - When enrichment would run
  - Then no fetch is made
- **Scenario: the host rules** — Must
  - Given the link fails a US-79 host rule
  - When enrichment would run
  - Then no fetch is made

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Empty optional fields are not labeled with placeholder sentences.

## Dependencies and risks

- Pipeline order is US-58. Search indexing of the new text is US-15.

## Implementation notes

Best-effort HTTP fetch. Not a design spec.
