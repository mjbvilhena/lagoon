# Epic E1: Capture and keep

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.

## Background & Value Proposition

Miguel needs a place on the phone that receives what he shares and what he types, and that still works with no network. Without this epic there is no vault to search or sync.

No separate success metric was given.

## Target Audience

Miguel. His pain is that a link, a passage, an image, or a PDF he cares about stays in the app where he found it.

## Scope & Phasing

- **In Scope (MVP):**
  - The app appears in Android's share sheet for links, plain text, images, and PDFs.
  - Choosing the vault is one tap and saves the item. Afterwards a non-blocking row of last-used tags can file it. There is no sheet he must finish first.
  - He can edit the note and tags later on the detail screen.
  - An empty title fills from the page title or the file name (US-40). A screenshot, an image, or a scan uses that file name. When he did not type a title and the link is token-looking, the filled title is the host only (A-E1-3). Acceptance: the path, query, and fragment of that link are not the title.
  - An item can hold more than one attachment (US-32).
  - He can add a note or a URL from inside the app.
  - Each saved item has a title, body, optional source URL, type (`link`, `note`, `image`, or `PDF`), tags, created date, and an optional personal note on why it matters.
  - A second share of the same page matches on a normalised URL (ignore scheme, `www`, a trailing slash, the `#fragment`, and `utm_*` / tracking parameters; lowercase the host), keeps the original URL, updates the existing item, and appends any new note as its own dated entry (US-01).
  - The primary store is on the device. Storing the item does not need a network. Link enrichment (E2) and topic processing (E11) wait for a network. A save queues E11. It does not block the save.
  - No analytics library. Before the first OCR use he accepts a disclosure that the image and the recognized text stay on the device, and that ML Kit still sends usage and diagnostic metrics to Google (A-E1-1). Declining, or leaving it unanswered, means OCR does not run and ML Kit is not started. He can withdraw the acceptance in Settings. There is no telemetry off switch. Acceptance: a vault he already saved still opens with no network. OCR starts only after that disclosure is accepted.
  - The non-blocking row after a save can set the one reminder or the one expiry on that item (A-E1-2, US-28). Acceptance: skipping Add date leaves the item without a date. It does not add a second date, a return-by date, or a warranty end.
  - After the save returns, a link is enriched, then the snapshot is taken from that fetch, then OCR runs, then the topic job runs (A-E1-4, US-58). A later step starts only after the step it needs. When a step adds text, search is re-indexed from that text. Acceptance: search and topics see the text that exists. The save itself does not wait for the network.
  - He can start a new item from the camera (A-E1-5, US-65, Should). Acceptance: a page can be kept without a share from another app. That capture uses the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`.
  - Saving an image or a PDF whose bytes match an item already stored tells him it is a duplicate (A-E1-6, US-67, Could). Acceptance: he can avoid a second copy of the same file. A repeat URL still matches under the second-share rule (US-49).
  - Source domain and sending app (US-61) are a Should.
  - One tap on an image attachment opens it full-screen, with screen brightness at maximum, and brightness is restored on exit (US-85, Should). It works offline. A sensitive item needs the US-37 unlock first. `FLAG_SECURE` applies for a sensitive item, as US-75 says. Proposed amendment. Needs approval by mjbvilhena.
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

- One tap, then a non-blocking chip row, title auto-fill, inbox, and date sort are in the specification. See US-28, US-38, US-39, and US-40. Edit later is US-12. Stories stay Draft.
- The name is Lagoon. The application id is `com.mjbvilhena.lagoon`. They do not block saving. Play Store and trademark checks for Lagoon were not done. Stories stay Draft.

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
- US-49 Update a link that was saved before, and undo that update.
- US-58 Run enrichment, snapshot, OCR, and topics in that order
- US-61 Filter by where an item came from. Should.
- US-65 Add a photo from the system camera. Should.
- US-67 Notice a duplicate image or PDF. Could.
- US-85 Show an image full-screen and bright. Should. Proposed amendment. Needs approval by mjbvilhena.
