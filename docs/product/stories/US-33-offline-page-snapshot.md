# US-33 Keep an offline snapshot of a saved page

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval is delegated to Miguel's bots (2026-10-17). None has signed this story.
> His decision that snapshots are in v1 is not approval of this story.
> The snapshot shape below is a consensus recommendation from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot under that delegation. Not approval.

## Title

Read a saved page offline, and keep its URL.

## User story

**As a** person named Miguel
**I want** a saved web page to keep a copy I can read offline, and to keep the original URL
**So that** the page is still there when the network is not, and I can still open the source

## Background

- He decided v1 stores an offline snapshot of a saved web page and keeps the original URL.
- US-08 still stores title, description, preview image, and readable text when the fetch works. This story is the snapshot in addition to that.
- US-09 still keeps the URL when the fetch fails. No snapshot is invented in that case.
- The snapshot is one sanitised archive: a readable extracted copy (headings, lists, and inline images downscaled and saved locally), plus title, site, author, capture time, and the original URL. The cap is about 10 MB. A truncated archive is marked "partial snapshot". It is searchable, encrypted, and synced like other content.
- A full-page MHTML copy is an optional later extra, not v1.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a logged-in or paywalled page, a pixel-perfect browser archive, and MHTML in v1.

## Acceptance criteria

- **Scenario: fetch works** — Must
  - Given he saves a link and the page can be fetched
  - When the save finishes
  - Then the item stores the original URL and one sanitised archive he can read with no network
  - And that archive contains the readable copy (headings, lists, and inline images downscaled and stored locally) plus title, site, author, capture time, and the original URL
- **Scenario: cap** — Must
  - Given the fetched page would make an archive larger than about 10 MB
  - When the snapshot is stored
  - Then the archive is cut at about 10 MB and marked "partial snapshot"
- **Scenario: under the cap** — Must
  - Given the archive fits
  - When it is stored
  - Then it is not marked partial
- **Scenario: fetch fails** — Must
  - Given the device is offline or the fetch fails
  - When he saves the link
  - Then the URL is kept, no fake snapshot is stored, and the app does not crash
- **Scenario: the URL stays** — Must
  - Given a snapshot was stored
  - When he opens the item
  - Then he can still see the original URL
- **Scenario: search, encryption, and sync** — Must
  - Given a snapshot was stored
  - When he searches, when the vault is encrypted (US-35), and when sync runs (US-36)
  - Then the snapshot text is searchable, the archive is encrypted with the other content, and the synced copy is ciphertext like the rest of the vault
- **Scenario: no MHTML in v1** — Must
  - Given a snapshot was stored
  - When he looks at the item
  - Then v1 does not require a full-page MHTML copy

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The snapshot is a readable extracted copy he can scroll. The UX artefact chooses the presentation.
- The "partial snapshot" marker is text, not only a color.
- The URL is text, not only a button.

## Dependencies and risks

- Depends on a link item from US-01 or US-06. A repeat save refreshes this snapshot only when the fetch works (US-01).
- Sanitising the archive is required. Script and active content from the page are not part of the readable copy.
- Encryption and sync follow E8 and E4. This story does not choose a cipher.

## Implementation notes

Store one archive with the item. Do not drop the URL. Not a design spec. MHTML is later.
