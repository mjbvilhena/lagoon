# US-47 Keep the item when the topic call fails

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Keep the item when the topic call fails.

## User story

**As a** person named Miguel
**I want** a failed topic call to leave my item in place, and to be able to try again
**So that** a cloud problem does not lose what I saved or invent a topic

## Background

- The call can fail after a save: the network drops, the service returns an error, or the response cannot be read. That is not the same as a successful call that names no topics (US-41).
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: an automatic retry schedule or a cost cap. Neither was set. Offline queueing before any call is US-44.

## Acceptance criteria

- **Scenario: the call fails** — Must
  - Given an item is saved and the topic call fails or the response cannot be read
  - When the failure is recorded
  - Then the item remains as saved, no topic is created, and no partial topic is left from that attempt
- **Scenario: he can see it failed** — Must
  - Given that failure
  - When he looks at the item
  - Then he can see that topics were not identified. The item does not look as if the call succeeded with no topics
- **Scenario: try again** — Must
  - Given a failed attempt
  - When he asks to try again and the next call succeeds
  - Then known topics follow US-42 and unknown names follow US-43
- **Scenario: a second failure** — Must
  - Given he tries again and that call also fails
  - When it fails
  - Then the item still remains, and still no topic is created

## Analytics and observability (optional)

Not applicable. No analytics. A failure is not a usage event.

## Design and a11y notes

- The failure is text, not color alone. A screen reader can announce it.
- This story does not invent the exact sentence. The later UX artefact can word it, as long as failure and "no topics" stay distinct.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on a saved item and on US-41's call. Success paths are US-42 and US-43.
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?
- No retry count was requested. "He asks to try again" is the retry in this story.

## Implementation notes

Record failure locally. Do not create a topic from an error body. Not a design spec.
