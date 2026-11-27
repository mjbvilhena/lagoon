# US-57 FTS5 and SQLCipher spike

Result: pass.

The API 26 emulator and an arm64 phone both held. Pass means every check below held on both targets. This note does not choose or change the cipher in accepted ADR-0006. It names the ADR-0006 settings that were tested: `PRAGMA key = "x'<64 hex>'"` as raw key data, and `PRAGMA temp_store = MEMORY`. It does not name a different mode or a different key size as the vault cipher. Choosing a cipher is a design decision for Miguel.

The library under test was `net.zetetic:sqlcipher-android:4.6.1`. That artifact bundles SQLCipher and its SQLite. The product app does not depend on it. The spec's fallback, Room 2 FTS4 plus app-side ranking, may be used later. It is not chosen here.

## API 26 emulator

Target: Android emulator, `system-images;android-26;google_apis;x86_64`, `ro.build.version.sdk` 26, ABI `x86_64`. This is not an arm64 phone.

`FtsSqlCipherProbeTest` passed on that emulator on 2026-11-21. The database opened with the raw-key pragma above. `PRAGMA temp_store` was MEMORY (`2`). An FTS5 table using `unicode61 remove_diacritics 2` was created and filled. A prefix match on `caf*` and on `cafe*` returned the row that contained "café". `bm25(search, 10.0, 5.0, 3.0, 1.0, 1.0, 1.0, 1.0)` ordered a title match, then a tags match, then a body match. Those weights are the US-50 weights for title, tags, personal note, body, extracted text, OCR text, and snapshot text. `snippet()` and `highlight()` wrapped the matched word. The file did not open with the wrong key. It opened again with the right key.

## Arm64 phone

Pass. Miguel ran `spike-debug.apk` on his own phone on 2026-11-27 at about 23:26 Europe/Dublin. The file is artifact 14708610769 from QA run 44906925384. Its SHA-256 is `dc103eb2019bbb99c7e04e9790eba753c925db29c7203095ad87785c3856ef34`. The screenshot is kept with his records and is not in this repository. The screenshot file's SHA-256 is `f1676a0cc876535914e1bf14ebccc97a1241e735426a6f772cd57311755f92da`.

The screen showed, verbatim:

```text
Lagoon spike
Raw key, memory temp store, FTS5, prefix, bm25, snippet, highlight, and reopen all held.
Model: samsung SM-S911B
ABI: arm64-v8a, armeabi-v7a, armeabi
Android: 17 (API 37)
```

That phone is a samsung SM-S911B. The ABI list starts with `arm64-v8a`. Android is 17, API 37. An arm64 emulator was not used in place of the phone.

`SpikeActivity` shows the sentence "Raw key, memory temp store, FTS5, prefix, bm25, snippet, highlight, and reopen all held." only as the detail of a report whose `passed` flag is true. `FtsSqlCipherProbe.run` sets that flag only after `runChecks` returns. `runChecks` opens the database with the raw-key pragma, requires `PRAGMA temp_store` MEMORY, creates and fills the FTS5 table, requires both prefix matches, requires the weighted `bm25()` order, requires `snippet()` and `highlight()` to wrap the word, requires the wrong key to fail, and requires the right key to reopen the four rows. Any failed `check` throws, and the catch path replaces that sentence with the error. The same screen update that shows the sentence sets the result line to PASS. The emulator result above and this phone together are the two targets. The build does not choose a cipher.

## APK size

Compared with the master debug build at `483d5891359942e640697c0665626e1d23532ee1`.

| APK | Bytes |
| --- | --- |
| Master `:app` debug | 2374931 |
| Spike debug built locally with the on-screen runner | 23450558 |
| Spike debug packaged by CI for install | 22563778 |

The product app was not rebuilt with this dependency, so the difference is the spike app, not a change to the Lagoon debug APK. Install the CI artifact `spike-debug`. The file is `spike-debug.apk`. Its SHA-256 is in `spike-debug.apk.sha256` in that same artifact. The CI package measured here is 20188847 bytes larger than the master debug APK.
