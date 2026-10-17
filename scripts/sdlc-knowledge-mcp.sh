#!/usr/bin/env bash
# Start the AI SDLC harness knowledge server with this repo as WORKSPACE_ROOT.
# Creates the virtualenv when it is missing. See scripts/bootstrap-sdlc-mcp.sh
# and docs/sdlc/repository-setup.md.
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
export WORKSPACE_ROOT="$ROOT"

VENV_PY="$ROOT/third_party/ai-sdlc-harness/mcp-server/venv/bin/python"
SERVER="$ROOT/third_party/ai-sdlc-harness/mcp-server/src/server.py"
BOOT="$ROOT/scripts/bootstrap-sdlc-mcp.sh"

venv_ok() {
  [[ -x "$VENV_PY" ]] && "$VENV_PY" -c "import mcp, thefuzz" >/dev/null 2>&1
}

if ! venv_ok; then
  echo "sdlc-knowledge: venv missing or incomplete at $VENV_PY" >&2
  echo "sdlc-knowledge: running $BOOT" >&2
  if ! "$BOOT"; then
    echo "sdlc-knowledge: bootstrap failed. The MCP server did not start." >&2
    exit 1
  fi
fi

if [[ ! -f "$SERVER" ]]; then
  echo "sdlc-knowledge: server not found at $SERVER" >&2
  echo "Initialize the submodule: git submodule update --init third_party/ai-sdlc-harness" >&2
  exit 1
fi

if ! venv_ok; then
  echo "sdlc-knowledge: venv still unusable at $VENV_PY after bootstrap." >&2
  echo "Re-run scripts/bootstrap-sdlc-mcp.sh and read its error." >&2
  exit 1
fi

exec "$VENV_PY" "$SERVER"
