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
| Primary Tech Stack | Markdown product docs now. Kotlin, Jetpack Compose, and Gradle are named for a later app and are not in the tree. |
| CI/CD System | GitHub Actions (`.github/workflows/qa.yml`) |

## Purpose Summary

This repository will hold Miguel's personal knowledge vault: an on-device Android app and the SDLC documents that precede it. The content expected over time is product and design markdown, then a Kotlin/Compose app, its tests, and release notes. No application source is in the tree at this gate.

## Where things live

The root `README.md` only describes the app. These paths are the rest of the notes:

| Path | What it is |
| --- | --- |
| `docs/product/vision.md` | Current vision. Restates the approved specification, epics E1–E9 and E11, and the approved amendment. |
| `docs/product/specification.md` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18. Amendment approved by mjbvilhena on 2026-10-20. Amendment approved by mjbvilhena on 2026-10-22. Amendment approved by mjbvilhena on 2026-10-24. Amendment approved by mjbvilhena on 2026-10-27. |
| `docs/product/epics/` | Approved by mjbvilhena on 2026-10-17. Amendment approved by mjbvilhena on 2026-10-18 for E1, E3, E4, E8, E9, and E11. E10 is Later, not in v1. |
| `docs/product/stories/` | Story files and `docs/product/stories/INDEX.md`. The approval line on each file is the status. |
| `docs/product/backlog.md` | Draft backlog |
| `docs/sdlc/pipeline-state.md` | Current gate and what has not been started |
| `docs/sdlc/harness-install.md` | Harness pin, installer, MCP command |
| `docs/sdlc/repository-setup.md` | Draft repository checks |
| `.cursor/commands/` | Installed harness skills and rules |
| `third_party/ai-sdlc-harness` | Harness submodule |

## Stack mapping

Markdown, secret scanning, and workflow lint follow leisure-orchestrator. The Android and Kotlin jobs were added because the 2026-10-17 GitHub request named them. They do not compile anything until a Gradle project exists. Bash and Python checks were not added.

| Content expected | Proposed check | In this repo |
| --- | --- | --- |
| Markdown documentation | `markdownlint` | Job `Markdown lint` in `.github/workflows/qa.yml` |
| Any repo | `gitleaks` and `CODEOWNERS` | Job `Secret scan` in `.github/workflows/qa.yml`, `.github/CODEOWNERS` |
| GitHub Actions YAML | `actionlint` 1.7.12 | Job `Workflow lint` |
| Bash scripts | `bats`, `shellcheck` | Not configured. This product repo is not a Bash project. The harness submodule has its own checks. |
| Python | `bandit` and pytest | Not configured. Python is only the harness MCP server inside the submodule. |
| Kotlin / Gradle | Build, Android lint, Kotlin style, CodeQL | Jobs `Build and test`, `Android lint`, `Kotlin style`, and `CodeQL`. Each passes with a clear message until `settings.gradle` or `settings.gradle.kts` exists. No Gradle project was added to make them pass. |

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

Read `docs/sdlc/pipeline-state.md` before editing product docs or writing code. Kotlin test commands do not exist yet. There is no app module, Gradle build, or APK in the tree.

### Harness knowledge server

`sdlc-conductor` on 2026-10-17: this bootstrap is repository setup. It does not advance the product pipeline, and it is not a skip of story approval, design, or implementation.

The Cursor MCP entry `.cursor/mcp.json` starts `scripts/sdlc-knowledge-mcp.sh`. That script needs a virtualenv at `third_party/ai-sdlc-harness/mcp-server/venv`. The venv is not committed. When it is missing, the wrapper runs `scripts/bootstrap-sdlc-mcp.sh`, which creates the venv and installs `third_party/ai-sdlc-harness/mcp-server/requirements.txt`. If Python, the `python3-venv` package, the submodule, or pip fails, the script exits 1 with a `sdlc-knowledge:` line and the server does not start.

```bash
git submodule update --init third_party/ai-sdlc-harness
scripts/bootstrap-sdlc-mcp.sh
```

Skills used for this note: `sdlc-conductor`, then `sdlc-setup-repository` with `get_sdlc_template("repository setup")` and `get_definition_of_done("repository setup")`. The MCP process was not running, so those calls were made by importing `third_party/ai-sdlc-harness/mcp-server/src/server.py`. No new CI job was added. Bash is not the product language, and the existing note that shellcheck is not configured still stands. Secret scan, Markdown lint, and CODEOWNERS are unchanged.

### Testing

No product test runner exists yet. `Build and test` will run `./gradlew assembleDebug testDebugUnitTest` after wrapper validation once a Gradle project exists (Temurin 17, `gradle/actions/setup-gradle`). Until then the job exits 0 with an explicit message. `Android lint` will run `lintDebug`. `Kotlin style` will run `ktlintCheck` or `detekt`. `CodeQL` will analyze Java/Kotlin. None of those tasks run today, because there is no Gradle project and implementation has not started.

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
- [x] Markdown lint and workflow lint are configured. Android and Kotlin jobs are present and no-op until Gradle exists.
- [x] Default code ownership is established (`.github/CODEOWNERS`).
- [x] CI uses `actions/checkout@v6`, gitleaks 8.30.1 (`gitleaks git` on full history), and `markdownlint-cli2-action@v24`. Checkout and Markdown lint match leisure-orchestrator. Secret scan does not use `gitleaks-action`.
- [x] `.gitignore` covers signing secrets, `google-services.json`, `local.properties`, and `.env*` except `.env.example`.
- [x] Branch protection policy for `master` is documented above. Pull requests are required after the initial import.
- [ ] Named approval of this setup note by `mjbvilhena`.

## Anti-patterns avoided

- No Gradle project, wrapper, or app module was added so the Android jobs would have something to compile. Those jobs no-op until the project exists.
- No end-to-end emulator workflow was added while there is no app.
