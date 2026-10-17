# Repository setup

> **Approval status: Draft. Not approved.**
> The checks below were added because the 2026-10-17 request said to set the repo up per the harness. That sentence is not a named approval of this document, and it is not product-spec approval.
> Approver required for this setup note: `mjbvilhena`.
> Sign-off on file: none.

Structured from `get_sdlc_template("repository setup")` and `get_definition_of_done("repository setup")`.

## Metadata

| Field | Value |
|---|---|
| Target Repository | This workspace (personal knowledge vault for Miguel) |
| Primary Tech Stack | Markdown product docs now. Kotlin, Jetpack Compose, and Gradle are named for a later app and are not in the tree. |
| CI/CD System | GitHub Actions |

## Purpose Summary

This repository will hold Miguel's personal knowledge vault: an on-device Android app and the SDLC documents that precede it. The content expected over time is product and design markdown, then a Kotlin/Compose app, its tests, and release notes. No application source is in the tree at this gate.

## Stack mapping

The harness mapping was applied as written. Checks were not added for languages that mapping does not list.

| Content expected | Proposed check | In this repo |
|---|---|---|
| Markdown documentation | `markdownlint` | `.github/workflows/markdownlint.yml` linting `README.md` and `docs/**/*.md` |
| Any repo | `gitleaks` and `CODEOWNERS` | `.github/workflows/secret-scan.yml`, `.github/CODEOWNERS` |
| Bash scripts | `bats`, `shellcheck` | Not configured. This product repo is not a Bash project. The harness submodule has its own checks. |
| Python | `bandit` and pytest | Not configured. Python is only the harness MCP server inside the submodule. |
| Kotlin / Gradle | — | Not in the mapping table. Detekt, ktlint, and Gradle test jobs were not added. Add them only when an approved technical design names them, or when the mapping is extended. |

Action versions match the harness workflows: `actions/checkout@v4`, `gitleaks/gitleaks-action@v2`, `articulate/actions-markdownlint@v1`. They are major-version tags, the same pins that harness uses, not commit SHAs.

## Configurations

### Security

Secret scanning is `.github/workflows/secret-scan.yml`. It checks out full history and runs Gitleaks with `GITHUB_TOKEN`. Signing keys, `local.properties`, and OAuth property files are also listed in `.gitignore` so a later release keystore is not committed by default.

SAST for Python was not added. There is no product Python package.

### Linting & Formatting

`.github/workflows/markdownlint.yml` uses `.markdownlint.json` (the same rule relaxations as the harness config: line length off, dash lists, and the same disabled structural rules). The file glob is `README.md` plus `docs/**/*.md` so generated slash commands under `.cursor/commands/` and the harness submodule are not treated as this product's docs.

No shellcheck, eslint, or ruff project exists here.

### Testing

No product test runner exists yet. User stories US-25 ask for repository/search tests, backup-packaging tests, and a Compose UI test when implementation is allowed. Those jobs are not wired now, because that would invent a Kotlin CI menu the template does not list and because implementation has not started.

### Ownership

`.github/CODEOWNERS` sets `* @mjbvilhena`. That assigns a default reviewer. It does not approve the product spec, epics, or stories.

## Branch protection

GitHub branch protection cannot be enforced from a file in the repo. Recommended rules for `main`, for Miguel to turn on when he wants them:

- Require a pull request before merging.
- Require the secret-scan and markdownlint checks to pass.
- Do not treat a green check as product approval or as permission to start implementation.

## Checklist

- [x] Purpose and content types have been identified (`README.md`, this file).
- [x] Universal secret scanning is configured (`.github/workflows/secret-scan.yml`).
- [x] Language-specific linting is configured for the markdown already in the repo. Kotlin lint is intentionally absent (see stack mapping).
- [x] Default code ownership is established (`.github/CODEOWNERS`).
- [x] CI actions use the same major-version tags as the harness workflows.
- [x] `.gitignore` covers signing secrets and Android/Gradle outputs named by the brief.
- [ ] Branch protection is documented above and is not enabled from this repo.
- [ ] Named approval of this setup note by `mjbvilhena`.

## Anti-patterns avoided

- No detekt, ktlint, or Gradle workflow was invented to look complete.
- No end-to-end emulator workflow was added while there is no app.
