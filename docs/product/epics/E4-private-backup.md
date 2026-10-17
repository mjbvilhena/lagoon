# Epic E4: Private backup

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Does not approve child stories. Shipped-epic DoD is not claimed.

## Background & Value Proposition

The vault lives on one phone. Miguel asked for a private Google Drive backup in the app-data folder, and a zip on device storage when Drive is not set up. Backup must not become a crash or a blocker when the OAuth client ID is missing.

No separate success metric was given.

## Target Audience

Miguel, protecting the library he already saved.

## Scope & Phasing

- **In Scope (MVP):**
  - Settings: sign in with Google, Back up now, automatic backup (daily via WorkManager is the given mechanism), Wi-Fi only toggle, last backup time, Restore from Drive.
  - Google Sign-In / Credential Manager and the Drive API with `drive.appdata` only. Backups are not placed in a folder he shares.
  - The backup includes the database and stored files (images and PDFs).
  - The OAuth client ID is a build-time value. With it unset, the app still builds, installs, and works offline, and the backup screen explains that backup is not configured.
  - Local zip export and import to device storage.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Live sync. Backup to a provider other than Google Drive. A committed client ID or keystore. Showing app-data files in the ordinary Drive browser as a feature.

## Core Workflows

1. **Backup is not configured**
   - He opens Settings on a build with no client ID.
   - He can read why backup is unavailable. The rest of the app still works. He can still export a zip.
2. **Back up and restore**
   - On a build with a client ID, he signs in, runs Back up now, and sees the last backup time.
   - He can turn on a daily backup and choose Wi-Fi only.
   - He can restore that backup onto the app.
3. **Zip fallback**
   - He exports a zip to device storage and can import a zip the app exported.

## Risks & Dependencies

- Depends on E1 so there is a database to back up, and on stored image/PDF files from E1 and E2.
- The OAuth client must be created by Miguel in Google Cloud with the release package name and SHA-1. That procedure is a README obligation in E6, not something this draft can complete.
- Q5 (replace versus merge on restore/import) changes the restore outcome. Stories do not invent the answer. US-20 and US-21 keep their Must criteria on the empty-vault case and mark the non-empty case as open.
- Q6 (whether Back up now ignores the Wi-Fi toggle) is called out the same way.

## Open Questions

- [ ] Q2. Application id, required for the OAuth client. (@mjbvilhena)
- [ ] Q5. Restore and import versus items that exist only locally. (@mjbvilhena)
- [ ] Q6. Does Back up now honor the Wi-Fi only toggle? (@mjbvilhena)
- [ ] Q8. Not a backup question, but the target SDK is unpinned. (@mjbvilhena)

## Draft stories

- US-17 See that Drive backup is not configured
- US-18 Sign in and back up now
- US-19 Run a daily backup and see when it last succeeded
- US-20 Restore from Drive
- US-21 Export and import a zip
