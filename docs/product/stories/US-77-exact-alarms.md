# US-77 Keep reminders on inexact alarms

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.
> Miguel (`mjbvilhena`) closed this question on 2026-10-17 at 20:03 IST. Alarms are inexact. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`. This is not story approval.

**Epic:** E9. Not an approval.

**MoSCoW:** Must. The question is closed by his 20:03 IST decision and by E9. Not a story approval.

## Title

Keep reminders on inexact alarms.

## User story

**As a** person named Miguel
**I want** reminders and expiries to use inexact alarms
**So that** Lagoon never asks for an exact-alarm permission

## Background

- E9 and the specification say inexact alarms. On 2026-10-17 at 20:03 IST he closed C-E9-2 with that answer.
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

- Closed by Miguel on 2026-10-17 at 20:03 IST. The US-80 allowlist already omits exact-alarm permissions. That allowlist was not changed.

## Implementation notes

Not a design spec. Status stays Draft.
