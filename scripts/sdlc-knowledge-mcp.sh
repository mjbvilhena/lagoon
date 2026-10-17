#!/usr/bin/env bash
# Start the AI SDLC harness knowledge server with this repo as WORKSPACE_ROOT.
# The Cursor installer writes an absolute venv path. This wrapper keeps the
# command portable across machines. See docs/sdlc/harness-install.md.
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
export WORKSPACE_ROOT="$ROOT"

VENV_PY="$ROOT/third_party/ai-sdlc-harness/mcp-server/venv/bin/python"
SERVER="$ROOT/third_party/ai-sdlc-harness/mcp-server/src/server.py"

if [[ ! -x "$VENV_PY" ]]; then
  echo "sdlc-knowledge: Python venv not found at $VENV_PY" >&2
  echo "From the repo root, run:" >&2
  echo "  python3 -m venv third_party/ai-sdlc-harness/mcp-server/venv" >&2
  echo "  third_party/ai-sdlc-harness/mcp-server/venv/bin/pip install -r third_party/ai-sdlc-harness/mcp-server/requirements.txt" >&2
  exit 1
fi

if [[ ! -f "$SERVER" ]]; then
  echo "sdlc-knowledge: server not found at $SERVER" >&2
  echo "Initialize the submodule: git submodule update --init third_party/ai-sdlc-harness" >&2
  exit 1
fi

exec "$VENV_PY" "$SERVER"
