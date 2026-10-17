# US-18 Sign in and back up now

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.

## Title

Sign in with Google and back up now.

## User story

**As a** person named Miguel
**I want to** sign in with Google and back up the vault now into the app's private Drive space
**So that** a copy of my database and my files exists off the phone, visible only to this app

## Background

- He asked for Google Sign-In / Credential Manager, the Drive API, and the `drive.appdata` scope, plus Back up now. The backup includes the database and stored images and PDFs.
- Domain constraints: none. No `DOMAIN.md`.
- Out of scope: the unconfigured state (US-17), the schedule (US-19), restore (US-20), and zip (US-21).

## Acceptance criteria

- **Scenario: back up** — Must
  - Given a build whose OAuth client ID is set, and he is signed in, and the vault has a note plus an image or a PDF
  - When he chooses Back up now
  - Then the app writes a backup into the Drive app-data folder that contains the database and those stored files, and Settings shows a last backup time of that run
- **Scenario: not signed in** — Must
  - Given a configured client ID and he is not signed in
  - When he chooses Back up now
  - Then the backup does not pretend to succeed. He can start Google sign-in.
- **Scenario: sign-in fails or he cancels** — Must
  - Given he cancels sign-in or Google returns an error
  - When control returns to the app
  - Then his local items are unchanged and he can see that backup did not run
- **Scenario: the backup is app-data** — Must
  - Given a successful backup
  - When its Drive location is checked
  - Then it is in the app-data folder for `drive.appdata`, not in a folder of his ordinary My Drive that other apps are invited to browse

## Analytics and observability (optional)

Not applicable. No analytics. Sign-in with Google is authentication he asked for, not product analytics.

## Design and a11y notes

- Sign-in, Back up now, and the last backup time are visible as text.
- No placeholder account name.

## Dependencies and risks

- Requires the OAuth client from US-26's procedure and Q2 (application id) plus the release SHA-1.
- Q6: this story's Back up now runs when he asks, on the current network, including mobile data. The Wi-Fi toggle is US-19. If he rejects Q6, this criterion changes.
- Which of Credential Manager and the older Google Sign-In API performs the sign-in is an ADR. He named both. This story does not pick.

## Implementation notes

Do not log tokens. Do not commit the client ID. Packaging of database plus files is what US-25 tests locally. Not a design spec.
