# Product backlog

> Statuses describe evidence in the tree on 2026-10-17. **Draft is not approval.** **Not started is not a skip.** A line marked Draft means the file exists and `mjbvilhena` has not signed it.
> No story is Done. There is no app, no test run, and no named sign-off.

## Gate

Stopped at **product specification approval**. See `docs/sdlc/pipeline-state.md`.

## Epic E1: Capture and keep

> Goal: Miguel can save a link, text, an image, or a PDF from the share sheet, or add a note or URL himself, and the item stays on the device. *(Draft — not approved)* Evidence: `docs/product/epics/E1-capture-and-keep.md`.

- **US-01** *(Draft)* Share a link. Evidence: `docs/product/stories/US-01-share-a-link.md`. Not implemented.
- **US-02** *(Draft)* Share plain text. Evidence: `docs/product/stories/US-02-share-plain-text.md`. Not implemented.
- **US-03** *(Draft)* Share an image. Evidence: `docs/product/stories/US-03-share-an-image.md`. Not implemented.
- **US-04** *(Draft)* Share a PDF. Evidence: `docs/product/stories/US-04-share-a-pdf.md`. Not implemented.
- **US-05** *(Draft)* Add a note by hand. Evidence: `docs/product/stories/US-05-add-a-note-by-hand.md`. Not implemented.
- **US-06** *(Draft)* Add a URL by hand. Evidence: `docs/product/stories/US-06-add-a-url-by-hand.md`. Not implemented.
- **US-07** *(Draft)* Offline use, no analytics. Evidence: `docs/product/stories/US-07-offline-and-no-analytics.md`. Not implemented.

## Epic E2: Link enrichment

> Goal: A saved link gains page details when a fetch works, and keeps its URL when it does not. *(Draft — not approved)* Evidence: `docs/product/epics/E2-link-enrichment.md`.

- **US-08** *(Draft)* Enrich a link. Evidence: `docs/product/stories/US-08-enrich-a-link.md`. Not implemented.
- **US-09** *(Draft)* Keep the URL when enrichment fails. Evidence: `docs/product/stories/US-09-keep-the-url-when-enrichment-fails.md`. Not implemented.

## Epic E3: Find and curate

> Goal: He can browse, read, edit, delete with undo, manage tags, search, and filter. *(Draft — not approved)* Evidence: `docs/product/epics/E3-find-and-curate.md`.

- **US-10** *(Draft)* Home list, newest first. Evidence: `docs/product/stories/US-10-browse-newest-first.md`. Not implemented.
- **US-11** *(Draft)* Item detail. Evidence: `docs/product/stories/US-11-read-an-item.md`. Not implemented.
- **US-12** *(Draft)* Edit, including why it matters. Evidence: `docs/product/stories/US-12-edit-an-item.md`. Not implemented.
- **US-13** *(Draft)* Delete with undo. Evidence: `docs/product/stories/US-13-delete-with-undo.md`. Not implemented.
- **US-14** *(Draft)* Tag management. Evidence: `docs/product/stories/US-14-manage-tags.md`. Not implemented.
- **US-15** *(Draft)* Ranked, highlighted search. Evidence: `docs/product/stories/US-15-search.md`. Not implemented.
- **US-16** *(Draft)* Filter by type and tag. Evidence: `docs/product/stories/US-16-filter-by-type-and-tag.md`. Not implemented.

## Epic E4: Private backup

> Goal: Drive app-data backup and restore, a daily Wi-Fi-aware schedule, and a zip fallback. Missing OAuth config does not crash the app. *(Draft — not approved)* Evidence: `docs/product/epics/E4-private-backup.md`.

- **US-17** *(Draft)* Backup not configured. Evidence: `docs/product/stories/US-17-backup-not-configured.md`. Not implemented.
- **US-18** *(Draft)* Sign in and back up now. Evidence: `docs/product/stories/US-18-back-up-now.md`. Not implemented.
- **US-19** *(Draft)* Daily automatic backup. Evidence: `docs/product/stories/US-19-automatic-backup.md`. Not implemented.
- **US-20** *(Draft)* Restore from Drive. Evidence: `docs/product/stories/US-20-restore-from-drive.md`. Not implemented. Q5 is open, so the non-empty case is not specified.
- **US-21** *(Draft)* Zip export and import. Evidence: `docs/product/stories/US-21-export-and-import-a-zip.md`. Not implemented. Q5 is open for the non-empty case.

## Epic E5: Look and feel

> Goal: Calm Material 3 light and dark UI, one accent, no dynamic color, an icon, and a short name he accepts. *(Draft — not approved)* Evidence: `docs/product/epics/E5-look-and-feel.md`. UX artefact not started.

- **US-22** *(Draft)* Light and dark appearance. Evidence: `docs/product/stories/US-22-calm-appearance.md`. Not implemented. Q3 is open.
- **US-23** *(Draft)* Icon and short name. Evidence: `docs/product/stories/US-23-icon-and-name.md`. Not implemented. Q1 is open. "Folio" is only a suggestion.

## Epic E6: Installable release

> Goal: Signed sideload APK, the named tests, README, and screenshots when an emulator exists. *(Draft — not approved)* Evidence: `docs/product/epics/E6-installable-release.md`.

- **US-24** *(Draft)* Signed release APK. Evidence: `docs/product/stories/US-24-signed-apk.md`. Not implemented.
- **US-25** *(Draft)* Search, packaging, and Compose tests. Evidence: `docs/product/stories/US-25-automated-tests.md`. Not implemented.
- **US-26** *(Draft)* README for build, sideload, and OAuth. Evidence: `docs/product/stories/US-26-readme-build-and-oauth.md`. The root README does not yet contain those steps, because there is no app. That absence is correct.
- **US-27** *(Draft)* Screenshots. Evidence: `docs/product/stories/US-27-screenshots.md`. Not started. No images were fabricated.

## Specification and vision

- **Vision** *(Draft — not accepted)* Evidence: `docs/product/vision.md`.
- **Product specification** *(Draft — approval gate)* Evidence: `docs/product/specification.md`.

## Repository setup

Checks from the harness mapping are in the tree. That is not product approval. Kotlin CI was not added. Evidence: `docs/sdlc/repository-setup.md`, `.github/workflows/secret-scan.yml`, `.github/workflows/markdownlint.yml`, `.github/CODEOWNERS`, `.gitignore`, `README.md`.

- **Harness install** *(In place)* Cursor skills under `.cursor/commands/`. Submodule `third_party/ai-sdlc-harness` at `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`. Evidence: `docs/sdlc/harness-install.md`.

## Not started, and not skipped

These need a later explicit go-ahead after the gates. No skip sentence was recorded.

- UX design (`sdlc-ux-designer`)
- Technical design: RFC, API design, ADRs (`sdlc-rfc-drafter`, `sdlc-api-designer`, `sdlc-adr-drafter`)
- Test strategy and threat model (`sdlc-test-planner`, `sdlc-threat-modeler`)
- `DOMAIN.md` / `LAYER.md` (no bound has been designed, so the architect skills were not run, and no MUST/NEVER rules were invented)
- App implementation (`sdlc-implementer`)
- Verification skills (test writer, UI tests beyond the plan, code review, security review)

## Open questions

Tracked in `docs/product/specification.md`. Q1 name, Q2 application id, Q3 accent, Q4 tag rename, Q5 restore/import versus local-only items, Q6 manual backup and the Wi-Fi toggle, Q7 OCR, Q8 target SDK level, Q9 note and tags at share time, Q10 extra encryption. US-15 also leaves any ranking beyond title-over-body as an open design question.
