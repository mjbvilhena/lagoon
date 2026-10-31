# US-58 Run enrichment, snapshot, OCR, and topics in that order

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Run enrichment, snapshot, OCR, and topics in that order.

## User story

**As a** person named Miguel
**I want** background work to run in a fixed order and to re-index when text changes
**So that** search and topics see the text that actually exists

## Background

- Order: the save returns first. Then enrichment for a link. Then the snapshot from that same fetch. Then OCR. Then the topic job.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: the topic payload (US-76).

## Acceptance criteria

- **Scenario: the order** — Must
  - Given he saves a link with an image attachment
  - When each step finishes
  - Then the order is save, enrich, snapshot, OCR, topic, and a later step can start only after the step it needs
- **Scenario: re-index** — Must
  - Given a step adds text
  - When that step finishes
  - Then FTS is updated from that text
- **Scenario: an edit** — Must
  - Given he edits the body or US-49 updates the item
  - When the new version is stored
  - Then FTS is re-indexed and the topic job is queued again. Enrichment and OCR re-run only when their input changed

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31).

## Dependencies and risks

- US-08, US-31, US-33, US-41, and US-44 are the steps.

## Implementation notes

Not a design spec.
