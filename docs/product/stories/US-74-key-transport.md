# US-74 Decide how the vault key reaches another device

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Not set. This story has no MoSCoW yet. Criteria follow from the ADR at the design gate.

## Title

Decide how the vault key reaches another device.

## User story

**As a** person named Miguel
**I want** a second device to re-create the vault key from the passphrase
**So that** ciphertext on Drive is not a dead end

## Background

- Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase. No copy of the key, wrapped or otherwise, is stored on Drive or anywhere else. Proposed amendment. Needs approval by mjbvilhena. 'Stored on Drive or anywhere else' changes to 'stored anywhere off the phone.' Forgetting the passphrase and losing the device still means the content is lost. The exact method, parameters, and where the non-secret salt lives are settled in the ADR at the design gate.
- The zip is encrypted with the vault key (US-21). Criteria follow from the ADR at the design gate.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: inventing a Google-derived key. Choosing the derivation method, the parameters, or where the non-secret salt lives.
- Design-gate inputs for the US-74 ADR, not criteria of this story: envelope encryption, or a full re-encrypt, when the passphrase changes (US-86). The KDF choice, for example Argon2id, the salt size, and the parameters that travel with every Drive copy and zip, timed on a low-end phone. The minimum passphrase strength and the retry delay. A reset of a forgotten passphrase from a phone that still holds the key.

## Acceptance criteria

This story has no Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. It is a placeholder waiting on the design-gate ADR. It is not ready for review.

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
