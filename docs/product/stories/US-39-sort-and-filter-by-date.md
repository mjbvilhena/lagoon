# US-39 Sort and filter the list by when it was saved

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

Sort and filter the list by when it was saved.

## User story

**As a** person named Miguel
**I want** to sort and filter by the date saved, including inside search
**So that** I can limit the list to a day or a range

## Background

- Boundaries use the device local zone. The sort key is last-saved. An edit does not move last-saved (US-12).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: an upcoming view (US-62, Should).

## Acceptance criteria

- **Scenario: newest and oldest** — Must
  - Given several items
  - When he chooses newest first, then oldest first
  - Then the order follows last-saved in that direction
- **Scenario: a local day** — Must
  - Given an item was saved at 23:30 local time
  - When he filters to that local date
  - Then the item is included, and an item from the next local date is not
- **Scenario: a range and quick ranges** — Must
  - Given items across a month
  - When he filters to a range, or to Today, Last 7 days, or Last 30 days
  - Then only items whose last-saved local date is inside that range are shown
- **Scenario: search keeps relevance by default** — Must
  - Given he is searching
  - When he has not chosen a date sort
  - Then US-50 relevance order is used
  - And when he applies a date filter, it limits those results
- **Scenario: nothing in range** — Must
  - Given no item is in the range
  - When the filter is applied
  - Then he sees that nothing is in the range
- **Scenario: clear** — Must
  - Given a date filter is on
  - When he clears it
  - Then the unfiltered list returns
- **Scenario: an edit** — Must
  - Given an item's last-saved date is outside the filter
  - When he edits the item
  - Then last-saved stays outside the filter. Only `updatedAt` moves

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Home default order is US-10.

## Implementation notes

Not a design spec.
