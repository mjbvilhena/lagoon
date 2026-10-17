# US-62 See what is coming due

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E9. Pending Miguel's keep/remove decision. Not an approval.

**MoSCoW:** Should. Not required by the approved epic. The epic requires the notification, not this view. Inside this story, the Upcoming list and the expired marker are Must if the story is built.

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

- **Scenario: the list** — Must
  - Given items have a future reminder, expiry, return-by date, or warranty end (US-34, US-54, US-68)
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
