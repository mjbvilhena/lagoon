# US-80 Keep the permission set to an allowlist

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Keep the permission set to an allowlist.

## User story

**As a** person named Miguel
**I want** the release app to request only the permissions the product needs
**So that** an install does not ask for storage, camera, or location

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated the permission set to his bots. He did not decide it.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77). They stay out of the allowlist while that question is open.

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: the allowlist** — Must
  - Given the release manifest
  - When its permissions are listed
  - Then the only ones present are `INTERNET`, `ACCESS_NETWORK_STATE`, `POST_NOTIFICATIONS` on API 33 and newer, `RECEIVE_BOOT_COMPLETED`, `USE_BIOMETRIC`, and `WAKE_LOCK`
  - And `POST_NOTIFICATIONS` is requested when the first reminder is set (US-34), not at first launch
- **Scenario: a long sync** — Must
  - Given US-51 needs a foreground service for a long upload
  - When that path is in the build
  - Then `FOREGROUND_SERVICE` and `FOREGROUND_SERVICE_DATA_SYNC` may be added, and they are absent when US-51 does not need them
- **Scenario: absent permissions** — Must
  - Given the release manifest
  - When it is inspected
  - Then these are absent: storage permissions, `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO`, `MANAGE_EXTERNAL_STORAGE`, `CAMERA`, exact-alarm permissions, `QUERY_ALL_PACKAGES`, `GET_ACCOUNTS`, location permissions, and contacts permissions
- **Scenario: exported components** — Must
  - Given the release manifest
  - When exported components are listed
  - Then only the share target and the launcher activity are exported
- **Scenario: CI** — Must
  - Given a release APK
  - When CI reads its permissions
  - Then the set matches this allowlist

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.

## Dependencies and risks

- US-77 stays open. Adding exact alarms would change this allowlist and needs Miguel's decision first.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
