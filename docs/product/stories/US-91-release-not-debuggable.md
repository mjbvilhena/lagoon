# US-91 Keep the release manifest non-debuggable

> **Approval status: Approved.**
> Security criteria approved by mjbvilhena on 2026-11-06.
> Mikey's Dev Bot, approved under Miguel's delegation of 2026-11-06.
> Scout, approved under Miguel's delegation of 2026-11-06.
> Mikey's Household Assistant, approved under Miguel's delegation of 2026-11-06.
> Mikey's Leisure Assistant, approved under Miguel's delegation of 2026-11-06.
> Mikey's Assistant, approved under Miguel's delegation of 2026-11-06.
> This is an artefact approval. Implementation is not Done. It covers no ruleset change and no vulnerability exception.

**Epic:** E6.

**MoSCoW:** Must.

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
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given the release APK built for the US-80 manifest test
  - When its merged manifest is read
  - Then `android:debuggable` is absent or false
  - And a true value fails that test
- **Scenario: signing stays US-81** — Must
  - Given this story
  - When the release APK is inspected
  - Then no certificate check and no `apksigner` check is added
  - And the assertion message in `releaseManifestMatchesTheAllowlist` states that the file is unsigned and is not the US-81 sideload release

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.
- The statement goes in the assertion message of `releaseManifestMatchesTheAllowlist`. It does not replace that test's allowlist or exported-component assertions.

## Dependencies and risks

- Security criteria approved by mjbvilhena on 2026-11-06. The story is approved as an artefact. Implementation is not Done. The assertion message that the file is unsigned must change when US-81 adds signing.
- The release APK already built in the unit-test task is the file this reads. No new required check name.

## Implementation notes

Not a design spec. Do not change the test in this documentation change.
