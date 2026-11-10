#!/usr/bin/env bash
# US-89. No settings.gradle means there is no SARIF and CodeQL fails.
set -euo pipefail

root="${1:-.}"
if [[ -f "$root/settings.gradle" || -f "$root/settings.gradle.kts" ]]; then
  exit 0
fi

echo "No Gradle project is present. There is no SARIF. The CodeQL job fails." >&2
exit 1
