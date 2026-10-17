# Traceability review, 2026-10-17

> **Draft. Not approval.** This file proposes removals and trims. It does not delete or edit any story.
> Miguel (`mjbvilhena`) at 18:47 IST: "Any user story (and associated downstream artefacts) that you can't trace back to the vision, the product spec or the epics should be removed. Do a full review."
> At 18:48 IST he said: "Let me know what you find before removing." Nothing was removed in this run.
> Sources that count: `docs/product/vision.md`, `docs/product/specification.md`, approved epics E1–E9 and E11, the amendment approved on 2026-10-17 at 15:55 IST, and `docs/product/amendments/2026-10-17-chat-decisions.md` (15:59, 16:00, and 18:05 IST). E10 is Later and is not a v1 parent.
> A bot consensus counts only where that text is inside one of those sources. A review pack, a story, or a threat note is not a source.
> Skills: `sdlc-conductor` (template `lifecycle pipeline`), `sdlc-docs-backlog-review` (template `docs backlog review`), `sdlc-product-owner` (template `product spec`, Definition of Done `epic`). Templates were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This was not a live MCP session. No domain consultant was found. No MUST/NEVER rules were invented.
> Head reviewed: `0209d5c`. Stories stay Draft. This file records no approval.

## Proposed removals

These stories have no line in the sources. Downstream means the clauses and index rows that exist only because of the story. They are still in the repo.

| Story | Why it does not trace | Downstream that would go with it |
|---|---|---|
| US-48 | E11 confirms, links, and declines a new topic. It does not say he can rename, delete, unlink, or merge a topic after it is saved. | INDEX row, backlog gap line, REVIEW-PACK row. US-46 only points at US-48 as out of scope. |
| US-62 | E9 shows the date on the item and sends a notification. It does not name an Upcoming list. | INDEX, backlog, REVIEW-PACK. US-39 and US-54 name the list as out of scope. US-75 scenario "upcoming and a pinned shortcut" (the US-62 half). Threat note T-1 names US-62. |
| US-63 | The story says it is not in the approved specification. The 15:59 addendum says a sensitive reminder shows no code, and that ordinary items follow the 14:56 rule. That is notification text, not a Mark as code or Copy code control. | INDEX, backlog, REVIEW-PACK. US-34 clause "shows a code only when US-63 is built". US-75 scenario "a copied code". |
| US-65 | E1 capture is the share sheet, a note, or a URL. No camera intent. | INDEX, backlog, REVIEW-PACK. US-03 and US-32 name the camera as out of scope. US-80 listing `CAMERA` as absent stays: that line forbids the permission. |
| US-66 | E3 is delete with undo. A 30-day trash is not in that epic. | INDEX, backlog, REVIEW-PACK. US-13 MoSCoW line and out-of-scope line that name US-66. |
| US-67 | E1 matches a repeat URL (US-49). It does not notice a duplicate image or PDF. | INDEX, backlog, REVIEW-PACK. US-49 stays. |
| US-68 | E9 is one reminder or one expiry. Return-by and warranty are not in the vision, the spec, or E9. They are not in the Later list either. | INDEX, backlog, REVIEW-PACK. US-55 phrase "a return-by date, and a warranty end". US-62 given-list, which goes if US-62 goes. US-75 scenario "other sensitive notices" where it names return-by and warranty. Threat note T-2. |
| US-69 | No epic names a pinned shortcut or a widget. | INDEX, backlog, REVIEW-PACK. US-75 pinned-shortcut half. US-80 clause "a widget receiver may be exported only if a widget ships (US-69)". |
| US-70 | The story says it is not in the approved specification. E2 and E7 already store the snapshot and the OCR text. A pending-jobs screen is not named. | INDEX, backlog, REVIEW-PACK. The US-33 retry stays. |
| US-71 | E3 has an Untagged inbox, one item at a time (US-38). Batch multi-select is not named. | INDEX, backlog, REVIEW-PACK. US-38 out-of-scope pointer. |
| US-72 | E11 does not ask for a usefulness score, a near-duplicate rule, or a 20-item fixture. | INDEX, backlog, REVIEW-PACK. The score lives only in this story. |
| US-82 | Spec Future Scope: "Picking out booking references and merchants. Stays proposed and later... No story." E7 Future Scope says the same, "No story either way." A typed merchant is still a merchant story. | INDEX, backlog, REVIEW-PACK. US-15 "merchant column from US-82". US-50 merchant weight and the draft merchant scenario. US-75 "a merchant word". If removed, backlog should gain a Later note pointing at that spec line, and no new story. |
| US-83 | Amount and currency are not in the vision, the spec, or any epic. Not in the Later list. | INDEX, backlog, REVIEW-PACK. No other story stores an amount. |
| US-84 | Purchase date is not in the vision, the spec, or any epic. US-39 already filters by when the item was saved. | INDEX, backlog, REVIEW-PACK. |

## Proposed trims

The story stays. The named clause does not trace.

| Story | Clause to cut | Why |
|---|---|---|
| US-15 | "or, when US-82 is in the build, the merchant column from US-82" | Goes with US-82. Spec search list is title, body, extracted text, OCR text, tags, and the personal note. |
| US-28 | Scenario "add a date" | E1's row is last-used tags. E9 sets a reminder or expiry on the item, not an Add date control on the share row. |
| US-34 | "It shows a code only when US-63 is built" | Goes with US-63. The title on an ordinary lock-screen reminder stays, via the 15:59 addendum's reference to the 14:56 ordinary-item rule. |
| US-40 | Scenarios that replace a file name with `Screenshot ·`, `Image ·`, or `Scan ·` plus a date | E1 and the vision say an empty title fills from the page title or the file name. They do not define those constructed titles. The token-looking host-only rule stays (15:59 addendum, US-79). |
| US-41 | Scenario "a cap" of 8 names | E11 risks: "No cap is set here." |
| US-50 | Draft merchant scenario and the merchant weight of 5 | Not in the spec weight list. Goes with US-82. |
| US-53 | Scenario "reduced motion" | E5 says "subtle motion". It does not name the system animator scale. Palette, Light/Dark/System, and optional dynamic color stay. |
| US-55 | The words "a return-by date, and a warranty end" | Goes with US-68. Boot re-registration stays (E9). |
| US-75 | Return-by and warranty inside "other sensitive notices"; the US-62 half and the US-69 half of "upcoming and a pinned shortcut"; "a merchant word"; scenario "a copied code" | Each exists only for US-68, US-62, US-69, US-82, or US-63. The 15:59 lock-screen reminder and the 15:55 topic exclusion stay. |

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
| US-15 | E3 | Spec: "Search covers title, body, extracted text... tags, and the personal note." | Partly traced. Merchant column clause does not trace. |
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
| US-34 | E9 | E9: "An optional expiry or reminder date on an item." Spec US-34 line: 09:00 local, inexact, `POST_NOTIFICATIONS` at the first reminder. | Partly traced. The US-63 code clause does not trace. |
| US-35 | E8 | E8: "Encrypted database and encrypted attachment files" and keys in Android Keystore. Spec Q10: a lost key means the content is lost. | Traced |
| US-36 | E8 | E8: "Drive stores ciphertext only." | Traced |
| US-37 | E8 | E8: "Fingerprint or biometric lock on items marked sensitive" and "App lock is optional. The device PIN is the fallback." | Traced |
| US-38 | E3 | E3: "An Untagged / Inbox view." | Traced |
| US-39 | E3 | E3: "Sort and filter by date saved." | Traced |
| US-40 | E1 | E1: "An empty title fills from the page title or the file name." | Partly traced. Constructed Screenshot, Image, and Scan titles do not trace. |
| US-41 | E11 | E11: identify main ideas and topics. 18:05 addendum: "Topics on by default, with an off switch." 15:55 amendment: sensitive items are excluded. | Partly traced. The cap of 8 does not trace. |
| US-42 | E11 | E11: "If an identified topic already exists, the new item is linked to it." | Traced |
| US-43 | E11 | E11: "A new topic is created only after he confirms." | Traced |
| US-44 | E11 | E11: "Offline, the item is stored and topic processing is queued." And the topic job's own Unmetered only setting, from the 15:55 amendment. | Traced |
| US-45 | E11 | E11 audience: "later looking across items that share a subject." | Traced. Judgement: no screen is named. |
| US-46 | E11 | E11: "If he declines, the topic is not created. The item remains." | Traced. Judgement: rename-before-confirm is treated as that confirmation. |
| US-47 | E11 | E11 workflow: he can still save, and processing waits. "The item remains." | Traced. Enabling so a failed call does not lose the item. |
| US-48 | None | No rename, delete, unlink, or merge of a saved topic in E11. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, and US-46's out-of-scope pointer. |
| US-49 | E1 | E1: "A second share of the same page matches on a normalised URL... updates the existing item." | Traced |
| US-50 | E3 | Spec US-15 line: weighted BM25, title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. | Partly traced. Merchant weight 5 does not trace. |
| US-51 | E4 | E4: "The vault syncs in the background." | Traced. Judgement: error text is not named. |
| US-52 | E4 | E4: "Zip export and import." | Traced |
| US-53 | E5 | E5 palette, Light/Dark/System, and "Dynamic color is an optional toggle." | Partly traced. Reduced motion does not trace. |
| US-54 | E9 | E9: "An expiry notifies 7 days before the date and again on the day." | Traced |
| US-55 | E9 | E9: "Reminders are stored and re-registered on `BOOT_COMPLETED`." | Partly traced. Return-by and warranty do not trace. Which install posts is a judgement call, not an E9 line. |
| US-56 | E4 | E4: "Sync merges: an item that exists on only one side is kept." | Traced. Enabling: merge needs a stable item id. |
| US-57 | E3 | Spec US-15 line: FTS5 with an encrypted database is a design-gate risk. "Spike it early." E8 requires encryption and E3 requires search. | Traced. Enabling for those two lines. |
| US-58 | E2, E7, E11 | E1: enrichment and topic processing wait. E11 risks: text from E2, E7, and the snapshot may be sent. | Traced. Enabling so those steps have an order. Judgement: the order itself is not specified. |
| US-59 | E4, E5, E9, E11 | Spec: "Settings then explain that sync is not configured." E5 theme, E9 09:00 default, E11 topic switch and Unmetered only, vision disclosure in Settings. | Traced. Enabling host for those controls. |
| US-60 | E11 | E11: he is asked before a new topic is created, and an existing topic is linked. | Traced. Judgement: a list of pending suggestions is not named. |
| US-61 | E1 | E1: "Source domain and sending app (US-61) are a Should." Vision says the same. 15:55 amendment, section 5. | Traced |
| US-62 | None | E9 shows the date on the item. It does not name an Upcoming list. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-39 and US-54 out-of-scope lines, US-75's US-62 half, threat note T-1. |
| US-63 | None | The story says it is not in the approved specification. No source names Mark as code or Copy code. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-34's US-63 code clause, US-75 scenario "a copied code". |
| US-64 | E3 | E3: "Sharing an item back out to other apps, as US-64 (Should)." Vision says the same. 15:55 amendment, section 3. Criteria stay proposed. | Traced |
| US-65 | None | E1 does not include a camera capture. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-03 and US-32 out-of-scope lines. The US-80 absent-`CAMERA` line stays. |
| US-66 | None | E3 stops at delete with undo. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-13's US-66 lines. |
| US-67 | None | No duplicate-file line. URL matching is US-49. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
| US-68 | None | No return-by or warranty line in E9 or the spec. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-55's return-by and warranty words, US-75's return-by and warranty notice, threat note T-2. Not a Later item unless Miguel adds one. |
| US-69 | None | No shortcut or widget line. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-75's pinned-shortcut half, US-80's widget-receiver clause. |
| US-70 | None | The story says it is not in the approved specification. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. US-33 retry stays. |
| US-71 | None | E3 inbox does not include batch triage. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-38's out-of-scope pointer. |
| US-72 | None | E11 does not ask for a usefulness score. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
| US-73 | E4 | Spec: "Inventing a conflict algorithm, a deletion rule, or a key-recovery design in this draft" stays open. "Concurrent edits and deletions are the open questions." | Traced. Placeholder for that open question, not a capability. |
| US-74 | E8 | Spec: "Keys on a new device" is an open design-gate question. | Traced. Placeholder, not a capability. |
| US-75 | E8 | 15:55 amendment names sensitive-item handling (US-75) as delegated, criteria still proposed. Topic exclusion is in that amendment. Lock-screen reminder is the 15:59 addendum. | Partly traced. Clauses that exist only for US-62, US-63, US-68, US-69, and US-82 do not trace. The rest of the proposed criteria stay proposed. |
| US-76 | E11 | E11 open question: provider, credential, and what is sent. Spec design gate says the same. | Traced. Placeholder, not a capability. |
| US-77 | E9 | E9: "No exact-alarm permission." The amendment lists exact alarms as not decided by the 15:55 approval. | Traced. Placeholder. The epic already says inexact. |
| US-78 | E4 | 15:55 amendment names Drive scope and sign-out (US-78) as delegated. Spec still names `appDataFolder`. Criteria stay proposed. | Traced |
| US-79 | E2 | 15:55 amendment names link-preview skipping (US-79) as delegated. Token-looking URLs are the 15:59 addendum. Host rules stay proposed. | Traced |
| US-80 | E6 | 15:55 amendment names the permission set (US-80) as delegated. E9 names `POST_NOTIFICATIONS` and no exact-alarm permission. | Partly traced. The widget-receiver clause does not trace. The rest stays proposed. |
| US-81 | E6 | 15:55 amendment names sideload policy (US-81) as delegated. E6 requires a signed sideload APK. Criteria stay proposed. | Traced |
| US-82 | None | Spec Future Scope: merchants stay later, "No story." | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK, US-15 merchant clause, US-50 merchant weight, US-75 merchant word. Later note belongs in the backlog only after Miguel agrees, pointing at that spec line. |
| US-83 | None | No amount or currency line. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
| US-84 | None | No purchase-date line. Date-saved filter is US-39. | Untraceable. Proposed for removal. Downstream: INDEX, backlog, REVIEW-PACK. |
