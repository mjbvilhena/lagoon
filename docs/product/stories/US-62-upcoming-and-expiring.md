# US-62 See what is coming due

> **Approval status: Draft. Not approved.**

**Epic:** E9.

**MoSCoW:** Should. If the story is built, the Upcoming list, the expired marker, and Done on an expiry are its Must criteria.

## Title

See what is coming due.

## User story

**As a** person named Miguel
**I want** a list of upcoming reminders and expiries, and to snooze or finish one from the notification
**So that** I can see what is due without opening each item

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77). Snooze length is 1 hour in this story.
- A sensitive reminder or expiry notice's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content (US-75). Tapping it opens Lagoon. The item's details appear after the US-37 unlock.

## Acceptance criteria

- **Scenario: the list** — Must
  - Given items have a reminder or an expiry (US-34, US-54), including a future date and a date that has passed
  - When he opens Upcoming
  - Then future dates are soonest first, then passed dates are in an Expired group, most recent first
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
