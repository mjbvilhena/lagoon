# Proposed threat note, 2026-10-17

> **Status: Proposed. Not an approved threat model.**
> The design band is closed. This note does not open it, does not start the threat-model gate, and does not claim the product is secure or STRIDE-compliant.
> Skill: `sdlc-threat-modeler`. Template: `get_sdlc_template("threat model")`. Definition of Done: `get_definition_of_done("security change")`.
> Security-change implementation DoD is unmet: there is no code. `get_domain_consultant` for capture, vault, and knowledge, and `get_layer_consultant("ui")`, returned no `DOMAIN.md` or `LAYER.md`. No MUST/NEVER rules were invented.
> The hypotheses below point at US-64, US-75, US-78, US-79, US-80, and US-81. Those stories stay Draft.

## Metadata

| Field | Value |
| --- | --- |
| Subject | Hypotheses for US-64, US-75, US-78, US-79, US-80, US-81 |
| Inputs reviewed | Those six story files and `docs/product/stories/REVIEW-PACK.md` |
| Residual risk accepted by | Nobody. This note is not accepted |

## System under review

- Trust boundaries, as stated in the stories, not as a built system: the phone holds the vault; Drive receives ciphertext; a topic call is a separate cloud hop; another app can receive a share.
- Actor: the person named Miguel. No other persona.
- Assets named by the stories: vault key, item bodies, attachments, tokens, preview fetches.
- Out of scope: exploit steps, provider choice, payload contents, key transport, and sync conflicts. Criteria follow from the ADR at the design gate. Alarms are inexact. This note does not decide those items.

## STRIDE findings

Each row is a hypothesis about the proposed criteria. Nothing here was observed in a running app.

| ID | Category | Asset / component | Evidence | Impact | Mitigation (defensive) | Observed or hypothesis |
| --- | --- | --- | --- | --- | --- | --- |
| T-1 | Information disclosure | Sensitive item rows, screenshots, clips | US-75. Sensitive items are excluded from the topic call | A list, a screenshot, or a clip shows body, OCR, a note, or a thumbnail before unlock | Neutral row: type and a Sensitive mark, no title, no snippet, no highlight. Before unlock, search matches tags only. After a US-37 unlock, titles show until the app is backgrounded. US-62 uses the neutral form. US-45 hides topic notes and quotes until unlock. `FLAG_SECURE` on the screen that shows unlocked content, not app-wide. `setRecentsScreenshotEnabled(false)` when app lock is on, API 33+. No sensitive thumbnail. Export waits for unlock | Hypothesis |
| T-2 | Information disclosure | Lock-screen notices for a sensitive item | US-75 | A lock-screen notice shows the title or a code of a sensitive item, including when the phone is unlocked or set to show all content | Both the public version and the private version are only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. The notice uses `VISIBILITY_PRIVATE` and `setPublicVersion`. Tapping it opens Lagoon. The item's details appear after the US-37 unlock. Ordinary items still follow US-34 | Hypothesis |
| T-3 | Information disclosure | Drive file names | US-36 opaque-id criterion and US-78 scope criterion | A file name or a broad OAuth scope exposes titles or more than app data | Opaque ids; granted scopes exactly `drive.appdata`; tokens out of logs and WorkManager inputs. The US-55 release marker is written before revoke. Offline sign-out clears local tokens and jobs, retries the revoke, and says it is pending. Delete still asks for a US-37 prompt when app lock is off | Hypothesis |
| T-4 | Information disclosure | Link preview fetch | US-79 | A fetch contacts a private host or sends cookies or a referral | http upgraded to https; cleartext off; skip private, local, IPv6 unique-local, link-local, multicast, and mapped addresses after DNS, on the connected address, and on each redirect; no cookies, Referer, or auth; generic User-Agent; no JavaScript; 15 seconds for the whole call; 5 redirects; 10 MiB of decompressed bytes | Hypothesis |
| T-5 | Information disclosure | Token-looking URLs | US-79 | An automatic preview fetch sends a secret that is sitting in the URL | No auto-fetch. A foggy placeholder is shown. A fetch runs only after he says yes. The detector also scans fragment parameters, requires a long value to mix letters and digits rather than hyphen-separated words, and adds the path segments invite, activate, signin, callback, and oauth. Hyphenated article slugs are negative tests. US-76 strips the query and fragment of a token-looking URL from a later payload. The same gate applies to enrichment and snapshots | Hypothesis |
| T-6 | Tampering | Zip and share bytes | US-21 vault-key zip; US-64 | A shared or exported file is readable without the key, or a plaintext copy stays on disk, or a photo still carries location | Zip stays encrypted with the vault key. With no part picked, share sends attachments, then the URL, then the body. "Note text" is the body. Share prefers a `ParcelFileDescriptor` pipe and no plaintext file. A temp copy, if one cannot be avoided, is wiped on return, within 15 minutes, and on next launch, and the URI grant is revoked with that wipe. Shared photos drop EXIF and XMP location and device fields unless he asks to keep location. Links use Custom Tabs or `ACTION_VIEW`, not a WebView | Hypothesis |
| T-7 | Elevation of privilege | Merged manifest permissions and exported components | US-80 | A library permission or an extra exported component lets another app reach the vault | Check the full merged manifest, including the application id's `DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION`. `USE_FINGERPRINT` stays with `maxSdkVersion` 28 for API 26 and 27. `AD_ID` and `ACCESS_ADSERVICES_*` are absent. `CAMERA`, `SCHEDULE_EXACT_ALARM`, and `USE_EXACT_ALARM` are absent. Other library entries are allowlisted or removed. Signature-protected components may remain | Hypothesis |
| T-8 | Spoofing | Upgrade and sideload | US-81 | A different key or an uninstall destroys the Keystore key and the vault | Same id, same key, higher versionCode. Uninstall, clear storage, or a key mismatch makes the vault and every zip unreadable. CI verifies the release APK with `apksigner verify --print-certs` and checks the published certificate SHA-256 and the APK SHA-256. The overview dated 2026-08-18 UTC says protections expand to all certified devices in 2027. The package is registered with the release key under a limited-distribution account before then. ADB stays the fallback | Hypothesis |
| T-9 | Repudiation | Topic result | US-41 provenance criterion | A stored topic cannot be tied back to the item and the call that produced it | Record item id, fields sent, timestamp, and model or prompt version | Hypothesis |
| T-10 | Denial of service | Preview fetch and long upload | US-79 limits; US-51 long-upload criterion | An unbounded fetch or upload stalls the phone | Redirect cap, timeout, 10 MiB cap; debounced unique work; foreground-service time limit or chunked uploads | Hypothesis |

Spoofing of the Google account beyond US-78's separate-copy rule is not expanded here. The sign-in flow is US-18.

## Missing controls (defensive)

These are unchecked because there is no code.

- [ ] Authentication / session
- [ ] Authorization checks on the new path
- [ ] Input validation and output encoding as appropriate
- [ ] Encryption in transit (and at rest if the change stores sensitive data)
- [ ] Audit logging of sensitive actions (no secrets in logs)
- [ ] Rate / size limits

## Secrets handling

No credential value appears in this note. US-26 and US-41 already say the docs and the APK do not carry a client secret or a plaintext topic key.

## Residual risk

A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock. A token-looking URL is not auto-fetched. ML Kit has no documented telemetry off switch, so US-07 requires disclosure and acceptance before the first OCR use. Topic extraction starts on, with an off switch. There is no per-item topics opt-out switch in v1. Sensitive items are always excluded, the Settings switch covers everything else, and "Get topics" lets him send one ordinary item saved while the switch was off. Content already sent cannot be recalled when an item is marked sensitive later. The topic payload, key transport, and sync conflicts follow from the ADR at the design gate. Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase, and forgetting the passphrase and losing the device still means the content is lost. This note does not settle that. It is still not an approved threat model.

## Anti-patterns avoided

No exploit steps, no "secure" or "STRIDE-compliant" claim, no invented CVE.
