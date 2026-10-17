# US-41 Extract topics when an item is saved

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

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
- Out of scope: linking (US-42), creating (US-43), the offline queue and the re-run after enrichment (US-44, US-58), and the topic page (US-45). Provider, credential storage, and payload slice stay open for Miguel (US-76). Whether a sensitive item is sent is proposed in US-75 and is not decided here. The cap of 8 suggestions is a draft rule so the scenario is testable. It is not his approval.
- The off switch lives in Settings (US-59) and defaults to off. Items saved while it is off are not sent later unless he asks. That later-send rule is a draft so the scenario is testable. It is not his approval.

## Acceptance criteria

- **Scenario: after the earlier steps** — Must
  - Given an ordinary item is stored and enrichment, snapshot, and OCR have finished or do not apply
  - When the topic job runs
  - Then a cloud call runs, the vault keeps the main ideas and topic names, and the call is not a Drive upload
- **Scenario: a cap** — Must
  - Given the call returns more than 8 topic names
  - When the result is stored
  - Then only 8 are kept as suggestions
- **Scenario: the switch is off** — Must
  - Given the topic switch in Settings (US-59) is off, which is the default
  - When an item is saved
  - Then no call is made
  - And when he later turns the switch on, that saved item is not sent unless he asks for topics on it
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
