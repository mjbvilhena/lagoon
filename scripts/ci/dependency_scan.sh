#!/usr/bin/env bash
# US-88. Read the resolved Gradle graph and fail closed on a high finding.
set -euo pipefail

root="$(cd "$(dirname "${BASH_SOURCE[0]}")/../.." && pwd)"
cd "$root"

osv_tag="v2.7.0"
osv_commit="bfe2f84abd2f09fe0b83ba7f6dad40d296762f40"
out="$root/build/ci"
mkdir -p "$out"

fail() {
  echo "dependency scan failed closed: $*" >&2
  echo "this is not a clean scan" >&2
  exit 1
}

if ! timeout 600 ./gradlew exportResolvedGraph \
  --init-script scripts/ci/resolve-classpaths.init.gradle.kts \
  --no-daemon; then
  fail "resolver failed or timed out"
fi

lock="$out/gradle.lockfile"
if [[ ! -s "$lock" ]]; then
  fail "resolver produced no graph"
fi

remote="$(git ls-remote --tags "https://github.com/google/osv-scanner.git" \
  "refs/tags/${osv_tag}" "refs/tags/${osv_tag}^{}")" || fail "cannot read the osv-scanner tag"
peeled=""
while read -r sha ref; do
  if [[ "$ref" == "refs/tags/${osv_tag}^{}" ]]; then
    peeled="$sha"
  elif [[ -z "$peeled" && "$ref" == "refs/tags/${osv_tag}" ]]; then
    peeled="$sha"
  fi
done <<<"$remote"
if [[ "$peeled" != "$osv_commit" ]]; then
  fail "osv-scanner ${osv_tag} does not resolve to ${osv_commit}"
fi

binary="$out/osv-scanner"
curl --fail --silent --show-error --location \
  --output "$binary" \
  "https://github.com/google/osv-scanner/releases/download/${osv_tag}/osv-scanner_linux_amd64" \
  || fail "cannot download osv-scanner"
chmod +x "$binary"
reported="$("$binary" --version | awk '/^commit:/ { print $2 }')"
if [[ "$reported" != "$osv_commit" ]]; then
  fail "osv-scanner binary commit is ${reported:-missing}"
fi

set +e
timeout 300 "$binary" scan source \
  --lockfile "$lock" \
  --format json \
  --all-packages \
  --output-file "$out/osv.json" \
  --verbosity error
status=$?
set -e
if [[ "$status" -ne 0 && "$status" -ne 1 ]]; then
  fail "vulnerability service failed or timed out (exit ${status})"
fi
if [[ ! -s "$out/osv.json" ]]; then
  fail "vulnerability service produced no result"
fi

python3 scripts/ci/dependency_gate.py \
  "$out/osv.json" \
  "$lock" \
  "$root/docs/sdlc/vulnerability-exceptions.md"
