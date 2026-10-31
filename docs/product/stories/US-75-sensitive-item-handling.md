# US-75 Limit what a sensitive item reveals

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
> Security criteria approved by mjbvilhena on 2026-10-18.

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
- A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock. A code is text already in the item title (US-34). There is no separate code field. An ordinary item may still show its title and codes (US-34).
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: the topic provider and payload (US-76). Ordinary lock-screen text (US-34).

## Acceptance criteria

- **Scenario: lock-screen reminder** — Must
  - Given a sensitive item has a reminder
  - When the notification is posted, including when the phone is unlocked and when the lock screen is set to show all notification content
  - Then both the public version and the private version are only "Lagoon reminder"
  - And neither version shows a title or a code
  - And it uses `VISIBILITY_PRIVATE` and `setPublicVersion`
- **Scenario: open a sensitive reminder** — Must
  - Given that notification is showing
  - When he taps it
  - Then Lagoon opens
  - And the item's details appear only after the US-37 unlock
- **Scenario: the topic call** — Must
  - Given a sensitive item
  - When a topic job would run
  - Then the item is excluded
  - And the job waits until the quick-add row has closed, so a sensitive mark on that row is seen first
- **Scenario: other sensitive notices** — Must
  - Given a sensitive item has an expiry
  - When that notice is posted, including when the phone is unlocked and when the lock screen is set to show all notification content
  - Then both the public version and the private version are only "Lagoon reminder", with no title and no code
  - And tapping it opens Lagoon, and the item's details appear after the US-37 unlock
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
  - Given he has completed one US-37 unlock in this app session
  - When the list is showing
  - Then every sensitive row shows its title
  - And when the app goes to the background, all sensitive rows return to the neutral form
- **Scenario: the Upcoming row** — Must when US-62 is in the build
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
  - And any topic job already queued for it (US-44) is cancelled and never sent
  - And the app says content already sent cannot be recalled
- **Scenario: screenshots** — Must
  - Given unlocked content of a sensitive item is on screen
  - When that content is showing
  - Then `FLAG_SECURE` is set for that screen, not for the whole app
  - And while sensitive titles are shown on the list, that list screen has `FLAG_SECURE`
  - And when app lock is on, on API 33 or newer, `setRecentsScreenshotEnabled(false)` is set
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
- Proposed amendment. Needs approval by mjbvilhena. Original: "Exact layout waits for the UX artefact, which does not exist." Replacement: "Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31)."

## Dependencies and risks

- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- The lock-screen reminder, the other notices, and the topic exclusion are requirements above.
- US-37 is the unlock. Details of a sensitive item appear only after that unlock.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
