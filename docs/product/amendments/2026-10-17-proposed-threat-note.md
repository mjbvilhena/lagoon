# Proposed threat note, 2026-10-17

> **Status: Proposed. Not an approved threat model.**
> The design band is closed. This note does not open it, does not start the threat-model gate, and does not claim the product is secure or STRIDE-compliant.
> Skill: `sdlc-threat-modeler`. Template: `get_sdlc_template("threat model")`. Definition of Done: `get_definition_of_done("security change")`.
> Security-change implementation DoD is unmet: there is no code. `get_domain_consultant` for capture, vault, and knowledge, and `get_layer_consultant("ui")`, returned no `DOMAIN.md` or `LAYER.md`. No MUST/NEVER rules were invented.
> The acceptance criteria this note points at are marked **Proposed: pending Miguel's approval** on US-64, US-75, US-78, US-79, US-80, and US-81.

## Metadata

| Field | Value |
|---|---|
| Subject | Proposed criteria for delegated security stories US-64, US-75, US-78, US-79, US-80, US-81 |
| Author role | `sdlc-threat-modeler`, draft only |
| Inputs reviewed | Those six story files, Miguel's 14:56 IST decisions as recorded in `docs/product/stories/REVIEW-PACK.md`, and the proposed amendment beside this file |
| Residual risk accepted by | Nobody. Miguel has not accepted this note |

## System under review

- Trust boundaries, as stated in the stories, not as a built system: the phone holds the vault; Drive receives ciphertext; a topic call is a separate cloud hop; another app can receive a share.
- Actor: the person named Miguel. No other persona.
- Assets named by the stories: vault key, item bodies, attachments, tokens, preview fetches.
- Out of scope: exploit steps, provider choice, payload contents, key transport, sync conflicts, exact alarms. Those stay open for Miguel. This note does not decide them.

## STRIDE findings

Each row is a hypothesis about the proposed criteria. Nothing here was observed in a running app.

| ID | Category | Asset / component | Evidence | Impact | Mitigation (defensive) | Observed or hypothesis |
|---|---|---|---|---|---|---|
| T-1 | Information disclosure | Sensitive item rows, screenshots, clips | US-75 proposed criteria | A list, a screenshot, or a clip shows body, OCR, or a thumbnail before unlock | Title, type, and a Sensitive mark only; `FLAG_SECURE`; sensitive clip flag; no sensitive thumbnail | Hypothesis |
| T-2 | Information disclosure | Lock-screen reminder for a sensitive item | US-75 states the question and does not choose | A lock-screen notice could show more than he wants, or less than his 14:56 ordinary-item decision | Left open for Miguel. Ordinary items still follow US-34 | Hypothesis |
| T-3 | Information disclosure | Drive file names | US-36 opaque-id criterion and US-78 scope criterion | A file name or a broad OAuth scope exposes titles or more than app data | Opaque ids; granted scopes exactly `drive.appdata`; tokens out of logs and WorkManager inputs | Hypothesis |
| T-4 | Information disclosure | Link preview fetch | US-79 proposed skip list | A fetch contacts a private host or sends cookies or a referral | http/https only; skip private and local hosts after DNS and on each redirect; no cookies, Referer, or auth; no JavaScript; size and redirect caps | Hypothesis |
| T-5 | Information disclosure | Token-looking URLs | US-79 open question | A preview fetch could send a secret that is sitting in a URL | Not decided. Recorded as open for Miguel | Hypothesis |
| T-6 | Tampering | Zip and share bytes | US-21 vault-key zip; US-64 FileProvider | A shared or exported file is readable without the key, or a `file://` URI exposes a path | Zip stays encrypted with the vault key. Share uses a non-exported FileProvider and a one-time read grant. Temp copies are wiped | Hypothesis |
| T-7 | Elevation of privilege | Manifest permissions and exported components | US-80 proposed allowlist | A wide permission or an extra exported component lets another app reach the vault | Allowlist, CI check, only the share target and the launcher exported | Hypothesis |
| T-8 | Spoofing | Upgrade and sideload | US-81 proposed signing rules | A different key or application id replaces the app and strands or swaps the vault | Same id, same key, higher versionCode; debug id suffix; published certificate hash | Hypothesis |
| T-9 | Repudiation | Topic result | US-41 provenance criterion | A stored topic cannot be tied back to the item and the call that produced it | Record item id, fields sent, timestamp, and model or prompt version | Hypothesis |
| T-10 | Denial of service | Preview fetch and long upload | US-79 limits; US-51 long-upload criterion | An unbounded fetch or upload stalls the phone | Redirect cap, timeout, 10 MiB cap; debounced unique work; foreground-service time limit or chunked uploads | Hypothesis |

Spoofing of the Google account beyond US-78's separate-copy rule is not expanded here. The sign-in flow is US-18.

## Missing controls (defensive)

These are unchecked because the controls are proposals, not code.

- [ ] Authentication / session
- [ ] Authorization checks on the new path
- [ ] Input validation and output encoding as appropriate
- [ ] Encryption in transit (and at rest if the change stores sensitive data)
- [ ] Audit logging of sensitive actions (no secrets in logs)
- [ ] Rate / size limits

## Secrets handling

No credential value appears in this note. US-26 and US-41 already say the docs and the APK do not carry a client secret or a plaintext topic key.

## Residual risk

Miguel has not approved the proposed criteria. Lock-screen text for sensitive items, token-looking URLs, the topic payload, key transport, and sync conflicts remain open. A lost key still means the content is lost, which he already decided. This note does not reduce that.

## Anti-patterns avoided

No exploit steps, no "secure" or "STRIDE-compliant" claim, no invented CVE.
