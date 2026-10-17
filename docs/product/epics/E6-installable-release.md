# Epic E6: Installable release

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> This epic is the delivery bar he set for v1. It is not a reason to write the app before the story and technical-design gates are actually approved.
> Approved amendment, 2026-10-17 at 20:02 IST. Approver: `mjbvilhena`. His words: "Yes, I approve all 25 proposed epic amendments as written". A-E6-1 and A-E6-2 are folded into the scope below from the text at commit `2fe0422`. The criteria on US-80 and US-81 stay proposed. This does not approve those stories.
> Clash fix, 2026-10-17 at 20:03 IST. Approver: `mjbvilhena`. C-E1-2 is resolved. Camera capture uses the system camera, so this allowlist still does not request `CAMERA`. The US-80 allowlist is unchanged.

## Background & Value Proposition

Miguel needs a file he can put on his phone without the Play Store, plus enough written steps to build it, allow unknown-source installs, and create the OAuth client that sync needs. He also asked for tests on search, sync and zip packaging, and a basic Compose screen, and for screenshots of the main screens.

No separate success metric was given.

## Target Audience

Miguel, installing and configuring the vault himself.

## Scope & Phasing

- **In Scope (MVP):**
  - Command-line Gradle build of a release APK.
  - `minSdk` 26. `compileSdk` and `targetSdk` are 37 (Android 17, stable since June 2026). That SDK choice is part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is Approved. Its stories stay Draft.
  - Application id `com.mjbvilhena.lagoon`. That id is part of the approved specification. This epic is Approved. Its stories stay Draft.
  - Signed with a self-generated release keystore. The keystore is documented. Secrets are not committed.
  - README: build, sideload (unknown sources), Google Cloud OAuth client steps (package name, SHA-1 of the signing cert he was given), and where the client ID goes.
  - Unit tests for the repository/search layer and for sync-payload and zip packaging. At least one Compose UI test.
  - Screenshots: home, search results, item detail, share-to-save, and settings showing sync state. From an emulator when one is available. If none is available, say so. Do not fabricate images.
  - The release app requests only the permissions the product needs (A-E6-1, US-80). Acceptance: an install does not ask for storage, camera, or location, while those criteria stay proposed. The 20:03 IST fix of C-E1-2 leaves `CAMERA` off this allowlist, because US-65 uses the system camera.
  - A later install signed with the same release key can replace the app without losing the vault (A-E6-2, US-81). Acceptance: the policy text stays proposed. Android developer verification for sideloaded APKs stays the open question already in this epic.
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
- The README can name Lagoon and `com.mjbvilhena.lagoon`. Both are part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is Approved. Its stories stay Draft. The signing SHA-1 still does not exist and must not be invented.
- Screenshot capture needs an emulator or a device. Availability is unknown at drafting time.
- The signing SHA-1 cannot be known until the keystore exists. It is an implementation output, documented in the README, not invented in this epic.

## Open Questions

- Q1, Q2, and Q8 are part of the specification approved by `mjbvilhena` on 2026-10-17. This epic is Approved. Its stories stay Draft.
- [ ] Android developer verification for sideloaded APKs, enforced globally in 2027. ADB installs are exempt. Flagged for Miguel. (@mjbvilhena)
- Q5 and Q10 are his decisions, recorded in the specification. They are not open here.

## Draft stories

- US-24 Install a signed release APK
- US-25 Run the tests he named
- US-26 Follow a README to build, sideload, and configure OAuth
- US-27 Review screenshots of the main screens
- US-80 Keep the permission set to an allowlist. Proposed. Not story approval.
- US-81 Publish a sideload signing policy. Proposed. Not story approval.

## Amendments applied 2026-10-17 at 20:02 IST

A-E6-1 and A-E6-2 were approved as written at `2fe0422` and folded into Scope above. C-E1-2 is resolved: the allowlist still does not request `CAMERA`. US-80 and US-81 criteria stay proposed. Stories stay Draft.
