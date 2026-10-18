# Product backlog

> Statuses describe evidence in the tree on 2026-10-18. **Draft is not approval.** **Not started is not a skip.**
> No story is Done. There is no app.
> The specification and epics E1–E11 are approved by mjbvilhena on 2026-10-17. E10 is Later, not in v1. US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. The other stories stay Draft, except US-66, which is Proposed for story review and is not approved.

## Gate

Spec gate passed. Epic gate passed. US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. The other stories stay Draft, except US-66, which is Proposed for story review and is not approved. Domain, layers, ADRs, and the threat model are Proposed and are not accepted. See `docs/sdlc/pipeline-state.md`.

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
- **US-49** *(Draft)* Update a link that was saved before, and undo that update. Evidence: `docs/product/stories/US-49-repeat-link-update.md`. Not implemented.
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
- **US-66** *(Proposed for story review)* Keep a trash beyond undo. Should. In v1. Not approved. Evidence: `docs/product/stories/US-66-trash.md`. Not implemented. Proposed amendment. Needs approval by mjbvilhena. Trash keeps a deleted item for 30 days on that phone. The delete stays local until the 30 days end, then becomes a tombstone.
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
- **US-73** *(Placeholder)* Decide sync conflicts and deletions. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Proposed ADR: `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`. Not accepted. Evidence: `docs/product/stories/US-73-sync-conflicts-and-deletions.md`. Not implemented.
- **US-78** *(Approved)* Keep Drive to app data, and sign out without deleting the phone. Evidence: `docs/product/stories/US-78-drive-scope-and-sign-out.md`. Not implemented.

## Epic E5: Look and feel

> Goal: Calm Material 3 UI with an in-app theme setting: Light, Dark, or System. System is the default and follows Android. Teal accent with the contrast rules. Dynamic color is an optional toggle, off by default, teal as the fallback. Icon and the name Lagoon. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E5-look-and-feel.md`. UX artefact not started. US-22 stays Draft.

- **US-22** *(Draft)* Light, Dark, or System theme. System is the default. Evidence: `docs/product/stories/US-22-calm-appearance.md`. Not implemented. Accent: light about `#0F766E`, dark about `#2DD4BF`, dark text on the dark accent (black or `#003731`). Never use `#0F766E` on dark surfaces.
- **US-23** *(Draft)* Icon and the name Lagoon. Evidence: `docs/product/stories/US-23-icon-and-name.md`. Not implemented. Play Store and trademark checks were not done.
- **US-53** *(Draft)* Use the teal palette, optional dynamic color, and reduced motion. Evidence: `docs/product/stories/US-53-palette-and-motion.md`. Not implemented.

## Epic E6: Installable release

> Goal: Signed sideload APK, the named tests, README, and screenshots when an emulator exists. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E6-installable-release.md`.

- **US-24** *(Draft)* Signed release APK. Evidence: `docs/product/stories/US-24-signed-apk.md`. Not implemented. `minSdk` 26. `compileSdk` and `targetSdk` 37. Sideload developer verification stays open.
- **US-25** *(Draft)* Search, sync/zip packaging, and a basic Compose test. Evidence: `docs/product/stories/US-25-automated-tests.md`. Not implemented. Packaging tests cover ciphertext, not a live Drive call.
- **US-26** *(Draft)* README for build, sideload, and OAuth. Evidence: `docs/product/stories/US-26-readme-build-and-oauth.md`. The root README does not yet contain those steps, because there is no app. That absence is correct. Application id `com.mjbvilhena.lagoon`. SHA-1 is still unknown. The README must not document a Back up now or a Restore button.
- **US-27** *(Draft)* Screenshots. Evidence: `docs/product/stories/US-27-screenshots.md`. Not started. No images were fabricated. Settings shows sync state, not a backup button.
- **US-80** *(Approved)* Keep the permission set to an allowlist. Evidence: `docs/product/stories/US-80-permission-set.md`. Not implemented.
- **US-81** *(Approved)* Publish a sideload signing policy. Evidence: `docs/product/stories/US-81-sideload-policy.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

## Epic E7: On-device text recognition

> Goal: In v1. Recognize text in screenshots, images, and scanned PDFs on the device so search can find it. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E7-on-device-text-recognition.md`. US-31 is approved. Text-layer PDFs stay in US-04 and are not OCR.

- **US-31** *(Approved, in v1)* On-device English recognition. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. The bundled ML Kit Latin-script model still fits. The first OCR use waits on the US-07 disclosure. Evidence: `docs/product/stories/US-31-on-device-text-recognition.md`. Not started. Acceptance tests are English only. The thin Play services artifact is absent because it collects a device identifier. Booking references and merchants are not this story.

## Epic E8: Encryption and sensitive items

> Goal: Encrypt the database and attachment files at rest, keys in Android Keystore. Sync only ciphertext. Biometric lock on items marked sensitive. *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/epics/E8-encryption-and-sensitive-items.md`. US-35, US-75, and US-86 are approved. The other stories in this epic stay Draft. Security criteria on US-35 and US-86 were approved by mjbvilhena on 2026-10-18. Proposed cipher and container: `docs/adr/ADR-0006-cipher-and-container.md`, not accepted. The specification still says the cipher waits for an ADR. That sentence is not edited. Threat model: `docs/technical_design/threat-model.md`, Proposed, not accepted.

- **US-35** *(Approved)* Encrypt at rest. The vault key does not require user authentication. Amendment approved by mjbvilhena on 2026-10-18. The key is derived from a passphrase, and a second device re-creates it. Security criteria approved by mjbvilhena on 2026-10-18. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-35-encrypt-at-rest.md`. Not implemented.
- **US-36** *(Draft)* Sync only ciphertext. Evidence: `docs/product/stories/US-36-sync-ciphertext.md`. Not implemented. Key transport is US-74. Criteria follow from the ADR at the design gate.
- **US-37** *(Draft)* Sensitive items use a biometric. App lock is optional, with the device PIN when there is no biometric. Evidence: `docs/product/stories/US-37-biometric-lock.md`. Not implemented. What a sensitive item reveals is US-75.
- **US-74** *(Placeholder)* Decide how the vault key reaches another device. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Proposed ADR: `docs/adr/ADR-0001-argon2id-vault-key.md`. Not accepted. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the key from the passphrase. Evidence: `docs/product/stories/US-74-key-transport.md`. Not implemented.
- **US-75** *(Approved)* Limit what a sensitive item reveals. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Evidence: `docs/product/stories/US-75-sensitive-item-handling.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18.
- **US-86** *(Approved)* Change the passphrase. Should. Evidence: `docs/product/stories/US-86-change-the-passphrase.md`. Not implemented. Security criteria approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-18. Proposed amendment, not approved: a forgotten-passphrase reset keeps the Drive copy on the old passphrase for 24 hours. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

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
- **US-76** *(Placeholder)* Decide the topic call's provider, credential, and payload. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Proposed ADR: `docs/adr/ADR-0003-topic-call.md`. Not accepted. Evidence: `docs/product/stories/US-76-topic-call-privacy.md`. Not implemented.

## Epic E10: Trip and collection grouping

> Goal: Later, not v1. Group items into a trip or collection with a date range. *(Approved by mjbvilhena on 2026-10-17. Later, not in v1.)* Evidence: `docs/product/epics/E10-trip-and-collection-grouping.md`. No stories. Do not build it in v1.

## Specification and vision

- **Vision** *(Approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/vision.md`. It states the current product. It does not describe backup or restore controls.
- **Product specification** *(Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17.)* Evidence: `docs/product/specification.md`. This does not approve the stories.

## Repository setup

Checks from the harness mapping are in the tree. That is not product approval. Evidence: `docs/sdlc/repository-setup.md`, `.github/workflows/qa.yml` (Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, CodeQL), `.github/CODEOWNERS`, `.gitignore`, `README.md`. The older `.github/workflows/secret-scan.yml` and `.github/workflows/markdownlint.yml` files are not in the tree. The Protect master ruleset requires Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, and Kotlin style. The branch must be up to date. There are no bypass actors.

- **Harness install** *(In place)* Cursor skills under `.cursor/commands/`. Submodule `third_party/ai-sdlc-harness` at `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`. Evidence: `docs/sdlc/harness-install.md`.
- **Protect master status checks** *(Done)* Decided by mjbvilhena on 2026-10-18: all seven checks required, no bypass.
- **Protect master bypass** *(Done)* Decided by mjbvilhena on 2026-10-18: all seven checks required, no bypass.

## Proposed design gate

Not approval. Needs approval by mjbvilhena. Implementation has not started. The map is `docs/product/stories/TRACEABILITY.md`.

- **Domain** *(Proposed)* `docs/domain/vault/DOMAIN.md`.
- **Layers** *(Proposed)* `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, `docs/layers/data/LAYER.md`.
- **ADR-0001** *(Proposed)* Argon2id vault key. The forgotten-passphrase reset accepts the US-37 biometric or device PIN, keeps the Drive copy on the old passphrase for 24 hours, and another phone that holds the key can cancel. A cancel keeps items saved on the resetting phone during those 24 hours. Passphrase floor and phones without secure hardware stay Decision pending. Placeholder US-74 points here. `docs/adr/ADR-0001-argon2id-vault-key.md`.
- **ADR-0002** *(Proposed)* Sync conflicts and deletions. Placeholder US-73 points here. `docs/adr/ADR-0002-sync-conflicts-and-deletions.md`.
- **ADR-0003** *(Proposed)* Topic call. The recommendation is the xAI Grok API. The Cursor API does not fit. The provider stays Decision pending. The topic backlog when a key is added stays a separate pending choice. Placeholder US-76 points here. `docs/adr/ADR-0003-topic-call.md`.
- **ADR-0004** *(Proposed)* ML Kit disclosure gate. `docs/adr/ADR-0004-ml-kit-telemetry.md`. Research input stays `docs/adr/inputs/2026-10-17-ml-kit-telemetry.md`.
- **ADR-0005** *(Proposed)* PDF rendering on API 26–29, isolated process, no PDF password. The definition of password-protected is Decision pending. `docs/adr/ADR-0005-pdf-renderer-api-26.md`.
- **ADR-0006** *(Proposed)* Cipher and container. Prerequisite for US-35 implementation. US-35 is not edited. `docs/adr/ADR-0006-cipher-and-container.md`.
- **Threat model** *(Proposed)* `docs/technical_design/threat-model.md`. The 2026-10-17 note is an earlier hypothesis list.

## Not started, and not skipped

These need a later explicit go-ahead. No skip sentence was recorded.

- UX design (`sdlc-ux-designer`)
- Test strategy (`sdlc-test-planner`)
- App implementation (`sdlc-implementer`)
- Verification skills (test writer, UI tests beyond the plan, code review, security review)

## Proposed, no stories

- OCR for languages other than English. Later option. v1 acceptance tests stay English-only (US-31).
- Picking out booking references and merchants. Later, unless it falls out of OCR with no extra product scope.

## Stories

The index is the full list. Every remaining story is also listed under its parent epic above. Not implemented.

- US-63, US-68, US-69, US-82, US-83, and US-84 are removed. Numbers are not reused.
- US-15, US-31, US-34, US-35, US-40, US-41, US-50, US-55, US-61, US-62, US-64, US-65, US-75, US-78, US-79, US-80, US-81, US-85, and US-86 are approved. US-73, US-74, and US-76 are placeholders. They have no Given/When/Then and no MoSCoW yet. They do not meet the user-story Definition of Done. They are not ready for review. Criteria follow from the ADR at the design gate. US-77 has criteria and stays Draft. Amendment approved by mjbvilhena on 2026-10-18 for the US-85 E1 wording. Security criteria approved by mjbvilhena on 2026-10-18. Alarms are inexact.
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
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- An empty title fills from the page title or the file name. Camera capture uses the system camera. Lagoon does not request `CAMERA`.
- Upcoming is the one reminder or the one expiry. Alarms are inexact.

## Open questions

- Sync conflicts, and how a deletion propagates. US-73. Proposed ADR-0002. Not accepted.
- How the vault key reaches a new device. US-74. Proposed ADR-0001. Not accepted. Amendment approved by mjbvilhena on 2026-10-18. A second device re-creates the same key from the passphrase.
- Topic provider, retention and training, how the credential is supplied, and what is sent. US-76. Proposed ADR-0003. Not accepted.

## Resolved

- One-sided items are kept. Conflicts and deletions are US-73. Criteria follow from the ADR at the design gate.
- Encryption at rest, and ciphertext in Drive. Key transport is US-74. Criteria follow from the ADR at the design gate.
- The vault key is a hardware-backed Android Keystore key that does not require user authentication. Background sync and reminders can decrypt while the phone is locked. Sensitive content still needs the US-37 unlock.
- There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off.
- A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock.
- Reminders follow the phone's current local zone. A 09:00 reminder fires at 09:00 wherever the phone is.
- English-only OCR. Inexact alarms. No Back up now.
- One tap on an image or PDF page opens it full-screen at maximum brightness, with pinch-zoom and the screen kept on. Brightness goes back to normal when you close it. It works offline. A sensitive item needs unlocking first and is protected from screenshots (US-85). Amendment approved by mjbvilhena on 2026-10-18.
