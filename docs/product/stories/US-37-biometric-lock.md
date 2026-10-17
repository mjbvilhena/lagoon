# US-37 Lock sensitive items, and optionally lock the app

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E8, plus Miguel's app-lock decision of 2026-10-17 14:56 IST.

## Title

Lock sensitive items, and optionally lock the app.

## User story

**As a** person named Miguel
**I want** a sensitive item to require a biometric, and an optional app lock that falls back to the device PIN
**So that** private items stay closed, and I can still lock the app when the phone has no biometric

## Background

- On 2026-10-17 at 14:56 IST, Miguel (`mjbvilhena`) decided that app lock is optional, and that the device PIN is the fallback when there are no biometrics.
- What lists, search, notifications, export, the cloud call, and screenshots show for a sensitive item is delegated to his bots. This story does not decide that.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: those delegated display questions (US-75).

## Acceptance criteria

- **Scenario: mark sensitive** — Must
  - Given an ordinary item
  - When he marks it sensitive
  - Then it is stored as sensitive
- **Scenario: open a sensitive item** — Must
  - Given a sensitive item and the device has a biometric
  - When he opens it
  - Then `androidx.biometric` prompts him, and the body stays hidden until that succeeds
- **Scenario: fail or cancel** — Must
  - Given the prompt is showing
  - When he cancels or fails
  - Then the body, attachments, and OCR text stay hidden
- **Scenario: ordinary items** — Must
  - Given an item is not sensitive and app lock is off
  - When he opens it
  - Then no prompt is shown
- **Scenario: app lock is optional** — Must
  - Given a fresh install
  - When he has not turned app lock on
  - Then the app opens without an app-level prompt
- **Scenario: no biometric** — Must
  - Given he has turned app lock on and the device has no biometric
  - When the lock is required
  - Then the device PIN is the prompt
- **Scenario: re-lock** — Must
  - Given app lock is on and he had unlocked
  - When the app goes to the background
  - Then the next open prompts again

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Lock-screen text for an ordinary reminder is US-34. Sensitive notification content is US-75.

## Implementation notes

Not a design spec.
