# US-10 Browse saved items, newest first

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. v1.

## Title

Browse saved items, newest first.

## User story

**As a** person named Miguel
**I want** the home list to show what I saved, newest first
**So that** the thing I just kept is the first row

## Background

- The sort key is last-saved, not created. A new save and a repeat share (US-49) move last-saved. An edit moves only `updatedAt` (US-12) and does not move the row.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: date filters and other sort directions (US-39). What a sensitive row reveals before unlock is US-75.
- A home row does not render a link preview image. The foggy placeholder from US-79 is not drawn on the row.

## Acceptance criteria

- **Scenario: several items** — Must
  - Given items with different last-saved times
  - When he opens the home list
  - Then they are ordered by last-saved, newest first, and created date is not the sort key
- **Scenario: what a row shows** — Must
  - Given an ordinary item with a title, a type, a last-saved date, and a source domain or sending app
  - When the row is shown
  - Then he sees the title, the type, the saved date, and that source. A reminder or expiry, when set, shows a marker
- **Scenario: nothing saved** — Must
  - Given the vault has no items
  - When he opens the home list
  - Then he sees that nothing is saved yet
- **Scenario: a new save** — Must
  - Given he saves a new item
  - When he returns to the home list
  - Then that item is the first row
- **Scenario: an edit does not move the row** — Must
  - Given an item is not the newest by last-saved
  - When he edits it (US-12)
  - Then last-saved stays and the row does not become first because of that edit

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Each row has an accessible name that includes the title.
- No placeholder sentences.

## Dependencies and risks

- Source fields come from US-61. The marker comes from US-34 or US-54.

## Implementation notes

Order by last-saved descending. Not a design spec.
