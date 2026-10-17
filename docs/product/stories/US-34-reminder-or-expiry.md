# US-34 Set a reminder or expiry and get notified

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His decision: optional expiry or reminder dates, with notifications, are in v1. Not approval.

## Title

Set an optional date and be notified.

## User story

**As a** person named Miguel
**I want to** put an optional reminder or expiry date on an item and be notified
**So that** something I saved can come back on the day it matters

## Background

- He decided this is in v1. The date is optional.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a date on every item by default, repeating reminders, and trip grouping (E10, not v1).

## Acceptance criteria

- **Scenario: set a date** — Must
  - Given a saved item
  - When he sets a reminder or expiry date and saves
  - Then the item shows that date
- **Scenario: the day arrives** — Must
  - Given the date is set and notification permission is granted
  - When that date arrives
  - Then he receives a notification that names the item
- **Scenario: no date** — Must
  - Given an item with no reminder or expiry
  - When days pass
  - Then this feature does not notify him about that item
- **Scenario: clear the date** — Must
  - Given an item with a date
  - When he clears it
  - Then the date is gone and no later notification fires for it
- **Scenario: notifications denied** — Must
  - Given he has denied notification permission
  - When he sets a date
  - Then the date is still stored on the item, and he can see it there

## Analytics and observability (optional)

Not applicable. No analytics. A local notification is not product analytics.

## Design and a11y notes

- The date control has a text label. The notification's text includes the item title.
- Whether it fires at the start of the day or at a time he picks is open and story-level. Do not invent a default hour.

## Dependencies and risks

- Uses the platform notification permission. Do not add a second messaging channel.
- Not a calendar integration.

## Implementation notes

One optional date per item. Not a design spec.
