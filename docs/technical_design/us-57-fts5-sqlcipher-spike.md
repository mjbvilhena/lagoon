# US-57 FTS5 and SQLCipher spike

Result: blocked.

The API 26 emulator passed. The arm64 phone did not run, so this is not a pass. Pass means every check below held on both targets. This note does not choose or change the cipher in accepted ADR-0006. It names the ADR-0006 settings that were tested: `PRAGMA key = "x'<64 hex>'"` as raw key data, and `PRAGMA temp_store = MEMORY`. It does not name a different mode or a different key size as the vault cipher.

The library under test was `net.zetetic:sqlcipher-android:4.6.1`. That artifact bundles SQLCipher and its SQLite. The product app does not depend on it. The spec's fallback, Room 2 FTS4 plus app-side ranking, may be used later. It is not chosen here.

## API 26 emulator

Target: Android emulator, `system-images;android-26;google_apis;x86_64`, `ro.build.version.sdk` 26, ABI `x86_64`. This is not an arm64 phone.

`FtsSqlCipherProbeTest` passed on that emulator on 2026-11-21. The database opened with the raw-key pragma above. `PRAGMA temp_store` was MEMORY (`2`). An FTS5 table using `unicode61 remove_diacritics 2` was created and filled. A prefix match on `caf*` and on `cafe*` returned the row that contained "café". `bm25(search, 10.0, 5.0, 3.0, 1.0, 1.0, 1.0, 1.0)` ordered a title match, then a tags match, then a body match. Those weights are the US-50 weights for title, tags, personal note, body, extracted text, OCR text, and snapshot text. `snippet()` and `highlight()` wrapped the matched word. The file did not open with the wrong key. It opened again with the right key.

## Arm64 phone

Blocked. No arm64 phone was attached. `adb` showed only the emulator above. An arm64 emulator was not used in place of the phone.

An installable debug build of this spike, labeled Lagoon spike, runs these same checks on a phone and shows PASS or FAIL with the model, ABI, and Android version. That phone run is not recorded here. This result stays blocked until it is. The build does not choose a cipher.

## APK size

Compared with the master debug build at `483d5891359942e640697c0665626e1d23532ee1`.

| APK | Bytes |
| --- | --- |
| Master `:app` debug | 2374931 |
| Spike debug, `net.zetetic:sqlcipher-android:4.6.1`, with the on-screen runner | 23450558 |

The spike debug APK is 21075627 bytes larger than that master debug APK. The product app was not rebuilt with this dependency, so that difference is the spike app, not a change to the Lagoon debug APK. The published CI artifact is the copy to install. Its SHA-256 is the checksum file beside that APK.
