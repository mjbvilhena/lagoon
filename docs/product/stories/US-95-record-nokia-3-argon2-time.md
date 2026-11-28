# US-95 Record the Nokia 3 Argon2id time

> **Approval status: Draft. Not approved.**

**Epic:** E8.

**MoSCoW:** Must. Split from the unmet unlock-time record on US-35. Decision 7 of the accepted vault key plan. This file is not approval of that decision again.

## Title

Record the Nokia 3 Argon2id time.

## User story

**As a** person named Miguel
**I want** the native Argon2id call at the accepted floor timed on a Nokia 3 (2017)
**So that** US-35 is not called Done without that record, and the floor is not lowered to suit the phone

## Background

- Accepted ADR-0001 says unlock time at the floor is measured on a Nokia 3 (2017), 2 GB RAM, on the official Android 8.0 update, API 26, and the measurement is recorded with the build. That record sets no pass or fail number of seconds. A slow result is not a reason to lower the floor without a new ADR.
- Decision 7 of `docs/technical_design/us-35-vault-key-plan.md` is a median under 1 second. Approved by mjbvilhena on 2026-11-28. He typed "Approve 4, 5 and 7" at 10:13 GMT. The result is recorded either way. The floor is never lowered. No measurement is in the tree.
- Slice 1 wires `com.lambdapioneer.argon2kt:argon2kt:1.6.0` and adds `Argon2FloorBenchmark`. That benchmark is not a Nokia 3 run. An emulator is not that phone.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: lowering memory, iterations, or parallelism. SQLCipher and Tink. A Moto E4. Calling US-35 Done.

## Acceptance criteria

- **Scenario: the Nokia 3 time is recorded** — Must
  - Given a Nokia 3 (2017), 2 GB RAM, on the official Android 8.0 update, API 26
  - And the native library is loaded first
  - When the native Argon2id call runs at memory 65536 KiB, iterations 3, parallelism 1, a fixed 16-byte salt, a 32-byte output, and version 0x13
  - And the passphrase is a 12-code-point fixture, not a real person's passphrase
  - And the call runs at least three times
  - Then each time and the median are recorded with the build
  - And the floor is not lowered
  - And a result of 1 second or more is still recorded
  - And an emulator time is not this record
- **Scenario: no phone yet** — Must
  - Given no Nokia 3 run is in the tree
  - When the record is read
  - Then it says the median is not yet measured

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

No screen. The fixture passphrase is not shown as guidance.

## Dependencies and risks

- The instrumented benchmark is `data/src/androidTest/kotlin/com/mjbvilhena/lagoon/data/crypto/Argon2FloorBenchmark.kt`. CI does not run it.
- US-35 stays not Done until this record exists, whether or not the median is under 1 second.

## Implementation notes

Do not claim a median. No hardware run exists at the time this story is drafted.
