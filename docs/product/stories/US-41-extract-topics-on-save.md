# US-41 Extract topics when an item is saved

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.
> Miguel (`mjbvilhena`) decided the default in chat on 2026-10-17 at 18:05 IST. His words: "Topics on by default, with an off switch". That quote is in `docs/product/amendments/2026-10-17-chat-decisions.md`. It is not story approval. It does not decide sensitive-item exclusion.
> On 2026-10-17 at 20:03 IST he removed the cap of 8 topic suggestions. There is no cap. This is not story approval.

**Epic:** E11. Not an approval.

**MoSCoW:** Must. v1, from approved epic E11.

## Title

Extract topics when an item is saved.

## User story

**As a** person named Miguel
**I want** the vault to identify main ideas and topic names after a save, once the earlier steps have text
**So that** I do not have to type the subject by hand

## Background

- The call is a cloud AI. Readable content may leave the phone. Drive sync stays ciphertext and is not this call. Topics are not tags, and every result is marked as AI-generated.
- Domain constraints: no `DOMAIN.md` for capture, vault, or knowledge. No MUST/NEVER rules. Do not invent any.
- Out of scope: linking (US-42), creating (US-43), the offline queue mechanics (US-44), the pipeline order (US-58), and the topic page (US-45). Provider, credential storage, and payload slice are US-76. There is no cap on topic suggestions. Miguel (`mjbvilhena`) removed the cap of 8 on 2026-10-17 at 20:03 IST. A per-item "Include in topics" opt-in stays proposed on US-75. It goes beyond the 18:05 IST quote and stays open for Miguel.
- The topic switch lives in Settings (US-59). It starts on. He can turn it off. Items saved while it is off are not sent later unless he chooses "Get topics" on that item. That is the named control behind "unless he asks".
- A sensitive item stays out of the call. That exclusion is US-75, from the amendment approved on 2026-10-17 at 15:55 IST. It is not part of the 18:05 IST quote. It still applies when this switch is on.
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
  - Given he has turned the topic switch off in Settings (US-59)
  - When an item is saved
  - Then no call is made, and any topic job already queued for US-44 is cancelled and is never sent
  - And when he later turns the switch on, that saved item is not sent unless he chooses "Get topics" on that item
- **Scenario: a sensitive item** — Must
  - Given the topic switch is on and the item is sensitive
  - When a topic job would run
  - Then no call is made for that item. The exclusion is US-75, from the amendment approved on 2026-10-17 at 15:55 IST. It is not the 18:05 IST quote
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
  - Given the topic switch is on and an OCR retry or enrichment adds text that was not in the earlier call
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

- Which fields may be sent is US-76 and is still open for Miguel.

## Implementation notes

Tests can use a stand-in for the cloud call. Not a design spec.
