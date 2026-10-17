# US-36 Sync only ciphertext

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.

**Epic:** E8. Not an approval.

**MoSCoW:** Must. v1, from approved epic E8.

## Title

Sync only ciphertext.

## User story

**As a** person named Miguel
**I want** Drive to receive ciphertext only
**So that** Google cannot read the vault

## Background

- He decided client-side encryption of everything synced. The zip rule is US-21, which he decided on 2026-10-17 at 14:56 IST is encrypted with the vault key.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: key transport (US-74). What a sensitive item reveals in an export is US-75. The topic call is not a Drive upload (US-41).

## Acceptance criteria

- **Scenario: the upload is ciphertext** — Must
  - Given a seeded sentinel is in an item
  - When the sync payload is built
  - Then the payload does not contain the sentinel and does not contain the header `SQLite format 3`
- **Scenario: Drive is not the key holder** — Must
  - Given the payload is uploaded
  - When Drive is inspected
  - Then the vault key is not in the uploaded bytes
- **Scenario: Drive names stay opaque** — Must
  - Given an item has a title and tags
  - When the Drive file is created
  - Then the Drive file name and its metadata carry no title and no tag. They use an opaque id

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Second-device readability waits on US-74. This story does not derive a key from the Google account.

## Implementation notes

Encrypt before upload. Do not specify the cipher. Not a design spec.
