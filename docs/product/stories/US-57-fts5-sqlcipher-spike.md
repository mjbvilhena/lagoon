# US-57 Prove that search and encryption can run together

> **Approval status: Draft. Not approved.**

**Epic:** E3.

**MoSCoW:** Must. Prove search on the encrypted database before US-15 and US-35 choose an engine.

## Title

Prove that search and encryption can run together.

## User story

**As a** person named Miguel
**I want** a written spike to say whether FTS5 and SQLCipher work together on this phone
**So that** search and encryption are not built on a combination that cannot run

## Background

- FTS5 needs a bundled SQLite. Stock SQLite on `minSdk` 26 has no FTS5. Room 2 has FTS4. SQLCipher is not yet shown to work with that bundle.
- The user-story Definition of Done allows a chore when the request asked for one.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: choosing or changing the cipher. ADR-0006 is Accepted. Approved by mjbvilhena on 2026-10-20. This spike does not choose or change that cipher.

## Acceptance criteria

- **Scenario: the spike writes a result** — Must
  - Given a small test app on `minSdk` 26
  - When FTS5 is tried with SQLCipher using a bundled SQLite, on an API 26 emulator and on an arm64 phone
  - Then a note in `docs/` records pass or blocked, with the error if blocked, and it records the APK size change against the current master debug build, or against the current master release build when that is the build that was measured
  - And pass means all of the following held on both targets: the database opened under the accepted ADR-0006 SQLCipher settings, `PRAGMA key = "x'<64 hex>'"` as raw key data and `PRAGMA temp_store = MEMORY`; an FTS5 table using `unicode61 remove_diacritics 2` was created and filled; a prefix match returned the row; weighted `bm25()` ranking followed the US-50 weights; snippet and highlight worked as US-15 states; and the file reopened only with the right key
- **Scenario: it does not choose a product cipher** — Must
  - Given that note
  - When it is read
  - Then it does not choose or change the cipher in accepted ADR-0006. It may name the ADR-0006 settings it tested. It does not name a different mode or a different key size as the vault's cipher

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout is in `docs/technical_design/ux-design.md` (approved 2026-10-31).

## Dependencies and risks

- US-15 is approved as an artefact. US-35 is approved as an artefact. FTS and SQLCipher are not combined until this note exists. A fallback, Room 2 FTS4 plus app-side ranking, may be recorded. It is not chosen here.

## Implementation notes

A chore, not an ADR.
