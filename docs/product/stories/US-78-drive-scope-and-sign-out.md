# US-78 Keep Drive to app data, and sign out without deleting the phone

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> The approved specification names `appDataFolder`. These criteria do not replace that.

**Epic:** E4. Not an approval.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Keep Drive to app data, and sign out without deleting the phone.

## User story

**As a** person named Miguel
**I want** sync to use only the app-data scope, and sign-out to leave my phone's vault in place
**So that** Drive access stays narrow and a sign-out is not a wipe

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated Drive scope and sign-out to his bots. He did not decide them.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: sync conflicts and deletion propagation (US-73). Key transport (US-74).

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: the scope** — Must
  - Given he grants Drive access
  - When the granted scopes are read
  - Then they are exactly `drive.appdata`
- **Scenario: the consent screen** — Must
  - Given the OAuth client from US-26
  - When the consent screen is configured
  - Then it is set to "In production"
  - And the docs state the US-26 fact that an external app left in Testing, with a non-basic scope, gets a refresh token that expires in 7 days
- **Scenario: sign out** — Must
  - Given he is signed in
  - When he chooses Sign out
  - Then the US-55 release marker is written, and after that `clearCredentialState` runs, the Drive grant is revoked, sync jobs are cancelled, the local vault stays, and the Drive copy stays
  - And there is no third control named "disconnect but keep the Drive copy", because Sign out already does that. Deleting the Drive copy is the separate action below
- **Scenario: delete the Drive copy** — Must
  - Given he chooses "Disconnect and delete Lagoon data from Drive" and confirms, after a US-37 prompt, including when app lock is off
  - When the app-data files are deleted and a following read shows they are gone
  - Then the US-55 release marker is written, and after that `revokeAccess()` runs, he can see that the Drive copy no longer exists, and the local vault is not deleted
  - And the next sign-in asks for consent again
- **Scenario: sign-out while offline** — Must
  - Given the phone has no network
  - When he chooses Sign out or the Drive delete
  - Then local tokens and sync jobs are cleared, the revoke is retried when a network returns, and he is told that the revoke is pending
- **Scenario: deletion fails** — Must
  - Given that delete is in progress
  - When the app-data files are still present
  - Then `revokeAccess()` is not called, and he is told the Drive copy is still there
- **Scenario: another account** — Must
  - Given he signs in with a different Google account
  - When sync would upload this vault
  - Then he is asked before that upload
  - And if he agrees, that account has its own Drive copy and does not read the previous account's copy
- **Scenario: tokens stay out of logs** — Must
  - Given a token exists
  - When logs and WorkManager inputs are inspected
  - Then the token is in neither

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Sign out and the Drive-delete action are separate named controls. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-18's round-2 verdict is conditional on this proposal. That condition is not an approval of either story.
- US-59 only hosts the sign-out slot.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
