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
| Story refine | `docs/product/stories/US-01` through `US-47` and `INDEX.md` | Draft. US-41–US-47 are the E11 drafts from this step. Not approved. |
| Story approval | Rule recorded. No story signed. | Not given. On 2026-10-17 Miguel delegated story approval to his bots. He decides epic-level and security matters only. The delegation is not a sign-off on US-01–US-47. |
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

Miguel delegated story approval to his bots. He decides epic-level and security matters only. The rule is recorded here. It does not approve US-01–US-47. Those stories stay Draft until a bot sign-off names them. Epic approval is now signed by him. The next gate is story review and approval by his bots. E11 stories US-41–US-47 are drafted and are part of that gate. Drafting them is not the sign-off.

## Design-gate questions

These stay open. Miguel answers them at the technical design, ADR, and threat-model gate. They are not delegated, and that gate has not started:

1. Sync edit conflicts: the same item edited on two devices.
2. How a deletion on one device propagates.
3. How keys reach a new or second device.
4. What happens if the key or passphrase is lost.
5. Whether unlocking the whole app is required, separate from the sensitive-item lock.
6. Android developer verification for sideloaded APKs (enforced globally in 2027; ADB installs are exempt).
7. Which AI provider processes topics, and what are its data-retention terms.
8. How the topic API key or credential is stored and protected. No key baked into the APK in plain form.
9. Whether items marked sensitive are excluded from cloud topic processing.
10. What exactly is sent for topic extraction: full text, images, OCR output, or some other slice.

The five story-level questions now have consensus recommendations in US-01, US-15, US-31, US-33, and US-34 (Mikey's Dev Bot and Scout, reconciled by Mikey's Vault App Bot). US-31's language scope was later revised by Mikey's Dev Bot to English only. The Dev Bot reports that Miguel told it in chat that English is enough for v1 OCR. That revision is not approval. Those stories stay Draft. The FTS5 / Room 3 / SQLCipher spike on US-15 is a design-gate risk, not a decision. See the specification.

The specification gate and the epic gate have passed. Later gates stay closed until each is actually signed:

1. Epic approval by `mjbvilhena` (E1–E11, including later epic E10). **Passed** on 2026-10-17 at 13:29 IST. E10 is Approved as Later, not in v1.
2. Story review and approval of US-01–US-47 by Miguel's bots, under the delegation of 2026-10-17. **This is the gate the pipeline stops on.** E11 stories US-41–US-47 are drafted. Stories stay Draft.
3. Technical design and ADR approval, once those documents exist, and the threat model. The security questions above go to Miguel. The cipher is an ADR, not a line in the spec. That band has not started.

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

## Memory checklist

| Artefact | This run |
|---|---|
| Backlog | Written. Statuses are Draft or Not started. Nothing marked Done. |
| Vision ↔ spec | Spec links the vision and epics E1–E11. The vision is still not accepted. The specification is approved at revision `c288b7e`. |
| User-story index | `docs/product/stories/INDEX.md` lists US-01–US-47. Drafts are not approved. Story approval is delegated to Miguel's bots; that delegation is not a sign-off. Review pack: `docs/product/stories/REVIEW-PACK.md`. |
| Design-artefact index | None. No design files. |
| ADRs | None. None accepted. |
| `DOMAIN.md` / `LAYER.md` | Not created. Consultants found nothing, and inventing MUST/NEVER rules is forbidden. |
| README | Points at this gate. Does not claim a build. |

## Re-entry

No verify/implement loop has run. Loop count for implementation is 0.
