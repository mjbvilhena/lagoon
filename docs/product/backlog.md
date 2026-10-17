# Product backlog

> Statuses describe evidence in the tree on 2026-10-17. **Draft is not approval.** **Not started is not a skip.** A line marked Draft means the file exists and has not been signed.
> No story is Done. There is no app, no test run, and no named sign-off.
> Product decisions and bot recommendations recorded in the specification are not sign-off.
> The specification is **Draft, pending review by `mjbvilhena`.** No approval of it was recorded.

## Gate

Stopped at **product specification approval**. Approver: `mjbvilhena`, pending. See `docs/sdlc/pipeline-state.md`.

On 2026-10-17 Miguel delegated story approval to his bots. He decides epic-level and security matters only. That rule does not approve any story, and the epic gate is not prepared while the specification is still Draft.

## Epic E1: Capture and keep

> Goal: Miguel can save a link, text, an image, or a PDF from the share sheet in one tap, file it with last-used tags afterwards, add a note or URL himself, and attach more than one file. *(Draft — not approved)* Evidence: `docs/product/epics/E1-capture-and-keep.md`.

- **US-01** *(Draft)* Share a link. A normalised repeat URL updates the existing item. Evidence: `docs/product/stories/US-01-share-a-link.md`. Not implemented. Bots' consensus, not approval.
- **US-02** *(Draft)* Share plain text. Evidence: `docs/product/stories/US-02-share-plain-text.md`. Not implemented.
- **US-03** *(Draft)* Share an image. Evidence: `docs/product/stories/US-03-share-an-image.md`. Not implemented.
- **US-04** *(Draft)* Share a PDF, including text-layer extraction. Evidence: `docs/product/stories/US-04-share-a-pdf.md`. Not implemented. Extraction is not OCR.
- **US-05** *(Draft)* Add a note by hand. Evidence: `docs/product/stories/US-05-add-a-note-by-hand.md`. Not implemented.
- **US-06** *(Draft)* Add a URL by hand. Evidence: `docs/product/stories/US-06-add-a-url-by-hand.md`. Not implemented.
- **US-07** *(Draft)* Offline use, no analytics. Evidence: `docs/product/stories/US-07-offline-and-no-analytics.md`. Not implemented.
- **US-28** *(Draft)* Last-used tag chips after the one-tap save. Evidence: `docs/product/stories/US-28-quick-add-sheet.md`. Not implemented. Bots' consensus on Q9. Not a blocking sheet. Not approval.
- **US-32** *(Draft)* More than one attachment on an item. Evidence: `docs/product/stories/US-32-multiple-attachments.md`. Not implemented. Miguel's v1 decision. Not approval.
- **US-40** *(Draft)* Fill an empty title from the page title or file name. Evidence: `docs/product/stories/US-40-autofill-title.md`. Not implemented. Bots' consensus on Q9. Not approval.

## Epic E2: Link enrichment

> Goal: A saved link gains page details and an offline snapshot when a fetch works, and always keeps its URL. *(Draft — not approved)* Evidence: `docs/product/epics/E2-link-enrichment.md`.

- **US-08** *(Draft)* Enrich a link. Evidence: `docs/product/stories/US-08-enrich-a-link.md`. Not implemented.
- **US-09** *(Draft)* Keep the URL when enrichment fails. Evidence: `docs/product/stories/US-09-keep-the-url-when-enrichment-fails.md`. Not implemented.
- **US-33** *(Draft)* Offline snapshot: one sanitised readable archive, about 10 MB, original URL kept. Evidence: `docs/product/stories/US-33-offline-page-snapshot.md`. Not implemented. Miguel's v1 decision plus the bots' snapshot shape. Not approval. MHTML is later.

## Epic E3: Find and curate

> Goal: He can browse, sort and filter by date saved, see an Untagged / Inbox view, read, edit, delete with undo, manage tags including a confirmed merge, search, and filter by type and tag. *(Draft — not approved)* Evidence: `docs/product/epics/E3-find-and-curate.md`.

- **US-10** *(Draft)* Home list, newest first. Evidence: `docs/product/stories/US-10-browse-newest-first.md`. Not implemented.
- **US-11** *(Draft)* Item detail. Evidence: `docs/product/stories/US-11-read-an-item.md`. Not implemented.
- **US-12** *(Draft)* Edit, including why it matters. Evidence: `docs/product/stories/US-12-edit-an-item.md`. Not implemented.
- **US-13** *(Draft)* Delete with undo. Evidence: `docs/product/stories/US-13-delete-with-undo.md`. Not implemented.
- **US-14** *(Draft)* Tag management. Evidence: `docs/product/stories/US-14-manage-tags.md`. Not implemented.
- **US-15** *(Draft)* Ranked, highlighted search. Weighted BM25 as recommended. Evidence: `docs/product/stories/US-15-search.md`. Not implemented. The Room 3 / FTS5 / SQLCipher spike is a design-gate risk, not decided. Not approval.
- **US-16** *(Draft)* Filter by type and tag. Evidence: `docs/product/stories/US-16-filter-by-type-and-tag.md`. Not implemented.
- **US-29** *(Draft)* Rename a tag everywhere. A case-insensitive clash asks, then merges. Evidence: `docs/product/stories/US-29-rename-a-tag.md`. Not implemented. Bots' consensus on Q4. Not approval.
- **US-38** *(Draft)* Untagged / Inbox view. Evidence: `docs/product/stories/US-38-untagged-inbox.md`. Not implemented. Bots' consensus on Q9. A view, not a stored Inbox tag. Not approval.
- **US-39** *(Draft)* Sort and filter by date saved. Evidence: `docs/product/stories/US-39-sort-and-filter-by-date.md`. Not implemented. Bots' consensus on Q9. Not approval.

## Epic E4: Seamless sync

> Goal: Background sync to Drive `appDataFolder`. No Back up now, no Restore, no last-backup-time control. A new install receives the vault and merges. Unmetered-only preference. Zip fallback. *(Draft — not approved)* Evidence: `docs/product/epics/E4-private-backup.md`. The file name is unchanged. The epic is sync.

- **US-17** *(Draft)* Sync not configured. Evidence: `docs/product/stories/US-17-backup-not-configured.md`. Not implemented. Missing OAuth must not crash.
- **US-18** *(Draft)* Sign in and background sync. Evidence: `docs/product/stories/US-18-back-up-now.md`. Not implemented. Drive API `appDataFolder`, not Android Auto Backup. Hidden in the Drive UI.
- **US-19** *(Draft)* Unmetered-only background sync, default on. Evidence: `docs/product/stories/US-19-automatic-backup.md`. Not implemented. `WorkManager` `UNMETERED`. Bots' consensus, kept because it still fits background sync.
- **US-30** *(Draft)* Absence of manual backup and restore controls. Evidence: `docs/product/stories/US-30-backup-size-warning.md`. Not implemented. No size warning and no roaming warning, because those belonged to Back up now.
- **US-20** *(Draft)* New install or second device receives the vault and merges one-sided items. Evidence: `docs/product/stories/US-20-restore-from-drive.md`. Not implemented. Q5 is resolved as merge. Concurrent edits and deletions stay open for Miguel.
- **US-21** *(Draft)* Zip export and import that merges. Evidence: `docs/product/stories/US-21-export-and-import-a-zip.md`. Not implemented. Must not be a plaintext hole beside encryption. The conflict algorithm is still open.

## Epic E5: Look and feel

> Goal: Calm Material 3 light and dark UI. Teal accent with the contrast rules. Dynamic color is an optional toggle, off by default, teal as the fallback. Icon and the name Lagoon. *(Draft — not approved)* Evidence: `docs/product/epics/E5-look-and-feel.md`. UX artefact not started.

- **US-22** *(Draft)* Light and dark appearance. Evidence: `docs/product/stories/US-22-calm-appearance.md`. Not implemented. Bots' consensus: light about `#0F766E`, dark about `#2DD4BF`, dark `onPrimary` (black or `#003731`). Never use `#0F766E` on dark surfaces. Not approval.
- **US-23** *(Draft)* Icon and the name Lagoon. Evidence: `docs/product/stories/US-23-icon-and-name.md`. Not implemented. Joint recommendation of Scout and Mikey's Vault App Bot. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done. Not approval.

## Epic E6: Installable release

> Goal: Signed sideload APK, the named tests, README, and screenshots when an emulator exists. *(Draft — not approved)* Evidence: `docs/product/epics/E6-installable-release.md`.

- **US-24** *(Draft)* Signed release APK. Evidence: `docs/product/stories/US-24-signed-apk.md`. Not implemented. `minSdk` 26. `compileSdk` and `targetSdk` 37 (bots' consensus on Q8, not approval). Sideload developer verification stays an open question for Miguel.
- **US-25** *(Draft)* Search, sync/zip packaging, and a basic Compose test. Evidence: `docs/product/stories/US-25-automated-tests.md`. Not implemented. Packaging tests cover ciphertext, not a live Drive call.
- **US-26** *(Draft)* README for build, sideload, and OAuth. Evidence: `docs/product/stories/US-26-readme-build-and-oauth.md`. The root README does not yet contain those steps, because there is no app. That absence is correct. Recommended application id `com.mjbvilhena.lagoon` (Q2, joint recommendation of Scout and Mikey's Vault App Bot, not approval). SHA-1 is still unknown. The README must not document a Back up now or a Restore button.
- **US-27** *(Draft)* Screenshots. Evidence: `docs/product/stories/US-27-screenshots.md`. Not started. No images were fabricated. Settings shows sync state, not a backup button.

## Epic E7: On-device text recognition

> Goal: In v1. Recognize text in screenshots, images, and scanned PDFs on the device so search can find it. *(Draft — not approved)* Evidence: `docs/product/epics/E7-on-device-text-recognition.md`. Miguel's decision. Not approval. Text-layer PDFs stay in US-04 and are not OCR.

- **US-31** *(Draft, in v1)* On-device Latin-script recognition, bundled ML Kit model. Evidence: `docs/product/stories/US-31-on-device-text-recognition.md`. Not started. Non-Latin scripts are later. Booking references and merchants are not this story. Not approval.

## Epic E8: Encryption and sensitive items

> Goal: Encrypt the database and attachment files at rest, keys in Android Keystore. Sync only ciphertext. Biometric lock on items marked sensitive. *(Draft — not approved)* Evidence: `docs/product/epics/E8-encryption-and-sensitive-items.md`. His Q10 words plus the sensitive-item decision. Not approval. Cipher waits for an ADR. Threat model not started.

- **US-35** *(Draft)* Encrypt the vault at rest. Evidence: `docs/product/stories/US-35-encrypt-at-rest.md`. Not implemented.
- **US-36** *(Draft)* Sync only ciphertext. Evidence: `docs/product/stories/US-36-sync-ciphertext.md`. Not implemented.
- **US-37** *(Draft)* Biometric lock on items marked sensitive. Evidence: `docs/product/stories/US-37-biometric-lock.md`. Not implemented. App-wide unlock is a separate open question for Miguel.

## Epic E9: Reminders and expiry

> Goal: Optional expiry or reminder date on an item, with a notification. *(Draft — not approved)* Evidence: `docs/product/epics/E9-reminders-and-expiry.md`. Miguel's v1 decision. Not approval.

- **US-34** *(Draft)* Reminder or expiry with a notification. Default 09:00 local, per-item time, expiry at 7 days and on the day, inexact alarms. Evidence: `docs/product/stories/US-34-reminder-or-expiry.md`. Not implemented. Bots' consensus, not approval.

## Epic E10: Trip and collection grouping

> Goal: Later, not v1. Group items into a trip or collection with a date range. *(Draft — not in v1 — not approved)* Evidence: `docs/product/epics/E10-trip-and-collection-grouping.md`. Miguel's decision that this is later. No stories. Do not build it in v1.

## Specification and vision

- **Vision** *(Draft — not accepted)* Evidence: `docs/product/vision.md`. It restates the original request, including backup and restore. Later decisions in the specification supersede that wording. They do not accept the vision.
- **Product specification** *(Draft — pending review by `mjbvilhena`)* Evidence: `docs/product/specification.md`. Not approved.

## Repository setup

Checks from the harness mapping are in the tree. That is not product approval. Kotlin CI was not added. Evidence: `docs/sdlc/repository-setup.md`, `.github/workflows/secret-scan.yml`, `.github/workflows/markdownlint.yml`, `.github/CODEOWNERS`, `.gitignore`, `README.md`.

- **Harness install** *(In place)* Cursor skills under `.cursor/commands/`. Submodule `third_party/ai-sdlc-harness` at `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`. Evidence: `docs/sdlc/harness-install.md`.

## Not started, and not skipped

These need a later explicit go-ahead after the gates. No skip sentence was recorded.

- UX design (`sdlc-ux-designer`)
- Technical design: RFC, API design, ADRs (`sdlc-rfc-drafter`, `sdlc-api-designer`, `sdlc-adr-drafter`), including the cipher
- Test strategy and threat model (`sdlc-test-planner`, `sdlc-threat-modeler`). A threat model is required before implementation because of encryption, biometric lock, and Drive sync. It is not written.
- `DOMAIN.md` / `LAYER.md` (no bound has been designed, so the architect skills were not run, and no MUST/NEVER rules were invented)
- App implementation (`sdlc-implementer`)
- Verification skills (test writer, UI tests beyond the plan, code review, security review)

## Proposed, no stories

- Full-screen QR and barcode display. He has not decided.
- Picking out booking references and merchants. Later, unless it falls out of OCR with no extra product scope.

## Open questions

### For Miguel at the design gate (security, data integrity, or release)

Still open. Assigned to the technical design, ADR, and threat-model gate. He answers them there. That gate has not started.

- Sync edit conflicts: the same item edited on two devices.
- How a deletion on one device propagates.
- How keys reach a new or second device.
- What happens if the key or passphrase is lost.
- Whether unlocking the whole app is required, separate from the sensitive-item lock.
- Android developer verification for sideloaded APKs (enforced globally in 2027; ADB installs are exempt).

### Story-level recommendations (not approval)

Answered as consensus from Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot. Stories stay Draft. See US-01, US-15, US-31, US-33, and US-34. The FTS5 / Room 3 / SQLCipher question on US-15 stays a design-gate spike, not a decision.

### Resolved

- **Q5.** Sync and zip import merge. One-sided items are kept. Concurrent edits and deletions are the open questions above.
- **Q10.** Extra encryption: at rest, and ciphertext only in Drive. Key transport, loss, and app unlock remain open.
