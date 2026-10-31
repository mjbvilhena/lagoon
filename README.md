# UX review prototype

Review aid only. `docs/technical_design/ux-design.md` is the source of truth. If this mockup and that file disagree, the file wins.

The status of that file is Proposed. This prototype is not an approval of it.

Open `index.html` in a browser. No server and no network are required. The page centers one phone frame of 360 by 800 CSS pixels. System, Light, and Dark, and the screen menu, sit outside the frame. Choices the UX file leaves open are in the collapsible panel under the phone.

The files are static. `.nojekyll` is in this directory so GitHub Pages does not run Jekyll. `.github/workflows/pages.yml` publishes this directory from `master`. The `gh-pages` branch serves the same files from the site root.
