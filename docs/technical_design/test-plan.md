# Test plan

**Status: Proposed.** This artefact is not approved and not agreed. It waits for mjbvilhena to walk through it. It does not approve a story, and it does not say any story is Done.

Template shape: `get_sdlc_template("test_plan")`, with the journey section in the shape of `get_sdlc_template("e2e_test_plan")`. Skill: `sdlc-test-planner`. Harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`.

This file plans tests. It does not add test code. `sdlc-test-writer` and `sdlc-e2e-scripter` execute it later, after implementation starts.

## Metadata

| Field | Value |
| --- | --- |
| Subject | v1 vault: E1–E9 and E11. E10 is out of scope. |
| Framework | No Gradle project exists. US-25 names local JVM tests plus Robolectric for the one Compose screen it requires. Domain tests are JVM tests with fake ports (`docs/layers/domain/LAYER.md`). Data tests cover search ranking and ciphertext packaging without a live Drive call (`docs/layers/data/LAYER.md`, US-25). CI is `.github/workflows/qa.yml`. The Build and test job no-ops until `settings.gradle` or `settings.gradle.kts` exists. |
| Related DoD | `feature`, `user story`, `ui change`, `security change`, `pr`. Those are implement and verify gates. This plan does not mark them Done. |

Definition of Done checks that were read, and what they still require after this plan:

| DoD | What this plan covers | What stays open until implementation |
| --- | --- | --- |
| `user story` | Each planned row names a Must scenario or says out of scope. | No story is Done. Automation does not exist. |
| `feature` | Risk rows include a failure path where the story has one. | Must AC are not met in a build. There is no app. |
| `ui change` | Compose rows name accessible names, keyboard, reduced motion, and no color-only meaning. | The checklist runs on implemented screens. No conformance claim. |
| `security change` | Crypto, sync, and sensitive-item rows name the fail-closed result. | A security review of a diff is verify-time. |
| `pr` | The pull request that adds this file is docs. It does not claim the product is tested. | Review and green CI are the PR gate for that pull request. |

## Scope

- **In:** Unit and integration behavior for the domain, the data adapters, SQLCipher with FTS, Argon2id and the AES-256-GCM containers in the accepted ADRs, sync merge, tombstones, forgotten-installs, Compose checks named by US-25 and by the UX artefact, and the journeys in the end-to-end section.
- **Out:** A live Google Drive call (US-25). A production account. Load tests. A visual snapshot farm. E10. Choosing a cipher (already accepted in ADR-0006). Writing the US-57 spike result (that note is the spike's own deliverable, not this plan). Executable tests in this change.
- **Oracles:** A story Then, a MUST or NEVER in `docs/domain/vault/DOMAIN.md` or a `LAYER.md`, or a rule in an accepted ADR. A test that needs a behavior none of those state is not added.

### Runner

The repo has no test sources and no test runner dependency. This plan does not switch stacks and does not add Playwright, Cypress, or a browser runner. The app is Android.

| Level | Runner the stories already name | When |
| --- | --- | --- |
| Domain unit | JVM, fake ports | Use cases. No Android types. |
| Data integration | JVM, and an Android test only where Keystore, SQLCipher, or ML Kit cannot be faked | No live Drive. |
| SQLCipher and FTS | US-57: a small test app on an API 26 emulator and on an arm64 phone | Before search and encryption are built on that pair. |
| Compose | Robolectric, JVM (US-25) | At least one screen's main control. Further screens use the same runner. |
| Journey | Not named | See the end-to-end section. The runner is open question 1. |
| CI | `qa.yml`: Secret scan, Markdown lint, Workflow lint, Build and test, Android lint, Kotlin style, CodeQL | A failed assertion fails the job (US-25). |

### Tink

ADR-0001, ADR-0006, the domain file, and the three layer files do not name Tink. The accepted constructions are native Argon2id, HKDF-SHA256 subkeys, SQLCipher 4 (AES-256-CBC with HMAC-SHA512), AES-256-GCM for files and containers, and an Android Keystore AES-256-GCM wrap. This plan tests those. It does not add a Tink dependency. Whether any of that AEAD is implemented with Tink is open question 2. Until an ADR names Tink, a Tink test is out of scope.

### UX artefact

`docs/technical_design/ux-design.md` is Proposed. Rows that lock a sentence marked Proposed in that file fail if the string drifts from that file, and they are re-pointed if mjbvilhena changes the sentence before approval. They do not approve the sentence.

## Risk-based coverage

Ordered by user and data risk. Status is planned. Nothing here is implemented.

| ID | Behavior | Level | Risk if wrong | Status |
| --- | --- | --- | --- | --- |
| TP-01 | A new vault uses native Argon2id 0x13 inside the floor (memory 65536 KiB, iterations 3, parallelism 1, salt 16 bytes, output 32 bytes) and refuses a header outside the ceiling (memory 262144 KiB, iterations 10, parallelism 1, salt 16, output 32, version 0x13). The KDF does not run on a header outside the bounds. Parameters are not clamped. | unit | A second key, or a weaker vault | planned |
| TP-02 | A passphrase shorter than 12 or longer than 128 Unicode code points after NFC, or one on the bundled common-password list, is refused before the KDF. There is no strength meter. | unit | A known password reaches the KDF | planned |
| TP-03 | If the native allocator cannot obtain 65536 KiB, setup and re-create from Drive are refused. Memory, iterations, and parallelism are not lowered. `isLowRamDevice()` is not the oracle. The test injects the allocation failure. | unit | A weaker parameter set ships | planned |
| TP-04 | The derived key and the wrap blob stay off Drive and out of the zip. Drive file names and metadata have no title and no tag. A payload seeded with a sentinel does not contain `SQLite format 3`, and the zip sentinel is unreadable without the vault key. | integration | A copied Drive file or zip is plaintext | planned |
| TP-05 | Wrong passphrase changes nothing. The next attempt waits 2, then 4, 8, 16, then 32 seconds, including after process death. The KDF does not run during the wait. A correct passphrase resets the delay. There is no permanent lockout. The wait does not apply to an offline guess against a copied file. | unit | Online guessing is cheap, or a correct passphrase stays locked | planned |
| TP-06 | SQLCipher 4 receives the database subkey as `PRAGMA key = "x'<64 hex>'"`, not as a passphrase. HMAC stays on. `temp_store` is `MEMORY`. HKDF-SHA256 subkeys for database, files, records, and zip are distinct and are not stored. A nonce is not reused under the same subkey. The clear header is additional authenticated data, including install id and object kind. Magic is `LAGOON` plus NUL, version 1. | integration | Page encryption is a second passphrase, or a swapped header decrypts | planned |
| TP-07 | A tag failure on an in-bounds header is reported as a wrong passphrase and changes nothing. A header outside the Argon2 bounds does not run the KDF. A lower generation of the same install's records file does not replace the stored file. A zip import is not refused only because its counter is lower. | integration | Rollback or a tampered header opens the vault | planned |
| TP-08 | US-57: FTS5 with SQLCipher on a bundled SQLite, on an API 26 emulator and on an arm64 phone, writes pass or blocked, the error if blocked, and the APK size change. The note does not name a mode or a key size as the vault cipher. Search and encryption are not built on that pair until the note exists. If it is blocked, the spec's Room 2 FTS4 plus app-side ranking may be recorded and is not chosen by this plan. | integration | Search is built on an engine that cannot run | planned |
| TP-09 | Search covers title, body, extracted text, OCR text, tags, and the personal note. Weights are title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. A match, a miss, and that order are covered (US-25, US-50). Snapshot text is searchable and has no separate weight. | integration | The wrong item ranks first, or a field is invisible | planned |
| TP-10 | A locked sensitive item is absent from title, body, OCR, and note search, and from highlights. A tag word can return the neutral row. After one US-37 unlock in the session the title is shown. Backgrounding the app returns the neutral row. | unit | A locked title or snippet is on screen | planned |
| TP-11 | One-sided items are kept. Two edits of one id merge user fields by the later `userEditedAt`, tie-break the lower install id. Tags union unless a removal was recorded. Notes append. `updatedAt` does not decide trash. | integration | The other phone's item disappears | planned |
| TP-12 | In-trash state merges by the later `trashChangedAt`. An equal `trashChangedAt` uses the lower install id. On every phone the item is hidden from home, search, Upcoming, tag, inbox, and topic views, and its reminders are silenced. A user edit with `userEditedAt` later than `trashChangedAt` restores it. A system write does not. A tombstone is written only after 30 × 24 hours from `trashedAt`, not at the end of undo. | integration | A delete skips the 30-day window, or a restore is lost | planned |
| TP-13 | A late restore or a late user edit from the trash window, arriving after a tombstone, brings the item back when its timestamp is after that trash's `trashedAt` and before the earliest tombstone timestamp. Any other edit for a permanently deleted id is dropped. The phone that held that dropped user edit shows the in-app notice once. OCR, a preview, and other system writes do not show it. | integration | A finished delete comes back, or a real edit vanishes without the notice | planned |
| TP-14 | Forgotten-installs. This phone writes only its own file, reads it back, and only then deletes the other install's records file. A failed write or read-back deletes nothing. A failed listing or read keeps the local vault and does not upload. A successful read of every file with this id in none of them, or a listing with no such file, keeps the local vault and uploads. A missing records file alone does not wipe the local vault. A newer-key file is not a read failure. | integration | A lost listing wipes the phone, or a forgotten phone uploads the old vault | planned |
| TP-15 | The sync-paused notice. Three consecutive syncs that reach Drive and fail to read or list a forgotten-installs file show the sentence in `docs/technical_design/ux-design.md`. The first sync that reads every forgotten-installs file successfully clears it. An offline sync, when Drive is not reachable, does not increment the count and does not reset it. The notice is in-app, names no title, and is not a system notification. | integration | He is told sync is paused while offline, or he is not told after three real failures | planned |
| TP-16 | Forget this phone requires the US-37 unlock first. A cancelled unlock changes nothing. The list shows other installs only, with device name, model, and last check-in, and "will be forgotten at its next sync" while a delete is pending. This phone uploads its merged records file first. Offline, or a failed upload, writes nothing and deletes nothing. The passphrase does not change. The forgotten phone's own forgotten-installs file is not deleted. | integration | The phone he is holding is retired, or a silent phone is retired for him | planned |
| TP-17 | A returning forgotten phone asks for the passphrase before discarding local copies. Cancel or a wrong passphrase discards nothing and does not upload. On accept it rebuilds from Drive, drops unsynced local edits, rejoins with a new install id, and leaves the old id in the forgotten-installs file. The lost-edits notice and the orphan-file notice appear only when something was actually lost or deleted. Orphan cleanup deletes local files only. | integration | Local edits are destroyed without a prompt, or a forgotten phone keeps uploading the old id | planned |
| TP-18 | A normal passphrase change re-encrypts the phone and Drive without the 24-hour wait. A crash leaves each copy under exactly one passphrase. A forgotten-passphrase reset keeps Drive on the old passphrase for 24 hours after the marker is on Drive. The resetting phone does not upload during the wait. Another phone that holds the key can cancel. The notice has no passphrase and no item content. Items saved on the resetting phone during the wait are kept on cancel. | integration | Drive and the phone disagree about the passphrase, or a thief re-keys Drive immediately | planned |
| TP-19 | Preview and enrichment do not auto-fetch a token-looking URL or a skipped host. The fetch sends no cookies, no Referer, and no authorization. User-Agent is `LagoonPreview`. At most 5 redirects, 15 seconds, and 10 MiB decompressed. `http` is fetched as `https`. A sensitive item is not fetched. The home row does not draw the fog. "Fetch preview" attempts one fetch and still applies the host rules. | integration | A token or a private host is requested | planned |
| TP-20 | `MlKit.initialize` is not called before the US-07 acceptance. The manifest removes `MlKitInitProvider`. The dependency is `com.google.mlkit:text-recognition`. `com.google.android.gms:play-services-mlkit-text-recognition` is absent. Decline or an unanswered disclosure does not start ML Kit. Withdraw shows the disclosure again. There is no telemetry off switch. Firebase Analytics and Crashlytics are absent. | integration | Metrics start before he accepts, or the image is sent | planned |
| TP-21 | Topic calls use the xAI endpoint in ADR-0003, the default model grok-4.20-0309-non-reasoning, and the bearer token only in the header. A response whose `x-zero-data-retention` is not `true` is discarded and topics pause. A sensitive item, an image, and attachment bytes are not sent. The first-key prompt defaults to No. The monthly count is local and is not a cap. The key is not in the APK. | integration | Vault content or the key leaves in a way the ADR forbids | planned |
| TP-22 | Reminders use `setAndAllowWhileIdle` only. `SCHEDULE_EXACT_ALARM` and `USE_EXACT_ALARM` are not requested. A 09:00 reminder fires at 09:00 in the phone's current local zone. Only the install US-55 picks posts. A missed reminder fires once. A sensitive notification's public and private text are only "Lagoon reminder". An ordinary notification may show the title. | integration | A code or a sensitive title is on the lock screen, or two phones both notify | planned |
| TP-23 | The manifest allowlist is US-80. `CAMERA`, `WRITE_SETTINGS`, and exact-alarm permissions are absent. `allowBackup` is false and the vault is excluded from `dataExtractionRules`. A release build strips `Log`. Share intake rejects `file://` and Lagoon's own FileProvider URIs, and refuses one image over 20 MiB. | integration | A new permission or a plaintext backup ships | planned |
| TP-24 | US-81 sideload policy: the release signing story's published rule is the oracle. A test checks the policy file and the build type that policy names, once those files exist. This plan does not invent a second signing scheme. | integration | An unsigned or differently signed APK is treated as the release | planned |
| TP-25 | Repeat URL normalisation (scheme, `www`, trailing slash, fragment, `utm_*` and tracking parameters, host case) updates one item, unions tags, appends a dated note, and does not overwrite text. The snackbar is "Saved before, added to existing" with Undo and "Keep separate". Trashed matches are ignored when a live match exists. | integration | A second item, or a note he typed is overwritten | planned |
| TP-26 | Full-screen sets `screenBrightness` to `1.0f` on that window, does not request `WRITE_SETTINGS`, keeps the screen on until close, does not write a decoded image or a rendered page to disk, and does not open a PDF that needs a password. An owner-password-only PDF still opens. A sensitive item prompts before the view and uses `FLAG_SECURE`. | integration | Brightness is a system setting, or a password is stored | planned |
| TP-27 | Compose. The passphrase field is a password input with `IME_FLAG_NO_PERSONALIZED_LEARNING`, and that screen uses `FLAG_SECURE`. Settings is one screen and has no Back up now, no Restore, no last-backup time, and no "Check my passphrase". Theme defaults to System. Light accent `#0F766E`, dark accent `#2DD4BF`, on-accent text in dark mode black or `#003731`, and `#0F766E` is not a dark-surface color. Dynamic color is off until toggled, and the toggle is absent on API 30 and below. Animator scale 0 completes the action with no motion. US-25 finds the main control of the named screen by its accessible name. | Compose | A backup button appears, or the dark accent is unreadable | planned |
| TP-28 | Module graph, once Gradle exists: `:domain` has no Android dependency, `:ui` does not depend on `:data`, `:data` does not depend on `:ui`, `:app` is the only module that depends on both. The architecture test fails the build otherwise. | unit | A composable imports SQLCipher or Drive | planned |
| TP-29 | Zip reading rejects an absolute path, `..`, and a path that escapes the destination. It stops at 10000 entries, 2 GiB uncompressed in total, and a single entry whose uncompressed size is more than 100 times its compressed size. A stop leaves the vault unchanged. | integration | A zip replaces the vault or writes outside the destination | planned |
| TP-30 | A failed assertion fails CI (US-25). Kotlin style runs `ktlintCheck` or `detekt` once Gradle exists, as `qa.yml` already says. | CI | A red test is ignored | planned |

Happy paths are the success column of each row. Failure and empty paths are the risk column and the unhappy table. This change is not a bugfix, so there is no old-code regression row.

Draft stories are still the oracle for rows that cite them. A Draft story is not an approval. If mjbvilhena changes a Draft story, the row moves with it.

## Fixtures and doubles

- Items, tags, and installs are synthetic. No real personal data, no real OAuth token, no real topic API key, and no production Drive URL.
- Time comes from the domain `Clock` port. Tests set the instant and the zone. A 09:00 reminder is asserted in that zone, not on the runner's zone.
- Install ids and item ids are fixed in the fixture. The tie-break on a lower install id is asserted with two explicit ids.
- Argon2id tests may use the real native KDF for one known vector inside the floor. Delay tests do not wait wall-clock minutes. They inject the clock.
- The memory-floor test injects allocator failure. It does not depend on the CI machine being low-RAM.
- Drive is a fake `SyncPort`. It can fail a list, fail one forgotten-installs read, succeed every read, or report Drive unreachable. It must not be the live API.
- ML Kit is not initialized in the unit tests. The disclosure test asserts the initialize function was not called.
- SQLCipher tests use a temporary directory that is deleted at the end. `temp_store = MEMORY` is asserted from the connection, not from a file appearing.
- Compose tests use the strings in `docs/technical_design/ux-design.md`. They do not invent a second sentence.
- Randomness: salt and nonce tests use a seeded `SecureRandom` stand-in where the test needs a known nonce, and a real `SecureRandom` only for the "two nonces differ" check.

## Unhappy paths

| Condition | Expected result | Row |
| --- | --- | --- |
| Passphrase of 11 code points, 129 code points, or a listed common password | Refused. KDF not run. | TP-02 |
| Header memory below 65536 KiB or above 262144 KiB | Refused. KDF not run. | TP-01 |
| Allocator cannot obtain 65536 KiB | No vault. Parameters unchanged. | TP-03 |
| Wrong passphrase | Nothing changes. Delay applies. | TP-05 |
| Tampered header inside the bounds | Reported as a wrong passphrase. | TP-07 |
| Forgotten-installs listing fails | Local vault kept. No upload. Count increases only if Drive was reached. | TP-14, TP-15 |
| Drive unreachable | Count unchanged. Banner unchanged. | TP-15 |
| Forget unlock cancelled | No write and no delete. | TP-16 |
| Returning phone, passphrase cancelled | Nothing discarded. No upload. | TP-17 |
| Token-looking URL | No fetch. Fog stays until he enables it. | TP-19 |
| OCR before acceptance | `MlKit.initialize` not called. | TP-20 |
| Topic response without `x-zero-data-retention: true` | Discarded. Topics paused. | TP-21 |
| Sensitive notification | Public and private text are "Lagoon reminder" only. | TP-22 |
| Image share over 20 MiB | Refused at intake. | TP-23 |
| Zip with `..` | Vault unchanged. | TP-29 |
| Empty vault on the home screen | "Nothing is saved yet." | TP-27 |
| Empty search | No rows. Not an error dialog. | TP-09 |
| Settings | No Back up now, Restore, last-backup time, or "Check my passphrase". | TP-27 |

## Definition of ready for this plan

- [x] Framework and paths match the repo: JVM, Robolectric, `qa.yml`, and no runner the repo does not have.
- [x] No secrets or production URLs in this plan.
- [x] Not a bugfix, so there is no test-that-failed-on-old-code requirement.
- [x] Domain and layer constraints that affect setup are noted: fake ports, no live Drive, SQLCipher raw key, architecture test, no Tink unless an ADR adds it.

## End-to-end test plan

Journeys below are the user-visible outcomes the stories require. `sdlc-e2e-scripter` executes them at verify time. This section does not contain a script.

### End-to-end metadata

| Field | Value |
| --- | --- |
| Journeys in scope | J-1 through J-12 |
| Runner | Not chosen. US-25 uses Robolectric for one screen and excludes an emulator from that story. US-27 uses an emulator for screenshots, which is not this plan. US-57 uses an API 26 emulator and an arm64 phone for the spike only. |
| Base URL source | None. There is no web app. The subject is the installed app on a local or CI device. |
| Layer constraints | UI consultant: Compose talks to domain use cases, not to Drive or SQLCipher. |

### J-1: Save a link and find it

- **Actor:** Miguel. Precondition: a vault exists, sync is not required, the item is ordinary.
- **Steps:** Share an `https` link that is not token-looking. See "Saved to Lagoon". Dismiss the quick-add row. Open the home list. Search a word from the title.
- **Assertions:** The row is newest. Search highlights the match. No network is required for the open.
- **Unhappy sibling:** Share the same URL again. The snackbar is "Saved before, added to existing". Undo restores the previous item.
- **Maps to:** US-01 (Draft), US-10 (Draft), US-15 Approved, US-28 (Draft), US-49 Approved.

### J-2: Skip a token-looking preview

- **Actor:** Miguel. Precondition: a saved link the US-79 rule classifies as token-looking.
- **Steps:** Open the item. Leave the prompt. Later choose "Fetch preview" only in the unhappy sibling.
- **Assertions:** No request was made. A foggy image is on the detail, not on the home row. The accessible name of the prompt is the sentence in the UX artefact.
- **Unhappy sibling:** Choose No. The fog stays. Then choose "Fetch preview" on a public `https` URL that is not token-looking and not a skipped host. One fetch runs.
- **Maps to:** US-79 Approved, US-11 (Draft).

### J-3: Open an image full screen

- **Actor:** Miguel. Precondition: an ordinary item with an image, airplane mode on.
- **Steps:** Tap the image once. Pinch. Close.
- **Assertions:** The view is full screen. The window brightness override is `1.0f`. The screen stays on while the view is open and that flag ends on close. It opens with no network.
- **Unhappy sibling:** The same tap on a sensitive item before unlock shows the US-37 prompt and, on cancel, no image.
- **Maps to:** US-85 Approved, US-75 Approved.

### J-4: Delete, undo, and restore from trash

- **Actor:** Miguel. Precondition: one ordinary item.
- **Steps:** Delete. Choose Undo. Delete again. Leave the snackbar. Open Settings, then Trash. Choose Restore.
- **Assertions:** Undo puts the item back on the home list. After the snackbar ends, the home list does not show it and Trash shows type, title, days left, and Restore. There is no Delete forever, Delete now, or Empty trash. Restore returns it to the home list.
- **Unhappy sibling:** A sensitive item in Trash shows "Sensitive" and no title until unlock.
- **Maps to:** US-13 (Draft), US-66 Approved, US-59 (Draft).

### J-5: Sensitive reminder

- **Actor:** Miguel. Precondition: a sensitive item with a reminder, notification permission granted.
- **Steps:** Let the inexact alarm fire with the phone locked, and again with notifications set to show all content. Tap the notification.
- **Assertions:** Public and private text are "Lagoon reminder". The title is absent. Lagoon opens. The item body appears only after a successful US-37 prompt.
- **Unhappy sibling:** Cancel the prompt. The body stays hidden.
- **Maps to:** US-34 Approved, US-75 Approved, US-37 (Draft), US-55 Approved.

### J-6: Theme, contrast, and reduced motion

- **Actor:** Miguel. Precondition: a fresh install.
- **Steps:** Open Settings. Read the theme. Choose Light. Choose Dark. Choose System. Set the animator duration scale to 0 and move from the home list to an item and back.
- **Assertions:** System is the default. Light uses `#0F766E`. Dark uses `#2DD4BF` with black or `#003731` on the accent. Dynamic color is off. The dynamic-color toggle is absent when the test device is API 30 or below. With the animator scale at 0 he reaches the item and returns without motion. Settings has one screen and none of the forbidden controls in TP-27.
- **Unhappy sibling:** None in the stories beyond the API 30 toggle absence.
- **Maps to:** US-22 (Draft), US-53 (Draft), US-30 (Draft), US-59 (Draft), E5.

### J-7: Sync paused, then cleared

- **Actor:** Miguel. Precondition: a fake Drive that this phone can reach, and a forgotten-installs read that fails.
- **Steps:** Run a sync that reaches Drive and fails the read, three times. Run a sync that does not reach Drive between them. Then run a sync that reads every forgotten-installs file.
- **Assertions:** The banner is absent after one and two reaching failures. It is present after the third, with the sentence in the UX artefact, on the home list and in Settings. The offline sync does not change the count. The successful read removes the banner. The local vault is still there.
- **Unhappy sibling:** A newer-key file is offered instead of a failed read. It does not increase the count. The phone asks for the new passphrase.
- **Maps to:** US-66 Approved, US-87 Approved, ADR-0002.

### J-8: Forget another phone

- **Actor:** Miguel, on a phone that holds the vault. Precondition: the fake Drive has one other install's records file.
- **Steps:** Open Settings. Choose "Forget this phone". Cancel the unlock. Start again, complete the unlock, and cancel the confirm. Start again, confirm that install.
- **Assertions:** A cancelled unlock and a cancelled confirm leave the other file in place. Confirm shows that install's device name, model, and last check-in. This install is not in the list. After success the other records file is gone and the passphrase is unchanged. If the delete fails after a successful read-back, the row says "will be forgotten at its next sync".
- **Unhappy sibling:** No other records file. After unlock, the sentence is "There is no other phone to forget."
- **Maps to:** US-87 Approved, US-59 (Draft).

### J-9: Set a passphrase and reject a weak one

- **Actor:** Miguel. Precondition: no vault yet. A share is waiting in memory.
- **Steps:** Enter an 11-character passphrase. Enter a passphrase on the bundled common list. Enter a 12-character passphrase that is not on the list and accept the warning.
- **Assertions:** The short passphrase and the listed passphrase show an error and do not create a vault. The share is still unstored. The accepted passphrase creates the vault and then stores the share. The field is a password field. There is no strength meter and no "Check my passphrase" on Settings.
- **Unhappy sibling:** Decline the warning. He is told nothing was saved. Nothing is on disk in plaintext.
- **Maps to:** US-35 Approved, ADR-0001.

### J-10: Text recognition waits for the disclosure

- **Actor:** Miguel. Precondition: an image item, acceptance not stored.
- **Steps:** Open the path that would run OCR. Decline. Open it again. Accept. Open Settings and withdraw. Open the OCR path again.
- **Assertions:** The disclosure contains the facts in the UX artefact. Decline does not call `MlKit.initialize`. Accept allows OCR and does not ask on the next image. Withdraw makes the next attempt show the disclosure again. Settings has no control that turns the metrics off inside the SDK.
- **Unhappy sibling:** Leave the dialog with neither button. OCR does not run.
- **Maps to:** US-07 (Draft), US-31 Approved, US-59 (Draft).

### J-11: Sign out keeps the phone

- **Actor:** Miguel. Precondition: signed in, one local item.
- **Steps:** Choose Sign out. Open the item.
- **Assertions:** The item is still on the phone. The Drive copy is still present in the fake. There is no third control named "disconnect but keep the Drive copy". The delete action is a different control.
- **Unhappy sibling:** Choose "Disconnect and delete Lagoon data from Drive" and cancel the US-37 prompt. The fake Drive copy remains.
- **Maps to:** US-78 Approved.

### J-12: Topics stay off a sensitive item

- **Actor:** Miguel. Precondition: topic switch on, an ordinary item and a sensitive item.
- **Steps:** Save both. Mark the ordinary item sensitive after it was sent. Choose "Get topics" on the sensitive item.
- **Assertions:** The sensitive item is not in the outgoing payload. After the later mark, the queued job is cancelled. The app says content already sent cannot be recalled. "Get topics" on a sensitive item does not send it.
- **Unhappy sibling:** Turn the Settings switch off and save an ordinary item. It is not sent until he chooses "Get topics" on that item.
- **Maps to:** US-41 Approved, US-75 Approved.

### Stability

- [x] Assertions use the accessible name or the visible string from the UX artefact, not a position in a list.
- [x] Waits follow the fake Drive and the `Clock` port. A journey does not sleep for the Argon2 delay or for 30 days. The clock moves.
- [x] Each journey gets its own vault directory. Journeys do not share a mutable vault.
- [x] Seeds are synthetic and named by the journey id.

### Data and secrets

- Fixtures carry no real token, no real passphrase of a person, and no real Drive file id.
- Screenshots of a journey are not committed with item bodies. US-27 screenshots are a different story and are not produced here.
- The topic client and Drive client in a journey are fakes. A third-party sandbox is not required.

### Environments

| Env | When to run | Forbidden |
| --- | --- | --- |
| Local JVM and Robolectric | Default for J-1, J-2, J-4, J-6, and the parts of the others that do not need hardware | Production data |
| API 26 emulator and an arm64 phone | US-57 only | Using that run to pick a cipher |
| Instrumented device or emulator | Journeys that need Keystore, notifications, or brightness, once a runner exists | A personal Google account |

### Out of scope

Visual snapshot farms, load tests, a live Drive tenant, Play Store review, and E10. US-81's human signing ceremony is not automated beyond the policy check in TP-24.

## Anti-patterns

- A test that only asserts a mock was called, with no oracle from a story or an ADR.
- A coverage percentage as the goal.
- Playwright or a web URL for an Android app.
- A live Drive call inside US-25.
- Treating this plan, or a green docs pull request, as implementation Done.
- Adding Tink, or a segment size, that ADR-0006 does not number. Fixed-size segments are asserted as fixed-size. The byte length is open question 3.

## Open questions

1. **Journey runner.** The stories do not name an instrumented runner. Robolectric covers the US-25 screen. Journeys that need a real notification, Keystore hardware check, or window brightness need a device or an emulator. Which runner, if any, is for `sdlc-e2e-scripter`?
2. **Tink.** The accepted ADRs do not name it. Should any AES-256-GCM path use Tink, or stay on the platform cipher? A yes is an ADR, not a test edit.
3. **GCM segment length.** ADR-0006 requires fixed-size segments and does not state the byte length. The test should lock the constant the implementation chooses. It should not invent the length in this file.
4. **Which Compose screen US-25 names.** US-25 says "the named screen" and does not name it. This plan uses Settings, because one screen must show the absence of Back up now and "Check my passphrase". mjbvilhena can name a different screen.
5. **Proposed UX strings.** Journey assertions follow `docs/technical_design/ux-design.md`. If those sentences change before approval, the assertions change with them. They are not a second copy of the product decision.
