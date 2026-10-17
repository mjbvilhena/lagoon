# US-79 Skip unsafe link previews

> **Approval status: Draft. Not approved.**
> Sign-off on file: none.
> Story approval has not been recorded. Epic approval is not story approval.
> **Proposed: pending Miguel's approval.** The criteria below are a bot draft. They are not his decision.
> Whether to skip token-looking URLs stays open. This story does not decide it.

**MoSCoW:** Proposed Must. Pending Miguel's approval. Not a status of Approved.

## Title

Skip unsafe link previews.

## User story

**As a** person named Miguel
**I want** a preview fetch to skip addresses that are not public web pages
**So that** saving a link does not call a private or local host

## Background

- On 2026-10-17 at 14:56 IST Miguel (`mjbvilhena`) delegated link-preview skipping to his bots. He did not decide it.
- Domain constraints: no `DOMAIN.md`. No MUST/NEVER rules. Do not invent any.
- Out of scope: token-looking URLs. Household, Mikey's Assistant, and Dev Bot recommended skipping them. Leisure recommended fetching by default with a global toggle. Miguel has not chosen. This story does not.
- Out of scope: the offline snapshot viewer making its own network calls. It does not.

## Acceptance criteria

> **Proposed: pending Miguel's approval.**

- **Scenario: only http and https** — Must
  - Given the URL scheme is not `http` or `https`
  - When a preview would be fetched
  - Then no fetch is made
- **Scenario: hosts that are skipped** — Must
  - Given the host is an IP literal, a private, loopback, link-local, or CGNAT address, a single-label host, or a host ending in `.local`, `.lan`, or `.internal`
  - When the check runs after DNS, and again on every redirect
  - Then no fetch is made to that host
- **Scenario: a sensitive item** — Must
  - Given the item is marked sensitive
  - When a preview would be fetched
  - Then no fetch is made
- **Scenario: what the fetch sends** — Must
  - Given a preview fetch is allowed
  - When it runs
  - Then it sends no cookies, no `Referer`, and no authorization header, and it does not run JavaScript
- **Scenario: limits** — Must
  - Given a preview fetch is allowed
  - When it runs
  - Then it follows at most 5 redirects, it times out, and it stops at 10 MiB
  - And cleartext traffic is off
- **Scenario: the viewer** — Must
  - Given he reads a stored snapshot
  - When the viewer is open
  - Then the viewer makes no network request
- **Scenario: he asks for one** — Must
  - Given a preview was skipped
  - When he chooses "Fetch preview" on that item
  - Then one fetch is attempted for that item, still under the host rules above

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- "Fetch preview" has a name. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Open for Miguel: token-looking URLs. Not decided in these criteria.
- US-08 still fetches an ordinary public page. This story is the skip list.

## Implementation notes

Not a design spec. Do not implement from this file until Miguel approves the proposal.
