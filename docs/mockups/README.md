# UX review mockups

Review aid only. `docs/technical_design/ux-design.md` is the source of truth. If this mockup and that file disagree, the file wins.

These pages were built from `docs/technical_design/ux-design.md` at commit `29c343afe30a9124c7788e212e1ee94e51192df7`.

The status of that file is Proposed. These pages are not an approval of it.

## Rebuild

1. Read `docs/technical_design/ux-design.md` at the commit above.
2. If you are picturing a newer commit, set `UX_COMMIT` in `docs/mockups/build.py` to that commit and update the page definitions so they still match the file. Do not add copy the file does not contain. Where the file leaves a detail unspecified, keep the label `not specified in ux-design.md`.
3. From the repository root, run `python3 docs/mockups/build.py`.
4. Open `docs/mockups/index.html`.

The script checks that the working copy of `ux-design.md` matches `UX_COMMIT`, and that every product string it writes is contained in that file. It does not edit `ux-design.md`.

## Preview

Open `index.html` in a browser. No server and no network are required. Each screen is 360 by 800 CSS pixels inside a device frame. System, Light, and Dark above the frame preview the palette. System is selected by default.

## Deploy

These files are static. There is no build step. `.nojekyll` is in this directory.

`.github/workflows/pages.yml` uploads `docs/mockups`, including hidden files, so `.nojekyll` is in the site root and GitHub Pages does not run Jekyll. The workflow uses the official actions `actions/checkout`, `actions/configure-pages`, `actions/upload-pages-artifact`, and `actions/deploy-pages`. It does not use a secret or a personal access token.

A push to `master` that changes `docs/mockups/**` or `.github/workflows/pages.yml` runs the workflow. The file also lists `workflow_dispatch`. GitHub offers that manual run only after `.github/workflows/pages.yml` is on the default branch, `master`.

The `github-pages` environment allows deployments from `master` only, unless its deployment branch rules are widened. The first deployment happens after this workflow reaches `master`. A run on another branch does not publish the site while that rule stands.

Pages source is GitHub Actions. `GITHUB_TOKEN` cannot change that setting. The one-time repository setting is **Settings > Pages > Build and deployment > Source: GitHub Actions**.

The `gh-pages` branch is not deleted by this workflow. With GitHub Actions as the source, that branch is optional.

## Pages

- [Home list](home-list.html) — Populated rows (`#home-list`)
- [Home list](home-list-empty.html) — Empty vault (`#home-list`)
- [Home list](home-list-sync-paused.html) — Sync-paused banner (`#home-list, #sync-paused-banner`)
- [Home list](home-list-filter-empty.html) — Filter matches nothing (`#home-list, #flows`)
- [Item detail](item-detail.html) — Ordinary item (`#item-detail`)
- [Item detail](item-detail-preview.html) — Skipped preview (`#item-detail, #link-preview-prompt`)
- [Item detail](item-detail-enrichment-failed.html) — Enrichment failed (`#item-detail, #copy`)
- [Item detail](item-detail-locked.html) — Sensitive item locked (`#item-detail`)
- [Item detail](item-detail-sensitive-open.html) — Sensitive item after unlock (`#item-detail`)
- [Edit item](edit-item.html) — Fields (`#edit-item`)
- [Edit item](edit-item-title-required.html) — Empty title refused (`#edit-item, #copy`)
- [Edit item](edit-item-already-sent.html) — Marked sensitive later (`#edit-item, #copy`)
- [Search](search.html) — Match and locked sensitive row (`#search`)
- [Search](search-empty.html) — Miss (`#search`)
- [Quick-add row](quick-add.html) — With tags (`#quick-add-row`)
- [Quick-add row](quick-add-no-tags.html) — No tags yet (`#quick-add-row`)
- [Add a note](add-note.html) — Fields (`#add-a-note`)
- [Add a note](add-note-not-saved.html) — Empty body (`#add-a-note, #copy`)
- [Add a URL](add-url.html) — Fields (`#add-a-url`)
- [Which-item picker](which-item.html) — Live matches (`#which-item-picker`)
- [Which-item picker](which-item-trash.html) — Every match is in trash (`#which-item-picker`)
- [Settings](settings.html) — Sync is not configured (`#settings`)
- [Settings](settings-signed-in.html) — Signed in (`#settings, #copy`)
- [Settings](settings-not-signed-in.html) — Not signed in (`#settings, #copy`)
- [Settings](settings-revoked.html) — Sign-in revoked (`#settings, #copy`)
- [Settings](settings-drive-full.html) — Drive full (`#settings, #copy`)
- [Settings](settings-upload-failed.html) — Upload failed (`#settings, #copy`)
- [Settings](settings-sync-paused.html) — Sync-paused banner in the sync area (`#settings, #sync-paused-banner`)
- [Settings](settings-api-30.html) — Dynamic color hidden (`#settings`)
- [Settings](settings-hardware-warning.html) — Key is not hardware-backed (`#settings`)
- [Settings](settings-accepted-yes.html) — Text recognition accepted (`#settings, #ml-kit-disclosure`)
- [Settings](settings-accepted-no.html) — Text recognition not accepted (`#settings, #ml-kit-disclosure`)
- [Settings](settings-topic-key-stored.html) — Topic key stored on this phone (`#settings`)
- [Settings](drive-delete-confirm.html) — Disconnect and delete confirmation (`#settings, #flows`)
- [Trash](trash.html) — Rows (`#trash`)
- [Trash](trash-after-deadline.html) — After the deadline, before removal (`#trash`)
- [Trash](trash-read-only.html) — Read-only open (`#trash, #item-detail`)
- [Forget this phone](forget-this-phone.html) — Other installs (`#forget-this-phone`)
- [Forget this phone](forget-this-phone-empty.html) — No other phone (`#forget-this-phone`)
- [Forget this phone](notice-nothing-forgotten.html) — Failed before delete (`#forget-this-phone, #copy`)
- [Full-screen image or PDF page](fullscreen.html) — Open (`#full-screen-image-or-pdf-page`)
- [Full-screen image or PDF page](pdf-password-protected.html) — Password required to open (`#full-screen-image-or-pdf-page, #copy`)
- [Passphrase setup](passphrase-setup.html) — First run (`#passphrase-setup`)
- [Passphrase setup](passphrase-setup-hardware.html) — Key will not be hardware-backed (`#passphrase-setup`)
- [Passphrase setup](passphrase-setup-low-memory.html) — Cannot allocate 65536 KiB (`#passphrase-setup`)
- [Passphrase setup](passphrase-decline.html) — After Decline (`#passphrase-setup`)
- [Change passphrase](change-passphrase.html) — Normal change (`#change-passphrase`)
- [Change passphrase](change-passphrase-saved.html) — After save (`#change-passphrase`)
- [Passphrase](passphrase-wrong.html) — Wrong passphrase (`#copy, #passphrase-setup`)
- [Forgotten-passphrase reset](forgotten-passphrase-reset.html) — This phone still holds the key (`#forgotten-passphrase-reset`)
- [Forgotten-passphrase reset](passphrase-reset-waiting.html) — Another phone holds the key (`#forgotten-passphrase-reset`)
- [Returning forgotten phone](returning-phone.html) — Passphrase before discard (`#flows`)
- [ML Kit disclosure](ml-kit-disclosure.html) — Before the first OCR use (`#ml-kit-disclosure`)
- [Topic list](topic-list.html) — A topic from a suggestion (`#topic-list`)
- [Topic list](topic-list-empty.html) — Empty (`#topic-list`)
- [Pending suggestions](pending-suggestions.html) — One suggestion (`#pending-suggestions`)
- [Topic detail](topic-detail.html) — Linked item (`#topic-detail`)
- [Topic detail](topic-detail-empty.html) — No links (`#topic-detail`)
- [Topic detail](topic-detail-sensitive-hidden.html) — Locked sensitive link (`#topic-detail`)
- [Topic failure](notice-try-again.html) — Try again (`#copy`)
- [Topic failure](notice-no-topics.html) — Empty result (`#copy, #topic-list`)
- [First topic key](notice-send-earlier.html) — Ask once (`#copy`)
- [Upcoming](upcoming.html) — Future, expired, and sensitive (`#upcoming`)
- [Inbox](inbox.html) — Untagged rows (`#inbox`)
- [Undo](notice-undo.html) — After delete (`#notices-that-are-not-screens`)
- [Repeat link](notice-repeat-link.html) — Snackbar (`#notices-that-are-not-screens`)
- [Dropped edit](notice-dropped-edit.html) — Before unlock (`#notices-that-are-not-screens, #copy`)
- [Dropped edit](notice-dropped-edit-title.html) — After unlock (`#notices-that-are-not-screens, #copy`)
- [Orphan files removed](notice-files-removed.html) — After rebuild (`#notices-that-are-not-screens, #copy`)
- [Unsynced edits lost](notice-edits-lost.html) — After rebuild (`#notices-that-are-not-screens, #copy`)
- [Ordinary reminder](notice-reminder.html) — System notification (`#notices-that-are-not-screens`)
- [Sensitive reminder or expiry](notice-reminder-sensitive.html) — System notification (`#notices-that-are-not-screens`)
- [Share in](system-share.html) — Android share sheet (`#system-surfaces-that-are-not-lagoon-screens, #flows`)
- [Google sign-in](system-sign-in.html) — System sheet (`#system-surfaces-that-are-not-lagoon-screens`)
- [Biometric prompt](system-biometric.html) — US-37 (`#system-surfaces-that-are-not-lagoon-screens`)
- [System camera](system-camera.html) — Capture (`#system-surfaces-that-are-not-lagoon-screens, #flows`)
- [Link viewer](system-viewer.html) — Custom Tabs or ACTION_VIEW (`#system-surfaces-that-are-not-lagoon-screens`)
- [Launcher](launcher.html) — Name and icon (`#system-surfaces-that-are-not-lagoon-screens`)
- [Clock warning](clock-not-designed.html) — Not designed (`#notices-that-are-not-screens, #open-questions`)
