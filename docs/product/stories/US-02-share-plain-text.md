# US-02 Share plain text into the vault

> **Approval status: Draft. Not approved.**

**Epic:** E1.

**MoSCoW:** Must. v1.

## Title

Share plain text into the vault.

## User story

**As a** person named Miguel
**I want** to share plain text into the vault
**So that** a passage I want to keep is stored without me retyping it

## Background

- Text shared from another app is not a link item unless the text contains a URL.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: OCR, topics, and the repeat-URL update (US-49). An empty title is capped at 120 characters.

## Acceptance criteria

- **Scenario: share text** — Must
  - Given another app shares plain text that is not only whitespace and contains no URL
  - When he chooses Lagoon
  - Then one note item is stored and he sees "Saved to Lagoon". The sending app name is recorded only when the platform reports it
- **Scenario: the text contains a URL** — Must
  - Given `EXTRA_TEXT` contains a URL and some other words
  - When he shares it
  - Then US-01 stores the link, and the leftover words are kept as a note, the same way as US-01
  - And the title uses the same order as US-01 and US-40: share subject, then a title he typed, then the page title or the file name, then leftover text, then host and path (host only when the link is token-looking, US-40)
- **Scenario: the share has a subject** — Must
  - Given `EXTRA_SUBJECT` is present
  - When the item is stored
  - Then that subject is the title
- **Scenario: a long auto-title** — Must
  - Given he did not supply a subject and the first line is longer than 120 characters
  - When the title is filled
  - Then the title is the first 120 characters and the full text stays in the body
- **Scenario: whitespace** — Must
  - Given the text is only whitespace
  - When he shares it
  - Then no item is stored and he sees that nothing was saved

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Confirmation text is "Saved to Lagoon".
- No placeholder sentences.

## Dependencies and risks

- A body-only note he types himself uses the same 120-character title rule (US-05).

## Implementation notes

Share intent `text/plain`. Not a design spec.
