# Product specification: personal knowledge vault

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-18. The passphrase amendment is included in that approval.
> Amendment approved by mjbvilhena on 2026-10-20.

## Background & Value Proposition

Miguel comes across links, text, images, and PDFs in other apps and has nowhere private on his phone that keeps them as one searchable store. The vault is that store. He captures what matters, adds a few words about why when he wants to, and finds it later without a network.

The phone is the primary copy. It works fully offline. When a network is available, the vault syncs itself to his private Google Drive app space. He does not run a backup or a restore. A zip on the device remains a fallback. What leaves the phone for Drive is ciphertext. Topic extraction is the recorded exception: readable content is sent to a cloud AI, and that call is not a Drive sync.

No numeric success metric was given.

## Target Audience

- **Primary user:** Miguel. No other persona is named.
- **Pain this addresses:** content that matters to him stays scattered in the apps where he saw it. He wants one on-device store he can search, and a private copy that follows him to another install without a manual backup ritual.

## Scope & Phasing

### In Scope (MVP)

v1 is the approved epics E1–E9 and E11. Epic approval is not story approval. E10 is later and is not v1. E11 stories are under `docs/product/stories/`. US-41 is approved. US-76 is a placeholder and is not approved. The other E11 stories stay Draft.

| Epic | Outcome | Draft |
| --- | --- | --- |
| E1 Capture and keep | One tap from the share sheet saves a link, text, image, or PDF. Afterwards, last-used tags can be applied from a non-blocking chip row. A note or URL can be added by hand. An item can hold more than one attachment. Titles auto-fill from the page title or file name when he did not type one. Offline. No analytics. | `docs/product/epics/E1-capture-and-keep.md` |
| E2 Link enrichment | A saved link keeps its URL and, when a fetch works, gains title, description, preview image, readable text, and an offline snapshot of the page. | `docs/product/epics/E2-link-enrichment.md` |
| E3 Find and curate | Browse, sort and filter by date saved, Untagged / Inbox, detail, edit, delete with undo, tags including rename-and-merge, search with ranked highlighted matches, filter by type and tag. | `docs/product/epics/E3-find-and-curate.md` |
| E4 Seamless sync | No Back up now, no Restore, no last-backup-time control. Background sync to Drive `appDataFolder` when connectivity allows. On-device first. A new install or a second device signs in and receives the vault. Sync merges. Unmetered-only preference. Zip export/import stays. | `docs/product/epics/E4-seamless-sync.md` |
| E5 Look and feel | Calm Material 3 light and dark UI. Deep teal accent with the contrast rules below. Dynamic color is an optional toggle; teal is the fallback. Launcher name Lagoon. | `docs/product/epics/E5-look-and-feel.md` |
| E6 Installable release | Signed sideload APK. Application id `com.mjbvilhena.lagoon`. `minSdk` 26. `compileSdk` and `targetSdk` 37. Tests, README, screenshots when an emulator exists. | `docs/product/epics/E6-installable-release.md` |
| E7 On-device text recognition | In v1. OCR for screenshots, images, and scanned PDFs, on the device, so that text is searchable. Text-based PDFs are extracted, not OCR'd. | `docs/product/epics/E7-on-device-text-recognition.md` |
| E8 Encryption and sensitive items | Encrypted database and attachment files at rest, keys in Android Keystore. Everything synced to Drive is client-side ciphertext. Fingerprint or biometric lock on items he marks sensitive. | `docs/product/epics/E8-encryption-and-sensitive-items.md` |
| E9 Reminders and expiry | Optional expiry or reminder date on an item, with a notification. | `docs/product/epics/E9-reminders-and-expiry.md` |
| E11 Topics | When an item is shared or added, a cloud AI identifies its main ideas and topics. An existing topic is linked and extended. A new topic is created only after he confirms. | `docs/product/epics/E11-topics.md` |

Constraints later design must not reopen unless he does:

- Kotlin, Jetpack Compose, Material 3.
- Room with FTS. Search covers title, body, extracted text (including text extracted from text-based PDFs, and OCR text once recognized), tags, and the personal note.
- `minSdk` 26. `compileSdk` 37 and `targetSdk` 37 (Android 17, stable since June 2026).
- Application id `com.mjbvilhena.lagoon`.
- Google Sign-In / Credential Manager and the Drive API with the app-data folder (`appDataFolder` / `drive.appdata`). Not Android Auto Backup. That folder is hidden in the Drive UI.
- WorkManager for background sync. The unmetered-only preference uses `UNMETERED`.
- OAuth client ID is build-time configuration. The app builds, installs, and works offline without it. Settings then explain that sync is not configured. They do not crash and they do not offer a manual backup.
- Release signing uses a self-generated keystore that is documented and not committed.
- Crypto algorithms, modes, and key-wrapping details wait for an ADR. This spec only states the outcomes.

### Future Scope (not v1)

- **E10 Trip or collection grouping** with a date range. Later, not v1. Draft: `docs/product/epics/E10-trip-and-collection-grouping.md`. No stories.
- Picking out booking references and merchants. Later, unless it falls out of OCR with no extra product scope. No story.

### Out of Scope / Non-Goals

- Analytics, usage telemetry, or a crash-reporting product.
- Play Store listing for this v1.
- An explicit backup or restore: no Back up now, no Restore action, no last-backup-time control, no manual-backup size warning, no roaming warning tied to a manual backup.
- Android Auto Backup as the sync mechanism.
- A cloud OCR or cloud vision API. On-device OCR stays in E7. Cloud AI is allowed for topic extraction only (E11). It is not a license for cloud OCR, cloud vision, or any other cloud processing of vault content.
- Image editing and PDF editing.
- Tag hierarchies, and any tag merge that is not the confirmed rename onto an existing name.
- Private APIs for YouTube or X.
- Using wallpaper color as the default accent. Dynamic color may be turned on; teal is the fallback when it is off.
- Committing the release keystore, its passwords, or OAuth client secrets.
- Inventing a conflict algorithm, a deletion rule, or a key-recovery design in this specification. Conflicts, deletions, and key transport are US-73 and US-74. Criteria follow from the ADR at the design gate.

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
4. **Topics**
   - After an item is shared or added, its content is processed to identify main ideas and topics.
   - If a topic already exists, the item is linked to it and the topic is extended with the new information.
   - If a topic does not exist yet, he is asked whether to add it. Nothing is created without that confirmation.
   - The phone keeps the item if it is offline. Topic processing waits and runs when a network is available. The processing call sends readable content to a cloud AI. Drive sync is still ciphertext only.

## Product rules

- There is no Back up now, no Restore, and no last-backup time. The phone is first. Sync runs when connectivity allows. A new install or a second device signs in and receives the vault. Sync merges: an item that exists on only one side is kept. Zip export and import stay as a fallback.
- Amendment approved by mjbvilhena on 2026-10-20. US-66 is Must for v1. v1 does not ship without it. After undo, the in-trash state merges by the later `trashChangedAt`, not by `updatedAt`. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. Only a user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. A system write does not. An older user edit is kept while the item stays in trash. A reminder that fell due in trash fires once on restore. A restore made before the deadline that arrives after a tombstone brings the item back. When 30 × 24 hours after `trashedAt` end, the delete becomes a tombstone and spreads. A delete does not become a tombstone by skipping trash. An item that exists on only one side is still kept. An item with a tombstone is not that case: the id is known, and the other phone does not keep it. The precise field rule is ADR-0002. Amendment approved by mjbvilhena on 2026-10-22. The approved amendment covers the trash rule in this bullet. US-66 stays Proposed for story review and is not approved. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. This story is not approved. What is still open is named in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena".
- The database and attachment files are encrypted at rest. Keys are in Android Keystore. Drive stores only ciphertext. The cipher itself waits for an ADR.
- Topic extraction may send readable content to a cloud AI. Drive sync stays ciphertext-only. That call is not a license for cloud OCR.
- In v1: on-device OCR for screenshots, images, and scanned PDFs; an optional reminder or expiry, with a notification; more than one attachment; an offline snapshot of a saved page, keeping the original URL; a biometric lock on items marked sensitive.
- Trip or collection grouping is later, not v1.
- The name is Lagoon. The application id is `com.mjbvilhena.lagoon`. Play Store and trademark checks were not done.
- Light accent about `#0F766E`. Dark accent about `#2DD4BF`. In dark mode the color on top of the accent is dark (black or `#003731`). Never use `#0F766E` on dark surfaces. Dynamic color is an optional toggle. Teal is the fallback.
- Renaming a tag applies to every item. Renaming onto a name that already exists, compared case-insensitively, asks for confirmation and then merges. Merging is in v1 only through that path.
- Background sync prefers an unmetered network (`WorkManager` `UNMETERED`). Sync uses Drive `appDataFolder`, not Android Auto Backup.
- v1 extracts and indexes the text of text-based PDFs. That extraction is not OCR. v1 OCR is English. The bundled ML Kit on-device Latin-script model is used. Acceptance tests use English samples. Other languages are later. Sideloaded APKs use `arm64` `abiFilters` or per-ABI APKs.
- Saving is one tap. Afterwards a non-blocking chip row of last-used tags can file the item. An empty title fills from the page title or the file name, including a screenshot, image, or scan, capped at 120 characters. Untagged items have an inbox. He can sort and filter by date saved.
- A repeat URL matches on a normalised form (ignore scheme, `www`, trailing slash, the `#fragment`, and `utm_*` / tracking parameters; lowercase the host) and keeps the original URL. The existing item is updated: refresh preview and snapshot, union the tags, never overwrite text. A new note is appended as a dated entry. Last-saved is bumped. Snackbar: "Saved before, added to existing", with Undo and "Keep separate".
- Search uses weighted BM25. Starting weights: title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. Exact phrases rank above scattered terms. Recency is only a light tie-break. Prefixes match as he types. Accents fold with `unicode61 remove_diacritics 2`. Weights and the tie-break are deterministic and tested. FTS5 needs Room 3 with the bundled SQLite driver. It is not yet verified with an encrypted database. Spike that early (US-57). Fallback: Room 2 FTS4 plus app-side ranking.
- Reminder time is chosen per item. The default is 09:00 in the phone's current local zone, with a per-item override. Reminders follow that zone, so a 09:00 reminder fires at 09:00 wherever the phone is. An expiry notifies 7 days before and on the day. Alarms are inexact (`setWindow` or `setAndAllowWhileIdle`). Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`. Reminders are stored and re-registered on `BOOT_COMPLETED`. `POST_NOTIFICATIONS` is requested when the first reminder is set, not at launch.
- A page snapshot is a readable extracted copy (headings, lists, inline images downscaled and saved locally), with title, site, author, capture time, and the original URL. One sanitised archive, capped at about 10 MB, marked "partial snapshot" if truncated. It is searchable, encrypted, and synced like other content. A full-page MHTML copy is later, not v1.
- App lock is optional. The device PIN is the fallback when there are no biometrics. The vault key does not require user authentication, so background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses, with a slow memory-hard key derivation and a salt, so entering the passphrase on any device re-creates the same key. No copy of the key, wrapped or otherwise, is stored anywhere off the phone. On the phone the derived key is held in the Android Keystore, or wrapped by a hardware-backed Keystore key, without user authentication, so normal use, sync, and reminders need no passphrase. Changing the passphrase means re-encrypting the vault (US-86). Forgetting the passphrase and losing the device still means the content is lost. Setup gives strong-passphrase guidance. The exact method, parameters, and where the non-secret salt lives are settled in the US-74 ADR at the design gate.
- If he forgets the passphrase while a phone still holds the vault key, that phone can set a new passphrase after the US-37 biometric or device-PIN unlock, without the old passphrase. This re-encrypts as in US-86. Amendment approved by mjbvilhena on 2026-10-18.
- Amendment approved by mjbvilhena on 2026-10-20. On that forgotten-passphrase reset, the Drive copy keeps the old passphrase for 24 hours before it is re-keyed to the new one. During those 24 hours every other phone that holds the key shows a notice and can cancel the reset. A normal passphrase change, where he enters the current passphrase, re-encrypts the phone and the Drive copy without that wait. The phone vault and the Drive copy each stay readable under exactly one passphrase.
- Amendment approved by mjbvilhena on 2026-10-20. A character is one Unicode code point after NFC. A passphrase shorter than 12 or longer than 128 is refused, and a passphrase on the bundled common-password list is refused. There is no strength meter. Setup still gives the strong-passphrase guidance above. A phone that cannot meet the Argon2id memory floor refuses to set up a vault and does not lower the parameters.
- Amendment approved by mjbvilhena on 2026-10-20. There is no passphrase recall check. Settings has no "Check my passphrase" control, and there is no periodic reminder to re-enter the passphrase. A forgotten passphrase, together with losing every phone that holds the key, means the content is lost. There is no in-app aid to remember it. Amendment approved by mjbvilhena on 2026-10-22. The approved amendment covers this specification rule. The same wording on US-35 carries Security criteria approved by mjbvilhena on 2026-10-22.
- Amendment approved by mjbvilhena on 2026-10-20. A phone whose key is not hardware-backed can still set up a vault. Before setup it shows: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." He has to accept that warning, or no vault is created. The same warning stays in Settings on that phone for as long as the key is not hardware-backed. Settings does not ask him to accept it again. It is not shown as a gate on later opens. A phone whose key is hardware-backed does not show it.
- Before the first OCR use he accepts a disclosure: the image and the recognized text stay on the device, and ML Kit still sends usage and diagnostic metrics to Google. Decline, or no answer, means OCR does not run and ML Kit is not started. He can withdraw that acceptance in Settings. There is no telemetry off switch.
- Topic extraction starts on for a new ordinary item. He can turn it off in Settings. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. There is no cap on topic suggestions.
- A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock. An ordinary lock-screen reminder may show the item title and codes.
- A token-looking URL is not auto-fetched. A foggy placeholder is shown until he enables that preview.
- The zip export is encrypted with the vault key.
- Camera capture uses the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`.
- One tap on an image or PDF page opens it full-screen at maximum brightness, with pinch-zoom and the screen kept on. Brightness goes back to normal when you close it. It works offline. A sensitive item needs unlocking first and is protected from screenshots (US-85, Should). Amendment approved by mjbvilhena on 2026-10-18.
- The Upcoming list shows only the one reminder or the one expiry. It does not show a return-by date or a warranty end.

## Risks & Dependencies

- Sync needs a Google Cloud OAuth client whose package name and signing-certificate SHA-1 match the release keystore. Until he supplies the client ID at build time, sync cannot run. The app must still install and work offline.
- A second device can receive ciphertext. Amendment approved by mjbvilhena on 2026-10-18. That device re-creates the same key from the passphrase. No copy of the key is stored on Drive. The method, parameters, and where the non-secret salt lives are US-74. Criteria follow from the ADR at the design gate. This specification does not invent a Google-derived key.
- Page fetches fail offline, on login walls, and on pages that only render in a browser. The item still keeps the URL. A snapshot exists only when a fetch succeeded.
- Share-sheet behavior differs by OEM.
- `appDataFolder` files do not appear in the ordinary Drive UI. That is expected, not a bug.
- OCR quality varies by phone and language. No accuracy rate is set here.
- An emulator may be unavailable for screenshots. Do not fabricate them.
- `docs/domain/vault/DOMAIN.md`, `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, and `docs/layers/data/LAYER.md` are approved. Approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-20.
- Topic extraction sends readable content off the phone to a cloud AI. That is a privacy risk he has accepted in principle. Which provider, and what that provider retains, is US-76. Criteria follow from the ADR at the design gate.
- Amendment approved by mjbvilhena on 2026-10-20. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Amendment approved by mjbvilhena on 2026-10-22. The approved amendment covers this specification rule. The US-41 wording that asks whether to send N earlier items carries Security criteria approved by mjbvilhena on 2026-10-22. US-76 stays a placeholder and is not approved.
- Cloud processing has a cost. No budget or per-item cap is set here.
- Offline, the item is saved and topic processing is queued until a network exists. The vault stays usable without that call.
- The cloud AI credential for a sideloaded APK must not be baked into the package in plain form. How it is supplied and protected is US-76. Criteria follow from the ADR at the design gate.
- Encryption, biometric lock, Drive sync, and the cloud topic call are security-sensitive. The harness has `sdlc-threat-modeler` and the `threat model` template. A threat model is required in the later design band, before implementation. `docs/technical_design/2026-10-17-proposed-threat-note.md` is not that threat model. This section does not skip the threat model and does not write it.

## Open questions

- What happens when the same item was edited on two devices before they sync? US-73. Criteria follow from the ADR at the design gate.
- How does a deletion on one device propagate to the other? US-73. Criteria follow from the ADR at the design gate.
- Amendment approved by mjbvilhena on 2026-10-20. The deletion answer proposed for that question is the product rule above. US-66 is Must for v1. v1 does not ship without trash. The in-trash field merges by the later `trashChangedAt`, not by `updatedAt`. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. Only a user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. A system write does not. An older user edit is kept. A reminder that fell due in trash fires once on restore. A restore made before the deadline that arrives after a tombstone brings the item back. The tombstone spreads only after 30 × 24 hours. Amendment approved by mjbvilhena on 2026-10-22. A delete does not become a tombstone by skipping trash. An item with a tombstone is not kept as a one-sided item. The approved amendment covers the trash rule in this bullet, including those two sentences. US-66 stays Proposed for story review and is not approved. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. This story is not approved. What is still open is named in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena".
- Amendment approved by mjbvilhena on 2026-10-20. A lost phone's Drive file is retired only when he chooses "Forget this phone" in Settings on a phone that still holds the vault. The action names one other install and deletes that install's records file. It does not change the passphrase. It is never automatic.
- How do keys reach a new or second device? US-74. Criteria follow from the ADR at the design gate. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase. No copy of the key is stored anywhere off the phone.
- Which cloud AI provider processes topics, and what are its data-retention terms? US-76. Criteria follow from the ADR at the design gate.
- Amendment approved by mjbvilhena on 2026-10-20. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not include `x-zero-data-retention` set to `true` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once: "Send N earlier items for topics?" N is the number of earlier ordinary items on this install that have no topic provenance, and the default is No. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Amendment approved by mjbvilhena on 2026-10-22. The approved amendment covers this specification rule. The US-41 wording that asks whether to send N earlier items carries Security criteria approved by mjbvilhena on 2026-10-22. US-76 stays a placeholder and is not approved.
- How is the topic API credential stored and protected? It must not be baked into the APK in plain form. US-76. Criteria follow from the ADR at the design gate.
- What exactly is sent to the cloud AI? US-76. Criteria follow from the ADR at the design gate.

## Resolved

- One-sided items are kept.
- The vault is encrypted at rest, and Drive stores ciphertext.
- App lock is optional.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- Amendment approved by mjbvilhena on 2026-10-20. A phone whose key is not hardware-backed can still set up a vault. Before setup it shows: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." He has to accept that warning, or no vault is created. The same warning stays in Settings on that phone for as long as the key is not hardware-backed. Settings does not ask him to accept it again. It is not shown as a gate on later opens. A phone whose key is hardware-backed does not show it.
- One tap on an image or PDF page opens it full-screen at maximum brightness, with pinch-zoom and the screen kept on. Brightness goes back to normal when you close it. It works offline. A sensitive item needs unlocking first and is protected from screenshots (US-85). Amendment approved by mjbvilhena on 2026-10-18.
- OCR is English only. Alarms are inexact. Topic suggestions have no cap.
- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- An empty title on a screenshot, image, or scan fills from the file name.
- Camera capture uses the system camera. The app does not request `CAMERA`.
- Upcoming is the one reminder or the one expiry.

## Approval

| Gate | Status |
| --- | --- |
| This specification | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18. The passphrase amendment is included in that approval. Amendment approved by mjbvilhena on 2026-10-20. |
| Epics E1–E9 and E11 | Approved by mjbvilhena on 2026-10-17. Epic amendments approved by mjbvilhena on 2026-10-17. Amendments to E1, E3, E4, E8, E9, and E11 approved by mjbvilhena on 2026-10-18. The passphrase amendment is included in that approval. Amendment approved by mjbvilhena on 2026-10-20. |
| Epic E10 | Approved by mjbvilhena on 2026-10-17. Later, not in v1. |
| User stories | US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. US-66 is Proposed for story review and is not approved. It is Must for v1. v1 does not ship without it. Amendment approved by mjbvilhena on 2026-10-22. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. This story is not approved. What is still open is named in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". The other stories stay Draft. Amendment approved by mjbvilhena on 2026-10-20. |
| Threat model, UX, test strategy, ADRs | Threat model approved. Approved by mjbvilhena on 2026-10-20. ADR-0001 through ADR-0006 accepted. Approved by mjbvilhena on 2026-10-20. UX and the test strategy are not started. Amendment approved by mjbvilhena on 2026-10-20. |

The specification gate and the epic gate have passed. US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. US-66 is Proposed for story review and is not approved. The non-security parts of US-66 are approved. Approved on 2026-10-20 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. This story is not approved. What is still open is named in `docs/product/stories/REVIEW-PACK.md` under "For mjbvilhena". The other stories stay Draft. Amendment approved by mjbvilhena on 2026-10-20. Implementation has not started.
