# US-75 Limit what a sensitive item reveals

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria in that block are a bot draft. They are not his decision.
> The lock-screen reminder criterion is decided. Miguel (`mjbvilhena`) decided it in chat on 2026-10-17 at 15:59 IST. That decision is not story approval. Status stays Draft.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Limit what a sensitive item reveals.

## User story

**As a** person named Miguel
**I want** lists, search, export, the cloud call, and screenshots to hide a sensitive item's contents until I unlock it
**So that** the sensitive mark limits what is shown

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated lists, search, export, the cloud call, and screenshots to his bots. He did not decide those. US-37 still requires a biometric or device credential before the body.
- On 2026-10-17 at 15:59 IST he decided the lock-screen reminder for a sensitive item. It narrows his 14:56 decision, which still lets an ordinary item show its title and codes (US-34). Sensitive items only: the lock screen shows a generic "Lagoon reminder" and nothing else.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the topic provider and payload (US-76). Ordinary lock-screen text (US-34).

## Acceptance criteria

- **Scenario: lock-screen reminder** — Must
  - Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 15:59 IST. This criterion is not Proposed.
  - Given a sensitive item has a reminder
  - When the notification is posted
  - Then it uses `VISIBILITY_PRIVATE` and `setPublicVersion`, and the public text is only "Lagoon reminder"
  - And that public version shows no title and no code
  - And the same holds when the device lock screen is set to hide sensitive notification content, and when it is set to show all notification content
  - And the private version, which may show the title and a code, appears only after the device is unlocked
  - And the item body still waits for the US-37 unlock

> **Proposed: pending Miguel's approval.** The scenarios below are still a bot draft.

- **Scenario: other sensitive notices** — Must
  - Given a sensitive item has an expiry, a return-by date, or a warranty end
  - When that notice is posted
  - Then it uses the same public version as the lock-screen reminder: `VISIBILITY_PRIVATE`, `setPublicVersion`, and the text "Lagoon reminder"
- **Scenario: a list row** — Must
  - Given a sensitive item is locked
  - When the home list shows it
  - Then the row shows the type and a "Sensitive" mark, and it does not show the title, a thumbnail, a snippet, or OCR text
- **Scenario: search before unlock** — Must
  - Given a sensitive item is locked, a word is only in the title or only in a tag, and a different word is only in the body or only in OCR text
  - When he searches
  - Then the title word and the tag word can return the neutral row, and the body word and the OCR word return nothing
- **Scenario: screenshots** — Must
  - Given unlocked content of a sensitive item is on screen
  - When that content is showing
  - Then `FLAG_SECURE` is set for that screen, not for the whole app
  - And when app lock is on, on API 33 or newer, `setRecentsScreenshotEnabled(false)` is set
- **Scenario: a copied code** — Must
  - Given he copies a code from a sensitive item on API 33 or newer
  - When the clip is set
  - Then it is marked `EXTRA_IS_SENSITIVE`
- **Scenario: the topic call** — Must
  - Given a sensitive item
  - When a topic job would run and he has not turned on that item's "Include in topics" control
  - Then the item is excluded. The control is per item, it defaults off, and it is available only after US-37 unlock
  - And the job waits until the quick-add row has closed, so a sensitive mark on that row is seen first
- **Scenario: the zip** — Must
  - Given he exports a zip (US-21)
  - When a sensitive item would be included
  - Then US-37 unlocks first
  - And the item is inside the vault-key zip and its entry name is an opaque id
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

- The lock-screen reminder is decided, above. The other scenarios in this file stay proposed.
- US-37 is the unlock. Details of a sensitive reminder appear only after that unlock.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
