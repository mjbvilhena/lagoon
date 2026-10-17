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
- Out of scope: linking (US-42), creating (US-43), the offline queue mechanics (US-44), and the topic page (US-45). Provider, credential storage, and payload slice stay open for Miguel (US-76). Whether a sensitive item is sent is delegated to his bots (US-75). The cap of 8 suggestions is a draft rule so the scenario is testable. It is not his approval.

## Acceptance criteria

- **Scenario: after the earlier steps** — Must
  - Given an ordinary item is stored and enrichment, snapshot, and OCR have finished or do not apply
  - When the topic job runs
  - Then a cloud call runs, the vault keeps the main ideas and topic names, and the call is not a Drive upload
- **Scenario: it runs again when those steps finish** — Must
  - Given OCR or enrichment finishes later
  - When that text is new
  - Then the topic job runs again for this item
- **Scenario: edit or repeat** — Must
  - Given he edits the item or US-49 updates it
  - When the new version is stored
  - Then the job is queued for that latest version
- **Scenario: a cap** — Must
  - Given the call returns more than 8 topic names
  - When the result is stored
  - Then only 8 are kept as suggestions
- **Scenario: the switch is off** — Must
  - Given he turned the topic call off in Settings
  - When an item is saved
  - Then no call is made
- **Scenario: grounding** — Must
  - Given the call returns a main idea
  - When the result is stored
  - Then that idea includes a quote that appears word for word in the text that was sent, and the result is marked AI-generated
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

- Provenance stored with the result: item id, time, and model or prompt version. Which fields may be sent is US-76.

## Implementation notes

Tests can use a stand-in for the cloud call. Not a design spec.
