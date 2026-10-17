# Product backlog

> Statuses describe evidence in the tree on 2026-10-17. **Draft is not approval.** **Not started is not a skip.** A line marked Draft means the file exists and has not been signed.
> No story is Done. There is no app, no test run, and no named sign-off.
> Product decisions and bot recommendations recorded in the specification are not the sign-off of the stories. The epics have their own approval, recorded below.
> The specification is **Approved** by `mjbvilhena` on 2026-10-17 at 12:36 IST. Wording: "Approved". Revision: `c288b7e`.

## Gate

Spec gate passed. Epic gate passed. Stopped at **story review and approval** of US-01–US-84 by Miguel's bots. Stories stay Draft. Round-2 verdicts at `40bb526` are recorded in `docs/product/stories/REVIEW-PACK.md` and are not a status of Approved. See `docs/sdlc/pipeline-state.md`.

Epics E1–E11 are **Approved** by `mjbvilhena` on 2026-10-17 at 13:29 IST. Wording: "All makes sense", then "Yes, approved". Revision: `98275b3`. E10 is Approved as Later, not in v1.

On 2026-10-17 Miguel delegated story approval to his bots. He decides epic-level and security matters only. That rule does not approve any story. Round 2 records Dev Bot, Scout, and Mikey's Assistant verdicts as approvals under his delegation of 2026-10-17, and Household and Leisure as recommendations only. No story status was flipped to Approved. He approved `docs/product/amendments/2026-10-17-security-delegation.md` on 2026-10-17 at 15:55 IST. Wording: "Yes". That approval does not approve the proposed criteria on US-64, US-75, US-78, US-79, US-80, or US-81.

## Epic E1: Capture and keep

> Goal: Miguel can save a link, text, an image, or a PDF from the share sheet in one tap, file it with last-used tags afterwards, add a note or URL himself, and attach more than one file. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E1-capture-and-keep.md`.

- **US-01** *(Draft)* Share a link. A normalised repeat URL updates the existing item. Evidence: `docs/product/stories/US-01-share-a-link.md`. Not implemented. Bots' consensus, not approval.
- **US-02** *(Draft)* Share plain text. Evidence: `docs/product/stories/US-02-share-plain-text.md`. Not implemented.
- **US-03** *(Draft)* Share an image. Evidence: `docs/product/stories/US-03-share-an-image.md`. Not implemented.
- **US-04** *(Draft)* Share a PDF, including text-layer extraction. Evidence: `docs/product/stories/US-04-share-a-pdf.md`. Not implemented. Extraction is not OCR.
- **US-05** *(Draft)* Add a note by hand. Evidence: `docs/product/stories/US-05-add-a-note-by-hand.md`. Not implemented.
- **US-06** *(Draft)* Add a URL by hand. Evidence: `docs/product/stories/US-06-add-a-url-by-hand.md`. Not implemented.
- **US-07** *(Draft)* Offline use, no analytics library the app chooses. ML Kit metrics are disclosed and accepted before the first OCR use, decided 2026-10-17 at 16:00 IST. Not story approval. Evidence: `docs/product/stories/US-07-offline-and-no-analytics.md`. Not implemented.
- **US-28** *(Draft)* Last-used tag chips after the one-tap save. Evidence: `docs/product/stories/US-28-quick-add-sheet.md`. Not implemented. Bots' consensus on Q9. Not a blocking sheet. Not approval.
- **US-32** *(Draft)* More than one attachment on an item. Evidence: `docs/product/stories/US-32-multiple-attachments.md`. Not implemented. Miguel's v1 decision. Not approval.
- **US-40** *(Draft)* Fill an empty title from the page title or file name. Evidence: `docs/product/stories/US-40-autofill-title.md`. Not implemented. Bots' consensus on Q9. Not approval.

## Epic E2: Link enrichment

> Goal: A saved link gains page details and an offline snapshot when a fetch works, and always keeps its URL. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E2-link-enrichment.md`.

- **US-08** *(Draft)* Enrich a link. Evidence: `docs/product/stories/US-08-enrich-a-link.md`. Not implemented.
- **US-09** *(Draft)* Keep the URL when enrichment fails. Evidence: `docs/product/stories/US-09-keep-the-url-when-enrichment-fails.md`. Not implemented.
- **US-33** *(Draft)* Offline snapshot: one sanitised readable archive, about 10 MB, original URL kept. Evidence: `docs/product/stories/US-33-offline-page-snapshot.md`. Not implemented. Miguel's v1 decision plus the bots' snapshot shape. Not approval. MHTML is later.

## Epic E3: Find and curate

> Goal: He can browse, sort and filter by date saved, see an Untagged / Inbox view, read, edit, delete with undo, manage tags including a confirmed merge, search, and filter by type and tag. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E3-find-and-curate.md`.

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

> Goal: Background sync to Drive `appDataFolder`. No Back up now, no Restore, no last-backup-time control. A new install receives the vault and merges. Unmetered-only preference. Zip fallback. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E4-seamless-sync.md`.

- **US-17** *(Draft)* Sync not configured. Evidence: `docs/product/stories/US-17-sync-not-configured.md`. Not implemented. Missing OAuth must not crash.
- **US-18** *(Draft)* Sign in. Evidence: `docs/product/stories/US-18-sign-in-and-sync.md`. Not implemented. The specification names `appDataFolder`. Drive scope and sign-out are US-78. The criteria stay proposed.
- **US-19** *(Draft)* Unmetered-only background sync, default on. Evidence: `docs/product/stories/US-19-unmetered-sync.md`. Not implemented. `WorkManager` `UNMETERED`.
- **US-30** *(Draft)* Absence of manual backup and restore controls. Evidence: `docs/product/stories/US-30-no-manual-backup.md`. Not implemented.
- **US-20** *(Draft)* New install receives one-sided items. Evidence: `docs/product/stories/US-20-receive-the-vault.md`. Not implemented. Conflicts and deletions are US-73, still open for Miguel.
- **US-21** *(Draft)* Zip export encrypted with the vault key. Evidence: `docs/product/stories/US-21-export-a-zip.md`. Not implemented. Miguel, 2026-10-17 14:56 IST. Import is US-52.
- **US-51** *(Draft)* Background sync worker and plain-language errors. Evidence: `docs/product/stories/US-51-sync-worker-and-errors.md`. Not implemented. Sign-out is US-78. The criteria stay proposed.
- **US-52** *(Draft)* Import a zip and merge one-sided items. Evidence: `docs/product/stories/US-52-import-a-zip.md`. Not implemented.

## Epic E5: Look and feel

> Goal: Calm Material 3 UI with an in-app theme setting: Light, Dark, or System. System is the default and follows Android. Teal accent with the contrast rules. Dynamic color is an optional toggle, off by default, teal as the fallback. Icon and the name Lagoon. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E5-look-and-feel.md`. UX artefact not started. His epic-review words on 2026-10-17 at 13:15 IST are in the approved revision. US-22 stays Draft.

- **US-22** *(Draft)* Light, Dark, or System theme. System is the default. Evidence: `docs/product/stories/US-22-calm-appearance.md`. Not implemented. Not story approval. Accent: light about `#0F766E`, dark about `#2DD4BF`, dark text on the dark accent (black or `#003731`). Never use `#0F766E` on dark surfaces.
- **US-23** *(Draft)* Icon and the name Lagoon. Evidence: `docs/product/stories/US-23-icon-and-name.md`. Not implemented. Joint recommendation of Scout and Mikey's Vault App Bot. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done. Not approval.

## Epic E6: Installable release

> Goal: Signed sideload APK, the named tests, README, and screenshots when an emulator exists. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E6-installable-release.md`.

- **US-24** *(Draft)* Signed release APK. Evidence: `docs/product/stories/US-24-signed-apk.md`. Not implemented. `minSdk` 26. `compileSdk` and `targetSdk` 37 (bots' consensus on Q8, not approval). Sideload developer verification stays an open question for Miguel.
- **US-25** *(Draft)* Search, sync/zip packaging, and a basic Compose test. Evidence: `docs/product/stories/US-25-automated-tests.md`. Not implemented. Packaging tests cover ciphertext, not a live Drive call.
- **US-26** *(Draft)* README for build, sideload, and OAuth. Evidence: `docs/product/stories/US-26-readme-build-and-oauth.md`. The root README does not yet contain those steps, because there is no app. That absence is correct. Recommended application id `com.mjbvilhena.lagoon` (Q2, joint recommendation of Scout and Mikey's Vault App Bot, not approval). SHA-1 is still unknown. The README must not document a Back up now or a Restore button.
- **US-27** *(Draft)* Screenshots. Evidence: `docs/product/stories/US-27-screenshots.md`. Not started. No images were fabricated. Settings shows sync state, not a backup button.

## Epic E7: On-device text recognition

> Goal: In v1. Recognize text in screenshots, images, and scanned PDFs on the device so search can find it. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E7-on-device-text-recognition.md`. Miguel's v1 decision. US-31 stays Draft. Text-layer PDFs stay in US-04 and are not OCR.

- **US-31** *(Draft, in v1)* On-device English recognition. The bundled ML Kit Latin-script model still fits. The first OCR use waits on the US-07 disclosure. Evidence: `docs/product/stories/US-31-on-device-text-recognition.md`. Not started. Mikey's Dev Bot's revised recommendation: the Dev Bot reports Miguel said in chat that English is enough. Not approval. Acceptance tests are English only. Booking references and merchants are not this story.

## Epic E8: Encryption and sensitive items

> Goal: Encrypt the database and attachment files at rest, keys in Android Keystore. Sync only ciphertext. Biometric lock on items marked sensitive. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E8-encryption-and-sensitive-items.md`. His Q10 words plus the sensitive-item decision. Stories stay Draft. Cipher waits for an ADR. Threat model not started.

- **US-35** *(Draft)* Encrypt at rest. Lost key means the content is lost. Evidence: `docs/product/stories/US-35-encrypt-at-rest.md`. Not implemented. Miguel, 2026-10-17 14:56 IST.
- **US-36** *(Draft)* Sync only ciphertext. Evidence: `docs/product/stories/US-36-sync-ciphertext.md`. Not implemented. Key transport is US-74.
- **US-37** *(Draft)* Sensitive items use a biometric. App lock is optional, with the device PIN when there is no biometric. Evidence: `docs/product/stories/US-37-biometric-lock.md`. Not implemented. Miguel, 2026-10-17 14:56 IST. What a sensitive item reveals is US-75. The lock-screen reminder is decided. The other criteria stay proposed.

## Epic E9: Reminders and expiry

> Goal: Optional expiry or reminder date on an item, with a notification. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E9-reminders-and-expiry.md`. Miguel's v1 decision. US-34 stays Draft.

- **US-34** *(Draft)* Set a reminder. Inexact alarms. Lock-screen title and codes for an ordinary item. Evidence: `docs/product/stories/US-34-set-a-reminder.md`. Not implemented. Miguel, 2026-10-17 14:56 IST, on the lock screen. Exact alarms are US-77.
- **US-54** *(Draft)* Expiry notices, 7 days and the day. Evidence: `docs/product/stories/US-54-expiry-notices.md`. Not implemented.
- **US-55** *(Draft)* Reschedule after boot, clock, update, or permission change. Evidence: `docs/product/stories/US-55-reschedule-reminders.md`. Not implemented.

## Epic E11: Topics

> Goal: In v1. Identify main ideas and topics when an item is shared or added. Link and extend a topic that already exists. Ask before creating a new one. Cloud AI, with readable content leaving the phone. Drive sync stays ciphertext. *(Approved by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E11-topics.md`. His words on 2026-10-17 at about 11:57 IST: "Use a cloud AI for all items; I accept the content leaves the phone." Stories below are Draft. They do not decide provider, credential storage, sensitive-item exclusion, or payload. Do not implement.

- **US-41** *(Draft)* Extract topics when an item is saved. Evidence: `docs/product/stories/US-41-extract-topics-on-save.md`. Not implemented. The four design-gate questions stay open.
- **US-42** *(Draft)* Link an item to a topic that already exists and extend that topic's notes. Evidence: `docs/product/stories/US-42-link-an-existing-topic.md`. Not implemented.
- **US-43** *(Draft)* Ask before creating a topic. Decline creates nothing. Evidence: `docs/product/stories/US-43-confirm-a-new-topic.md`. Not implemented.
- **US-44** *(Draft)* Save offline and queue topic processing. Evidence: `docs/product/stories/US-44-queue-topics-offline.md`. Not implemented.
- **US-45** *(Draft)* Open a topic and see its items and summary. Evidence: `docs/product/stories/US-45-read-a-topic.md`. Not implemented.
- **US-46** *(Draft)* Reject, rename, or merge a suggested topic. Evidence: `docs/product/stories/US-46-reject-rename-or-merge-a-suggestion.md`. Not implemented. Does not merge two topics that already exist.
- **US-47** *(Draft)* A failed topic call leaves the item and creates no topic. Evidence: `docs/product/stories/US-47-topic-call-fails.md`. Not implemented.

## Epic E10: Trip and collection grouping

> Goal: Later, not v1. Group items into a trip or collection with a date range. *(Approved as Later, not in v1, by `mjbvilhena`, 2026-10-17 13:29 IST, revision `98275b3`)* Evidence: `docs/product/epics/E10-trip-and-collection-grouping.md`. Miguel's decision that this is later. No stories. Do not build it in v1.

## Specification and vision

- **Vision** *(Current vision, 2026-10-17 15:55 IST)* Evidence: `docs/product/vision.md`. It states the approved specification, epics E1–E9 and E11, and the approved amendment. It does not describe backup or restore controls.
- **Product specification** *(Approved by `mjbvilhena`, 2026-10-17 12:36 IST, wording "Approved", revision `c288b7e`. Amendment sections 1 and 6 applied after his approval at 15:55 IST, wording "Yes".)* Evidence: `docs/product/specification.md`. This does not approve the stories.

## Repository setup

Checks from the harness mapping are in the tree. That is not product approval. Evidence: `docs/sdlc/repository-setup.md`, `.github/workflows/qa.yml` (Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, CodeQL), `.github/CODEOWNERS`, `.gitignore`, `README.md`. The older `.github/workflows/secret-scan.yml` and `.github/workflows/markdownlint.yml` files are not in the tree.

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
- OCR for languages other than English. Later option. v1 acceptance tests stay English-only (US-31).
- Picking out booking references and merchants. Later, unless it falls out of OCR with no extra product scope.

## Stories split or added on 2026-10-17

The index is the full list. These ids were added while refining. All Draft. Not implemented.

- Splits: US-49 repeat link, US-50 ranking, US-51 sync worker, US-52 import, US-53 palette and motion, US-54 expiry, US-55 reschedule.
- Gaps: US-48 manage topics, US-56 stable ids, US-57 FTS5 spike, US-58 pipeline, US-59 Settings, US-60 topic list, US-61 source (Should, now in E1), US-62 upcoming (Should), US-63 copy a code (Should), US-65 camera (Should), US-66 trash (Should), US-67 duplicate files (Could), US-68 return-by and warranty dates (Should), US-69 pin one item (Could), US-70 offline check (Could), US-71 inbox batch (Could), US-72 topic evaluation (Could).
- Proposed criteria, pending Miguel's approval, status still Draft: US-64 sharing out (Should), US-75 sensitive-item handling, US-78 Drive scope and sign-out, US-79 link-preview skipping, US-80 permissions, US-81 sideload policy.
- Still open for Miguel, no asserting criterion: US-73 conflicts and deletions, US-74 key transport, US-76 topic-call privacy, US-77 exact alarms.
- Split from US-68 in round 2: US-82 merchant, US-83 amount and currency, US-84 purchase date. All Should. All Draft.

## Open questions

The consolidated list is `docs/product/stories/REVIEW-PACK.md`. Nothing below is a story approval.

### Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 14:56 IST

- Lost vault key: the content is lost. No recovery. US-35.
- App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
- Lock-screen notifications may show the item title and codes. US-34.
- The zip export is encrypted with the vault key. US-21.

### Still open for Miguel

- Sync conflicts, and how a deletion propagates. US-73. Scout's option is recorded there and not chosen.
- How the vault key reaches a new device. US-74.
- Exact alarms, which would amend E9. US-77. Stories keep inexact alarms.
- Topic provider, retention and training, how the credential is supplied, and what is sent. US-76.
- Whether 09:00 should follow a fixed zone instead of the phone. Not decided. Stories use the device local zone.
- Full-screen QR and barcode display. No story.
- US-28 keeps Add date as a Must. Not changed.
- US-82, US-83, and US-84 stay Should. US-68 stays the date story. Not changed.
- Whether the vault key is bound to device authentication. US-35 does not choose it.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 15:59 IST. Not story approval.

- A sensitive lock-screen reminder shows only "Lagoon reminder", with no title or codes until unlock. US-75.
- A token-looking URL is not auto-fetched. A foggy placeholder is shown until he enables that preview. US-79. The other preview rules stay proposed.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 16:00 IST. Not story approval. His words: "Requirement will be to let the user choose to disable (if possible), otherwise will have to disclose and accept".

- Official Google docs do not document a disable switch for ML Kit usage or diagnostic telemetry. US-07 requires a disclosure he accepts before the first OCR use. ML Kit is not started until he accepts, and he can withdraw that acceptance in Settings (US-59). Research: `docs/product/research/2026-10-17-ml-kit-telemetry.md`.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 18:05 IST. Not story approval. His words: "Topics on by default, with an off switch".

- Topic extraction starts on for a new ordinary item. He can turn it off in Settings. US-41 and US-59. A sensitive item stays excluded (US-75). The topic job keeps its own unmetered setting (US-44). This is not a new specification approval.

### Delegated to Miguel's bots on 2026-10-17 at 14:56 IST

He did not decide these. Round 2 proposes criteria on the stories, marked pending his approval. The proposals are not approval.

- Sensitive-item handling in lists, search, export, the cloud call, and screenshots. US-75. The lock-screen reminder is decided at 15:59 IST. The other criteria stay proposed.
- Drive scope and sign-out. US-78. The specification still names `appDataFolder`.
- Link-preview skipping. US-79. Token-looking URLs are decided at 15:59 IST. The host rules stay proposed.
- Sharing out. US-64. Epic E3 includes it as a Should. The criteria stay proposed.
- Permissions. US-80.
- Sideloading. US-81. US-24 still requires a signed APK because E6 does.

### Resolved earlier

- **Q5.** One-sided items are kept. Conflicts and deletions stay open.
- **Q10.** Encryption at rest and ciphertext in Drive. Loss is now decided, above. Key transport stays open.
- English-only OCR. Inexact alarms unless he amends E9. OCR search weight equals body. URL normalisation is the specification's rule. No Back up now.
