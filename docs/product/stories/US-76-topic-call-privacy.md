# US-76 Decide the topic call's provider, credential, and payload

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Not set. Criteria follow from the ADR at the design gate.

## Title

Decide the topic call's provider, credential, and payload.

## User story

**As a** person named Miguel
**I want** the topic call to use a provider, a credential, and a payload once those are decided
**So that** readable content does not leave the phone under a rule nobody chose

## Background

- Criteria follow from the ADR at the design gate: which provider and its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. The APK must not contain the key in plaintext (US-41). A sensitive item is excluded (US-75).
- The query and the fragment of a token-looking URL are not sent (US-79). The rest of the payload follows from that ADR.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: choosing those answers in this story.

## Acceptance criteria

Criteria follow from the ADR at the design gate.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Criteria follow from the ADR at the design gate. Sensitive-item exclusion is US-75.

## Implementation notes

Not a design spec. Do not implement from this file.
