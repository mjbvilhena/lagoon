# US-35 vault key plan

**Status: Proposed.** Needs approval by mjbvilhena. This file is a plan. It does not implement a story. It does not mark a story Done. Approving this file is a typed sentence from mjbvilhena. A merged pull request is not that approval.

mjbvilhena said on 2026-11-28 to start planning US-35. That sentence starts this plan. It is not approval of the plan, and it is not permission to write production code.

Conductor: `sdlc-conductor`. Child skills for this draft: `sdlc-test-planner` and `sdlc-migration-planner`. `sdlc-implementer` does not start. Harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`. Master at the start of this plan is `e2f177926a19d4bb229220cacf1dbb0c1021d439`.

The `mcp` package was not running as a server in this environment. The template files those tools read were opened from that harness commit: `lifecycle pipeline`, `user story`, `test plan`, `e2e test plan`, `threat model`, `security review`, `pr`, `adr`, `migration plan`, and `ux design`. Definition of Done files opened were `user story`, `feature`, `pr`, `ui change`, `security change`, and `data migration`. `docs/domain/vault/DOMAIN.md` was read. Capture and knowledge have no `DOMAIN.md`. `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, and `docs/layers/data/LAYER.md` were read. No new domain or layer rule is written here.

## Order

Three slices, in this order. Each later slice waits until the earlier slice is in the tree. A slice is one implementation pull request. This plan does not open those pull requests.

1. Key storage for the US-35 vault key.
2. The encrypted on-disk vault. SQLCipher with Room, as accepted ADR-0008. FTS5 comes from SQLCipher's bundled SQLite, which Room opens.
3. Search on that encrypted database, together with US-92.

US-93 and US-57 are described after the three slices. US-93 is a follower of slice 2. US-57 is already Done.

## What is already decided

| Record | Status used by this plan |
| --- | --- |
| US-35 | Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena. Security criteria approved by mjbvilhena on 2026-10-18. Security criteria approved by mjbvilhena on 2026-10-22. Not implemented. |
| US-15, US-50 | Approved on 2026-10-18 by the same five on behalf of mjbvilhena. Not implemented. |
| US-56 | Done. The product opener is `openInMemoryVault` in `data/src/main/kotlin/com/mjbvilhena/lagoon/data/item/VaultDatabase.kt`. A file database is not opened. |
| US-57 | Done. `docs/technical_design/us-57-fts5-sqlcipher-spike.md` records pass. |
| US-92 | Approved on 2026-11-28 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant under Miguel's delegation of 2026-10-17. Artefact approval. Implementation is not Done. The Decisions paragraph still records that the 07:11 GMT sentence was not whole-story approval. The status line is the later approval. |
| US-93 | Approved the same way. The approval covers the non-security scope. Security criteria approved by mjbvilhena on 2026-11-28. That marker covers only the storage criterion. |
| ADR-0001, ADR-0002, ADR-0006 | Accepted. Approved by mjbvilhena on 2026-10-20. |
| ADR-0007 | Accepted. Approved by mjbvilhena on 2026-10-29. |
| ADR-0008 | Accepted. Approved by mjbvilhena on 2026-11-28. |
| UX, test plan, threat model, domain, three layers | Approved, on the dates already in those files. This plan does not edit them except the proposed test-plan rows named below. |
| Specification search sentence | Amendment approved by mjbvilhena on 2026-11-28. The live sentence is "FTS5 comes from SQLCipher's bundled SQLite, which Room opens." |

Placeholder US-74 stays a placeholder. Accepted ADR-0001 is the decision record. This plan does not add criteria to US-74.

Threats these slices sit under, all still Hypothesis or Accepted risk as written in `docs/technical_design/threat-model.md`: T-1 (bytes at rest), T-2 (software Keystore, accepted risk), T-6 (share before the key), T-14 (on-phone guesses), T-19 (offline guesses). A security review of an implementation diff is verify-time (`sdlc-security-reviewer`, template `security review`). This plan is not that review. It contains no exploit steps.

## Slice 1 — key storage

**Stories:** US-35, for the key, the passphrase, and the on-phone wrap. Accepted ADR-0001. The Keystore wrap in accepted ADR-0006.

**Where it lives:** `data.crypto`, as `docs/layers/data/LAYER.md` already names. The UI calls a domain use case. A composable or an Activity does not import Android Keystore. `:ui` does not depend on `:data`. `:app` wires the graph. That boundary is already `docs/layers/ui/LAYER.md`.

**What the slice stores:** native Argon2id, version 0x13, at the ADR-0001 floor (memory 65536 KiB, iterations 3, parallelism 1, salt 16 bytes, output 32 bytes) and inside the ceiling (memory 262144 KiB, iterations 10, parallelism 1, salt 16, output 32, version 0x13). A header outside those bounds does not run the KDF. Parameters are not clamped. The 32-byte output is the vault key. Android Keystore generates an AES-256 wrapping key with user authentication off. On API 28+ the key is created with `setUnlockedDeviceRequired(false)`. `setInvalidatedByBiometricEnrollment` is not set. The wrap is AES-256-GCM. The nonce comes from the Keystore and is stored with the wrap blob. The wrap blob stays on the phone. The clear header stores the KDF id, the salt, memory, iterations, parallelism, output length, and the key version. It does not store the key or the passphrase.

**Passphrase checks, before the KDF:** NFC, then 12 to 128 Unicode code points. A passphrase on the bundled common-password list is refused. There is no strength meter. The guidance sentence stays the UX sentence: "Choose a passphrase of 12 to 128 characters that you do not use anywhere else." The loss warning states that the key is derived from the passphrase, that forgetting it and losing the device means the content is lost, that uninstall or clear storage destroys the Keystore copy, and that losing the signing key forces a reinstall that destroys that copy too. The key is not created until he accepts. Decline of a waiting share tells him nothing was saved. The pending share stays in memory. `IME_FLAG_NO_PERSONALIZED_LEARNING`, a password input, and `FLAG_SECURE` are the passphrase screen. The passphrase is never logged and never written to disk.

**Wrong passphrase:** nothing changes. The next attempt waits 2, then 4, 8, 16, then 32 seconds, including after process death. Further wrong attempts stay at 32 seconds. The KDF does not run during the wait. A correct passphrase resets the delay. There is no permanent lockout.

**Hardware:** on API 31+ `KeyInfo.getSecurityLevel()` is StrongBox or TEE, and on API 26–30 `KeyInfo.isInsideSecureHardware()` is true, read on the wrapping key. `isUserAuthenticationRequired` is false. A phone whose Keystore cannot provide that level shows "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key." The vault is not created until he accepts. Decline deletes the software entry and writes no vault. After acceptance the same warning stays in Settings and is not a second gate. A hardware-backed key does not show it. A new biometric does not drop the key.

**Key gone:** if the Keystore entry is missing, or the vault cannot be unwrapped, he can enter the passphrase and the same key is derived from the clear header. No copy of the key is read from Drive or from anywhere else. That path is not the forgotten-passphrase reset.

**"I've forgotten my passphrase" on a phone that holds no key:** he is told the vault can't be opened on this phone without the passphrase, and he is offered a new empty vault. The Drive half of "a new empty vault" is blocked. See Gates.

**Existing rows this slice executes:** TP-01, TP-02, TP-03, TP-05, TP-07, TP-27, and journey J-9. Proposed rows TP-40, TP-41, and TP-42.

**Prototype hashes this implementation pull request checks:** `index.html#/passphrase`, `#/passphrase-hardware`, `#/passphrase-memory`, `#/passphrase-decline`, `#/pass-wrong`, and `#/settings-hardware`. The GitHub Pages rule is below.

### US-35 acceptance criteria in slice 1

| Scenario | How this slice covers it |
| --- | --- |
| the warning | Setup copy from the UX design. Accept creates the key. Decline writes nothing and, when a share is waiting, says nothing was saved. The share stays in memory. |
| a passphrase under the minimum | Refused before the KDF, including the common-password list and a length above 128. No strength meter. |
| the phone cannot meet the memory floor | A failed native allocation of 65536 KiB refuses setup. Parameters are not lowered. `isLowRamDevice()` is not the oracle. |
| what "key gone" means | He can re-create the key by entering the passphrase. |
| re-create from the passphrase | Same derivation, header from ADR-0001. No key is read from off the phone. |
| a new biometric does not drop the key | `setInvalidatedByBiometricEnrollment` is not set. |
| no user authentication on the key | `isUserAuthenticationRequired` is false. Hardware level, or the software warning. The flags that allow decrypt after the first unlock since boot are created here. |
| the passphrase field | Password input, `IME_FLAG_NO_PERSONALIZED_LEARNING`, `FLAG_SECURE`. Never logged, never written. |
| a wrong passphrase | Reported. Nothing is changed or deleted. The delay is TP-05. |
| I've forgotten my passphrase | The local sentence and the offer of a new empty vault. |
| after the first unlock since boot | On API 28+ `setUnlockedDeviceRequired(false)`. On API 26–27 the key is never unlock-gated. |
| which key is hardware-backed | The check reads the wrapping key. |
| a phone without secure hardware | The warning, the decline path, and the Settings sentence. |

## Slice 2 — encrypted on-disk vault

**Stories:** US-35 "data at rest", "derived files", and "he can still use the app". Accepted ADR-0006 for SQLCipher 4, the raw-key pragma, HMAC on, and `temp_store` MEMORY. Accepted ADR-0008 for `net.zetetic:sqlcipher-android:4.6.1` and for Room opening that library's SQLite. Accepted ADR-0007 for file bytes. Accepted ADR-0001 for the root the subkeys come from.

**Library:** `net.zetetic:sqlcipher-android:4.6.1` on `:data`, after the data-layer sentences in Gates are approved. Room stays `androidx.room:room-runtime:2.7.2`. Room's own bundled SQLite is not the vault database. HMAC stays on. The database subkey is `PRAGMA key = "x'<64 hex>'"`. The hex string is not a passphrase. `kdf_iter` is not set to 1 as a substitute. `PRAGMA temp_store = MEMORY`. HKDF-SHA256 derives the database subkey from the Argon2id root. The subkey is not stored. A file subkey is a different purpose and is not this database key.

The spike opened `net.zetetic.database.sqlcipher.SQLiteDatabase` directly. That is the evidence the library ran. The product opener is Room, which is what ADR-0008 requires. This plan does not name a Room helper class the spike did not use.

**What changes for a saved item:** while the key does not exist, `openInMemoryVault` stays the opener and vault bytes stay off disk. When the key exists, the vault database is a SQLCipher file. Saving and opening an ordinary item uses that file and does not ask for the passphrase. `allowBackup` is already false and `dataExtractionRules` already excludes app storage. `ReleaseManifestAllowlistTest` already asserts both. This slice does not reopen that manifest work.

**Files:** an attachment or a derived file, when one is written, uses Tink `AES256_GCM_HKDF_1MB` under the file subkey. A full ciphertext segment is 1048576 bytes. The segment number stays strictly below 2^32. This slice does not build thumbnail, preview, snapshot, OCR, or WebView producers. Those stay their own stories. The rule is that a write which does happen is ciphertext, and logs and WorkManager input do not contain the passphrase or the key.

**Migration:** there is no on-disk vault to convert. `openInMemoryVault` uses `Room.inMemoryDatabaseBuilder`. There is no plaintext database file. ADR-0006 key create encrypts an empty vault at generation 1. Rows that exist only in a running in-memory process are not copied into the file. Room schema version 2 and `ITEM_MIGRATION_1_2` stay the in-memory schema. They are not a dump of debug rows. Backup of those in-memory rows is unknown. There is no backup Restore in v1. Because the rows are not copied, that unknown backup does not block the empty encrypted file. A job that copied them would be a migration this plan refuses. Rehearsal of a row copy does not exist and is not required, because the copy does not run. Volume is not stated, because no rows move.

**Measurement:** when this slice's debug APK includes the library, record the APK size in bytes next to the spike figures ADR-0008 already accepted (CI spike APK 22,563,778 bytes, master debug APK at `483d5891359942e640697c0665626e1d23532ee1` of 2,374,931 bytes). The record is a number from the build. This plan sets no pass or fail size. `abiFilters` stay as they are. An arm64-only product APK was not a target of ADR-0008.

**Dependency scan:** `:data` does not declare this coordinate today. Adding it is a new resolved graph. The job fails closed on a high finding. This plan adds no vulnerability-exception line. A red Dependency scan stops the slice.

**Existing rows:** TP-06 and TP-31. Proposed rows TP-43 and TP-44.

**Prototype hashes:** `#/home` and `#/detail`, because save and open stay those screens. If the visible copy does not change, the pull request says which hashes were checked and does not restyle them.

### US-35 acceptance criteria in slice 2

| Scenario | How this slice covers it |
| --- | --- |
| data at rest | The database file is SQLCipher under the database subkey. A wrong key does not open it. The file does not start with `SQLite format 3`. |
| derived files | A file this slice writes is a Tink stream. Producers that do not exist yet are not built here. |
| he can still use the app | An ordinary item saves and opens. Normal use does not ask for the passphrase. |
| backup of app data is off | Already true in `app/src/main/AndroidManifest.xml` and in `ReleaseManifestAllowlistTest`. |

## Slice 3 — search on the encrypted database, with US-92

**Stories:** US-15, US-50, and US-92. The engine is the FTS5 table accepted ADR-0008 names: `tokenize = 'unicode61 remove_diacritics 2'`, columns `title`, `tags`, `note`, `body`, `extracted`, `ocr`, and `snapshot`. Ranking weights stay in the domain: title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. Snapshot text is searchable. The domain does not give snapshot text its own weight. US-50 gives snapshot text weight 1, the same as body. This plan uses that US-50 sentence. It does not add a new weight.

The index is inside the SQLCipher database from slice 2. There is no second plaintext index.

**US-15, ordinary items:** a match in title, body, personal note, tags, extracted text, the OCR column, or snapshot text returns that item. Highlight wraps the matched word in the snippet, and the snippet text is available to a screen reader. A query containing `"`, `-`, `*`, `AND`, or `NEAR` treats those tokens as plain words and does not crash. No match says nothing matched. An empty query follows the home-list rules and shows no error. US-15's scenarios use ordinary items. Sensitive results stay US-75.

**US-50:** a title match ranks above a tag match, and a tag match ranks above a body match, at the same term frequency and the same field length. An OCR-only match and a body-only match do not outrank each other on weight. A phrase ranks above the same words scattered. Recency orders a tie and does not beat a higher score. "cafe" and the prefix "caf" match "café". The same query twice returns the same order. US-39 can replace this order later. US-39 stays Draft. Relevance stays the default.

**US-92:** saving an edit re-indexes the edited field from the new text. The field may be the title, the body, a personal note, or a tag, and the item may be sensitive. For an ordinary item, a word the edit adds finds the item, a word it removes no longer finds the item because of that word, and a word it keeps still finds the item. The re-index writes only into the encrypted FTS5 table.

**Existing rows:** TP-09 and TP-37. Proposed rows TP-45, TP-46, and TP-47. Proposed journey J-13. J-1 also searches a title, and its repeat-URL and quick-add steps are US-49 and Draft US-28. Those steps are not this slice. J-1's "US-01 (Draft)" and "US-10 (Draft)" sentences are stale. US-01 and US-10 are approved. This plan does not edit J-1.

**Prototype hashes:** `#/search` and `#/search-miss`.

### Acceptance criteria in this slice

| Story scenario | Coverage |
| --- | --- |
| US-15 match in each field | Each named column, on an ordinary item, in the encrypted FTS5 table. |
| US-15 highlight | `highlight()` on the snippet, with the snippet text exposed to a screen reader. |
| US-15 punctuation in the query | `"`, `-`, `*`, `AND`, and `NEAR` are plain words. |
| US-15 no match | The empty-result sentence. |
| US-15 empty query | Home-list rules. No error. |
| US-50 field weights | Title, then tag, then body. |
| US-50 OCR equals body | Neither outranks the other on weight. |
| US-50 exact phrase | The phrase ranks above the scattered words. |
| US-50 recency is only a tie-break | Newer last-saved wins a tie and does not beat a higher score. |
| US-50 prefix and accents | `unicode61 remove_diacritics 2`. "cafe" and "caf" match "café". |
| US-50 the order is testable | The same seeded query twice has the same order. |
| US-92 an edited indexed field is re-indexed | Ordinary items, in full. The index update for a sensitive item is included. |

US-92 also says a search after the US-37 unlock finds the sensitive item. That clause waits on Draft US-37. Slice 3 does not mark US-92 Done. US-12 stays not Done.

## How US-93 fits

US-93 is approved. It is not one of the three slices. It starts only after slice 2, because the pending delete is held only in storage US-35 already encrypts. A plaintext file is not a substitute. There is no new key purpose.

The story's Must scenarios, already TP-38:

- After the undo snackbar is dismissed, the screen is left, or the snackbar times out, a kill before the trash write finishes still leaves the item in trash on the next launch. `trashedAt` and `trashChangedAt` are the instant the confirmation ended.
- A kill while the snackbar is still showing also completes the delete on the next launch. `trashedAt` and `trashChangedAt` are the instant the delete completes on that launch.
- `inTrash` is set. `updatedAt` is set as for any trash write. `userEditedAt` is unchanged.
- The pending delete does not sync. Building sync is Draft US-18. Meeting "does not sync" here means the pending record is not handed to a sync writer. This follower does not build Drive.

Security criteria approved by mjbvilhena on 2026-11-28 cover only that storage criterion. Implementing the follower is still app and data security, so it waits on his approval of this plan. It does not block slice 3. US-13 stays not Done until US-93 and US-94 are Done. US-94 is outside this plan.

Prototype hashes if that follower is later implemented: the undo snackbar and `#/trash`.

## How US-57 fits

US-57 is Done. The spike note records pass on both targets:

- API 26 emulator, `system-images;android-26;google_apis;x86_64`, on 2026-11-21.
- Miguel's samsung SM-S911B on 2026-11-27. The ABI list starts with `arm64-v8a`. Android is 17, API 37. The screen showed the success sentence only after `FtsSqlCipherProbe.runChecks` returned.

That phone run is the arm64 target. This plan does not schedule a second run on that phone. An arm64 emulator was not used in place of the phone, and this plan does not add one. The note does not choose or change the cipher in accepted ADR-0006. The product modules do not depend on `net.zetetic:sqlcipher-android:4.6.1` until slice 2. A later `sqlcipher-android` release, including 4.20.0, stays unused until the same two targets pass. That is ADR-0008 E1.

The specification still says "It is not yet verified with an encrypted database." The spike note is the verification record. This plan does not edit the specification.

## GitHub Pages prototype

Each implementation pull request for a slice above updates `docs/mockups` in that same pull request when the slice changes a screen the prototype shows. The hashes are listed on the slice. `docs/technical_design/ux-design.md` stays the source of truth. If the mockup and that file disagree, the file wins.

The pull request does not push to `gh-pages`. `.github/workflows/pages.yml` publishes `docs/mockups` from `master` after merge. The work-in-progress copy under `/wip/` stays the `gh-pages` branch. This plan does not dispatch that workflow and does not write that branch.

This planning pull request does not change `docs/mockups`. The screens it names are already in the prototype.

## Gates and decisions that need mjbvilhena

These touch app or data security. Delegated story approval is not a substitute. Each one needs his own typed approval. This plan does not write that approval.

1. **This plan, before any production code.** He said planning continues until he approves the plan in typed words.
2. **Slice 1, slice 2, slice 3, and the US-93 follower.** Each one stores or wraps the vault key, or writes ciphertext. `security change` Definition of Done applies at verify, including a human review. `sdlc-security-reviewer` runs on the diff. This plan is not that review.
3. **Data-layer sentences for slice 2.** `docs/layers/data/LAYER.md` allows "SQLCipher" and still says US-57 has to show FTS with it. ADR-0008 names `net.zetetic:sqlcipher-android:4.6.1` and says a later amendment can put that coordinate on the allowed-dependency row. The sentences below are Proposed. Needs approval by mjbvilhena. They are not written into the layer file in this pull request. Slice 2 does not add the Gradle coordinate until he approves them.

   Proposed replacement for the Room row's note: "Room opens `net.zetetic:sqlcipher-android:4.6.1`. FTS5 comes from that artifact's SQLite. The product database is not Room's bundled SQLite."

   Proposed replacement for the SQLCipher row's note: "Database encryption in ADR-0006 and ADR-0008. The artifact is `net.zetetic:sqlcipher-android:4.6.1`. The subkey is raw key data, passed as the ADR-0006 hex pragma, and `temp_store` is `MEMORY`. US-57 records pass on the API 26 emulator and on the samsung SM-S911B."

   Proposed replacement for the NEVER that says FTS5 plus SQLCipher is not yet proven: "US-57 records pass. The vault database uses `net.zetetic:sqlcipher-android:4.6.1` as ADR-0008 states. A later version needs the same two-target checks."

4. **Argon2id package.** ADR-0001 requires native Argon2id, JNI to the Password Hashing Competition reference, and forbids a pure-Java or pure-Kotlin port. No Maven coordinate is named in the ADR or the data layer. The tree has no Argon2id dependency. Slice 1 does not pick a coordinate. Needs approval by mjbvilhena before that dependency is added.
5. **Common-password list.** The refusal is required. No corpus is named, and no list file is in the tree. The prototype's sample list is not the product list (`docs/mockups/notes.html`). Slice 1 does not choose a corpus. Needs approval by mjbvilhena.
6. **Proposed test rows TP-40 through TP-47 and proposed journey J-13.** They lock key and ciphertext behavior. Needs approval by mjbvilhena. They are not an amendment approval of the test plan.
7. **Nokia 3 measurement, before US-35 is called Done.** ADR-0001: unlock time at the memory floor is measured on a Nokia 3 (2017), 2 GB RAM, official Android 8.0 (API 26), and recorded with the build. The ADR sets no pass or fail number of seconds. A slow result is not a reason to lower the floor. The measurement is not in the tree. This plan does not invent one.
8. **A vulnerability exception.** None is proposed. If Dependency scan fails on the new coordinate, the slice stops. An exception line would need `Exception approved by mjbvilhena on` and a date, in a later decision. This plan does not add `docs/sdlc/vulnerability-exceptions.md`.

Ruleset 25303010 was read on 2026-11-28. It requires nine checks: Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, CodeQL, Kotlin style, Dependency scan, and Workflow security. The branch must be up to date. There are no bypass actors. This is a status reading. It is not an approval marker. This plan does not edit the ruleset.

## Blockers that are still Draft or outside the slice

| Block | What it holds |
| --- | --- |
| Draft US-18 | US-35 "a new empty vault" must not overwrite or delete a Drive copy until he confirms. The local offer in "I've forgotten my passphrase" can be worded in slice 1. The Drive write stays out. |
| Draft US-18 and US-34 not implemented | US-35 says background sync and reminders can decrypt while the phone is locked, after the first unlock since boot. Slice 1 creates the Keystore flags. The jobs themselves are those other stories. US-35 is not Done on this clause. |
| Draft US-37 | Sensitive content still needs the US-37 unlock. US-92's search-after-unlock clause stays unmet. US-75 stays unimplemented. What a locked search shows stays US-75. |
| Draft US-59 | The Community Edition notice on an open-source licences screen in Settings. ADR-0008 E2. The screen is not these slices. |
| Placeholder US-74 | Stays a placeholder. ADR-0001 remains the decision record. |
| US-86 | Passphrase change and the 24-hour Drive reset. Approved, and not a criterion of US-35. Not these slices. |
| US-94 | Approved. Attachments are Draft US-03, US-04, and US-32. Topic links, the queued job, and pending suggestions are Draft US-45, US-42, US-43, US-44, US-60, and US-46. Not these slices. |
| Draft US-08, US-31, US-33, and the snapshot producer | Slice 3 can index extracted text, OCR text, and snapshot text when those columns hold text. It does not build the producers. |
| Spec sentence "It is not yet verified with an encrypted database." | Left as written. The spike note records pass. |

US-35, US-15, US-50, US-12, US-92, and US-13 stay not Done at the end of this plan. US-57 stays Done.

## Out of these slices

Drive upload, sign-in, the forgotten-passphrase reset, Forget this phone, topic calls, OCR, reminders, trash restore of related records, the licences screen, a Room version bump, an `abiFilters` change, and any edit to ADR-0006. The in-memory debug rows are not copied into the new vault file.
