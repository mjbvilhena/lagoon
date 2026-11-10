# US-90 Check workflow permissions, pull-request input, and pinned actions

> **Approval status: Draft. Not approved.**
> Security criteria approved by mjbvilhena on 2026-11-06.

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
- T-30 names pinned Actions as a proposed control for `.github/workflows/qa.yml` and says that file uses version tags. That sentence is not approval of a pin, and it does not describe `.github/workflows/pages.yml`.
- On master, `qa.yml` has workflow `permissions: contents: read`. Jobs other than CodeQL inherit that set. The CodeQL job replaces it with `actions: read`, `contents: read`, `packages: read`, and `security-events: write` (`.github/workflows/qa.yml` lines 154–158). Its `uses` values are version tags.
- On master, `pages.yml` publishes the mockups. It triggers on push to `master` for `docs/mockups/**`, `.github/workflows/pages.yml`, and `scripts/assemble-pages-site.sh`, and on `workflow_dispatch`. It does not trigger on `pull_request`. Workflow permissions are `contents: read`. The Deploy mockups job replaces that set with `contents: read`, `pages: write`, and `id-token: write` (`.github/workflows/pages.yml` lines 17–34). Its four `uses` values are commit SHAs. Those SHAs match the commented release tags: `actions/checkout` `v6.1.0`, `actions/configure-pages` `v6.0.0`, `actions/upload-pages-artifact` `v5.0.0`, and `actions/deploy-pages` `v5.0.1`. Checked against the Git tag objects on 2026-11-06.
- The `gh-pages` branch, not master, has `.github/workflows/dispatch-pages.yml`. It triggers only on push to `gh-pages`. Workflow permissions are `actions: write` and `contents: read`. The only job inherits that set. It has no `uses` key. It posts `workflow_dispatch` of master `pages.yml` with the pushed commit as `wip_sha`. A master pull request does not contain this file.
- No workflow under this repo's `.github` calls a local action or a reusable workflow. `third_party/**` workflows are not these files.
- A job `permissions` block replaces the workflow set. Inheritance of the workflow set is allowed when that set is the least privilege for every job that does not replace it. A missing `permissions` key at both levels fails, because the default token is not an explicit set.
- Domain constraints: `docs/domain/vault/DOMAIN.md` is approved. Approved by mjbvilhena on 2026-10-20. This story does not add a vault rule.
- Out of scope: replacing actionlint, disabling Pages, changing what the mockup site publishes, a self-hosted runner, and adding a job that checks out a pull request and runs it with a write token. This story does not exempt every deployment workflow. A check of a master commit does not scan a push to `gh-pages`. `dispatch-pages.yml` is checked only when that file is in the commit under review.

## Acceptance criteria

- **Scenario: permissions match the named sets** — Must
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given a workflow file in the commit being checked
  - When the check reads the effective set
  - Then a job with `permissions` uses that block, and a job without one uses the workflow block
  - And neither level having `permissions` fails the check
  - And `.github/workflows/qa.yml` jobs that inherit the workflow set are exactly `contents: read`
  - And the CodeQL job is exactly `actions: read`, `contents: read`, `packages: read`, and `security-events: write`
  - And `.github/workflows/pages.yml` workflow permissions are exactly `contents: read`
  - And its Deploy mockups job is exactly `contents: read`, `pages: write`, and `id-token: write`
  - And `pages: write` or `id-token: write` on a workflow that also triggers on `pull_request` or `pull_request_target` fails the check
  - And the gh-pages file `.github/workflows/dispatch-pages.yml`, when that file is the one being checked, is exactly `actions: write` and `contents: read`, inherited from the workflow block, and its only trigger is push to `gh-pages`
  - And `actions: write` on any other file, or on a `pull_request` or `pull_request_target` trigger, fails the check
  - And any permission outside the set named for that file fails the check
- **Scenario: untrusted pull-request input** — Must
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given a workflow
  - When the check runs
  - Then a `pull_request_target` trigger fails the job
  - And a `run` step whose text contains `${{ github.event.pull_request.title }}`, `${{ github.event.pull_request.body }}`, or a pull request head ref fails the job
  - And a `run` step whose text contains `${{ github.event.inputs.wip_sha }}` fails the job
  - And `pages.yml` may pass `github.event.inputs.wip_sha` through an environment variable, and the step must reject a non-empty value that is not 40 hexadecimal characters before that value is a checkout ref
  - And the check does not execute the pull request's code
- **Scenario: actions are pinned** — Must
  - Security criteria approved by mjbvilhena on 2026-11-06.
  - Given a remote `uses` value (`owner/name@ref`)
  - When the check runs
  - Then a tag or a branch fails the job
  - And a 40-character commit SHA passes only when it is the commit of a published release tag of that action
  - And the SHA is taken from the release the maintainer published, not from memory
  - And a local `uses` path is not a SHA pin. The called file is checked under these same permission rules. This repo has no local `uses` path
  - And a workflow with no `uses` key, which is `dispatch-pages.yml` today, does not fail the pin rule for lacking an action
  - And the four SHAs already in `pages.yml` satisfy this rule for those commented tags
  - And the version tags in `qa.yml` do not satisfy this rule. This draft does not replace them
- **Scenario: the checker fails closed** — Must
  - Given the checker cannot read a workflow or exits with an error
  - When the check runs
  - Then the job fails
- **Scenario: the job inherits contents read** — Must
  - Security criteria approved by mjbvilhena on 2026-11-10.
  - Given the `Workflow security` job is to be added to `.github/workflows/qa.yml`
  - When the job is written
  - Then it adds no `permissions` block
  - And it inherits that workflow's `contents: read` set
  - And a job that needs any other permission needs a new approval from mjbvilhena

## Analytics and observability (optional)

Not applicable. No analytics.

## Design and a11y notes

- Not a screen story. No placeholder sentences.
- The candidate checker is `zizmorcore/zizmor` release `v1.32.0`, published 2026-11-02. It is not actionlint. The commit pin is chosen when this story is approved, from that release's Git tag.
- Security criteria approved by mjbvilhena on 2026-11-10. The job name is `Workflow security`. It is not `Workflow lint`. The job is to be added to `.github/workflows/qa.yml`. It adds no `permissions` block, so it inherits that workflow's `contents: read` set, the same set the other ordinary jobs inherit. A job that needs any other permission needs a new approval from mjbvilhena. It is not a separate workflow file.
- The `Dependency scan` and `Workflow security` jobs are to be added to `.github/workflows/qa.yml` with release commit SHAs from the start. A version tag on those new jobs does not meet the pin scenario.
- It is not one of the seven required checks. The job failing is not the same thing as a merge failing. A merge stays open until ruleset 25303010 lists the name `Workflow security`. This story does not change the ruleset. Whole-story approval would not change it either. It does not broaden E6.

## Dependencies and risks

- Security criteria approved by mjbvilhena on 2026-11-06. The story stays Draft. Whole-story approval is not recorded. Adding the job to `qa.yml` and listing `Workflow security` on ruleset 25303010 are two different steps.
- Amendment approved by mjbvilhena on 2026-11-10. An approved US-90 story is enough to add the `Workflow security` job to the repository-setup mapping. A change to ruleset 25303010 still needs approval by mjbvilhena. The story stays Draft. This sentence does not approve the story, does not add the job, and does not change the ruleset.
- Replacing the version tags in `.github/workflows/qa.yml` with release commits is part of meeting the pin scenario. It is not done in this draft. `pages.yml` is already pinned. `dispatch-pages.yml` has no `uses` key.

## Implementation notes

Not a design spec. Do not pin actions and do not add the job from this draft.
