# US-41 Extract topics when an item is saved

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> Epic E11 is Approved. This story is not.
> Provider, credential storage, sensitive-item exclusion, and payload contents are open design-gate questions for Miguel. This story does not decide them.

## Title

Extract topics when an item is saved.

## User story

**As a** person named Miguel
**I want** the vault to identify the main ideas and topics of something I just saved
**So that** a later step can attach it to a topic without me typing the subject by hand

## Background

- Today a saved item has no topics. E11 says that when he shares or adds something, a cloud AI identifies its main ideas and topics. Readable content may leave the phone for that call. He accepted that. Drive sync stays ciphertext and is not this call.
- Domain constraints: `get_domain_consultant` for capture, vault, and knowledge found no `DOMAIN.md`. No MUST/NEVER rules are implied. Do not invent any.
- Out of scope: linking an existing topic (US-42), creating a new one (US-43), the offline queue (US-44), the topic page (US-45), renaming or merging a suggestion (US-46), and a failed call (US-47). Cloud OCR and a cloud vision API stay out (E7 is on-device). Choosing the provider, where the credential lives, whether a sensitive item is sent, and which bytes are sent.

## Acceptance criteria

- **Scenario: a save can be processed** — Must
  - Given he shares an item into the vault or adds one inside it, the design gate has not forbidden this item, and a topic call can complete
  - When the save has been stored
  - Then a cloud AI call runs for that item and the vault keeps the main ideas and topic names the call returns, tied to that item
  - And the call is not a Drive upload
- **Scenario: both ways of saving** — Must
  - Given a share from another app, and separately an item added inside the vault (a note or a URL)
  - When each save is stored and the call can complete
  - Then each item gets its own result. One save does not reuse another item's result
- **Scenario: the call returns no topics** — Must
  - Given the call succeeds and names no topics and no main ideas
  - When the result is stored
  - Then the item remains, no topic is created, and the outcome is not shown as a failed call
- **Scenario: the key is not in the APK** — Must
  - Given a release APK built for sideload
  - When its packaged files are inspected
  - Then the topic API key is not present as plaintext. How the credential is supplied is still open
- **Scenario: a sensitive item is not decided here** — Must
  - Given an item he has marked sensitive, and he has not answered whether those items are excluded
  - When the item is saved
  - Then the item is stored. This story does not state that a topic call was made, and it does not state that the item was excluded. That answer waits on him

## Analytics and observability (optional)

Not applicable. The brief forbids analytics. A topic call is not a usage event.

## Design and a11y notes

- Any later screen that shows the main ideas uses words he can read. This story does not invent that layout.
- No placeholder sentences in the shipped UI.

## Dependencies and risks

- Depends on a stored item from E1 (US-01 through US-06).
- Open, for Miguel at the design gate, not decided here:
  - Which AI provider processes topics, and what are its data-retention terms?
  - How is the API key or credential stored and protected, with no key baked into the APK in plain form?
  - Are items marked sensitive excluded from cloud processing?
  - What exactly is sent: full text, images, OCR output, or some other slice?
- Text from E2, E7, and the snapshot may be part of what could be sent. What is sent stays the question above.
- No cost cap was set. This story does not invent one.
- A threat model is required later and is not started.

## Implementation notes

Tests can use a stand-in for the cloud call. Do not name a provider or a payload in this story. Not a design spec. No `LAYER.md` exists.
