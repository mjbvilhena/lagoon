# US-54 Notify before an expiry

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Must. v1, from approved epic E9. Split from US-34.

## Title

Notify before an expiry.

## User story

**As a** person named Miguel
**I want** an expiry to notify me 7 days before and on the day
**So that** I can act before the date, and a close date does not schedule a notice that would already have passed

## Background

- An item has either a reminder (US-34) or an expiry, not both.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77) and the upcoming list (US-62).

## Acceptance criteria

- **Scenario: seven days and the day** — Must
  - Given he sets an expiry more than 7 days away
  - When the alarms are registered
  - Then one inexact notification is 7 days before and one is on the day. Both use 09:00 local unless he picked a time
- **Scenario: closer than 7 days** — Must
  - Given the expiry is fewer than 7 days away
  - When he sets it
  - Then the 7-day notice is not scheduled and the day-of notice is
- **Scenario: a past date** — Must
  - Given the date is already past
  - When he tries to set it
  - Then he sees that the date is in the past and no notification is scheduled
- **Scenario: the item shows it** — Must
  - Given the expiry day has passed
  - When he opens the item
  - Then the item shows that it is expired

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Lock-screen title and codes for an ordinary item follow US-34. Sensitive notification content is delegated.

## Implementation notes

Not a design spec.
