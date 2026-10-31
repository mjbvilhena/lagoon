#!/usr/bin/env bash
# Build the Pages artifact: master's docs/mockups at the root, and the
# gh-pages prototype under wip/. Prototype files only.
set -euo pipefail

if [[ $# -ne 3 ]]; then
  echo "usage: assemble-pages-site.sh STABLE_DIR WIP_DIR DEST_DIR" >&2
  exit 2
fi

stable_src=$(cd "$1" && pwd)
wip_src=$(cd "$2" && pwd)
dest=$(mkdir -p "$3" && cd "$3" && pwd)
rm -rf "$dest"
mkdir -p "$dest"

copy_prototype() {
  local src="$1" out="$2" path base
  mkdir -p "$out"
  shopt -s dotglob nullglob
  for path in "$src"/*; do
    base=$(basename "$path")
    if [[ -d "$path" ]]; then
      case "$base" in
        .git | .github) continue ;;
        *)
          echo "refusing directory $base in $src" >&2
          exit 1
          ;;
      esac
    fi
    [[ -f "$path" ]] || {
      echo "refusing non-file $base in $src" >&2
      exit 1
    }
    case "$base" in
      README.md) continue ;;
      .nojekyll) cp "$path" "$out/$base" ;;
      .*)
        echo "refusing dotfile $base in $src" >&2
        exit 1
        ;;
      *.html | *.css | *.js | *.webmanifest | *.png | *.svg | *.webp | *.ico | *.gif | *.jpg | *.jpeg | *.woff | *.woff2 | *.json)
        cp "$path" "$out/$base"
        ;;
      *)
        echo "refusing non-prototype file $base in $src" >&2
        exit 1
        ;;
    esac
  done
  shopt -u dotglob nullglob
}

copy_prototype "$stable_src" "$dest"
copy_prototype "$wip_src" "$dest/wip"

python3 - "$dest" <<'PY'
import json
import re
import sys
from pathlib import Path

dest = Path(sys.argv[1])
required = (
    "index.html",
    "notes.html",
    "prototype.css",
    "prototype.js",
    "manifest.webmanifest",
    ".nojekyll",
)
for root in (dest, dest / "wip"):
    for name in required:
        path = root / name
        if not path.is_file():
            sys.exit(f"missing {path}")

for path in dest.rglob("*"):
    if path.name == "README.md" or path.suffix == ".md" or path.name == ".github":
        sys.exit(f"artifact contains {path}")

def refuse_root_absolute(path: Path, text: str) -> None:
    if re.search(r"""(?:href|src)\s*=\s*["']/(?!/)""", text):
        sys.exit(f"{path} has a root-absolute href or src")

for path in (
    dest / "index.html",
    dest / "notes.html",
    dest / "wip" / "index.html",
    dest / "wip" / "notes.html",
):
    refuse_root_absolute(path, path.read_text(encoding="utf-8"))

for path in (dest / "manifest.webmanifest", dest / "wip" / "manifest.webmanifest"):
    manifest = json.loads(path.read_text(encoding="utf-8"))
    for key in ("start_url", "scope"):
        value = manifest.get(key, "")
        if not isinstance(value, str) or not value.startswith("."):
            sys.exit(f"{path} {key} must stay relative, got {value!r}")

for path in (dest / "prototype.js", dest / "wip" / "prototype.js"):
    if "serviceWorker" in path.read_text(encoding="utf-8"):
        sys.exit(f"{path} registers a service worker; its scope must stay inside this copy")

for path in (dest / "notes.html", dest / "wip" / "notes.html"):
    if 'href="index.html#/trash"' not in path.read_text(encoding="utf-8"):
        sys.exit(f"{path} is missing the relative #/trash link")

notes = dest / "wip" / "notes.html"
text = notes.read_text(encoding="utf-8")
if 'class="wip"' not in text:
    if "</style>" not in text or "<main>" not in text:
        sys.exit("wip notes.html has no style block or main element")
    style = "  .wip { margin: 0 0 12px; font-size: 0.8rem; color: #5c5852; }\n"
    text = text.replace("</style>", style + "</style>", 1)
    text = text.replace("<main>\n", '<main>\n<p class="wip">Work in progress.</p>\n', 1)
    notes.write_text(text, encoding="utf-8")

wip_notes = notes.read_text(encoding="utf-8")
if 'class="wip"' not in wip_notes or "Work in progress." not in wip_notes:
    sys.exit("wip notes.html is missing the work-in-progress line")

for path in (dest / "index.html", dest / "notes.html", dest / "wip" / "index.html"):
    text = path.read_text(encoding="utf-8")
    if 'class="wip"' in text or "Work in progress." in text:
        sys.exit(f"{path} must not carry the work-in-progress line")
PY
