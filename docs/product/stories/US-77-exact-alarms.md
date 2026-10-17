# US-77 Keep reminders on inexact alarms

> **Approval status: Draft. Not approved.**

**Epic:** E9.

**MoSCoW:** Must. Alarms are inexact.

## Title

Keep reminders on inexact alarms.

## User story

**As a** person named Miguel
**I want** reminders and expiries to use inexact alarms
**So that** Lagoon never asks for an exact-alarm permission

## Background

- E9 and the specification say inexact alarms.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a later change that would amend E9. None is open now.

## Acceptance criteria

- **Scenario: the alarm is inexact** — Must
  - Given a reminder or an expiry is registered
  - When the alarm is set
  - Then it uses `setWindow` or `setAndAllowWhileIdle`
- **Scenario: no exact-alarm permission** — Must
  - Given the merged release manifest
  - When its permissions are listed
  - Then `SCHEDULE_EXACT_ALARM` and `USE_EXACT_ALARM` are absent

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The US-80 allowlist omits exact-alarm permissions. That allowlist is unchanged.

## Implementation notes

Not a design spec. Status stays Draft.
