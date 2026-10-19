# Epic E11: Topics

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-18.

## Background & Value Proposition

A saved item is easier to find again when the vault can name what it is about and attach it to a topic he already keeps. When he shares or adds something, the vault identifies the main ideas and topics. A topic he already has grows. A topic he does not have yet is offered, and it is not created unless he says yes.

No success metric was given.

## Target Audience

Miguel, saving into the vault and later looking across items that share a subject.

## Scope & Phasing

- **In Scope (MVP):**
  - When content is shared into the vault or added inside it, it is extracted and processed to identify its main ideas and topics.
  - If an identified topic already exists, the new item is linked to it and the topic is extended with the new information.
  - If a topic does not exist yet, he is asked whether it should be added. Nothing is created without confirmation.
  - The processor is a cloud AI. Readable content leaves the phone for that call. Topic extraction starts on for a new ordinary item. He can turn it off.
  - Proposed amendment. Needs approval by mjbvilhena. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved.
  - Drive sync stays ciphertext-only. This call is not a Drive upload.
  - Offline, the item is stored and topic processing is queued until a network exists.
  - The topic job has its own "Unmetered only" setting (US-44, US-59). That setting is not the sync preference.
  - No cap on the number of topic suggestions.
  - Opening a topic shows the items linked to it and the notes already stored on that topic (A-E11-1, US-45). Acceptance: he can look across what he saved on that subject. Opening the topic does not start a new AI call.
  - After a topic is saved he can rename it, delete it, unlink an item, or merge it with another saved topic (A-E11-2, US-48). Acceptance: a saved subject can be corrected. A topic that does not exist yet is still created only after he confirms.
  - He has a list of saved topics and one place for suggestions that are waiting (A-E11-3, US-60). Acceptance: he can open a subject and answer a suggestion without a prompt on every save. Nothing is created without confirmation.
  - A usefulness score for topics is computed on the phone (A-E11-4, US-72, Could). Acceptance: he is not asked to hand-label items, and the score is not sent off the phone.
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Cloud OCR or a cloud vision API (E7 stays on-device). Creating a topic without his confirmation. Choosing the provider, the retention terms, the credential store, or the exact payload. Criteria follow from the ADR at the design gate (US-76). There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. The topic job's own "Unmetered only" setting is in scope above (US-44, US-59). That setting is not the sync preference.

## Core Workflows

1. **A known topic**
   - He shares or adds an item.
   - Processing identifies a topic the vault already has.
   - The item is linked to that topic, and the topic gains the new information.
2. **A new topic**
   - Processing identifies a topic the vault does not have.
   - He is asked whether to add it.
   - If he declines, the topic is not created. The item remains.
3. **No network**
   - He can still save the item.
   - Topic processing waits and runs when connectivity allows.

## Risks & Dependencies

- Depends on E1 for an item to process. Text from E2, E7, and the snapshot may be part of what could be sent. What is actually sent is US-76. Criteria follow from the ADR at the design gate.
- Privacy: readable content leaves the device for this call. Provider and retention terms are US-76. Criteria follow from the ADR at the design gate.
- Proposed amendment. Needs approval by mjbvilhena. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved.
- Cost: each call can spend money. No cap is set here.
- A sideloaded APK must not contain the API key in plain form. How the credential is supplied and protected is US-76. Criteria follow from the ADR at the design gate.
- Encryption of the vault at rest and of the Drive copy stays E8. This epic does not weaken that. It is the exception that sends readable content to a cloud AI.
- Threat model is required later and is not started.

## Open Questions

- [ ] Which AI provider, and what are its data-retention terms? US-76. Criteria follow from the ADR at the design gate.
- Proposed amendment. Needs approval by mjbvilhena. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved. The checkbox above is unchanged.
- [ ] How is the API key or credential stored and protected, with no key baked into the APK in plain form? US-76. Criteria follow from the ADR at the design gate.
- [x] Sensitive items are excluded from the topic call.
- [ ] What exactly is sent: full text, images, OCR output, or some other slice? US-76. Criteria follow from the ADR at the design gate.

## Draft stories

A story stays Draft unless its line says Approved. Paths are under `docs/product/stories/`.

- US-41 Extract topics when an item is saved. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- US-42 Link an item to a topic the vault already has
- US-43 Confirm a topic before it is created
- US-44 Queue topic processing while offline
- US-45 Read a topic
- US-46 Reject, rename, or merge a suggested topic
- US-47 Keep the item when the topic call fails
- US-48 Manage a topic that is already saved.
- US-60 Browse topics and pending suggestions
- US-72 Score whether topics are useful. Could.
- US-76 Decide the topic call's provider, credential, and payload. Placeholder. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Criteria follow from the ADR at the design gate.
