# US-51 Sync in the background and show errors

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Split from US-18 after the review at `9e7de63`. This draft was not in that review.
> Story approval has not been recorded.

**MoSCoW:** Must. v1, from approved epic E4.

## Title

Sync in the background and show errors.

## User story

**As a** person named Miguel
**I want** sync to run in the background and to tell me when it fails
**So that** I do not have to press a backup button and I can see a real failure

## Background

- The specification names WorkManager for background sync. The trigger is not "while the app is running".
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: the cipher (an ADR), key transport (US-74), conflict rules (US-73), and sign-out. Sign-out is US-78.

## Acceptance criteria

- **Scenario: the worker runs** — Must
  - Given he is signed in and the unmetered preference allows the network (US-19)
  - When an item changes
  - Then one unique WorkManager job for the vault is queued, debounced after that change, and a periodic run also exists
  - And the job still runs when the app is not in the foreground
- **Scenario: a long upload** — Must
  - Given the upload is long and the device is API 34 or newer
  - When the worker runs
  - Then it uses a `dataSync` foreground service, within the Android 15 time limit, or it uses resumable chunked uploads
- **Scenario: a download** — Must
  - Given another install is receiving the vault
  - When bytes come down
  - Then that path is US-20, not a second download design in this story
- **Scenario: revoked auth** — Must
  - Given Drive rejects the grant
  - When the worker finishes
  - Then Settings says, in plain words, that sign-in was revoked, and local items remain
- **Scenario: Drive is full or the upload fails** — Must
  - Given Drive is full, or the upload fails for another reason
  - When the worker finishes
  - Then Settings says which of those happened, in plain words, and the local item remains

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Error sentences do not use raw HTTP codes as the only text. No placeholder sentences.

## Dependencies and risks

- The payload is ciphertext only after US-36. Tests may use a stand-in that still satisfies the US-25 sentinel rule.
- There is no Back up now button (US-17, US-30).

## Implementation notes

Not a design spec.
