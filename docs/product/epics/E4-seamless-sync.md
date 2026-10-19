# Epic E4: Seamless sync

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-18.

## Background & Value Proposition

There is no explicit backup or restore. Sync runs in the background. The phone holds the vault. Sync copies it to Drive `appDataFolder` when a network is allowed. A new install or a second device signs in and receives it. A zip on the device remains if Drive is not set up.

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
  - Unmetered-only preference for background sync (`WorkManager` `UNMETERED`).
  - A new install or second device signs in and receives the vault. Sync merges: an item that exists on only one side is kept.
  - Proposed amendment. Needs approval by mjbvilhena. The in-trash state syncs like a field, and the later edit wins (US-66, Must, in v1). v1 does not ship without it. On every phone the item is hidden and its reminders are silenced, and he can restore it from any phone. Only a user-initiated edit whose `userEditedAt` is later than `trashedAt` brings it back. A system write does not. An older user edit is kept. A reminder that fell due in trash fires once on restore. A restore made before the deadline that arrives after a tombstone brings the item back. The tombstone, the permanent delete, spreads only after 30 × 24 hours. A delete does not become a tombstone by skipping trash. Sync still keeps an item that exists on only one side. A tombstoned id is not that item. Decided by mjbvilhena; this record is Proposed until he approves it. This is not approved.
  - Proposed amendment. Needs approval by mjbvilhena. A lost phone's Drive file is retired only through "Forget this phone" in Settings. The action names one other install, deletes that install's records file, and does not change the passphrase. It is never automatic. This is not approved.
  - Zip export and import on device storage, as a fallback. It does not replace sync.
  - Bytes sent to Drive are ciphertext (E8). This epic does not choose the cipher.
  - Background sync tells him when it fails (A-E4-1, US-51). Acceptance: there is still no Back up now and no Restore. A failure is an error he can read.
  - Each item keeps one id and an updated time across an edit, a repeat save, and a sync (A-E4-2, US-56). Acceptance: an edit or a second device does not fork the item. Which side wins when two updates share that id is US-73. Criteria follow from the ADR at the design gate.
  - Theme, sync, the reminder default, app lock, the topic controls, and the ML Kit disclosure slot live on one Settings screen (A-E4-3, US-59). Acceptance: he does not hunt through the app for those controls. The screen has no Back up now, no Restore, and no last-backup time. Each control keeps the behavior of its own story.
  - Sign-out does not delete the vault on the phone (A-E4-4, US-78). Drive access stays `appDataFolder`. Acceptance: the phone vault remains after sign-out. The rest of the scope and sign-out behavior is the criteria on US-78.
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

- Depends on E1 for items and files, and on E8 for ciphertext. Key transport to the second device is US-74. Criteria follow from the ADR at the design gate. This epic does not invent it.
- Concurrent edits and deletion propagation are US-73. Criteria follow from the ADR at the design gate. Stories do not pick a winner or a delete rule.
- OAuth client setup is E6's README, after a real SHA-1 exists.

## Open Questions

- [ ] Same item edited on two devices. US-73. Criteria follow from the ADR at the design gate.
- [ ] How deletions propagate. US-73. Criteria follow from the ADR at the design gate.
- Proposed amendment. Needs approval by mjbvilhena. Deletion propagation is the synced in-trash field, then a tombstone after 30 × 24 hours, in the scope bullet above. US-66 is Must. v1 does not ship without that trash. An item with a tombstone is not kept as a one-sided item. This is not approved.
- [ ] How keys reach the second device. US-74. Criteria follow from the ADR at the design gate. Owned with E8.
- One-sided items are kept. That question is closed.

## Draft stories

- US-17 See that sync is not configured
- US-18 Sign in and let the vault sync
- US-19 Keep background sync on unmetered networks
- US-20 Receive the vault on a new install
- US-21 Export a zip
- US-52 Import a zip and merge it.
- US-30 No manual backup or restore
- US-51 Sync in the background and show errors
- US-56 Keep a stable identity for each item
- US-59 Use one Settings screen
- US-73 Decide sync conflicts and deletions. Placeholder. Not ready for review. No Given/When/Then and no MoSCoW yet. It does not meet the user-story Definition of Done. Criteria follow from the ADR at the design gate.
- US-78 Drive scope and sign-out. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
