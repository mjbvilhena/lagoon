# US-70 See what is not ready for offline

> **Approval status: Draft. Not approved.**

**Epic:** E2.

**MoSCoW:** Could.

## Title

See what is not ready for offline.

## User story

**As a** person named Miguel
**I want** a check that lists items whose snapshot or OCR is still pending
**So that** I know what will be missing before I go offline

## Background

- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: US-07, which already requires saved content to open offline.

## Acceptance criteria

- **Scenario: the check** — Could
  - Given some snapshots or OCR jobs are pending, failed, or have never run
  - When he opens the check
  - Then those items are listed, and a failed or never-run snapshot offers the retry from US-33

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-07.

## Implementation notes

Not a design spec.
