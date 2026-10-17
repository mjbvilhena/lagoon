# US-64 Share or open an item in another app

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> Epic E3 includes sharing out as a Should because Miguel approved `docs/product/amendments/2026-10-17-security-delegation.md` on 2026-10-17 at 15:55 IST. That approval is the delegation. These criteria stay proposed.

**MoSCoW:** Should. Proposed, pending Miguel's approval. Not a status of Approved.

## Title

Share or open an item in another app.

## User story

**As a** person named Miguel
**I want** to send an attachment, a URL, or the note text to another app
**So that** I can use the item outside Lagoon without handing over the vault

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated sharing out to his bots. He did not decide it. These criteria are the bots' proposal.
- Domain constraints: `get_domain_consultant("capture")` and `get_domain_consultant("vault")` found no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: sharing the database, the vault key, or snapshot internals. Key transport (US-74).

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: share an attachment, a URL, or note text** — Should
  - Given an ordinary item has an attachment, a source URL, or note text
  - When he chooses Share or Open with
  - Then he can send that attachment, that URL, or that note text
- **Scenario: the other app receives a stream** — Should
  - Given he shares an attachment
  - When the other app reads it
  - Then the bytes come from a non-exported FileProvider, with a one-time read grant, and the URI is not `file://`
- **Scenario: a temp copy** — Should
  - Given a temp copy in `cacheDir/share/` cannot be avoided
  - When the next launch runs, or the wipe job runs
  - Then that copy is gone
- **Scenario: photo location** — Should
  - Given the attachment is a photo that carries GPS
  - When he shares it and he has not asked to keep location
  - Then the shared bytes do not include that GPS
- **Scenario: a sensitive item** — Should
  - Given the item is marked sensitive
  - When he chooses Share
  - Then US-37 unlocks it first and he sees a warning before the share continues
- **Scenario: the vault stays here** — Should
  - Given any share
  - When the sheet is built
  - Then the database, the vault key, and snapshot internals are not among the choices

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Share and Open with have names. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Depends on an approved amendment of E3 before this Should is scheduled. The amendment is proposed, not approved.
- US-35 does not encrypt a temp copy that another app must read. This story wipes that copy instead.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
