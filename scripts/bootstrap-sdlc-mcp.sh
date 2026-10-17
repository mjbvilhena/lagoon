#!/usr/bin/env bash
# Create the sdlc-knowledge virtualenv when it is missing or incomplete.
# Safe to re-run. Does not commit the venv. See docs/sdlc/repository-setup.md.
set -euo pipefail

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
MCP_DIR="$ROOT/third_party/ai-sdlc-harness/mcp-server"
VENV_PY="$MCP_DIR/venv/bin/python"
REQ="$MCP_DIR/requirements.txt"
SERVER="$MCP_DIR/src/server.py"

fail() {
  echo "sdlc-knowledge: $*" >&2
  exit 1
}

if [[ ! -f "$SERVER" ]]; then
  fail "server not found at $SERVER. Initialize the submodule: git submodule update --init third_party/ai-sdlc-harness"
fi

if [[ ! -f "$REQ" ]]; then
  fail "requirements not found at $REQ"
fi

if ! command -v python3 >/dev/null 2>&1; then
  fail "python3 is not on PATH. Install Python 3, then re-run scripts/bootstrap-sdlc-mcp.sh"
fi

if [[ -x "$VENV_PY" ]] && "$VENV_PY" -c "import mcp, thefuzz" >/dev/null 2>&1; then
  echo "sdlc-knowledge: venv already usable at $VENV_PY"
  exit 0
fi

echo "sdlc-knowledge: creating venv at $MCP_DIR/venv"
rm -rf "$MCP_DIR/venv"
if ! python3 -m venv "$MCP_DIR/venv"; then
  rm -rf "$MCP_DIR/venv"
  fail "could not create the virtualenv. Install the Python venv package (on Debian/Ubuntu: python3-venv), then re-run scripts/bootstrap-sdlc-mcp.sh"
fi

if ! "$MCP_DIR/venv/bin/pip" install -r "$REQ"; then
  fail "pip install failed. The server did not start. Fix the network or $REQ, then re-run scripts/bootstrap-sdlc-mcp.sh"
fi

if ! "$VENV_PY" -c "import mcp, thefuzz" >/dev/null 2>&1; then
  fail "venv exists but mcp and thefuzz did not import. Re-run scripts/bootstrap-sdlc-mcp.sh"
fi

echo "sdlc-knowledge: venv ready at $VENV_PY"
