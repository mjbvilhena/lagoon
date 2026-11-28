# US-94 Put related records back when a delete is undone

> **Approval status: Approved.**
> Approved on 2026-11-28 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant under Miguel's delegation of 2026-10-17.

**Epic:** E3.

**MoSCoW:** Must. Split from the unmet restore list on US-13.

## Title

Put related records back when a delete is undone.

## User story

**As a** person named Miguel
**I want** undo to bring back the item's attachments, search row, reminders, and topic records
**So that** a mistaken delete does not drop work that belongs to the item

## Background

- US-13 says undo returns the item with its text, attachments, FTS row, reminders, topic links, queued topic job, and pending suggestions.
- This slice stores the item text. It does not have an FTS index, attachments, a reminder scheduler, or topic records.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: creating those records. The owners below create them. Cleanup of topic notes still waits for the tombstone, as US-13 says. The item text itself stays with US-13.

## Decisions

mjbvilhena decided on 2026-11-28 at 07:11 GMT. He typed "1-5 approved as recommended". For this story that is items 3 and 4. A reminder that falls due during the undo window fires once after Undo. On Undo, a queued topic job goes back to the queue exactly as it was, not sent straight away. These are decisions. They are not whole-story approval. US-94 stays Draft. Not approved.

## Acceptance criteria

- **Scenario: undo puts the search row back** — Must
  - Given the item had a search row and the US-15 search index is in the build
  - When he chooses Undo before the confirmation ends
  - Then search finds the item again
- **Scenario: undo puts attachments back** — Must
  - Given the item had attachments
  - When he chooses Undo before the confirmation ends
  - Then those attachments are back with the item
- **Scenario: undo puts the reminder back** — Must
  - Given the item had a reminder
  - When he chooses Undo before the confirmation ends
  - Then the reminder is rescheduled (US-34, US-55)
  - And a reminder that fell due during the undo window fires once after Undo
- **Scenario: undo puts topic links back** — Must
  - Given the item had topic links
  - When he chooses Undo before the confirmation ends
  - Then those topic links show again (US-45)
- **Scenario: undo puts the queued topic job back** — Must
  - Given a topic job was queued
  - When he chooses Undo before the confirmation ends
  - Then the job goes back to the queue exactly as it was (US-44)
  - And it is not sent straight away
- **Scenario: undo lists pending suggestions** — Must
  - Given the item had pending suggestions
  - When he chooses Undo before the confirmation ends
  - Then those pending suggestions are listed (US-60)
  - And a suggestion he had already rejected, renamed, or merged stays in that state (US-46)

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Undo stays a named action.

## Dependencies and risks

- Search finding the item again is US-15. US-15 is approved.
- Attachments are US-03, US-04, and US-32. US-03, US-04, and US-32 are Draft.
- Rescheduling the reminder is US-34 and US-55. US-34 and US-55 are approved.
- Topic links showing again is US-45. US-45 is Draft. A link to a topic the vault already has is US-42, which is Draft. A confirmed new topic is US-43, which is Draft.
- The queued topic job is US-44. US-44 is Draft.
- Pending suggestions are US-60. US-60 is Draft. A rejected, renamed, or merged suggestion is US-46. US-46 is Draft.
- US-41 is approved and owns extracting topics. This story does not send a topic call on Undo.

## Implementation notes

Not a design spec.
