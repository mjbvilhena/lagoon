# US-87 Forget this phone

> **Approval status: Proposed for story review. Non-security parts approved.**
> Approved on 2026-10-23 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E4.

**MoSCoW:** Must. v1.

## Title

Forget this phone.

## User story

**As a** person named Miguel
**I want** a Settings button that retires a lost phone's Drive sync file, and never does that on its own
**So that** Drive no longer keeps that phone's records file, and a silent phone is not retired for me

## Background

- The control is named "Forget this phone". It lives in Settings (US-59). It retires one other install's records file. It does not retire the install he is holding.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- The retire rules are the ones ADR-0002 already states. This story does not add a rule that record does not state.
- Out of scope: "Disconnect and delete Lagoon data from Drive" (US-78). That is a different control.
- Out of scope: changing the passphrase (US-86). Forgetting does not re-key and does not mint a key version.

## Acceptance criteria

- **Scenario: the unlock** — Must
  - Given he is on a phone that still holds the vault, and another install has a records file
  - When he chooses "Forget this phone"
  - Then the US-37 unlock is required first
  - And a cancelled or failed unlock changes nothing
  - Security criteria approved by mjbvilhena on 2026-10-22. "Forget this phone" requires the US-37 unlock first. A cancelled or failed unlock changes nothing.
- **Scenario: which phone** — Must
  - Given the US-37 unlock succeeded, and at least one other install has a records file
  - When the list is shown
  - Then he sees each other install, with its device name, its model, and its last check-in date
  - Security criteria approved by mjbvilhena on 2026-10-23. And an install whose records-file delete is pending shows as "will be forgotten at its next sync".
  - And this install is not listed
  - And when he chooses one, Lagoon asks him to confirm, naming that install
  - And cancelling changes nothing
  - Security criteria approved by mjbvilhena on 2026-10-23. A failed records-file delete is retried at each later sync of this phone until that records file is gone. Until then, that phone shows as pending ("will be forgotten at its next sync") in the Forget this phone list.
- **Scenario: no other phone** — Must
  - Given no other install has a records file
  - When the US-37 unlock has succeeded
  - Then it says there is no other phone to forget
  - And it deletes nothing
- **Scenario: one other install** — Must
  - Given the US-37 unlock succeeded
  - When he names one other install and "Forget this phone" runs
  - Then this phone uploads its own merged records file
  - Security criteria approved by mjbvilhena on 2026-10-23. If the upload of this phone's own merged records file before Forget fails, or the phone is offline, nothing is written or deleted, he is told, and he can retry.
  - And this phone writes that install's id into this phone's forgotten-installs file and reads that file back
  - And if that write or that read-back fails, nothing is deleted and he is told
  - And that install's records file is deleted from Drive
  - And that install's own forgotten-installs file, if it has one, is not deleted, and the ids in it stay forgotten
  - And the install he is holding is not retired
  - And the passphrase does not change
  - And no key version is minted
  - And US-55 stops counting that install's check-in
  - Security criteria approved by mjbvilhena on 2026-10-22. Forget this phone does not revoke a stolen phone. Revoking a stolen phone is a passphrase change (US-86) and Google sign-out.
  - Security criteria approved by mjbvilhena on 2026-10-22. During a pending forgotten-passphrase reset, Forget this phone requires the current passphrase, the same as US-78. Until the reset completes, the current passphrase is the old passphrase. The new passphrase counts only after the reset completes.
  - Amendment approved by mjbvilhena on 2026-10-22. Forget this phone first merges that install's latest records file. If the read fails, nothing is deleted and the user is told.
  - Amendment approved by mjbvilhena on 2026-10-22. Each phone that runs Forget this phone writes only its own forgotten-installs file, and no other phone writes that file, except a re-key, which rewrites every forgotten-installs file under the new key version and never deletes one. Forget this phone writes that other install's id into this phone's forgotten-installs file and reads that file back before it deletes the other install's records file. If that write or that read-back fails, nothing is deleted and he is told.
  - Security criteria approved by mjbvilhena on 2026-10-23. Before a phone forgets another, it uploads its own merged records file.
  - Security criteria approved by mjbvilhena on 2026-10-23. A re-key reads back each forgotten-installs file it rewrites and retries if an entry is missing. After a re-key, each phone re-reads its own forgotten-installs file and re-adds any missing id it wrote.
- **Scenario: Drive does not delete** — Must
  - Given he has confirmed one other install
  - And this phone has written that install's id into this phone's forgotten-installs file and read that file back
  - When the delete of that install's records file fails
  - Then that forgotten-installs entry stays
  - And he is told that phone will be forgotten at its next sync
  - Security criteria approved by mjbvilhena on 2026-10-23. A failed records-file delete is retried at each later sync of this phone until that records file is gone. Until then, that phone shows as pending ("will be forgotten at its next sync") in the Forget this phone list.
- **Scenario: the forgotten phone syncs before the retry** — Must
  - Security criteria approved by mjbvilhena on 2026-10-23. Given this phone has written another install's id into its forgotten-installs file and read it back, and the delete of that install's records file is still pending.
  - Security criteria approved by mjbvilhena on 2026-10-23. When that forgotten phone syncs before the retry has deleted that records file.
  - Security criteria approved by mjbvilhena on 2026-10-23. Then it is handled as in "the phone returns".
  - Security criteria approved by mjbvilhena on 2026-10-23. And its old records file is not used in the rebuild.
  - Security criteria approved by mjbvilhena on 2026-10-23. And US-55 does not count it when picking the newest check-in (US-55).
  - Security criteria approved by mjbvilhena on 2026-10-23. And this phone's retry still deletes that old records file, and never the new records file the rejoined phone uploads under its new install id.
  - Security criteria approved by mjbvilhena on 2026-10-23. If the forgotten phone syncs before the retry deletes its records file, it is handled as a returning forgotten phone. Its old records file is not used in the rebuild and is not counted when picking the newest check-in (US-55), and this phone's retry still deletes it.
- **Scenario: never automatic** — Must
  - Given an install has a records file
  - When that phone is old, silent, or misses a check-in
  - Then the file is not retired
  - And it is retired only when he chooses "Forget this phone" in Settings on a phone that still holds the vault
- **Scenario: attachments** — Must
  - Given that records file is deleted
  - When an attachment object is referenced by no remaining records file
  - Then it becomes eligible for the same collection as any other unreferenced attachment
  - And it is not deleted in that step while another file still points at it
- **Scenario: tombstones** — Must
  - Given the forgotten install has no records file
  - When tombstones are collected
  - Then they no longer wait for that install to merge a file
  - And collection still waits for every install that still has a file, and for the 90 days after the tombstone timestamp
- **Scenario: own records file missing and this install is not forgotten: local vault kept, phone uploads** — Must
  - Given this phone's records file is not on Drive
  - And every forgotten-installs file was read successfully and this install's id is in none of them, or a successful listing finds no forgotten-installs file
  - When the next sync runs
  - Then the local vault is kept
  - And this phone uploads its records file
  - Amendment approved by mjbvilhena on 2026-10-22. If every forgotten-installs file reads successfully and this install's id is in none of them, or a successful listing finds no forgotten-installs file, the phone keeps the local vault and uploads its records file. A missing records file alone, a US-78 Drive delete, or a never-uploaded phone never wipes the local vault.
- **Scenario: forgotten-installs read or listing failed: local vault kept, no upload** — Must
  - Given this phone's records file is not on Drive
  - And any forgotten-installs read failed, or the listing failed
  - When the next sync runs
  - Then the local vault is kept
  - And this phone does not upload
  - And it retries until a later sync reads it successfully
  - And a file under a newer key is not a failed read
  - Amendment approved by mjbvilhena on 2026-10-23. And the sync-paused notice shows after 3 consecutive syncs with a failed forgotten-installs read or listing, and clears at the first sync that reads every forgotten-installs file successfully.
  - Decided by mjbvilhena; this record is Proposed until he approves it. An offline sync, when Drive is not reachable, neither counts toward the 3 consecutive failed syncs for the sync-paused notice nor resets that run. Only a sync that reaches Drive and fails to read or list a forgotten-installs file counts.
  - Decided by mjbvilhena; this record is Proposed until he approves it. The sync-paused notice is named only. Its exact text waits for the UX artefact.
  - Amendment approved by mjbvilhena on 2026-10-22. If any forgotten-installs read fails, or the listing fails, the phone keeps the local vault, does not upload, and retries until a later sync reads it successfully.
- **Scenario: an offline sync does not count toward the sync-paused notice** — Must
  - Decided by mjbvilhena; this record is Proposed until he approves it. Given Drive is not reachable
  - Decided by mjbvilhena; this record is Proposed until he approves it. When a sync runs
  - Decided by mjbvilhena; this record is Proposed until he approves it. Then that offline sync does not count toward the 3 consecutive failed syncs for the sync-paused notice
  - Decided by mjbvilhena; this record is Proposed until he approves it. And it does not reset that run
  - Decided by mjbvilhena; this record is Proposed until he approves it. And only a sync that reaches Drive and fails to read or list a forgotten-installs file counts
- **Scenario: files are under a newer key** — Must
  - Security criteria approved by mjbvilhena on 2026-10-23. Given this phone holds an older key version than the files on Drive.
  - When the next sync runs
  - Then it asks for the new passphrase first and then reads the forgotten-installs files
  - And a newer-key file is not a read failure
  - And cancelling that prompt or entering a wrong passphrase discards nothing, the phone does not upload, and it asks again at the next sync
  - Security criteria approved by mjbvilhena on 2026-10-23. A forgotten phone that returns after a passphrase change, and finds the files under a newer key, asks for the new passphrase first and then reads the forgotten-installs files. Cancelling or entering a wrong passphrase discards nothing, the phone does not upload, and it asks again at the next sync. A newer-key file is not a read failure.
- **Scenario: the phone returns** — Must
  - Given every forgotten-installs file was read successfully and one of them contains this install's id
  - When the next sync finds that
  - Then he is asked for the passphrase before the local copies are discarded, because the vault is rebuilt from Drive and those copies will be lost
  - Security criteria approved by mjbvilhena on 2026-10-23. And if the passphrase was already accepted at this sync in the newer-key scenario ("files are under a newer key"), he is not asked again in 'the phone returns'.
  - And during a pending forgotten-passphrase reset that passphrase is the old passphrase until the reset completes, and the new passphrase counts only after the reset completes
  - And cancelling that prompt or entering a wrong passphrase discards nothing, the phone does not upload, and it asks again at the next sync
  - And when the passphrase is accepted, the phone discards its local copies and rebuilds from Drive
  - And items permanently deleted stay gone
  - And that phone's unsynced local edits are lost
  - And he sees a short notice that those unsynced local edits were lost only when something was actually lost
  - And after rebuilding, it rejoins with a new install id
  - And the old id stays in the forgotten-installs file of the phone that forgot it
  - And that forgotten-installs file stays on Drive and is still read, and this rebuild does not change it
  - And the old id is never removed from that file
  - And Forget this phone never deletes that file, and the ids in it stay forgotten
  - Amendment approved by mjbvilhena on 2026-10-23. Security criteria approved by mjbvilhena on 2026-10-23. And if this phone had itself forgotten other installs, its own forgotten-installs file under its old install id keeps the same ids. Forget and rebuild never change it. A re-key may rewrite it under the new key with the same ids.
  - And only a US-78 delete removes these files
  - And a rejoined phone that later runs Forget writes a new forgotten-installs file under its new install id
  - And it uploads a new records file under its new install id and the current key version
  - And attachments of items that are live or in trash are rebuilt from Drive
  - And after that rebuild, attachment files that belong to no item that is live or in trash are deleted on this phone only, never as a Drive object
  - Amendment approved by mjbvilhena on 2026-10-22. A returning phone reads every forgotten-installs file. It treats itself as forgotten only when every one of those files was read successfully and one of them contains its install id. If any read fails, or the listing fails, it keeps the local vault, does not upload, and retries at a later sync. The trigger is the next sync that finds the entry. After rebuilding, the phone rejoins with a new install id. It uploads a new records file under its new install id and the current key version, and the old id stays in the forgotten-installs file.
  - Amendment approved by mjbvilhena on 2026-10-22. A returning forgotten phone discards its local copies and rebuilds from Drive. Items permanently deleted stay gone, and that phone's unsynced local edits are lost. It shows a short notice saying so only when something was actually lost.
  - Security criteria approved by mjbvilhena on 2026-10-22. He is asked for the passphrase at that sync, before the local copies are discarded, because rebuilding from Drive needs the vault key. Cancelling that prompt or entering a wrong passphrase discards nothing, the phone does not upload, and it asks again at the next sync. Until a pending forgotten-passphrase reset completes, that passphrase is the old passphrase. The new passphrase counts only after the reset completes.
  - Security criteria approved by mjbvilhena on 2026-10-22. The returning phone's orphan-attachment cleanup deletes local copies only, never a Drive object.
  - Amendment approved by mjbvilhena on 2026-10-22. That cleanup runs after the rebuild from Drive. Attachments of items that are live or in trash are rebuilt from Drive.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The control is named "Forget this phone". No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-37 is the unlock. US-59 hosts the control. US-78 is the Drive delete, a different control, and it does not mark an install forgotten. US-86 is the passphrase change. The behavior is this story.
- ADR-0001, ADR-0002, and US-55. US-55 stops counting the forgotten install's check-in.
- A forgotten phone that returns rebuilds from Drive. Attachment files that belong to no item that is live or in trash are US-66. That cleanup is local only, and it runs after the rebuild.
- The forgotten-installs file rules are "one other install" and "the phone returns".

## Implementation notes

Not a design spec. The story is Proposed for story review. Non-security parts approved. Do not implement from this file.
