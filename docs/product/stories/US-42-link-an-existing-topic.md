# US-42 Link an item to a topic the vault already has

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Link an item to a topic the vault already has.

## User story

**As a** person named Miguel
**I want** a saved item to join a topic I already keep, and that topic to gain what is new
**So that** the subject I already have grows instead of starting over

## Background

- E11: if an identified topic already exists, the item is linked to it and the topic is extended with the new information. He is not asked first for a topic that already exists.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: creating a topic that does not exist (US-43), renaming a suggestion (US-46), and merging two topics that are already saved. Fuzzy or "close enough" matching is not invented here.

## Acceptance criteria

- **Scenario: a known topic** — Must
  - Given the vault already has a topic, and a successful topic result for a new item names that topic
  - When the names are the same, compared case-insensitively, with no other folding
  - Then the item is linked to that topic, and the topic gains a dated note of the main ideas returned for this item
  - And he is not asked to confirm that link
- **Scenario: older notes stay** — Must
  - Given that topic already has notes from earlier items
  - When this item is linked
  - Then the earlier notes are still there. The new note is added. It does not replace them
- **Scenario: more than one known topic** — Must
  - Given the result names two topics that both already exist
  - When processing finishes
  - Then the item is linked to each of them, and each topic gains its own dated note
- **Scenario: a different name** — Must
  - Given the result names a topic whose name is not an existing topic under that comparison
  - When processing finishes
  - Then this story does not link it and does not create a topic

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The link is something he can find later on the topic page (US-45). This story does not draw that page.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on a stored result from US-41. The result's provider and payload are still open.
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?
- "The same name" is only the case-insensitive comparison above. A smarter match would be a change to this story, not an assumption.

## Implementation notes

Likely a link from item to topic plus an appended note. Not a design spec.
