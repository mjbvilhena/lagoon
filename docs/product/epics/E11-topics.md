# Epic E11: Topics

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Added during his section-by-section spec review on 2026-10-17 at about 11:57 IST. His words: "Use a cloud AI for all items; I accept the content leaves the phone." That decision is not approval of this epic or of the specification. Shipped-epic DoD is not claimed.
> No user stories yet. Stories come after the specification gate and the epic gate. Story approval is delegated to Miguel's bots. Do not write the stories in place of those gates.

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
  - The processor is a cloud AI, for every item, under his recorded decision. Readable content leaves the phone for that call.
  - Drive sync stays ciphertext-only. This call is not a Drive upload.
  - Offline, the item is stored and topic processing is queued until a network exists.
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Cloud OCR or a cloud vision API (E7 stays on-device). Creating a topic without his confirmation. Choosing the provider, the retention terms, the credential store, whether sensitive items are excluded, or the exact payload. Those are design-gate questions for him. Writing user stories before the spec and epic gates.

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

- Depends on E1 for an item to process. Text from E2, E7, and the snapshot may be part of what could be sent. What is actually sent is an open question for him.
- Privacy: readable content leaves the device. He has accepted that for this epic. Provider and retention terms are not chosen.
- Cost: each call can spend money. No cap is set here.
- A sideloaded APK must not contain the API key in plain form. How the credential is supplied and protected is open.
- Encryption of the vault at rest and of the Drive copy stays E8. This epic does not weaken that. It is the exception that sends readable content to a cloud AI.
- Threat model is required later and is not started.

## Open Questions

- [ ] Which AI provider, and what are its data-retention terms? (@mjbvilhena, design gate)
- [ ] How is the API key or credential stored and protected, with no key baked into the APK in plain form? (@mjbvilhena, design gate)
- [ ] Are items marked sensitive excluded from cloud processing? (@mjbvilhena, design gate)
- [ ] What exactly is sent: full text, images, OCR output, or some other slice? (@mjbvilhena, design gate)

## Draft stories

None. Do not add them until the specification is approved and this epic is approved. After that, Miguel's bots approve stories under his delegation of 2026-10-17. He still decides epic-level and security matters.
