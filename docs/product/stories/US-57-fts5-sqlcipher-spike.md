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
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: choosing the cipher. That stays an ADR.

## Acceptance criteria

- **Scenario: the spike writes a result** — Must
  - Given a small test app on `minSdk` 26
  - When FTS5 is tried with SQLCipher using a bundled SQLite, on an API 26 emulator and on an arm64 phone
  - Then a note in `docs/` records pass or blocked, with the error if blocked, and it records the APK size change
- **Scenario: it does not choose a product cipher** — Must
  - Given that note
  - When it is read
  - Then it does not name a mode or a key size as the vault's cipher

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-15 is approved as an artefact. US-35 is approved as an artefact. FTS and SQLCipher are not combined until this note exists. A fallback, Room 2 FTS4 plus app-side ranking, may be recorded. It is not chosen here.

## Implementation notes

A chore, not an ADR.
