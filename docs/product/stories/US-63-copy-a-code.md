# US-63 Copy a code he marked

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> Review verdicts are not approvals. Status stays Draft until all five bots re-review.

**MoSCoW:** Should. Not in the approved specification. Booking and merchant extraction stay out of scope.

## Title

Copy a code he marked.

## User story

**As a** person named Miguel
**I want** to mark a code on an item and copy it
**So that** I can copy a code without selecting it by hand each time

## Background

- On 2026-10-17 at 14:56 IST Miguel decided a lock-screen notification may show codes. This story is the mark he uses so US-34 knows which text is a code.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: detecting bookings or merchants. Full-screen QR remains an open question for Miguel.

## Acceptance criteria

- **Scenario: mark and copy** — Should
  - Given he selects text on an ordinary item and chooses Mark as code
  - When he later chooses Copy code on API 33 or newer
  - Then that text is on the clipboard and the clip is marked `EXTRA_IS_SENSITIVE`
- **Scenario: a sensitive item** — Should
  - Given the code is on an item marked sensitive
  - When he copies it
  - Then the copy follows US-75, which is still proposed and pending Miguel's approval
- **Scenario: no code** — Should
  - Given he has not marked a code
  - When he opens the item
  - Then Copy code is not offered and no code is invented

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- US-34 shows a marked code on an ordinary lock-screen notification.

## Implementation notes

Not a design spec.
