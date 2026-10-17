# US-21 Export a zip

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E4. Import is US-52.

## Title

Export a zip to a place he chooses.

## User story

**As a** person named Miguel
**I want** to export the vault to a zip at a location I choose
**So that** I have a file fallback that is not a Drive button

## Background

- He picks the location with the system create-document flow. The file has a format version.
- On 2026-10-17 at 14:56 IST, Miguel (`mjbvilhena`) decided that the zip export is encrypted with the vault key. That decision is recorded in `docs/product/stories/REVIEW-PACK.md`. It is not story approval.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: how much a file name or size reveals (sensitive-item export is delegated to his bots), and import on a device that has no key (US-74). The cipher algorithm is still an ADR.

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
