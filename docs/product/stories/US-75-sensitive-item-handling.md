# US-75 Limit what a sensitive item reveals

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Must.

## Title

Limit what a sensitive item reveals.

## User story

**As a** person named Miguel
**I want** lists, search, export, the cloud call, and screenshots to hide a sensitive item's contents until I unlock it
**So that** the sensitive mark limits what is shown

## Background

- US-37 still requires a biometric or device credential before the body.
- A lock-screen reminder for a sensitive item uses a public version whose text is only "Lagoon reminder". That public version shows no title and no code. A code is text already in the item title (US-34). There is no separate code field. Whether the posted notification's private version is also only "Lagoon reminder" is an open question below. An ordinary item may still show its title and codes (US-34).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the topic provider and payload (US-76). Ordinary lock-screen text (US-34).

## Acceptance criteria

- **Scenario: lock-screen reminder** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item has a reminder
  - When the notification is posted
  - Then it uses `VISIBILITY_PRIVATE` and `setPublicVersion`, and the public text is only "Lagoon reminder"
  - And that public version shows no title and no code
  - And when the lock screen is set to hide sensitive content, it shows only the public version
  - And the item body still waits for the US-37 unlock
- **Scenario: the topic call** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item
  - When a topic job would run
  - Then the item is excluded
  - And the job waits until the quick-add row has closed, so a sensitive mark on that row is seen first
- **Scenario: other sensitive notices** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item has an expiry
  - When that notice is posted
  - Then it uses the same public version as the lock-screen reminder: `VISIBILITY_PRIVATE`, `setPublicVersion`, and the text "Lagoon reminder"
- **Scenario: a list row** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item is locked
  - When the home list shows it
  - Then the row shows the type and a "Sensitive" mark, and it does not show the title, a thumbnail, a snippet, or OCR text
- **Scenario: search before unlock** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item is locked
  - When he searches
  - Then a tag word can return the neutral row
  - And a title word, a body word, an OCR word, and a note word return nothing
  - And the row has no snippet and no highlight
- **Scenario: titles after unlock** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given he has completed one US-37 unlock in this app session
  - When the list is showing
  - Then every sensitive row shows its title
  - And when the app goes to the background, all sensitive rows return to the neutral form
- **Scenario: the Upcoming row** — Must when US-62 is in the build. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item is locked and the US-62 row is its one reminder or its one expiry
  - When that Upcoming row is shown
  - Then it uses the neutral "Sensitive" form, not the title
- **Scenario: topic notes on the topic page** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item is locked
  - When the US-45 page would show that item's topic notes or quotes
  - Then those notes and quotes stay hidden until US-37 unlock
- **Scenario: marked sensitive later** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given an ordinary item was already sent to the topic call
  - When he marks it sensitive
  - Then future topic calls for that item stop
  - And any topic job already queued for it (US-44) is cancelled and never sent
  - And the app says content already sent cannot be recalled
- **Scenario: screenshots** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given unlocked content of a sensitive item is on screen
  - When that content is showing
  - Then `FLAG_SECURE` is set for that screen, not for the whole app
  - And while sensitive titles are shown on the list, that list screen has `FLAG_SECURE`
  - And when app lock is on, on API 33 or newer, `setRecentsScreenshotEnabled(false)` is set
- **Scenario: the zip** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given he exports a zip (US-21)
  - When a sensitive item would be included
  - Then US-37 unlocks first
  - And the item is inside the vault-key zip and its entry name is an opaque id
- **Scenario: caches** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a sensitive item has an image
  - When caches are written
  - Then they do not contain a thumbnail of that item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The "Sensitive" mark has a name a screen reader can announce. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Open questions

- Whether a per-item "Include in topics" control exists. The default exclusion in "the topic call" still holds.
- Whether the posted notification's private version is also only "Lagoon reminder". The public version in "lock-screen reminder" still stands. The device setting that shows all notification content can reveal the private version. That choice is not made here.

## Dependencies and risks

- The lock-screen reminder and the topic exclusion are requirements above. The two open questions are listed above. They are not acceptance criteria.
- US-37 is the unlock. Details of a sensitive reminder appear only after that unlock.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
