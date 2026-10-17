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
- What lists, search, notifications, export, the cloud call, and screenshots show for a sensitive item is US-75.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: those display questions (US-75).

## Acceptance criteria

- **Scenario: mark sensitive** — Must
  - Given an ordinary item
  - When he marks it sensitive
  - Then it is stored as sensitive
- **Scenario: open a sensitive item** — Must
  - Given a sensitive item and the device has a biometric enrolled
  - When he opens it on API 30 or newer
  - Then one prompt offers `BIOMETRIC_STRONG` or `DEVICE_CREDENTIAL`, and the body stays hidden until that succeeds
- **Scenario: older APIs** — Must
  - Given a sensitive item, a biometric is enrolled, and the device is API 26, 27, 28, or 29
  - When he opens it
  - Then the prompt is the biometric prompt and it offers "Use PIN"
  - And "Use PIN" opens the device credential, and the body stays hidden until the biometric or that credential succeeds
- **Scenario: no biometric enrolled** — Must
  - Given a sensitive item and no biometric is enrolled, and a screen lock exists
  - When he opens it
  - Then the device PIN, pattern, or password is the prompt
- **Scenario: no screen lock** — Must
  - Given a sensitive item and the device has no screen lock at all
  - When he tries to open it
  - Then the body stays hidden and he sees that a screen lock is required
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
- **Scenario: the toggle** — Must
  - Given Settings (US-59)
  - When he looks for app lock
  - Then the toggle is there, and it is off until he turns it on
- **Scenario: one unlock** — Must
  - Given he has unlocked
  - When he stays in the app, including while a share sheet, a picker, or the biometric prompt is showing
  - Then he is not asked again
  - And when the app goes to the background, the next open prompts again

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Lock-screen text for an ordinary reminder is US-34. Sensitive notification content is US-75.

## Implementation notes

Not a design spec.
