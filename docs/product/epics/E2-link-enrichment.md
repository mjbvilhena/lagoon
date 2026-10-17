# Epic E2: Link enrichment

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Does not approve child stories. Shipped-epic DoD is not claimed.

## Background & Value Proposition

A bare URL is a weak memory. When the phone can read the page, Miguel wants the title, a short description, a preview image, and the readable text stored with the link. When it cannot, he still wants the URL.

No separate success metric was given. "Where possible" and "fail gracefully" are the bar he set.

## Target Audience

Miguel, saving links from a browser, YouTube, X, or a URL he typed.

## Scope & Phasing

- **In Scope (MVP):**
  - Best-effort fetch of title, description, preview image, and readable text for a link he shared or typed.
  - Store what the page actually provides. Missing pieces stay empty.
  - If the device is offline or the fetch fails, keep the item and the URL. Do not drop the save and do not crash.
  - When the fetch works, also keep an offline snapshot he can read later, and always keep the original URL (US-33).
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Logged-in or paywalled content. YouTube or X private APIs. A pixel-perfect archive. A guarantee that JavaScript-only pages yield body text. OCR (that is E7).

## Core Workflows

1. **Enrich when the page can be read**
   - He saves a link and the device can fetch it.
   - The item keeps the URL and gains whatever title, description, preview image, readable text, and offline snapshot the fetch obtained.
2. **Keep the URL when it cannot**
   - He saves a link with no network, or the fetch fails.
   - The link item remains, with the URL, and the app stays usable.

## Risks & Dependencies

- Depends on E1 to have a link item to enrich.
- Many pages will return little or no readable text. That is an expected miss, not a failed save.
- Preview images and snapshots are stored files and are part of sync and the zip. The snapshot cap is about 10 MB (US-33, bots' consensus, not approval). The old manual-backup size warning is gone.

## Open Questions

- OCR is Miguel's v1 decision and lives in E7. This epic does not perform it.
- Snapshot shape is the US-33 recommendation: a sanitised readable archive, not MHTML in v1. Not approval.
- [ ] No question was opened here about which HTML fields count as description. Technical design should say which signals are tried, without inventing a success rate.

## Draft stories

- US-08 Enrich a link when the page can be fetched
- US-09 Keep the URL when enrichment does not run
- US-33 Keep an offline snapshot of a saved page
