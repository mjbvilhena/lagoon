# US-79 Skip unsafe link previews

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.
> Security criteria approved by mjbvilhena on 2026-10-18.

**Epic:** E2.

**MoSCoW:** Must.

## Title

Skip unsafe link previews.

## User story

**As a** person named Miguel
**I want** a preview fetch to skip addresses that are not public web pages
**So that** saving a link does not call a private or local host

## Background

- The vault does not auto-fetch a token-looking URL. It shows a foggy placeholder and asks whether to enable the preview for that link. A fetch happens only after he says yes.
- The host rules and the detector are the criteria below.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. Its MUST rules apply. Do not invent further rules.
- Out of scope: the offline snapshot viewer making its own network calls. It does not.

## Acceptance criteria

- **Scenario: the prompt** — Must
  - Given a saved link is token-looking and no preview has been fetched
  - When the item is shown
  - Then no fetch has been made, a foggy placeholder image stands in place of the preview, and he is asked whether to enable the preview for that link
- **Scenario: he says yes** — Must
  - Given that prompt is showing
  - When he says yes
  - Then one fetch runs, and it follows the safe-fetch rules in the scenarios below
- **Scenario: he says no** — Must
  - Given that prompt is showing
  - When he says no
  - Then no fetch is made, the foggy placeholder stays, and he can enable the preview later
- **Scenario: no answer** — Must
  - Given that prompt is showing
  - When he leaves it without answering
  - Then no fetch is made and the foggy placeholder stays

- **Scenario: only http and https** — Must
  - Given the URL scheme is not `http` or `https`
  - When a preview would be fetched
  - Then no fetch is made
- **Scenario: what counts as token-looking** — Must
  - Given a URL table in the test
  - When the rule runs
  - Then a URL is token-looking if any one of these is true. Parameter names match without case, including the same names in the fragment (`#access_token=`): a query or fragment parameter named `token`, `access_token`, `id_token`, `auth`, `key`, `api_key`, `sig`, `signature`, `code`, `otp`, `session`, `sid`, `secret`, `password`, `X-Amz-Signature`, `X-Amz-Credential`, or `X-Goog-Signature`; user info of the form `user:pass@`; a value containing `eyJ`; a path segment or parameter value of at least 24 characters that mixes letters and digits and is not hyphen-separated words; or a path segment `reset`, `verify`, `magic`, `confirm`, `unsubscribe`, `login`, `invite`, `activate`, `signin`, `callback`, or `oauth`
  - And `https://example.com/notes/1` is not token-looking
  - And `https://example.com/how-to-make-sourdough-bread-at-home` is not token-looking
  - And `https://example.com/how-to-fix-a-leaking-tap-2026` is not token-looking
  - And the same rule blocks enrichment (US-08) and a snapshot (US-33) until he confirms
  - And enrichment waits until the quick-add row has closed
- **Scenario: hosts that are skipped** — Must
  - Given the host is an IP literal, a private, loopback, link-local, or CGNAT address, `0.0.0.0`, an IPv4 multicast address, an IPv6 address in `fc00::/7` or link-local `fe80::/10` or multicast `ff00::/8`, an IPv4-mapped form of any skipped IPv4 address, a single-label host, or a host ending in `.local`, `.lan`, or `.internal`
  - When the check runs after DNS, on the connected peer address, and again on every redirect
  - Then no fetch is made to that host
  - And a redirect to a scheme other than `http` or `https` is not followed
- **Scenario: a sensitive item** — Must
  - Given the item is marked sensitive
  - When a preview would be fetched
  - Then no fetch is made
- **Scenario: what the fetch sends** — Must
  - Given a preview fetch is allowed
  - When it runs
  - Then it sends no cookies, no `Referer`, and no authorization header, it does not run JavaScript, and the User-Agent is the fixed string `LagoonPreview` with no device id, account, or install id
- **Scenario: limits** — Must
  - Given a preview fetch is allowed
  - When it runs
  - Then it follows at most 5 redirects, the whole call stops at 15 seconds, and it stops at 10 MiB of decompressed bytes
  - And an `http` URL is fetched as `https`. Cleartext stays off, so a failed upgrade is not fetched as `http`
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

- The prompt asks whether to enable the preview for that link. The foggy image is not a sentence of placeholder copy.
- "Fetch preview" has a name. No placeholder sentences.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Token-looking URLs are not auto-fetched. The host rules are the scenarios below.
- US-08 still fetches an ordinary public page. US-10 does not draw a preview on the home row. US-11 shows the foggy placeholder when that is the stored preview.

## Implementation notes

Not a design spec. The story is Draft. Do not implement from this file.
