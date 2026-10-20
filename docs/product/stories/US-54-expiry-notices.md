# US-54 Notify before an expiry

> **Approval status: Draft. Not approved.**

**Epic:** E9.

**MoSCoW:** Must. v1.

## Title

Notify before an expiry.

## User story

**As a** person named Miguel
**I want** an expiry to notify me 7 days before and on the day
**So that** I can act before the date, and a close date does not schedule a notice that would already have passed

## Background

- An item has either a reminder (US-34) or an expiry, not both.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: exact alarms (US-77) and the upcoming list (US-62).

## Acceptance criteria

- **Scenario: seven days and the day** — Must
  - Given he sets an expiry more than 7 days away
  - When the alarms are registered
  - Then one inexact notification is 7 days before, counted as seven midnights in the device local zone, and one is on the day. Both use 09:00 local unless he picked a time
- **Scenario: exactly 7 days** — Must
  - Given the expiry date is exactly 7 local midnights ahead
  - When the alarms are registered
  - Then both the 7-day notice and the day-of notice are scheduled
- **Scenario: closer than 7 days** — Must
  - Given the expiry is fewer than 7 days away and 09:00 local on that day has not passed
  - When he sets it
  - Then the 7-day notice is not scheduled and the day-of notice is
- **Scenario: 09:00 today has passed** — Must
  - Given he sets an expiry for today and 09:00 local has already passed, and he did not pick a later time
  - When he sets it
  - Then no notice is scheduled for that passed time, and the expiry date is still stored
- **Scenario: a past date** — Must
  - Given the date is already past
  - When he sets it
  - Then the expiry is stored, the item is marked expired, and no notification is scheduled
- **Scenario: the item shows it** — Must
  - Given the expiry day has passed
  - When he opens the item
  - Then the item shows that it is expired
- **Scenario: a notice that came due in trash** — Must
  - Proposed amendment. Needs approval by mjbvilhena. Decided by mjbvilhena; this record is Proposed until he approves it.
  - Given an expiry notice was already due to fire, the item then went to trash, and that notice's time passed while the item was in trash
  - When he restores the item, or a later user edit brings it out of trash
  - Then that notice is shown once, right after the item leaves trash
  - And it is not shown twice
  - And the 7-day notice and the day-of notice are each shown once if that notice came due while the item was in trash
  - And this is an exception to "09:00 today has passed" and "a past date"
  - And those two scenarios still hold when he sets an expiry whose time has already passed: the date is stored and no notice is scheduled for that passed time

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Lock-screen title and codes for an ordinary item follow US-34. Sensitive notification content is US-75.

## Implementation notes

Not a design spec.
