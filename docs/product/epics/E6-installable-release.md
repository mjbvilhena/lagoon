# Epic E6: Installable release

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> Does not approve child stories. Shipped-epic DoD is not claimed.
> This epic is the delivery bar he set for v1. It is not a reason to write the app before the spec, epic, story, and technical-design gates are actually approved.

## Background & Value Proposition

Miguel needs a file he can put on his phone without the Play Store, plus enough written steps to build it, allow unknown-source installs, and create the OAuth client that backup needs. He also asked for tests on search, backup packaging, and a basic Compose screen, and for screenshots of the main screens.

No separate success metric was given.

## Target Audience

Miguel, installing and configuring the vault himself.

## Scope & Phasing

- **In Scope (MVP):**
  - Command-line Gradle build of a release APK.
  - `minSdk` 26. `compileSdk` and `targetSdk` are 37 (Android 17, stable since June 2026). Bots' consensus on Q8. Not his approval of this spec.
  - Application id `com.mjbvilhena.folio` (Q2, delegated recommendation).
  - Signed with a self-generated release keystore. The keystore is documented. Secrets are not committed.
  - README: build, sideload (unknown sources), Google Cloud OAuth client steps (package name, SHA-1 of the signing cert he was given), and where the client ID goes.
  - Unit tests for the repository/search layer and the backup-packaging layer. At least one Compose UI test.
  - Screenshots: home, search results, item detail, share-to-save, settings/backup. From an emulator when one is available. If none is available, say so. Do not fabricate images.
- **Future Scope (v2+):** Nothing scheduled.
- **Out of Scope / Non-Goals:** Play Store upload. A committed keystore or client ID. CI jobs for Kotlin that the repository-setup mapping does not list, until a later approved change adds them.

## Core Workflows

1. **Install the APK**
   - He builds or receives the signed release APK.
   - He allows installs from unknown sources and installs it.
   - The app opens without a Play Store account and without an OAuth client ID.
2. **Turn backup on later**
   - He follows the README, creates an OAuth client for the package name and SHA-1, puts the client ID in the build-time config, and rebuilds.
   - Until he does that, E4's unconfigured state is what he sees.

## Risks & Dependencies

- Depends on E1–E5 for there to be screens and behavior worth testing and photographing.
- The README can name Folio and `com.mjbvilhena.folio` as the delegated recommendations. The signing SHA-1 still does not exist and must not be invented. These recommendations are not Miguel's approval.
- Screenshot capture needs an emulator or a device. Availability is unknown at drafting time.
- The signing SHA-1 cannot be known until the keystore exists. It is an implementation output, documented in the README, not invented in this epic.

## Open Questions

- Q1, Q2, and Q8 are the bots' consensus. They are not approval.
- [ ] Android developer verification for sideloaded APKs, enforced globally in 2027. ADB installs are exempt. Flagged for Miguel. (@mjbvilhena)
- Q5 and Q10 are his decisions, recorded in the specification. They are not open here.

## Draft stories

- US-24 Install a signed release APK
- US-25 Run the tests he named
- US-26 Follow a README to build, sideload, and configure OAuth
- US-27 Review screenshots of the main screens
