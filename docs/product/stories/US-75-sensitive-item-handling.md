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
- A lock-screen reminder for a sensitive item shows only "Lagoon reminder", with no title and no code until the device is unlocked. An ordinary item may still show its title and codes (US-34).
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the topic provider and payload (US-76). Ordinary lock-screen text (US-34).

## Acceptance criteria

- **Scenario: lock-screen reminder** — Must
  - Given a sensitive item has a reminder
  - When the notification is posted
  - Then it uses `VISIBILITY_PRIVATE` and `setPublicVersion`, and the public text is only "Lagoon reminder"
  - And that public version shows no title and no code
  - And the same holds when the device lock screen is set to hide sensitive notification content, and when it is set to show all notification content
  - And the private version, which may show the title and a code, appears only after the device is unlocked
  - And the item body still waits for the US-37 unlock
- **Scenario: the topic call** — Must
  - Given a sensitive item
  - When a topic job would run
  - Then the item is excluded
  - And the job waits until the quick-add row has closed, so a sensitive mark on that row is seen first

- **Scenario: a per-item opt-in** — open
  - Given a sensitive item
  - When an "Include in topics" control is considered
  - Then whether that per-item control exists is still open. The default exclusion above still holds
- **Scenario: other sensitive notices** — Must
  - Given a sensitive item has an expiry
  - When that notice is posted
  - Then it uses the same public version as the lock-screen reminder: `VISIBILITY_PRIVATE`, `setPublicVersion`, and the text "Lagoon reminder"
- **Scenario: a list row** — Must
  - Given a sensitive item is locked
  - When the home list shows it
  - Then the row shows the type and a "Sensitive" mark, and it does not show the title, a thumbnail, a snippet, or OCR text
- **Scenario: search before unlock** — Must
  - Given a sensitive item is locked
  - When he searches
  - Then a tag word can return the neutral row
  - And a title word, a body word, an OCR word, and a note word return nothing
  - And the row has no snippet and no highlight
- **Scenario: titles after unlock** — Must
  - Given he has unlocked the item with US-37
  - When the list is showing and the app has not gone to the background
  - Then the title is shown
  - And when the app goes to the background, the row returns to the neutral form
- **Scenario: the Upcoming row** — Must
  - Given a sensitive item is locked and the US-62 row is its one reminder or its one expiry
  - When that Upcoming row is shown
  - Then it uses the neutral "Sensitive" form, not the title
- **Scenario: topic notes on the topic page** — Must
  - Given a sensitive item is locked
  - When the US-45 page would show that item's topic notes or quotes
  - Then those notes and quotes stay hidden until US-37 unlock
- **Scenario: marked sensitive later** — Must
  - Given an ordinary item was already sent to the topic call
  - When he marks it sensitive
  - Then future topic calls for that item stop
  - And the app says content already sent cannot be recalled
- **Scenario: screenshots** — Must
  - Given unlocked content of a sensitive item is on screen
  - When that content is showing
  - Then `FLAG_SECURE` is set for that screen, not for the whole app
  - And when app lock is on, on API 33 or newer, `setRecentsScreenshotEnabled(false)` is set
- **Scenario: the posted notification itself** — open
  - Given a sensitive reminder is posted
  - When the device is set to show all notification content
  - Then whether the posted notification itself carries only "Lagoon reminder" is still open, because that setting can reveal the private version
  - And the public version above still stands
- **Scenario: the zip** — Must
  - Given he exports a zip (US-21)
  - When a sensitive item would be included
  - Then US-37 unlocks first
  - And the item is inside the vault-key zip and its entry name is an opaque id
- **Scenario: caches** — Must
  - Given a sensitive item has an image
  - When caches are written
  - Then they do not contain a thumbnail of that item

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The "Sensitive" mark has a name a screen reader can announce. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- The lock-screen reminder and the topic exclusion are requirements above. Whether a per-item "Include in topics" control exists, and whether a sensitive notification's private version is also only "Lagoon reminder", stay open.
- US-37 is the unlock. Details of a sensitive reminder appear only after that unlock.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
