# Epic E11: Topics

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve any story. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> Added during his section-by-section spec review on 2026-10-17 at about 11:57 IST. His words: "Use a cloud AI for all items; I accept the content leaves the phone." That decision is part of the specification approved by `mjbvilhena` on 2026-10-17.
> Draft stories US-41 through US-47 are in `docs/product/stories/`. They are Draft. They are not approved. Story approval is delegated to Miguel's bots under his delegation of 2026-10-17. Do not implement.
> Amendment `docs/product/amendments/2026-10-17-security-delegation.md`, section 4, applied after his approval.
> Approver: `mjbvilhena`. Date and time: 2026-10-17 15:55 IST.
> His words, after a 4-part walkthrough the same day. Part 1, 15:48 IST: "Yes, that's what I meant". Part 2: "Happy with both". Part 3: "Yes". Final approval at 15:55 IST: "Yes".
> Approved revision of this application: commit `86573af` (`86573afe32a946b761f74c354acc389f2a7bac96`). This record does not change the product text of that revision.
> This amendment approval does not approve the US-75 proposal. Sensitive-item exclusion stays out of this epic until he approves that proposal.

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
  - The topic job has its own "Unmetered only" setting (US-44, US-59). That setting is not the sync preference.
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Cloud OCR or a cloud vision API (E7 stays on-device). Creating a topic without his confirmation. Choosing the provider, the retention terms, the credential store, or the exact payload. Those stay design-gate questions for him. Whether sensitive items are excluded from the topic call is proposed in US-75 and stays out of this epic until he approves that proposal. The topic job has its own "Unmetered only" setting in US-44 and US-59. That setting is not the sync preference, and it is not in this epic until he approves this amendment. He approved this amendment on 2026-10-17 at 15:55 IST, so that setting is in scope above.

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

These are not approved. Paths are under `docs/product/stories/`. Drafting them is not story approval. Miguel's bots approve stories under his delegation of 2026-10-17. He still decides epic-level and security matters.

- US-41 Extract topics when an item is saved
- US-42 Link an item to a topic the vault already has
- US-43 Confirm a topic before it is created
- US-44 Queue topic processing while offline
- US-45 Read a topic
- US-46 Reject, rename, or merge a suggested topic
- US-47 Keep the item when the topic call fails
