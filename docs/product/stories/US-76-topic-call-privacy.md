# US-76 Decide the topic call's provider, credential, and payload

> **Approval status: Draft. Not approved.**

**Epic:** E11.

**MoSCoW:** Not set. The question is still open.

## Title

Decide the topic call's provider, credential, and payload.

## User story

**As a** person named Miguel
**I want** the topic call to use a provider, a credential, and a payload once those are decided
**So that** readable content does not leave the phone under a rule nobody chose

## Background

- Still open: which provider and its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. The APK must not contain the key in plaintext (US-41). A sensitive item is excluded (US-75).
- The query and the fragment of a token-looking URL are not sent (US-79). The rest of the payload is still open.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: choosing those answers in this revision.

## Acceptance criteria

No Must criterion. A Must would decide the question. This artefact fails the user-story Definition of Done until the question is closed or moved.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The questions above stay open. Sensitive-item exclusion is US-75.

## Implementation notes

Not a design spec. Do not implement from this file.
