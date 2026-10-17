# US-70 See what is not ready for offline

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**Epic:** E2. Pending Miguel's keep/remove decision. Not an approval.

**MoSCoW:** Could. Not in the approved specification.

## Title

See what is not ready for offline.

## User story

**As a** person named Miguel
**I want** a check that lists items whose snapshot or OCR is still pending
**So that** I know what will be missing before I go offline

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: US-07, which already requires saved content to open offline.

## Acceptance criteria

- **Scenario: the check** — Could
  - Given some snapshots or OCR jobs are pending, failed, or have never run
  - When he opens the check
  - Then those items are listed, and a failed or never-run snapshot offers the retry from US-33

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-07.

## Implementation notes

Not a design spec.
