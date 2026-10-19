# Harness install

The AI SDLC harness is installed in this workspace from a pinned submodule.

| Field | Value |
| --- | --- |
| Source | [mjbvilhena/ai-sdlc-harness](https://github.com/mjbvilhena/ai-sdlc-harness) |
| Commit | `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604` |
| Submodule path | `third_party/ai-sdlc-harness` |
| Installer used | `install/install_cursor.sh --workspace` (this repo) |
| Skills and rules | `.cursor/commands/sdlc-*.md` (30 files) |
| MCP entry | `.cursor/mcp.json` server name `sdlc-knowledge` |

`agents/` is absent in that commit. The installer skipped it and continued.

The two example stubs, `sdlc-example-skill` and `sdlc-example-rule`, are not installed here. Running `install/install_cursor.sh` again would copy them back. The harness submodule still contains those stubs.

## MCP command

The installer registered an absolute interpreter path on this machine:

`/workspace/third_party/ai-sdlc-harness/mcp-server/venv/bin/python`

That path is not portable. The committed `.cursor/mcp.json` points at `scripts/sdlc-knowledge-mcp.sh` instead. The script sets `WORKSPACE_ROOT` to this repo and execs the submodule venv. This deviation is recorded here so a later re-install is not mistaken for a product decision.

## Recreate the knowledge-server venv

The venv is not committed. `scripts/sdlc-knowledge-mcp.sh` runs `scripts/bootstrap-sdlc-mcp.sh` when the venv is missing or cannot import `mcp` and `thefuzz`. Bootstrap is safe to re-run. If it fails, it exits with a `sdlc-knowledge:` message and the server does not start.

```bash
git submodule update --init third_party/ai-sdlc-harness
scripts/bootstrap-sdlc-mcp.sh
```

## Tools called before drafting

From that server, this run called:

- `get_sdlc_template("lifecycle pipeline")`
- `get_sdlc_template("product spec")`
- `get_sdlc_template("user story")`
- `get_sdlc_template("repository setup")`
- `get_definition_of_done("epic")`
- `get_definition_of_done("user story")`
- `get_definition_of_done("repository setup")`
- `get_domain_consultant("vault")`, `get_domain_consultant("knowledge")`, and `get_domain_consultant("capture")` — no `DOMAIN.md` files exist
- `get_layer_consultant("ui")` — no `LAYER.md` files exist

No domain or layer rules were invented to fill those misses. That list is what the setup run found. `docs/domain/vault/DOMAIN.md` and `docs/layers/ui/LAYER.md`, `docs/layers/domain/LAYER.md`, and `docs/layers/data/LAYER.md` are now in the tree. They are Proposed. Needs approval by mjbvilhena. They are not approved.
