# US-21 Export a zip to a place he chooses

> **Approval status: Draft. Not approved.**

**Epic:** E4.

**MoSCoW:** Must. v1. Import is US-52.

## Title

Export a zip to a place he chooses.

## User story

**As a** person named Miguel
**I want** to export the vault to a zip at a location I choose
**So that** I have a file fallback that is not a Drive button

## Background

- He picks the location with the system create-document flow. The file has a format version.
- The zip export is encrypted with the vault key.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: how much a file name or size reveals (sensitive-item export is US-75), and import on a device that has no key (US-74). The cipher algorithm is still an ADR.

## Acceptance criteria

- **Scenario: he chooses where** — Must
  - Given the vault has items
  - When he exports
  - Then the system create-document flow runs, the file is written only where he confirms, and there is no Back up now button
- **Scenario: what is inside** — Must
  - Given items, attachments, topics, topic links, and reminders exist
  - When the zip is produced
  - Then it contains those, and the format version field is 1
- **Scenario: encrypted with the vault key** — Must
  - Given a seeded sentinel string is in an item body, and the vault key exists
  - When the zip is written and then opened without that key
  - Then the sentinel cannot be read from the file
  - And when the same file is opened with the vault key, the sentinel and the item body can be read
- **Scenario: the notice** — Must
  - Given he exports
  - When the file is written
  - Then he sees that the file opens only with this vault's key, and that it cannot be opened on a replacement phone until US-74 is decided
- **Scenario: trash is included** — Must
  - Proposed.
  - Given an item is in trash
  - When he exports
  - Then the zip includes that item with `inTrash` and `trashedAt`
- **Scenario: empty vault** — Must
  - Given there are no items
  - When he exports
  - Then a versioned zip is still written and he sees that it has no items

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The create-document flow is the platform picker. No placeholder sentences.

## Dependencies and risks

- US-25 tests the sentinel rule. US-74 holds any further export-protection question.

## Implementation notes

`ACTION_CREATE_DOCUMENT`. Not a design spec.
