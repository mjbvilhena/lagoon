# US-90 Check workflow permissions, pull-request input, and pinned actions

> **Approval status: Draft. Not approved.**
> Security criteria: Proposed. Needs approval by mjbvilhena.

**Epic:** E6.

**MoSCoW:** Must. Proposed for v1. Not approved.

## Title

Check workflow permissions, pull-request input, and pinned actions.

## User story

**As a** person named Miguel
**I want** a workflow change to fail when the token is too broad, a pull request can inject a command, or an action is not pinned
**So that** actionlint syntax is not the only check on the workflows

## Background

- `Workflow lint` runs actionlint 1.7.12. That is a syntax check. It does not decide token scope, `pull_request_target`, or immutable pins.
- T-30 says Actions are pinned by commit SHA only as a proposed control, and that the workflow uses version tags. That sentence is not approval of a pin.
- The workflows today declare `permissions: contents: read`, except the CodeQL job, which also has `security-events: write`. No workflow uses `pull_request_target`.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. This story does not add a vault rule.
- Out of scope: replacing actionlint, a self-hosted runner, and any job that checks out a pull request and runs it with a write token.

## Acceptance criteria

- **Scenario: permissions stay least privilege** — Must
  - Security criteria: Proposed. Needs approval by mjbvilhena.
  - Given a workflow file
  - When the check runs
  - Then every job has an explicit `permissions` block
  - And a permission other than `contents: read` fails the job, except the CodeQL job's `security-events: write`
  - And a missing `permissions` block fails the job
- **Scenario: untrusted pull-request input** — Must
  - Security criteria: Proposed. Needs approval by mjbvilhena.
  - Given a workflow
  - When the check runs
  - Then a `pull_request_target` trigger fails the job
  - And a `run` step that interpolates `github.event.pull_request.title`, `github.event.pull_request.body`, or a pull request head ref fails the job
  - And the check does not execute the pull request's code
- **Scenario: actions are pinned** — Must
  - Security criteria: Proposed. Needs approval by mjbvilhena.
  - Given a `uses` value
  - When the check runs
  - Then a floating tag or a branch fails the job
  - And a 40-character commit SHA passes only when it is the commit of a release tag on that action's repository
  - And the SHA is taken from the release the maintainer published, not from memory
- **Scenario: the checker fails closed** — Must
  - Given the checker cannot read a workflow or exits with an error
  - When the check runs
  - Then the job fails

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.
- The candidate checker is `zizmorcore/zizmor` release `v1.32.0`, published 2026-11-02. It is not actionlint. The commit pin is chosen when this story is approved, from that release's Git tag.
- The job name is `Workflow security`. It is not `Workflow lint` and it is not one of the seven required checks. It does not block a merge until ruleset 25303010 lists that exact name. This story does not change the ruleset.

## Dependencies and risks

- Blocked on approval of this story and of the security criteria above by mjbvilhena.
- Pinning the actions already in `.github/workflows/qa.yml` is part of meeting the pin scenario. It is not done in this draft.

## Implementation notes

Not a design spec. Do not pin actions and do not add the job from this draft.
