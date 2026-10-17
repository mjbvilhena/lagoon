# Epic E1: Capture and keep

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> Amendment `docs/product/amendments/2026-10-17-security-delegation.md`, section 5, applied after his approval.
> Approver: `mjbvilhena`. Date and time: 2026-10-17 15:55 IST.
> His words, after a 4-part walkthrough the same day. Part 1, 15:48 IST: "Yes, that's what I meant". Part 2: "Happy with both". Part 3: "Yes". Final approval at 15:55 IST: "Yes".
> Approved revision of this application: commit `86573af` (`86573afe32a946b761f74c354acc389f2a7bac96`). This record does not change the product text of that revision.
> This amendment approval does not approve US-61. The story stays Draft.

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
  - Source domain and sending app (US-61) are a Should.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Fetching page details and snapshots (E2). Search polish (E3). Sync (E4). OCR (E7, which is in v1 but not this epic). Editing pixels or PDF pages. A maximum attachment count. He did not set one. Source domain and sending app are in scope above as a Should (US-61).

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

- Q9 (one tap, then a non-blocking chip row, title auto-fill, inbox, and date sort) is part of the specification approved by `mjbvilhena` on 2026-10-17. See US-28, US-38, US-39, and US-40. Edit later is US-12. This epic is Approved. Its stories stay Draft.
- Q1 and Q2 (name Lagoon, application id `com.mjbvilhena.lagoon`) are part of that approved specification. They do not block saving. Play Store and trademark checks for Lagoon were not done. This epic is Approved. Its stories stay Draft.

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
