# US-20 Receive the vault on a new install

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Replaces Restore. His decision: a new install or a second device signs in and gets the vault. Sync merges. Q5 is resolved. Not approval.

## Title

Receive the vault after signing in on another install.

## User story

**As a** person named Miguel
**I want** a new install or a second device to pick up the vault after I sign in
**So that** I do not run a restore

## Background

- He said sync is seamless and a second device gets the vault automatically.
- Q5 is resolved: sync merges. An item that exists on only one side is kept.
- The same item edited on both sides, and how deletions propagate, are still open. This story does not invent those rules.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: a Restore button. Reading ciphertext before keys exist (US-36 and his key question).

## Acceptance criteria

- **Scenario: empty phone, existing Drive copy** — Must
  - Given he is signed in, Drive holds a vault, and this install has no items
  - When sync runs
  - Then the items, notes, tags, and attachments from that copy are on the device
- **Scenario: merge** — Must
  - Given this phone has an item the Drive copy does not, and the Drive copy has an item this phone does not
  - When sync finishes
  - Then both items exist on the phone and in the synced copy
- **Scenario: no remote vault** — Must
  - Given he is signed in and Drive has no vault yet
  - When sync runs
  - Then local items are not wiped
- **Scenario: not configured or not signed in** — Must
  - Given no client ID, or he is not signed in
  - When he uses the app
  - Then nothing is pulled from Drive and the app does not crash
- **Scenario: conflicting edits** — Must
  - Given the same item was changed on two devices
  - When they sync
  - Then the app does not pick a winner this story invents. Behavior waits on his open question.
- **Scenario: deletions** — Must
  - Given he deleted an item on one device only
  - When the other device syncs
  - Then this story does not specify that the other copy disappears. That rule waits on his open question.

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- There is no Restore control. Sign-in is the way onto a new install.
- If the copy cannot be read yet because keys are unanswered, the screen says so in real words rather than showing a fake empty vault. Wording waits for the UX artefact.

## Dependencies and risks

- Blocked by US-18 having synced a vault, and by his answers on conflicts, deletions, and keys before those paths can be called done.
- Do not soft-pass the open cases by dropping them.

## Implementation notes

Read and write `appDataFolder` only. Do not implement a silent replace. Not a design spec.
