# US-74 Decide how the vault key reaches another device

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Not set. Criteria follow from the ADR at the design gate.

## Title

Decide how the vault key reaches another device.

## User story

**As a** person named Miguel
**I want** a second device to read the vault, once I have decided how the key gets there
**So that** ciphertext on Drive is not a dead end

## Background

- A lost key means the content is lost, with no recovery (US-35). The zip is encrypted with the vault key (US-21). Criteria follow from the ADR at the design gate.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: inventing a Google-derived key or a passphrase here.

## Acceptance criteria

Criteria follow from the ADR at the design gate.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Criteria follow from the ADR at the design gate.

## Implementation notes

Not a design spec. Do not implement from this file.
