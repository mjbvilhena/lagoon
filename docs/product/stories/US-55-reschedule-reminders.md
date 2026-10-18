# US-55 Reschedule reminders after a restart or a clock change

> **Approval status: Draft. Not approved.**

**Epic:** E9.

**MoSCoW:** Must. v1.

## Title

Reschedule reminders after a restart or a clock change.

## User story

**As a** person named Miguel
**I want** reminders and expiries to be registered again after a reboot, a clock change, an update, or a permission change
**So that** a notification is not lost because the phone restarted

## Background

- Alarms stay inexact. Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77).

## Acceptance criteria

- **Scenario: boot, clock, update, permission** — Must
  - Given a reminder or expiry is stored
  - When the device boots, the clock or time zone changes, the app is updated, or notification permission changes
  - Then the inexact alarms are registered again for that wall-clock time in the phone's current local zone
- **Scenario: a time-zone change** — Must
  - Given a reminder is set for 09:00
  - When the phone's time zone changes
  - Then that reminder fires at 09:00 in the new local zone
- **Scenario: app start** — Must
  - Given notification permission may have changed while the app was closed
  - When he opens the app
  - Then the app checks `POST_NOTIFICATIONS` again and registers or skips the alarms from that result
- **Scenario: delete and undo** — Must
  - Given he deletes the item
  - When the delete is pending
  - Then the notification is cancelled, and Undo restores it
- **Scenario: which device posts** — Must
  - Given the same reminder has synced to more than one install
  - When the time arrives
  - Then the install with the newest check-in posts, and only that install posts
  - And the 7-day rule hands the duty over only when another signed-in install has checked in more recently. A sole signed-in install always posts, however old its check-in is
  - And when another signed-in install has the more recent check-in, and this install's check-in is more than 7 days old, that newer install posts and this one does not
  - And when two check-ins are equal, the lower install id, by plain string comparison, posts
  - And a check-in is the timestamp an install writes when the app is opened or when a sync succeeds
  - And the same rule covers a reminder and an expiry
- **Scenario: one install past 7 days** — Must
  - Given one signed-in install whose last check-in is more than 7 days old
  - When a reminder time arrives
  - Then that install posts
- **Scenario: a signed-out install** — Must
  - Given an install is signed out
  - When the time arrives
  - Then that install does not post from its local vault
  - And the reason is the release: sign-out writes the US-55 release marker before the US-78 revoke, so the duty has left that install
  - And an install that has never signed in still posts, because it has no release marker and there is no other install to take the duty
  - And when no other signed-in install has checked in, sign-out tells him before it completes (US-78) that reminders will stop on this phone until he signs in again

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-13 also restores reminders on Undo.
- Residual risk, not closed: if the newest signed-in install is offline when the time arrives, nothing posts. Two installs that have not yet synced each other's latest check-in may both post.

## Implementation notes

Not a design spec.
