# Personal knowledge vault

On-device Android vault where Miguel keeps links, notes, images, and PDFs he wants to find again. Search and the library work offline. The draft spec now calls for background sync of ciphertext to private Drive app data, not a manual backup. A zip on the phone remains the fallback. There is still no app.

There is no app module in this repository yet. Do not look for an APK or a Gradle build.

## Where the work stands

The AI SDLC harness is installed. The product vision, specification, epics, and user stories are **drafts**. None of them is approved.

The pipeline is stopped at the first human approval gate: **product specification approval** by `mjbvilhena`. The specification is Draft and pending his review.

Decisions in the specification are recorded and are not approval. Miguel decided sync, encryption, and the v1 scope (OCR, reminders, multiple attachments, page snapshots, biometric lock on sensitive items). Scout and Mikey's Vault App Bot jointly recommend the name Lagoon and the application id `com.mjbvilhena.lagoon`. His other bots recommended the remaining delegated answers. Trip grouping is later. Q5 and Q10 are resolved.

On 2026-10-17 he delegated story approval to his bots. He decides epic-level and security matters only. No story is approved by that rule. Six questions stay open for him at the technical design, ADR, and threat-model gate: sync edit conflicts, delete propagation, key distribution to a new device, a lost key or passphrase, whole-app unlock, and Android developer verification for sideloading. That gate has not started.

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
