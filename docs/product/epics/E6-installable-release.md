# Epic E6: Installable release

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.

## Background & Value Proposition

Miguel needs a file he can put on his phone without the Play Store, plus enough written steps to build it, allow unknown-source installs, and create the OAuth client that sync needs. He also asked for tests on search, sync and zip packaging, and a basic Compose screen, and for screenshots of the main screens.

No separate success metric was given.

## Target Audience

Miguel, installing and configuring the vault himself.

## Scope & Phasing

- **In Scope (MVP):**
  - Command-line Gradle build of a release APK.
  - `minSdk` 26. `compileSdk` and `targetSdk` are 37 (Android 17, stable since June 2026). US-80 is approved. The other stories in this epic stay Draft.
  - Application id `com.mjbvilhena.lagoon`.
  - Signed with a self-generated release keystore. The keystore is documented. Secrets are not committed.
  - README: build, sideload (unknown sources), Google Cloud OAuth client steps (package name, SHA-1 of the signing cert he was given), and where the client ID goes.
  - Unit tests for the repository/search layer and for sync-payload and zip packaging. At least one Compose UI test.
  - Screenshots: home, search results, item detail, share-to-save, and settings showing sync state. From an emulator when one is available. If none is available, say so. Do not fabricate images.
  - The release app requests only the permissions the product needs (A-E6-1, US-80). Acceptance: an install does not ask for storage, camera, or location. `CAMERA` stays off this allowlist, because US-65 uses the system camera.
  - A later install signed with the same release key can replace the app without losing the vault (A-E6-2, US-81). Acceptance: the policy is the criteria on US-81. Android developer verification for sideloaded APKs stays open.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Play Store upload. A committed keystore or client ID. CI jobs for Kotlin that the repository-setup mapping does not list, until a later approved change adds them.

## Core Workflows

1. **Install the APK**
   - He builds or receives the signed release APK.
   - He allows installs from unknown sources and installs it.
   - The app opens without a Play Store account and without an OAuth client ID.
2. **Turn sync on later**
   - He follows the README, creates an OAuth client for the package name and SHA-1, puts the client ID in the build-time config, and rebuilds.
   - Until he does that, E4's unconfigured sync state is what he sees. There is no Back up now and no Restore.

## Risks & Dependencies

- Depends on E1–E5 for there to be screens and behavior worth testing and photographing.
- The README can name Lagoon and `com.mjbvilhena.lagoon`. US-80 is approved. The other stories in this epic stay Draft. The signing SHA-1 still does not exist and must not be invented.
- Screenshot capture needs an emulator or a device. Availability is unknown at drafting time.
- The signing SHA-1 cannot be known until the keystore exists. It is an implementation output, documented in the README, not invented in this epic.

## Open Questions

- The name, the application id, and the SDK levels are in the specification. US-80 is approved. The other stories in this epic stay Draft.
- [ ] Android developer verification for sideloaded APKs, enforced globally in 2027. ADB installs are exempt.
- Sync merge and encryption are not open here.

## Draft stories

- US-24 Install a signed release APK
- US-25 Run the tests he named
- US-26 Follow a README to build, sideload, and configure OAuth
- US-27 Review screenshots of the main screens
- US-80 Keep the permission set to an allowlist. Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
- US-81 Publish a sideload signing policy.
