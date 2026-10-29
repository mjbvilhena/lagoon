# Product backlog

> Statuses describe evidence in the tree on 2026-10-23 and were re-checked on 2026-10-27, on 2026-10-28, and on 2026-10-29. The 2026-10-19 review section below is that day's record. **Draft is not approval.** **Not started is not a skip.** **Proposed is not approval.**
> No story is Done. There is no app.
> The specification and epics E1–E11 are approved by mjbvilhena on 2026-10-17. E10 is Later, not in v1. US-15, US-31, US-34, US-35, US-40, US-41, US-49, US-50, US-55, US-61, US-62, US-64, US-65, US-66, US-75, US-78, US-79, US-80, US-81, US-85, US-86, and US-87 are approved. The other stories stay Draft. US-49 is approved. Approved on 2026-10-23 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. US-87 is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. US-66 is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. Amendment approved by mjbvilhena on 2026-10-20. US-66 is Must for v1. v1 does not ship without it.

## Design band

Drafted on 2026-10-28 from master `f9a9a772d96c33fdf30e123e7eb623e595e09030`. These files are Proposed. They are not approved. A merged pull request would not approve them.

- **UX design** *(Proposed)* `docs/technical_design/ux-design.md`. It includes a proposed sync-paused sentence. ADR-0002, US-66, and US-87 had deferred that text. The sentence is not approved.
- **Test plan** *(Proposed)* `docs/technical_design/test-plan.md`. Unit, integration, SQLCipher and FTS, Argon2id, sync and forgotten-installs, Compose, and end-to-end journeys on JVM + Robolectric. No test code. Streaming containers point at accepted ADR-0007. The plan is not approved.

## Gate

Spec gate passed. Epic gate passed. US-15, US-31, US-34, US-35, US-40, US-41, US-49, US-50, US-55, US-61, US-62, US-64, US-65, US-66, US-75, US-78, US-79, US-80, US-81, US-85, US-86, and US-87 are approved. The other stories stay Draft. US-49 is approved. Approved on 2026-10-23 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. US-87 is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. US-66 is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. Amendment approved by mjbvilhena on 2026-10-20. US-66 is Must for v1. v1 does not ship without it. Domain, layers, ADR-0001 through ADR-0006, and the threat model are approved. Approved by mjbvilhena on 2026-10-20. See `docs/sdlc/pipeline-state.md`.

## Epic E1: Capture and keep

> Goal: Miguel can save a link, text, an image, or a PDF from the share sheet in one tap, file it with last-used tags afterwards, add a note or URL himself, and attach more than one file. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E1-capture-and-keep.md`.

- **US-01** *(Draft)* Share a link. A normalised repeat URL updates the existing item. Evidence: `docs/product/stories/US-01-share-a-link.md`. Not implemented.
- **US-02** *(Draft)* Share plain text. Evidence: `docs/product/stories/US-02-share-plain-text.md`. Not implemented.
- **US-03** *(Draft)* Share an image. Evidence: `docs/product/stories/US-03-share-an-image.md`. Not implemented.
- **US-04** *(Draft)* Share a PDF, including text-layer extraction. Evidence: `docs/product/stories/US-04-share-a-pdf.md`. Not implemented. Extraction is not OCR.
- **US-05** *(Draft)* Add a note by hand. Evidence: `docs/product/stories/US-05-add-a-note-by-hand.md`. Not implemented.
- **US-06** *(Draft)* Add a URL by hand. Evidence: `docs/product/stories/US-06-add-a-url-by-hand.md`. Not implemented.
- **US-07** *(Draft)* Offline use, no analytics library the app chooses. ML Kit metrics are disclosed and accepted before the first OCR use. Evidence: `docs/product/stories/US-07-offline-and-no-analytics.md`. Not implemented.
- **US-28** *(Draft)* Last-used tag chips after the one-tap save. Evidence: `docs/product/stories/US-28-quick-add-sheet.md`. Not implemented.
- **US-32** *(Draft)* More than one attachment on an item. Evidence: `docs/product/stories/US-32-multiple-attachments.md`. Not implemented.
- **US-40** *(Approved)* Fill an empty title from the page title or file name. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-40-autofill-title.md`. Not implemented.
- **US-49** *(Approved)* Update a link that was saved before, and undo that update. Approved on 2026-10-23 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-49-repeat-link-update.md`. Not implemented.
- **US-58** *(Draft)* Run enrichment, snapshot, OCR, and topics in that order. Evidence: `docs/product/stories/US-58-background-pipeline.md`. Not implemented.
- **US-61** *(Approved)* Filter or search by where an item came from. Should. Evidence: `docs/product/stories/US-61-source-domain-and-app.md`. Not implemented.
- **US-65** *(Approved)* Add a photo from the camera. Should. Evidence: `docs/product/stories/US-65-in-app-camera.md`. Not implemented.
- **US-67** *(Draft)* Notice a duplicate image or PDF. Could. Evidence: `docs/product/stories/US-67-duplicate-files.md`. Not implemented.
- **US-85** *(Approved)* Show an image or PDF page full-screen and bright. Should. Evidence: `docs/product/stories/US-85-show-an-image-full-screen.md`. Not implemented. Amendment approved by mjbvilhena on 2026-10-18 for the E1 wording. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

## Epic E2: Link enrichment

> Goal: A saved link gains page details and an offline snapshot when a fetch works, and always keeps its URL. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E2-link-enrichment.md`.

- **US-08** *(Draft)* Enrich a link. Evidence: `docs/product/stories/US-08-enrich-a-link.md`. Not implemented.
- **US-09** *(Draft)* Keep the URL when enrichment fails. Evidence: `docs/product/stories/US-09-keep-the-url-when-enrichment-fails.md`. Not implemented.
- **US-33** *(Draft)* Offline snapshot: one sanitised readable archive, about 10 MB, original URL kept. Evidence: `docs/product/stories/US-33-offline-page-snapshot.md`. Not implemented. MHTML is later.
- **US-70** *(Draft)* See what is not ready for offline. Could. Evidence: `docs/product/stories/US-70-offline-readiness.md`. Not implemented.
- **US-79** *(Approved)* Skip unsafe link previews. Evidence: `docs/product/stories/US-79-link-preview-skipping.md`. Not implemented.

## Epic E3: Find and curate

> Goal: He can browse, sort and filter by date saved, see an Untagged / Inbox view, read, edit, delete with undo, manage tags including a confirmed merge, search, and filter by type and tag. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E3-find-and-curate.md`.

- **US-10** *(Draft)* Home list, newest first. Evidence: `docs/product/stories/US-10-browse-newest-first.md`. Not implemented.
- **US-11** *(Draft)* Item detail. Evidence: `docs/product/stories/US-11-read-an-item.md`. Not implemented.
- **US-12** *(Draft)* Edit, including why it matters. Evidence: `docs/product/stories/US-12-edit-an-item.md`. Not implemented.
- **US-13** *(Draft)* Delete with undo. Evidence: `docs/product/stories/US-13-delete-with-undo.md`. Not implemented.
- **US-14** *(Draft)* Tag management. Evidence: `docs/product/stories/US-14-manage-tags.md`. Not implemented.
- **US-15** *(Approved)* Ranked, highlighted search. Weighted BM25 as recommended. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-15-search.md`. Not implemented. Source domain and sending app are not in this Must. The Room 3 / FTS5 / SQLCipher spike is US-57.
- **US-50** *(Approved)* Rank search matches. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-50-search-ranking.md`. Not implemented.
- **US-16** *(Draft)* Filter by type and tag. Evidence: `docs/product/stories/US-16-filter-by-type-and-tag.md`. Not implemented.
- **US-29** *(Draft)* Rename a tag everywhere. A case-insensitive clash asks, then merges. Evidence: `docs/product/stories/US-29-rename-a-tag.md`. Not implemented.
- **US-38** *(Draft)* Untagged / Inbox view. Evidence: `docs/product/stories/US-38-untagged-inbox.md`. Not implemented. A view, not a stored Inbox tag.
- **US-39** *(Draft)* Sort and filter by date saved. Evidence: `docs/product/stories/US-39-sort-and-filter-by-date.md`. Not implemented.
- **US-57** *(Draft)* Prove that search and encryption can run together. Evidence: `docs/product/stories/US-57-fts5-sqlcipher-spike.md`. Not implemented.
- **US-64** *(Approved)* Share or open an item in another app. Should. Evidence: `docs/product/stories/US-64-share-or-open-out.md`. Not implemented.
- **US-66** *(Approved)* Keep a trash beyond undo. Must. In v1. v1 does not ship without it. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. Evidence: `docs/product/stories/US-66-trash.md`. Not implemented. Amendment approved by mjbvilhena on 2026-10-22. The in-trash state merges by the later `trashChangedAt`, not by `updatedAt`. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. The tombstone spreads only after 30 × 24 hours. A delete does not become a tombstone by skipping trash.
- **US-71** *(Draft)* Triage the inbox in a batch. Could. Evidence: `docs/product/stories/US-71-inbox-multi-select.md`. Not implemented.

## Epic E4: Seamless sync

> Goal: Background sync to Drive `appDataFolder`. No Back up now, no Restore, no last-backup-time control. A new install receives the vault and merges. Unmetered-only preference. Zip fallback. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E4-seamless-sync.md`.

- **US-17** *(Draft)* Sync not configured. Evidence: `docs/product/stories/US-17-sync-not-configured.md`. Not implemented. Missing OAuth must not crash.
- **US-18** *(Draft)* Sign in. Evidence: `docs/product/stories/US-18-sign-in-and-sync.md`. Not implemented. Drive scope and sign-out are US-78.
- **US-19** *(Draft)* Unmetered-only background sync, default on. Evidence: `docs/product/stories/US-19-unmetered-sync.md`. Not implemented. `WorkManager` `UNMETERED`.
- **US-30** *(Draft)* Absence of manual backup and restore controls. Evidence: `docs/product/stories/US-30-no-manual-backup.md`. Not implemented.
- **US-20** *(Draft)* New install receives one-sided items. Evidence: `docs/product/stories/US-20-receive-the-vault.md`. Not implemented. Conflicts and deletions are US-73. Criteria follow from the ADR at the design gate.
- **US-21** *(Draft)* Zip export encrypted with the vault key. Evidence: `docs/product/stories/US-21-export-a-zip.md`. Not implemented. Import is US-52.
- **US-51** *(Draft)* Background sync worker and plain-language errors. Evidence: `docs/product/stories/US-51-sync-worker-and-errors.md`. Not implemented. Sign-out is US-78.
- **US-52** *(Draft)* Import a zip and merge one-sided items. Evidence: `docs/product/stories/US-52-import-a-zip.md`. Not implemented.
- **US-56** *(Draft)* Keep a stable identity for each item. Evidence: `docs/product/stories/US-56-item-data-model.md`. Not implemented.
- **US-59** *(Draft)* Use one Settings screen. Evidence: `docs/product/stories/US-59-settings-screen.md`. Not implemented.
- **US-73** *(Placeholder)* Decide sync conflicts and deletions. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Accepted ADR: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. Approved by mjbvilhena on 2026-10-20. Evidence: `docs/product/stories/US-73-sync-conflicts-and-deletions.md`. Not implemented.
- **US-78** *(Approved)* Keep Drive to app data, and sign out without deleting the phone. Evidence: `docs/product/stories/US-78-drive-scope-and-sign-out.md`. Not implemented.
- **US-87** *(Approved)* Forget this phone. A Settings button retires one other install's Drive records file and is never automatic. Evidence: `docs/product/stories/US-87-forget-this-phone.md`. Not implemented. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The unlock criterion requires the US-37 unlock first. A cancelled or failed unlock changes nothing. Security criteria approved by mjbvilhena on 2026-10-22. Security criteria approved by mjbvilhena on 2026-10-23. Those marks cover every marked security criterion in that story.
- **Clock warning** *(Not done)* No story yet. Lagoon shows a warning when this phone's clock is badly out compared with Drive's server time. Merges are unchanged and the residual risk is accepted. Evidence: T-28 in `docs/technical_design/threat-model.md` and `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. The warning threshold and text are not decided. Not implemented.

## Epic E5: Look and feel

> Goal: Calm Material 3 UI with an in-app theme setting: Light, Dark, or System. System is the default and follows Android. Teal accent with the contrast rules. Dynamic color is an optional toggle, off by default, teal as the fallback. Icon and the name Lagoon. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E5-look-and-feel.md`. The UX artefact is Proposed in `docs/technical_design/ux-design.md`. Not approved. US-22 stays Draft.

- **US-22** *(Draft)* Light, Dark, or System theme. System is the default. Evidence: `docs/product/stories/US-22-calm-appearance.md`. Not implemented. Accent: light about `#0F766E`, dark about `#2DD4BF`, dark text on the dark accent (black or `#003731`). Never use `#0F766E` on dark surfaces.
- **US-23** *(Draft)* Icon and the name Lagoon. Evidence: `docs/product/stories/US-23-icon-and-name.md`. Not implemented. Play Store and trademark checks were not done.
- **US-53** *(Draft)* Use the teal palette, optional dynamic color, and reduced motion. Evidence: `docs/product/stories/US-53-palette-and-motion.md`. Not implemented.

## Epic E6: Installable release

> Goal: Signed sideload APK, the named tests, README, and screenshots when an emulator exists. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E6-installable-release.md`.

- **US-24** *(Draft)* Signed release APK. Evidence: `docs/product/stories/US-24-signed-apk.md`. Not implemented. `minSdk` 26. `compileSdk` and `targetSdk` 37. Sideload developer verification stays open.
- **US-25** *(Draft)* Search, sync/zip packaging, and a basic Compose test. Evidence: `docs/product/stories/US-25-automated-tests.md`. Not implemented. Packaging tests cover ciphertext, not a live Drive call. The test plan names Settings as that screen. The story file does not. Decided by mjbvilhena; this record is Proposed until he approves it.
- **US-26** *(Draft)* README for build, sideload, and OAuth. Evidence: `docs/product/stories/US-26-readme-build-and-oauth.md`. The root README does not yet contain those steps, because there is no app. That absence is correct. Application id `com.mjbvilhena.lagoon`. SHA-1 is still unknown. The README must not document a Back up now or a Restore button.
- **US-27** *(Draft)* Screenshots. Evidence: `docs/product/stories/US-27-screenshots.md`. Not started. No images were fabricated. Settings shows sync state, not a backup button.
- **US-80** *(Approved)* Keep the permission set to an allowlist. Evidence: `docs/product/stories/US-80-permission-set.md`. Not implemented.
- **US-81** *(Approved)* Publish a sideload signing policy. Evidence: `docs/product/stories/US-81-sideload-policy.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

## Epic E7: On-device text recognition

> Goal: In v1. Recognize text in screenshots, images, and scanned PDFs on the device so search can find it. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E7-on-device-text-recognition.md`. US-31 is approved. Text-layer PDFs stay in US-04 and are not OCR.

- **US-31** *(Approved, in v1)* On-device English recognition. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The bundled ML Kit Latin-script model still fits. The first OCR use waits on the US-07 disclosure. Evidence: `docs/product/stories/US-31-on-device-text-recognition.md`. Not started. Acceptance tests are English only. The thin Play services artifact is absent because it collects a device identifier. Booking references and merchants are not this story.

## Epic E8: Encryption and sensitive items

> Goal: Encrypt the database and attachment files at rest, keys in Android Keystore. Sync only ciphertext. Biometric lock on items marked sensitive. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E8-encryption-and-sensitive-items.md`. US-35, US-75, and US-86 are approved. The other stories in this epic stay Draft. Security criteria on US-35 and US-86 were approved by mjbvilhena on 2026-10-18. Accepted cipher and container: `docs/adr/ADR-0006-cipher-and-container.md`. Approved by mjbvilhena on 2026-10-20. The specification still says the cipher waits for an ADR. That sentence is not edited. Threat model: `docs/technical_design/threat-model.md`, approved. Approved by mjbvilhena on 2026-10-20.

- **US-35** *(Approved)* Encrypt at rest. The vault key does not require user authentication. Amendment approved by mjbvilhena on 2026-10-18. The key is derived from a passphrase, and a second device re-creates it. Security criteria approved by mjbvilhena on 2026-10-18. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-35-encrypt-at-rest.md`. Not implemented.
- **US-36** *(Draft)* Sync only ciphertext. Evidence: `docs/product/stories/US-36-sync-ciphertext.md`. Not implemented. Key transport is US-74. Criteria follow from the ADR at the design gate.
- **US-37** *(Draft)* Sensitive items use a biometric. App lock is optional, with the device PIN when there is no biometric. Evidence: `docs/product/stories/US-37-biometric-lock.md`. Not implemented. What a sensitive item reveals is US-75.
- **US-74** *(Placeholder)* Decide how the vault key reaches another device. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Accepted ADR: `docs/adr/ADR-0001-argon2id-vault-key.md`. Approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the key from the passphrase. Evidence: `docs/product/stories/US-74-key-transport.md`. Not implemented.
- **US-75** *(Approved)* Limit what a sensitive item reveals. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-75-sensitive-item-handling.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18.
- **US-86** *(Approved)* Change the passphrase. Should. Evidence: `docs/product/stories/US-86-change-the-passphrase.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-18. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
  - Security criteria approved by mjbvilhena on 2026-10-22. A forgotten-passphrase reset keeps the Drive copy on the old passphrase for 24 hours.

## Epic E9: Reminders and expiry

> Goal: Optional expiry or reminder date on an item, with a notification. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E9-reminders-and-expiry.md`. US-34 is approved.

- **US-34** *(Approved)* Set a reminder. Inexact alarms. Lock-screen title and codes for an ordinary item. A code is text already in the title. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-34-set-a-reminder.md`. Not implemented. Exact alarms are closed on US-77.
- **US-54** *(Draft)* Expiry notices, 7 days and the day. Evidence: `docs/product/stories/US-54-expiry-notices.md`. Not implemented.
- **US-55** *(Approved)* Reschedule after boot, clock, update, or permission change. Evidence: `docs/product/stories/US-55-reschedule-reminders.md`. Not implemented. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- **US-62** *(Approved)* See what is coming due. Should. Evidence: `docs/product/stories/US-62-upcoming-and-expiring.md`. Not implemented.
- **US-77** *(Draft)* Keep reminders on inexact alarms. Evidence: `docs/product/stories/US-77-exact-alarms.md`. Not implemented. Alarms are inexact.

## Epic E11: Topics

> Goal: In v1. Identify main ideas and topics when an item is shared or added. Link and extend a topic that already exists. Ask before creating a new one. Cloud AI, with readable content leaving the phone. Drive sync stays ciphertext. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E11-topics.md`. US-41 is approved. The other stories below stay Draft. Provider, credential storage, and payload are US-76. Criteria follow from the ADR at the design gate. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. Do not implement.

- **US-41** *(Approved)* Extract topics when an item is saved. Evidence: `docs/product/stories/US-41-extract-topics-on-save.md`. Not implemented. Provider, credential, and payload are US-76. Criteria follow from the ADR at the design gate.
- **US-42** *(Draft)* Link an item to a topic that already exists and extend that topic's notes. Evidence: `docs/product/stories/US-42-link-an-existing-topic.md`. Not implemented.
- **US-43** *(Draft)* Ask before creating a topic. Decline creates nothing. Evidence: `docs/product/stories/US-43-confirm-a-new-topic.md`. Not implemented.
- **US-44** *(Draft)* Save offline and queue topic processing. Evidence: `docs/product/stories/US-44-queue-topics-offline.md`. Not implemented.
- **US-45** *(Draft)* Open a topic and see its items and summary. Evidence: `docs/product/stories/US-45-read-a-topic.md`. Not implemented.
- **US-46** *(Draft)* Reject, rename, or merge a suggested topic. Evidence: `docs/product/stories/US-46-reject-rename-or-merge-a-suggestion.md`. Not implemented. Does not merge two topics that already exist.
- **US-47** *(Draft)* A failed topic call leaves the item and creates no topic. Evidence: `docs/product/stories/US-47-topic-call-fails.md`. Not implemented.
- **US-48** *(Draft)* Manage a topic that is already saved. Evidence: `docs/product/stories/US-48-manage-saved-topics.md`. Not implemented.
- **US-60** *(Draft)* Browse topics and pending suggestions. Evidence: `docs/product/stories/US-60-topic-list-and-pending.md`. Not implemented.
- **US-72** *(Draft)* Score whether topics are useful. Could. Evidence: `docs/product/stories/US-72-topic-usefulness.md`. Not implemented. The score stays on the phone.
- **US-76** *(Placeholder)* Decide the topic call's provider, credential, and payload. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Accepted ADR: `docs/adr/ADR-0003-topic-call.md`. Approved by mjbvilhena on 2026-10-20. Evidence: `docs/product/stories/US-76-topic-call-privacy.md`. Not implemented.

## Epic E10: Trip and collection grouping

> Goal: Later, not v1. Group items into a trip or collection with a date range. *(Approved by mjbvilhena on 2026-10-17. Later, not in v1.)* Evidence: `docs/product/epics/E10-trip-and-collection-grouping.md`. No stories. Do not build it in v1.

## Specification and vision

- **Vision** *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/vision.md`. It states the current product. It does not describe backup or restore controls.
- **Product specification** *(Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18.)* Evidence: `docs/product/specification.md`. This does not approve the stories. The 2026-10-18 amendment includes the passphrase. Amendment approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-24. Amendment approved by mjbvilhena on 2026-10-27. Amendment approved by mjbvilhena on 2026-10-28.

## Repository setup

Checks from the harness mapping are in the tree. That is not product approval. Evidence: `docs/sdlc/repository-setup.md`, `.github/workflows/qa.yml` (Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, CodeQL), `.github/CODEOWNERS`, `.gitignore`, `README.md`. The older `.github/workflows/secret-scan.yml` and `.github/workflows/markdownlint.yml` files are not in the tree. The Protect master ruleset requires Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, and Kotlin style. The branch must be up to date. There are no bypass actors.

- **Harness install** *(In place)* Cursor skills under `.cursor/commands/`. Submodule `third_party/ai-sdlc-harness` at `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`. Evidence: `docs/sdlc/harness-install.md`.
- **Protect master status checks** *(Done)* Approved by mjbvilhena on 2026-10-18. All seven checks are required, and there are no bypass actors.
- **Protect master bypass** *(Done)* Approved by mjbvilhena on 2026-10-18. All seven checks are required, and there are no bypass actors.

## Design gate

Approved by mjbvilhena on 2026-10-20. Implementation has not started. The map is `docs/product/stories/TRACEABILITY.md`.

- **Domain** *(Approved)* `docs/domain/vault/DOMAIN.md`. Approved by mjbvilhena on 2026-10-20.
- **Layers** *(Approved)* `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, `docs/layers/data/LAYER.md`. Approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-29. The data layer amendment adds `com.google.crypto.tink:tink-android:1.24.0` for streaming file, Drive, and zip encryption only, pointing at accepted ADR-0007. The accepted text is unchanged.
- **ADR-0001** *(Accepted)* Approved by mjbvilhena on 2026-10-20. Argon2id vault key. The forgotten-passphrase reset accepts the US-37 biometric or device PIN, keeps the Drive copy on the old passphrase for 24 hours, and another phone that holds the key can cancel. A cancel keeps items saved on the resetting phone during those 24 hours. The passphrase is 12 to 128 Unicode code points after NFC, the bundled common-password list is blocked, and there is no strength meter. A phone that cannot meet the Argon2id memory floor refuses to set up a vault and does not lower the parameters. A phone without secure hardware can still create a vault after he accepts the warning that someone with the phone could extract the key. That warning stays in Settings and is not a second gate. There is no passphrase recall check, no "Check my passphrase" control, and no periodic reminder. Placeholder US-74 points here. `docs/adr/ADR-0001-argon2id-vault-key.md`.
- **ADR-0002** *(Accepted)* Approved by mjbvilhena on 2026-10-20. Sync conflicts and deletions. US-66 is Must for v1. v1 does not ship without trash. The in-trash field syncs. On every phone a trashed item is hidden and its reminders are silenced, and he can restore it from any phone. Only a user-initiated edit whose `userEditedAt` is later than `trashChangedAt` brings it back. In-trash state merges by `trashChangedAt`, not by `updatedAt`. A system write does not. An older user edit is kept. A reminder that fell due in trash fires once on restore. A restore made before the deadline that arrives after a tombstone brings the item back. The tombstone spreads only after 30 × 24 hours. A lost phone's file is retired only through "Forget this phone". Placeholder US-73 points here. `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`.
- **ADR-0003** *(Accepted)* Approved by mjbvilhena on 2026-10-20. Topic call. The topic call uses the xAI Grok API. Zero data retention is turned on for his xAI team. The default low-cost model is grok-4.20-0309-non-reasoning. A response that does not confirm `x-zero-data-retention` is discarded, and topics pause until he checks. When the key is first added, Lagoon asks once, "Send N earlier items for topics?", and the default is No. N is the number of earlier ordinary items on this install that have no topic provenance. A sensitive item is not counted and is not sent. Per-item "Get topics" stays. There is no monthly cap. Settings shows this month's call count on this install. That count is not synced. De-duplication still applies. The enterprise terms, including the business-purposes grant, are accepted for this personal vault. Governing law is Texas. Data transfer is under the standard contractual clauses. There is no EU region. Placeholder US-76 points here. `docs/adr/ADR-0003-topic-call.md`.
- **ADR-0004** *(Accepted)* Approved by mjbvilhena on 2026-10-20. ML Kit disclosure gate. `docs/adr/ADR-0004-ml-kit-telemetry.md`. Research input stays `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`.
- **ADR-0005** *(Accepted)* Approved by mjbvilhena on 2026-10-20. PDF rendering on API 26–29, isolated process, no PDF password. A password-protected PDF means only a PDF that needs a password to open. A PDF restricted only by an owner password opens, renders, and is searchable as normal. `docs/adr/ADR-0005-pdf-renderer-api-26.md`.
- **ADR-0006** *(Accepted)* Approved by mjbvilhena on 2026-10-20. Cipher and container. Prerequisite for US-35 implementation. US-35 is not edited. `docs/adr/ADR-0006-cipher-and-container.md`. Amendment approved by mjbvilhena on 2026-10-29. The amendment replaces the per-segment `SecureRandom` nonce and the clear header as GCM additional authenticated data with Tink `AES256_GCM_HKDF_1MB` from `com.google.crypto.tink:tink-android:1.24.0`. The accepted sentences stay. The Accepted status is unchanged.
- **ADR-0007** *(Accepted)* `docs/adr/ADR-0007-tink-streaming-aead.md`. Tink AES256_GCM_HKDF_1MB for streaming containers. Approved by mjbvilhena on 2026-10-29. It does not change ADR-0001 or ADR-0006's accepted decisions. Proposed amendment. Needs approval by mjbvilhena. Stale status sentences in ADR-0006, ADR-0007, and `docs/layers/data/LAYER.md` have proposed replacements beside the originals. Those replacements are not approved.
- **Threat model** *(Approved)* `docs/technical_design/threat-model.md`. Approved by mjbvilhena on 2026-10-20. The 2026-10-17 note is an earlier hypothesis list.

## Not started, and not skipped

These need a later explicit go-ahead. No skip sentence was recorded. Re-checked on 2026-10-29. The UX design and the test strategy are Proposed drafts. They are not approved, and they are not a skip. App implementation and verification are still not started.

- UX design (`sdlc-ux-designer`) *(Proposed)* `docs/technical_design/ux-design.md`. Not approved. Not a skip. Implementation stays closed.
- Test strategy (`sdlc-test-planner`) *(Proposed)* `docs/technical_design/test-plan.md`. Not approved. Not a skip. Implementation stays closed.
- App implementation (`sdlc-implementer`) *(Not started)*
- Verification skills (test writer, UI tests beyond the plan, code review, security review) *(Not started)*

## Proposed, no stories

- OCR for languages other than English. Later option. v1 acceptance tests stay English-only (US-31).
- Picking out booking references and merchants. Later, unless it falls out of OCR with no extra product scope.

## Stories

The index is the full list. Every remaining story is also listed under its parent epic above. Not implemented.

- US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Numbers are not reused.
- US-15, US-31, US-34, US-35, US-40, US-41, US-49, US-50, US-55, US-61, US-62, US-64, US-65, US-66, US-75, US-78, US-79, US-80, US-81, US-85, US-86, and US-87 are approved. US-73, US-74, and US-76 are placeholders. They have no Given/When/Then and no MoSCoW yet. They do not meet the user-story Definition of Done. They are not ready for review. Criteria follow from the ADR at the design gate. US-77 has criteria and stays Draft. Amendment approved by mjbvilhena on 2026-10-18 for the US-85 E1 wording. Security criteria approved by mjbvilhena on 2026-10-18. Alarms are inexact.
- The map is `docs/product/stories/TRACEABILITY.md`.

## Requirements

- Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses. Forgetting the passphrase and losing the device still means the content is lost.
- App lock is optional. The device PIN is the fallback when there are no biometrics.
- An ordinary lock-screen reminder may show the item title and codes. A code is text already in that title. There is no separate code field. A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- The zip export is encrypted with the vault key.
- A token-looking URL is not auto-fetched. A foggy placeholder is shown until that preview is enabled.
- ML Kit metrics are disclosed and accepted before the first OCR use. There is no telemetry off switch. Research: `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`.
- Topic extraction starts on for a new ordinary item. He can turn it off in Settings. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. There is no cap. The topic job has its own unmetered setting.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- Amendment approved by mjbvilhena on 2026-10-20. A phone whose key is not hardware-backed can still set up a vault. Before setup it shows: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." He has to accept that warning, or no vault is created. The same warning stays in Settings on that phone for as long as the key is not hardware-backed. Settings does not ask him to accept it again. It is not shown as a gate on later opens. A phone whose key is hardware-backed does not show it.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- An empty title fills from the page title or the file name. Camera capture uses the system camera. Lagoon does not request `CAMERA`.
- Upcoming is the one reminder or the one expiry. Alarms are inexact.

## Open questions

- Sync conflicts, and how a deletion propagates. US-73. Accepted ADR-0002. Approved by mjbvilhena on 2026-10-20. The story stays a placeholder.
- How the vault key reaches a new device. US-74. Accepted ADR-0001. Approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase. The story stays a placeholder.
- Topic provider, retention and training, how the credential is supplied, and what is sent. US-76. Accepted ADR-0003. Approved by mjbvilhena on 2026-10-20. The story stays a placeholder. The topic call uses the xAI Grok API, with zero data retention turned on for his xAI team. When the key is first added, Lagoon asks once, "Send N earlier items for topics?", and the default is No. Sensitive items are never counted or sent.

## Resolved

- One-sided items are kept. Conflicts and deletions are US-73. Criteria follow from the ADR at the design gate.
- Encryption at rest, and ciphertext in Drive. Key transport is US-74. Criteria follow from the ADR at the design gate.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- Amendment approved by mjbvilhena on 2026-10-20. A phone whose key is not hardware-backed can still set up a vault. Before setup it shows: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." He has to accept that warning, or no vault is created. The same warning stays in Settings on that phone for as long as the key is not hardware-backed. Settings does not ask him to accept it again. It is not shown as a gate on later opens. A phone whose key is hardware-backed does not show it.
- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- English-only OCR. Inexact alarms. No Back up now.
- One tap on an image or PDF page opens it full-screen at maximum brightness, with pinch-zoom and the screen kept on. Brightness goes back to normal when you close it. It works offline. A sensitive item needs unlocking first and is protected from screenshots (US-85). Amendment approved by mjbvilhena on 2026-10-18.

## Docs review 2026-10-19

These are not new decisions. The records they name are approved. Approved by mjbvilhena on 2026-10-20. Re-checked on 2026-10-21. The three items below still match the files. Status unchanged.

- **Sensitive-mark merge clock** *(Done)* T-26 merges the sensitive mark by the later `userEditedAt`, and a tie goes to the lower install id, as ADR-0002 does for other user fields. Evidence: `docs/technical_design/threat-model.md`, `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. Both files are approved. Approved by mjbvilhena on 2026-10-20.
- **ADR-0002 summary against its rules** *(Done)* The Decision summary says user fields merge by the later `userEditedAt`, only a user-initiated edit whose `userEditedAt` is later than `trashChangedAt` clears trash, in-trash state merges by `trashChangedAt` rather than `updatedAt`, and the tombstone spreads after 30 × 24 hours. Evidence: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. The record is accepted. Approved by mjbvilhena on 2026-10-20.
- **Story lines that say no domain file** *(Done)* Story lines name `docs/domain/vault/DOMAIN.md`. That file is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. The specification sentence that the cipher waits for an ADR is unchanged.

## Docs review 2026-10-21

These are not decisions. They do not approve a story, an amendment, or implementation. The draft pull request stays open.

- **E11 stories do not all stay Draft** *(Done)* `docs/product/specification.md` says US-41 is approved, US-76 is a placeholder and is not approved, and the other E11 stories stay Draft. US-41 was not edited.
- **US-66 is approved** *(Done)* The specification approval table, the paragraph under it, and `docs/product/epics/E3-find-and-curate.md` record the approval. Security criteria approved by mjbvilhena on 2026-10-22. The written US-66 security criteria carry that mark. US-66 is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- **Placeholder approval lines say Draft** *(Done)* The approval lines on US-73, US-74, and US-76 say Placeholder. Not approved. No acceptance criteria were added.
- **ML Kit input still says not to draft an ADR** *(Done)* The next step points at ADR-0004. That record is accepted. Approved by mjbvilhena on 2026-10-20. The research conclusion is unchanged. US-07 and US-59 stay Draft.

## Docs review 2026-10-27

The items below match the files. No story is Done. There is no app. The pull request stays open.

- **US-66 backlog marker** *(Done)* The story file says Approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The backlog marker matches the story file and `docs/product/stories/INDEX.md`. Evidence: `docs/product/stories/US-66-trash.md`.
- **Earlier review items** *(Done)* The 2026-10-19 and 2026-10-21 items were re-checked. Their evidence still matches the files. Status unchanged. The Protect master ruleset was read again on 2026-10-27. It still requires Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, and Kotlin style, the branch must be up to date, and there are no bypass actors. That item stays Done.
- **US-55 newest check-in line** *(Done)* Amendment approved by mjbvilhena on 2026-10-23. `docs/product/stories/US-55-reschedule-reminders.md` says forgotten installs and installs with a pending delete are not counted when picking the newest check-in.
- **Offline syncs and the sync-paused count** *(Done)* US-87, US-66, ADR-0002, `docs/domain/vault/DOMAIN.md`, and `docs/product/stories/REVIEW-PACK.md` say an offline sync neither counts toward nor resets the run of 3 consecutive syncs with a failed forgotten-installs read or listing. Only a sync that reaches Drive and fails to read or list a forgotten-installs file counts.
- **Sync-paused notice wording** *(Done)* The sync-paused notice is named only. Its exact text waits for the UX artefact. Evidence: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`, `docs/product/stories/US-66-trash.md`, and `docs/product/stories/US-87-forget-this-phone.md`. A Proposed sentence is in `docs/technical_design/ux-design.md`. It is not approved. The stories still defer the exact text.
- **Specification US-66 delegated dates** *(Done)* Amendment approved by mjbvilhena on 2026-10-24. `docs/product/specification.md` dates the US-66 delegated line 2026-10-27, matching `docs/product/stories/US-66-trash.md`.
- **Specification line 70** *(Done)* `docs/product/specification.md` points conflicts and deletions, including trash, at the accepted ADR-0002, and key transport and the forgotten-passphrase reset at the accepted ADR-0001. US-73 and US-74 stay placeholders. `docs/product/stories/REVIEW-PACK.md` no longer lists that specification line as open. The clock-warning threshold and text stay the Not done item under Epic E4.
- **US-87 delegated sign-off line** *(Done)* `docs/product/stories/US-87-forget-this-phone.md` is approved. Approved on 2026-10-27 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- **ADR-0002 line 129** *(Done)* Amendment approved by mjbvilhena on 2026-10-23. Amendment approved by mjbvilhena on 2026-10-22. The returning phone's short notice keeps the sentence that the notice shows only when something was actually lost.

## Docs review 2026-10-28

Re-checked against master `04e1e9d576d92be9f5253f5c882cb21a828af832`. No open pull request. The seven required checks on that commit are green. No story is Done. There is no app. Amendment approved by mjbvilhena on 2026-10-28.

- **E11 threat-model sentence** *(Done)* `docs/product/epics/E11-topics.md` states that the design-gate threat model is `docs/technical_design/threat-model.md`. Approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-28. The E11 header carries Amendment approved by mjbvilhena on 2026-10-28.
- **Specification epic-amendment dates** *(Done)* The epic gate row in `docs/product/specification.md` includes the 2026-10-27 amendments on E1, E3, and E4. Amendment approved by mjbvilhena on 2026-10-28. Amendments to E1, E3, and E4 approved by mjbvilhena on 2026-10-27. The headers of E1, E3, and E4 carry Amendment approved by mjbvilhena on 2026-10-27. The E11 header and the specification header carry Amendment approved by mjbvilhena on 2026-10-28.
- **Product specification amendment dates** *(Done)* The specification bullet in this file now includes Amendment approved by mjbvilhena on 2026-10-24, Amendment approved by mjbvilhena on 2026-10-27, and Amendment approved by mjbvilhena on 2026-10-28, matching the specification header.
- **Earlier review items** *(Done)* The 2026-10-19, 2026-10-21, and 2026-10-27 items were re-checked. Their evidence still matches the files. Status unchanged. The Protect master ruleset was read on 2026-10-28. It still requires Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, and Kotlin style. The branch must be up to date. There are no bypass actors.

## Docs review 2026-10-29

Re-checked on this branch against master `f9a9a772d96c33fdf30e123e7eb623e595e09030`. That commit is `origin/master`. Pull request #10 is the only open pull request. The design-band files are Proposed. They are not approved. No story is Done. There is no app. Implementation stays closed. `docs/technical_design/ux-design.md` and `docs/technical_design/test-plan.md` were not edited.

- **Pipeline-state master line** *(Done)* The inspection line said the design-band draft sits on master `f9a9a77`. That commit does not contain the drafts. The line now says master is that commit, and the drafts are on this branch in pull request #10. They are Proposed. Not approved. Evidence: `docs/sdlc/pipeline-state.md`.
- **Index pointers** *(Done)* `docs/product/stories/TRACEABILITY.md` and `docs/product/stories/REVIEW-PACK.md` named the Proposed files. They are not approved. The sentence that UX and the test strategy are not started stays until mjbvilhena approves the specification amendment.
- **Backlog UX status** *(Done)* The E5 backlog line and the "Not started" list now say the UX design and the test plan are Proposed. Not approved. Not a skip. US-22 stays Draft. Implementation stays closed.
- **Sync-paused wording evidence** *(Done)* Status unchanged. The stories still defer the exact text. A Proposed sentence is in `docs/technical_design/ux-design.md`. It is not approved.
- **Earlier review items** *(Done)* The 2026-10-19, 2026-10-21, 2026-10-27, and 2026-10-28 items were re-checked. Their evidence still matches the files. Status unchanged, except the sync-paused note above. The Protect master ruleset was read on 2026-10-29. It still requires Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, and Kotlin style. The branch must be up to date. There are no bypass actors. One approving review and code owner review are still required.
- **Master drift** *(Done)* `origin/master` `f9a9a772d96c33fdf30e123e7eb623e595e09030` has no material docs drift that this pull request does not already cover. On that commit the UX design and the test plan are absent, so "not started" is still true there.
- **Story lines say the UX artefact does not exist** *(Not done)* Story files still say the exact layout waits for the UX artefact, which does not exist. That includes approved stories US-31, US-34, US-35, US-40, US-41, US-55, US-61, US-62, US-64, US-65, US-66, US-75, US-78, US-79, US-85, US-86, and US-87, and the Draft and placeholder stories that use the same sentence. US-07 says the disclosure sentences wait for an artefact which does not exist. The Proposed file is `docs/technical_design/ux-design.md`. It is not approved. This review did not edit the story files.
- **E5 epic sentence** *(Not done)* `docs/product/epics/E5-look-and-feel.md` still says "UX design has not started." That sentence is in the approved epic. This review did not edit it.
- **US-25 screen name** *(Not done)* The test plan says the US-25 Compose screen is Settings. Decided by mjbvilhena; this record is Proposed until he approves it. `docs/product/stories/US-25-automated-tests.md` still says "the named screen" and does not name Settings. The story header, `docs/product/stories/INDEX.md`, and the epic line above all say Draft. The story file was not edited. A story amendment is still needed before Settings is in the story.
- **Test-plan items 7 to 10** *(Proposed)* Journey runner, Tink, the 1 MB segment, and the Settings screen are recorded in `docs/technical_design/test-plan.md` and accepted ADR-0007. They are not approved. The test plan as a whole stays Proposed. Items other than those four were not edited.
- **Citation gaps in the Proposed artefacts** *(Not done)* Some rows in the Proposed UX design and test plan do not name an approved story, an ADR, or the threat model. This review did not edit those files. Flows: Edit cites only Draft US-12. Theme cites Draft US-22, US-53, US-30, and US-59, plus epic E5. The Settings-and-sync flow cites only Draft stories, while the Settings wireframe still shows Forget this phone, Sign out, and Trash. Screens: Inbox cites Draft US-38. Topic detail cites Draft US-45. Pending suggestions cite Draft US-43 and US-46. Journey J-6 cites the same Draft stories as the Theme flow, plus E5. Copy rows whose source cell names only a Draft story, the vision, or the specification, and not an approved story, an ADR, or the threat model, include the launcher name, "Saved to Lagoon", the repeat-link actions, Undo, the empty home, empty note, empty title, enrichment failure, topic failure, empty topic list, empty topic detail, theme labels, dynamic color, the sync sentences, Unmetered only, the topic-call count label, the missing topic key, App lock, Export zip and Import zip, Use PIN, and the disclosure sentences. Risk rows TP-01 through TP-07, TP-11 through TP-20, TP-25, TP-26, TP-28, and TP-29 do not name an approved story, an ADR, or a threat id in the row. TP-08, TP-27, and TP-30 name Draft US-57 or Draft US-25. TP-10 names Draft US-37. Several of those rows restate a rule that already lives in an accepted ADR or an approved story. The gap is the missing citation. A later edit of the test plan records the journey runner, Tink, the 1 MB segment, and Settings. That edit does not close this citation gap.
