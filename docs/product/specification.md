# Product specification: personal knowledge vault

> **Approval status: Draft. Not approved.**
> This is the first human approval gate. Do not start technical design, UX, threat modeling, test planning, or implementation from this file alone.
> Approver required: `mjbvilhena` — a named sign-off, or an explicit sentence that says to treat this specification as approved.
> Sign-off on file: none.
> Drafted from `docs/product/vision.md` by `sdlc-product-owner` on 2026-10-17. The vision file itself is still unaccepted.
> Template: `get_sdlc_template("product spec")`. Epic readiness notes: `get_definition_of_done("epic")` (that DoD is the shipped outcome, not this planning gate).

## Background & Value Proposition

Miguel comes across links, text, images, and PDFs in other apps and has nowhere private on his phone that keeps them as one searchable store. The vault is that store. He captures what matters, adds a few words about why when he wants to, and finds it later without a network.

It matters now because the request is to build this as v1, including an installable signed APK and a private backup path. The request does not give a business metric. Success is the capability in the vision: capture, search, offline use, and backup or export, at the quality bar he set.

## Target Audience

- **Primary user:** Miguel. No other persona is named.
- **Pain this addresses:** content that matters to him stays scattered in the apps where he saw it. He wants one on-device store he can search, and a private way to copy it off the phone.

## Scope & Phasing

### In Scope (MVP)

Everything in `docs/product/vision.md` under "What he asked to be able to do" is in v1. It is split into six draft epics. Epic text is not epic approval.

| Epic | Outcome | Draft |
|---|---|---|
| E1 Capture and keep | He can save a link, plain text, an image, or a PDF from the share sheet, or add a note or URL himself. Items keep the fields he named. The store is on the device and the app works offline. No analytics. | `docs/product/epics/E1-capture-and-keep.md` |
| E2 Link enrichment | A saved link gains a title, description, preview image, and readable text when a fetch works. When it does not, the URL remains. | `docs/product/epics/E2-link-enrichment.md` |
| E3 Find and curate | He can browse newest first, open, edit, delete with undo, manage tags, search with ranked highlighted matches, and filter by type and tag. | `docs/product/epics/E3-find-and-curate.md` |
| E4 Private backup | He can sign in and back up or restore via Drive app data, including on a schedule, and export or import a zip. Without an OAuth client ID, Settings explains that backup is not configured. | `docs/product/epics/E4-private-backup.md` |
| E5 Look and feel | The shipped UI is calm and minimal, in light and dark, with one accent and no dynamic color, plus an icon and a short name he accepts. | `docs/product/epics/E5-look-and-feel.md` |
| E6 Installable release | A signed release APK he can sideload, the tests he named, a README for build, install, and OAuth setup, and screenshots when an emulator is available. | `docs/product/epics/E6-installable-release.md` |

Constraints he already fixed, which later design must not reopen unless he does:

- Kotlin, Jetpack Compose, Material 3.
- Room with FTS for search.
- `minSdk` 26. Target SDK is the latest stable SDK at implementation time (the number is not pinned here).
- Google Sign-In / Credential Manager, Drive API, `drive.appdata` scope.
- WorkManager for the daily automatic backup example.
- OAuth client ID is build-time configuration, not a secret baked into the repo as a committed credential.
- Release signing uses a self-generated keystore that is documented and not committed.

### Future Scope (v2+)

He did not ask for a v2. Nothing is scheduled past v1.

### Out of Scope / Non-Goals

- Analytics, usage telemetry, or a crash-reporting product. He said no analytics.
- Play Store listing and distribution.
- Live sync across devices, or a second person's access to the vault.
- OCR, image editing, and PDF editing.
- Private APIs for YouTube or X. Those apps are examples of share sources.
- Material You dynamic color as the way the accent is chosen.
- Committing the release keystore, its passwords, or the OAuth client secret material.

A short name was requested as a suggestion. **Folio** is the suggestion. It is not the decided name. See Open Questions.

## Core Workflows

1. **Save something from another app**
   - Miguel is in a browser or another app, on a link, a passage of text, an image, or a PDF.
   - He shares it and chooses this vault.
   - The app stores an item of the matching type with the fields it can fill. A link also keeps the URL if page details cannot be fetched.
   - He can add or skip the optional note about why it matters.
2. **Find it later**
   - He opens the vault with no network.
   - The home list shows what he saved, newest first.
   - He searches, filters by type or tag, opens an item, and edits or deletes it. A delete can be undone.
3. **Keep a private copy**
   - In Settings he either sees that Drive backup is not configured yet, or he signs in, backs up now, and can turn on a daily backup.
   - He can restore from Drive, or export and import a zip on the device, without sending the vault to anyone else.

Sprint-level acceptance criteria live in the draft stories, not here. Those stories were written in the same run because the request asked for them. Writing them did not approve this specification.

## Risks & Dependencies

- Drive backup needs a Google Cloud OAuth client whose package name and signing-certificate SHA-1 match the release keystore. Until Miguel creates that client and supplies the client ID at build time, backup cannot succeed. The app must still install and run.
- Page fetches fail on offline devices, login walls, and pages that only render in a browser. The item must still keep the URL.
- Share-sheet behavior differs across Android versions and manufacturers. The types he named still have to be receivable on a normal current device.
- `drive.appdata` backups are hidden from the user's normal Drive UI. Restore has to find the app's own backup without him browsing a public folder.
- Images and PDFs can be large. The request does not set a size cap.
- An emulator may be unavailable when screenshots are due. The request allows explaining that, and does not allow fake screenshots.
- No `DOMAIN.md` or `LAYER.md` exists. Consultants were queried and returned none. This specification does not invent those rules.

## Open Questions

- [ ] **Q1.** What is the short name? Suggestion only: Folio. (@mjbvilhena)
- [ ] **Q2.** What is the Android application id? It depends on Q1 and is required in the OAuth setup steps. (@mjbvilhena)
- [ ] **Q3.** What is the single accent? He asked for one accent and did not name a color. Decide in the UX gate, not in this draft. (@mjbvilhena)
- [ ] **Q4.** Does tag management in v1 include renaming a tag, or only adding and removing tags? (@mjbvilhena)
- [ ] **Q5.** When he restores from Drive or imports a zip, do items that exist only on the device remain, or does the copy replace the local vault? (@mjbvilhena)
- [ ] **Q6.** The Wi-Fi only toggle is specified for automatic backup. This draft treats Back up now as running when he asks, even on mobile data. Confirm or correct that. (@mjbvilhena)
- [ ] **Q7.** Is OCR of scanned PDFs or images in v1? It was not requested. This draft leaves it out. (@mjbvilhena)
- [ ] **Q8.** Which API level is "the latest stable SDK" on the day of implementation? Record it in technical design. Do not guess it here. (@mjbvilhena)
- [ ] **Q9.** Must the share flow ask for the personal note and tags before the item is saved, or is editing them later enough? This draft requires that both can be empty and can be changed later. (@mjbvilhena)
- [ ] **Q10.** Is any encryption required beyond the app's private on-device storage? None was requested. (@mjbvilhena)

## Approval

| Gate | Status | What advances it |
|---|---|---|
| This specification | Draft | Named sign-off by `mjbvilhena`, or a sentence that treats this file as approved |
| Epics E1–E6 | Draft, separate gate | Not approved by approving this file |
| User stories | Draft, separate gate | Not approved by approving this file or the epics |

Next gate after a real approval of this file: epic approval, still by `mjbvilhena`. Story files already exist as drafts. Their presence is not a reason to skip epic approval or story approval, and it is not permission to open UX, RFC, ADR, test-plan, or implementation work.
