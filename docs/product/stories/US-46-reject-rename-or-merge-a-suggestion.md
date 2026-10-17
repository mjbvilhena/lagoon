# US-46 Reject, rename, or merge a suggested topic

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Reject, rename, or merge a suggested topic.

## User story

**As a** person named Miguel
**I want** to reject a suggested topic, rename it, or fold it into one I already have
**So that** the vault's subjects stay the ones I mean

## Background

- US-43 lets him confirm or decline the name the call returned. This story is what he can do with that suggestion besides accepting it unchanged: reject it, rename it and then confirm, or merge it into a topic that already exists.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: merging two topics that are already saved, a tag merge (US-29), and fuzzy matching. A suggestion is not a topic until he confirms it.

## Acceptance criteria

- **Scenario: reject** — Must
  - Given a suggestion is waiting for a topic the vault does not have
  - When he rejects it
  - Then no topic is created, the item remains, and that suggestion is gone
- **Scenario: rename, then confirm** — Must
  - Given a suggestion is waiting
  - When he changes the suggested name and confirms
  - Then the created topic uses his name, not the name the call returned, and the item is linked to it
  - And if his name matches an existing topic under the US-42 comparison, this story does not create a second topic. That case is the merge below
- **Scenario: merge into an existing topic** — Must
  - Given a suggestion is waiting and the vault already has another topic
  - When he merges the suggestion into that topic
  - Then no new topic is created, the item is linked to the existing topic, and that topic gains a dated note of the main ideas for this item
  - And the existing topic's name is unchanged
- **Scenario: two saved topics are not merged here** — Must
  - Given two topics that both already exist, and no suggestion is waiting
  - When he is looking at those topics
  - Then this story does not offer a way to merge them

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Reject, rename, and merge are named actions, not gestures with no alternative.
- A screen reader can announce the suggestion and those actions.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on a waiting suggestion from US-43. The link rules match US-42.
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?

## Implementation notes

Edit or dismiss a pending suggestion. Do not merge saved topics in this story. Not a design spec.
