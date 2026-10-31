/* Lagoon review prototype. Local only. No network. */
(function () {
  "use strict";

  var device = document.getElementById("app");
  var NOW = "2026-10-31T12:00:00Z";
  var demoPhrase = ["island", "ferry", "2026"].join("-");
  var commonList = ["letmeinplease1", "adminadmin1234", "qwertyuiopasdf", "iloveyou12345"];
  var seq = 100;
  var lastScreen = "";
  var snackTimer = 0;
  var waitTimer = 0;

  function uid() { seq += 1; return "id" + seq; }

  function esc(s) {
    return String(s == null ? "" : s)
      .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
  }

  function svg(inner) {
    return '<svg viewBox="0 0 24 24" aria-hidden="true">' + inner + "</svg>";
  }

  var I = {
    back: svg('<path d="M14.5 5.5 8 12l6.5 6.5" fill="none" stroke="currentColor" stroke-width="2"/>'),
    home: svg('<path d="M4 10.5 12 4l8 6.5V20H4z"/>'),
    search: svg('<path d="M10 4a6 6 0 1 0 3.7 10.7L20 21.2 21.2 20l-6.3-6.3A6 6 0 0 0 10 4z"/>'),
    inbox: svg('<path d="M3 6h18v12H3zm0 7h5l2 2h4l2-2h5"/>'),
    topics: svg('<path d="M4 12 12 4h7v7l-8 8z M16.5 7.5h.01"/>'),
    settings: svg('<path d="M4 7h10M18 7h2M4 12h2M10 12h10M4 17h8M16 17h4" fill="none" stroke="currentColor" stroke-width="2"/>'),
    add: svg('<path d="M11 5h2v14h-2zM5 11h14v2H5z"/>'),
    camera: svg('<path d="M8 7 9.5 5h5L16 7h3v12H5V7zm4 10a4 4 0 1 0 0-8 4 4 0 0 0 0 8z"/>'),
    lock: svg('<path d="M7 10V8a5 5 0 0 1 10 0v2h1v10H6V10zm2 0h6V8a3 3 0 0 0-6 0z"/>'),
    close: svg('<path d="M6 6l12 12M18 6 6 18" fill="none" stroke="currentColor" stroke-width="2"/>')
  };

  var COPY = {
    saved: "Saved to Lagoon",
    repeat: "Saved before, added to existing",
    undo: "Undo",
    keep: "Keep separate",
    emptyHome: "Nothing is saved yet.",
    noteEmpty: "The note was not saved.",
    titleRequired: "A title is required.",
    enrich: "Page details are not available",
    previewAsk: "Enable the preview for this link?",
    yes: "Yes",
    no: "No",
    fetch: "Fetch preview",
    topicsBtn: "Get topics",
    tryAgain: "Try again",
    noTopics: "No topics",
    noTopicList: "There are no topics.",
    nothingLinked: "Nothing is linked.",
    sensitive: "Sensitive",
    reminder: "Lagoon reminder",
    which: "Which item?",
    syncOff: "Sync is not configured.",
    syncOut: "Sync is not signed in. Items on this phone are still here.",
    syncIn: "Signed in",
    revoked: "Sign-in was revoked. Items on this phone are still here.",
    full: "Drive is full. The item on this phone is still here.",
    upload: "The upload failed. The item on this phone is still here.",
    unmetered: "Unmetered only",
    signOut: "Sign out",
    drive: "Disconnect and delete Lagoon data from Drive",
    forget: "Forget this phone",
    forgetEmpty: "There is no other phone to forget.",
    pending: "will be forgotten at its next sync",
    nothingForgotten: "Nothing was forgotten.",
    topics: "Topics",
    calls: "This month's topic calls",
    missingKey: "The topic key is not copied from the other phone. Type it again on this phone.",
    appLock: "App lock",
    exportZip: "Export zip",
    importZip: "Import zip",
    trash: "Trash",
    restore: "Restore",
    paused: "Sync is paused. Lagoon reached Drive and could not read which phones were forgotten. Nothing on this phone was deleted or uploaded. It will try again.",
    dropped: "That edit couldn't be kept.",
    files: "Files on this phone that are not part of a saved item were removed.",
    edits: "Edits on this phone that had not synced were not kept.",
    resetWait: "A passphrase reset is waiting.",
    cancelReset: "Cancel reset",
    hardware: "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key.",
    pdf: "This PDF is password-protected.",
    recalled: "Content already sent cannot be recalled.",
    expired: "Expired",
    done: "Done",
    snooze: "Snooze",
    usePin: "Use PIN",
    disclosure: "The image and the recognized text stay on this phone. They are not sent to Google. ML Kit sends usage and diagnostic metrics to Google over HTTPS. ML Kit does not transfer them to third parties. ML Kit may contact Google for model updates and bug fixes. This app has no switch to turn those metrics off. The metrics are: device information and ML hardware accelerators, package name and version, per-installation identifiers, performance metrics, API configuration, input and output size, feature version, event types, and error codes.",
    accept: "Accept",
    decline: "Decline",
    withdraw: "Withdraw acceptance",
    wait: "Wait, then try again."
  };

  function blankItem(partial) {
    return Object.assign({
      id: uid(), type: "note", title: "", body: "", note: "", noteDate: "", tags: [],
      source: "", savedAt: "2026-10-31", url: "", sensitive: false, reminder: "", expiry: "",
      preview: "none", enrichFailed: false, pages: 0, images: 0, ocr: "", extracted: "",
      provenance: false, topicSent: false, trashedAt: "", passwordPdf: false,
      textNotExtracted: false, ownerPasswordOnly: false, pendingTrash: false, done: false, snooze: ""
    }, partial);
  }

  function seedItems() {
    return [
      blankItem({ id: "n-ferry", title: "Ferry times to the island", body: "Sunday boat leaves at 09:00.", note: "Check the Sunday boat.", noteDate: "2026-10-20", tags: ["travel"], source: "Lagoon", savedAt: "2026-10-28", reminder: "2026-11-02", provenance: true, topicSent: true }),
      blankItem({ id: "n-plants", type: "link", title: "Field guide to coastal plants", url: "https://example.com/coastal-plants", source: "example.com", tags: ["plants"], savedAt: "2026-10-27", preview: "ready", extracted: "kelp along the shore", provenance: true, topicSent: true }),
      blankItem({ id: "n-token", type: "link", title: "Shared photo album", url: "https://example.com/a?token=example", source: "example.com", savedAt: "2026-10-26", preview: "fog" }),
      blankItem({ id: "n-market", type: "image", title: "Market receipt", source: "Camera", savedAt: "2026-10-25", tags: ["kitchen"], images: 2, ocr: "oats" }),
      blankItem({ id: "n-pdf", type: "pdf", title: "Workshop notes", source: "Files", savedAt: "2026-10-24", tags: ["travel"], pages: 3, extracted: "agenda and notes" }),
      blankItem({ id: "n-lock", type: "pdf", title: "Sealed minutes", source: "Files", savedAt: "2026-10-23", pages: 1, passwordPdf: true, textNotExtracted: true }),
      blankItem({ id: "n-owner", type: "pdf", title: "Printed map", source: "Files", savedAt: "2026-10-22", pages: 2, ownerPasswordOnly: true, extracted: "coast road" }),
      blankItem({ id: "n-harbour", type: "link", title: "Harbour chart", url: "https://example.com/harbour-chart", source: "example.com", savedAt: "2026-10-21", enrichFailed: true }),
      blankItem({ id: "n-key", title: "Spare key code", body: "The code is 4821.", tags: ["home"], source: "Lagoon", savedAt: "2026-10-19", sensitive: true }),
      blankItem({ id: "n-grocery", title: "Grocery list", body: "Oats and tea.", source: "Lagoon", savedAt: "2026-10-30" }),
      blankItem({ id: "n-stew", title: "Sunday stew", tags: ["kitchen"], source: "Lagoon", savedAt: "2026-10-18", expiry: "2026-10-01" }),
      blankItem({ id: "n-tide", type: "link", title: "Tide table", url: "https://example.com/tide", source: "example.com", tags: ["travel"], savedAt: "2026-10-17" }),
      blankItem({ id: "n-tide2", type: "link", title: "Tide table copy", url: "https://www.Example.com/tide/", source: "example.com", tags: ["travel"], savedAt: "2026-10-16" }),
      blankItem({ id: "n-gate", title: "Gate 4B boarding", tags: ["travel"], source: "Lagoon", savedAt: "2026-10-29", reminder: "2026-11-03" }),
      blankItem({ id: "n-lockern", title: "Locker number", tags: ["home"], source: "Lagoon", savedAt: "2026-10-15", reminder: "2026-11-04", sensitive: true }),
      blankItem({ id: "n-old", type: "link", title: "Old article", url: "https://example.com/old-article", source: "example.com", savedAt: "2026-09-01", trashedAt: "2026-10-19T12:00:00Z" }),
      blankItem({ id: "n-pass", type: "image", title: "Boarding pass", source: "Camera", savedAt: "2026-09-12", images: 1, trashedAt: "2026-10-19T12:00:00Z" }),
      blankItem({ id: "n-sense", title: "Old gate code", tags: ["home"], source: "Lagoon", savedAt: "2026-09-20", sensitive: true, trashedAt: "2026-10-20T12:00:00Z" }),
      blankItem({ id: "n-dead", type: "pdf", title: "Expired ticket", source: "Files", savedAt: "2026-08-01", pages: 1, trashedAt: "2026-09-01T12:00:00Z" })
    ];
  }

  function seedTopics() {
    return [
      { id: "t-plants", name: "Coastal plants", ai: true, links: [{ itemId: "n-plants", note: "Useful before the trip", date: "2026-10-27", ai: true }] },
      { id: "t-ferry", name: "Ferries", ai: false, links: [{ itemId: "n-ferry", note: "Sunday", date: "2026-10-28", ai: false }, { itemId: "n-key", note: "Hidden until unlock", date: "2026-10-19", ai: false }] },
      { id: "t-empty", name: "Markets", ai: false, links: [] }
    ];
  }

  function seedSuggestions() {
    return [{ id: "s1", name: "Tide tables", quote: "Spring tide on the 12th.", itemId: "n-tide" }];
  }

  function seedInstalls() {
    return [
      { id: "p1", name: "Pixel Tablet", model: "Pixel Tablet", seen: "2026-10-29", pending: false },
      { id: "p2", name: "Old Pixel", model: "Pixel 7", seen: "2026-10-02", pending: true }
    ];
  }

  var S = {};

  function fresh() {
    S.theme = S.theme || "system";
    S.screen = "passphrase";
    S.tab = "home";
    S.stack = [];
    S.vault = false;
    S.items = [];
    S.topics = [];
    S.suggestions = [];
    S.installs = [];
    S.phrase = "";
    S.hardware = true;
    S.lowMemory = false;
    S.api = 34;
    S.shareWaiting = false;
    S.sharePayload = "link";
    S.pickerUrl = "";
    S.pickerTrash = false;
    S.query = "";
    S.filters = { type: "all", tag: "", source: "", date: "" };
    S.hideItems = false;
    S.hideTopics = false;
    S.hideInstalls = false;
    S.sessionOpen = false;
    S.appLock = false;
    S.locked = false;
    S.dynamic = false;
    S.sync = "unconfigured";
    S.paused = false;
    S.unmetered = true;
    S.topicsOn = true;
    S.topicsUnmetered = false;
    S.calls = 3;
    S.topicReady = false;
    S.topicDraft = "";
    S.askedEarlier = false;
    S.ml = "no";
    S.notifications = "Off";
    S.notificationsAsked = false;
    S.reminderTime = "09:00";
    S.clock = false;
    S.resetBanner = false;
    S.quick = null;
    S.noTagHistory = false;
    S.snack = null;
    S.dialog = null;
    S.sheet = null;
    S.loading = false;
    S.detailId = null;
    S.readOnly = false;
    S.passDraft = { current: "", next: "", again: "" };
    S.passError = "";
    S.fails = 0;
    S.waitUntil = 0;
    S.noteDraft = { title: "", body: "", why: "" };
    S.noteError = "";
    S.urlDraft = { url: "", title: "" };
    S.urlError = "";
    S.editDraft = { title: "", body: "", why: "", tags: "", sensitive: false, kind: "", date: "" };
    S.editError = "";
    S.sentNote = false;
    S.declinedMessage = "";
    S.fsIndex = 0;
    S.fsZoom = 1;
    S.bright = false;
    S.pin = "";
    S.afterUnlock = "";
    S.pending = "";
    S.topicFail = "";
    S.forgetFail = false;
    S.installId = "";
    S.topicId = "";
    S.viewerLabel = "";
    S.droppedTitle = "";
    S.lost = false;
    S.passSaved = false;
    S.dateDraft = { kind: "reminder", date: "2026-11-02" };
    S.qnote = "";
    S.afterNotif = "";
    S.focusDialog = false;
    S.promptHidden = false;
  }

  fresh();

  function loadDemo() {
    S.items = seedItems().map(function (it) { return Object.assign({}, it, { tags: it.tags.slice() }); });
    S.topics = seedTopics().map(function (t) { return Object.assign({}, t, { links: t.links.map(function (l) { return Object.assign({}, l); }) }); });
    S.suggestions = seedSuggestions().map(function (s) { return Object.assign({}, s); });
    S.installs = seedInstalls().map(function (i) { return Object.assign({}, i); });
    S.phrase = demoPhrase;
    S.calls = 3;
    S.vault = true;
    S.installId = "p1";
  }

  function setBind(path, value) {
    var bits = path.split(".");
    var obj = S;
    var i;
    for (i = 0; i < bits.length - 1; i += 1) obj = obj[bits[i]];
    obj[bits[bits.length - 1]] = value;
  }

  function resolvedTheme() {
    if (S.theme === "system") {
      return window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light";
    }
    return S.theme;
  }

  function typeName(t) {
    return { note: "Note", link: "Link", image: "Image", pdf: "PDF" }[t] || t;
  }

  function byId(id) {
    var i;
    for (i = 0; i < S.items.length; i += 1) if (S.items[i].id === id) return S.items[i];
    return null;
  }

  function current() { return byId(S.detailId); }

  function liveItems() {
    return S.items.filter(function (it) { return !it.trashedAt && !it.pendingTrash; });
  }

  function trashItems() {
    return S.items.filter(function (it) { return it.trashedAt && !it.pendingTrash; });
  }

  function shownTitle(it) {
    if (it.sensitive && !S.sessionOpen) return COPY.sensitive;
    return it.title;
  }

  function homeMeta(it) {
    if (it.sensitive && !S.sessionOpen) return typeName(it.type) + " · " + COPY.sensitive;
    var parts = [typeName(it.type), it.savedAt];
    if (it.source) parts.push(it.source);
    return parts.join(" · ");
  }

  function marker(it) {
    if (it.sensitive && !S.sessionOpen) return "";
    if (it.snooze) return "Snooze " + it.snooze.replace("T", " ");
    if (it.reminder) return "Reminder " + it.reminder;
    if (it.expiry) return "Expiry " + it.expiry;
    return "";
  }

  function daysLeft(it) {
    if (!it.trashedAt) return null;
    var deadline = Date.parse(it.trashedAt) + 30 * 24 * 60 * 60 * 1000;
    var left = deadline - Date.parse(NOW);
    if (left <= 0) return null;
    return Math.ceil(left / (24 * 60 * 60 * 1000));
  }

  function whenOf(it) {
    if (it.snooze) return it.snooze;
    var d = it.reminder || it.expiry || "";
    return d.length === 10 ? d + "T09:00" : d;
  }

  function normalise(url) {
    try {
      var u = new URL(url);
      u.protocol = "https:";
      var host = u.hostname.toLowerCase();
      if (host.indexOf("www.") === 0) host = host.slice(4);
      u.hostname = host;
      u.hash = "";
      var drop = [];
      u.searchParams.forEach(function (v, k) {
        var key = k.toLowerCase();
        if (key.indexOf("utm_") === 0 || key === "fbclid" || key === "gclid" || key === "mc_eid" || key === "igshid" || key === "si") drop.push(k);
      });
      drop.forEach(function (k) { u.searchParams.delete(k); });
      var out = u.toString();
      if (out.charAt(out.length - 1) === "/") out = out.slice(0, -1);
      return out;
    } catch (e) {
      return String(url || "").trim().toLowerCase();
    }
  }

  function tokenLooking(url) {
    return /[?&](token|access_token|key)=/i.test(url || "");
  }

  function hostOf(url) {
    try { return new URL(url).hostname.replace(/^www\./i, ""); } catch (e) { return ""; }
  }

  function findMatches(url) {
    var n = normalise(url);
    var live = [];
    var trashed = [];
    S.items.forEach(function (it) {
      if (!it.url || normalise(it.url) !== n) return;
      if (it.trashedAt) trashed.push(it);
      else if (!it.pendingTrash) live.push(it);
    });
    return { live: live, trashed: trashed };
  }

  function score(it, q) {
    var query = q.trim().toLowerCase();
    if (!query) return 0;
    if (it.sensitive && !S.sessionOpen) {
      var words = query.split(/\s+/);
      var tags = it.tags.map(function (t) { return t.toLowerCase(); });
      var w;
      for (w = 0; w < words.length; w += 1) if (tags.indexOf(words[w]) !== -1) return 5;
      return 0;
    }
    var s = 0;
    if (it.title.toLowerCase().indexOf(query) !== -1) s += 10;
    if (it.tags.join(" ").toLowerCase().indexOf(query) !== -1) s += 5;
    if ((it.note || "").toLowerCase().indexOf(query) !== -1) s += 3;
    if ((it.body || "").toLowerCase().indexOf(query) !== -1) s += 1;
    if ((it.extracted || "").toLowerCase().indexOf(query) !== -1) s += 1;
    if (S.ml === "yes" && (it.ocr || "").toLowerCase().indexOf(query) !== -1) s += 1;
    return s;
  }

  function snippet(it, q) {
    var fields = [it.body, it.note, it.extracted, S.ml === "yes" ? it.ocr : ""];
    var query = q.toLowerCase();
    var f;
    for (f = 0; f < fields.length; f += 1) {
      var text = fields[f] || "";
      var i = text.toLowerCase().indexOf(query);
      if (i >= 0) {
        var start = Math.max(0, i - 24);
        var end = Math.min(text.length, i + q.length + 24);
        return (start > 0 ? "…" : "") + text.slice(start, end) + (end < text.length ? "…" : "");
      }
    }
    return "";
  }

  function mark(text, q) {
    var src = text || "";
    var query = (q || "").trim();
    if (!query) return esc(src);
    var i = src.toLowerCase().indexOf(query.toLowerCase());
    if (i < 0) return esc(src);
    return esc(src.slice(0, i)) + "<mark>" + esc(src.slice(i, i + query.length)) + "</mark>" + esc(src.slice(i + query.length));
  }

  function checkPhrase(value) {
    var n = (value || "").normalize("NFC");
    var points = Array.from(n).length;
    if (points < 12 || points > 128) return "Use 12 to 128 characters.";
    if (commonList.indexOf(n.toLowerCase()) !== -1) return "That passphrase is too common.";
    return "";
  }

  function samePhrase(a, b) {
    return (a || "").normalize("NFC") === (b || "").normalize("NFC");
  }

  function waitSeconds() {
    if (S.waitUntil <= Date.now()) return 0;
    return Math.ceil((S.waitUntil - Date.now()) / 1000);
  }

  function noteWrong() {
    var waits = [2, 4, 8, 16, 32];
    var w = waits[Math.min(S.fails, waits.length - 1)];
    S.fails += 1;
    S.waitUntil = Date.now() + w * 1000;
    S.passError = COPY.wait;
  }

  function btn(act, label, cls, extra) {
    return '<button type="button" class="btn ' + (cls || "btn-text") + '" data-act="' + esc(act) + '" ' + (extra || "") + ">" + esc(label) + "</button>";
  }

  function iconBtn(act, label, icon) {
    return '<button type="button" class="icon-btn" data-act="' + act + '" aria-label="' + esc(label) + '">' + icon + "</button>";
  }

  function leaveScreen() {
    if (S.snack && S.snack.undoKind === "trash") commitTrash();
    S.snack = null;
    S.quick = null;
  }

  function commitTrash() {
    if (!S.snack || S.snack.undoKind !== "trash") return;
    var it = byId(S.snack.id);
    if (it && it.pendingTrash) {
      it.pendingTrash = false;
      it.trashedAt = NOW;
    }
  }

  function navigate(screen) {
    leaveScreen();
    if (S.screen !== screen) S.stack.push(S.screen);
    S.screen = screen;
  }

  function background() {
    leaveScreen();
    S.sessionOpen = false;
    if (S.appLock) S.locked = true;
    S.stack = [];
    S.dialog = null;
    S.sheet = null;
    S.bright = false;
    S.screen = "launcher";
  }

  function back() {
    if (S.loading) return;
    if (S.dialog) { dismissDialog(); return; }
    if (S.sheet) { cancelSheet(); return; }
    if (S.quick) { S.quick = null; return; }
    if (S.screen === "fullscreen") S.bright = false;
    if (S.stack.length) {
      leaveScreen();
      S.screen = S.stack.pop();
      if (S.screen !== "fullscreen") S.bright = false;
      return;
    }
    if (S.screen !== "home" && ["search", "inbox", "topics", "settings"].indexOf(S.screen) !== -1) {
      leaveScreen();
      S.screen = "home";
      S.tab = "home";
      return;
    }
    background();
  }

  function showQuick(it) {
    if (it) maybeAutoTopic(it);
    S.quick = it ? { id: it.id } : null;
    S.stack = [];
    S.screen = "home";
    S.tab = "home";
    S.hideItems = false;
    S.sheet = null;
  }

  function maybeAutoTopic(it) {
    if (!it || it.sensitive || !S.topicsOn || !S.topicReady || it.topicSent) return;
    addSuggestion(it);
    it.topicSent = true;
    it.provenance = true;
    S.calls += 1;
  }

  function addSuggestion(it) {
    var quote = (it.body || it.extracted || it.title || "").slice(0, 80);
    S.suggestions.push({ id: uid(), name: (it.title || "Topic").split(" ")[0], quote: quote, itemId: it.id });
  }

  function addItem(partial) {
    var it = blankItem(partial || {});
    if (!it.tags) it.tags = [];
    S.items.push(it);
    return it;
  }

  function cloneItem(it) {
    return Object.assign({}, it, { tags: it.tags.slice() });
  }

  function busy(ms, after) {
    S.loading = true;
    setTimeout(function () {
      S.loading = false;
      after();
      render();
    }, ms);
  }

  function mergeInto(it) {
    var snap = cloneItem(it);
    it.note = (it.note ? it.note + "\n" : "") + "2026-10-31";
    it.noteDate = "2026-10-31";
    S.snack = { undoKind: "merge", text: COPY.repeat, until: Date.now() + 8000, token: uid(), snap: snap };
    S.screen = "home";
    S.tab = "home";
    S.stack = [];
    S.quick = null;
    S.dialog = null;
    S.sheet = null;
  }

  function openPicker(rows, trash, url) {
    S.pickerRows = rows.map(function (it) { return it.id; });
    S.pickerTrash = trash;
    S.pickerUrl = url;
    S.screen = "picker";
    S.stack = ["home"];
    S.sheet = null;
    S.dialog = null;
  }

  function finishShare() {
    var p = S.sharePayload;
    if (p === "empty" || p === "file" || p === "provider" || p === "big" || p === "notpdf") {
      S.screen = "home";
      S.tab = "home";
      S.stack = [];
      S.dialog = { kind: "nothing" };
      S.focusDialog = true;
      return;
    }
    if (p === "text") {
      showQuick(addItem({ type: "note", title: "Spring tide is on Sunday.", body: "Spring tide is on Sunday.", source: "Messages" }));
      return;
    }
    if (p === "image") {
      showQuick(addItem({ type: "image", title: "photo.jpg", source: "Messages", images: 1 }));
      return;
    }
    if (p === "pdf") {
      showQuick(addItem({ type: "pdf", title: "notes.pdf", source: "Messages", pages: 2, extracted: "notes" }));
      return;
    }
    var url = "https://example.com/harbour-notes";
    if (p === "repeat") url = "https://example.com/coastal-plants";
    if (p === "two") url = "https://example.com/tide";
    if (p === "trash") url = "https://example.com/old-article";
    if (p === "out" && S.pickerUrl) url = S.pickerUrl;
    var matches = findMatches(url);
    if (matches.live.length === 1) { mergeInto(matches.live[0]); return; }
    if (matches.live.length > 1) { openPicker(matches.live, false, url); return; }
    if (matches.trashed.length) { openPicker(matches.trashed, true, url); return; }
    var title = url.length > 120 ? url.slice(0, 120) : url;
    showQuick(addItem({ type: "link", title: title, url: url, source: hostOf(url), preview: tokenLooking(url) ? "fog" : "none" }));
  }

  function secureNow() {
    if (["passphrase", "passphrase-decline", "change", "change-saved", "reset", "returning"].indexOf(S.screen) !== -1) return true;
    if (S.screen === "trash") return true;
    if (S.screen === "detail" && S.readOnly) return true;
    var it = current();
    if ((S.screen === "detail" || S.screen === "fullscreen") && it && it.sensitive && S.sessionOpen) return true;
    return false;
  }

  function appBar() {
    var titles = {
      home: "Lagoon", search: "Search", inbox: "Inbox", topics: "Topics", settings: "Settings",
      passphrase: "Set a passphrase", "passphrase-decline": "Set a passphrase",
      edit: "Edit", "add-note": "Add a note", "add-url": "Add a URL", trash: COPY.trash,
      forget: COPY.forget, change: "Change passphrase", "change-saved": "Change passphrase",
      reset: "Set a new passphrase", returning: "Enter the passphrase", picker: COPY.which,
      upcoming: "Upcoming", suggestions: "Pending suggestions", locked: "Lagoon"
    };
    var title = titles[S.screen] || "";
    if (S.screen === "topic") {
      var topic = topicById(S.topicId);
      title = topic ? topic.name : "";
    }
    var backBtn = ["home", "search", "inbox", "topics", "settings"].indexOf(S.screen) === -1 ? iconBtn("back", "Back", I.back) : "";
    var extra = "";
    if (S.screen === "home") {
      extra = '<button type="button" class="text-action" data-act="upcoming">Upcoming</button>' + iconBtn("camera", "Camera", I.camera);
    }
    if (S.screen === "detail" && !S.readOnly) extra = btn("edit", "Edit", "btn-text");
    return '<div class="appbar">' + backBtn + "<h1>" + esc(title) + "</h1>" + extra + "</div>";
  }

  function nav() {
    if (["home", "search", "inbox", "topics", "settings"].indexOf(S.screen) === -1) return "";
    var items = [["home", "Home", I.home], ["search", "Search", I.search], ["inbox", "Inbox", I.inbox], ["topics", "Topics", I.topics], ["settings", "Settings", I.settings]];
    return '<nav class="nav">' + items.map(function (it) {
      var on = S.screen === it[0] ? ' aria-current="page"' : "";
      return '<button type="button" data-act="tab" data-tab="' + it[0] + '"' + on + '><span class="ico">' + it[2] + "</span>" + esc(it[1]) + "</button>";
    }).join("") + "</nav>";
  }

  function row(it, act) {
    var markLine = marker(it);
    return '<button type="button" class="row" data-act="' + (act || "open-item") + '" data-id="' + esc(it.id) + '"><span class="row-title">' + esc(shownTitle(it)) + '</span><span class="row-meta">' + esc(homeMeta(it)) + "</span>" + (markLine ? '<span class="row-meta">' + esc(markLine) + "</span>" : "") + "</button>";
  }

  function banner(text) {
    return '<p class="sync-banner">' + esc(text) + "</p>";
  }

  function homeItems() {
    if (S.hideItems) return [];
    return liveItems().filter(function (it) {
      if (S.filters.type !== "all" && it.type !== S.filters.type) return false;
      if (S.filters.tag && it.tags.indexOf(S.filters.tag) === -1) return false;
      if (S.filters.source && it.source !== S.filters.source) return false;
      if (S.filters.date && it.savedAt !== S.filters.date) return false;
      return true;
    }).sort(function (a, b) { return b.savedAt.localeCompare(a.savedAt); });
  }

  function filtersHtml() {
    if (S.hideItems || liveItems().length === 0) return "";
    var tags = [];
    var sources = [];
    S.items.forEach(function (it) {
      it.tags.forEach(function (t) { if (tags.indexOf(t) === -1) tags.push(t); });
      if (it.source && sources.indexOf(it.source) === -1) sources.push(it.source);
    });
    if (S.filters.tag && tags.indexOf(S.filters.tag) === -1) tags.push(S.filters.tag);
    return '<div class="filters"><select data-bind="filters.type" aria-label="Type">' +
      opt("all", "Type", S.filters.type) + opt("note", "Note", S.filters.type) + opt("link", "Link", S.filters.type) + opt("image", "Image", S.filters.type) + opt("pdf", "PDF", S.filters.type) +
      '</select><select data-bind="filters.tag" aria-label="Tag"><option value="">Tag</option>' +
      tags.map(function (t) { return opt(t, t, S.filters.tag); }).join("") +
      '</select><select data-bind="filters.source" aria-label="Source"><option value="">Source</option>' +
      sources.map(function (t) { return opt(t, t, S.filters.source); }).join("") +
      '</select><input type="date" data-bind="filters.date" aria-label="Date saved" value="' + esc(S.filters.date) + '"></div>';
  }

  function opt(value, label, current) {
    return '<option value="' + esc(value) + '"' + (current === value ? " selected" : "") + ">" + esc(label) + "</option>";
  }

  function renderHome() {
    var rows = homeItems();
    var top = "";
    if (S.paused) top += banner(COPY.paused);
    if (S.clock) top += banner("This phone's clock does not match Drive.");
    if (S.resetBanner) {
      top += '<div class="info-banner"><p>' + esc(COPY.resetWait) + "</p>" + btn("cancel-reset", COPY.cancelReset, "btn-text") + "</div>";
    }
    top += filtersHtml();
    if (!rows.length) {
      if (S.hideItems || liveItems().length === 0) return top + '<p class="empty">' + esc(COPY.emptyHome) + "</p>";
      return top;
    }
    return top + rows.map(function (it) { return row(it); }).join("") + '<div class="pad"></div>';
  }

  function artClass(n) {
    if (n % 3 === 1) return "art p2";
    if (n % 3 === 2) return "art p3";
    return "art";
  }

  function renderDetail() {
    var it = current();
    if (!it) return "";
    var html = '<div class="detail"><h2>' + esc(shownTitle(it)) + "</h2>";
    html += '<p class="kicker">' + esc(homeMeta(it)) + "</p>";
    if (it.enrichFailed) html += '<p class="kicker">' + esc(COPY.enrich) + "</p>";
    if (it.textNotExtracted) html += '<p class="kicker">text not extracted</p>';
    if (!(it.sensitive && !S.sessionOpen)) {
      if (it.url) html += '<p class="url">' + esc(it.url) + "</p>";
      if (it.body) html += '<p class="body">' + esc(it.body) + "</p>";
      if (it.note) html += '<p class="note">' + esc((it.noteDate || "YYYY-MM-DD") + "\n" + it.note) + "</p>";
      if (it.preview === "ready" || it.type === "image" || (it.type === "pdf" && !it.passwordPdf)) {
        html += '<button type="button" class="attach" data-act="open-fs" aria-label="' + (it.type === "pdf" ? "PDF page" : "Image") + '"><div class="' + artClass(0) + '"></div></button>';
      }
      if (it.preview === "fog") {
        html += '<div class="fog" role="img" aria-label="Preview"></div>';
        if (!S.promptHidden) {
          html += '<div class="prompt"><p>' + esc(COPY.previewAsk) + "</p>" + btn("preview-yes", COPY.yes, "btn-filled") + btn("preview-no", COPY.no, "btn-text") + "</div>";
        }
      }
    }
    html += '<div class="actions">';
    if (it.preview === "fog" && !it.sensitive) html += btn("show-prompt", COPY.fetch, "btn-row btn-text");
    if (!S.readOnly) html += btn("get-topics", COPY.topicsBtn, "btn-row btn-text");
    html += btn("share-out", "Share", "btn-row btn-text");
    html += btn("open-link", "Open", "btn-row btn-text");
    if (!S.readOnly) html += btn("delete-item", "Delete", "btn-row btn-danger");
    html += "</div></div>";
    return html;
  }

  function field(label, bind, value, extra) {
    var kind = extra && extra.area ? "textarea" : "input";
    var type = extra && extra.secret ? "password" : (extra && extra.type ? extra.type : "text");
    var attrs = ' data-bind="' + bind + '" value="' + esc(value || "") + '" autocomplete="off" autocapitalize="off" spellcheck="false"';
    if (kind === "textarea") {
      return '<label class="field"><span>' + esc(label) + '</span><textarea data-bind="' + bind + '" autocomplete="off" spellcheck="false">' + esc(value || "") + "</textarea></label>";
    }
    return '<label class="field"><span>' + esc(label) + '</span><input type="' + type + '"' + attrs + "></label>";
  }

  function renderEdit() {
    var d = S.editDraft;
    var html = field("Title", "editDraft.title", d.title) + field("Body", "editDraft.body", d.body, { area: true }) + field("Why it matters", "editDraft.why", d.why, { area: true }) + field("Tags", "editDraft.tags", d.tags);
    html += '<label class="set"><span>Sensitive</span><span class="switch"><input type="checkbox" data-bind="editDraft.sensitive"' + (d.sensitive ? " checked" : "") + "><i></i></span></label>";
    html += '<div class="choice"><span>Reminder or expiry</span></div>';
    html += '<label class="choice"><input type="radio" name="when" data-bind="editDraft.kind" value="reminder"' + (d.kind === "reminder" ? " checked" : "") + "> Reminder</label>";
    html += '<label class="choice"><input type="radio" name="when" data-bind="editDraft.kind" value="expiry"' + (d.kind === "expiry" ? " checked" : "") + "> Expiry</label>";
    html += field("Date", "editDraft.date", d.date, { type: "date" });
    if (S.editError) html += '<p class="field-error" role="alert">' + esc(S.editError) + "</p>";
    if (S.sentNote) html += '<p class="field-error">' + esc(COPY.recalled) + "</p>";
    html += '<div class="form-actions">' + btn("save-edit", "Save", "btn-filled") + "</div>";
    return html;
  }

  function renderSearch() {
    var q = S.query.trim();
    var hits = [];
    if (q) {
      hits = liveItems().map(function (it) { return { it: it, s: score(it, q) }; }).filter(function (x) { return x.s > 0; });
      hits.sort(function (a, b) { return b.s - a.s || b.it.savedAt.localeCompare(a.it.savedAt); });
    }
    var html = '<label class="field"><input id="q" data-bind="query" data-live="1" aria-label="Search" autocomplete="off" value="' + esc(S.query) + '"></label>';
    html += hits.map(function (x) {
      var it = x.it;
      if (it.sensitive && !S.sessionOpen) {
        return '<button type="button" class="row" data-act="open-item" data-id="' + esc(it.id) + '"><span class="row-title">Sensitive</span><span class="row-meta">' + esc(typeName(it.type)) + " · Sensitive</span></button>";
      }
      var snip = snippet(it, q);
      return '<button type="button" class="row" data-act="open-item" data-id="' + esc(it.id) + '"><span class="row-title">' + mark(it.title, q) + "</span>" + (snip ? '<span class="row-meta">' + mark(snip, q) + "</span>" : "") + "</button>";
    }).join("");
    return html;
  }

  function renderNote() {
    var d = S.noteDraft;
    return field("Title", "noteDraft.title", d.title) + field("Body", "noteDraft.body", d.body, { area: true }) + field("Why it matters", "noteDraft.why", d.why, { area: true }) +
      (S.noteError ? '<p class="field-error" role="alert">' + esc(S.noteError) + "</p>" : "") +
      '<div class="form-actions">' + btn("save-note", "Save", "btn-filled") + "</div>";
  }

  function renderUrl() {
    var d = S.urlDraft;
    return field("URL", "urlDraft.url", d.url) + field("Title (optional)", "urlDraft.title", d.title) +
      (S.urlError ? '<p class="field-error" role="alert">' + esc(S.urlError) + "</p>" : "") +
      '<div class="form-actions">' + btn("save-url", "Save", "btn-filled") + "</div>";
  }

  function switchRow(label, change, on) {
    return '<label class="set"><span>' + esc(label) + '</span><span class="switch"><input type="checkbox" data-change="' + change + '"' + (on ? " checked" : "") + "><i></i></span></label>";
  }

  function linkRow(act, label) {
    return '<button type="button" class="btn btn-text btn-row linkish" data-act="' + act + '">' + esc(label) + "</button>";
  }

  function syncSentence() {
    if (S.sync === "signedin") return COPY.syncIn;
    if (S.sync === "signedout") return COPY.syncOut;
    if (S.sync === "revoked") return COPY.revoked;
    if (S.sync === "full") return COPY.full;
    if (S.sync === "failed") return COPY.upload;
    return COPY.syncOff;
  }

  function renderSettings() {
    var html = '<div class="choice">';
    html += '<label><input type="radio" name="app-theme" data-bind="theme" value="light"' + (S.theme === "light" ? " checked" : "") + "> Light</label>";
    html += '<label><input type="radio" name="app-theme" data-bind="theme" value="dark"' + (S.theme === "dark" ? " checked" : "") + "> Dark</label>";
    html += '<label><input type="radio" name="app-theme" data-bind="theme" value="system"' + (S.theme === "system" ? " checked" : "") + "> System</label></div>";
    if (S.api >= 31) html += switchRow("Dynamic color", "dynamic", S.dynamic);
    html += '<hr class="section-gap">';
    html += '<p class="kicker">' + esc(syncSentence()) + "</p>";
    if (S.paused) html += banner(COPY.paused);
    html += switchRow(COPY.unmetered, "unmetered", S.unmetered);
    html += linkRow("sign-in", "Sign in") + linkRow("sign-out", COPY.signOut) + linkRow("drive-delete", COPY.drive) + linkRow("open-forget", COPY.forget);
    html += '<hr class="section-gap">';
    html += '<label class="set"><span>Reminder default time</span><input type="time" data-bind="reminderTime" value="' + esc(S.reminderTime) + '" aria-label="Reminder default time"></label>';
    html += '<p class="set"><span>Notifications</span><span>' + esc(S.notifications) + "</span></p>";
    html += switchRow(COPY.appLock, "lock", S.appLock);
    html += '<hr class="section-gap">';
    html += switchRow(COPY.topics, "topics", S.topicsOn);
    html += '<label class="set"><span>Topics unmetered only</span><input type="checkbox" data-change="topics-unmetered"' + (S.topicsUnmetered ? " checked" : "") + "></label>";
    html += '<p class="set"><span>' + esc(COPY.calls) + "</span><span>" + esc(String(S.calls)) + "</span></p>";
    if (!S.topicReady) {
      html += '<p class="disclosure">' + esc(COPY.missingKey) + "</p>";
      html += field("Topic key", "topicDraft", S.topicDraft, { secret: true });
      html += '<div class="form-actions">' + btn("save-topic-key", "Save", "btn-filled") + "</div>";
    }
    html += '<hr class="section-gap">';
    html += '<p class="kicker">Text recognition</p><p class="disclosure">' + esc(COPY.disclosure) + "</p>";
    html += '<p class="kicker">Accepted: ' + (S.ml === "yes" ? "yes" : "no") + "</p>";
    html += linkRow("withdraw", COPY.withdraw);
    html += '<hr class="section-gap">';
    html += linkRow("export-zip", COPY.exportZip) + linkRow("import-zip", COPY.importZip) + linkRow("open-trash", COPY.trash);
    html += linkRow("open-change", "Change passphrase") + linkRow("open-reset", "Set a new passphrase");
    if (!S.hardware) html += '<p class="warn">' + esc(COPY.hardware) + "</p>";
    return html;
  }

  function renderTrash() {
    var rows = trashItems();
    if (!rows.length) return "";
    return rows.map(function (it) {
      var days = daysLeft(it);
      var day = days == null ? "" : '<span class="row-meta">' + esc(days + " days left") + "</span>";
      var head = it.sensitive && !S.sessionOpen ? COPY.sensitive : typeName(it.type) + " · " + it.title;
      var sub = it.sensitive && !S.sessionOpen ? typeName(it.type) + " · Sensitive" : "";
      return '<div class="row-split"><button type="button" class="row-main" data-act="open-trash-row" data-id="' + esc(it.id) + '"><span class="row-title">' + esc(head) + "</span>" + (sub ? '<span class="row-meta">' + esc(sub) + "</span>" : "") + day + '</button>' + btn("restore", COPY.restore, "btn-text", 'data-id="' + esc(it.id) + '"') + "</div>";
    }).join("");
  }

  function renderForget() {
    if (S.hideInstalls || !S.installs.length) return '<p class="empty">' + esc(COPY.forgetEmpty) + "</p>";
    var html = S.installs.map(function (ins) {
      var sub = ins.pending ? COPY.pending : ins.model + " · " + ins.seen;
      return '<label class="row"><input type="radio" name="install" data-change="install" data-id="' + esc(ins.id) + '"' + (S.installId === ins.id ? " checked" : "") + "><span class=\"row-title\">" + esc(ins.name) + '</span><span class="row-meta">' + esc(sub) + "</span></label>";
    }).join("");
    var chosen = S.installs.filter(function (i) { return i.id === S.installId; })[0];
    var name = chosen ? chosen.name : "that install";
    html += '<div class="form-actions">' + btn("forget-confirm", "Forget " + name, "btn-filled") + btn("back", "Cancel", "btn-text") + "</div>";
    return html;
  }

  function topicById(id) {
    var i;
    for (i = 0; i < S.topics.length; i += 1) if (S.topics[i].id === id) return S.topics[i];
    return null;
  }

  function renderTopics() {
    if (S.hideTopics || !S.topics.length) return '<p class="empty">' + esc(COPY.noTopicList) + "</p>";
    return S.topics.map(function (t) {
      var ai = t.ai ? " · AI-generated" : "";
      return '<button type="button" class="row" data-act="open-topic" data-id="' + esc(t.id) + '"><span class="row-title">' + esc(t.name + " · " + t.links.length + " items" + ai) + "</span></button>";
    }).join("");
  }

  function renderTopic() {
    var t = topicById(S.topicId);
    if (!t) return "";
    if (!t.links.length) return '<p class="empty">' + esc(COPY.nothingLinked) + "</p>";
    return t.links.map(function (link) {
      var it = byId(link.itemId);
      if (it && it.sensitive && !S.sessionOpen) {
        return '<button type="button" class="row" data-act="open-item" data-id="' + esc(it.id) + '"><span class="row-title">Sensitive</span></button>';
      }
      var title = it ? it.title : "";
      var ai = link.ai ? " · AI" : "";
      return '<button type="button" class="row" data-act="open-item" data-id="' + esc(link.itemId) + '"><span class="row-title">' + esc(title) + '</span><span class="row-meta">' + esc(link.note + " · " + link.date + ai) + "</span></button>";
    }).join("");
  }

  function renderSuggestions() {
    if (!S.suggestions.length) return "";
    return S.suggestions.map(function (s) {
      return '<article class="row"><span class="row-title">' + esc(s.name) + '</span><span class="row-meta">' + esc(s.quote) + '</span><span>' + btn("confirm-sug", "Confirm", "btn-filled", 'data-id="' + esc(s.id) + '"') + btn("decline-sug", "Decline", "btn-text", 'data-id="' + esc(s.id) + '"') + "</span></article>";
    }).join("");
  }

  function renderUpcoming() {
    var rows = liveItems().filter(function (it) { return (it.reminder || it.expiry) && !it.done; });
    var future = [];
    var expired = [];
    rows.forEach(function (it) {
      if (whenOf(it) < "2026-10-31T12:00") expired.push(it);
      else future.push(it);
    });
    future.sort(function (a, b) { return whenOf(a).localeCompare(whenOf(b)); });
    expired.sort(function (a, b) { return whenOf(b).localeCompare(whenOf(a)); });
    function block(it, expiredRow) {
      var title = it.sensitive && !S.sessionOpen ? COPY.sensitive : it.title;
      var meta = it.sensitive && !S.sessionOpen ? "" : (expiredRow ? "expired" : whenOf(it).replace("T", " "));
      return '<div class="row-split"><button type="button" class="row-main" data-act="open-item" data-id="' + esc(it.id) + '"><span class="row-title">' + esc(title) + "</span>" + (meta ? '<span class="row-meta">' + esc(meta) + "</span>" : "") + "</button><div>" + btn("done-up", COPY.done, "btn-text", 'data-id="' + esc(it.id) + '"') + btn("snooze", COPY.snooze, "btn-text", 'data-id="' + esc(it.id) + '"') + "</div></div>";
    }
    return future.map(function (it) { return block(it, false); }).join("") + (expired.length ? '<p class="kicker">' + esc(COPY.expired) + "</p>" : "") + expired.map(function (it) { return block(it, true); }).join("");
  }

  function renderInbox() {
    var rows = liveItems().filter(function (it) { return it.tags.length === 0; });
    return btn("open-suggestions", "Pending suggestions", "btn-text") + rows.map(function (it) { return row(it); }).join("");
  }

  function renderPicker() {
    var ids = S.pickerRows || [];
    var html = ids.map(function (id) {
      var it = byId(id);
      if (!it) return "";
      var title = it.sensitive && !S.sessionOpen ? COPY.sensitive : it.title + " · " + typeName(it.type);
      var restore = S.pickerTrash ? btn("picker-restore", COPY.restore, "btn-text", 'data-id="' + esc(it.id) + '"') : "";
      return '<div class="row-split"><button type="button" class="row-main" data-act="' + (it.sensitive && !S.sessionOpen ? "unlock-row" : "noop") + '" data-id="' + esc(it.id) + '"><span class="row-title">' + esc(title) + "</span></button>" + restore + "</div>";
    }).join("");
    html += '<div class="form-actions">' + btn("picker-dismiss", "dismiss", "btn-text") + "</div>";
    return html;
  }

  function passErrorHtml() {
    if (!S.passError && waitSeconds() <= 0) return "";
    var line = S.passError || COPY.wait;
    var extra = waitSeconds() > 0 ? '<p class="field-error">' + esc(String(waitSeconds())) + "s</p>" : "";
    return '<p class="field-error" role="alert">' + esc(line) + "</p>" + extra;
  }

  function renderPassphrase() {
    if (S.screen === "passphrase-decline") {
      return '<p class="empty">' + esc(S.declinedMessage || "No vault was created.") + "</p>" + '<div class="form-actions">' + btn("retry-setup", "Set a passphrase", "btn-filled") + "</div>";
    }
    var html = "";
    if (!S.hardware) html += '<p class="warn">' + esc(COPY.hardware) + "</p>";
    if (S.lowMemory) {
      html += '<p class="empty">This phone cannot allocate 65536 KiB, so a vault cannot be created.</p>';
      html += '<div class="form-actions">' + btn("decline-setup", COPY.decline, "btn-text") + "</div>";
      return html;
    }
    html += '<p class="guide">Choose a passphrase of 12 to 128 characters that you do not use anywhere else.</p>';
    html += '<p class="guide">The vault key is derived from this passphrase. Forgetting it and losing this device means the content is lost. Uninstall or clear storage destroys the Keystore copy. Losing the signing key forces a reinstall that destroys that copy too.</p>';
    html += field("Passphrase", "passDraft.next", S.passDraft.next, { secret: true });
    html += passErrorHtml();
    var dis = waitSeconds() > 0 ? "disabled" : "";
    html += '<div class="form-actions">' + btn("accept-setup", COPY.accept, "btn-filled", dis) + btn("decline-setup", COPY.decline, "btn-text") + "</div>";
    return html;
  }

  function renderChange() {
    var dis = waitSeconds() > 0 ? "disabled" : "";
    return field("Current passphrase", "passDraft.current", S.passDraft.current, { secret: true }) +
      field("New passphrase", "passDraft.next", S.passDraft.next, { secret: true }) +
      field("New passphrase again", "passDraft.again", S.passDraft.again, { secret: true }) +
      passErrorHtml() + '<div class="form-actions">' + btn("save-change", "Save", "btn-filled", dis) + "</div>";
  }

  function renderChangeSaved() {
    return '<p class="guide">Zips you already exported still open only with the old passphrase.</p><div class="form-actions">' + btn("to-settings", "Settings", "btn-text") + "</div>";
  }

  function renderReset() {
    return field("New passphrase", "passDraft.next", S.passDraft.next, { secret: true }) +
      field("New passphrase again", "passDraft.again", S.passDraft.again, { secret: true }) +
      passErrorHtml() + '<div class="form-actions">' + btn("save-reset", "Save", "btn-filled") + "</div>";
  }

  function renderReturning() {
    var dis = waitSeconds() > 0 ? "disabled" : "";
    return field("Passphrase", "passDraft.current", S.passDraft.current, { secret: true }) + passErrorHtml() +
      '<div class="form-actions">' + btn("return-accept", "Continue", "btn-filled", dis) + btn("return-cancel", "Cancel", "btn-text") + "</div>";
  }

  function renderFullscreen() {
    var it = current();
    var count = pageCount(it);
    var cls = artClass(S.fsIndex);
    return '<div class="fs"><div class="fs-top">' + btn("close-fs", "Close", "btn-text") + '<span class="pager">' + (S.fsIndex + 1) + " / " + count + "</span></div>" +
      '<div class="fs-stage" data-fs="1"><div class="' + cls + '" style="transform:scale(' + S.fsZoom + ')"></div></div></div>';
  }

  function pageCount(it) {
    if (!it) return 1;
    if (it.type === "pdf") return it.pages || 1;
    if (it.type === "image") return it.images || 1;
    return 1;
  }

  function body() {
    if (S.loading && S.screen === "home" && S.forceLoad) return "";
    switch (S.screen) {
      case "home": return renderHome();
      case "detail": return renderDetail();
      case "edit": return renderEdit();
      case "search": return renderSearch();
      case "add-note": return renderNote();
      case "add-url": return renderUrl();
      case "settings": return renderSettings();
      case "trash": return renderTrash();
      case "forget": return renderForget();
      case "topics": return renderTopics();
      case "topic": return renderTopic();
      case "suggestions": return renderSuggestions();
      case "upcoming": return renderUpcoming();
      case "inbox": return renderInbox();
      case "picker": return renderPicker();
      case "passphrase":
      case "passphrase-decline": return renderPassphrase();
      case "change": return renderChange();
      case "change-saved": return renderChangeSaved();
      case "reset": return renderReset();
      case "returning": return renderReturning();
      case "locked": return "";
      default: return "";
    }
  }

  function recentTags() {
    if (S.noTagHistory) return [];
    var seen = [];
    liveItems().slice().sort(function (a, b) { return b.savedAt.localeCompare(a.savedAt); }).forEach(function (it) {
      it.tags.forEach(function (t) { if (seen.indexOf(t) === -1 && seen.length < 8) seen.push(t); });
    });
    return seen;
  }

  function overlays() {
    var html = "";
    if (S.quick) {
      var it = byId(S.quick.id);
      var chips = recentTags().map(function (t) {
        return '<button type="button" class="chip" data-act="quick-tag" data-id="' + esc(S.quick.id) + '" data-tag="' + esc(t) + '">' + esc(t) + "</button>";
      }).join("");
      html += '<div class="scrim bottom" data-act="quick-dismiss"><div class="quick" data-act="stop"><div class="handle"></div><h2>' + esc(COPY.saved) + '</h2><div class="chips">' + chips + "</div>" + btn("quick-note", "Add note", "btn-text") + btn("quick-date", "Add date", "btn-text") + "</div></div>";
    }
    if (S.sheet) html += sheetHtml();
    if (S.dialog) html += dialogHtml();
    if (S.snack) {
      var actions = S.snack.undoKind === "merge"
        ? btn("undo", COPY.undo, "btn-text") + btn("keep-separate", COPY.keep, "btn-text")
        : btn("undo", COPY.undo, "btn-text");
      html += '<div class="snack-wrap"><div class="snack" role="status"><span>' + esc(S.snack.text) + "</span>" + actions + "</div></div>";
    }
    if (S.loading) html += '<div class="load" role="status" aria-label="Loading"><div class="spin"></div></div>';
    if (S.screen === "home" && !S.quick && !S.dialog && !S.sheet) {
      html += '<button type="button" class="fab" data-act="add-menu">' + I.add + " Add</button>";
    }
    return html;
  }

  function dialogBox(titleId, title, inner, buttons) {
    return '<div class="scrim" data-act="scrim"><div class="dialog" role="dialog" aria-modal="true" aria-labelledby="' + titleId + '" data-act="stop"><h2 id="' + titleId + '">' + esc(title) + "</h2>" + inner + '<div class="form-actions">' + buttons + "</div></div></div>";
  }

  function dialogHtml() {
    var d = S.dialog;
    if (!d) return "";
    if (d.kind === "disclosure") {
      return dialogBox("dlg-ml", "Text recognition", '<p>' + esc(COPY.disclosure) + "</p>", btn("ml-accept", COPY.accept, "btn-filled") + btn("ml-decline", COPY.decline, "btn-text"));
    }
    if (d.kind === "nothing") return dialogBox("dlg-no", "Lagoon", "<p>Nothing was saved.</p>", btn("scrim", "OK", "btn-text"));
    if (d.kind === "drive") {
      return dialogBox("dlg-drive", COPY.drive, "<p>This lasts only if every other phone is signed out first, because forgotten-installs files are removed too.</p>", btn("drive-confirm", "Confirm", "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    }
    if (d.kind === "forget-fail") return dialogBox("dlg-ff", COPY.forget, "<p>" + esc(COPY.nothingForgotten) + "</p>", btn("scrim", "OK", "btn-text"));
    if (d.kind === "pdf") {
      return dialogBox("dlg-pdf", "PDF", "<p>" + esc(COPY.pdf) + "</p>", btn("pdf-open", "Open", "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    }
    if (d.kind === "dropped") {
      var inner = "<p>" + esc(COPY.dropped) + "</p>" + (S.droppedTitle ? "<p>" + esc(S.droppedTitle) + "</p>" : "");
      return dialogBox("dlg-drop", "Lagoon", inner, btn("scrim", "OK", "btn-text"));
    }
    if (d.kind === "files") return dialogBox("dlg-files", "Lagoon", "<p>" + esc(COPY.files) + "</p>", btn("scrim", "OK", "btn-text"));
    if (d.kind === "edits") return dialogBox("dlg-edits", "Lagoon", "<p>" + esc(COPY.edits) + "</p>", btn("edits-ok", "OK", "btn-text"));
    if (d.kind === "try") return dialogBox("dlg-try", "Topics", "<p>" + esc(COPY.tryAgain) + "</p>", btn("try-again", COPY.tryAgain, "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    if (d.kind === "none") return dialogBox("dlg-none", "Topics", "<p>" + esc(COPY.noTopics) + "</p>", btn("scrim", "OK", "btn-text"));
    if (d.kind === "need-key") return dialogBox("dlg-key", "Topics", "<p>" + esc(COPY.missingKey) + "</p>", btn("scrim", "OK", "btn-text"));
    if (d.kind === "earlier") {
      var n = liveItems().filter(function (it) { return !it.sensitive && !it.provenance; }).length;
      return dialogBox("dlg-earlier", "Topics", "<p>Send " + n + " earlier items for topics?</p>", btn("earlier-no", COPY.no, "btn-filled") + btn("earlier-yes", COPY.yes, "btn-text"));
    }
    if (d.kind === "notif") {
      return dialogBox("dlg-notif", "Notifications", "<p>Allow Lagoon to send notifications?</p>", btn("notif-allow", "Allow", "btn-filled") + btn("notif-deny", "Don't allow", "btn-text"));
    }
    if (d.kind === "date") {
      return dialogBox("dlg-date", "Add date", '<label class="choice"><input type="radio" name="qd" data-bind="dateDraft.kind" value="reminder"' + (S.dateDraft.kind === "reminder" ? " checked" : "") + '> Reminder</label><label class="choice"><input type="radio" name="qd" data-bind="dateDraft.kind" value="expiry"' + (S.dateDraft.kind === "expiry" ? " checked" : "") + '> Expiry</label>' + field("Date", "dateDraft.date", S.dateDraft.date, { type: "date" }), btn("save-date", "Save", "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    }
    if (d.kind === "qnote") {
      return dialogBox("dlg-qn", "Add note", field("Why it matters", "qnote", S.qnote, { area: true }), btn("save-qnote", "Save", "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    }
    if (d.kind === "file") {
      var mode = d.mode === "import" ? COPY.importZip : COPY.exportZip;
      return dialogBox("dlg-file", mode, "<p>" + esc(mode === COPY.exportZip ? "lagoon.zip" : "Choose a zip") + "</p>", btn("file-done", mode === COPY.exportZip ? "Save" : "Open", "btn-filled") + btn("scrim", "Cancel", "btn-text"));
    }
    return "";
  }

  function sheetHtml() {
    var sh = S.sheet;
    if (!sh) return "";
    if (sh.kind === "add") {
      return '<div class="scrim sheet-wrap" data-act="scrim"><div class="sheet" data-act="stop"><div class="handle"></div>' + btn("open-note", "Add a note", "btn-row btn-text") + btn("open-url", "Add a URL", "btn-row btn-text") + "</div></div>";
    }
    if (sh.kind === "bio") {
      return '<div class="scrim" data-act="bio-cancel"><div class="dialog" role="dialog" aria-modal="true" aria-label="Unlock" data-act="stop"><button type="button" class="bio-mark" data-act="bio-ok" aria-label="Fingerprint"><span></span></button><div class="form-actions">' + btn("bio-cancel", "Cancel", "btn-text") + btn("use-pin", COPY.usePin, "btn-text") + "</div></div></div>";
    }
    if (sh.kind === "pin") {
      var dots = "";
      var i;
      for (i = 0; i < 4; i += 1) dots += '<i class="' + (S.pin.length > i ? "on" : "") + '"></i>';
      var keys = ["1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "⌫"].map(function (k) {
        if (!k) return "<span></span>";
        return '<button type="button" data-act="pin-key" data-key="' + esc(k) + '">' + esc(k) + "</button>";
      }).join("");
      return '<div class="scrim" data-act="bio-cancel"><div class="dialog" role="dialog" aria-modal="true" aria-label="PIN" data-act="stop"><div class="pin">' + dots + '</div><div class="keys">' + keys + '</div><div class="form-actions">' + btn("pin-ok", "Confirm", "btn-filled", S.pin.length === 4 ? "" : "disabled") + btn("bio-cancel", "Cancel", "btn-text") + "</div></div></div>";
    }
    if (sh.kind === "signin") {
      return '<div class="scrim sheet-wrap" data-act="scrim"><div class="sheet" data-act="stop"><div class="handle"></div><h2>Sign in</h2><button type="button" class="account" data-act="noop">miguel@example.com</button><label class="choice"><input type="checkbox" checked disabled> Allow Lagoon to use its Drive app data folder</label><div class="form-actions">' + btn("signin-go", "Continue", "btn-filled") + btn("scrim", "Cancel", "btn-text") + "</div></div></div>";
    }
    return "";
  }

  function shareScreen() {
    var payloads = [["link", "Link"], ["text", "Text"], ["image", "Image"], ["pdf", "PDF"], ["empty", "Empty text"], ["file", "file URI"], ["provider", "FileProvider"], ["big", "Over 20 MiB"], ["notpdf", "Not a PDF"], ["repeat", "Repeat link"], ["two", "Two matches"], ["trash", "Trash match"]];
    var chips = payloads.map(function (p) {
      return '<button type="button" data-act="share-pick" data-payload="' + p[0] + '" aria-pressed="' + (S.sharePayload === p[0] ? "true" : "false") + '">' + esc(p[1]) + "</button>";
    }).join("");
    var preview = S.sharePayload === "text" ? "Spring tide is on Sunday." : S.sharePayload;
    return '<div class="share-app"><div class="preview-card">' + esc(preview) + '</div><div class="scrim sheet-wrap" data-act="share-close"><div class="sheet" data-act="stop"><div class="handle"></div><div class="payloads">' + chips + '</div><div class="targets"><button type="button" class="app-ico" data-act="share-lagoon"><span class="squircle lagoon">' + I.home + '</span>Lagoon</button><span class="app-ico"><span class="squircle alt"></span>Messages</span></div></div></div></div>';
  }

  function launcherScreen(shade) {
    var theme = resolvedTheme();
    var icon = '<span class="squircle lagoon">' + I.home + "</span>";
    var html = '<div class="launcher"><p class="clock-big">09:41</p>';
    if (shade) {
      var title = shade === "sensitive" ? COPY.reminder : "Gate 4B boarding";
      var text = shade === "sensitive" ? COPY.reminder : "Gate 4B boarding";
      html += '<button type="button" class="shade" data-act="open-shade"><strong>Lagoon</strong><span>' + esc(title) + "</span><p>" + esc(text) + "</p></button>";
    }
    html += '<div class="launcher-grid"><span class="app-ico"><span class="squircle alt"></span>Phone</span><button type="button" class="app-ico" data-act="launcher-share"><span class="squircle msg"></span>Messages</button><button type="button" class="app-ico" data-act="launcher-camera"><span class="squircle cam">' + I.camera + '</span>Camera</button><button type="button" class="app-ico" data-act="open-app">' + icon + "Lagoon</button></div></div>";
    return html;
  }

  function cameraScreen() {
    if (S.screen === "camera-missing") {
      return '<div class="finder"></div><p class="empty">No camera app</p><div class="form-actions">' + btn("camera-cancel", "Cancel", "btn-text") + "</div>";
    }
    return '<div class="finder"></div><div class="form-actions">' + btn("camera-cancel", "Cancel", "btn-text") + btn("camera-confirm", "Confirm", "btn-filled") + "</div>";
  }

  function viewerScreen() {
    return '<div class="viewer-bar">' + iconBtn("viewer-close", "Close", I.close) + "<p>" + esc(S.viewerLabel || "") + '</p></div><div class="viewer-body"></div>';
  }

  function frame() {
    if (S.screen === "launcher") return launcherScreen();
    if (S.screen === "reminder") return launcherScreen("ordinary");
    if (S.screen === "reminder-sensitive") return launcherScreen("sensitive");
    if (S.screen === "share") return shareScreen();
    if (S.screen === "camera" || S.screen === "camera-missing") return cameraScreen();
    if (S.screen === "viewer") return viewerScreen();
    if (S.screen === "fullscreen") return renderFullscreen();
    var animate = lastScreen !== S.screen;
    lastScreen = S.screen;
    return appBar() + '<div class="content ' + (animate ? "content-in" : "") + '">' + body() + "</div>" + nav() + overlays();
  }

  function captureFocus() {
    var el = document.activeElement;
    if (!el || !el.dataset || !el.dataset.bind || !device.contains(el)) return null;
    return { bind: el.dataset.bind, start: el.selectionStart, end: el.selectionEnd };
  }

  function restoreFocus(saved) {
    if (!saved) return;
    var el = device.querySelector('[data-bind="' + saved.bind + '"]');
    if (!el) return;
    el.focus();
    if (saved.start != null && el.setSelectionRange) {
      try { el.setSelectionRange(saved.start, saved.end); } catch (e) { /* date inputs */ }
    }
  }

  function armSnack() {
    clearTimeout(snackTimer);
    if (!S.snack || !S.snack.until) return;
    var token = S.snack.token;
    var ms = S.snack.until - Date.now();
    snackTimer = setTimeout(function () {
      if (!S.snack || S.snack.token !== token) return;
      if (S.snack.undoKind === "trash") commitTrash();
      S.snack = null;
      render();
    }, Math.max(ms, 30));
  }

  function armWait() {
    clearInterval(waitTimer);
    if (S.waitUntil <= Date.now()) return;
    waitTimer = setInterval(function () {
      if (S.waitUntil <= Date.now()) {
        clearInterval(waitTimer);
        if (S.passError === COPY.wait) S.passError = "";
      }
      render();
    }, 400);
  }

  function paintChrome() {
    var theme = resolvedTheme();
    document.documentElement.dataset.theme = theme;
    document.documentElement.dataset.dynamic = S.dynamic ? "on" : "off";
    device.dataset.bright = S.bright ? "on" : "off";
    device.dataset.screen = S.screen;
    var meta = document.getElementById("theme-color");
    if (meta) meta.setAttribute("content", S.bright ? "#ffffff" : (theme === "dark" ? "#101211" : "#f7f6f4"));
  }

  function render() {
    var saved = captureFocus();
    paintChrome();
    device.innerHTML = frame();
    restoreFocus(saved);
    if (S.focusDialog) {
      var b = device.querySelector(".dialog button, .sheet button");
      if (b) b.focus();
      S.focusDialog = false;
    }
    armSnack();
    armWait();
    bindFs();
  }

  function bindFs() {
    var el = device.querySelector("[data-fs]");
    if (!el) return;
    var startX = 0;
    var startY = 0;
    el.addEventListener("pointerdown", function (e) { startX = e.clientX; startY = e.clientY; });
    el.addEventListener("pointerup", function (e) {
      var dx = e.clientX - startX;
      var dy = e.clientY - startY;
      if (Math.abs(dx) < 48 || Math.abs(dx) < Math.abs(dy) || S.fsZoom !== 1) return;
      stepFs(dx < 0 ? 1 : -1);
    });
    el.addEventListener("wheel", function (e) {
      e.preventDefault();
      zoomFs(e.deltaY < 0 ? 0.2 : -0.2);
    }, { passive: false });
  }

  function stepFs(dir) {
    var count = pageCount(current());
    var next = S.fsIndex + dir;
    if (next < 0 || next >= count) return;
    S.fsIndex = next;
    render();
  }

  function zoomFs(delta) {
    S.fsZoom = Math.min(4, Math.max(1, S.fsZoom + delta));
    var art = device.querySelector("[data-fs] .art");
    if (art) art.style.transform = "scale(" + S.fsZoom + ")";
  }

  function dismissDialog() {
    var kind = S.dialog && S.dialog.kind;
    var thenFiles = S.dialog && S.dialog.thenFiles;
    S.dialog = null;
    if (kind === "disclosure") S.pending = "";
    if (kind === "edits" && thenFiles) {
      S.dialog = { kind: "files" };
      S.focusDialog = true;
    }
  }

  function cancelSheet() {
    var kind = S.sheet && S.sheet.kind;
    S.sheet = null;
    S.afterUnlock = kind === "bio" || kind === "pin" ? "" : S.afterUnlock;
    if (kind === "bio" || kind === "pin") {
      if (S.locked) S.screen = "launcher";
    }
  }

  function bioOk() {
    S.sessionOpen = true;
    S.locked = false;
    var next = S.afterUnlock;
    S.sheet = null;
    S.afterUnlock = "";
    if (next === "detail") {
      S.readOnly = false;
      var opened = current();
      if (opened && (opened.type === "image" || opened.type === "pdf") && S.ml !== "yes") {
        S.pending = "open-detail";
        S.dialog = { kind: "disclosure" };
        S.focusDialog = true;
        return;
      }
      navigate("detail");
    }
    else if (next === "trash-read") { S.readOnly = true; navigate("detail"); }
    else if (next === "fs") { S.fsIndex = 0; S.fsZoom = 1; S.bright = true; navigate("fullscreen"); }
    else if (next === "forget") { navigate("forget"); }
    else if (next === "drive") { S.sync = "signedout"; }
    else if (next === "reset") {
      S.passDraft = { current: "", next: "", again: "" };
      S.passError = "";
      navigate("reset");
    } else if (next === "app") {
      S.screen = "home";
      S.tab = "home";
    }
  }

  function openSensitiveOr(id, next) {
    var it = byId(id);
    S.detailId = id;
    if (it && it.sensitive && !S.sessionOpen) {
      S.afterUnlock = next;
      S.sheet = { kind: "bio" };
      S.focusDialog = true;
      return true;
    }
    return false;
  }

  function askDisclosure(pending) {
    if (S.ml === "yes") return false;
    S.pending = pending;
    S.dialog = { kind: "disclosure" };
    S.focusDialog = true;
    return true;
  }

  function draftFrom(it) {
    S.editDraft = {
      title: it.title, body: it.body, why: it.note, tags: it.tags.join(", "),
      sensitive: !!it.sensitive, kind: it.reminder ? "reminder" : (it.expiry ? "expiry" : ""),
      date: (it.reminder || it.expiry || "").slice(0, 10)
    };
    S.editError = "";
    S.sentNote = false;
  }

  function saveEdit() {
    var it = current();
    if (!it) return;
    if (!S.editDraft.title.trim()) {
      S.editError = COPY.titleRequired;
      S.editDraft.title = it.title;
      return;
    }
    var became = it.topicSent && !it.sensitive && S.editDraft.sensitive;
    it.title = S.editDraft.title.trim();
    it.body = S.editDraft.body;
    it.note = S.editDraft.why;
    it.tags = S.editDraft.tags.split(",").map(function (s) { return s.trim(); }).filter(Boolean);
    it.sensitive = !!S.editDraft.sensitive;
    it.reminder = S.editDraft.kind === "reminder" ? S.editDraft.date : "";
    it.expiry = S.editDraft.kind === "expiry" ? S.editDraft.date : "";
    if (became) S.sentNote = true;
    if ((it.reminder || it.expiry) && !S.notificationsAsked) {
      S.notificationsAsked = true;
      S.dialog = { kind: "notif" };
      S.focusDialog = true;
      S.afterNotif = "stay";
      return;
    }
    if (!became) back();
  }

  var actions = {
    stop: function () { return false; },
    noop: function () { return false; },
    back: back,
    scrim: function () {
      if (S.dialog) dismissDialog();
      else if (S.sheet) cancelSheet();
      else if (S.quick) S.quick = null;
    },
    tab: function (t) {
      var tab = t.dataset.tab;
      if (S.screen === tab) return;
      leaveScreen();
      S.stack = [];
      S.tab = tab;
      S.screen = tab;
      S.hideItems = false;
      S.readOnly = false;
    },
    "open-item": function (t) {
      var id = t.dataset.id;
      S.readOnly = false;
      S.promptHidden = false;
      if (openSensitiveOr(id, "detail")) return;
      S.detailId = id;
      var it = byId(id);
      if (it && (it.type === "image" || it.type === "pdf") && askDisclosure("open-detail")) return;
      navigate("detail");
    },
    "unlock-row": function (t) {
      openSensitiveOr(t.dataset.id, "detail");
    },
    edit: function () {
      var it = current();
      if (!it) return;
      draftFrom(it);
      navigate("edit");
    },
    "save-edit": saveEdit,
    "delete-item": function () {
      var it = current();
      if (!it || S.readOnly) return;
      it.pendingTrash = true;
      S.snack = { undoKind: "trash", text: "In trash", until: Date.now() + 8000, token: uid(), id: it.id };
      S.stack = [];
      S.screen = "home";
      S.tab = "home";
      S.quick = null;
    },
    undo: function () {
      if (!S.snack) return;
      if (S.snack.undoKind === "trash") {
        var it = byId(S.snack.id);
        if (it) it.pendingTrash = false;
      }
      if (S.snack.undoKind === "merge" && S.snack.snap) {
        var snap = S.snack.snap;
        var live = byId(snap.id);
        if (live) Object.assign(live, snap, { tags: snap.tags.slice() });
      }
      S.snack = null;
    },
    "keep-separate": function () {
      if (!S.snack || !S.snack.snap) return;
      var snap = S.snack.snap;
      var live = byId(snap.id);
      if (live) Object.assign(live, snap, { tags: snap.tags.slice() });
      var url = S.pickerUrl || snap.url;
      S.snack = null;
      showQuick(addItem({ type: "link", title: url.slice(0, 120), url: url, source: hostOf(url) }));
    },
    "preview-yes": function () {
      var it = current();
      if (!it || it.sensitive) return;
      busy(700, function () { it.preview = "ready"; S.promptHidden = true; });
    },
    "preview-no": function () { S.promptHidden = true; },
    "show-prompt": function () { S.promptHidden = false; },
    "get-topics": function () {
      var it = current();
      if (!it || it.sensitive || S.readOnly) return;
      if (!S.topicReady) { S.dialog = { kind: "need-key" }; S.focusDialog = true; return; }
      if (S.topicFail === "try") { S.dialog = { kind: "try" }; S.focusDialog = true; return; }
      if (S.topicFail === "none") { S.dialog = { kind: "none" }; S.focusDialog = true; return; }
      addSuggestion(it);
      it.topicSent = true;
      S.calls += 1;
    },
    "try-again": function () {
      S.topicFail = "";
      S.dialog = null;
      actions["get-topics"]();
    },
    "share-out": function () {
      var it = current();
      S.sharePayload = it && it.url ? "repeat" : "text";
      if (it && it.url) S.sharePayload = "out";
      S.pickerUrl = it && it.url ? it.url : "";
      if (it && !it.url) S.sharePayload = "text";
      S.screen = "share";
    },
    "open-link": function () {
      var it = current();
      if (it && it.passwordPdf) { S.dialog = { kind: "pdf" }; S.focusDialog = true; return; }
      if (!it || !it.url || !/^https?:/i.test(it.url)) return;
      S.viewerLabel = it.url;
      navigate("viewer");
    },
    "open-fs": function () {
      var it = current();
      if (!it) return;
      if (it.passwordPdf) { S.dialog = { kind: "pdf" }; S.focusDialog = true; return; }
      if (openSensitiveOr(it.id, "fs")) return;
      S.fsIndex = 0;
      S.fsZoom = 1;
      S.bright = true;
      navigate("fullscreen");
    },
    "close-fs": function () { S.bright = false; back(); },
    "pdf-open": function () {
      S.viewerLabel = "Sealed minutes";
      S.dialog = null;
      navigate("viewer");
    },
    "add-menu": function () { S.sheet = { kind: "add" }; },
    "open-note": function () {
      S.noteDraft = { title: "", body: "", why: "" };
      S.noteError = "";
      S.sheet = null;
      navigate("add-note");
    },
    "open-url": function () {
      S.urlDraft = { url: "", title: "" };
      S.urlError = "";
      S.sheet = null;
      navigate("add-url");
    },
    "save-note": function () {
      var bodyText = (S.noteDraft.body || "").trim();
      if (!bodyText) { S.noteError = COPY.noteEmpty; return; }
      var title = (S.noteDraft.title || "").trim();
      if (!title) title = bodyText.split("\n")[0].slice(0, 120);
      var it = addItem({ type: "note", title: title, body: bodyText, note: S.noteDraft.why, source: "Lagoon" });
      S.noteError = "";
      showQuick(it);
    },
    "save-url": function () {
      var url = (S.urlDraft.url || "").trim();
      if (!url) { S.urlError = "A URL is required."; return; }
      var matches = findMatches(url);
      if (matches.live.length === 1) { S.pickerUrl = url; mergeInto(matches.live[0]); return; }
      if (matches.live.length > 1) { openPicker(matches.live, false, url); return; }
      if (matches.trashed.length && !matches.live.length) { openPicker(matches.trashed, true, url); return; }
      var title = (S.urlDraft.title || "").trim() || url.slice(0, 120);
      var it = addItem({ type: "link", title: title, url: url, source: hostOf(url), preview: tokenLooking(url) ? "fog" : "none" });
      S.urlError = "";
      showQuick(it);
    },
    camera: function () {
      if (!S.vault) { S.screen = "passphrase"; return; }
      S.screen = "camera";
    },
    "camera-cancel": function () { S.screen = S.vault ? "home" : "passphrase"; S.tab = "home"; },
    "camera-confirm": function () {
      var it = addItem({ type: "image", title: "photo.jpg", source: "Camera", images: 1 });
      showQuick(it);
      if (S.ml !== "yes") {
        S.pending = "camera";
        S.detailId = it.id;
        S.dialog = { kind: "disclosure" };
        S.focusDialog = true;
      } else it.ocr = "market stall";
    },
    upcoming: function () { navigate("upcoming"); },
    "done-up": function (t) { var it = byId(t.dataset.id); if (it) it.done = true; },
    snooze: function (t) { var it = byId(t.dataset.id); if (it) it.snooze = "2026-10-31T13:00"; },
    "open-topic": function (t) { S.topicId = t.dataset.id; navigate("topic"); },
    "open-suggestions": function () { navigate("suggestions"); },
    "confirm-sug": function (t) {
      var s = null;
      S.suggestions.forEach(function (item) { if (item.id === t.dataset.id) s = item; });
      if (!s) return;
      S.topics.push({ id: uid(), name: s.name, ai: true, links: [{ itemId: s.itemId, note: s.quote, date: "2026-10-31", ai: true }] });
      S.suggestions = S.suggestions.filter(function (item) { return item.id !== s.id; });
    },
    "decline-sug": function (t) {
      S.suggestions = S.suggestions.filter(function (item) { return item.id !== t.dataset.id; });
    },
    "open-trash": function () { navigate("trash"); },
    restore: function (t) {
      var it = byId(t.dataset.id);
      if (!it) return;
      it.trashedAt = "";
      it.pendingTrash = false;
    },
    "picker-restore": function (t) {
      var it = byId(t.dataset.id);
      if (!it) return;
      it.trashedAt = "";
      it.pendingTrash = false;
      it.note = (it.note ? it.note + "\n" : "") + "2026-10-31";
      S.screen = "home";
      S.tab = "home";
      S.stack = [];
    },
    "picker-dismiss": function () {
      var url = S.pickerUrl || "https://example.com/tide";
      showQuick(addItem({ type: "link", title: url.slice(0, 120), url: url, source: hostOf(url) }));
    },
    "open-forget": function () {
      S.afterUnlock = "forget";
      S.sheet = { kind: "bio" };
      S.focusDialog = true;
    },
    "forget-confirm": function () {
      if (S.forgetFail) { S.dialog = { kind: "forget-fail" }; S.focusDialog = true; return; }
      var ins = null;
      S.installs.forEach(function (i) { if (i.id === S.installId) ins = i; });
      if (!ins || ins.pending) return;
      S.installs = S.installs.filter(function (i) { return i.id !== ins.id; });
      S.installId = S.installs.length ? S.installs[0].id : "";
    },
    "sign-in": function () { S.sheet = { kind: "signin" }; },
    "signin-go": function () {
      S.sheet = null;
      busy(700, function () { S.sync = "signedin"; S.screen = "settings"; });
    },
    "sign-out": function () {
      if (S.sync === "signedin" || S.sync === "revoked" || S.sync === "full" || S.sync === "failed") S.sync = "signedout";
    },
    "drive-delete": function () { S.dialog = { kind: "drive" }; S.focusDialog = true; },
    "drive-confirm": function () {
      S.dialog = null;
      S.afterUnlock = "drive";
      S.sheet = { kind: "bio" };
      S.focusDialog = true;
    },
    "export-zip": function () { S.dialog = { kind: "file", mode: "export" }; S.focusDialog = true; },
    "import-zip": function () { S.dialog = { kind: "file", mode: "import" }; S.focusDialog = true; },
    "file-done": function () { S.dialog = null; },
    "open-change": function () {
      S.passDraft = { current: "", next: "", again: "" };
      S.passError = "";
      navigate("change");
    },
    "open-reset": function () {
      S.afterUnlock = "reset";
      S.sheet = { kind: "bio" };
      S.focusDialog = true;
    },
    "save-change": function () {
      if (waitSeconds() > 0) return;
      if (!samePhrase(S.passDraft.current, S.phrase)) { noteWrong(); return; }
      var err = checkPhrase(S.passDraft.next);
      if (err) { S.passError = err; return; }
      if (!samePhrase(S.passDraft.next, S.passDraft.again)) { S.passError = "The new passphrase does not match."; return; }
      S.phrase = S.passDraft.next.normalize("NFC");
      S.fails = 0;
      S.waitUntil = 0;
      S.passError = "";
      S.screen = "change-saved";
    },
    "save-reset": function () {
      var err = checkPhrase(S.passDraft.next);
      if (err) { S.passError = err; return; }
      if (!samePhrase(S.passDraft.next, S.passDraft.again)) { S.passError = "The new passphrase does not match."; return; }
      S.phrase = S.passDraft.next.normalize("NFC");
      S.passError = "";
      S.screen = "settings";
      S.tab = "settings";
      S.stack = [];
    },
    "to-settings": function () { S.screen = "settings"; S.tab = "settings"; S.stack = []; },
    "return-accept": function () {
      if (waitSeconds() > 0) return;
      if (!samePhrase(S.passDraft.current, S.phrase)) { noteWrong(); return; }
      S.fails = 0;
      S.waitUntil = 0;
      S.passError = "";
      busy(800, function () {
        S.screen = "home";
        S.tab = "home";
        S.stack = [];
        if (S.lost) S.dialog = { kind: "edits", thenFiles: true };
      });
    },
    "return-cancel": function () {
      S.screen = "home";
      S.tab = "home";
      S.stack = [];
      S.passError = "";
    },
    "edits-ok": function () {
      S.dialog = { kind: "files" };
      S.focusDialog = true;
    },
    "accept-setup": function () {
      if (S.loading || S.lowMemory) return;
      var err = checkPhrase(S.passDraft.next);
      if (err) { S.passError = err; return; }
      busy(800, function () {
        S.vault = true;
        S.phrase = S.passDraft.next.normalize("NFC");
        S.passError = "";
        S.screen = "home";
        S.tab = "home";
        S.stack = [];
        if (S.shareWaiting) {
          S.shareWaiting = false;
          finishShare();
        }
      });
    },
    "decline-setup": function () {
      var waiting = S.shareWaiting;
      S.vault = false;
      S.phrase = "";
      S.shareWaiting = false;
      S.declinedMessage = waiting ? "Nothing was saved." : "No vault was created.";
      S.screen = "passphrase-decline";
      S.stack = [];
    },
    "retry-setup": function () {
      S.declinedMessage = "";
      S.screen = "passphrase";
      S.passDraft = { current: "", next: "", again: "" };
      S.passError = "";
    },
    "ml-accept": function () {
      S.ml = "yes";
      S.dialog = null;
      if (S.pending === "open-detail") navigate("detail");
      if (S.pending === "camera") {
        var it = current();
        if (it) it.ocr = "market stall";
      }
      S.pending = "";
    },
    "ml-decline": function () {
      S.ml = "no";
      S.dialog = null;
      if (S.pending === "open-detail") navigate("detail");
      S.pending = "";
    },
    withdraw: function () { S.ml = "no"; },
    "save-topic-key": function () {
      if (!(S.topicDraft || "").trim()) return;
      S.topicDraft = "";
      S.topicReady = true;
      if (!S.askedEarlier) { S.dialog = { kind: "earlier" }; S.focusDialog = true; }
    },
    "earlier-no": function () { S.askedEarlier = true; S.dialog = null; },
    "earlier-yes": function () {
      var n = 0;
      liveItems().forEach(function (it) {
        if (!it.sensitive && !it.provenance) { it.provenance = true; n += 1; }
      });
      S.calls += n;
      S.askedEarlier = true;
      S.dialog = null;
    },
    "notif-allow": function () { S.notifications = "On"; S.dialog = null; },
    "notif-deny": function () { S.notifications = "Off"; S.dialog = null; },
    "quick-dismiss": function () { S.quick = null; },
    "quick-tag": function (t) {
      var it = byId(t.dataset.id);
      var tag = t.dataset.tag;
      if (it && tag && it.tags.indexOf(tag) === -1) it.tags.push(tag);
    },
    "quick-note": function () { S.qnote = ""; S.dialog = { kind: "qnote" }; S.focusDialog = true; },
    "save-qnote": function () {
      var it = S.quick ? byId(S.quick.id) : null;
      if (it) {
        var extra = (S.qnote || "").trim();
        it.note = (it.note ? it.note + "\n" : "") + "2026-10-31" + (extra ? "\n" + extra : "");
        it.noteDate = "2026-10-31";
      }
      S.dialog = null;
    },
    "quick-date": function () {
      S.dateDraft = { kind: "reminder", date: "2026-11-02" };
      S.dialog = { kind: "date" };
      S.focusDialog = true;
    },
    "save-date": function () {
      var it = S.quick ? byId(S.quick.id) : null;
      if (it) {
        it.reminder = S.dateDraft.kind === "reminder" ? S.dateDraft.date : "";
        it.expiry = S.dateDraft.kind === "expiry" ? S.dateDraft.date : "";
      }
      S.dialog = null;
      if (!S.notificationsAsked) {
        S.notificationsAsked = true;
        S.dialog = { kind: "notif" };
        S.focusDialog = true;
      }
    },
    "cancel-reset": function () { S.resetBanner = false; },
    "bio-ok": bioOk,
    "bio-cancel": function () { cancelSheet(); },
    "use-pin": function () { S.sheet = { kind: "pin" }; S.pin = ""; },
    "pin-key": function (t) {
      var key = t.dataset.key;
      if (key === "⌫") S.pin = S.pin.slice(0, -1);
      else if (S.pin.length < 4) S.pin += key;
    },
    "pin-ok": function () { if (S.pin.length === 4) bioOk(); },
    "share-pick": function (t) { S.sharePayload = t.dataset.payload; },
    "share-lagoon": function () {
      if (!S.vault) {
        S.shareWaiting = true;
        S.screen = "passphrase";
        return;
      }
      finishShare();
    },
    "share-close": function () { S.screen = S.vault ? "home" : "launcher"; },
    "open-app": function () {
      if (!S.vault) { S.screen = "passphrase"; S.locked = false; return; }
      if (S.lowMemory) { S.screen = "passphrase"; return; }
      if (S.appLock && S.locked) {
        S.screen = "locked";
        S.afterUnlock = "app";
        S.sheet = { kind: "bio" };
        S.sessionOpen = false;
        return;
      }
      S.screen = "home";
      S.tab = "home";
    },
    "launcher-share": function () { S.screen = "share"; S.sharePayload = "link"; },
    "launcher-camera": function () {
      if (!S.vault) { S.screen = "passphrase"; return; }
      S.screen = "camera";
    },
    "open-shade": function () {
      if (S.screen === "reminder-sensitive") {
        S.detailId = "n-lockern";
        S.afterUnlock = "detail";
        S.screen = "locked";
        S.sheet = { kind: "bio" };
        return;
      }
      S.detailId = "n-gate";
      S.readOnly = false;
      S.screen = "detail";
      S.stack = ["home"];
    },
    "viewer-close": function () { back(); }
  };

  actions["open-trash-row"] = function (t) {
    var id = t.dataset.id;
    if (openSensitiveOr(id, "trash-read")) return;
    S.detailId = id;
    S.readOnly = true;
    navigate("detail");
  };

  function prep(opts) {
    opts = opts || {};
    var theme = S.theme;
    fresh();
    S.theme = theme;
    loadDemo();
    S.hardware = opts.hardware !== false;
    S.lowMemory = !!opts.lowMemory;
    S.api = opts.api || 34;
    S.screen = opts.screen || "home";
    S.tab = opts.tab || (["home", "search", "inbox", "topics", "settings"].indexOf(S.screen) !== -1 ? S.screen : "home");
    S.stack = opts.stack ? opts.stack.slice() : [];
    S.paused = !!opts.paused;
    S.clock = !!opts.clock;
    S.resetBanner = !!opts.resetBanner;
    S.hideItems = !!opts.hideItems;
    S.hideTopics = !!opts.hideTopics;
    S.hideInstalls = !!opts.hideInstalls;
    S.sessionOpen = !!opts.sessionOpen;
    S.dynamic = !!opts.dynamic;
    S.sync = opts.sync || "unconfigured";
    S.ml = opts.ml || "no";
    S.topicReady = !!opts.topicReady;
    S.query = opts.query || "";
    S.filters = { type: "all", tag: opts.tag || "", source: "", date: "" };
    S.detailId = opts.detailId || null;
    S.readOnly = !!opts.readOnly;
    S.bright = S.screen === "fullscreen";
    S.passError = opts.passError || "";
    S.waitUntil = opts.wait || 0;
    S.fails = opts.fails || 0;
    S.noTagHistory = !!opts.noTagHistory;
    S.topicFail = opts.topicFail || "";
    S.forgetFail = !!opts.forgetFail;
    S.sentNote = !!opts.sentNote;
    S.editError = opts.editError || "";
    S.noteError = opts.noteError || "";
    S.lost = !!opts.lost;
    S.loading = !!opts.loading;
    S.forceLoad = !!opts.loading;
    S.vault = opts.vault !== false;
    S.declinedMessage = opts.declinedMessage || "";
    S.sharePayload = opts.sharePayload || "link";
    S.appLock = !!opts.appLock;
    S.locked = !!opts.locked;
    if (opts.dialog) S.dialog = opts.dialog;
    if (opts.sheet) S.sheet = opts.sheet;
    if (opts.quick) S.quick = opts.quick;
    if (opts.topicId) S.topicId = opts.topicId;
    if (opts.promptHidden) S.promptHidden = true;
    S.focusDialog = !!(opts.dialog || opts.sheet);
  }

  var jumps = {
    passphrase: function () { var theme = S.theme; fresh(); S.theme = theme; S.screen = "passphrase"; },
    "passphrase-hardware": function () { jumps.passphrase(); S.hardware = false; },
    "passphrase-memory": function () { jumps.passphrase(); S.lowMemory = true; },
    "passphrase-decline": function () { jumps.passphrase(); S.screen = "passphrase-decline"; S.declinedMessage = "No vault was created."; },
    home: function () { prep({ screen: "home" }); },
    "home-empty": function () { prep({ screen: "home", hideItems: true }); },
    "home-paused": function () { prep({ screen: "home", paused: true, sync: "signedin" }); },
    "home-filter-empty": function () { prep({ screen: "home", tag: "missing" }); },
    "home-loading": function () { prep({ screen: "home", loading: true }); },
    detail: function () { prep({ screen: "detail", detailId: "n-ferry", stack: ["home"] }); },
    "detail-preview": function () { prep({ screen: "detail", detailId: "n-token", stack: ["home"] }); },
    "detail-failed": function () { prep({ screen: "detail", detailId: "n-harbour", stack: ["home"] }); },
    "detail-locked": function () { prep({ screen: "home", detailId: "n-key", sheet: { kind: "bio" } }); S.afterUnlock = "detail"; },
    "detail-sensitive": function () { prep({ screen: "detail", detailId: "n-key", sessionOpen: true, stack: ["home"] }); },
    edit: function () { prep({ screen: "edit", detailId: "n-ferry", stack: ["detail", "home"] }); draftFrom(byId("n-ferry")); },
    "edit-title": function () { prep({ screen: "edit", detailId: "n-ferry", stack: ["detail"], editError: COPY.titleRequired }); draftFrom(byId("n-ferry")); S.editError = COPY.titleRequired; },
    "edit-sent": function () { prep({ screen: "edit", detailId: "n-plants", stack: ["detail"], sentNote: true }); draftFrom(byId("n-plants")); S.editDraft.sensitive = true; S.sentNote = true; },
    search: function () { prep({ screen: "search", query: "ferry" }); },
    "search-miss": function () { prep({ screen: "search", query: "zzzz" }); },
    "quick-add": function () { prep({ screen: "home", quick: { id: "n-grocery" } }); },
    "quick-add-empty": function () { prep({ screen: "home", quick: { id: "n-grocery" }, noTagHistory: true }); },
    "add-note": function () { prep({ screen: "add-note", stack: ["home"] }); },
    "add-note-empty": function () { prep({ screen: "add-note", stack: ["home"], noteError: COPY.noteEmpty }); },
    "add-url": function () { prep({ screen: "add-url", stack: ["home"] }); },
    picker: function () { prep({ screen: "picker", stack: ["home"] }); S.pickerRows = ["n-tide", "n-tide2"]; S.pickerTrash = false; S.pickerUrl = "https://example.com/tide"; },
    "picker-trash": function () { prep({ screen: "picker", stack: ["home"] }); S.pickerRows = ["n-old"]; S.pickerTrash = true; S.pickerUrl = "https://example.com/old-article"; },
    settings: function () { prep({ screen: "settings" }); },
    "settings-signed-in": function () { prep({ screen: "settings", sync: "signedin" }); },
    "settings-signed-out": function () { prep({ screen: "settings", sync: "signedout" }); },
    "settings-revoked": function () { prep({ screen: "settings", sync: "revoked" }); },
    "settings-full": function () { prep({ screen: "settings", sync: "full" }); },
    "settings-upload": function () { prep({ screen: "settings", sync: "failed" }); },
    "settings-paused": function () { prep({ screen: "settings", sync: "signedin", paused: true }); },
    "settings-api30": function () { prep({ screen: "settings", api: 30 }); },
    "settings-hardware": function () { prep({ screen: "settings", hardware: false }); },
    "settings-ocr-yes": function () { prep({ screen: "settings", ml: "yes" }); },
    "settings-ocr-no": function () { prep({ screen: "settings", ml: "no" }); },
    "settings-key": function () { prep({ screen: "settings", topicReady: false }); },
    "settings-key-stored": function () { prep({ screen: "settings", topicReady: true }); },
    "drive-delete": function () { prep({ screen: "settings", sync: "signedin", dialog: { kind: "drive" } }); },
    trash: function () { prep({ screen: "trash", stack: ["settings"] }); },
    "trash-deadline": function () { prep({ screen: "trash", stack: ["settings"] }); },
    "trash-read": function () { prep({ screen: "detail", detailId: "n-pass", readOnly: true, stack: ["trash"] }); },
    forget: function () { prep({ screen: "forget", stack: ["settings"] }); },
    "forget-empty": function () { prep({ screen: "forget", hideInstalls: true, stack: ["settings"] }); },
    "forget-failed": function () { prep({ screen: "forget", forgetFail: true, dialog: { kind: "forget-fail" }, stack: ["settings"] }); },
    fullscreen: function () { prep({ screen: "fullscreen", detailId: "n-pdf", stack: ["detail"] }); S.bright = true; },
    "pdf-password": function () { prep({ screen: "detail", detailId: "n-lock", dialog: { kind: "pdf" }, stack: ["home"] }); },
    "change-pass": function () { prep({ screen: "change", stack: ["settings"] }); },
    "change-pass-saved": function () { prep({ screen: "change-saved", stack: ["settings"] }); },
    "pass-wrong": function () { prep({ screen: "change", stack: ["settings"], passError: COPY.wait, wait: Date.now() + 2000, fails: 1 }); },
    "reset-local": function () { prep({ screen: "reset", stack: ["settings"], sessionOpen: true }); },
    "reset-waiting": function () { prep({ screen: "home", resetBanner: true }); },
    returning: function () { prep({ screen: "returning" }); },
    "returning-lost": function () { prep({ screen: "returning", lost: true }); },
    disclosure: function () { prep({ screen: "home", dialog: { kind: "disclosure" } }); },
    topics: function () { prep({ screen: "topics" }); },
    "topics-empty": function () { prep({ screen: "topics", hideTopics: true }); },
    suggestions: function () { prep({ screen: "suggestions", stack: ["inbox"] }); },
    topic: function () { prep({ screen: "topic", topicId: "t-plants", stack: ["topics"] }); },
    "topic-empty": function () { prep({ screen: "topic", topicId: "t-empty", stack: ["topics"] }); },
    "topic-sensitive": function () { prep({ screen: "topic", topicId: "t-ferry", stack: ["topics"] }); },
    "topic-try": function () { prep({ screen: "detail", detailId: "n-ferry", topicFail: "try", dialog: { kind: "try" }, stack: ["home"] }); },
    "topic-none": function () { prep({ screen: "detail", detailId: "n-ferry", topicFail: "none", dialog: { kind: "none" }, stack: ["home"] }); },
    "send-earlier": function () { prep({ screen: "settings", topicReady: true, dialog: { kind: "earlier" } }); },
    upcoming: function () { prep({ screen: "upcoming", stack: ["home"] }); },
    inbox: function () { prep({ screen: "inbox" }); },
    "snack-undo": function () {
      prep({ screen: "home" });
      var it = byId("n-grocery");
      it.pendingTrash = true;
      S.snack = { undoKind: "trash", text: "In trash", until: Date.now() + 8000, token: uid(), id: it.id };
    },
    "snack-repeat": function () {
      prep({ screen: "home" });
      var it = byId("n-plants");
      S.snack = { undoKind: "merge", text: COPY.repeat, until: Date.now() + 8000, token: uid(), snap: cloneItem(it) };
      S.pickerUrl = it.url;
    },
    dropped: function () { prep({ screen: "home", dialog: { kind: "dropped" } }); S.droppedTitle = ""; },
    "dropped-title": function () { prep({ screen: "home", dialog: { kind: "dropped" }, sessionOpen: true }); S.droppedTitle = "Ferry times to the island"; },
    "files-removed": function () { prep({ screen: "home", dialog: { kind: "files" } }); },
    "edits-lost": function () { prep({ screen: "home", dialog: { kind: "edits" } }); },
    reminder: function () { prep({ screen: "reminder" }); },
    "reminder-sensitive": function () { prep({ screen: "reminder-sensitive" }); },
    share: function () { prep({ screen: "share", sharePayload: "link" }); },
    "sign-in": function () { prep({ screen: "settings", sheet: { kind: "signin" } }); },
    biometric: function () { prep({ screen: "home", sheet: { kind: "bio" } }); S.afterUnlock = "app"; },
    camera: function () { prep({ screen: "camera" }); },
    "camera-missing": function () { prep({ screen: "camera-missing" }); },
    viewer: function () { prep({ screen: "viewer", stack: ["detail"] }); S.viewerLabel = "https://example.com/coastal-plants"; },
    launcher: function () { prep({ screen: "launcher" }); },
    clock: function () { prep({ screen: "home", clock: true }); }
  };

  function routeFromHash() {
    var h = location.hash.replace(/^#/, "");
    if (h.charAt(0) === "/") h = h.slice(1);
    return h;
  }

  function applyJump(id) {
    if (!jumps[id]) return;
    jumps[id]();
    var next = "#/" + id;
    if (location.hash !== next) history.replaceState(null, "", next);
  }

  function onClick(e) {
    var t = e.target.closest("[data-act]");
    if (!t || !device.contains(t)) return;
    if (S.loading) return;
    var fn = actions[t.dataset.act];
    if (!fn) return;
    var result = fn(t, e);
    if (result !== false) render();
  }

  function onInput(e) {
    var t = e.target;
    if (!t.dataset || !t.dataset.bind) return;
    setBind(t.dataset.bind, t.value);
    if (t.dataset.live) render();
  }

  function onChange(e) {
    var t = e.target;
    if (t.dataset && t.dataset.bind) {
      if (t.type === "checkbox") setBind(t.dataset.bind, t.checked);
      else setBind(t.dataset.bind, t.value);
    }
    if (t.dataset && t.dataset.bind === "theme") {
      try { localStorage.setItem("lagoon-theme", S.theme); } catch (err) { /* ignore */ }
    }
    if (t.dataset && t.dataset.change === "dynamic") S.dynamic = t.checked;
    if (t.dataset && t.dataset.change === "lock") S.appLock = t.checked;
    if (t.dataset && t.dataset.change === "topics") S.topicsOn = t.checked;
    if (t.dataset && t.dataset.change === "unmetered") S.unmetered = t.checked;
    if (t.dataset && t.dataset.change === "topics-unmetered") S.topicsUnmetered = t.checked;
    if (t.dataset && t.dataset.change === "install") S.installId = t.dataset.id;
    var type = t.type || "";
    if (type === "password" || type === "text" || type === "textarea" || t.tagName === "TEXTAREA") return;
    render();
  }

  function boot() {
    try {
      var saved = localStorage.getItem("lagoon-theme");
      if (saved === "light" || saved === "dark" || saved === "system") S.theme = saved;
    } catch (e) { /* ignore */ }
    device.addEventListener("click", onClick);
    device.addEventListener("input", onInput);
    device.addEventListener("change", onChange);
    document.addEventListener("keydown", function (e) {
      if (e.key === "Escape") {
        if (S.dialog || S.sheet || S.quick) { actions.scrim(); render(); return; }
        var tag = e.target && e.target.tagName;
        if (tag === "INPUT" || tag === "TEXTAREA" || tag === "SELECT") return;
        back();
        render();
        return;
      }
      if (S.screen !== "fullscreen") return;
      if (e.target && !device.contains(e.target) && e.target !== document.body) return;
      if (e.key === "ArrowRight") stepFs(1);
      if (e.key === "ArrowLeft") stepFs(-1);
      if (e.key === "+" || e.key === "=") zoomFs(0.25);
      if (e.key === "-" || e.key === "_") zoomFs(-0.25);
    });
    window.matchMedia("(prefers-color-scheme: dark)").addEventListener("change", function () {
      if (S.theme === "system") render();
    });
    window.addEventListener("hashchange", function () {
      var id = routeFromHash();
      if (!jumps[id]) return;
      jumps[id]();
      render();
    });
    var hash = routeFromHash();
    if (jumps[hash]) applyJump(hash);
    else jumps.passphrase();
    render();
  }

  boot();
}());
