# US-65 Add a photo from the camera

> **Approval status: Approved.**
> Approved on 2026-10-18 by Scout, Mikey's Dev Bot, Mikey's Assistant, Mikey's Household Assistant and Mikey's Leisure Assistant on behalf of mjbvilhena.

**Epic:** E1.

**MoSCoW:** Should.

## Title

Add a photo from the camera.

## User story

**As a** person named Miguel
**I want** to start a new item from the camera
**So that** I can keep a page without a share from another app

## Background

- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. ADR rules in it are MUST. Do not invent further rules.
- The photo comes from the system camera through `ACTION_IMAGE_CAPTURE`. Lagoon does not request `CAMERA`. The US-80 allowlist stays as it is.
- Out of scope: the share sheet and the document picker (US-03, US-32). Declaring `CAMERA` in the Lagoon manifest.

## Acceptance criteria

- **Scenario: a new item** — Must if the story is built
  - Given he starts a capture with `ACTION_IMAGE_CAPTURE`
  - When Lagoon passes `EXTRA_OUTPUT` and the system camera returns that photo and he confirms
  - Then the full-resolution photo is written through `EXTRA_OUTPUT` to a non-exported FileProvider content URI in Lagoon's no-backup cache, with a one-time write grant
  - And Lagoon does not write a copy into the shared gallery
  - And a new item is stored with that photo as its attachment
  - And the photo is encrypted into the vault (US-35), the plaintext file is deleted, and the URI grant is revoked
  - And the Lagoon manifest does not declare `CAMERA`
- **Scenario: cancel** — Must if the story is built
  - Given he starts `ACTION_IMAGE_CAPTURE`
  - When he cancels, the camera returns nothing, or no camera app can handle the intent
  - Then no item is stored, and the empty or partial file is deleted
  - And a leftover plaintext file is deleted on the next launch
- **Scenario: multi-page** — Should
  - Given he takes several photos that way into that new item
  - When he saves them
  - Then they are attachments in that order
  - And Lagoon still does not declare `CAMERA`

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- No placeholder sentences in the shipped UI.
- Exact layout waits for the UX artefact, which does not exist.

## Dependencies and risks

- Does not block US-32.
- The system camera app may keep its own gallery copy, and Lagoon cannot prevent that.

## Implementation notes

Not a design spec.
