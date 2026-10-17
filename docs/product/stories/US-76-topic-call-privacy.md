# US-76 Decide the topic call's provider, credential, and payload

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E11. Not an approval.

**MoSCoW:** Not set. Blocked. Not scheduled until the question it holds is answered.

## Title

Decide the topic call's provider, credential, and payload.

## User story

**As a** person named Miguel
**I want** the topic call to use a provider, a credential, and a payload once those are decided
**So that** readable content does not leave the phone under a rule nobody chose

## Background

- Still open for Miguel: which provider and its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. The APK must not contain the key in plaintext (US-41). Whether a sensitive item is excluded is US-75, from the 15:55 IST amendment.
- A proposed constraint from US-79, not a decision of the rest of the payload: the query and the fragment of a token-looking URL are not sent.
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

- Open questions for Miguel. Sensitive-item exclusion is US-75.

## Implementation notes

Not a design spec. Do not implement from this file.
