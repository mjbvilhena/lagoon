# US-55 Reschedule reminders after a restart or a clock change

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

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
- A new install needs the passphrase to re-create the key before the vault and its reminders are available (US-35).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77).

## Acceptance criteria

- **Scenario: boot, clock, update, permission** — Must
  - Given a reminder or expiry is stored
  - When the device boots, the clock or time zone changes, the app is updated, or notification permission changes
  - Then the inexact alarms are registered again for that wall-clock time in the phone's current local zone
- **Scenario: due while the phone was off, or before the first unlock** — Must
  - Given a reminder or an expiry notice fell due while the phone was off, or before the first unlock since boot
  - When the phone boots, or after the first unlock if that is needed
  - Then it fires once, as soon as possible
  - And it is not fired twice
  - And it fires only on the install that "which device posts" picks, so an install that was not picked does not fire it on boot.
- **Scenario: a time-zone change** — Must
  - Given a reminder is set for 09:00
  - When the phone's time zone changes
  - Then that reminder fires at 09:00 in the new local zone
- **Scenario: the new zone has already passed that time** — Must
  - Given a reminder is set for 09:00 today
  - When the phone's time zone changes and 09:00 today has already passed in the new zone
  - Then that reminder fires once, as soon as possible
  - And it is not skipped and it is not fired twice
- **Scenario: a daylight-saving gap** — Must
  - Given a reminder time falls in a gap when the clock springs forward
  - When that local day arrives
  - Then the reminder fires at the first valid time after the gap
- **Scenario: a repeated hour** — Must
  - Given a reminder time falls in an hour that happens twice when the clock falls back
  - When that local day arrives
  - Then the reminder fires once, at the first occurrence of that time
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
  - Then the signed-in install with the newest check-in posts, and only that install posts
  - And a sole signed-in install that holds the vault key always posts, however old its check-in is
  - And when two check-ins are equal, the lower install id, by plain string comparison, posts
  - And an install writes a check-in, and counts as newest, only once it holds the vault key
  - And a check-in is the timestamp that install writes when the app is opened or when a sync succeeds, after it holds the vault key
  - And the same rule covers a reminder and an expiry
- **Scenario: two installs** — Must
  - Given install A checked in 2 days ago and install B checked in 1 day ago, and both are signed in
  - When the reminder time arrives
  - Then B posts and A does not
- **Scenario: a signed-out install** — Must
  - Given an install is signed out
  - When the time arrives
  - Then that install does not post from its local vault
  - And the reason is the release: sign-out writes the US-55 release marker before the US-78 revoke, so the duty has left that install
  - And an install that has never signed in still posts, because it has no release marker and there is no other install to take the duty
- **Scenario: a new install** — Must
  - Given a new install has not entered the passphrase
  - When a reminder time arrives
  - Then that install posts nothing
  - And once the passphrase re-creates the key, its reminders register under the which-device rule
- **Scenario: sign-out warning** — Must
  - Given this phone is the only signed-in install
  - When he signs out
  - Then before sign-out finishes he is told that reminders and expiry notices will stop

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
