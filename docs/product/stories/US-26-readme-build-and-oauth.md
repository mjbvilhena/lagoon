# US-26 Follow written steps to build, sideload, and create the OAuth client

> **Approval status: Draft. Not approved.**

**Epic:** E6.

**MoSCoW:** Must. v1. The root README stays the app overview. These steps live in `docs/` and the README links there, once the app exists.

## Title

Follow written steps to build, sideload, and create the OAuth client.

## User story

**As a** person named Miguel
**I want** written steps to build, sideload, and create the OAuth client
**So that** I can install a build and turn sync on without guessing

## Background

- There is no app yet, so those steps are not in the tree. That absence is correct until implementation.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- Out of scope: documenting a Back up now or Restore button.

## Acceptance criteria

- **Scenario: build and sideload steps exist** — Must
  - Given a release build exists
  - When he follows the docs
  - Then they name the commands to build and sideload the APK
- **Scenario: OAuth clients** — Must
  - Given the OAuth section
  - When he reads it
  - Then it names an Android client with the package `com.mjbvilhena.lagoon` and the release SHA-1, a separate Android client for `com.mjbvilhena.lagoon.debug` with the debug SHA-1, and a Web client ID used as `serverClientId` when Credential Manager needs one
  - And the root README names that debug client in one line and points at these steps
- **Scenario: consent screen** — Must
  - Given the OAuth section
  - When he follows the client steps
  - Then it includes a step to set the OAuth consent screen to "In production"
  - And it states, as Google's documented rule, that an external consent screen left in Testing issues a refresh token that expires in 7 days, unless the only scopes are a subset of name, email, and profile (`userinfo.email`, `userinfo.profile`, `openid`, or their OpenID Connect equivalents). Source: [Refresh token expiration](https://developers.google.com/identity/protocols/oauth2#expiration), last updated 2026-10-16 UTC. Drive app data is not one of those basic scopes, so a Testing client hits that 7-day expiry
- **Scenario: missing client ID** — Must
  - Given the client ID is absent
  - When he reads the docs
  - Then they say the app still builds, installs, and works offline, and that Settings explains sync is not configured
- **Scenario: no secret in the docs** — Must
  - Given those pages
  - When they are read
  - Then they contain no client secret, keystore password, or API key

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a UI story.

## Dependencies and risks

- SHA-1 values are produced by the signing config. They are unknown until a keystore exists.

## Implementation notes

Not a design spec.
