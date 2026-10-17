# US-62 See what is coming due

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. Not required by the approved epic. The epic requires the notification, not this view.

## Title

See what is coming due.

## User story

**As a** person named Miguel
**I want** a list of upcoming reminders and expiries, and to snooze or finish one from the notification
**So that** I can see what is due without opening each item

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77). Snooze length of 1 hour is a draft rule. It is not Miguel's approval.

## Acceptance criteria

- **Scenario: the list** — Should
  - Given items have a future reminder or expiry
  - When he opens Upcoming
  - Then they are sorted by that date
- **Scenario: expired** — Should
  - Given the date has passed
  - When the row is shown
  - Then it is marked expired
- **Scenario: snooze and done** — Should
  - Given a notification is showing
  - When he chooses Snooze
  - Then the next inexact alarm is one hour later
  - And when he chooses Done, the reminder is cleared

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-34.

## Implementation notes

Not a design spec.
