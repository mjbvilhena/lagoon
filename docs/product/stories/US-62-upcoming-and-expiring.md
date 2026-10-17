# US-62 See what is coming due

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.
> Miguel (`mjbvilhena`) on 2026-10-17 at 20:03 IST removed return-by and warranty from this list. It shows the one reminder or the one expiry. This is not story approval.

**Epic:** E9. Kept by Miguel on 2026-10-17 at 20:02 IST. Not a story approval.

**MoSCoW:** Should. In E9 by the amendment approved on 2026-10-17 at 20:02 IST. Not a story approval. Inside this story, the Upcoming list and the expired marker are Must.

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
