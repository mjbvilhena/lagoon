# US-74 Decide how the vault key reaches another device

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Not set. The question is still open.

## Title

Decide how the vault key reaches another device.

## User story

**As a** person named Miguel
**I want** a second device to read the vault, once I have decided how the key gets there
**So that** ciphertext on Drive is not a dead end

## Background

- A lost key means the content is lost, with no recovery (US-35). The zip is encrypted with the vault key (US-21). How a key reaches a new device is still open.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: inventing a Google-derived key or a passphrase here.

## Acceptance criteria

No Must criterion. A Must would decide the question. This artefact fails the user-story Definition of Done until the question is closed or moved.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Open question. Listed in `docs/product/stories/REVIEW-PACK.md`.

## Implementation notes

Not a design spec. Do not implement from this file.
