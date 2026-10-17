# Research report: ML Kit usage and diagnostic telemetry

Input for a later ADR. This file is not an ADR. US-07 stays Draft.

## Metadata

| Field | Value |
| --- | --- |
| Topic | Whether an Android app can disable ML Kit usage and diagnostic telemetry |
| Domains | `get_domain_consultant("capture")` returned not found. No `DOMAIN.md`. No MUST/NEVER rules were invented. |
| Layers | `get_layer_consultant("ui")` returned not found. No `LAYER.md`. |
| Primary Objective | If the official docs allow a disable, a Settings toggle. They do not, so the requirement is disclosure and acceptance before the first OCR use |

## Executive Summary

Official Google ML Kit documentation does not give an app a switch that turns off usage or diagnostic telemetry. The terms say the APIs send performance and utilization metrics to Google, and that the app developer must inform users about that processing. The Android data-disclosure page says it lists the configurations a developer can control, and the controls it lists are feature options, not a telemetry off switch. Text recognition is not given a separate opt-out. Because a disable control is not in those docs, US-07 requires disclosure and acceptance before the first OCR use, with the same disclosure readable in Settings (US-59). There is no toggle, so there is no default to copy from the docs. Two further constraints come from the same pages: use the bundled text-recognition library, and do not start ML Kit until he accepts. Pre-first-call metrics are not documented.

## Context and constraints

- If the docs allow a disable, the app offers one. They do not, so the requirement is disclosure and acceptance.
- US-31 requires on-device English OCR. The vision and E7 keep that recognition. This note does not remove OCR.
- The specification forbids an analytics product the app chooses, including Firebase Analytics and Crashlytics. That rule stays. It is a different product from ML Kit's own metrics.
- The app is a signed sideload APK in v1, not a Play Store product. This note does not fill in a Play Data safety form.
- Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This was not a live MCP stdio session.

## Options

### Option 1: a Settings toggle that disables the telemetry

- **Description:** Add a control on US-59 that turns ML Kit usage and diagnostic metrics off, and record the default from Google's docs.
- **Key characteristics:** This is the branch if a disable is possible. A real toggle needs a documented developer API or configuration.
- **Pros:**
  - Would be the fit if a disable API existed.
  - Would let him refuse the metrics and still keep a written choice.
- **Cons:**
  - The ML Kit terms (last modified May 14, 2025) describe the metrics as something the APIs send. They do not describe a way to stop them.
  - The Android data-disclosure page (last modified May 14, 2025) says it covers configurations or invocations the developer can control. The controls on that page are feature options, such as custom models and barcode auto zoom. Text recognition is not given an opt-out. Data collected for every feature includes device information, application information, identifiers, performance metrics, API configuration, feature input and output size, feature version, event type, and error codes, each marked for diagnostics and usage analytics.
  - `MlKit.initialize`, and removing the provider `com.google.mlkit.common.internal.MlKitInitProvider`, only changes automatic startup initialization. The reference presents that as init timing, not as a telemetry switch.
  - The Android text-recognition guide (last updated 2026-10-15 UTC) documents recognizer options and image input. It does not document a logging or metrics flag.
  - The iOS data-disclosure page says "By default, the ML Kit SDK collects" the same kind of diagnostics and usage analytics. Lagoon is Android. That page is corroboration that collection is the default, not an Android API.
- **Suitability:** Not suitable. The docs that were read do not provide the control, so a toggle would be an invented API. No default is stated because no switch is stated.
- **Security, performance, and operations:** Shipping a toggle that does not call a real SDK control would tell him the metrics are off when the docs say the SDK still sends them.

### Option 2: disclose the metrics and require acceptance before the first OCR use

- **Description:** Before the first OCR run, the app shows that the image and the recognized text stay on the device, and that ML Kit still sends usage and diagnostic metrics to Google. OCR does not run until he accepts. Settings shows the same disclosure and whether he has accepted it. Declining, or leaving the disclosure unanswered, leaves OCR unrun. A later OCR use does not ask again after an acceptance.
- **Key characteristics:** This is the requirement when a disable is not possible. Acceptance is an app gate on OCR. It is not a documented SDK off switch.
- **Pros:**
  - Matches the requirement when a disable is not documented.
  - Matches the terms, which assign the developer the job of informing users about Google's processing of ML Kit metrics.
  - Keeps English on-device OCR, which E7 and US-31 already require.
  - The disclosure can be tested without inventing an API.
- **Cons:**
  - He cannot turn the metrics off and still use OCR. The terms do not offer that choice.
  - Declining does not, on the evidence of these docs, guarantee that the SDK sends nothing if it initializes on its own. The acceptance gate stops the app from running OCR. It does not claim a network block the docs do not provide.
  - Exact sentences for the screen wait for a UX artefact. The facts that must appear are in US-07.
- **Suitability:** This is the fit when option 1 is unavailable.
- **Security:** Input images, video, and text are processed on device, and the terms say ML Kit does not send that input or the outputs to Google servers. Metrics still leave the device: performance and utilization, plus the all-features set on the Android disclosure page, over HTTPS, not transferred to third parties by ML Kit. The disclosure has to say both facts. Logs must not gain a new analytics pipeline.
- **Performance:** OCR stays the on-device job in US-31. This option does not add a second recognition service.
- **Operations:** Store the acceptance on the device so a later OCR use can proceed, and show the same text in Settings. Do not treat Firebase Analytics `setAnalyticsCollectionEnabled`, or any Crashlytics flag, as this control. Those products stay absent under the existing CI rule.

### Option 3: drop ML Kit so there is no ML Kit telemetry

- **Description:** Remove the OCR dependency. No ML Kit metrics, because there is no ML Kit call.
- **Key characteristics:** This avoids the SDK. It also removes the v1 OCR outcome.
- **Pros:**
  - No ML Kit metrics to disclose.
- **Cons:**
  - Contradicts approved E7 and US-31, which require on-device English recognition.
  - Dropping OCR is not the requirement. The requirement is a disable if the docs allow one, and otherwise disclosure and acceptance.
- **Suitability:** Not suitable for this decision.
- **Security, performance, and operations:** Search would lose recognized text from images and scanned pages. That is a product cut, not a telemetry setting.

## Trade-off analysis

| Constraint | Option 1, toggle | Option 2, disclose and accept | Option 3, drop ML Kit |
| --- | --- | --- | --- |
| Official disable API | Not documented | Not required | Not required |
| Requirement | A toggle, only if a disable exists | Required once option 1 is not possible | Drops OCR, which stays in v1 |
| Approved on-device OCR | Kept, but the toggle would be fiction | Kept | Removed |
| What he can refuse | Metrics, if a real API existed | The OCR run, until he accepts | OCR itself |
| Default to record | None in the docs | Not applicable. There is no toggle | Not applicable |

## Recommendation

- **Chosen option:** Option 2.
- **Rationale:** The terms, the Android data-disclosure page, the `MlKit` initialization reference, and the text-recognition guide do not document a developer switch that disables usage or diagnostic telemetry. The requirement is then disclosure and acceptance. Option 3 would cancel on-device OCR, which stays in v1.
- **Next steps:** The acceptance criteria are on US-07 and US-59. Both stories stay Draft. Do not implement. Do not fill a Play Data safety form from this note. Do not draft an ADR from this note.

## Bundled model and init timing

Same sources. Two facts that change how option 2 is built, not the choice of option.

- **Bundled model.** The Android data-disclosure page separates thin features from bundled features. Thin features collect a device identifier for diagnostics and usage analytics. Bundled features collect per-installation identifiers that are not intended to uniquely identify a user or a physical device. Text recognition has a Play services artifact, `com.google.android.gms:play-services-mlkit-text-recognition`, and a bundled artifact, `com.google.mlkit:text-recognition`. US-31 requires the bundled artifact and requires the Play services artifact to be absent.
- **When the SDK starts.** Those pages do not say whether metrics are sent before the first API call. That gap is undocumented, not a hidden off switch. US-07 therefore removes `com.google.mlkit.common.internal.MlKitInitProvider` with `tools:node="remove"` and calls `MlKit.initialize` only after he accepts. A decline means the app never calls `initialize`, so ML Kit does not start. Withdrawing the acceptance in Settings returns to that state. This is still init timing. It is not a documented way to turn metrics off after the SDK has been used.

Option 1 stays unavailable. Option 2 stays the recommendation.

## References

- [ML Kit Terms & Privacy](https://developers.google.com/ml-kit/terms). Last modified May 14, 2025. On-device processing of input and outputs. APIs may contact Google for bug fixes, updated models, and hardware-accelerator compatibility. "The ML Kit APIs also send metrics about the performance and utilization of the APIs in your app to Google." The developer must inform users about Google's processing of that metrics data as required by applicable law. No disable API.
- [Prepare for Google Play's data disclosure requirements (Android)](https://developers.google.com/ml-kit/android-data-disclosure). Last modified May 14, 2025. Lists data collected in all features for diagnostics and usage analytics, and the configurations a developer can control. Those configurations are feature options. Text recognition has no separate opt-out on this page. The developer remains responsible for any Play Data safety answers. This research does not answer that form.
- [MlKit reference](https://developers.google.com/android/reference/com/google/mlkit/common/MlKit). `initialize(Context)` and removing `com.google.mlkit.common.internal.MlKitInitProvider` disable automatic content-provider initialization. That is init timing, not a telemetry switch.
- [Recognize text in images with ML Kit on Android](https://developers.google.com/ml-kit/vision/text-recognition/v2/android). Last updated 2026-10-15 UTC. Recognizer setup and image input. No metrics or logging disable.
- [Prepare for Apple's App Store data disclosure requirements](https://developers.google.com/ml-kit/ios-data-disclosure). "By default, the ML Kit SDK collects" device information, application information, identifiers, performance metrics, API configuration, event type, and error codes for diagnostics and usage analytics. iOS only. Cited as corroboration that collection is described as the default, not as an Android control.

## Anti-patterns avoided

- No claim that a disable API exists.
- No claim that removing `MlKitInitProvider` turns metrics off.
- No claim that a Firebase Analytics or Crashlytics flag is an ML Kit flag.
- Third-party privacy pages were not used as the basis.
- No exploit steps, and no "secure" or compliance claim.
