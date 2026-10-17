# Harness install

The AI SDLC harness is installed in this workspace from a pinned submodule.

| Field | Value |
|---|---|
| Source | [mjbvilhena/ai-sdlc-harness](https://github.com/mjbvilhena/ai-sdlc-harness) |
| Commit | `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604` |
| Submodule path | `third_party/ai-sdlc-harness` |
| Installer used | `install/install_cursor.sh --workspace` (this repo) |
| Skills and rules | `.cursor/commands/sdlc-*.md` (32 files) |
| MCP entry | `.cursor/mcp.json` server name `sdlc-knowledge` |

`agents/` is absent in that commit. The installer skipped it and continued.

## MCP command

The installer registered an absolute interpreter path on this machine:

`/workspace/third_party/ai-sdlc-harness/mcp-server/venv/bin/python`

That path is not portable. The committed `.cursor/mcp.json` points at `scripts/sdlc-knowledge-mcp.sh` instead. The script sets `WORKSPACE_ROOT` to this repo and execs the submodule venv. This deviation is recorded here so a later re-install is not mistaken for a product decision.

## Recreate the knowledge-server venv

The venv is not committed.

```bash
git submodule update --init third_party/ai-sdlc-harness
python3 -m venv third_party/ai-sdlc-harness/mcp-server/venv
third_party/ai-sdlc-harness/mcp-server/venv/bin/pip install -r third_party/ai-sdlc-harness/mcp-server/requirements.txt
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

No domain or layer rules were invented to fill those misses.
