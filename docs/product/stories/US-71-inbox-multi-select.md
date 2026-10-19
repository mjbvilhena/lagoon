# US-71 Triage the inbox in a batch

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Could.

## Title

Triage the inbox in a batch.

## User story

**As a** person named Miguel
**I want** to tag or mark filed several inbox items at once
**So that** filing does not mean opening each row

## Background

- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: US-38, which remains one item at a time.

## Acceptance criteria

- **Scenario: multi-select** — Could
  - Given several inbox items
  - When he selects them and tags them or marks them filed
  - Then each one follows the US-38 result

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-38.

## Implementation notes

Not a design spec.
