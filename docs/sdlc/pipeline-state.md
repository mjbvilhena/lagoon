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
| Product spec | `docs/product/specification.md` | Draft. Pending review by `mjbvilhena`. **This is the gate this run stops on.** |
| Epic approval | `docs/product/epics/E1` through `E10` | Draft. E1–E9 are the v1 set. E10 is later and has no stories. Not approved. |
| Story refine | `docs/product/stories/US-01` through `US-40` and `INDEX.md` | Draft. All of those stories are in the v1 set. Not approved. |
| Story approval | Rule recorded. No story signed. | Not given. On 2026-10-17 Miguel delegated story approval to his bots. He decides epic-level and security matters only. The delegation is not a sign-off on US-01–US-40. |
| Technical design / ADRs | No RFC, API design, or ADR files | Not started. |
| UX | No UX artefact | Not started. |
| Test strategy / threat model | No test plan, e2e plan, or threat model | Not started. A threat model is required in the design band before implementation (encryption, biometric lock, Drive sync). |
| Domain / layer | No `DOMAIN.md` or `LAYER.md` | Not started. Bounds have not been designed. |
| Repository checks | Workflows, CODEOWNERS, gitignore, README | Present for markdown and secrets. See `docs/sdlc/repository-setup.md`. Not a product approval. |
| Implement | No application code | Not started. Blocked. |

## Stop

**Stopped at product specification approval.**

`mjbvilhena` is the named approver, and the specification is pending his review. Nothing in git history, this file, or the backlog counts as his sign-off. A future approval has to name the artefact and say it is approved, or say to treat it as approved.

An instruction to record the sentence "Approve the spec, answer the 6 security questions at design" (chat, 2026-10-17, 09:20 IST) as spec approval was withdrawn before any approval was written. No approval commit exists. The specification stays Draft.

He delegated Q1–Q4 and Q6–Q9. Mikey's Dev Bot recommended answers, and Scout, Mikey's Assistant, Mikey's Household Assistant, and Mikey's Leisure Assistant reviewed them. Q1 and Q2 were later changed by the joint recommendation of Scout and Mikey's Vault App Bot: the name is Lagoon and the application id is `com.mjbvilhena.lagoon`. Scout's web search found no clashing notes, bookmark, or vault app called Lagoon. Play Store and trademark checks were not done. That record is in `docs/product/specification.md`. It is not approval.

He later decided, in his own words where he gave them: seamless sync instead of backup and restore (Q5 resolved as merge); extra encryption at rest and as ciphertext in Drive (Q10 resolved as yes); and a v1 scope of on-device OCR, reminders, multiple attachments, offline page snapshots, and a biometric lock on sensitive items. Trip or collection grouping is later (E10). Those decisions are not approval of the specification, the epics, or the stories.

## Story approval rule (2026-10-17)

Miguel delegated story approval to his bots. He decides epic-level and security matters only. The rule is recorded here. It does not approve US-01–US-40. Those stories stay Draft until a bot sign-off names them. Epic approval stays with him, and that gate is not prepared while this specification is still Draft.

## Design-gate questions

These six stay open. Miguel answers them at the technical design, ADR, and threat-model gate. They are not delegated, and that gate has not started:

1. Sync edit conflicts: the same item edited on two devices.
2. How a deletion on one device propagates.
3. How keys reach a new or second device.
4. What happens if the key or passphrase is lost.
5. Whether unlocking the whole app is required, separate from the sensitive-item lock.
6. Android developer verification for sideloaded APKs (enforced globally in 2027; ADB installs are exempt).

Story-level questions may be delegated. See the specification.

After spec approval, these gates are still closed until each is actually signed:

1. Epic approval by `mjbvilhena` (E1–E9 for v1; E10 is later and not in that v1 gate). Not prepared yet.
2. Story approval of US-01–US-40 by Miguel's bots, under the rule above.
3. Technical design and ADR approval, once those documents exist. The cipher is an ADR, not a line in the spec. The six questions above are his to answer in that band.

UX, test strategy, and a threat model sit in the design band after story approval. The threat model is required before implementation. They were not skipped and they were not written. Implementation stays illegal until those gates are actually passed or he explicitly skips one and the skip is recorded here.

## Memory checklist

| Artefact | This run |
|---|---|
| Backlog | Written. Statuses are Draft or Not started. Nothing marked Done. |
| Vision ↔ spec | Spec links the vision and epics E1–E10. The vision still restates the original backup request. The spec supersedes that. Neither file is approved. |
| User-story index | `docs/product/stories/INDEX.md`. Drafts are not approved. Story approval is delegated to Miguel's bots; that delegation is not a sign-off. |
| Design-artefact index | None. No design files. |
| ADRs | None. None accepted. |
| `DOMAIN.md` / `LAYER.md` | Not created. Consultants found nothing, and inventing MUST/NEVER rules is forbidden. |
| README | Points at this gate. Does not claim a build. |

## Re-entry

No verify/implement loop has run. Loop count for implementation is 0.
