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
| Vision | `docs/product/vision.md` | Current vision, rewritten 2026-10-17 at 15:55 IST to match the approved specification, epics E1–E9 and E11, and the approved amendment. |
| Product spec | `docs/product/specification.md` | **Approved** by `mjbvilhena` on 2026-10-17 at 12:36 IST. Wording: "Approved". Revision `c288b7e`. Amendment sections 1 and 6 applied after his approval at 15:55 IST. Wording: "Yes". |
| Epic approval | `docs/product/epics/E1` through `E11` | **Approved** by `mjbvilhena` on 2026-10-17 at 13:29 IST. Wording: "All makes sense", then "Yes, approved". Revision `98275b3`. E1–E9 and E11 are v1. E10 is Approved as Later, not in v1, and has no stories. |
| Story refine | `docs/product/stories/US-01` through `US-84` and `INDEX.md` | Draft. Refined again on 2026-10-17 after the round-2 review of `40bb526`. Not approved. |
| Story approval | Rule recorded. No story signed. | Not given. Round-2 verdicts are not a status of Approved. Household and Leisure are recommendations only. |
| Technical design / ADRs | No RFC, API design, or ADR files | Not started. |
| UX | No UX artefact | Not started. |
| Test strategy / threat model | `docs/product/amendments/2026-10-17-proposed-threat-note.md` | Not started as a gate. A proposed note exists. It is not an approved threat model and it does not open the design band. |
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

Miguel delegated story approval to his bots. He decides epic-level and security matters only. The rule is recorded here. It does not by itself approve US-01–US-84. Round-2 cells for Dev Bot, Scout, and Mikey's Assistant use the words "Approved under Miguel's delegation of 2026-10-17" where the review of `40bb526` was a clean approval. Household and Leisure stay recommendations until he confirms the delegation to them directly. No story status field was set to Approved in this run. Epic approval is signed by him. The next gate is still story review. Drafting is not the sign-off.

## Design-gate questions

Miguel (`mjbvilhena`) decided these on 2026-10-17 at 14:56 IST. Not story approval:

1. If the vault key is lost, the content is lost. No recovery. US-35.
2. App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
3. Lock-screen notifications for an ordinary item may show the item title and codes. US-34. A sensitive item is the 15:59 IST decision below.
4. The zip export is encrypted with the vault key. US-21.

Still open for him. The design band has not started:

1. Sync edit conflicts, and how a deletion propagates. US-73.
2. How the vault key reaches a new device. US-74.
3. Exact alarms, which would amend E9. US-77. Stories stay on inexact alarms.
4. Topic provider, retention and training, how the credential is supplied, and the payload. US-76. The APK still must not contain a plaintext key.
5. Whether 09:00 should use a fixed zone instead of the phone.
6. Full-screen QR and barcode display.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 15:59 IST. Not story approval.

1. A sensitive item's lock-screen reminder shows only "Lagoon reminder" (`VISIBILITY_PRIVATE`, public version), with no title or codes until unlock. US-75. Ordinary items still show title and codes.
2. A token-looking URL is not auto-fetched. A foggy placeholder is shown and he is asked whether to enable that preview. US-79. The other US-79 criteria stay proposed.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 16:00 IST. Not story approval. His words: "Requirement will be to let the user choose to disable (if possible), otherwise will have to disclose and accept".

1. Official Google ML Kit docs do not document a developer switch that disables usage or diagnostic telemetry. The research note is `docs/product/research/2026-10-17-ml-kit-telemetry.md`. There is no Settings toggle and no default to copy from the docs.
2. US-07 requires a disclosure he accepts before the first OCR use. Declining, or leaving it unanswered, means OCR does not run. US-59 shows the same disclosure and whether he has accepted it. The image and the recognized text stay on the device. This does not approve US-07 or US-59.

Decided by Miguel (`mjbvilhena`) on 2026-10-17 at 18:05 IST. Not story approval. His words: "Topics on by default, with an off switch".

1. Asked whether topic extraction should be on by default for new items, he said it starts on, with an off switch in Settings. US-41 and US-59. His words are quoted in `docs/product/amendments/2026-10-17-chat-decisions.md`.
2. That quote does not exclude sensitive items. The exclusion is the amendment approved on 2026-10-17 at 15:55 IST, recorded on US-75. The topic job keeps its own unmetered setting (US-44). This does not approve US-41, US-59, or US-75, and it is not a new specification approval.

Delegated to his bots the same afternoon. He did not decide them. Round 2 proposes criteria, marked pending his approval, in `docs/product/amendments/2026-10-17-security-delegation.md` and on the stories. The proposals are not approval.

1. Sensitive-item handling in lists, search, export, the cloud call, and screenshots. US-75.
2. Drive scope and sign-out. US-78.
3. Link-preview skipping. US-79.
4. Sharing out. US-64.
5. Permissions. US-80.
6. Sideloading. US-81.

The five story-level questions now have consensus recommendations in US-01, US-15, US-31, US-33, and US-34 (Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot). US-31's language scope was later revised by Mikey's Dev Bot to English only. The Dev Bot reports that Miguel told it in chat that English is enough for v1 OCR. That revision is not approval. Those stories stay Draft. The FTS5 / Room 3 / SQLCipher spike on US-15 is a design-gate risk, not a decision. See the specification.

The specification gate and the epic gate have passed. Later gates stay closed until each is actually signed:

1. Epic approval by `mjbvilhena` (E1–E11, including later epic E10). **Passed** on 2026-10-17 at 13:29 IST. E10 is Approved as Later, not in v1.
2. Story review and approval of US-01–US-84 by Miguel's bots, under the delegation of 2026-10-17. **This is the gate the pipeline stops on.** Round-2 verdicts are recorded. No story status is Approved. Household and Leisure remain recommendations until he confirms the delegation to them.
3. Technical design and ADR approval, once those documents exist, and the threat model. The questions still marked for Miguel go to him. The questions marked delegated go to his bots. The cipher is an ADR, not a line in the spec. That band has not started.

UX, test strategy, and an approved threat model sit in the design band after story approval. The threat model is required before implementation. A proposed note exists and does not open that band. Implementation stays illegal until those gates are actually passed or he explicitly skips one and the skip is recorded here.

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

## Harness step (2026-10-17, round 2): revise stories after the review of 40bb526

`sdlc-conductor` again. The legal step is still story refinement. Stories are Draft. Design, UX, an approved threat model, and implementation stay closed. The user's instruction to apply the round-2 review is the confirm to stay in `sdlc-user-story-refiner`. No story status was flipped to Approved. Pull request #3 stays open and unmerged.

The story branch does not contain `scripts/bootstrap-sdlc-mcp.sh` (that script is on pull request #4). The virtualenv created by that script was already on disk at `third_party/ai-sdlc-harness/mcp-server/venv`. `get_sdlc_template` and `get_definition_of_done` were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with that virtualenv's Python. This was not a live MCP stdio session. `get_domain_consultant` for capture, vault, and knowledge, and `get_layer_consultant("ui")`, found no files. No MUST/NEVER rules were invented.

Skills, templates, and Definition of Done checks used for this revision:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`. Legal step: story refinement.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
3. `sdlc-threat-modeler`, template `get_sdlc_template("threat model")`, Definition of Done `get_definition_of_done("security change")`. The proposed note is not an approved threat model. Security-change implementation DoD is unmet: there is no code.
4. `sdlc-dod-checker`, `get_definition_of_done("user story")`, artefact shape only.
5. `sdlc-product-owner`, template `get_sdlc_template("product spec")`, Definition of Done `get_definition_of_done("epic")`. The amendment is Proposed, awaiting `mjbvilhena`. The approved specification and epics were not edited.
6. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
7. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

Approved stories touched in this revision, and why. Each edit is small. The round-2 cell asks for re-confirm. Status stays Draft.

- US-01. One title order, shared with US-02 and US-40. Leftover text is kept as a note the same way in US-01 and US-02. The sending app is recorded only when the platform reports it.
- US-45. A hard-deleted item's topic note is removed, so the story matches US-13. The previous "item deleted" note contradicted that delete.
- US-57. The spike also runs on an API 26 emulator and an arm64 phone, and the note records the APK size change.

`sdlc-dod-checker` artefact result: fail US-73, US-74, US-76, US-77. Pass artefact shape: every other story, including the six proposed security stories. Implementation DoD fails for every story. Nothing is Done.

Proposed criteria, marked **Proposed: pending Miguel's approval**, are on US-64, US-75, US-78, US-79, US-80, and US-81. The proposed threat note is `docs/product/amendments/2026-10-17-proposed-threat-note.md`. The proposed amendment is `docs/product/amendments/2026-10-17-security-delegation.md`.

Not decided, and not turned into criteria: ML Kit telemetry, lock-screen text for sensitive items, token-looking URL previews, the topic provider and payload, sync conflicts, key transfer, exact alarms, a fixed time zone, and full-screen QR.

## Harness step (2026-10-17, amendment approval): apply the approved security-delegation amendment

`sdlc-conductor`. The pipeline is still stopped at story review. Spec and epic text already have a named human gate, and Miguel (`mjbvilhena`) approved this amendment at 15:55 IST after a 4-part walkthrough. Applying sections 1–6 is legal. It does not open the design band, does not approve any story, and does not start implementation. His instruction to apply it is the confirm. The next product gate remains story approval.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. This was not a live MCP stdio session.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-product-owner`, template `get_sdlc_template("product spec")` (the epic alias resolves to the same template). Definition of Done `get_definition_of_done("epic")` and `get_definition_of_done("product spec")`, artefact shape only. Shipped-epic DoD is unmet: nothing is built.
3. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
4. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

His words. Part 1, 15:48 IST: "Yes, that's what I meant". Part 2: "Happy with both". Part 3: "Yes". Final approval at 15:55 IST: "Yes". He changed section 7. The vision file is rewritten to the current vision. No supersession sentence was added. His words: "Vision document should always reflect the current vision, not an historic vision with some amendments added to it".

Sections 1–6 are applied to `docs/product/specification.md` and epics E1, E3, E8, and E11. Where a proposed sentence said a rule stayed out until this amendment was approved, the file records that he approved it, so US-61, US-64 (Should), and the topic job's own unmetered setting are now in those epics. Sensitive-item exclusion stays out of E11 until he approves the US-75 proposal. Proposed criteria on US-64, US-75, US-78, US-79, US-80, and US-81 stay marked **Proposed: pending Miguel's approval**.

No story status was flipped to Approved. The stale drift list (specification line 27, the design-gate "not delegated" sentence, E8, E3, E11, E1, and the old vision) is removed because those files now match the approved amendment.

Approved revision of this application: commit `86573af` (`86573afe32a946b761f74c354acc389f2a7bac96`).

## Harness step (2026-10-17, 15:59 IST): two security decisions

`sdlc-conductor`. The legal step is still story refinement. Stories stay Draft. Design, an approved threat model, and implementation stay closed. Recording these two named decisions is legal. It does not approve US-75 or US-79, and it does not approve the rest of their proposed criteria. The user's instruction to record them is the confirm.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. This was not a live MCP stdio session. `get_domain_consultant("vault")` and `get_layer_consultant("ui")` found no files. No MUST/NEVER rules were invented.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
3. `sdlc-threat-modeler`, template `get_sdlc_template("threat model")`, Definition of Done `get_definition_of_done("security change")`. The note stays proposed. Security-change implementation DoD is unmet: there is no code.
4. `sdlc-dod-checker`, `get_definition_of_done("user story")`, artefact shape only.
5. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
6. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

Miguel (`mjbvilhena`) decided, in chat on 2026-10-17 at 15:59 IST:

1. US-75. A lock-screen reminder for a sensitive item shows only "Lagoon reminder" (`VISIBILITY_PRIVATE` with a public version). No title and no codes. Details appear after unlock. This narrows the 14:56 title-and-codes decision for sensitive items only. This criterion is decided, not Proposed. The other US-75 criteria stay Proposed.
2. US-79. A token-looking URL is not auto-fetched. A foggy placeholder image is shown, and he is asked whether to enable the preview for that link. Yes fetches under the proposed safe-fetch rules. No leaves the placeholder, and he can enable it later. No answer leaves the placeholder. This criterion is decided. The rest of US-79 stays Proposed.

US-10 does not draw a preview on the home row. US-11 shows the foggy placeholder when that is the stored preview. US-11 had a clean round-2 verdict, so that edit is small and needs re-confirm. US-10 did not have a clean approval. Status stays Draft.

ML Kit telemetry (US-07) stays open.

`sdlc-dod-checker` artefact result is unchanged in kind: fail US-73, US-74, US-76, US-77. US-75 and US-79 still pass artefact shape. Their decided criteria assert, and the remaining proposed criteria still assert. Implementation DoD fails for every story. Nothing is Done. No story status was flipped to Approved.

## Harness step (2026-10-17, 16:00 IST): ML Kit telemetry

`sdlc-conductor`. The legal step is still story refinement. Stories stay Draft. Design, an approved threat model, and implementation stay closed. Recording this named decision, after the research the user asked for, is legal. It does not approve US-07 or US-59. The user's instruction to write the criteria is the confirm.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. This was not a live MCP stdio session. `get_domain_consultant("capture")` and `get_layer_consultant("ui")` found no files. No MUST/NEVER rules were invented.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-researcher`, template `get_sdlc_template("research")`, Definition of Done `get_definition_of_done("research")`. The report is `docs/product/research/2026-10-17-ml-kit-telemetry.md`. It has an executive summary, three options, pros and cons, security and performance notes, and the sources below. The DoD review checkbox is this pull request, for Miguel. It is not a separate stakeholder review and it is not his approval of the research.
3. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
4. `sdlc-dod-checker`, `get_definition_of_done("user story")`, artefact shape only. Also `get_definition_of_done("pr")` for the landing description. `get_definition_of_done("security change")` stays unmet: there is no code, and the threat note stays proposed.
5. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
6. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`.

Finding, from the official pages, not from a third-party privacy policy: no documented developer control disables ML Kit usage or diagnostic telemetry. Sources cited in the research note and in the US-07 implementation notes:

- [ML Kit Terms & Privacy](https://developers.google.com/ml-kit/terms), last modified May 14, 2025.
- [Android data disclosure](https://developers.google.com/ml-kit/android-data-disclosure), last modified May 14, 2025.
- [MlKit reference](https://developers.google.com/android/reference/com/google/mlkit/common/MlKit). Removing `MlKitInitProvider` is init timing, not a telemetry switch.
- [Text recognition on Android](https://developers.google.com/ml-kit/vision/text-recognition/v2/android), last updated 2026-10-15 UTC.
- [iOS data disclosure](https://developers.google.com/ml-kit/ios-data-disclosure), corroboration only. Lagoon is Android.

Because a disable is not in those docs, the criteria are the other branch of his sentence: an in-app disclosure he accepts before the first OCR use, also readable in Settings. No toggle was added. No default was invented.

US-31 had a clean round-2 verdict. The edit there is one factual note pointing at the US-07 gate. It needs re-confirm. Status stays Draft.

`sdlc-dod-checker` artefact result: fail US-73, US-74, US-76, US-77. US-07 and US-59 pass artefact shape. Their new criteria assert, and US-07 no longer leaves ML Kit as a silent open question. Implementation DoD fails for every story. Nothing is Done. No story status was flipped to Approved.

The catalog cell for US-44 no longer says E11 omits the topic unmetered setting. E11 includes that setting after the 15:55 IST amendment. The story file was not rewritten for that cell.

## Harness step (2026-10-17, round 3): story revision at df8c088

`sdlc-conductor`. The legal step is still story refinement. Stories stay Draft. Design, an approved threat model, and implementation stay closed. The user's instruction to apply the round-3 list is the confirm. This run records no approval.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. That script lives on PR #4. This was not a live MCP stdio session. `get_domain_consultant("capture")` and `get_layer_consultant("ui")` found no files. No MUST/NEVER rules were invented.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
3. `sdlc-threat-modeler`, template `get_sdlc_template("threat model")`, Definition of Done `get_definition_of_done("security change")`. The note stays proposed. Security-change implementation DoD is unmet: there is no code.
4. `sdlc-researcher`, template `get_sdlc_template("research")`, Definition of Done `get_definition_of_done("research")`. The ML Kit note now requires the bundled model and records that pre-first-call metrics are undocumented.
5. `sdlc-dod-checker`, `get_definition_of_done("user story")` from `third_party/ai-sdlc-harness/mcp-server/data/dod/user_story.md`, checked against every story file. Artefact shape fails US-73, US-74, US-76, and US-77. Every other story passes shape. Implementation DoD fails every story. Nothing is Done.
6. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
7. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

Left open for Miguel at the end of round 3, and not changed in that run: the topic off-switch default (decided later the same day at 18:05 IST), US-28 Add date, the priority of US-82 through US-84 and the scope of US-68, whether the key is auth-bound, US-73, US-74, US-76, US-77, the reminder time zone, and full-screen QR. The questions that stayed open are listed in `docs/product/stories/REVIEW-PACK.md`.

The 15:59 IST and 16:00 IST chat decisions were recorded in the specification decision table in that run. Round 4 moves them to `docs/product/amendments/2026-10-17-chat-decisions.md`. That record is not a new specification approval.

Clean-verdict stories edited in this run, needing re-confirm, status still Draft: US-08, US-15, US-19, US-31, US-33, US-50.

`ubuntu-latest` moves to Ubuntu 26.04 on 2026-10-19. Pinning `ubuntu-24.04` is a separate pull request. The required checks stay no-ops until Gradle exists.

## Harness step (2026-10-17, 18:05 IST): topic default

`sdlc-conductor`. The legal step is still story refinement. The specification gate and the epic gate have passed. Story approval has not. Design, an approved threat model, and implementation stay closed. The user's instruction to apply the 18:05 IST decision is the confirm. This run records no approval and does not flip any story to Approved.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. That script lives on PR #4. This was not a live MCP stdio session. `get_domain_consultant("knowledge")` and `get_layer_consultant("ui")` found no files. No MUST/NEVER rules were invented.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`. US-41 and US-59 only. The topic switch starts on. He can turn it off in Settings. The topic job keeps its own unmetered setting (US-44). Round 4 later records that sensitive-item exclusion is the 15:55 IST amendment, not this quote.
3. `sdlc-dod-checker`, `get_definition_of_done("user story")` from `third_party/ai-sdlc-harness/mcp-server/data/dod/user_story.md`. US-41 and US-59 pass artefact shape. Implementation DoD fails both, and every other story. Nothing is Done.
4. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`. The topic default is removed from the open-question lists. The decision is recorded next to the 15:59 IST and 16:00 IST decisions.
5. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

The decision was first written into the specification decision table. Round 4 moves it to `docs/product/amendments/2026-10-17-chat-decisions.md`. It is not a new specification approval. US-41 and US-59 stay Draft. They were not clean approvals at `40bb526`, so this edit does not add them to the re-confirm list.

## Harness step (2026-10-17, round 4): story revision at 5ba70f5

`sdlc-conductor`. The legal step is still story refinement. The specification gate and the epic gate have passed. Story approval has not. Design, an approved threat model, and implementation stay closed. The user's instruction to apply the round-4 list is the confirm. This run records no approval and does not flip any story to Approved.

Templates and Definition of Done checks were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This branch does not contain `scripts/bootstrap-sdlc-mcp.sh`. That script lives on PR #4. This was not a live MCP stdio session. `get_domain_consultant` for capture, vault, and knowledge, and `get_layer_consultant("ui")`, found no files. No MUST/NEVER rules were invented.

Skills used:

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
3. `sdlc-threat-modeler`, template `get_sdlc_template("threat model")`, Definition of Done `get_definition_of_done("security change")`. The note stays proposed. Security-change implementation DoD is unmet: there is no code.
4. `sdlc-product-owner`, template `get_sdlc_template("product spec")`, Definition of Done `get_definition_of_done("product spec")` and `get_definition_of_done("epic")`. The chat-decisions addendum is not a specification approval. E11 now cites the 15:55 IST amendment for sensitive-item exclusion and the addendum for the 18:05 IST default.
5. `sdlc-dod-checker`, `get_definition_of_done("user story")` from `third_party/ai-sdlc-harness/mcp-server/data/dod/user_story.md`, checked against every story file.
6. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
7. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

Left open for Miguel, and not decided in this run: US-82 through US-84 scope and priority, the US-50 merchant weight, whether the key is auth-bound, the per-item "Include in topics" opt-in, whether a sensitive notification's private version is also generic, the reminder time zone, full-screen QR, US-73, US-74, US-76, US-77, US-28 Add date, and US-68 scope. Those are listed in `docs/product/stories/REVIEW-PACK.md`.

### Harness: story removal, 2026-10-17 at 19:00 and 19:01 IST

1. `sdlc-conductor`, template `get_sdlc_template("lifecycle pipeline")`.
2. `sdlc-user-story-refiner`, template `get_sdlc_template("user story")`.
3. `sdlc-docs-backlog-review`, template `get_sdlc_template("docs backlog review")`.
4. `sdlc-dod-checker`, `get_definition_of_done("user story")` from `third_party/ai-sdlc-harness/mcp-server/data/dod/user_story.md`.
5. `sdlc-pr-summarizer`, template `get_sdlc_template("pr")`, Definition of Done `get_definition_of_done("pr")`.

Templates were imported from `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python. This was not a live MCP session. No domain consultant was found. No MUST/NEVER rules were invented.

Miguel (`mjbvilhena`) at 19:00 IST: "68, 69, and 82 to 84 should be removed." At 19:01 IST: "63 can be removed as well." Deleted US-63, US-68, US-69, US-82, US-83, and US-84. Numbers were not reused. No story status was set to Approved.

Downstream clauses cut with those stories: US-15 merchant column, US-34 code clause, US-50 merchant weight and draft merchant scenario, US-55 return-by and warranty words, US-75 return-by and warranty notice, pinned-shortcut half, merchant word, copied-code scenario, and widget cache line, US-80 widget receiver, and the matching phrases in the proposed threat note. US-62 was left in place, including its given-list and the US-75 Upcoming row.

Still awaiting his decision: US-48, US-62, US-65, US-66, US-67, US-70, US-71, US-72, the US-28 Add date trim, the US-40 generated-titles trim, the US-41 cap-of-8 trim, and the US-53 reduced-motion trim. Also still open: whether the key is auth-bound, the per-item "Include in topics" opt-in, whether a sensitive notification's private version is also generic, the reminder time zone, full-screen QR, US-73, US-74, US-76, and US-77.

`ubuntu-latest` moves to Ubuntu 26.04 on 2026-10-19. Pinning `ubuntu-24.04` is a separate pull request. The required checks stay no-ops until Gradle exists.

## Memory checklist

| Artefact | This run |
|---|---|
| Backlog | Written. Statuses are Draft or Not started. Nothing marked Done. |
| Vision ↔ spec | The vision states the current product. The specification is approved at revision `c288b7e`, with the 15:55 IST amendment applied. The 15:59, 16:00, and 18:05 IST chat decisions are in `docs/product/amendments/2026-10-17-chat-decisions.md`. They are not a new specification approval. |
| User-story index | `docs/product/stories/INDEX.md` lists the remaining stories. US-63, US-68, US-69, US-82, US-83, and US-84 were removed on 2026-10-17 at 19:00 and 19:01 IST. Numbers were not reused. Drafts are not approved. Review pack: `docs/product/stories/REVIEW-PACK.md`. Traceability: `docs/product/stories/TRACEABILITY.md`. Amendment: `docs/product/amendments/2026-10-17-security-delegation.md`, approved 2026-10-17 at 15:55 IST. Chat decisions: `docs/product/amendments/2026-10-17-chat-decisions.md`. ML Kit research: `docs/product/research/2026-10-17-ml-kit-telemetry.md`. |
| Design-artefact index | None. No design files. |
| ADRs | None. None accepted. |
| `DOMAIN.md` / `LAYER.md` | Not created. Consultants found nothing, and inventing MUST/NEVER rules is forbidden. |
| README | Points at this gate. Does not claim a build. |

## Re-entry

No verify/implement loop has run. Loop count for implementation is 0.
