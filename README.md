# UX review prototype

Review aid only. `docs/technical_design/ux-design.md` is the source of truth. If this mockup and that file disagree, the file wins.

The status of that file is Proposed. This prototype is not an approval of it.

Open `index.html` in a browser. No server and no network are required. The page is the app, edge to edge. On a wide window the app stays a phone width and the surrounding page uses the same surface colour. Theme follows the system until Settings changes it. Add it to the home screen to open it full screen.

Reviewer notes, the placeholder list, and links to each screen are in [notes.html](notes.html). That page is not linked from inside the app.

The files are static. `.nojekyll` is in this directory so GitHub Pages does not run Jekyll. `.github/workflows/pages.yml` publishes this directory from `master`. The `gh-pages` branch serves the same files from the site root.
