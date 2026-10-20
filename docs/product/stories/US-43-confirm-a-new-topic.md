# US-43 Confirm a topic before it is created

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Confirm a topic before it is created.

## User story

**As a** person named Miguel
**I want** to be asked before a new topic is created, without a prompt on every share
**So that** a subject I do not want is never created, and one-tap capture stays one tap

## Background

- Suggestions live in one pending place near the Inbox (US-60).
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: linking a name that already matches (US-42).

## Acceptance criteria

- **Scenario: no prompt on the share** — Must
  - Given he shares an item
  - When the save completes
  - Then he is not asked to confirm a topic on that sheet
- **Scenario: a pending suggestion** — Must
  - Given the call names a topic the vault does not have
  - When he opens the pending list
  - Then he sees the name and the quote from US-41
- **Scenario: he confirms** — Must
  - Given a pending suggestion
  - When he confirms it
  - Then the topic is created, marked AI-generated, and the item is linked
- **Scenario: he declines** — Must
  - Given a pending suggestion
  - When he declines it
  - Then no topic is created, and that name is not suggested again for this item
- **Scenario: silence** — Must
  - Given he does not answer
  - When time passes, including across a restart
  - Then no topic is created and the suggestion is still pending
- **Scenario: bulk** — Must
  - Given several pending suggestions
  - When he confirms or declines them together
  - Then each one follows the same confirm or decline rule

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Pending suggestions are vault data. Conflict rules for them are US-73.

## Implementation notes

A pending suggestion is not a topic. Not a design spec.
