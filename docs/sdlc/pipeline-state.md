# Pipeline state

Inspection date: 2026-10-17. Conductor: `sdlc-conductor`. Runtime graph: `get_sdlc_template("lifecycle pipeline")` from harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`.

## What was inspected

- Workspace root `README.md` (rewritten in this run; the seed was a one-line stub).
- No product docs existed before this run. No app source, no Gradle build, no `DOMAIN.md`, no `LAYER.md`, no ADR, no UX file, no test plan.
- MCP consultants: `get_domain_consultant` for `vault`, `knowledge`, and `capture` returned no domains. `get_layer_consultant("ui")` returned no layers.

## What this run was asked to do

The request said to set up the repo per the harness, then produce the product spec, epics, refined user stories, and a draft backlog, and to stop at the first human approval gate. It named `mjbvilhena` as the approver. It said not to invent approvals and not to start implementation before the gates are approved.

That is an explicit request to draft stories in the same run as the spec. It is **not** a sentence that treats the spec, the epics, or the stories as approved. It is **not** a skip of UX, technical design, threat modeling, or test planning.

## Legal state

| State | Evidence | Status |
|---|---|---|
| Vision | `docs/product/vision.md` | Draft. Not accepted. |
| Product spec | `docs/product/specification.md` | **Approved** by `mjbvilhena` on 2026-10-17 at 12:36 IST. Wording: "Approved". Revision `c288b7e`. |
| Epic approval | `docs/product/epics/E1` through `E11` | **Approved** by `mjbvilhena` on 2026-10-17 at 13:29 IST. Wording: "All makes sense", then "Yes, approved". Revision `98275b3`. E1–E9 and E11 are v1. E10 is Approved as Later, not in v1, and has no stories. |
| Story refine | `docs/product/stories/US-01` through `US-81` and `INDEX.md` | Draft. Refined again on 2026-10-17 after the five-bot review and Miguel's 14:56 IST decisions. Not approved. |
| Story approval | Rule recorded. No story signed. | Not given. Verdicts at `9e7de63` are not approvals. Stories stay Draft until all five bots re-review. |
| Technical design / ADRs | No RFC, API design, or ADR files | Not started. |
| UX | No UX artefact | Not started. |
| Test strategy / threat model | No test plan, e2e plan, or threat model | Not started. A threat model is required in the design band before implementation (encryption, biometric lock, Drive sync, and the cloud topic call). |
| Domain / layer | No `DOMAIN.md` or `LAYER.md` | Not started. Bounds have not been designed. |
| Repository checks | Workflows, CODEOWNERS, gitignore, README | `.github/workflows/qa.yml` on `master`. See `docs/sdlc/repository-setup.md`. Not a product approval. |
| Implement | No application code | Not started. Blocked. |

## Stop

**Epic gate passed. Stopped at story review and approval.**

GitHub publication, 2026-10-17 at 13:26 IST, from `mjbvilhena`: the public repository is `github.com/mjbvilhena/lagoon`, default branch `master`. The initial import is the one direct push of this history. After that import, every change on that GitHub repository goes through a pull request into `master`. Required checks are `Secret scan`, `Markdown lint`, `Workflow lint`, `Build and test`, `Android lint`, `Kotlin style`, and `CodeQL`. The Android and CodeQL jobs no-op until a Gradle project exists. This publication does not approve the epics, the stories, or this setup note.

Epic-gate clarification, 2026-10-17 at 13:15 IST, from `mjbvilhena` on E5: "Look & feel should allow for light or dark theme, or follow the system setting." E5 and US-22 now describe an in-app setting, Light, Dark, and System, with System as the default. E5 was still Draft at that clarification and was approved later the same day. US-22 stays Draft. The approved specification was not revised. Its E5 line already calls for a light and dark UI and does not forbid following the Android light/dark setting. Dynamic color remains a separate optional toggle. Spec approval stands.

`mjbvilhena` approved `docs/product/specification.md` on 2026-10-17 at 12:36 IST. His words, after a full section-by-section walkthrough (parts 1–4, including E11 at `c288b7e`), in reply to a request to approve the Lagoon spec in his own words: "Approved". The approved product revision is `c288b7e` (`c288b7ef020a9aafe821b3df3af21bd2ff75dc1b`).

An earlier sentence, "Approve the spec, answer the 6 security questions at design" (09:20 IST), was withdrawn and is not this sign-off. The 12:36 IST "Approved" is.

That spec sign-off does not by itself approve epics E1–E11, stories US-01–US-40, or any design artefact. The epic gate was signed later, recorded below. Stories stay Draft. Implementation has not started.

He delegated Q1–Q4 and Q6–Q9. Mikey's Dev Bot recommended answers, and Scout, Mikey's Assistant, Mikey's Household Assistant, and Mikey's Leisure Assistant reviewed them. Q1 and Q2 were later changed by the joint recommendation of Scout and Mikey's Vault App Bot: the name is Lagoon and the application id is `com.mjbvilhena.lagoon`. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done. That record is in `docs/product/specification.md`. It is not approval.

He later decided, in his own words where he gave them: seamless sync instead of backup and restore (Q5 resolved as merge); extra encryption at rest and as ciphertext in Drive (Q10 resolved as yes); and a v1 scope of on-device OCR, reminders, multiple attachments, offline page snapshots, and a biometric lock on sensitive items. Trip or collection grouping is later (E10). Those decisions were not, at the time, approval of the specification, the epics, or the stories.

On 2026-10-17 at about 11:57 IST, during his section-by-section review, he added v1 epic E11 Topics. His words: "Use a cloud AI for all items; I accept the content leaves the phone." Readable content may be sent to a cloud AI for topic extraction only. Drive sync stays ciphertext-only. Cloud OCR stays out of scope. E11 had no stories. That scope change was inside the specification revision he later approved.

`mjbvilhena` approved epics E1–E11 on 2026-10-17 at 13:29 IST, after a 3-part walkthrough of every epic, including the E5 Light/Dark/System theme change at `98275b3`. His words: "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11. The approved epic revision is `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). E10 is Approved as Later and is not in v1. This sign-off does not approve stories US-01–US-40. At that moment E11 stories were not written yet. They were drafted later the same day (US-41–US-47) and stay Draft. Implementation has not started.

## Story approval rule (2026-10-17)

Miguel delegated story approval to his bots. He decides epic-level and security matters only. The rule is recorded here. It does not approve US-01–US-81. Those stories stay Draft until all five bots re-review and a sign-off names them. Verdicts at `9e7de63` are not that sign-off. Epic approval is now signed by him. The next gate is still story review. Drafting is not the sign-off.

## Design-gate questions

Miguel (`mjbvilhena`) decided these on 2026-10-17 at 14:56 IST. Not story approval:

1. If the vault key is lost, the content is lost. No recovery. US-35.
2. App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
3. Lock-screen notifications may show the item title and codes. US-34.
4. The zip export is encrypted with the vault key. US-21.

Still open for him. The design band has not started:

1. Sync edit conflicts, and how a deletion propagates. US-73.
2. How the vault key reaches a new device. US-74.
3. Exact alarms, which would amend E9. US-77. Stories stay on inexact alarms.
4. Topic provider, retention and training, how the credential is supplied, and the payload. US-76. The APK still must not contain a plaintext key.
5. Whether 09:00 should use a fixed zone instead of the phone.
6. Full-screen QR and barcode display.

Delegated to his bots the same afternoon. He did not decide them:

1. Sensitive-item handling in lists, search, notifications, export, the cloud call, and screenshots. US-75.
2. Drive scope and sign-out. US-78.
3. Link-preview skipping. US-79.
4. Sharing out. US-64.
5. Permissions. US-80.
6. Sideloading. US-81.

The five story-level questions now have consensus recommendations in US-01, US-15, US-31, US-33, and US-34 (Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot). US-31's language scope was later revised by Mikey's Dev Bot to English only. The Dev Bot reports that Miguel told it in chat that English is enough for v1 OCR. That revision is not approval. Those stories stay Draft. The FTS5 / Room 3 / SQLCipher spike on US-15 is a design-gate risk, not a decision. See the specification.

The specification gate and the epic gate have passed. Later gates stay closed until each is actually signed:

1. Epic approval by `mjbvilhena` (E1–E11, including later epic E10). **Passed** on 2026-10-17 at 13:29 IST. E10 is Approved as Later, not in v1.
2. Story review and approval of US-01–US-81 by Miguel's bots, under the delegation of 2026-10-17. **This is the gate the pipeline stops on.** Stories stay Draft until all five bots re-review.
3. Technical design and ADR approval, once those documents exist, and the threat model. The questions still marked for Miguel go to him. The questions marked delegated go to his bots. The cipher is an ADR, not a line in the spec. That band has not started.

UX, test strategy, and a threat model sit in the design band after story approval. The threat model is required before implementation. They were not skipped and they were not written. Implementation stays illegal until those gates are actually passed or he explicitly skips one and the skip is recorded here.

## Harness step (2026-10-17): E11 stories, then docs backlog review

The MCP server did not start. `bash scripts/sdlc-knowledge-mcp.sh` exited 1 with `sdlc-knowledge: Python venv not found at third_party/ai-sdlc-harness/mcp-server/venv/bin/python`. The same functions were imported and called directly from `third_party/ai-sdlc-harness/mcp-server/src/server.py`. `mcp` and `thefuzz` were stubbed only so that module could import. Alias resolution matched the exact logical names before any fuzzy score. This is not a live MCP session.

Skills used, in order:

1. `sdlc-conductor` (`.cursor/commands/sdlc-conductor.md`). Next legal step from `get_sdlc_template("lifecycle pipeline")`: the specification and epics E1–E11 are approved, story artefacts for E11 were missing, and story approval is not signed. The child skill is `sdlc-user-story-refiner`. UX, technical design, the threat model, and implementation stay closed. The correction that asked to drive this step is the confirm to hand off. Drafting is not approval.
2. `sdlc-user-story-refiner` (`.cursor/commands/sdlc-user-story-refiner.md`). Structure from `get_sdlc_template("user story")`. Each of US-41–US-47 was checked against `get_definition_of_done("user story")`.
3. `sdlc-docs-backlog-review` (`.cursor/commands/sdlc-docs-backlog-review.md`). Procedure from `get_sdlc_template("docs backlog review")`. The landing description follows `get_sdlc_template("pr")` and `get_definition_of_done("pr")`.

Templates fetched with `get_sdlc_template`: `lifecycle pipeline`, `user story`, `docs backlog review`, `pr`.

Definition of Done fetched with `get_definition_of_done`: `user story` (artefact self-check; implementation DoD is unmet), `epic` (fetched because a new epic would have required it; none was added), `pr` (docs-only change; green CI is not permission to merge).

Consultants: `get_domain_consultant` for `capture`, `vault`, and `knowledge`, and `get_layer_consultant` for `ui`, each returned not found. No `DOMAIN.md` or `LAYER.md` is in the tree. No MUST/NEVER rules were invented.

User-story DoD self-check for US-41–US-47 (artefact shape only):

- Story shape holds: actor is a person named Miguel, with I want and So that.
- Must criteria are Given/When/Then, independently valuable, and testable. Dependencies on other stories are named.
- Out of scope is listed. None of these seven stories has a Nice criterion.
- The four design-gate questions stay open and are not answered: provider and retention, credential storage, sensitive-item exclusion, and payload slice.
- Implementation DoD is unmet. There is no app, automation is absent, and no Must criterion is true in software. The stories stay Draft. They are not Done and not approved.

Docs backlog review, five phases, on 2026-10-17:

1. Drift against the tree: current-state lines still said E11 had no stories; the backlog now cites `.github/workflows/qa.yml` and no longer treats removed workflow files as present; a few older stories still used backup wording where the approved epic says sync, or a heading that did not match Light / Dark / System.
2. New drift backlog items: 0. The approved specification still says "E11 has no stories yet" (`docs/product/specification.md`). That sentence was not edited. It is residual approved text, not a new epic.
3. Status audit: no item flipped to Done or to In progress. US-01–US-47 stay Draft. E10 stays Later and has no stories. Nothing is implemented.
4. Straightforward fixes in this change: US-04, US-22 heading, US-25, US-27, US-36, plus the E11 story list in the backlog, the index, and this file. The historical sentence above now says the stories were unwritten at epic approval, then drafted later.
5. The review pull request stays open. It is not merged. Drafts in that pull request are not approvals.

## Harness step (2026-10-17, later): revise stories after the five-bot review

`sdlc-conductor` again. Legal step is still story refinement. Stories are Draft. Design, UX, the threat model, and implementation stay closed. The user's instruction to apply the review is the confirm to stay in `sdlc-user-story-refiner`.

The MCP server still did not start (`scripts/sdlc-knowledge-mcp.sh` exits 1, venv missing). `get_sdlc_template` and `get_definition_of_done` were called directly from `third_party/ai-sdlc-harness/mcp-server/src/server.py`, same as the earlier step.

Skills used for this revision:

1. `sdlc-conductor`
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`
3. `sdlc-dod-checker`, `get_definition_of_done("user story")`, artefact shape only
4. `sdlc-docs-backlog-review`, templates `docs backlog review` and `pr`
5. `sdlc-pr-summarizer`, template `pr` and Definition of Done `pr`

Miguel (`mjbvilhena`) confirmed these in chat on 2026-10-17 at 14:56 IST. They are his decisions. They are not story approval:

1. If the vault key is lost, the content is lost. No recovery. US-35 now has a warning he must accept before the key is created, and no recovery flow.
2. App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
3. Lock-screen notifications may show the item title and codes. US-34. What a sensitive item shows is still delegated.
4. The zip export is encrypted with the vault key. US-21.

Delegated to his bots the same afternoon, not decided by him: sensitive-item handling (lists, search, notifications, export, cloud, screenshots), Drive scope and sign-out, link-preview skipping, sharing out, permissions, and sideloading. Those are open questions in `docs/product/stories/REVIEW-PACK.md`, marked delegated to bots. US-64 and US-75 through US-81 hold them and have no Must that decides them.

Scout's Approve verdicts at `9e7de63`, under the delegation of 2026-10-17, cover US-03, US-04, US-07, US-09, US-10, US-11, US-16, US-17, US-19, US-24, US-26, US-27, US-28, US-30, US-36, US-38, and US-39. Recorded as review verdicts only. Status stays Draft until all five bots re-review.

`sdlc-dod-checker` artefact result: US-64, US-73, US-74, US-75, US-76, US-77, US-78, US-79, US-80, and US-81 fail because the open question is not closed and they have no asserting Must. Every other story passes artefact shape (actor, value, asserting Must criteria, out of scope listed, open questions moved). Implementation DoD is unmet for every story: there is no app and no automation. Nothing is Done.

Doc drift, not edited: `docs/product/specification.md` line 27 still says "E11 has no stories yet". `docs/product/vision.md` still lists OCR under "Not in the request" and still describes Back up now. Both are left as written. The approved specification and epics were not edited.

## Memory checklist

| Artefact | This run |
|---|---|
| Backlog | Written. Statuses are Draft or Not started. Nothing marked Done. |
| Vision ↔ spec | Spec links the vision and epics E1–E11. The vision is still not accepted. The specification is approved at revision `c288b7e`. |
| User-story index | `docs/product/stories/INDEX.md` lists US-01–US-81. Drafts are not approved. Review pack: `docs/product/stories/REVIEW-PACK.md`. |
| Design-artefact index | None. No design files. |
| ADRs | None. None accepted. |
| `DOMAIN.md` / `LAYER.md` | Not created. Consultants found nothing, and inventing MUST/NEVER rules is forbidden. |
| README | Points at this gate. Does not claim a build. |

## Re-entry

No verify/implement loop has run. Loop count for implementation is 0.
