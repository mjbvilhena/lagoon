# US-80 Keep the permission set to an allowlist

> **Approval status: Draft. Not approved.**

**Epic:** E6.

**MoSCoW:** Must.

## Title

Keep the permission set to an allowlist.

## User story

**As a** person named Miguel
**I want** the release app to request only the permissions the product needs
**So that** an install does not ask for storage, camera, or location

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: exact alarms (US-77). They stay out of the allowlist. Camera capture uses the system camera, so `CAMERA` stays absent.

## Acceptance criteria

- **Scenario: the allowlist** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given the merged release manifest
  - When its permissions are listed
  - Then the app-declared ones are `INTERNET`, `ACCESS_NETWORK_STATE`, `POST_NOTIFICATIONS`, `RECEIVE_BOOT_COMPLETED`, `USE_BIOMETRIC`, and `WAKE_LOCK`
  - And `POST_NOTIFICATIONS` is always declared. The runtime request happens on API 33 or newer, when the first reminder is set (US-34), not at first launch
  - And `USE_FINGERPRINT` from `androidx.biometric` stays, with `maxSdkVersion` 28, because US-37 needs it on API 26 and 27
  - And these library entries are either named on this allowlist or removed with `tools:node="remove"`: `FOREGROUND_SERVICE` and the diagnostics receiver from WorkManager, and any permission a merged ML Kit or Play services manifest adds
- **Scenario: a long sync** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given US-51 needs a foreground service for a long upload
  - When that path is in the build
  - Then `FOREGROUND_SERVICE` and `FOREGROUND_SERVICE_DATA_SYNC` may be added, and they are absent when US-51 does not need them
- **Scenario: absent permissions** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given the merged release manifest
  - When it is inspected
  - Then these are absent: storage permissions, `READ_MEDIA_IMAGES`, `READ_MEDIA_VIDEO`, `READ_MEDIA_AUDIO`, `MANAGE_EXTERNAL_STORAGE`, `CAMERA`, exact-alarm permissions, `QUERY_ALL_PACKAGES`, `GET_ACCOUNTS`, `AD_ID`, `ACCESS_ADSERVICES_AD_ID`, `ACCESS_ADSERVICES_ATTRIBUTION`, `ACCESS_ADSERVICES_TOPICS`, any other `ACCESS_ADSERVICES_` permission, location permissions, and contacts permissions
- **Scenario: exported components** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given the merged release manifest
  - When exported components are listed
  - Then the share target and the launcher activity are exported
  - And an app-defined signature permission such as `<applicationId>.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION` may be present
  - And an exported component protected by a system or signature permission may be present, including `SystemJobService` with `BIND_JOB_SERVICE` and the profileinstaller receiver
  - And no other component is exported
- **Scenario: CI** — Must. Proposed security criterion. Needs approval by mjbvilhena.
  - Given a release APK
  - When CI reads the full merged manifest, including `<applicationId>.DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`
  - Then the set matches this allowlist

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.

## Dependencies and risks

- Exact-alarm permissions stay absent. `CAMERA` stays absent.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
