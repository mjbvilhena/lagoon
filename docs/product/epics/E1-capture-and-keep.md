# Epic E1: Capture and keep

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Epic approval is its own gate. It is not granted by the product specification, and it does not approve the child stories.
> Shipped-epic DoD (`get_definition_of_done("epic")`) is not claimed. Nothing in this epic is in a user's hands.

## Background & Value Proposition

Miguel needs a place on the phone that receives what he shares and what he types, and that still works with no network. Without this epic there is no vault to search or back up.

No separate success metric was given.

## Target Audience

Miguel. His pain is that a link, a passage, an image, or a PDF he cares about stays in the app where he found it.

## Scope & Phasing

- **In Scope (MVP):**
  - The app appears in Android's share sheet for links, plain text, images, and PDFs.
  - Choosing the vault is one tap and saves the item. Afterwards a non-blocking row of last-used tags can file it. There is no sheet he must finish first.
  - He can edit the note and tags later on the detail screen.
  - An empty title fills from the page title or the file name (US-40).
  - An item can hold more than one attachment (US-32).
  - He can add a note or a URL from inside the app.
  - Each saved item has a title, body, optional source URL, type (`link`, `note`, `image`, or `PDF`), tags, created date, and an optional personal note on why it matters.
  - A second share of the same page matches on a normalised URL (ignore scheme, `www`, a trailing slash, the `#fragment`, and `utm_*` / tracking parameters; lowercase the host), keeps the original URL, updates the existing item, and appends any new note as its own dated entry (US-01).
  - The primary store is on the device. Storing the item does not need a network. Link enrichment (E2) and topic processing (E11) wait for a network. A save queues E11. It does not block the save.
  - No analytics.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Fetching page details and snapshots (E2). Search polish (E3). Sync (E4). OCR (E7, which is in v1 but not this epic). Editing pixels or PDF pages. A maximum attachment count. He did not set one.

## Core Workflows

1. **Share in**
   - Miguel shares a link, text, image, or PDF from another app.
   - He chooses this vault. That is one tap and stores the item.
   - A non-blocking row of last-used tags may follow. He can ignore it. He can edit the note and tags later.
2. **Add by hand**
   - He opens the vault and adds a note or a URL.
   - The new item appears in the on-device store with a created date.

## Risks & Dependencies

- Share intents differ by OEM. The four types still have to be offered from common apps on a stock-like device.
- Depends on no other epic to store a note. Link items created here are enriched by E2 when a network fetch is possible.
- No domain consultant exists for capture. No MUST/NEVER rules were invented.

## Open Questions

- Q9 (one tap, then a non-blocking chip row, title auto-fill, inbox, and date sort) is part of the specification approved by `mjbvilhena` on 2026-10-17. See US-28, US-38, US-39, and US-40. Edit later is US-12. This epic is still Draft and awaits his approval.
- Q1 and Q2 (name Lagoon, application id `com.mjbvilhena.lagoon`) are part of that approved specification. They do not block saving. Play Store and trademark checks for Lagoon were not done. This epic is still Draft and awaits his approval.

## Draft stories

These are not approved. Paths are under `docs/product/stories/`.

- US-01 Share a link into the vault
- US-02 Share plain text into the vault
- US-03 Share an image into the vault
- US-04 Share a PDF into the vault
- US-05 Add a note by hand
- US-06 Add a URL by hand
- US-07 Use the vault offline, with no analytics
- US-28 File a capture with last-used tags
- US-32 Keep more than one attachment on an item
- US-40 Fill the title from the page or the file
