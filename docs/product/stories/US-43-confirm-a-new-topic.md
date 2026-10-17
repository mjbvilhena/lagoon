# US-43 Confirm a topic before it is created

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Confirm a topic before it is created.

## User story

**As a** person named Miguel
**I want** to be asked before the vault adds a topic it does not already have
**So that** a subject I do not want is never created for me

## Background

- E11: if a topic does not exist yet, he is asked whether it should be added. Nothing is created without confirmation. If he declines, the topic is not created and the item remains.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: linking a topic that already matches (US-42). Rename and merge of a suggestion are US-46. This story is only create-or-not under the name the call returned.

## Acceptance criteria

- **Scenario: he is asked** — Must
  - Given a successful result names a topic the vault does not have, under the US-42 name comparison
  - When processing finishes
  - Then he is asked whether to add that topic, and no topic with that name exists until he answers
- **Scenario: he confirms** — Must
  - Given that question is waiting
  - When he confirms
  - Then the topic is created with that name, the item is linked to it, and the topic's notes start with the main ideas returned for this item
- **Scenario: he declines** — Must
  - Given that question is waiting
  - When he declines
  - Then no topic is created, the item remains saved, and the suggestion is gone
- **Scenario: silence does not create it** — Must
  - Given the question is waiting and he has not answered
  - When he leaves and comes back
  - Then the topic still does not exist

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The question names the suggested topic in text. A screen reader can announce the question and the confirm and decline actions.
- This story does not pick snackbar, sheet, or dialog. That is the later UX artefact.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on US-41 for a result. US-42 handles names that already exist, so those do not ask.
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?

## Implementation notes

A pending suggestion is not a topic. Not a design spec.
