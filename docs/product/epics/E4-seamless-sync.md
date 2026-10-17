# Epic E4: Seamless sync

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Miguel decided there is no explicit backup or restore. That decision is part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is still Draft and awaits his approval. Shipped-epic DoD is not claimed.

## Background & Value Proposition

He said: "There should be no explicit backup and restore, there should be just a seamless sync behind the scenes." The phone holds the vault. Sync copies it to Drive `appDataFolder` when a network is allowed. A new install or a second device signs in and receives it. A zip on the device remains if Drive is not set up.

No separate success metric was given.

## Target Audience

Miguel, on this phone and on a later install of the same app.

## Scope & Phasing

- **In Scope (MVP):**
  - Background sync of the vault (database and attachments) through the Drive API `appDataFolder`. Not Android Auto Backup.
  - `appDataFolder` stays hidden in the Drive UI.
  - On-device first. The app works fully offline. Sync runs when connectivity allows.
  - No Back up now, no Restore, no last-backup-time control, no size warning, and no roaming warning on a manual backup.
  - Sign in with Google (Google Sign-In / Credential Manager). A missing OAuth client ID leaves sync unconfigured, explains that, and does not crash.
  - Unmetered-only preference for background sync (`WorkManager` `UNMETERED`), from the bots' consensus, kept because it still fits background sync.
  - A new install or second device signs in and receives the vault. Sync merges: an item that exists on only one side is kept.
  - Zip export and import on device storage, as a fallback. It does not replace sync.
  - Bytes sent to Drive are ciphertext (E8). This epic does not choose the cipher.
- **Future Scope (v2+):** Nothing named here.
- **Out of Scope / Non-Goals:** A backup button, a restore wizard, Android Auto Backup, a second cloud provider, showing app-data files in the normal Drive browser.

## Core Workflows

1. **Sync is not configured**
   - He opens Settings with no client ID.
   - He reads that sync is not configured. Capture and search still work. Zip export still works.
2. **It syncs itself**
   - On a configured build he signs in.
   - The vault syncs in the background. He is not asked to back up or restore, and he is not shown a last-backup clock.
   - With the unmetered-only preference on, sync waits for an unmetered network.
3. **Another device**
   - He installs the app again, or on a second phone, and signs in.
   - The vault from Drive is merged into the device.
4. **Zip fallback**
   - He exports a zip and can import one the app exported, including when Drive is unavailable.

## Risks & Dependencies

- Depends on E1 for items and files, and on E8 for ciphertext. Key transport to the second device is an open question for Miguel. This epic does not invent it.
- Concurrent edits and deletion propagation are open questions for him. Stories must not pick a winner or a delete rule.
- OAuth client setup is E6's README, after a real SHA-1 exists.

## Open Questions

- [ ] Same item edited on two devices. (@mjbvilhena)
- [ ] How deletions propagate. (@mjbvilhena)
- [ ] How keys reach the second device. Owned with E8. (@mjbvilhena)
- Q5 is not open. His sync decision resolved it: merge.

## Draft stories

- US-17 See that sync is not configured
- US-18 Sign in and let the vault sync
- US-19 Keep background sync on unmetered networks
- US-20 Receive the vault on a new install
- US-21 Export and import a zip
- US-30 No manual backup or restore
