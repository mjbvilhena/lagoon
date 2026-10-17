# US-75 Limit what a sensitive item reveals

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> Lock-screen text for a sensitive item stays open. This story does not decide it.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Limit what a sensitive item reveals.

## User story

**As a** person named Miguel
**I want** lists, search, export, the cloud call, and screenshots to hide a sensitive item's contents until I unlock it
**So that** the sensitive mark limits what is shown

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated this to his bots. He did not decide it. US-37 still requires a biometric or device credential before the body.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: lock-screen reminder text for a sensitive item. That question is open for Miguel. Bots have recommended a generic "Lagoon reminder" via `VISIBILITY_PRIVATE`, or the title only. Neither is chosen here. His 14:56 decision that ordinary lock-screen notifications may show the title and codes is unchanged.
- Out of scope: the topic provider and payload (US-76).

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: a list or a search row** — Must
  - Given a sensitive item whose body contains a sentinel string
  - When the home list or search shows it before unlock
  - Then the row shows the title, the type, and a "Sensitive" mark, and it does not show a thumbnail, a snippet, or OCR text
  - And the sentinel is absent from that row
- **Scenario: screenshots** — Must
  - Given a sensitive item is on screen
  - When the screen is shown
  - Then `FLAG_SECURE` is set
  - And when app lock is on, `FLAG_SECURE` is set for the whole app, and `setRecentsScreenshotEnabled(false)` is set
- **Scenario: a copied code** — Must
  - Given he copies a code from a sensitive item on API 33 or newer
  - When the clip is set
  - Then it is marked `EXTRA_IS_SENSITIVE`
- **Scenario: the topic call** — Must
  - Given a sensitive item and he has not opted it in
  - When a topic job would run
  - Then the item is excluded from the cloud topic call
- **Scenario: the zip** — Must
  - Given he exports a zip (US-21)
  - When a sensitive item is included
  - Then it is inside the vault-key zip and its entry name is an opaque id
- **Scenario: caches and widgets** — Must
  - Given a sensitive item has an image
  - When caches and widgets are written
  - Then they do not contain a thumbnail of that item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The "Sensitive" mark has a name a screen reader can announce. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Open for Miguel: what a lock-screen reminder shows for a sensitive item. Not decided in these criteria.
- US-37 is the unlock. This story is what is visible before that unlock.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
