# Product specification: personal knowledge vault

> **Approval status: Draft. Not approved. Pending review by `mjbvilhena`.**
> This is the first human approval gate. Do not start technical design, UX, threat modeling, test planning, or implementation from this file alone.
> Approver required: `mjbvilhena`. Sign-off on file: none. He still needs to review this specification.
> Decisions recorded below are not that sign-off. A decision from Miguel, and a recommendation from his bots, are different things. Neither one approves this specification.
> Template: `get_sdlc_template("product spec")`. Epic readiness notes: `get_definition_of_done("epic")` (that DoD is the shipped outcome, not this planning gate).

## Background & Value Proposition

Miguel comes across links, text, images, and PDFs in other apps and has nowhere private on his phone that keeps them as one searchable store. The vault is that store. He captures what matters, adds a few words about why when he wants to, and finds it later without a network.

The phone is the primary copy. It works fully offline. When a network is available, the vault syncs itself to his private Google Drive app space. He does not run a backup or a restore. A zip on the device remains a fallback. What leaves the phone for Drive is ciphertext.

No numeric success metric was given.

## Target Audience

- **Primary user:** Miguel. No other persona is named.
- **Pain this addresses:** content that matters to him stays scattered in the apps where he saw it. He wants one on-device store he can search, and a private copy that follows him to another install without a manual backup ritual.

## Scope & Phasing

### In Scope (MVP)

v1 is the draft epics E1–E9. Epic text is not epic approval. E10 is later and is not v1.

| Epic | Outcome | Draft |
|---|---|---|
| E1 Capture and keep | One tap from the share sheet saves a link, text, image, or PDF. Afterwards, last-used tags can be applied from a non-blocking chip row. A note or URL can be added by hand. An item can hold more than one attachment. Titles auto-fill from the page title or file name when he did not type one. Offline. No analytics. | `docs/product/epics/E1-capture-and-keep.md` |
| E2 Link enrichment | A saved link keeps its URL and, when a fetch works, gains title, description, preview image, readable text, and an offline snapshot of the page. | `docs/product/epics/E2-link-enrichment.md` |
| E3 Find and curate | Browse, sort and filter by date saved, Untagged / Inbox, detail, edit, delete with undo, tags including rename-and-merge, search with ranked highlighted matches, filter by type and tag. | `docs/product/epics/E3-find-and-curate.md` |
| E4 Seamless sync | No Back up now, no Restore, no last-backup-time control. Background sync to Drive `appDataFolder` when connectivity allows. On-device first. A new install or a second device signs in and receives the vault. Sync merges. Unmetered-only preference. Zip export/import stays. | `docs/product/epics/E4-private-backup.md` |
| E5 Look and feel | Calm Material 3 light and dark UI. Deep teal accent with the contrast rules below. Dynamic color is an optional toggle; teal is the fallback. Launcher name Lagoon. | `docs/product/epics/E5-look-and-feel.md` |
| E6 Installable release | Signed sideload APK. Application id `com.mjbvilhena.lagoon`. `minSdk` 26. `compileSdk` and `targetSdk` 37. Tests, README, screenshots when an emulator exists. | `docs/product/epics/E6-installable-release.md` |
| E7 On-device text recognition | In v1. OCR for screenshots, images, and scanned PDFs, on the device, so that text is searchable. Text-based PDFs are extracted, not OCR'd. | `docs/product/epics/E7-on-device-text-recognition.md` |
| E8 Encryption and sensitive items | Encrypted database and attachment files at rest, keys in Android Keystore. Everything synced to Drive is client-side ciphertext. Fingerprint or biometric lock on items he marks sensitive. | `docs/product/epics/E8-encryption-and-sensitive-items.md` |
| E9 Reminders and expiry | Optional expiry or reminder date on an item, with a notification. | `docs/product/epics/E9-reminders-and-expiry.md` |

Constraints later design must not reopen unless he does:

- Kotlin, Jetpack Compose, Material 3.
- Room with FTS. Search covers title, body, extracted text (including text extracted from text-based PDFs, and OCR text once recognized), tags, and the personal note.
- `minSdk` 26. `compileSdk` 37 and `targetSdk` 37 (Android 17, stable since June 2026). Bots' consensus on Q8. Not his approval of this spec.
- Application id `com.mjbvilhena.lagoon`. Joint recommendation of Scout and Mikey's Vault App Bot on Q2. Not his approval of this spec.
- Google Sign-In / Credential Manager and the Drive API with the app-data folder (`appDataFolder` / `drive.appdata`). Not Android Auto Backup. That folder is hidden in the Drive UI.
- WorkManager for background sync. The unmetered-only preference uses `UNMETERED`.
- OAuth client ID is build-time configuration. The app builds, installs, and works offline without it. Settings then explain that sync is not configured. They do not crash and they do not offer a manual backup.
- Release signing uses a self-generated keystore that is documented and not committed.
- Crypto algorithms, modes, and key-wrapping details wait for an ADR. This spec only states the outcomes.

### Future Scope (not v1)

- **E10 Trip or collection grouping** with a date range. Miguel decided this is later, not v1. Draft: `docs/product/epics/E10-trip-and-collection-grouping.md`. No stories. Not approval.
- Picking out booking references and merchants. Stays proposed and later, unless it falls out of OCR with no extra product scope. No story.
- Full-screen QR and barcode display. Still proposed. He has not decided. No story.

### Out of Scope / Non-Goals

- Analytics, usage telemetry, or a crash-reporting product.
- Play Store listing for this v1.
- An explicit backup or restore: no Back up now, no Restore action, no last-backup-time control, no manual-backup size warning, no roaming warning tied to a manual backup.
- Android Auto Backup as the sync mechanism.
- A cloud OCR or cloud vision API.
- Image editing and PDF editing.
- Tag hierarchies, and any tag merge that is not the confirmed rename onto an existing name.
- Private APIs for YouTube or X.
- Using wallpaper color as the default accent. Dynamic color may be turned on; teal is the fallback when it is off.
- Committing the release keystore, its passwords, or OAuth client secrets.
- Inventing a conflict algorithm, a deletion rule, or a key-recovery design in this draft. Those stay open for Miguel at the technical design, ADR, and threat-model gate.

## Core Workflows

1. **Save something from another app**
   - He shares a link, text, image, or PDF and chooses the vault. One tap saves it.
   - The title fills from the page title or the file name when he did not type one.
   - A non-blocking row of his last-used tags can be tapped afterwards. He can skip it. Note and tags remain editable on the detail screen.
   - Untagged items show in an Untagged / Inbox view until he files them.
2. **Find it later**
   - With no network he can browse, sort and filter by the date saved, search, and open items, including text-based PDF text and, once OCR has run, text from screenshots, images, and scans.
   - He can set an optional reminder or expiry. He can mark an item sensitive so opening it takes a biometric.
   - An item may have more than one attachment. A saved link keeps its URL and, when the fetch worked, an offline snapshot.
3. **The vault follows him**
   - The phone holds the vault first. Sync runs in the background into Drive `appDataFolder` when the network policy allows. He does not press backup or restore.
   - On a new install or a second device he signs in and the vault arrives. Copies merge: an item that exists on only one side is kept.
   - Drive stores ciphertext. He can still export and import a zip on the device.

## Decisions recorded

None of these approve the specification.

### Miguel (`mjbvilhena`)

Recorded as his decisions, in his words where he gave them.

- **Sync, not backup.** "There should be no explicit backup and restore, there should be just a seamless sync behind the scenes." On-device first. Works fully offline. Syncs when connectivity allows. A new install or a second device signs in and receives the vault. **Q5 is resolved:** sync merges. Zip export/import stays as a fallback.
- **Q10. Encryption.** "Yes, extra encryption would be good." Encryption at rest beyond app-private storage (encrypted database and encrypted attachment files, keys in Android Keystore). Client-side end-to-end encryption of everything synced, so Drive only holds ciphertext. The crypto design itself is not decided here.
- **In v1:** (1) on-device OCR for screenshots, images, and scanned PDFs, so their text is searchable; (2) optional expiry or reminder dates, with notifications; (3) multiple attachments per item; (4) offline snapshots of saved web pages, keeping the original URL; (5) fingerprint or biometric lock on items marked sensitive, tied to the encryption work.
- **Later, not v1:** trip or collection grouping with a date range.

### Q1 and Q2 (joint recommendation, not approval)

Scout and Mikey's Vault App Bot jointly recommend these answers under Miguel's delegation of the product questions. This replaces the earlier name and application id. It is not Miguel's approval.

- **Q1. Name.** Lagoon. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done.
- **Q2. Application id.** `com.mjbvilhena.lagoon`.

### Bots' consensus on the other delegated questions (not approval)

Mikey's Dev Bot first recommended answers under Miguel's delegation of Q3, Q4, and Q6–Q9. Scout, Mikey's Assistant, Mikey's Household Assistant, and Mikey's Leisure Assistant then reviewed them. The lines below are that **consensus recommendation**. They are not Miguel's approval.

- **Q3. Accent.** The teal stays. Light accent about `#0F766E`. Dark accent about `#2DD4BF`. In dark mode the color on top of the accent (`onPrimary`) must be dark (black or `#003731`), because white on `#2DD4BF` fails contrast. Never use `#0F766E` on dark surfaces. Material dynamic color can be an optional toggle, with teal as the fallback. The UX gate can still check the values.
- **Q4. Tags.** Renaming a tag applies to every item. Renaming onto a name that already exists, compared case-insensitively, asks for confirmation and then merges the two tags. Merging is in v1 only through that path.
- **Q6. Network, after his sync decision.** The background-sync preference is Unmetered only (`WorkManager` `UNMETERED`). There is no Back up now, so there is no "runs on any connection" manual action and no roaming warning on a manual backup. Sync uses the Drive API `appDataFolder`, not Android Auto Backup. `appDataFolder` is hidden in the Drive UI. The earlier size warning (about 50 MB on a manual backup) is dropped with Back up now.
- **Q7. OCR timing.** The bots left timing to Miguel. He has since decided OCR is in v1 (see above). Their other point still stands: v1 extracts and indexes the text of text-based PDFs, and that extraction is not OCR. Title, personal note, and tags are searchable.
- **Q8. SDK.** `compileSdk` and `targetSdk` are 37. `minSdk` stays 26.
- **Q9. After the tap.** Saving stays one tap. Afterwards the UI shows an optional, non-blocking chip row of last-used tags, auto-fills the title from the page title or file name, and offers an Untagged / Inbox view so new captures get filed. He can sort and filter by date saved. Note and tags stay editable on the detail screen.

### Proposed, pending Miguel

No stories are added for these.

- Full-screen QR and barcode display.
- Picking out booking references and merchants. Later, unless it falls out of the OCR work for free.

## Risks & Dependencies

- Sync needs a Google Cloud OAuth client whose package name and signing-certificate SHA-1 match the release keystore. Until he supplies the client ID at build time, sync cannot run. The app must still install and work offline.
- A second device can receive ciphertext and still be unable to read it until he answers how keys move (open question below). This draft does not invent a passphrase or a Google-derived key.
- Page fetches fail offline, on login walls, and on pages that only render in a browser. The item still keeps the URL. A snapshot exists only when a fetch succeeded.
- Share-sheet behavior differs by OEM.
- `appDataFolder` files do not appear in the ordinary Drive UI. That is expected, not a bug.
- OCR quality varies by phone and language. No accuracy rate is set here.
- An emulator may be unavailable for screenshots. Do not fabricate them.
- No `DOMAIN.md` or `LAYER.md` exists. No MUST/NEVER rules were invented for them.
- Encryption, biometric lock, and Drive sync are security-sensitive. The harness has `sdlc-threat-modeler` and the `threat model` template. A threat model is required in the later design band, before implementation. It is not started. This note does not skip it and does not write it.

## Open questions

### For Miguel at the design gate

These six stay open. They are security, data integrity, or release questions. They are not delegated. Miguel answers them at the technical design, ADR, and threat-model gate. That gate has not started. Assigning them there does not open it, and it does not approve this specification.

- [ ] **Sync conflicts.** What happens when the same item was edited on two devices before they sync? (@mjbvilhena, design gate)
- [ ] **Deletions.** How does a deletion on one device propagate to the other? (@mjbvilhena, design gate)
- [ ] **Keys on a new device.** How do keys reach a new or second device (for example a passphrase or recovery key, versus a key derived from the Google account)? (@mjbvilhena, design gate)
- [ ] **Lost key.** What happens if the key or passphrase is lost? (@mjbvilhena, design gate)
- [ ] **App unlock.** Is unlocking the app (biometric or device credential) required? This is separate from the v1 lock on items marked sensitive. (@mjbvilhena, design gate)
- [ ] **Sideload verification.** Android developer verification for sideloaded APKs, enforced globally in 2027. ADB installs are exempt. What should this project do about that? (@mjbvilhena, design gate)

### Story-level recommendations (not approval)

Consensus from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot under Miguel's delegation of 2026-10-17. The stories stay Draft. This specification stays Draft.

- **US-01.** Match a repeat URL on a normalised form (ignore scheme, `www`, trailing slash, the `#fragment`, and `utm_*` / tracking parameters; lowercase the host) and keep the original URL. Update the existing item: refresh preview and snapshot, union the tags, never overwrite text. Append a new note as a dated entry. Bump last-saved. Snackbar: "Saved before, added to existing", with Undo and "Keep separate".
- **US-15.** Weighted BM25, starting weights title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. Exact phrases rank above scattered terms. Recency is only a light tie-break. Prefixes match as he types. Accents fold with `unicode61 remove_diacritics 2`. Weights and the tie-break are deterministic and tested. Design-gate risk, not decided here: FTS5 needs Room 3 with the bundled SQLite driver (stock SQLite on `minSdk` 26 lacks FTS5, and Room 2 only has FTS4), and it is not yet verified with an encrypted database (SQLCipher). Spike it early. Fallback: Room 2 FTS4 plus app-side ranking.
- **US-31.** Bundled ML Kit on-device Latin-script model only (English, Irish, and other Latin-script languages), offline from install. Non-Latin scripts come later. Acceptance tests use English plus one accented Latin-script sample. Design note: sideloaded APKs do not get per-device splits, so use `arm64` `abiFilters` or per-ABI APKs.
- **US-34.** He picks the time. Default is 09:00 local in Settings, with a per-item override. Expiry notifies 7 days before and on the day. Inexact alarms (`setWindow` or `setAndAllowWhileIdle`); no exact-alarm permission. Persist reminders and re-register them on `BOOT_COMPLETED`. Ask for `POST_NOTIFICATIONS` when the first reminder is set, not at launch.
- **US-33.** A readable extracted copy (headings, lists, inline images downscaled and saved locally), with title, site, author, capture time, and the original URL. One sanitised archive, capped at about 10 MB, marked "partial snapshot" if truncated. Searchable, encrypted, and synced like other content. A full-page MHTML copy is later, not v1.

### Resolved, not open

- **Q5.** Resolved by Miguel's sync decision: sync merges. An item that exists on only one side is kept. Concurrent edits and deletions are the open questions above, not a return of Q5.
- **Q10.** Resolved by Miguel: extra encryption, at rest and end-to-end for Drive. Key transport, loss, and app unlock remain open.

## Approval

| Gate | Status | What advances it |
|---|---|---|
| This specification | Draft, pending `mjbvilhena` | Named sign-off by `mjbvilhena`, or a sentence that treats this file as approved. He still needs to review it. |
| Epics E1–E9 | Draft, separate gate, not prepared | Epic-level matters stay with Miguel. Not approved by his product decisions or by the bots' consensus. |
| Epic E10 | Draft, later, not v1 | His decision that it is later. Not approval to build it, and not part of v1. |
| User stories | Draft. None approved. | On 2026-10-17 Miguel delegated story approval to his bots. He decides epic-level and security matters only. That rule is not a sign-off on any story. |
| Threat model, UX, test strategy, ADRs | Not started | The six security, data-integrity, and release questions above are answered here, by Miguel. Required before implementation where the pipeline says so. Not skipped. |

This specification is still the open gate. Epic approval comes only after he approves this file. Draft stories are not a reason to skip epic approval, story approval, or the design band.
