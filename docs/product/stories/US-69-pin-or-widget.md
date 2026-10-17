# US-69 Pin a shortcut or a widget

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Could. Not in the approved specification.

## Title

Pin a shortcut or a widget.

## User story

**As a** person named Miguel
**I want** to pin a specific item as a launcher shortcut
**So that** I can open that item without hunting for it

## Background

- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: v1 capture, which already uses the share sheet.

## Acceptance criteria

- **Scenario: a specific item** — Could
  - Given he pins one item as a launcher shortcut
  - When he taps it and the item is not sensitive
  - Then that item opens
- **Scenario: a sensitive item** — Could
  - Given the pinned item is sensitive
  - When he taps the shortcut
  - Then US-37 asks him to unlock before the body is shown

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-01.

## Implementation notes

Not a design spec.
