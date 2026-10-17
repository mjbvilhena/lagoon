# US-18 Sign in so the vault can sync

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> The file was renamed from `US-18-back-up-now.md`. There is no Back up now button.

**Epic:** E4. Not an approval.

**MoSCoW:** Must. v1, from approved epic E4. The worker and errors are US-51. Sign-out is the proposed US-78. A round-2 verdict on this story is conditional on US-78. That condition is not an approval.

## Title

Sign in so the vault can sync.

## User story

**As a** person named Miguel
**I want** to sign in with Google and grant the app-data scope
**So that** background sync is allowed to use my private Drive app space

## Background

- Sign-in uses Credential Manager and the Identity AuthorizationClient. This story ends when the grant exists. It does not upload.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a Back up now button, the WorkManager worker (US-51), ciphertext rules (US-36), and key transport (US-74). Drive scope and sign-out are US-78. The approved specification names `appDataFolder`.

## Acceptance criteria

- **Scenario: sign in** — Must
  - Given a build with an OAuth client ID and he is not signed in
  - When he completes the Google sign-in and grants `drive.appdata`
  - Then Settings shows that he is signed in, and no Back up now button was required
- **Scenario: not signed in** — Must
  - Given he has not signed in
  - When the app is open
  - Then nothing is uploaded
- **Scenario: he cancels or sign-in fails** — Must
  - Given he cancels the sheet or the grant fails
  - When control returns to the app
  - Then he sees plain words that sync is not signed in, and local items remain
- **Scenario: the scope is app data** — Must
  - Given he is signed in
  - When the granted scope is inspected
  - Then it is the Drive app-data scope, and the copy is hidden in the ordinary Drive UI

## Analytics and observability (optional)

Not applicable. No analytics. Sign-in is authentication, not product analytics.

## Design and a11y notes

- Failure text is specific. No placeholder sentences.

## Dependencies and risks

- Uploads of real ciphertext wait on US-35 and US-36. This story can be tested with the grant alone.
- OAuth client setup is US-26.

## Implementation notes

Do not log tokens. Do not commit the client ID. Not a design spec.
