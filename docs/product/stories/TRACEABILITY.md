# Traceability review, 2026-10-17

> **Draft. Not approval.** This file records a traceability review and the removals Miguel then chose.
> Miguel (`mjbvilhena`) at 18:47 IST: "Any user story (and associated downstream artefacts) that you can't trace back to the vision, the product spec or the epics should be removed. Do a full review."
> At 18:48 IST he said: "Let me know what you find before removing." The findings below were written before any deletion.
> At 19:00 IST he said: "68, 69, and 82 to 84 should be removed." US-68, US-69, US-82, US-83, and US-84 are removed. Numbers are not reused.
> At 19:01 IST he said: "63 can be removed as well." US-63 is removed. The number is not reused.
> At 19:02 IST he left US-28, US-40, US-41, and US-53 unchanged, including US-53 reduced motion. Those trims are not applied.
> At 19:04 IST he said each remaining story must map to an epic, and an epic may gain a proposed amendment when it does not already cover the story. Those amendments are not approved.
> At 20:02 IST he approved all 25 epic amendments as written. US-48, US-62, US-65, US-66, US-67, US-70, US-71, and US-72 are kept. At 20:03 IST he closed the five clashes. Stories stay Draft.
> Sources that count: `docs/product/vision.md`, `docs/product/specification.md`, approved epics E1–E9 and E11, the amendment approved on 2026-10-17 at 15:55 IST, and `docs/product/amendments/2026-10-17-chat-decisions.md` (15:59, 16:00, and 18:05 IST). E10 is Later and is not a v1 parent.
> A bot consensus counts only where that text is inside one of those sources. A review pack, a story, or a threat note is not a source.
> Skills: `sdlc-conductor` (template `lifecycle pipeline`), `sdlc-docs-backlog-review` (template `docs backlog review`), `sdlc-product-owner` (template `product spec`, Definition of Done `epic`). Templates were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This was not a live MCP session. No domain consultant was found. No MUST/NEVER rules were invented.
> Head reviewed: `0209d5c`. Stories stay Draft. This file records no approval.

## Epic map, updated 2026-10-17 at 20:02 and 20:03 IST

Miguel (`mjbvilhena`) at 20:02 IST: "Yes, I approve all 25 proposed epic amendments as written". Those amendments are folded into the epics. `traced via approved amendment` means that approval is what covers the story. It is not story approval. US-48, US-62, US-65, US-66, US-67, US-70, US-71, and US-72 are kept. At 20:03 IST he closed C-E1-1, C-E1-2, C-E9-1, C-E9-2, and C-E11-1. US-73, US-74, and US-76 stay the open questions their epics already name. E7 and E10 gained no scope. E10 stays later.

| Story | Epic | Traced or traced via approved amendment | Amendment reference |
|---|---|---|---|
| US-01 Share a link into the vault. | E1 | traced | — |
| US-02 Share plain text into the vault. | E1 | traced | — |
| US-03 Share an image into the vault. | E1 | traced | — |
| US-04 Share a PDF into the vault. | E1 | traced | — |
| US-05 Add a note by hand. | E1 | traced | — |
| US-06 Add a URL by hand. | E1 | traced | — |
| US-07 Use the saved vault with no network and no analytics. | E1 | traced via approved amendment | A-E1-1 |
| US-08 Enrich a saved link from the page. | E2 | traced | — |
| US-09 Keep the link when the page cannot be fetched. | E2 | traced | — |
| US-10 Browse saved items, newest first. | E3 | traced | — |
| US-11 Read a saved item. | E3 | traced | — |
| US-12 Edit a saved item. | E3 | traced | — |
| US-13 Delete an item and undo that delete. | E3 | traced | — |
| US-14 Manage tags on saved items. | E3 | traced | — |
| US-15 Search the vault and see highlighted matches. | E3 | traced | — |
| US-16 Filter items by type and by tag. | E3 | traced | — |
| US-17 See that sync is not configured. | E4 | traced | — |
| US-18 Sign in so the vault can sync. | E4 | traced | — |
| US-19 Prefer an unmetered network for background sync. | E4 | traced | — |
| US-20 Receive the vault on another install. | E4 | traced | — |
| US-21 Export a zip to a place he chooses. | E4 | traced | — |
| US-22 Choose light, dark, or the system theme. | E5 | traced | — |
| US-23 Show a launcher icon and the name Lagoon. | E5 | traced | — |
| US-24 Install a signed release APK without the Play Store. | E6 | traced | — |
| US-25 Cover search, sync and zip packaging, and a basic screen with automated tests. | E6 | traced | — |
| US-26 Follow written steps to build, sideload, and create the OAuth client. | E6 | traced | — |
| US-27 Review screenshots of the main screens. | E6 | traced | — |
| US-28 Optionally tag a save from the last-used tags. | E1 | traced via approved amendment | A-E1-2 |
| US-29 Rename a tag, merging only after he confirms a clash. | E3 | traced | — |
| US-30 Do not offer backup or restore controls. | E4 | traced | — |
| US-31 Recognize English text on the device. | E7 | traced | — |
| US-32 Keep more than one attachment on an item. | E1 | traced | — |
| US-33 Keep an offline snapshot of a saved page. | E2 | traced | — |
| US-34 Set a reminder. | E9 | traced | — |
| US-35 Encrypt the vault at rest. | E8 | traced | — |
| US-36 Sync only ciphertext. | E8 | traced | — |
| US-37 Lock sensitive items, and optionally lock the app. | E8 | traced | — |
| US-38 File new captures from an Untagged inbox. | E3 | traced | — |
| US-39 Sort and filter the list by when it was saved. | E3 | traced | — |
| US-40 Fill an empty title from the page or the file. | E1 | traced via approved amendment | A-E1-3; C-E1-1 resolved 20:03 IST |
| US-41 Extract topics when an item is saved. | E11 | traced | C-E11-1 resolved 20:03 IST |
| US-42 Link an item to a topic the vault already has. | E11 | traced | — |
| US-43 Confirm a topic before it is created. | E11 | traced | — |
| US-44 Queue topic processing while offline. | E11 | traced | — |
| US-45 Read a topic. | E11 | traced via approved amendment | A-E11-1 |
| US-46 Reject, rename, or merge a suggested topic. | E11 | traced | — |
| US-47 Keep the item when the topic call fails. | E11 | traced | — |
| US-48 Manage a topic that is already saved. | E11 | traced via approved amendment | A-E11-2 |
| US-49 Update a link that was saved before, and undo that update. | E1 | traced | — |
| US-50 Rank search matches. | E3 | traced | — |
| US-51 Sync in the background and show errors. | E4 | traced via approved amendment | A-E4-1 |
| US-52 Import a zip and merge it. | E4 | traced | — |
| US-53 Use the teal palette, optional dynamic color, and reduced motion. | E5 | traced via approved amendment | A-E5-1 |
| US-54 Notify before an expiry. | E9 | traced | — |
| US-55 Reschedule reminders after a restart or a clock change. | E9 | traced via approved amendment | A-E9-1 |
| US-56 Keep a stable identity for each item. | E4 | traced via approved amendment | A-E4-2 |
| US-57 Prove that search and encryption can run together. | E3 | traced via approved amendment | A-E3-1 |
| US-58 Run enrichment, snapshot, OCR, and topics in that order. | E1 | traced via approved amendment | A-E1-4 |
| US-59 Use one Settings screen. | E4 | traced via approved amendment | A-E4-3 |
| US-60 Browse topics and pending suggestions. | E11 | traced via approved amendment | A-E11-3 |
| US-61 Filter by where an item came from. | E1 | traced | — |
| US-62 See what is coming due. | E9 | traced via approved amendment | A-E9-2; C-E9-1 resolved 20:03 IST |
| US-64 Share or open an item in another app. | E3 | traced | — |
| US-65 Add a photo from the camera. | E1 | traced via approved amendment | A-E1-5; C-E1-2 resolved 20:03 IST |
| US-66 Keep a trash beyond undo. | E3 | traced via approved amendment | A-E3-2 |
| US-67 Notice a duplicate image or PDF. | E1 | traced via approved amendment | A-E1-6 |
| US-70 See what is not ready for offline. | E2 | traced via approved amendment | A-E2-2 |
| US-71 Triage the inbox in a batch. | E3 | traced via approved amendment | A-E3-3 |
| US-72 Score whether topics are useful. | E11 | traced via approved amendment | A-E11-4 |
| US-73 Decide sync conflicts and deletions. | E4 | traced | — |
| US-74 Decide how the vault key reaches another device. | E8 | traced | — |
| US-75 Limit what a sensitive item reveals. | E8 | traced via approved amendment | A-E8-1 |
| US-76 Decide the topic call's provider, credential, and payload. | E11 | traced | — |
| US-77 Keep reminders on inexact alarms. | E9 | traced | C-E9-2 closed 20:03 IST |
| US-78 Keep Drive to app data, and sign out without deleting the phone. | E4 | traced via approved amendment | A-E4-4 |
| US-79 Skip unsafe link previews. | E2 | traced via approved amendment | A-E2-1 |
| US-80 Keep the permission set to an allowlist. | E6 | traced via approved amendment | A-E6-1; C-E1-2 resolved 20:03 IST |
| US-81 Publish a sideload signing policy. | E6 | traced via approved amendment | A-E6-2 |

Clashes closed by Miguel (`mjbvilhena`) on 2026-10-17 at 20:03 IST:

- **C-E1-1.** Resolved. An empty title on a screenshot, image, or scan fills from the file name. The host-only title for a token-looking link stays (A-E1-3).
- **C-E1-2.** Resolved. US-65 uses `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`. The US-80 allowlist is unchanged.
- **C-E9-1.** Resolved. The Upcoming list shows only the one reminder or the one expiry. The US-75 Upcoming row follows that.
- **C-E9-2.** Closed. Alarms are inexact. Lagoon never requests `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`.
- **C-E11-1.** Resolved. US-41 has no cap of 8. E11 and the specification still say no cap.
- **E7.** No merchant or booking story remains. The approved Future Scope says that stays later, and it says "No story." Nothing was added.

## Proposed removals

These stories have no line in the sources. Downstream means the clauses and index rows that exist only because of the story. They are still in the repo.

| Story | Why it does not trace | Downstream that would go with it |
|---|---|---|
| US-48 | E11 confirms, links, and declines a new topic. It does not say he can rename, delete, unlink, or merge a topic after it is saved. | INDEX row, backlog gap line, REVIEW-PACK row. US-46 only points at US-48 as out of scope. |
| US-62 | E9 shows the date on the item and sends a notification. It does not name an Upcoming list. | INDEX, backlog, REVIEW-PACK. US-39 and US-54 name the list as out of scope. US-75 scenario "upcoming and a pinned shortcut" (the US-62 half). Threat note T-1 names US-62. |
| US-63 | The story says it is not in the approved specification. The 15:59 addendum says a sensitive reminder shows no code, and that ordinary items follow the 14:56 rule. That is notification text, not a Mark as code or Copy code control. | Removed by Miguel at 19:01 IST. Downstream clauses in US-34 and US-75 were cut. |
| US-65 | E1 capture is the share sheet, a note, or a URL. No camera intent. | INDEX, backlog, REVIEW-PACK. US-03 and US-32 name the camera as out of scope. US-80 listing `CAMERA` as absent stays: that line forbids the permission. |
| US-66 | E3 is delete with undo. A 30-day trash is not in that epic. | INDEX, backlog, REVIEW-PACK. US-13 MoSCoW line and out-of-scope line that name US-66. |
| US-67 | E1 matches a repeat URL (US-49). It does not notice a duplicate image or PDF. | INDEX, backlog, REVIEW-PACK. US-49 stays. |
| US-68 | E9 is one reminder or one expiry. Return-by and warranty are not in the vision, the spec, or E9. They are not in the Later list either. | Removed by Miguel at 19:00 IST. US-55 and US-75 wording and threat note T-2 were cut. US-62 was kept at 20:02 IST, without return-by or warranty. |
| US-69 | No epic names a pinned shortcut or a widget. | Removed by Miguel at 19:00 IST. The US-75 pinned-shortcut half, the US-75 widget cache line, and the US-80 widget receiver were cut. |
| US-70 | The story says it is not in the approved specification. E2 and E7 already store the snapshot and the OCR text. A pending-jobs screen is not named. | INDEX, backlog, REVIEW-PACK. The US-33 retry stays. |
| US-71 | E3 has an Untagged inbox, one item at a time (US-38). Batch multi-select is not named. | INDEX, backlog, REVIEW-PACK. US-38 out-of-scope pointer. |
| US-72 | E11 does not ask for a usefulness score, a near-duplicate rule, or a 20-item fixture. | INDEX, backlog, REVIEW-PACK. The score lives only in this story. |
| US-82 | Spec Future Scope: "Picking out booking references and merchants. Stays proposed and later... No story." E7 Future Scope says the same, "No story either way." A typed merchant is still a merchant story. | Removed by Miguel at 19:00 IST. US-15, US-50, and US-75 merchant clauses were cut. The backlog Later line for picking merchants out of a receipt stays. No new story. |
| US-83 | Amount and currency are not in the vision, the spec, or any epic. Not in the Later list. | Removed by Miguel at 19:00 IST. No other story stored an amount. |
| US-84 | Purchase date is not in the vision, the spec, or any epic. US-39 already filters by when the item was saved. | Removed by Miguel at 19:00 IST. |

## Proposed trims

The story stays. The named clause does not trace.

| Story | Clause to cut | Why |
|---|---|---|
| US-15 | "or, when US-82 is in the build, the merchant column from US-82" | Applied with the US-82 removal at 19:00 IST. Spec search list is title, body, extracted text, OCR text, tags, and the personal note. |
| US-28 | Scenario "add a date" | Not cut. He left the story unchanged at 19:02 IST, then approved A-E1-2 at 20:02 IST. Add date is in E1. |
| US-34 | "It shows a code only when US-63 is built" | Applied with the US-63 removal at 19:01 IST. The title on an ordinary lock-screen reminder stays, via the 15:59 addendum's reference to the 14:56 ordinary-item rule. |
| US-40 | Scenarios that replace a file name with `Screenshot ·`, `Image ·`, or `Scan ·` plus a date | Applied on 2026-10-17 at 20:03 IST. The file name fills those titles. The host-only rule stays. |
| US-41 | Scenario "a cap" of 8 names | Applied on 2026-10-17 at 20:03 IST. There is no cap. |
| US-50 | Draft merchant scenario and the merchant weight of 5 | Applied with the US-82 removal at 19:00 IST. Not in the spec weight list. |
| US-53 | Scenario "reduced motion" | Not cut. He left the story unchanged at 19:02 IST, then approved A-E5-1 at 20:02 IST. Reduced motion is in E5. |
| US-55 | The words "a return-by date, and a warranty end" | Applied with the US-68 removal at 19:00 IST. Boot re-registration stays (E9). |
| US-75 | Return-by and warranty inside "other sensitive notices"; the US-69 half of "upcoming and a pinned shortcut"; "a merchant word"; scenario "a copied code"; the widget half of "caches and widgets" | Applied with the 19:00 and 19:01 IST removals. The Upcoming row stays and follows the one reminder or one expiry on US-62. The 15:59 lock-screen reminder and the 15:55 topic exclusion stay. |

## Judgement calls

Marked Traced below, but the line is indirect. These are the ones to show Miguel before any cut.

- US-15 also matches snapshot text, source domain, and the sending app. Snapshot text is in the spec's US-33 line ("Searchable"). Source domain and sending app are the E1 Should (US-61), which says filter, not a search column.
- US-28 also caps the tag row at 8. The epic says last-used tags and sets no cap. Same pattern as other draft bounds.
- US-34's ordinary lock-screen title is the 15:59 addendum saying ordinary items still follow the 14:56 decision. The addendum does not restate that decision.
- US-41's rule that an item saved while the switch is off is not sent later, unless he chooses "Get topics", is not in the 18:05 quote. The quote is only "Topics on by default, with an off switch".
- US-45's topic page is the E11 audience line "later looking across items that share a subject", not a screen name.
- US-46 reject traces to E11 "If he declines, the topic is not created." Rename-before-confirm and linking onto an existing name are treated here as part of that confirmation, not as US-48.
- US-50's extra weights for snapshot text, source domain, and sending app are not in the spec weight list (title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1).
- US-51's error text is not named. Background sync is.
- US-55's rules for which install posts, the 7-day check-in, the install-id tie-break, and a signed-out install are not in E9. E9 only says re-register on `BOOT_COMPLETED`.
- US-56, US-57, US-58, and US-59 are hosts or spikes. They are counted as traced because sync merge, search plus encryption, enrichment/OCR/topics, and the Settings controls need them. The exact pipeline order is not written down.
- US-60's list of pending suggestions is the place he answers E11's confirm step. A separate browser is not named.
- US-64's Should is in E3 and the vision. The share mechanics (pipe, EXIF, default part order) are proposed criteria, not extra product scope.
- US-73, US-74, US-76, and US-77 trace to open questions the spec or E11 says not to invent. They are placeholders, not capabilities. US-77 asks about exact alarms, which E9 already rules out.
- US-75, US-78, US-79, US-80, and US-81 are named in the 15:55 amendment as delegated, with criteria still proposed. The amendment does not approve those criteria. Decided slices stay: topic exclusion (15:55), sensitive lock-screen reminder and token-looking URLs (15:59).
- US-75's per-item "Include in topics" control is proposed and goes beyond the 18:05 quote. It is not cut here because it is inside a delegated story, not a separate capability from US-82's class. Miguel still has to accept or drop it.

## Every story

| ID | Epic | Source | Verdict |
|---|---|---|---|
| US-01 | E1 | E1: "The app appears in Android's share sheet for links, plain text, images, and PDFs." Spec US-01 line covers the repeat URL. | Traced |
| US-02 | E1 | Same E1 share-sheet line, plain text. | Traced |
| US-03 | E1 | Same E1 share-sheet line, images. | Traced |
| US-04 | E1 | Same E1 share-sheet line, PDFs. Spec E7 row: text-based PDFs are extracted, not OCR'd. | Traced |
| US-05 | E1 | E1: "He can add a note or a URL from inside the app." | Traced |
| US-06 | E1 | Same E1 line, a URL. | Traced |
| US-07 | E1 | E1: "The primary store is on the device" and "No analytics." Vision states the 16:00 IST ML Kit disclosure. | Traced |
| US-08 | E2 | E2: "Best-effort fetch of title, description, preview image, and readable text." Token-looking fetch gate is the 15:59 addendum. | Traced |
| US-09 | E2 | E2: "If the device is offline or the fetch fails, keep the item and the URL." | Traced |
| US-10 | E3 | E3: "Home list, most recent first." | Traced |
| US-11 | E3 | E3: "Item detail." Foggy placeholder is the 15:59 addendum. | Traced |
| US-12 | E3 | E3: "Edit, including the personal note and tags." | Traced |
| US-13 | E3 | E3: "Delete with undo." | Traced |
| US-14 | E3 | E3: "Tag management, including rename." | Traced |
| US-15 | E3 | Spec: "Search covers title, body, extracted text... tags, and the personal note." | Traced. The merchant column clause was removed with US-82 at 19:00 IST. |
| US-16 | E3 | E3: "Filter by type and by tag." | Traced |
| US-17 | E4 | E4: "A missing OAuth client ID leaves sync unconfigured, explains that, and does not crash." | Traced |
| US-18 | E4 | E4: "Sign in with Google." | Traced |
| US-19 | E4 | E4: "Unmetered-only preference for background sync." | Traced |
| US-20 | E4 | E4: "A new install or second device signs in and receives the vault." | Traced |
| US-21 | E4 | E4: "Zip export and import on device storage, as a fallback." E8: the zip must not be a plaintext hole. | Traced |
| US-22 | E5 | E5: "An in-app theme setting with three options: Light, Dark, and System." | Traced |
| US-23 | E5 | E5: "A tasteful launcher icon and the short name Lagoon." Vision: "The name is Lagoon." | Traced |
| US-24 | E6 | E6: "Signed with a self-generated release keystore." Vision: "He installs a signed APK himself." | Traced |
| US-25 | E6 | E6: "Unit tests for the repository/search layer and for sync-payload and zip packaging. At least one Compose UI test." | Traced. Enabling for that E6 line. |
| US-26 | E6 | E6: "README: build, sideload... Google Cloud OAuth client steps." | Traced |
| US-27 | E6 | E6: "Screenshots: home, search results, item detail, share-to-save, and settings." | Traced |
| US-28 | E1 | Spec E1 row: "last-used tags can be applied from a non-blocking chip row." | Partly traced. Add date does not trace. |
| US-29 | E3 | E3: "A rename applies to every item. A case-insensitive clash asks for confirmation and then merges." | Traced |
| US-30 | E4 | E4: "No Back up now, no Restore, no last-backup-time control." Vision says the same. | Traced |
| US-31 | E7 | E7: "On-device text recognition for screenshots, images, and scanned PDFs." Vision: English, bundled model, disclosure. | Traced |
| US-32 | E1 | E1: "An item can hold more than one attachment." Vision says the same. | Traced |
| US-33 | E2 | E2: "keep an offline snapshot... and always keep the original URL." Spec US-33 line: sanitised archive, about 10 MB, searchable. | Traced |
| US-34 | E9 | E9: "An optional expiry or reminder date on an item." Spec US-34 line: 09:00 local, inexact, `POST_NOTIFICATIONS` at the first reminder. | Traced. The US-63 code clause was removed at 19:01 IST. |
| US-35 | E8 | E8: "Encrypted database and encrypted attachment files" and keys in Android Keystore. Spec Q10: a lost key means the content is lost. | Traced |
| US-36 | E8 | E8: "Drive stores ciphertext only." | Traced |
| US-37 | E8 | E8: "Fingerprint or biometric lock on items marked sensitive" and "App lock is optional. The device PIN is the fallback." | Traced |
| US-38 | E3 | E3: "An Untagged / Inbox view." | Traced |
| US-39 | E3 | E3: "Sort and filter by date saved." | Traced |
| US-40 | E1 | E1: "An empty title fills from the page title or the file name." | Partly traced. Constructed Screenshot, Image, and Scan titles do not trace. |
| US-41 | E11 | E11: identify main ideas and topics. 18:05 addendum: "Topics on by default, with an off switch." 15:55 amendment: sensitive items are excluded. | Traced. The cap of 8 was removed on 2026-10-17 at 20:03 IST. |
| US-42 | E11 | E11: "If an identified topic already exists, the new item is linked to it." | Traced |
| US-43 | E11 | E11: "A new topic is created only after he confirms." | Traced |
| US-44 | E11 | E11: "Offline, the item is stored and topic processing is queued." And the topic job's own Unmetered only setting, from the 15:55 amendment. | Traced |
| US-45 | E11 | E11 audience: "later looking across items that share a subject." | Traced. Judgement: no screen is named. |
| US-46 | E11 | E11: "If he declines, the topic is not created. The item remains." | Traced. Judgement: rename-before-confirm is treated as that confirmation. |
| US-47 | E11 | E11 workflow: he can still save, and processing waits. "The item remains." | Traced. Enabling so a failed call does not lose the item. |
| US-48 | None | No rename, delete, unlink, or merge of a saved topic in E11. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, and US-46's out-of-scope pointer. |
| US-49 | E1 | E1: "A second share of the same page matches on a normalised URL... updates the existing item." | Traced |
| US-50 | E3 | Spec US-15 line: weighted BM25, title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. | Traced. The merchant weight was removed with US-82 at 19:00 IST. |
| US-51 | E4 | E4: "The vault syncs in the background." | Traced. Judgement: error text is not named. |
| US-52 | E4 | E4: "Zip export and import." | Traced |
| US-53 | E5 | E5 palette, Light/Dark/System, and "Dynamic color is an optional toggle." | Partly traced. Reduced motion does not trace. |
| US-54 | E9 | E9: "An expiry notifies 7 days before the date and again on the day." | Traced |
| US-55 | E9 | E9: "Reminders are stored and re-registered on `BOOT_COMPLETED`." | Partly traced. Return-by and warranty were removed with US-68 at 19:00 IST. Which install posts is a judgement call, not an E9 line. |
| US-56 | E4 | E4: "Sync merges: an item that exists on only one side is kept." | Traced. Enabling: merge needs a stable item id. |
| US-57 | E3 | Spec US-15 line: FTS5 with an encrypted database is a design-gate risk. "Spike it early." E8 requires encryption and E3 requires search. | Traced. Enabling for those two lines. |
| US-58 | E2, E7, E11 | E1: enrichment and topic processing wait. E11 risks: text from E2, E7, and the snapshot may be sent. | Traced. Enabling so those steps have an order. Judgement: the order itself is not specified. |
| US-59 | E4, E5, E9, E11 | Spec: "Settings then explain that sync is not configured." E5 theme, E9 09:00 default, E11 topic switch and Unmetered only, vision disclosure in Settings. | Traced. Enabling host for those controls. |
| US-60 | E11 | E11: he is asked before a new topic is created, and an existing topic is linked. | Traced. Judgement: a list of pending suggestions is not named. |
| US-61 | E1 | E1: "Source domain and sending app (US-61) are a Should." Vision says the same. 15:55 amendment, section 5. | Traced |
| US-62 | None | E9 shows the date on the item. It does not name an Upcoming list. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-39 and US-54 out-of-scope lines, US-75's US-62 half, threat note T-1. |
| US-63 | None | The story said it was not in the approved specification. No source names Mark as code or Copy code. | Removed by Miguel at 19:01 IST. |
| US-64 | E3 | E3: "Sharing an item back out to other apps, as US-64 (Should)." Vision says the same. 15:55 amendment, section 3. Criteria stay proposed. | Traced |
| US-65 | None | E1 does not include a camera capture. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-03 and US-32 out-of-scope lines. The US-80 absent-`CAMERA` line stays. |
| US-66 | None | E3 stops at delete with undo. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-13's US-66 lines. |
| US-67 | None | No duplicate-file line. URL matching is US-49. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
| US-68 | None | No return-by or warranty line in E9 or the spec. | Removed by Miguel at 19:00 IST. Not a Later item. |
| US-69 | None | No shortcut or widget line. | Removed by Miguel at 19:00 IST. |
| US-70 | None | The story says it is not in the approved specification. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. US-33 retry stays. |
| US-71 | None | E3 inbox does not include batch triage. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-38's out-of-scope pointer. |
| US-72 | None | E11 does not ask for a usefulness score. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
| US-73 | E4 | Spec: "Inventing a conflict algorithm, a deletion rule, or a key-recovery design in this draft" stays open. "Concurrent edits and deletions are the open questions." | Traced. Placeholder for that open question, not a capability. |
| US-74 | E8 | Spec: "Keys on a new device" is an open design-gate question. | Traced. Placeholder, not a capability. |
| US-75 | E8 | 15:55 amendment names sensitive-item handling (US-75) as delegated, criteria still proposed. Topic exclusion is in that amendment. Lock-screen reminder is the 15:59 addendum. | Partly traced. Clauses that existed only for US-63, US-68, US-69, and US-82 were removed at 19:00 and 19:01 IST. The Upcoming row stays with US-62, which is still awaiting his decision. The rest of the proposed criteria stay proposed. |
| US-76 | E11 | E11 open question: provider, credential, and what is sent. Spec design gate says the same. | Traced. Placeholder, not a capability. |
| US-77 | E9 | E9: "No exact-alarm permission." Closed by Miguel on 2026-10-17 at 20:03 IST: inexact alarms, and no `SCHEDULE_EXACT_ALARM` or `USE_EXACT_ALARM`. | Traced. Not a story approval. |
| US-78 | E4 | 15:55 amendment names Drive scope and sign-out (US-78) as delegated. Spec still names `appDataFolder`. Criteria stay proposed. | Traced |
| US-79 | E2 | 15:55 amendment names link-preview skipping (US-79) as delegated. Token-looking URLs are the 15:59 addendum. Host rules stay proposed. | Traced |
| US-80 | E6 | 15:55 amendment names the permission set (US-80) as delegated. E9 names `POST_NOTIFICATIONS` and no exact-alarm permission. | Traced for the delegated permission set. The widget-receiver clause was removed with US-69 at 19:00 IST. The rest stays proposed. |
| US-81 | E6 | 15:55 amendment names sideload policy (US-81) as delegated. E6 requires a signed sideload APK. Criteria stay proposed. | Traced |
| US-82 | None | Spec Future Scope: merchants stay later, "No story." | Removed by Miguel at 19:00 IST. The backlog Later line for picking merchants out of a receipt stays. No new story. |
| US-83 | None | No amount or currency line. | Removed by Miguel at 19:00 IST. |
| US-84 | None | No purchase-date line. Date-saved filter is US-39. | Removed by Miguel at 19:00 IST. |
