# US-89 Fail CodeQL on a high security finding

> **Approval status: Draft. Not approved.**
> Security criteria approved by mjbvilhena on 2026-11-06.

**Epic:** E6.

**MoSCoW:** Must. Proposed for v1. Not approved.

## Title

Fail CodeQL on a high security finding.

## User story

**As a** person named Miguel
**I want** the CodeQL check to fail when the analysis reports a high security finding
**So that** a successful upload is not treated as a clean result

## Background

- The `CodeQL` job in `.github/workflows/qa.yml` traces `./gradlew assembleDebug` and uploads SARIF. The required check passes when that job succeeds. It does not fail on finding severity.
- Listing code-scanning alerts for this repository returned HTTP 403 for the token used on 2026-11-06. This story does not depend on that API. The gate reads the SARIF the job just produced.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. This story does not add a vault rule.
- Out of scope: a new query pack, a quality-only finding, dismissing an existing alert, and a second required check name.

## Acceptance criteria

- **Scenario: a high security result fails the same job** — Must
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given the CodeQL job produced SARIF for this commit
  - When a result has `security-severity` of 7.0 or higher and the query is tagged `security`
  - Then the `CodeQL` job fails
  - And a result below 7.0 does not fail the job
  - And a query that is not tagged `security` does not fail the job
- **Scenario: analysis errors are not a clean scan** — Must
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given database creation, the debug assemble, analysis, or SARIF upload fails, or the SARIF file is missing
  - When the job finishes
  - Then the job fails
  - And the job does not report success because an alerts API was unauthorized
- **Scenario: no baseline and no new check name** — Must
  - Given this policy is in force
  - When the job is configured
  - Then it does not use a baseline that hides findings already present
  - And the required check name stays `CodeQL`
  - And ruleset 25303010 is not edited by this story

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.
- An exception uses the same file and the same approval sentence as US-88, with the CodeQL rule id and the result location in place of the OSV id and module version. A line without `Exception approved by mjbvilhena on` and a date does not skip the result.

## Dependencies and risks

- Blocked on approval of this story and of the security criteria above by mjbvilhena.
- US-88 owns the exception file. This story does not invent a second one.

## Implementation notes

Not a design spec. Do not change `.github/workflows/qa.yml` from this draft.
