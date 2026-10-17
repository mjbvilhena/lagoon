# Personal knowledge vault

On-device Android vault where Miguel keeps links, notes, images, and PDFs he wants to find again. Search and the library work offline. Google Drive backup is private to the app and stays off until he configures an OAuth client. A zip export on the phone is the fallback.

There is no app module in this repository yet. Do not look for an APK or a Gradle build.

## Where the work stands

The AI SDLC harness is installed. The product vision, specification, epics, and user stories are **drafts**. None of them is approved.

The pipeline is stopped at the first human approval gate: **product specification approval** by `mjbvilhena`.

Read `docs/sdlc/pipeline-state.md` before editing product docs or writing code.

| Path | What it is |
|---|---|
| `docs/product/vision.md` | Draft vision, taken from the request |
| `docs/product/specification.md` | Draft product specification |
| `docs/product/epics/` | Draft epics |
| `docs/product/stories/` | Draft user stories and the index |
| `docs/product/backlog.md` | Draft backlog |
| `docs/sdlc/pipeline-state.md` | Current gate and what has not been started |
| `docs/sdlc/harness-install.md` | Harness pin, installer, MCP command |
| `docs/sdlc/repository-setup.md` | Draft repository checks |
| `.cursor/commands/` | Installed harness skills and rules |
| `third_party/ai-sdlc-harness` | Harness submodule |

## Run the document checks locally

Markdownlint and Gitleaks are the configured checks. Kotlin test commands do not exist yet.

```bash
git submodule update --init third_party/ai-sdlc-harness
```

See `docs/sdlc/harness-install.md` for the MCP virtualenv.
