# US-64 Share or open an item in another app

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> Epic E3 includes sharing out as a Should because Miguel approved `docs/product/amendments/2026-10-17-security-delegation.md` on 2026-10-17 at 15:55 IST. That approval is the delegation. These criteria stay proposed.

**Epic:** E3. Not an approval.

**MoSCoW:** Should. Proposed, pending Miguel's approval. Not a status of Approved.

## Title

Share or open an item in another app.

## User story

**As a** person named Miguel
**I want** to send an attachment, a URL, or the body text to another app
**So that** I can use the item outside Lagoon without handing over the vault

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated sharing out to his bots. He did not decide it. These criteria are the bots' proposal.
- Domain constraints: `get_domain_consultant("capture")` and `get_domain_consultant("vault")` found no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: sharing the database, the vault key, or snapshot internals. Key transport (US-74).

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: share an attachment, a URL, or the body** — Should
  - Given an ordinary item has an attachment, a source URL, or body text
  - When he chooses Share and does not pick a part
  - Then the share uses attachments first, then the URL, then the body text
  - And "note text" in this story means that body, not the "why it matters" note
  - And he can instead send one attachment, the URL, or the body
- **Scenario: every attachment** — Should
  - Given an ordinary item has more than one attachment
  - When he chooses to share all of them
  - Then one send includes every attachment
- **Scenario: the other app receives a stream** — Should
  - Given he shares an attachment
  - When the other app reads it
  - Then the bytes are a `ParcelFileDescriptor` pipe from a non-exported FileProvider, with a one-time read grant, the URI is not `file://`, and no plaintext copy is written for that share
- **Scenario: a temp copy** — Should
  - Given a pipe cannot be used and a temp copy cannot be avoided
  - When the other app returns, or 15 minutes pass, or the next launch runs
  - Then that copy is gone, and the URI grant is revoked when the copy is wiped
- **Scenario: a link** — Should
  - Given he opens a source URL
  - When Open with runs
  - Then it uses Custom Tabs or `ACTION_VIEW`, the scheme is `http` or `https` only, and no WebView loads the page
- **Scenario: photo location** — Should
  - Given the attachment is a photo that carries location or device fields in EXIF or XMP
  - When he shares it and he has not asked to keep location
  - Then the shared bytes do not include those EXIF or XMP location fields or device fields
- **Scenario: a sensitive item** — Should
  - Given the item is marked sensitive
  - When he chooses Share or Open with
  - Then US-37 unlocks it first and he sees a warning before either one continues
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

- Epic E3 already includes this Should. These criteria stay proposed.
- US-35 does not encrypt a temp copy that another app must read. This story wipes that copy instead. The 15 minute bound is a draft so the wipe is testable. It is not his approval.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
