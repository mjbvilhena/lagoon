# Threat model: vault at rest, Drive, share, previews, sideload, notifications, passphrase reset

## Metadata

| Field | Value |
| --- | --- |
| Subject | Design-gate threat model for at-rest encryption, Drive backup, share intake, link previews, sideloaded APK updates, notifications, and the passphrase reset |
| Author role | Design draft. Not an acceptance |
| Inputs reviewed | Approved stories US-31, US-34, US-35, US-41, US-64, US-75, US-78, US-79, US-80, US-81, US-85, US-86. Draft stories cited where they already state a control (US-04, US-07, US-13, US-20, US-36). Proposed ADR-0001 through ADR-0005. `get_sdlc_template("threat model")`. `get_definition_of_done("security change")`. `get_domain_consultant("vault")` returned `docs/domain/vault/DOMAIN.md`. `get_domain_consultant("capture")` and `get_domain_consultant("knowledge")` found no separate payload; those capabilities are inside the vault domain. `get_layer_consultant` returned `ui`, `domain`, and `data` |
| Residual risk accepted by | Nobody. This model is not accepted |

**Status: Proposed. Needs approval by mjbvilhena.**

Skill: `sdlc-threat-modeler`. The note `docs/technical_design/2026-10-17-proposed-threat-note.md` is an older hypothesis list. It is not this model.

Security-change Definition of Done is not met. There is no application code, so authentication checks, negative tests, and runtime audit events cannot be shown. This file is the threat review. It contains no exploit procedure and no certification claim. Human acceptance is still required.

## System under review

- Trust boundaries, from the stories and the Proposed ADRs, not from a running app: the phone holds the vault and the Keystore wrap; Drive `appDataFolder` holds ciphertext and a clear KDF header; the paid Gemini API would receive ordinary text (ADR-0003); ML Kit would receive metrics after acceptance (ADR-0004); another app can send a share or receive one item; link preview is an outbound HTTPS fetch.
- Actor: Miguel. A second person is not named. The interesting other parties are whoever holds the phone, whoever can read Drive app data, the topic provider, and an app that sends or receives a share.
- Assets: passphrase, vault key, wrap blob, item bodies, attachments, tokens in URLs, OAuth tokens, the topic API key, notification text.
- Out of scope: exploit steps, the content cipher (still no ADR), Play Store distribution, E10 trips. Alarms stay inexact (US-77).

## STRIDE findings

There is no running app. Every row is a hypothesis about the written design. Evidence is a story or an ADR, not a trace.

| ID | Category | Asset / component | Evidence | Impact | Mitigation (defensive) | Observed or hypothesis |
| --- | --- | --- | --- | --- | --- | --- |
| T-1 | Information disclosure | Database and files at rest | US-35, ADR-0001, domain MUST on encrypt-before-write | A copy of app storage is readable if the key is also there, or if bytes were written before the key existed | Vault key from Argon2id, never stored off the phone. Keystore wrap stays on the phone. Pre-key share stays in memory. Derived files use the same key. `allowBackup` false | Hypothesis |
| T-2 | Spoofing | Software Keystore | US-35 `KeyInfo` scenario, ADR-0001 | A phone with no TEE stores a key any local process can unwrap later | If the wrapping key is not StrongBox or TEE (API 31+) or `isInsideSecureHardware()` (API 26–30), delete it and write no vault. No software fallback | Hypothesis |
| T-3 | Information disclosure | Drive app data | US-36, US-78, ADR-0001 | A Drive token or a Google-side reader sees titles, or a wrapped key | Ciphertext only, opaque names, scope exactly `drive.appdata`. Header carries salt and parameters, not the key and not the wrap blob. Topic call is not this upload (US-41) | Hypothesis |
| T-4 | Tampering | Two phones and a zip | US-20, US-13, US-52, ADR-0002 | The last upload replaces the other phone, or a deleted item returns | Merge per item id inside the ciphertext. One-sided ids with no tombstone are kept. A finished delete writes a tombstone that wins, including against a later `updatedAt` and a zip import. Sync does not read old Drive revisions | Hypothesis |
| T-5 | Information disclosure | Old Drive revisions and old zips | US-86, ADR-0001 | After a passphrase change, the old passphrase still opens an old revision | Accepted residual. The app does not delete Drive revisions. US-86 tells him old zips need the old passphrase. Current copy is re-encrypted under the new key | Hypothesis |
| T-6 | Information disclosure | Share that arrives before setup | US-35 | The first shared item is written in the clear because the key does not exist yet | Hold it in memory. If he declines the warning, nothing is saved and he is told. Domain NEVER on plaintext before the key exists | Hypothesis |
| T-7 | Tampering | Malicious or protected PDF in a share | US-04, US-85, US-80, ADR-0005 | A crafted PDF hits the platform renderer, or a password prompt stores a second secret | API 26–29 uses the bundled library and does not call `PdfRenderer`. API 30+ uses memfd in a non-exported process. Protected PDFs are stored, not rendered, and no password is taken, including on API 35 | Hypothesis |
| T-8 | Information disclosure | Link preview fetch | US-79 | The fetch hits a private host, sends a cookie, or ships a secret that was sitting in the URL | No auto-fetch for a token-looking URL. A sensitive item is not fetched. An `http` URL is fetched as `https`, and cleartext stays off. Skip the private, loopback, link-local, and other hosts US-79 names, after DNS and on each redirect. No cookies, Referer, or authorization. User-Agent `LagoonPreview`. At most 5 redirects, 15 seconds, 10 MiB decompressed | Hypothesis |
| T-9 | Spoofing | Sideloaded update | US-81 | A different signature replaces the app, or uninstall is treated as an upgrade and the key copy is gone | Same application id, same release key, higher `versionCode`. Android refuses a key mismatch and does not remove the key. Only uninstall or clear storage removes the key copy. No self-updater. CI checks `apksigner` certificate and APK SHA-256. Debug id ends in `.debug` | Hypothesis |
| T-10 | Information disclosure | Notifications | US-34, US-75, US-55 | A sensitive reminder shows a title or a code on the lock screen, or an ordinary one is treated as empty | Sensitive public and private text are only "Lagoon reminder", including when the phone shows all notification content. Tap opens Lagoon and the item waits for US-37. Ordinary reminders may show the title. That ordinary text is an accepted residual | Hypothesis |
| T-11 | Elevation of privilege | Passphrase reset | US-35, US-37, US-86, ADR-0001 | Someone who does not hold the key, or who cancels the biometric, sets a new passphrase | A phone with no key copy cannot reset. "I've forgotten my passphrase" there only offers a new empty vault after an explicit warning, and does not overwrite Drive until he confirms. A phone that holds the key resets only after a successful US-37 unlock. Cancel or fail changes nothing. Re-encrypt mints a new salt. The other install must not overwrite the new Drive copy | Hypothesis |
| T-12 | Information disclosure | Topic request | US-41, US-75, US-79, ADR-0003 | Vault text is used to train a model, or a sensitive item or an image is included, or the API key ships in the APK | Paid Gemini API only, billed-project confirmation, no grounding and no File API. Text fields only. Sensitive items excluded. Token query and fragment stripped. Key stored as vault ciphertext, not in the APK. 55-day abuse log remains (T-16) | Hypothesis |
| T-13 | Information disclosure | ML Kit metrics | US-07, US-31, ADR-0004 | OCR starts ML Kit before he accepts, or a fake off switch hides metrics that still flow | Bundled model only. Init provider removed. `MlKit.initialize` only after acceptance. No metrics toggle. Image and recognized text stay on device per the terms the research note cites. Metrics themselves still leave after acceptance (T-17) | Hypothesis |
| T-14 | Denial of service | Passphrase guesses and preview size | US-35, US-79, ADR-0001 | A tight loop runs Argon2id, or a preview download fills the disk | Retry wait 2s doubling to 32s, KDF does not run during the wait, 12-code-point floor checked first. Preview stops at 15 seconds and 10 MiB | Hypothesis |
| T-15 | Repudiation | Topic result and passphrase change | US-41, US-35 | A stored topic cannot be tied to the call, or a passphrase lands in a log | Provenance stores item id, fields sent, timestamp, and model id. Passphrase and topic key are never logged. No separate audit product is added, because US-07 forbids an analytics or crash product | Hypothesis |
| T-16 | Information disclosure | Gemini abuse log | ADR-0003, Gemini paid-service terms | Prompts and responses are retained for abuse monitoring for up to 55 days, and a mis-confirmed unpaid key would be used to improve products | Named residual. Paid tier does not train on prompts. The app cannot prove billing. Unpaid use is forbidden by the ADR and is not technically enforced | Hypothesis |
| T-17 | Information disclosure | ML Kit after acceptance | ADR-0004, US-07 | Usage and diagnostic metrics go to Google for as long as he uses OCR | Named residual. He can refuse or withdraw acceptance, which stops later `initialize`. He cannot turn the metrics off and keep OCR. No third-party analytics SDK is added | Hypothesis |
| T-18 | Elevation of privilege | Exported components and permissions | US-80 | A library permission or an extra exported component lets another app reach the vault | Merged-manifest allowlist. Share target and launcher are the exported app components. PDF render process is not exported (ADR-0005). `CAMERA` and exact-alarm permissions stay absent | Hypothesis |

Spoofing of the Google account beyond US-78's separate-copy rule is not expanded. Sign-in is US-18.

Denial of service against Drive quota is not given a number. The specification sets no sync budget.

## Missing controls (defensive)

Unchecked because there is no code. The design names the control. It does not demonstrate it.

- [ ] Authentication / session. US-37 covers sensitive items. The vault key does not use user authentication, by US-35. Passphrase reset is T-11.
- [ ] Authorization checks on the new path. Share-out of a sensitive item waits on US-37 (US-64). No second user exists to authorize.
- [ ] Input validation and output encoding as appropriate. PDF type check (US-04), preview host rules (US-79), topic payload allowlist (ADR-0003).
- [ ] Encryption in transit (and at rest if the change stores sensitive data). At rest is T-1. Preview and topic calls are HTTPS. The cipher algorithm is still an open ADR.
- [ ] Audit logging of sensitive actions (no secrets in logs). T-15. No new analytics pipeline.
- [ ] Rate / size limits. T-14, ADR-0003's 100000-character send bound, US-31's OCR page cap.

## Secrets handling

No credential value is written in this model. US-26 and US-41 already say the repo and the APK do not carry a client secret or a plaintext topic key. ADR-0001 forbids the vault key on Drive. ADR-0003 stores the topic key only as vault ciphertext.

## Residual risk

A lost passphrase on a phone that no longer holds the key still loses the content (US-35). Old Drive revisions and old zips still open with the old passphrase (T-5). Ordinary notification titles are visible (T-10). Paid Gemini keeps prompts for abuse monitoring for up to 55 days, and an unpaid key is not detectable (T-16). ML Kit metrics continue after he accepts OCR (T-17). The bundled PDF parser on API 26–29 is in-process (ADR-0005). The content cipher is not chosen. This model does not claim the product is secure or STRIDE-compliant.

## Anti-patterns

- Exploit steps, payloads, or a procedure for breaking the KDF.
- A "secure" or "STRIDE-compliant" claim.
- Inventing a CVE number. The API 27 renderer bug is cited by its public issuetracker id in ADR-0005, not as a new CVE.
- Marking a hypothesis as something observed in an app. There is no app.

Status: Proposed. Needs approval by mjbvilhena.
