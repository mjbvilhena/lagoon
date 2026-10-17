# US-37 Lock sensitive items with a biometric

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His decision: fingerprint or biometric lock on items marked sensitive, tied to encryption. Not approval. App-wide unlock is a separate open question.

## Title

Require a biometric to open a sensitive item.

## User story

**As a** person named Miguel
**I want** an item I mark sensitive to stay closed until a fingerprint or other biometric succeeds
**So that** opening that item is a deliberate step on top of encryption

## Background

- He decided this is in v1 and tied it to the encryption work (E8).
- Whether the whole app requires unlock is still open. This story does not lock the app.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: marking every item sensitive by default. A biometric gate on search results' titles. Inventing a PIN because biometrics failed, unless he answers the app-unlock question that way.

## Acceptance criteria

- **Scenario: mark sensitive** — Must
  - Given an ordinary item
  - When he marks it sensitive
  - Then it is stored as sensitive and the mark is visible on the item
- **Scenario: open** — Must
  - Given a sensitive item and a device that can biometrically authenticate
  - When he opens it and the biometric succeeds
  - Then he can read the item
- **Scenario: fail or cancel** — Must
  - Given a sensitive item
  - When he cancels the biometric prompt or it fails
  - Then the item's body, note, and attachments stay hidden
- **Scenario: ordinary items** — Must
  - Given an item he has not marked sensitive
  - When he opens it
  - Then no biometric is required by this story
- **Scenario: app unlock** — Must
  - Given he has not answered whether the app itself must unlock
  - When he opens the vault
  - Then this story does not add an app-wide lock

## Analytics and observability (optional)

Not applicable. No analytics. Biometric success is not an analytics event.

## Design and a11y notes

- The sensitive mark has a text label, not only an icon.
- The system biometric prompt is the platform's. Failure is announced in text.

## Dependencies and risks

- Tied to US-35. A sensitive item's bytes are still encrypted at rest.
- Threat model required before implementation. Not started.
- A device with no biometrics is not given a fallback in this story. That gap sits with his app-unlock question rather than a guessed PIN.

## Implementation notes

Use the platform biometric prompt for items flagged sensitive. Not a design spec.
