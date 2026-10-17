# US-78 Keep Drive to app data, and sign out without deleting the phone

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> The approved specification still names `appDataFolder` until he approves an amendment. That specification is not edited here.

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
- **Scenario: sign out** — Must
  - Given he is signed in
  - When he chooses Sign out
  - Then tokens are cleared, sync jobs are cancelled, the local vault stays, and the Drive copy stays
- **Scenario: delete the Drive copy** — Must
  - Given he chooses "Disconnect and delete Lagoon data from Drive" and confirms, after unlock
  - When that action finishes
  - Then `revokeAccess()` has been called, the Drive copy is the target, and the local vault is not deleted
- **Scenario: another account** — Must
  - Given he signs in with a different Google account
  - When sync runs
  - Then that account has its own Drive copy and does not read the previous account's copy
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
