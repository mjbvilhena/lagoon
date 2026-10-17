# Story review pack

> **Draft. Not approval.** Listing a story or a verdict here is not a sign-off.
> No story status is Approved. Round-2 cells that say "Approved under Miguel's delegation of 2026-10-17" are verdicts, not a status flip.
> Household and Leisure cells are recommendations only.
> Revised 2026-10-17 after the review of commit `40bb526`. The round-1 table below is kept.

## Decisions by Miguel

Miguel (`mjbvilhena`) confirmed these in chat on 2026-10-17 at 14:56 IST. They are not story approval.

- If the vault key is lost, the content is lost. No recovery. US-35 requires a clear warning before the key is created, and no recovery flow.
- App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
- Lock-screen notifications may show the item title and codes. US-34. This is for an ordinary item.
- The zip export is encrypted with the vault key. US-21.

Miguel (`mjbvilhena`) decided these in chat on 2026-10-17 at 15:59 IST. They are not story approval. They narrow two questions that were still open. The rest of US-75 and US-79 stays **Proposed: pending Miguel's approval**.

- A lock-screen reminder for a sensitive item shows only a generic "Lagoon reminder", using `VISIBILITY_PRIVATE` and a public version. It shows no title and no codes. Those details appear after unlock. US-75. Ordinary items still follow the 14:56 decision.
- A token-looking URL is not auto-fetched. A foggy placeholder image stands in for the preview, and he is asked whether to enable the preview for that link. Yes fetches under the proposed safe-fetch rules. No, or no answer, leaves the placeholder. He can enable it later after a no. US-79. The home row (US-10) does not draw that image. The item detail (US-11) shows it when it is the stored preview.

Miguel (`mjbvilhena`) decided this in chat on 2026-10-17 at 16:00 IST. It is not story approval. His words: "Requirement will be to let the user choose to disable (if possible), otherwise will have to disclose and accept".

- Official Google ML Kit docs do not document a switch that disables usage or diagnostic telemetry. The research note is `docs/product/research/2026-10-17-ml-kit-telemetry.md`. There is no Settings toggle, and no default to copy from the docs.
- Before the first OCR use he accepts a disclosure that the image and the recognized text stay on the device, and that ML Kit still sends usage and diagnostic metrics to Google. Declining, or leaving it unanswered, means OCR does not run and ML Kit is not started. He can withdraw the acceptance in Settings. US-07 and US-59. US-31 requires the bundled model.

Miguel (`mjbvilhena`) decided this in chat on 2026-10-17 at 18:05 IST. It is not story approval and it is not a new specification approval. Asked whether topic extraction should be on by default for new items, he said: "Topics on by default, with an off switch".

- The topic switch starts on. He can turn it off in Settings. US-41 and US-59. The quote is in `docs/product/amendments/2026-10-17-chat-decisions.md`.
- Sensitive-item exclusion is not part of that quote. It is the amendment approved on 2026-10-17 at 15:55 IST, recorded on US-75. The topic job keeps its own unmetered setting (US-44).

Miguel (`mjbvilhena`) removed stories in chat on 2026-10-17. This is not story approval. Numbers are not reused. No new story replaces them.

- At 19:00 IST: "68, 69, and 82 to 84 should be removed." US-68, US-69, US-82, US-83, and US-84 are gone.
- At 19:01 IST: "63 can be removed as well." US-63 is gone.
- Picking merchants out of a receipt stays Later, with no story, as the specification already says.

Miguel (`mjbvilhena`) at 19:02 IST left US-28, US-40, US-41, and US-53 unchanged, including US-53 reduced motion. That is not story approval. The earlier proposed trims on those four stories are not applied.

Miguel (`mjbvilhena`) at 19:04 IST said each remaining story must map to an epic. Parent epics are set. Where the approved epic does not already cover the story, a proposed amendment sits in that epic under "Proposed amendment 2026-10-17 (awaiting mjbvilhena approval)". None of those amendments is approved. Conflicts are flagged in `docs/product/stories/TRACEABILITY.md` and were not written in as scope.

## Open questions for Miguel

Not decided. Not delegated.

- Sync conflicts, and how a deletion propagates. Options recorded on US-73, not chosen. One option from Scout is tombstones, per-field last-writer-wins, and merged notes.
- How the vault key reaches a new device. US-74.
- Exact alarms. That would amend E9. US-77. Stories keep inexact alarms.
- Topic provider, its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. US-76. The release APK must not contain that key in plaintext.
- Whether 09:00 should follow a fixed zone, such as Europe/Dublin, instead of the phone. Stories use the device local zone.
- Full-screen QR and barcode display. No story.
- US-28 keeps "Add date" as a Must. Miguel has not moved it.
- A per-item "Include in topics" control on US-75. It goes beyond the 18:05 IST quote. It stays Proposed.
- Whether a sensitive notification's private version is also only "Lagoon reminder". Android can reveal the private version when the device shows all notification content. US-75 records that as a Proposed option. Miguel has not chosen it.
- Whether the vault key is bound to device authentication. US-35 does not choose it. An OS authentication change is no longer a Must on that story.
- Reminder time zone. Stories still use the device local zone.

## Open questions delegated to bots

On 2026-10-17 at 14:56 IST Miguel delegated these to his bots. He did not decide them. Round 2 writes proposed criteria on the stories. Each block is marked **Proposed: pending Miguel's approval**. That mark is not his approval.

- Sensitive-item handling in lists, search, export, the cloud call, and screenshots. US-75. The lock-screen reminder is no longer in this list. He decided it at 15:59 IST.
- Drive scope and sign-out. US-78. The approved specification still names `appDataFolder`.
- Link-preview skipping. US-79. Token-looking URLs are no longer in this list. He decided them at 15:59 IST. The host rules stay proposed.
- Sharing out. US-64. Epic E3 includes it as a Should. The criteria stay proposed.
- Permissions. US-80. US-34 still asks for `POST_NOTIFICATIONS` at the first reminder, which is the recorded specification text.
- Sideloading. US-81. US-24 still requires a signed APK because epic E6 does.

## Amendment applied

`docs/product/amendments/2026-10-17-security-delegation.md` is **Approved** by `mjbvilhena` on 2026-10-17 at 15:55 IST. His words: "Yes". Sections 1–6 are applied to the specification and to epics E1, E3, E8, and E11. Section 7 is the vision rewrite. He did not want a supersession sentence.

The stale drift list from the round-2 pack is removed. Those sentences are no longer in the approved files.

US-61 cites the approved amendment and stays Draft. US-64 cites it for the Should in E3. The acceptance criteria on US-64, US-75, US-78, US-79, US-80, and US-81 stay **Proposed: pending Miguel's approval**. The amendment approved the delegation, not those criteria.

## Proposed threat note

`docs/product/amendments/2026-10-17-proposed-threat-note.md` is a hypothesis note for the six proposed stories. It is not an approved threat model. The design band stays closed.

## Definition of Done, artefact shape

`sdlc-dod-checker` used `get_definition_of_done("user story")` by importing `third_party/ai-sdlc-harness/mcp-server/src/server.py` with the virtualenv Python from `scripts/bootstrap-sdlc-mcp.sh` on PR #4. This branch does not contain that script. The virtualenv was already on disk. This is artefact shape only.

Implementation DoD fails for every story: there is no app and no automation, so no story is Done.

Fail artefact shape: US-73, US-74, US-76, US-77. Each holds an open question for Miguel and has no asserting criterion. Every other story passes shape, including US-41 and US-59. That pass is not story approval. Round 4 records no approvals.

The seven required checks are no-ops until a Gradle project exists. `ubuntu-latest` moves to Ubuntu 26.04 on 2026-10-19. Pinning `ubuntu-24.04` is a separate pull request, not this one. The script that starts the knowledge server lives on PR #4. This branch imports `server.py` with the virtualenv Python. That import is not a live MCP session.

Pass artefact shape: every other story, including US-64, US-75, US-78, US-79, US-80, and US-81. Those six pass on shape only. Their criteria are marked **Proposed: pending Miguel's approval**. That pass is not his approval, and it is not a status of Approved.

Open questions in the lists above were not turned into deciding criteria. US-56 and US-57 are chores the review asked for.

## Review verdicts at 9e7de63

Dev Bot, Scout, Mikey's Assistant, Household, and Leisure. Only US-09 and US-27 were Approve from all five. Scout's Approve verdicts, under Miguel's delegation, also cover US-03, US-04, US-07, US-10, US-11, US-16, US-17, US-19, US-24, US-26, US-28, US-30, US-36, US-38, and US-39. Where the consolidated review did not give a separate word, the cell says so. A verdict is not an approval.

| ID | Dev Bot | Scout | Assistant | Household | Leisure |
|---|---|---|---|---|---|
| US-01 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-02 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-03 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-04 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-05 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-06 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-07 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-08 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-09 | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval |
| US-10 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-11 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-12 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-13 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-14 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-15 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-16 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-17 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-18 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-19 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-20 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-21 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-22 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-23 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-24 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-25 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-26 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-27 | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval |
| US-28 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-29 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-30 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-31 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-32 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-33 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-34 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-35 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-36 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-37 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-38 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-39 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-40 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-41 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-42 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-43 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-44 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-45 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-46 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-47 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |

US-48 through US-81 were written after that review. They have no verdict from it.

## Review verdicts at 40bb526

Dev Bot, Scout, and Mikey's Assistant may record an approval under Miguel's delegation of 2026-10-17. Household and Leisure are recommendations only, until Miguel confirms the delegation to them directly. A cell is not a story status. No status was flipped to Approved.

Clean approvals from all five at `40bb526`, with Household and Leisure as recommendations: US-01, US-05, US-06, US-08, US-09, US-11, US-14, US-15, US-16, US-17, US-19, US-20, US-22, US-23, US-24, US-27, US-30, US-31, US-32, US-33, US-38, US-42, US-43, US-45, US-46, US-47, US-50, US-56, US-57, US-58, US-60, US-67, US-71.

Conditional, not clean: US-18 depends on US-78. US-25 and US-52 were Dev Bot [RD] at `40bb526`. The zip is encrypted with the vault key because Miguel said so in chat on 2026-10-17 at 14:56 IST, not because the 15:55 IST amendment met that cell. The cells below stay as recorded at `40bb526`. This run records no new approval. Story status stays Draft.

Approved stories edited in the round-2 revision, minimal, and needing re-confirm: US-01 (one title order, leftover text aligned with US-02, sending app only when the platform reports it), US-45 (a hard-deleted item's topic note is removed, matching US-13), US-57 (the spike also runs on an API 26 emulator and an arm64 phone, and records the APK size change).

US-11 also had a clean verdict at `40bb526`. The 15:59 IST edit adds one line: when the stored preview is the foggy placeholder from US-79, the detail shows that image and does not fetch. That edit needs re-confirm. Status stays Draft. US-10 does not draw a preview, so the placeholder is not on the home row.

US-31 also had a clean verdict at `40bb526`. The 16:00 IST edit is a factual note: ML Kit metrics are the US-07 disclosure, and the first OCR use waits on that acceptance. Status stays Draft. That edit needs re-confirm. US-07 was not a clean all-five approval, so its new criteria are not a re-confirm of a unanimous verdict.

Round 3, against `df8c088`, edited these clean-verdict stories again. They need re-confirm. Status stays Draft. This run records no approval: US-08 (the skip rules are US-79, and enrichment waits for the quick-add row), US-15 (merchant column), US-19 (the topic queue has its own unmetered setting), US-31 (bundled `com.google.mlkit:text-recognition` only), US-33 (no snapshot for a token-looking link until he confirms, none for a sensitive item, host rules from US-79), US-50 (merchant weight 5).

The 18:05 IST edit changes US-41 and US-59 only. Those stories were not clean approvals at `40bb526`, so this edit does not add them to the re-confirm list. Status stays Draft.

Round 4, against `5ba70f5`, records no approvals. It edits stories and moves the 15:59, 16:00, and 18:05 IST chat decisions into `docs/product/amendments/2026-10-17-chat-decisions.md`. Sensitive-item exclusion stays cited to the 15:55 IST amendment. Status stays Draft.

| ID | Dev Bot | Scout | Mikey's Assistant | Household | Leisure |
|---|---|---|---|---|---|
| US-01 | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. |
| US-02 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-03 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-04 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-05 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-06 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-07 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-08 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-09 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-10 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-11 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-12 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-13 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-14 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-15 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-16 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-18 | Conditional on US-78. Not a clean approval. | Conditional on US-78. Not a clean approval. | Conditional on US-78. Not a clean approval. | Recommend approve, conditional on US-78 | Recommend approve, conditional on US-78 |
| US-19 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-20 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-21 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-22 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-23 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-24 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-25 | [RD] conditional until Miguel approves the amendment | Not recorded as a clean approval at 40bb526 | Not recorded as a clean approval at 40bb526 | Recommend approve | Recommend approve |
| US-26 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-27 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-28 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-29 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-30 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-31 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-32 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-33 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-34 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-35 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-36 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-37 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-38 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-39 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-40 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-41 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-42 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-43 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-44 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-45 | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. |
| US-46 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-47 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-48 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-49 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-50 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-51 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-52 | [RD] conditional until Miguel approves the amendment | Not recorded as a clean approval at 40bb526 | Not recorded as a clean approval at 40bb526 | Recommend approve | Recommend approve |
| US-53 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-54 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-55 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-56 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-57 | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Approved under Miguel's delegation of 2026-10-17. Verdict is on 40bb526. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. | Recommend approve. This revision edited the file. Re-confirm. |
| US-58 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-59 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-60 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-61 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-62 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-64 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |
| US-65 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-66 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-67 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-70 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-71 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Approved under Miguel's delegation of 2026-10-17 | Recommend approve | Recommend approve |
| US-72 | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes requested at 40bb526. Not a clean approval. | Changes | Changes |
| US-73 | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Recommend leaving open | Recommend leaving open |
| US-74 | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Recommend leaving open | Recommend leaving open |
| US-75 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |
| US-76 | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Recommend leaving open | Recommend leaving open |
| US-77 | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Open for Miguel. Not decided. | Recommend leaving open | Recommend leaving open |
| US-78 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |
| US-79 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |
| US-80 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |
| US-81 | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Proposed: pending Miguel's approval | Recommend the proposal. Pending Miguel's approval. | Recommend the proposal. Pending Miguel's approval. |

## Stories

Must counts are scenarios marked Must. Should and Could are named when present. Proposed criteria are not approvals.

| ID | Title | Epic | Summary | MoSCoW | Must | Open |
|---|---|---|---|---|---|---|
| US-01 | Share a link into the vault. | E1 | the page is kept on my phone without me copying the address | Must | 5 | None on this story |
| US-02 | Share plain text into the vault. | E1 | a passage I want to keep is stored without me retyping it | Must | 5 | None on this story |
| US-03 | Share an image into the vault. | E1 | the picture and any words I attached are kept on the phone | Must | 6 | None on this story |
| US-04 | Share a PDF into the vault. | E1 | the file and the text it already contains are searchable later | Must | 5 | None on this story |
| US-05 | Add a note by hand. | E1 | I can keep something that did not come from a share | Must | 4 | None on this story |
| US-06 | Add a URL by hand. | E1 | I can keep a page when I am not in the app that showed it | Must | 4 | None on this story |
| US-07 | Use the saved vault with no network and no analytics. | E1 | I can read and search it offline, and the app does not report my use | Must | 8 | None. Disclosure decided 16:00 IST |
| US-08 | Enrich a saved link from the page. | E2 | I can recognise the page later without opening it | Must | 8 | None on this story |
| US-09 | Keep the link when the page cannot be fetched. | E2 | a failed fetch does not lose the link (1 Should) | Must | 2 | None on this story |
| US-10 | Browse saved items, newest first. | E3 | the thing I just kept is the first row | Must | 5 | None on this story |
| US-11 | Read a saved item. | E3 | I can use it without going back to the app it came from | Must | 5 | None on this story |
| US-12 | Edit a saved item. | E3 | I can correct it and say why it matters | Must | 5 | None on this story |
| US-13 | Delete an item and undo that delete. | E3 | a mistake does not destroy the item, and a finished delete really removes it | Must | 4 | None on this story |
| US-14 | Manage tags on saved items. | E3 | I can file items without inventing a second spelling of the same tag | Must | 6 | None on this story |
| US-15 | Search the vault and see highlighted matches. | E3 | I can find an item and see why it matched | Must | 5 | None on this story |
| US-16 | Filter items by type and by tag. | E3 | I can look at one kind of item without reading the rest | Must | 4 | None on this story |
| US-17 | See that sync is not configured. | E4 | the app stays usable and does not pretend it can sync | Must | 4 | None on this story |
| US-18 | Sign in so the vault can sync. | E4 | background sync is allowed to use my private Drive app space | Must | 4 | Conditional on proposed US-78 |
| US-19 | Prefer an unmetered network for background sync. | E4 | sync does not use mobile data by default | Must | 4 | None on this story |
| US-20 | Receive the vault on another install. | E4 | the vault follows me without a restore button | Must | 4 | None on this story |
| US-21 | Export a zip to a place he chooses. | E4 | I have a file fallback that is not a Drive button | Must | 5 | None on this story |
| US-22 | Choose light, dark, or the system theme. | E5 | the vault follows my phone unless I choose otherwise | Must | 4 | None on this story |
| US-23 | Show a launcher icon and the name Lagoon. | E5 | I can find the app and share into it | Must | 3 | None on this story |
| US-24 | Install a signed release APK without the Play Store. | E6 | I can install Lagoon without a store listing | Must | 5 | None on this story |
| US-25 | Cover search, sync and zip packaging, and a basic screen with automated tests. | E6 | those parts cannot change silently before I install a build | Must | 4 | None on this story |
| US-26 | Follow written steps to build, sideload, and create the OAuth client. | E6 | I can install a build and turn sync on without guessing | Must | 5 | None on this story |
| US-27 | Review screenshots of the main screens. | E6 | I can see those screens without installing a build first | Must | 3 | None on this story |
| US-28 | Optionally tag a save from the last-used tags. | E1 | I can file the item without a second form | Must | 7 | None on this story |
| US-29 | Rename a tag, merging only after he confirms a clash. | E3 | I do not have to retag each item, and a clash does not happen by accident | Must | 5 | None on this story |
| US-30 | Do not offer backup or restore controls. | E4 | sync stays in the background and I am not asked to run a backup | Must | 3 | None on this story |
| US-31 | Recognize English text on the device. | E7 | I can search that text without sending the image to a cloud OCR service | Must | 7 | None on this story |
| US-32 | Keep more than one attachment on an item. | E1 | a receipt and a photo of the same thing stay together | Must | 5 | None on this story |
| US-33 | Keep an offline snapshot of a saved page. | E2 | I can read the page when the network is gone, and still keep the original URL | Must | 11 | None. Fetch gates are US-79 |
| US-34 | Set a reminder. | E9 | I do not forget the item | Must | 9 | Exact alarms and a fixed time zone stay open. Sensitive lock-screen text is decided on US-75 |
| US-35 | Encrypt the vault at rest. | E8 | a copy of the phone's storage does not contain the vault in the clear, and I am not promised a recovery that does not exist | Must | 9 | Key transport and auth-binding stay open. Loss is decided |
| US-36 | Sync only ciphertext. | E8 | Google cannot read the vault | Must | 3 | None on this story |
| US-37 | Lock sensitive items, and optionally lock the app. | E8 | private items stay closed, and I can still lock the app when the phone has no biometric | Must | 10 | None on this story |
| US-38 | File new captures from an Untagged inbox. | E3 | new captures do not disappear into the full list before I tag them | Must | 5 | None on this story |
| US-39 | Sort and filter the list by when it was saved. | E3 | I can limit the list to a day or a range | Must | 7 | None on this story |
| US-40 | Fill an empty title from the page or the file. | E1 | a one-tap save is not called Untitled | Must | 9 | None on this story |
| US-41 | Extract topics when an item is saved. | E11 | I do not have to type the subject by hand | Must | 12 | Provider, credential, and payload stay open. Per-item opt-in stays open |
| US-42 | Link an item to a topic the vault already has. | E11 | the subject I already have grows instead of starting over | Must | 5 | None on this story |
| US-43 | Confirm a topic before it is created. | E11 | a subject I do not want is never created, and one-tap capture stays one tap | Must | 6 | None on this story |
| US-44 | Queue topic processing while offline. | E11 | capturing something never waits on the cloud | Must | 6 | None on this story |
| US-45 | Read a topic. | E11 | I can look across what I saved on that subject | Must | 5 | None on this story |
| US-46 | Reject, rename, or merge a suggested topic. | E11 | the subjects stay the ones I mean | Must | 3 | None on this story |
| US-47 | Keep the item when the topic call fails. | E11 | a cloud problem does not lose what I saved or invent a topic | Must | 6 | None on this story |
| US-48 | Manage a topic that is already saved. | E11 | I can correct a subject after I have confirmed it | Must | 5 | None on this story |
| US-49 | Update a link that was saved before, and undo that update. | E1 | one page does not become two items, and a mistake is reversible | Must | 7 | None on this story |
| US-50 | Rank search matches. | E3 | the item I meant is not buried under a weak match | Must | 6 | None on this story |
| US-51 | Sync in the background and show errors. | E4 | I do not have to press a backup button and I can see a real failure | Must | 5 | None on this story |
| US-52 | Import a zip and merge it. | E4 | a file fallback can fill this phone without wiping it | Must | 3 | None on this story |
| US-53 | Use the teal palette, optional dynamic color, and reduced motion. | E5 | I can read the app in light and dark and with reduced motion | Must | 6 | None on this story |
| US-54 | Notify before an expiry. | E9 | I can act before the date, and a close date does not schedule a notice that would already have passed | Must | 6 | None on this story |
| US-55 | Reschedule reminders after a restart or a clock change. | E9 | a notification is not lost because the phone restarted | Must | 5 | Time zone stays open |
| US-56 | Keep a stable identity for each item. | E4 | an edit or a second device does not fork the item or lose it | Must | 3 | None on this story |
| US-57 | Prove that search and encryption can run together. | E3 | search and encryption are not built on a combination that cannot run | Must | 2 | None on this story |
| US-58 | Run enrichment, snapshot, OCR, and topics in that order. | E1 | search and topics see the text that actually exists | Must | 3 | None on this story |
| US-59 | Use one Settings screen. | E4 | I do not hunt through the app for those controls | Must | 4 | None. Topic switch starts on. ML Kit disclosure is the US-07 slot |
| US-60 | Browse topics and pending suggestions. | E11 | I can open a subject and answer suggestions without a prompt on every save | Must | 3 | None on this story |
| US-61 | Filter by where an item came from. | E1 | I can find what I saved from one site or one app | Should | 0 | In E1. Filter only. Story stays Draft |
| US-62 | See what is coming due. | E9 | I can see what is due without opening each item (1 Should) | Should | 3 | None on this story |
| US-64 | Share or open an item in another app. | E3 | I can use the item outside Lagoon without handing over the vault (8 Should) | Proposed Should | 0 | Should is in E3. Criteria stay proposed |
| US-65 | Add a photo from the camera. | E1 | I can keep a page without a share from another app (2 Should) | Should | 0 | None on this story |
| US-66 | Keep a trash beyond undo. | E3 | I can recover it after the undo snackbar has gone (2 Should) | Should | 0 | None on this story |
| US-67 | Notice a duplicate image or PDF. | E1 | I do not keep two copies of the same file (1 Could) | Could | 0 | None on this story |
| US-70 | See what is not ready for offline. | E2 | I know what will be missing before I go offline (1 Could) | Could | 0 | None on this story |
| US-71 | Triage the inbox in a batch. | E3 | filing does not mean opening each row (1 Could) | Could | 0 | None on this story |
| US-72 | Score whether topics are useful. | E11 | usefulness can be judged without me hand-labeling items and without sending counts off the phone (3 Could) | Could | 0 | None on this story |
| US-73 | Decide sync conflicts and deletions. | E4 | two phones do not invent a winner | Not set | 0 | Conflicts and deletions, for Miguel |
| US-74 | Decide how the vault key reaches another device. | E8 | ciphertext on Drive is not a dead end | Not set | 0 | Key transport, for Miguel |
| US-75 | Limit what a sensitive item reveals. | E8 | the sensitive mark limits what is shown | Proposed Must | 12 | Lock-screen reminder decided 15:59 IST. Topic exclusion is the 15:55 IST amendment. Other criteria stay proposed |
| US-76 | Decide the topic call's provider, credential, and payload. | E11 | readable content does not leave the phone under a rule nobody chose | Not set | 0 | Provider, credential, payload, for Miguel |
| US-77 | Decide whether reminders may use exact alarms. | E9 | I do not get a permission the epic did not ask for | Not set | 0 | Exact alarms, for Miguel |
| US-78 | Keep Drive to app data, and sign out without deleting the phone. | E4 | Drive access stays narrow and a sign-out is not a wipe | Proposed Must | 8 | Proposed, pending Miguel |
| US-79 | Skip unsafe link previews. | E2 | saving a link does not call a private or local host | Proposed Must | 12 | Token-looking URLs decided 15:59 IST. Other criteria stay proposed |
| US-80 | Keep the permission set to an allowlist. | E6 | an install does not ask for storage, camera, or location | Proposed Must | 5 | Proposed, pending Miguel |
| US-81 | Publish a sideload signing policy. | E6 | a later install can replace the app without losing the vault | Proposed Must | 6 | Proposed, pending Miguel |
