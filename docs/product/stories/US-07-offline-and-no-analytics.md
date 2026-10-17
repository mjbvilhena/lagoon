# US-07 Use the saved vault with no network and no analytics

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Scout's review verdict at commit `9e7de63`, under Miguel's delegation of 2026-10-17, was Approve. That verdict is not an approval. Status stays Draft until all five bots re-review.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded.
> Miguel (`mjbvilhena`) decided the ML Kit rule in chat on 2026-10-17 at 16:00 IST. His words: "Requirement will be to let the user choose to disable (if possible), otherwise will have to disclose and accept". That decision is not story approval.

**Epic:** E1. Not an approval.

**MoSCoW:** Must. v1, from approved epic E1 and the specification.

## Title

Use the saved vault with no network and no analytics.

## User story

**As a** person named Miguel
**I want** the vault I already saved to keep working with no network, and to send no analytics
**So that** I can read and search it offline, and the app does not report my use

## Background

- The phone is the primary copy. Analytics, usage telemetry, and a crash-reporting product are out of the specification. The app still does not add Firebase Analytics, Crashlytics, or another usage-analytics library.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: a page fetch while offline (US-09) and the topic call (US-44). Those wait. They do not block reading what is already stored.
- Miguel decided this on 2026-10-17 at 16:00 IST. Official Google ML Kit docs do not document a switch that disables usage or diagnostic telemetry, so the requirement is disclosure and acceptance, not a Settings toggle. The research note is `docs/product/research/2026-10-17-ml-kit-telemetry.md`. There is no toggle, so there is no default to take from the docs.
- The image and the recognized text stay on the device. The disclosure is about ML Kit's metrics, not about sending the image.

## Acceptance criteria

- **Scenario: airplane mode** — Must
  - Given items already exist, including a PDF whose text was extracted, a link with a stored snapshot, and a search match that occurs only in that PDF text
  - When the phone has no network
  - Then he can open the PDF text, open the snapshot, and see that search hit
- **Scenario: a fresh install with no network and no OAuth client ID** — Must
  - Given a new install, no network, and no OAuth client ID
  - When he opens the app
  - Then it does not crash, he can save and read local items, and Settings explains that sync is not configured (US-17)
- **Scenario: no analytics libraries** — Must
  - Given the release classpath and manifest
  - When CI inspects them
  - Then Firebase Analytics, Crashlytics, and any other usage-analytics dependency the app adds are absent. That check is not a switch for ML Kit's own metrics
- **Scenario: disclosure before the first OCR use** — Must
  - Given he has not accepted the ML Kit disclosure
  - When the app is about to run OCR for the first time (US-31)
  - Then OCR does not run, `MlKit.initialize` has not been called, and the app shows a disclosure
  - And the disclosure states that the image and the recognized text stay on the device and are not sent to Google
  - And it lists the metrics categories from the Android data-disclosure page: device information and ML hardware accelerators, package name and version, per-installation identifiers, performance metrics, API configuration, input and output size, feature version, event types, and error codes
  - And it states that those metrics go to Google over HTTPS and that ML Kit does not transfer them to third parties
  - And it states that ML Kit may contact Google for model updates and bug fixes
  - And it states that Google's docs do not give the app a switch to turn those metrics off
- **Scenario: he accepts** — Must
  - Given that disclosure is showing
  - When he accepts
  - Then the acceptance is stored, `MlKit.initialize` runs, OCR may run, and a later OCR use does not ask again
- **Scenario: he does not accept** — Must
  - Given that disclosure is showing
  - When he declines, or he leaves it without answering
  - Then OCR does not run, `MlKit.initialize` is not called, and the manifest has removed `com.google.mlkit.common.internal.MlKitInitProvider` with `tools:node="remove"`, so ML Kit does not start
  - And the next attempt to run OCR shows the disclosure again
- **Scenario: he withdraws acceptance** — Must
  - Given he has accepted
  - When he withdraws that acceptance in Settings (US-59)
  - Then the next OCR use shows the disclosure again and does not call `MlKit.initialize` until he accepts
- **Scenario: the same disclosure in Settings** — Must
  - Given he opens Settings (US-59)
  - When the screen is shown, whether or not he has accepted
  - Then he can read the same disclosure, he can see whether he has accepted it, and he can withdraw an acceptance. There is no control that disables the metrics inside the SDK

## Analytics and observability (optional)

The specification forbids an analytics product the app chooses. This story's Must is that absence, plus the ML Kit disclosure above. Do not add a usage event for the acceptance.

## Design and a11y notes

- Offline use has no extra screen. No placeholder sentences.
- Exact disclosure sentences wait for the UX artefact, which does not exist. The facts in the disclosure scenario are the ones that must be present.

## Dependencies and risks

- Search behavior is US-15 and US-50. This story only requires a hit that is already indexed.
- OCR behavior is US-31. This story gates the first run. It does not change English-only recognition.

## Implementation notes

Test approach: the analytics check is an automated CI rule on dependencies and the manifest, not a hand look. The disclosure scenarios are app behavior. Not a design spec.

Sources, read on 2026-10-17. Facts, not a guess that a hidden flag exists:

- [ML Kit Terms & Privacy](https://developers.google.com/ml-kit/terms), last modified May 14, 2025. Input processing is on device, and ML Kit does not send that input or the outputs to Google servers. The APIs also send performance and utilization metrics to Google. The developer informs users about that processing. No disable API is stated.
- [Android data disclosure](https://developers.google.com/ml-kit/android-data-disclosure), last modified May 14, 2025. The page covers configurations the developer can control. Those are feature options. Data collected in all features is for diagnostics and usage analytics. Text recognition is not given an opt-out there.
- [MlKit reference](https://developers.google.com/android/reference/com/google/mlkit/common/MlKit). Removing `com.google.mlkit.common.internal.MlKitInitProvider` only stops automatic startup initialization. This story uses that removal so `MlKit.initialize` runs only after acceptance. It is still not a documented telemetry off switch. Whether the SDK would send metrics before the first call is not stated in these pages.
- [Text recognition on Android](https://developers.google.com/ml-kit/vision/text-recognition/v2/android), last updated 2026-10-15 UTC. No metrics disable.
- [iOS data disclosure](https://developers.google.com/ml-kit/ios-data-disclosure) says the SDK collects diagnostics and usage analytics by default. Lagoon is Android. That page only corroborates the default. It is not an Android control.

Do not treat a Firebase Analytics or Crashlytics flag as an ML Kit control. Those dependencies stay absent.
