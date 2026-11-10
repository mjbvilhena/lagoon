# Repository setup

> **Approval status: Draft. Not approved.**
> The checks below were added because the 2026-10-17 request said to set the repo up per the harness. That sentence is not a named approval of this document, and it is not product-spec approval.
> Approver required for this setup note: `mjbvilhena`.
> Sign-off on file: none.

Structured from `get_sdlc_template("repository setup")` and `get_definition_of_done("repository setup")`.

## Metadata

| Field | Value |
| --- | --- |
| Target Repository | `github.com/mjbvilhena/lagoon` (public). Default branch `master`. |
| Primary Tech Stack | Kotlin and Gradle are in the tree for the US-80 manifest slice. Jetpack Compose is named and is not in this slice. This note stays Draft. |
| CI/CD System | GitHub Actions. Required checks are `.github/workflows/qa.yml`. `.github/workflows/pages.yml` publishes the mockups and is not a required check. |

## Purpose Summary

This repository will hold Miguel's personal knowledge vault: an on-device Android app and the SDLC documents that precede it. The content expected over time is product and design markdown, then a Kotlin/Compose app, its tests, and release notes. The US-80 manifest slice is in the tree. This note stays Draft.

## Where things live

The root `README.md` only describes the app. These paths are the rest of the notes:

| Path | What it is |
| --- | --- |
| `docs/product/vision.md` | Current vision. Restates the approved specification, epics E1–E9 and E11, and the approved amendment. |
| `docs/product/specification.md` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-22. Amendment approved by mjbvilhena on 2026-10-24. Amendment approved by mjbvilhena on 2026-10-27. Amendment approved by mjbvilhena on 2026-10-28. Amendment approved by mjbvilhena on 2026-10-31. Amendment approved by mjbvilhena on 2026-11-03. |
| `docs/product/epics/` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18 for E1, E3, E4, E8, E9, and E11. E10 is Later, not in v1. |
| `docs/product/stories/` | Story files and `docs/product/stories/INDEX.md`. The approval line on each file is the status. |
| `docs/product/backlog.md` | Draft backlog |
| `docs/sdlc/pipeline-state.md` | Current gate. US-80 has started and is not Done. |
| `docs/sdlc/harness-install.md` | Harness pin, installer, MCP command |
| `docs/sdlc/repository-setup.md` | Draft repository checks |
| `.cursor/commands/` | Installed harness skills and rules |
| `third_party/ai-sdlc-harness` | Harness submodule |
| `docs/mockups/` | Static UX review prototype. `docs/technical_design/ux-design.md` is the source of truth. |
| `.github/workflows/pages.yml` | Publishes that prototype. Not one of the seven required checks. |

## Stack mapping

Markdown, secret scanning, and workflow lint follow leisure-orchestrator. The Android and Kotlin jobs were added because the 2026-10-17 GitHub request named them. They compile when a Gradle project exists, and `settings.gradle.kts` is in the tree. Bash and Python checks were not added.

| Content expected | Proposed check | In this repo |
| --- | --- | --- |
| Markdown documentation | `markdownlint` | Job `Markdown lint` in `.github/workflows/qa.yml` |
| Any repo | `gitleaks` and `CODEOWNERS` | Job `Secret scan` in `.github/workflows/qa.yml`, `.github/CODEOWNERS` |
| GitHub Actions YAML | `actionlint` 1.7.12 | Job `Workflow lint` |
| Bash scripts | `bats`, `shellcheck` | Not configured. This product repo is not a Bash project. The harness submodule has its own checks. |
| Python | `bandit` and pytest | Not configured. Python is only the harness MCP server inside the submodule. |
| Kotlin / Gradle | Build, Android lint, Kotlin style, CodeQL | Jobs `Build and test`, `Android lint`, `Kotlin style`, and `CodeQL`. A Gradle project is in the tree for the US-80 manifest slice, so those jobs run the real tasks. It was not added to make the jobs pass before implementation. |

The workflow matches `mjbvilhena/leisure-orchestrator` `.github/workflows/qa.yml` on triggers and permissions: `push` to `master`, `pull_request`, `workflow_dispatch`, `permissions: contents: read`, `actions/checkout@v6`. The older Origin-era workflows `.github/workflows/secret-scan.yml` and `.github/workflows/markdownlint.yml` are removed so those checks are not duplicated.

Secret scan does not use `gitleaks/gitleaks-action`. That action scans `<first-pushed-commit>^..<head>`, which fails when a push includes the root commit. This repo installs gitleaks 8.30.1 and runs `gitleaks git` on the full history. `GITLEAKS_LICENSE` is required for organization repositories that use the action. It is not required here.

## Configurations

### Security

Secret scanning is the `Secret scan` job. It checks out full history (`fetch-depth: 0`) and runs gitleaks 8.30.1 (`gitleaks git`) over that history. Store tokens in GitHub Actions secrets. Do not commit them. `GITHUB_TOKEN` is provided by GitHub for other Actions uses. This job does not need it, and it does not need `GITLEAKS_LICENSE`. `.gitignore` excludes `local.properties`, `*.jks`, `*.keystore`, `keystore.properties`, `google-services.json`, and `.env*` except `.env.example`, plus the signing and OAuth property files already listed.

SAST for Python was not added. There is no product Python package.

### Linting & Formatting

`.github/workflows/qa.yml` job `Markdown lint` uses `DavidAnson/markdownlint-cli2-action@v24` and `.markdownlint-cli2.jsonc`. Line length (`MD013`) and ordered-list prefix (`MD029`) are off, matching leisure-orchestrator. `.cursor/**`, `.agents/**`, and `third_party/**` are ignored so slash commands and the harness submodule are not treated as this product's docs. `.markdownlint-cli2.jsonc` is the only Markdown lint config in this repo.

No shellcheck, eslint, or ruff project exists here.

From the repo root, the same Markdown rules CI uses:

```bash
npx --yes markdownlint-cli2
```

Read `docs/sdlc/pipeline-state.md` before editing product docs or writing code. From the repo root, the Gradle tasks those jobs run are `./gradlew assembleDebug testDebugUnitTest`, `./gradlew lintDebug`, and `./gradlew ktlintCheck`.

### Harness knowledge server

`sdlc-conductor` on 2026-10-17: this bootstrap is repository setup. It does not advance the product pipeline, and it is not a skip of story approval, design, or implementation.

The Cursor MCP entry `.cursor/mcp.json` starts `scripts/sdlc-knowledge-mcp.sh`. That script needs a virtualenv at `third_party/ai-sdlc-harness/mcp-server/venv`. The venv is not committed. When it is missing, the wrapper runs `scripts/bootstrap-sdlc-mcp.sh`, which creates the venv and installs `third_party/ai-sdlc-harness/mcp-server/requirements.txt`. If Python, the `python3-venv` package, the submodule, or pip fails, the script exits 1 with a `sdlc-knowledge:` line and the server does not start.

```bash
git submodule update --init third_party/ai-sdlc-harness
scripts/bootstrap-sdlc-mcp.sh
```

Skills used for this note: `sdlc-conductor`, then `sdlc-setup-repository` with `get_sdlc_template("repository setup")` and `get_definition_of_done("repository setup")`. The MCP process was not running, so those calls were made by importing `third_party/ai-sdlc-harness/mcp-server/src/server.py`. No new CI job was added. Bash is not the product language, and the existing note that shellcheck is not configured still stands. Secret scan, Markdown lint, and CODEOWNERS are unchanged.

### Testing

`Build and test` runs `./gradlew assembleDebug testDebugUnitTest` after wrapper validation when `settings.gradle` or `settings.gradle.kts` exists (Temurin 17, `gradle/actions/setup-gradle`). `Android lint` runs `lintDebug`. `Kotlin style` runs `ktlintCheck` or `detekt`. `CodeQL` analyzes Java/Kotlin by tracing `./gradlew assembleDebug`. `build-mode: none` saw no Kotlin once the project existed. Those tasks run for the US-80 manifest slice. This note stays Draft.

US-88, US-89, US-90, and US-91 are approved as artefacts. Implementation is not Done. It covers no ruleset change and no vulnerability exception. Security criteria approved by mjbvilhena on 2026-11-06. The E6 amendment, TP-33 through TP-36, and T-39 through T-42 are amendments. Amendment approved by mjbvilhena on 2026-11-06. This setup note stays Draft. No job for those stories is in `.github/workflows/qa.yml`. `Dependency scan` and `Workflow security` are to be added to `.github/workflows/qa.yml`. `Dependency scan` and `Workflow security` are not required check names. The `CodeQL` job does not fail on finding severity. The release manifest test does not yet assert `android:debuggable`. Signature verification stays US-81. On 2026-11-06 the GitHub API reported Dependabot alerts disabled for this repository. That is why US-88 does not use Dependabot. US-90 does not treat every workflow as `contents: read` plus CodeQL `security-events: write`. `pages.yml` Deploy mockups is `contents: read`, `pages: write`, and `id-token: write`, and its actions are already release SHAs. `qa.yml` still uses version tags, and its CodeQL job also has `actions: read` and `packages: read`. The gh-pages file `.github/workflows/dispatch-pages.yml` is `actions: write` and `contents: read` and has no `uses` key. Those sets are the approved allowance in US-90. They are not an exemption for every deploy workflow. The checks are not implemented. A job to be added to `qa.yml` and a name on ruleset 25303010 are different steps. This note does not list `Dependency scan` or `Workflow security` as required checks, and it does not broaden E6. A master check does not scan a push to `gh-pages`. The approved US-89 fail-closed scenario says a missing SARIF file fails the CodeQL job. The live workflow still exits 0 when `settings.gradle` and `settings.gradle.kts` are absent, and that path produces no SARIF. That live exit is not a successful result under the criterion. The workflow file is not changed here. Security criteria approved by mjbvilhena on 2026-11-10. US-88 fails a finding with neither a CVSS v3 score nor a database severity. The OSV id and exact resolved module version exception schema is unchanged. No vulnerability exception line is approved. US-89 has no CodeQL exception path. Amendment approved by mjbvilhena on 2026-11-10 for TP-33 and T-39. This note stays Draft. No job was added. Security criteria approved by mjbvilhena on 2026-11-10. Those jobs add no `permissions` block and inherit `contents: read`. A job that needs any other permission needs a new approval from mjbvilhena. Amendment approved by mjbvilhena on 2026-11-10. Approved US-88 and US-90 stories are enough to add those jobs to this mapping. A change to ruleset 25303010 still needs approval by mjbvilhena. Status-only update. mjbvilhena said on 2026-11-10 that this needs no approval. US-88, US-89, US-90, and US-91 are approved, as this paragraph already says. This note does not approve the stories and does not change the ruleset. Security criteria approved by mjbvilhena on 2026-11-10. With no Gradle project there is no SARIF, and the CodeQL check fails.

### Ownership

`.github/CODEOWNERS` sets `* @mjbvilhena`. That assigns a default reviewer. It does not approve the product spec, epics, or stories.

## Branch protection

The GitHub default branch is `master`. The initial import of this history is the one direct push. After that, changes on `github.com/mjbvilhena/lagoon` go through pull requests into `master`.

The active ruleset is Protect master (id 25303010, created 2026-10-17, enforcement active, target `refs/heads/master`). Read from the GitHub ruleset API on 2026-10-18:

- Require a pull request before merging. One approving review. Code owner review is required (`.github/CODEOWNERS`). A new push dismisses stale reviews. Unattributed changes need an extra approval.
- Allowed merge methods are merge, squash, and rebase.
- Required status checks, and the branch must be up to date: `Secret scan`, `Markdown lint`, `Workflow lint`, `Build and test`, `Android lint`, `CodeQL`, and `Kotlin style`.
- Block force-pushes and branch deletion.
- The ruleset has no bypass actors.

A green check is not product approval and is not permission to start implementation. This setup note stays Draft.

## Checklist

- [x] Purpose and content types have been identified (`README.md`, this file).
- [x] Universal secret scanning is configured (job `Secret scan`).
- [x] Markdown lint and workflow lint are configured. Android and Kotlin jobs run the real Gradle tasks because `settings.gradle.kts` is in the tree. The workflow still no-ops when `settings.gradle` and `settings.gradle.kts` are absent.
- [x] Default code ownership is established (`.github/CODEOWNERS`).
- [x] CI uses `actions/checkout@v6`, gitleaks 8.30.1 (`gitleaks git` on full history), and `markdownlint-cli2-action@v24`. Checkout and Markdown lint match leisure-orchestrator. Secret scan does not use `gitleaks-action`.
- [x] `.gitignore` covers signing secrets, `google-services.json`, `local.properties`, and `.env*` except `.env.example`.
- [x] Branch protection policy for `master` is documented above. Pull requests are required after the initial import.
- [ ] Named approval of this setup note by `mjbvilhena`.

## Anti-patterns avoided

- The Gradle project was not added while this repository was docs-only. It is in the tree for the US-80 manifest slice. The workflow still no-ops when `settings.gradle` and `settings.gradle.kts` are absent.
- No end-to-end emulator workflow was added. The US-80 manifest slice is in the tree. There is still no vault UI.
