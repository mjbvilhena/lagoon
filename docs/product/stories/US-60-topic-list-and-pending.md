# US-60 Browse topics and pending suggestions

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1. E11 needs a way to open a topic and a place for suggestions.

## Title

Browse topics and pending suggestions.

## User story

**As a** person named Miguel
**I want** a list of topics and one pending-suggestions place near the Inbox
**So that** I can open a subject and answer suggestions without a prompt on every save

## Background

- Confirm and decline rules are US-43.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: inventing a topic that has no items.

## Acceptance criteria

- **Scenario: the list** — Must
  - Given topics exist
  - When he opens the topic list
  - Then each row shows the name and the linked-item count, and the name is marked as AI-generated when the topic was created from a suggestion
- **Scenario: pending** — Must
  - Given suggestions are pending
  - When he opens the place next to the Inbox
  - Then he sees them, and he can confirm or decline one or many (US-43)
- **Scenario: empty** — Must
  - Given there are no topics
  - When he opens the list
  - Then he sees that there are no topics

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-45 opens from a row.

## Implementation notes

Not a design spec.
