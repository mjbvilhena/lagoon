# US-67 Notice a duplicate image or PDF

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Could.

## Title

Notice a duplicate image or PDF.

## User story

**As a** person named Miguel
**I want** the vault to tell me when an image or PDF is already saved
**So that** I do not keep two copies of the same file

## Background

- Domain constraints: a Proposed `docs/domain/vault/DOMAIN.md` is in the tree. It is not a MUST while it is unapproved. Do not invent further rules.
- Out of scope: URL duplicates, which are US-49.

## Acceptance criteria

- **Scenario: same bytes** — Could
  - Given an image or PDF with the same content hash is already stored
  - When he shares it again
  - Then he is offered to add it to the existing item, and refusing still saves a new item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block capture.

## Implementation notes

Not a design spec.
