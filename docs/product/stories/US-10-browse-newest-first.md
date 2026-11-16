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

- The sort key is last-saved, not created. A new save and a repeat share (US-49) move last-saved. A user edit sets `userEditedAt` and `updatedAt` to the same instant (ADR-0002, US-12). Created date and last-saved do not move, and the row does not move.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
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
- **Scenario: an item in trash** — Must
  - Given an item is in trash (US-66)
  - When he opens the home list
  - Then that item is not shown
- **Scenario: nothing saved** — Must
  - Given the vault has no items that are not in trash, including when every saved item is in trash
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

- Source fields are recorded by US-01, US-02, US-03, and US-04. This story displays them. Filter and search of those fields are US-61. The marker comes from US-34 or US-54.
- **Proposed amendment P3. Not approved.** The priority of source domain and sending app is the proposed amendment in US-01. The Must at "what a row shows" stays until that amendment is approved.

## Implementation notes

Order by last-saved descending. Not a design spec.
