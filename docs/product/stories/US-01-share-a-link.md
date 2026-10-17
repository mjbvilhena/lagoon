# US-01 Share a link into the vault

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Review verdicts at commit `9e7de63` are in `docs/product/stories/REVIEW-PACK.md`. A verdict is not an approval.
> Story approval has not been recorded. Epic approval is not story approval.

**Epic:** E1. Not an approval.

**MoSCoW:** Must. v1, from approved epic E1.

## Title

Share a link into the vault.

## User story

**As a** person named Miguel
**I want** to share a link from another app into the vault in one tap
**So that** the page is kept on my phone without me copying the address

## Background

- A link he sees in another app stays in that app until the vault can take a share.
- Domain constraints: `get_domain_consultant("capture")` found no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: fetching the page (US-08, US-33), a repeat of the same page (US-49), last-used tags (US-28), title fill when he did not supply a subject (US-40), search, and sync. Tracking-parameter match rules live in US-49.

## Acceptance criteria

- **Scenario: share a link** — Must
  - Given the vault is installed and another app is sharing one http or https URL
  - When he chooses Lagoon on the share sheet
  - Then one tap stores one link item, the source URL is the URL he shared, created and last-saved are that instant, and he sees the confirmation "Saved to Lagoon"
- **Scenario: text that also contains a URL** — Must
  - Given the share has text and a URL
  - When he chooses Lagoon
  - Then the URL is the source URL and the leftover text is kept as a note, the same way US-02 keeps leftover text
  - And the title uses one order, shared with US-02 and US-40: share subject, then a title he typed, then the page title or the file name, then leftover text, then host and path (host only when the link is token-looking, US-40)
- **Scenario: where it came from** — Must
  - Given the share includes a URL
  - When the item is stored
  - Then the item records the URL host as the source domain
  - And the sending app is recorded only when the platform reports it. Display, search, and filter of those fields are US-61
- **Scenario: nothing to save** — Must
  - Given the share has no URL and is not plain text, an image, or a PDF
  - When the vault receives it
  - Then no item is stored and he sees that nothing was saved
- **Scenario: a different page** — Must
  - Given the shared URL still differs from every stored link after the normalisation in US-49
  - When he shares it
  - Then a new item is stored and the repeat snackbar from US-49 is not shown

## Analytics and observability (optional)

Not applicable. The brief forbids analytics.

## Design and a11y notes

- The share sheet name is Lagoon.
- The new-item confirmation is exactly "Saved to Lagoon".
- No placeholder sentences in the shipped UI.
- The share target has a name a screen reader can announce. Layout waits for the UX artefact.

## Dependencies and risks

- A hand-entered URL uses US-06, then US-49 for a repeat.
- Open questions for Miguel are in `docs/product/stories/REVIEW-PACK.md`. This story does not decide them.

## Implementation notes

Android share intent for a URL. Not a design spec. No `LAYER.md` exists.
