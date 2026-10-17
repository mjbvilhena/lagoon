# US-62 See what is coming due

> **Approval status: Draft. Not approved.**

**Epic:** E9.

**MoSCoW:** Should. The Upcoming list and the expired marker are Must.

## Title

See what is coming due.

## User story

**As a** person named Miguel
**I want** a list of upcoming reminders and expiries, and to snooze or finish one from the notification
**So that** I can see what is due without opening each item

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77). Snooze length is 1 hour in this story.

## Acceptance criteria

- **Scenario: the list** — Must
  - Given items have a future reminder or a future expiry (US-34, US-54)
  - When he opens Upcoming
  - Then they are sorted by that date
- **Scenario: expired** — Must
  - Given the date has passed
  - When the row is shown
  - Then it is marked expired
- **Scenario: done on an expiry** — Must
  - Given an expiry notice is showing
  - When he chooses Done
  - Then further notices for that expiry stop and the date stays on the item
- **Scenario: snooze and done on a reminder** — Should
  - Given a reminder notification is showing
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
