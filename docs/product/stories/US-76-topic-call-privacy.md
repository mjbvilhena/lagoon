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
- Proposed amendment. Needs approval by mjbvilhena. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. The backlog when a key is added stays Decision pending. This placeholder still has no acceptance criteria. This is not approved.
- The query and the fragment of a token-looking URL are not sent (US-79). The rest of the payload follows from that ADR.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
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

- Criteria follow from the ADR at the design gate. Sensitive-item exclusion is US-75.
- Proposed record: `docs/adr/ADR-0003-topic-call.md`. Status: Proposed. Needs approval by mjbvilhena. This story stays a placeholder. It does not gain acceptance criteria from that record.

## Implementation notes

Not a design spec. Do not implement from this file.
