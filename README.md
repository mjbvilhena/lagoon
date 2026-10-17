# Personal knowledge vault

On-device Android vault where Miguel keeps links, notes, images, and PDFs he wants to find again. Search and the library work offline. The draft spec now calls for background sync of ciphertext to private Drive app data, not a manual backup. A zip on the phone remains the fallback. There is still no app.

There is no app module in this repository yet. Do not look for an APK or a Gradle build.

## Where the work stands

The AI SDLC harness is installed. The product specification is **Approved** by `mjbvilhena` on 2026-10-17 at 12:36 IST. His wording was "Approved". The approved revision is `c288b7e`. The vision, epics, and user stories stay **drafts**.

The pipeline is stopped at **epic approval** of E1–E11 by `mjbvilhena`. After that, Miguel's bots review and approve the stories under his delegation of 2026-10-17. Technical design, ADRs, and the threat model come next, and the security questions go to him. Nothing is implemented.

Miguel decided sync, encryption, and the v1 scope (OCR, reminders, multiple attachments, page snapshots, biometric lock on sensitive items, and topic extraction). On 2026-10-17 at about 11:57 IST he added draft epic E11: a cloud AI may read item content to identify topics, and Drive sync stays ciphertext-only. Scout and Mikey's Vault App Bot jointly recommend the name Lagoon and the application id `com.mjbvilhena.lagoon`. His other bots recommended the remaining delegated answers. Trip grouping is later. Q5 and Q10 are resolved.

On 2026-10-17 he delegated story approval to his bots. He decides epic-level and security matters only. No story is approved by that rule. E11 has no stories yet. Design-gate questions stay open for him, including the earlier six and the topic-extraction questions (provider and retention, credential storage, sensitive items, and what is sent). That gate has not started.

Read `docs/sdlc/pipeline-state.md` before editing product docs or writing code.

| Path | What it is |
|---|---|
| `docs/product/vision.md` | Draft vision, taken from the request |
| `docs/product/specification.md` | Approved product specification (`c288b7e`) |
| `docs/product/epics/` | Draft epics |
| `docs/product/stories/` | Draft user stories and the index |
| `docs/product/backlog.md` | Draft backlog |
| `docs/sdlc/pipeline-state.md` | Current gate and what has not been started |
| `docs/sdlc/harness-install.md` | Harness pin, installer, MCP command |
| `docs/sdlc/repository-setup.md` | Draft repository checks |
| `.cursor/commands/` | Installed harness skills and rules |
| `third_party/ai-sdlc-harness` | Harness submodule |

## Quality checks

GitHub Actions on `push` to `master`, pull requests, and `workflow_dispatch`. The workflow is `.github/workflows/qa.yml`.

| Check | Tool | Why |
| --- | --- | --- |
| Secret scan | [Gitleaks](https://github.com/gitleaks/gitleaks-action) | Universal control against committed credentials |
| Markdown lint | [markdownlint-cli2](https://github.com/DavidAnson/markdownlint-cli2-action) | Markdown is the current primary content |
| Workflow lint | [actionlint](https://github.com/rhysd/actionlint) 1.7.12 | GitHub Actions YAML is part of this repo |
| Build and test | Gradle `assembleDebug` and `testDebugUnitTest` | App build, once a Gradle project exists. Until then the job passes with a clear skip message |
| Android lint | Gradle `lintDebug` | Android lint, once a Gradle project exists. Until then the job passes with a clear skip message |
| Kotlin style | ktlint or detekt | Kotlin style, once a Gradle project exists. Until then the job passes with a clear skip message |
| CodeQL | CodeQL for Java and Kotlin | SAST, once a Gradle project exists. Until then the job passes with a clear skip message |

### Secrets

Store tokens in GitHub Actions secrets. Do not commit them.

- `GITHUB_TOKEN` — provided by GitHub; used by Gitleaks
- `GITLEAKS_LICENSE` — **required for GitHub Organization repositories**, not for personal accounts. Add it under repository or organization secrets if this repo lives in an org

Signing keys, `local.properties`, `google-services.json`, OAuth client files, and `.env*` (except `.env.example`) are listed in `.gitignore`.

## Branch protection

Enforced on `master` by the Protect master ruleset:

1. Require a pull request before merging
2. Require at least one approving review (CODEOWNERS covers the tree)
3. Require status checks to pass: `Secret scan`, `Markdown lint`, `Workflow lint`, `Build and test`, `Android lint`, `Kotlin style`, and `CodeQL`
4. Disallow force-pushes and branch deletion

**Break-glass:** repository admins may bypass these rules only when merging a pull request. Direct pushes to `master` remain blocked.

The first publication of this history is the import onto `master`. After that import, changes to the GitHub repository go through pull requests into `master`.

## Local lint

```bash
npx --yes markdownlint-cli2
```

```bash
git submodule update --init third_party/ai-sdlc-harness
```

See `docs/sdlc/harness-install.md` for the MCP virtualenv. Kotlin test commands do not exist yet.
