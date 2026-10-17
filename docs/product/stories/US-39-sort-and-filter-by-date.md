# US-39 Sort and filter by the date saved

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Bots' consensus on Q9. Not approval.

## Title

Sort and filter the list by when it was saved.

## User story

**As a** person named Miguel
**I want to** sort items by the date I saved them and narrow them to a date
**So that** I can look at what I kept on a particular day, not only the newest page

## Background

- The home list defaults to newest first (US-10).
- Consensus: also sort and filter by date saved.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: sorting by title. A calendar. Trip date ranges (E10, not v1).

## Acceptance criteria

- **Scenario: oldest first** — Must
  - Given items saved on different days
  - When he sorts by date saved, oldest first
  - Then the oldest item is first
- **Scenario: newest first** — Must
  - Given that sort
  - When he sorts newest first
  - Then the order matches US-10
- **Scenario: filter to a day** — Must
  - Given items saved on two different days
  - When he filters to one of those days
  - Then only items saved that day are shown
- **Scenario: filter to a range** — Must
  - Given items saved before, during, and after a range of dates
  - When he filters to that range
  - Then only items saved inside the range are shown, inclusive of the end dates
- **Scenario: nothing in range** — Must
  - Given no item was saved in the range
  - When he applies it
  - Then the list is empty and he can see that the filter caused it
- **Scenario: clear** — Must
  - Given a date filter is on
  - When he clears it
  - Then the hidden items return

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The active sort and the active date filter are text, not only a colored control.
- The date control is the platform's or the UX artefact's. This story does not draw it.

## Dependencies and risks

- Composes with type and tag filters (US-16). All active filters apply together.
- "Date saved" is the created date, not an edited date and not the reminder date (US-34).

## Implementation notes

Filter and order on the created date. Not a design spec.
