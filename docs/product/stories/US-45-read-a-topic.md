# US-45 Read a topic

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Read a topic.

## User story

**As a** person named Miguel
**I want** to open a topic and see its items and a summary
**So that** I can look across what I saved on that subject

## Background

- A topic he already has, or one he confirmed, should be readable as its own page: the items linked to it, and a summary of what those items added.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: editing an item from this page (US-12), search ranking (US-15), and inventing a topic that has no items. Provider, credential, sensitive-item exclusion, and payload stay open and are not answered by this page.

## Acceptance criteria

- **Scenario: open a topic** — Must
  - Given a topic with at least one linked item and notes from those items
  - When he opens the topic
  - Then he sees the topic name, the linked items, and a summary made of those notes
- **Scenario: the summary is the notes** — Must
  - Given the topic has notes from two items, added at different times
  - When he reads the summary
  - Then both are visible and the earlier one is still there
- **Scenario: an item that is not linked** — Must
  - Given another saved item is not linked to this topic
  - When he opens the topic
  - Then that item is not listed
- **Scenario: a deleted item** — Must
  - Given a linked item is deleted and the undo period has ended (US-13)
  - When he opens the topic
  - Then that item is not listed. The notes already written stay

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The topic name is a heading a screen reader can announce. Each linked item has a name he can read.
- Exact layout is for the later UX artefact.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on a topic created or extended by US-42 or US-43.
- Open, for Miguel at the design gate, not decided here. This page does not answer them:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?
- How he reaches the list of topics is the UX artefact. This story is the page once he is there.

## Implementation notes

Read local topic, notes, and items. Not a design spec.
