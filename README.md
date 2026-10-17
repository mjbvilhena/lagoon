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

## Run the document checks locally

Markdownlint and Gitleaks are the configured checks. Kotlin test commands do not exist yet.

```bash
git submodule update --init third_party/ai-sdlc-harness
```

See `docs/sdlc/harness-install.md` for the MCP virtualenv.
