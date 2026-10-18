# ADR-0005: Bundle PDF rendering on API 26–29, and never take a PDF password

## Status

Proposed. Needs approval by mjbvilhena.

US-85 leaves this choice to the design gate. Drafting this record is not acceptance.

## Context

`minSdk` is 26. US-04 stores a PDF and extracts a text layer with a library that works on that minimum, naming PdfBox-Android as an example, not as an ADR. Pages with no text layer go to on-device OCR (US-31). A password-protected PDF is stored, marked "text not extracted", and v1 stores no PDF password (US-04). US-85, which is approved, opens a page full-screen and says a password-protected PDF does not open full-screen, no password is asked for or stored, and no plaintext decoded image or rendered page is written to disk.

US-85 also states the platform constraint: `PdfRenderer` needs a seekable file descriptor. On API 26–29, rendering a decrypted PDF without a plaintext file needs a bundled renderer. `memfd` is an option on API 30+.

Platform facts used here:

- `android.system.Os.memfd_create` is API 30.
- `PdfRenderer(ParcelFileDescriptor, LoadParams)` and `LoadParams.Builder.setPassword` are API 35 (also S Extensions 13). Before that, the single-argument constructor throws `SecurityException` when the file requires a password or the scheme is not supported. [PdfRenderer](https://developer.android.com/reference/android/graphics/pdf/PdfRenderer), [LoadParams.Builder](https://developer.android.com/reference/android/graphics/pdf/LoadParams.Builder).
- The same class doc says an untrusted file should be rendered in a separate isolated process with minimal permissions.
- On API 27 and below, opening one password-protected file can make a later unprotected file throw "password required". That bug was not backported ([issuetracker 63867014](https://issuetracker.google.com/issues/63867014)).

The vault file on disk is ciphertext (US-35). A temp plaintext PDF would be a derived file written in the clear, which US-85 and US-35 both refuse. Share intake is untrusted input.

Domain consultant: `docs/domain/vault/DOMAIN.md`. Layer consultant: `docs/layers/data/LAYER.md`.

## Decision

Recommended option: on API 26–29, render and extract with a bundled library (PdfBox-Android, the library US-04 already allows as the example). On API 30 and newer, render with platform `PdfRenderer` backed by an anonymous memfd, in a non-exported process that cannot read the vault. On every API level, including 35 and newer, do not ask for, store, or pass a PDF password.

Rules, if this record is accepted:

- Detect encryption before any renderer runs. A protected PDF is stored as the original bytes, marked "text not extracted", not OCR'd, and not opened full-screen. He is told it is password-protected and offered Open with another app (US-64, US-85). `setPassword` is not called. `write(..., removePasswordProtection)` is not called.
- API 26–29 does not call platform `PdfRenderer` at all, including for an unprotected file. That avoids a plaintext file, and it avoids the API 27 sticky `SecurityException`.
- API 30+ may call `PdfRenderer` only with a seekable anonymous descriptor (`memfd`) filled from the decrypted bytes. The descriptor is closed with the renderer. Nothing is written to a file for the renderer. The render process is not exported and has no vault path (US-80). Bitmaps stay in memory (US-85).
- If the decrypted PDF cannot be held in that anonymous memory, rendering fails and the stored ciphertext stays. The failure must not fall back to a cache file.
- Text-layer extraction on every API uses the bundled library, so API 26 and API 35 share one extractor. Pages with a text layer are not OCR'd (US-04, US-31).
- One page is rendered at a time (the platform renderer already allows only one open page).

Default: a PDF that fails detection is treated as protected and is not passed to `PdfRenderer`.

## Alternatives considered

| Option | Why it was plausible | Why it lost |
| --- | --- | --- |
| Write a temp plaintext file on API 26–29 and pass it to `PdfRenderer` | It is the only seekable descriptor the platform offers before `memfd` | US-85 forbids a plaintext rendered page on disk. US-35 treats temp copies as vault files that must be encrypted. A kill mid-render leaves the file. Trade-off of the bundle: a second renderer and a larger APK on old phones |
| Refuse in-app PDF view below API 30 | One code path, platform renderer only | US-85 is in v1 at `minSdk` 26 and includes a PDF page. Dropping those phones is a product cut |
| Use `LoadParams.setPassword` on API 35 | The platform can open protected files there | US-04 and US-85 forbid asking for or storing a PDF password. Calling `setPassword` needs a password. Protected files stay unopened in-app on API 35 as well |
| Platform `PdfRenderer` for every unprotected file, including API 26–29, inside the app process | Fewer moving parts | Below API 30 it needs a real file. In-process Pdfium is what the platform doc tells apps to isolate, and the API 27 bug is process-wide |

## Consequences

- **Positive.** API 26–29 never needs a plaintext PDF file. Protected PDFs never reach `PdfRenderer`, so the sticky password bug is not triggered. API 35 does not grow a password prompt the stories forbid.
- **Negative / accepted trade-offs.** Two renderers. The bundled library on API 26–29 runs in the app process; a malicious PDF is still a parser bug, without the platform sandbox. memfd rendering can fail closed on a very large file. Full-screen quality may differ between PdfBox and Pdfium.
- **Follow-ups.** The isolated process is a manifest entry and must stay inside the US-80 export rules. He is the approver.

## Compliance

- [ ] An API 26–29 test renders a text-layer page with no file under the cache or files directory created for the renderer
- [ ] A protected-PDF fixture on API 26 and on API 35 stores the file, sets "text not extracted", does not open full-screen, and does not call `setPassword`
- [ ] API 30+ renderer input is an anonymous descriptor, and the render component is not exported
- [ ] A failure to allocate that descriptor does not create a fallback file

## Related

- Domain consultant `vault`. Layer consultants `data` and `ui`.
- US-04, US-11, US-31, US-35, US-64, US-80, US-85.
- [PdfRenderer](https://developer.android.com/reference/android/graphics/pdf/PdfRenderer), [LoadParams.Builder](https://developer.android.com/reference/android/graphics/pdf/LoadParams.Builder).

## Anti-patterns

- A password field "only on API 35".
- Copying the decrypted PDF into `cacheDir` for the renderer.
- Treating PdfBox-Android in US-04's story text as already accepted. This record is the acceptance point, and it is still Proposed.

## Skill State Machine (sdlc-adr-drafter)

```mermaid
stateDiagram-v2
    [*] --> DraftADR
    DraftADR --> Review: Submit for Approval
    Review --> DraftADR: Needs Revision (Max 3 iterations allowed)
    Review --> Accepted: Approved
    Review --> Rejected: Declined
    Accepted --> Implementation: Handoff / Enforce
    Rejected --> [*]
```

Status: Proposed. Needs approval by mjbvilhena.
