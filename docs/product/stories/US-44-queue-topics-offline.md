# US-44 Queue topic processing while offline

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Queue topic processing while offline.

## User story

**As a** person named Miguel
**I want** a save to finish when I have no network, and topics to be processed later
**So that** capturing something never waits on the cloud

## Background

- E11: offline, the item is stored and topic processing is queued until a network exists. E1 says a save queues E11 and does not block the save.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: link enrichment while offline (US-09). The queue is not analytics (US-07). What the later call sends is still open.

## Acceptance criteria

- **Scenario: save with no network** — Must
  - Given the device has no network
  - When he shares or adds an item
  - Then the item is stored, he can read it, and no topic call is made
- **Scenario: the save does not wait** — Must
  - Given he saves while offline
  - When the save completes
  - Then it completes without a topic result, and no topic is created in place of that result
- **Scenario: the queue runs later** — Must
  - Given an item was saved with topic processing queued, and no call has succeeded for it yet
  - When the device can reach the network
  - Then that item is processed: known topics follow US-42 and unknown names follow US-43
  - And the item is not processed twice for the same unchanged save

## Analytics and observability (optional)

Not applicable. No analytics. A queued job is not a usage event.

## Design and a11y notes

- He can tell the item is saved before topics exist. This story does not invent a badge or a sentence of placeholder copy.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on E1 for the save. The later call is US-41. Link and confirm are US-42 and US-43.
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?
- No retry budget is set. A call that fails after the device is online is US-47, not this queue.

## Implementation notes

Queue on the device. Do not block the save on the network. Not a design spec.
