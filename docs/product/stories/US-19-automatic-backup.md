# US-19 Keep background sync on unmetered networks

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> The bots' consensus named an Unmetered-only setting for automatic transfer. Miguel removed manual backup. The preference remains for background sync. Not approval.

## Title

Prefer an unmetered network for background sync.

## User story

**As a** person named Miguel
**I want** background sync to wait for an unmetered network unless I turn that preference off
**So that** the vault does not quietly upload on a metered connection

## Background

- Consensus: the automatic transfer setting is Unmetered only, using WorkManager `UNMETERED`.
- There is no Back up now, so this is not a Wi-Fi toggle beside a manual button, and there is no roaming warning on a manual backup.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: choosing the hour of day. A size warning. A last-sync clock.

## Acceptance criteria

- **Scenario: unmetered only is the preference** — Must
  - Given he is signed in on a configured build and the preference is on
  - When the device has only a metered connection
  - Then background sync does not upload
  - And when the device later has an unmetered connection, background sync may run
- **Scenario: preference off** — Must
  - Given he turns Unmetered only off
  - When the device has a metered connection
  - Then background sync may run on that connection
- **Scenario: default** — Must
  - Given a fresh install
  - When he has not changed the preference
  - Then Unmetered only is on
- **Scenario: offline** — Must
  - Given no network
  - When he uses the vault
  - Then local capture and search still work, and sync waits

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- The preference has a text label and a state a screen reader can hear.
- It is not labeled Back up now.

## Dependencies and risks

- Depends on US-18 for a signed-in, configured build.
- Roaming is not a separate warning. A roaming network that is metered is covered by Unmetered only.

## Implementation notes

WorkManager network constraint `UNMETERED` when the preference is on. Not a design spec.
