# US-41 Extract topics when an item is saved

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Must. v1.

## Title

Extract topics when an item is saved.

## User story

**As a** person named Miguel
**I want** the vault to identify main ideas and topic names after a save, once the earlier steps have text
**So that** I do not have to type the subject by hand

## Background

- The call is a cloud AI. Readable content may leave the phone. Drive sync stays ciphertext and is not this call. Topics are not tags, and every result is marked as AI-generated.
- Domain constraints: no `DOMAIN.md` for capture, vault, or knowledge. No MUST/NEVER rules. Do not invent any.
- Out of scope: linking (US-42), creating (US-43), the offline queue mechanics (US-44), the pipeline order (US-58), and the topic page (US-45). Provider, credential storage, and payload slice are US-76. There is no cap on topic suggestions. There is no per-item topics control in v1. Sensitive items are always excluded, and the Settings switch covers everything else.
- The topic switch lives in Settings (US-59). It starts on. He can turn it off. Items saved while it is off are not sent later unless he chooses "Get topics" on that item. That is the named control behind "unless he asks".
- A sensitive item stays out of the call (US-75). That exclusion still applies when this switch is on.
- The topic job keeps its own "Unmetered only" setting (US-44). Turning topics on does not use the sync preference and does not ignore that setting.

## Acceptance criteria

- **Scenario: after the earlier steps** — Must
  - Given an ordinary item is stored and enrichment, snapshot, and OCR have finished or do not apply
  - When the topic job runs
  - Then a cloud call runs, the vault keeps the main ideas and topic names, and the call is not a Drive upload
- **Scenario: the switch starts on** — Must
  - Given a fresh install and an ordinary item that is not sensitive
  - When the item is saved and the earlier steps have text
  - Then the topic switch is on and a topic call is allowed, subject to the unmetered setting below
- **Scenario: he turns it off** — Must
  - Given the topic switch is on and topic jobs are queued for US-44
  - When he turns the switch off in Settings (US-59)
  - Then every queued US-44 topic job is cancelled and is never sent
- **Scenario: saved while off** — Must
  - Given the topic switch is off
  - When an item is saved
  - Then no call is made
  - And when he later turns the switch on, that saved item is not sent unless he chooses "Get topics" on that item
- **Scenario: Get topics** — Must
  - Given an ordinary item that was saved while the topic switch was off
  - When he chooses "Get topics" on that item (item detail, US-11), including while the switch is still off
  - Then one topic job is queued for that item under US-44, subject to the topic Unmetered-only setting
  - And "Get topics" is not offered on a sensitive item (US-75)
- **Scenario: a sensitive item** — Must
  - Given the topic switch is on and the item is sensitive
  - When a topic job would run
  - Then no call is made for that item (US-75)
- **Scenario: unmetered still applies** — Must
  - Given the topic switch is on and the topic "Unmetered only" setting (US-44) is on
  - When the network is metered
  - Then the call waits. It does not use the sync preference
- **Scenario: provenance** — Must
  - Given a call returns
  - When the result is stored
  - Then the vault records the item id, the fields that were sent, the timestamp, and the model or prompt version
- **Scenario: grounding** — Must
  - Given the call returns a main idea whose quote appears word for word in the text that was sent
  - When the result is stored
  - Then that quote is kept and the result is marked AI-generated
- **Scenario: a quote that is not in the text** — Must
  - Given a returned quote is not in the sent text word for word
  - When the result is stored
  - Then that quote is dropped, or the idea is flagged ungrounded, and it is not shown as a grounded quote
- **Scenario: no topics** — Must
  - Given the call succeeds and names nothing
  - When the result is stored
  - Then the item remains, no topic is created, and the status is "no topics", not "failed"
- **Scenario: text arrives later** — Must
  - Given an ordinary item, the topic switch is on, and an OCR retry or enrichment adds text that was not in the earlier call
  - When that text is stored
  - Then the topic job runs again for that item
- **Scenario: the key is not in the APK** — Must
  - Given a release APK
  - When its packaged files are inspected
  - Then the topic API key is not present as plaintext. How the credential is supplied stays US-76

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Which fields may be sent is US-76. Criteria follow from the ADR at the design gate.

## Implementation notes

Tests can use a stand-in for the cloud call. Not a design spec.
