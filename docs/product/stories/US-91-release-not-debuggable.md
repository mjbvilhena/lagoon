# US-91 Keep the release manifest non-debuggable

> **Approval status: Draft. Not approved.**
> Security criteria: Proposed. Needs approval by mjbvilhena.

**Epic:** E6.

**MoSCoW:** Must. Proposed for v1. Not approved.

## Title

Keep the release manifest non-debuggable.

## User story

**As a** person named Miguel
**I want** the release APK to be refused when it is debuggable
**So that** a debug build is not mistaken for the release file

## Background

- US-80 already checks the release manifest allowlist, the absent permissions, backup flags, and which components are exported. Those tests stay the US-80 tests. This story does not add a second copy.
- US-81 owns signature verification with `apksigner`, the certificate SHA-256, and the APK SHA-256. That stays deferred until the approved US-81 policy files exist. This story does not invent a signing scheme.
- A release build today is unsigned. It is not a store release.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. This story does not add a vault rule.
- Out of scope: the permission allowlist, exported-component rules, `allowBackup`, log stripping, and the debug application id. Those stay US-80 and US-81.

## Acceptance criteria

- **Scenario: release is not debuggable** — Must
  - Security criteria: Proposed. Needs approval by mjbvilhena.
  - Given the release APK built for the US-80 manifest test
  - When its merged manifest is read
  - Then `android:debuggable` is absent or false
  - And a true value fails that test
- **Scenario: signing stays US-81** — Must
  - Given this story
  - When the release APK is inspected
  - Then no certificate check and no `apksigner` check is added
  - And an unsigned release APK is still not described as the sideload release

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.
- The assertion is added beside the existing release-manifest test. It does not replace that test's allowlist or exported-component assertions.

## Dependencies and risks

- Blocked on approval of this story and of the security criteria above by mjbvilhena.
- The release APK already built in the unit-test task is the file this reads. No new required check name.

## Implementation notes

Not a design spec. Do not change the test from this draft.
