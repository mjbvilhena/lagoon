# UX review prototype

Review aid only. `docs/technical_design/ux-design.md` is the source of truth. If this mockup and that file disagree, the file wins.

This prototype was built from the approved file. Approved by mjbvilhena on 2026-10-31. The placeholders it used for navigation, Settings order, passphrase guidance, the undo snackbar, the fog, Dynamic color on-accent colors, the launcher mark, the system-ui typeface, and the clock-warning text are now decisions in that file. The clock-warning threshold is still open.

Open `index.html` in a browser. No server and no network are required. The page is the app, edge to edge. On a wide window the app stays a phone width and the surrounding page uses the same surface colour. Theme follows the system until Settings changes it. Add it to the home screen to open it full screen.

Reviewer notes, the placeholder list, and links to each screen are in [notes.html](notes.html). That page is not linked from inside the app.

The files are static. `.nojekyll` is in this directory so GitHub Pages does not run Jekyll. `.github/workflows/pages.yml` publishes this directory from `master`. The `gh-pages` branch serves the same files from the site root.
