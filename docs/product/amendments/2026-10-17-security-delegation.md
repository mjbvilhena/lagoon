# Amendment, 2026-10-17

> **Status: Approved by `mjbvilhena` on 2026-10-17 at 15:55 IST.**
> His words, after a 4-part walkthrough the same day. Part 1, 15:48 IST: "Yes, that's what I meant". Part 2: "Happy with both". Part 3: "Yes". Final approval at 15:55 IST: "Yes".
> He changed section 7. The vision is rewritten to the current vision. No supersession sentence was added. His words: "Vision document should always reflect the current vision, not an historic vision with some amendments added to it".
> Sections 1–6 are applied to `docs/product/specification.md` and epics E1, E3, E8, and E11. Approved revision of that application: commit `86573af` (`86573afe32a946b761f74c354acc389f2a7bac96`).
> Skill: `sdlc-product-owner`. Template: `get_sdlc_template("product spec")`. Definition of Done: `get_definition_of_done("epic")` and `get_definition_of_done("product spec")`, artefact shape only. Shipped-epic DoD is unmet: nothing is built.
> This approval does not approve any story. Proposed criteria on US-64, US-75, US-78, US-79, US-80, and US-81 stay proposed.

This amendment records where the story revision of commit `40bb526`, plus the round-2 proposals, no longer matched the then-approved text. Each section quotes the text as it stood and the text he approved. Sections 1–6 are now applied.

## 1. Specification design-gate list

File: `docs/product/specification.md`. Not edited.

Current text, in "For Miguel at the design gate":

> These stay open. They are security, data integrity, or release questions. They are not delegated. Miguel answers them at the technical design, ADR, and threat-model gate.

The same list still includes, as open and not delegated:

> **Lost key.** What happens if the key or passphrase is lost?
>
> **App unlock.** Is unlocking the app (biometric or device credential) required? This is separate from the v1 lock on items marked sensitive.
>
> **Sideload verification.** Android developer verification for sideloaded APKs, enforced globally in 2027. ADB installs are exempt. What should this project do about that?
>
> **Sensitive items and the cloud.** Are items marked sensitive excluded from cloud topic processing?

On 2026-10-17 at 14:56 IST Miguel decided lost key and app unlock, and delegated sideload verification and sensitive-item cloud handling to his bots. The "not delegated" sentence now conflicts with that chat.

Proposed text:

> Some design-gate questions are still open. Miguel answers those at the technical design, ADR, and threat-model gate. That gate has not started.
>
> Decided by Miguel on 2026-10-17 at 14:56 IST, not by this amendment: if the vault key is lost, the content is lost, and there is no recovery. App lock is optional. The device PIN is the fallback when there are no biometrics.
>
> Delegated by him the same afternoon, with proposed story criteria still pending his approval: sensitive-item handling (US-75), Drive scope and sign-out (US-78), link-preview skipping (US-79), sharing out (US-64), the permission set (US-80), and sideload policy (US-81).
>
> Still open for him, and not decided by the stories: sync conflicts, how a deletion propagates, how keys reach a new device, the topic AI provider and its retention terms, how the topic credential is supplied, and what the topic call sends.

## 2. Epic E8

File: `docs/product/epics/E8-encryption-and-sensitive-items.md`. Not edited.

Current out-of-scope phrase:

> App-wide unlock, which is still an open question for him.

Current risks sentence:

> Lost key, key transport, and app unlock are his open questions. Stories do not answer them.

Proposed out-of-scope phrase:

> App-wide unlock is optional, per Miguel's decision of 2026-10-17 at 14:56 IST. It is no longer an open question.

Proposed risks sentence:

> Key transport is his open question. Stories do not answer it. On 2026-10-17 at 14:56 IST he decided that a lost key means the content is lost, with no recovery, and that app lock is optional with a device-PIN fallback.

## 3. Epic E3

File: `docs/product/epics/E3-find-and-curate.md`. Not edited.

Current out-of-scope phrase:

> Sharing an item back out to other apps.

Proposed text:

> Sharing an item back out to other apps is proposed as US-64 (Should) and stays out of this epic until Miguel approves this amendment.

## 4. Epic E11

File: `docs/product/epics/E11-topics.md`. Not edited.

Current out-of-scope sentence:

> Choosing the provider, the retention terms, the credential store, whether sensitive items are excluded, or the exact payload. Those are design-gate questions for him.

The epic does not say that the topic call follows the unmetered sync preference.

Proposed text:

> Choosing the provider, the retention terms, the credential store, or the exact payload. Those stay design-gate questions for him. Whether sensitive items are excluded from the topic call is proposed in US-75 and stays out of this epic until he approves that proposal. The topic job has its own "Unmetered only" setting in US-44 and US-59. That setting is not the sync preference, and it is not in this epic until he approves this amendment.

## 5. Epic E1

File: `docs/product/epics/E1-capture-and-keep.md`. Not edited.

Current out-of-scope line does not mention a source domain or a sending app:

> Fetching page details and snapshots (E2). Search polish (E3). Sync (E4). OCR (E7, which is in v1 but not this epic). Editing pixels or PDF pages. A maximum attachment count. He did not set one.

Proposed addition, not a replacement of the rest of that line:

> Source domain and sending app (US-61) are a Should. They are not in this epic until Miguel approves this amendment.

US-61 is marked Should for that reason. The story is not an epic edit.

## 6. Specification line 27, drift

File: `docs/product/specification.md`. Not edited.

Current text:

> v1 is the draft epics E1–E9 and E11. Epic text is not epic approval. E10 is later and is not v1. E11 has no stories yet.

The epics are approved. E11 has drafted stories. Those stories are not approved.

Proposed text:

> v1 is the approved epics E1–E9 and E11. Epic approval is not story approval. E10 is later and is not v1. E11 stories are drafted under `docs/product/stories/` and stay Draft until story approval.

## 7. Vision

File: `docs/product/vision.md`.

He did not accept the proposed supersession sentence. He changed this section during the walkthrough. The vision file is rewritten so it states the current approved vision: the specification, epics E1–E9 and E11, and this amendment. It includes English-only OCR, offline snapshots, multiple attachments, expiry and reminders, the biometric lock on sensitive items with an optional app lock, end-to-end encrypted seamless Drive sync, AI topics, the Light/Dark/System theme, and sharing out as a Should. It does not describe Back up now, Restore, or a last-backup time.

## What this amendment does not decide

Left open for Miguel. Not proposed as closed:

- ML Kit usage or diagnostic telemetry (US-07).

Later than this approval, and not part of it: on 2026-10-17 at 15:59 IST Miguel decided the sensitive lock-screen reminder (US-75) and token-looking previews (US-79). Those decisions are on the stories. This amendment's 15:55 IST text is unchanged. ML Kit telemetry stayed open.
- Topic provider, retention, credential supply, and payload (US-76).
- Sync conflicts and deletion propagation (US-73).
- How the vault key reaches a new device (US-74).
- Exact alarms (US-77). Reminders stay inexact because E9 says so, not because of the 14:56 chat.
- Whether 09:00 should be a fixed zone.
- Full-screen QR display.
