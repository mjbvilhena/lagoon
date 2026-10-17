# Product vision

> **Approval status: Draft. Not accepted.**
> The pipeline exits Vision when the user accepts a vision document. This file restates the request. It has not been accepted.
> Approver required: `mjbvilhena`.
> Sign-off on file: none.
> Source: the product request on 2026-10-17. No goals, personas, or metrics were added.

## Vision

Miguel saves digital content he comes across that matters to him. Over time that becomes a searchable knowledge store on his Android phone. He can send links, plain text, images, and PDFs in from other apps, or add a note or URL himself. The vault works fully offline. He can back it up privately to Google Drive and, if that is not available, export a zip on the device.

## Who it is for

Miguel. He is the only person named in the request. The vault is personal, not a shared workspace.

## What he asked to be able to do

- See the app in Android's share sheet and share links, plain text, images, and PDFs into it from other apps, including a browser, YouTube, and X.
- Add a note or a URL by hand inside the app.
- For shared links, fetch and store the page title, description, preview image, and readable text where possible. This is best effort. Offline, or when the fetch fails, keep the URL.
- Keep, on each item: title, content/body, source URL (optional), type (link, note, image, or PDF), tags, created date, and an optional personal note on why it matters.
- Search the full text of title, body, extracted text, tags, and notes. Results are ranked and highlighted. Filter by type and by tag. Search uses Room and FTS.
- Browse a home list, most recent first. Open an item, edit it, delete it with undo, and manage tags.
- Keep the primary store on the device. The app works fully offline. No analytics.
- Back up and restore with Google Drive, using Google Sign-In / Credential Manager and the Drive API with the app-data folder (`drive.appdata`), so backups stay private to the app.
- In Settings: sign in with Google, Back up now, automatic backup (daily via WorkManager is the example given), a Wi-Fi only toggle, last backup time, and Restore from Drive.
- Back up the database and the stored files (images and PDFs).
- Make the Google OAuth client ID a build-time config value. The app must build, install, and work fully offline when that client ID is missing. The backup screen then explains that backup is not configured, and it must not crash.
- Offer a local zip export and import to device storage as a fallback.
- Ship a polished, professional, minimalist interface: calm, lots of whitespace, refined typography, a restrained neutral palette with a single accent, smooth subtle animations, light and dark mode, and no dependence on Material You dynamic color. A tasteful app icon and a short name. No clutter and no placeholder text in the shipped UI.
- Deliver a working APK he can sideload, signed with a self-generated release keystore. Document the keystore. Do not commit secrets. `minSdk` 26. Target the latest stable SDK.
- Stack: Kotlin, Jetpack Compose, Material 3.
- Tests: unit tests for the repository/search layer and the backup-packaging layer, and at least a basic Compose UI test.
- A README that covers the build, installing the APK (allow installs from unknown sources), and the Google Drive setup, including how to create the OAuth client (package name and the signing certificate SHA-1) and where to put the client ID.
- Screenshots of home, search results, item detail, share-to-save, and settings/backup, from an emulator when one is available.

## How success would be recognized

The request does not name a numeric target. Success is the outcome above: Miguel can install the signed app without the Play Store, save and search his items offline, and back them up or export them as specified.

## Non-goals stated in the request

- No analytics.
- The interface must not depend on Material You dynamic color.
- Secrets, including the release keystore, are not committed.
- Drive backup must not crash or block the rest of the app when the OAuth client ID is absent.

## Not in the request

These are not promised and are not goals of this vision. They are listed so a later draft does not treat silence as a requirement.

- Publishing on the Play Store.
- A second person using the same vault, or live multi-device sync. Backup and restore is what was asked for.
- OCR of images or scanned PDFs.
- An image editor or a PDF editor.
- Special integrations with YouTube or X beyond receiving a share and, for a link, a best-effort page fetch.
- A chosen app name, package name, or accent color. A short name was asked for as a suggestion, not supplied.

## Later decisions

This vision file is still not accepted. It keeps the original request, including backup and restore, so that history stays readable.

`docs/product/specification.md` records what happened after this file was written. Those lines supersede the backup wording, the "not in the request" notes on multi-device copy and OCR, and the open status of Q5 and Q10. They do not accept this vision and they do not approve the specification.

In short, and only as a pointer:

- Miguel decided on seamless background sync, extra encryption, and a v1 that includes on-device OCR, reminders, multiple attachments, offline page snapshots, and a biometric lock on sensitive items. Trip or collection grouping is later.
- His bots' consensus on the delegated questions (name, application id, accent, tag merge, unmetered sync, text-layer PDF extraction, SDK 37, capture filing) is also in the specification. That consensus is not his approval.

