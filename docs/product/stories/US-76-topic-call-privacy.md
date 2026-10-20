# US-76 Decide the topic call's provider, credential, and payload

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Not set. This story has no MoSCoW yet. Criteria follow from the ADR at the design gate.

## Title

Decide the topic call's provider, credential, and payload.

## User story

**As a** person named Miguel
**I want** the topic call to use a provider, a credential, and a payload once those are decided
**So that** readable content does not leave the phone under a rule nobody chose

## Background

- Criteria follow from the ADR at the design gate: which provider and its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. The APK must not contain the key in plaintext (US-41). A sensitive item is excluded (US-75).
- Proposed amendment. Needs approval by mjbvilhena. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Decided by mjbvilhena; this record is Proposed until he approves it. This placeholder still has no acceptance criteria. This is not approved.
- The query and the fragment of a token-looking URL are not sent (US-79). The rest of the payload follows from that ADR.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: choosing those answers in this story.

## Acceptance criteria

This story has no Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. It is a placeholder waiting on the design-gate ADR. It is not ready for review.

Criteria follow from the ADR at the design gate.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The ADR is accepted. This story stays a placeholder. Sensitive-item exclusion is US-75.
- Accepted record: `docs/adr/ADR-0003-topic-call.md`. Status: Accepted. Approved by mjbvilhena on 2026-10-20. This story stays a placeholder. It does not gain acceptance criteria from that record.

## Implementation notes

Not a design spec. Do not implement from this file.
