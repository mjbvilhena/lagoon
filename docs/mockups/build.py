#!/usr/bin/env python3
"""Regenerate the static UX review pages in this directory.

The script does not edit docs/technical_design/ux-design.md. It checks that
every product string it writes is contained in that file at UX_COMMIT.
"""

from __future__ import annotations

import html
import re
import subprocess
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
OUT = Path(__file__).resolve().parent
UX_REL = "docs/technical_design/ux-design.md"
UX_COMMIT = "29c343afe30a9124c7788e212e1ee94e51192df7"
BANNER = (
    "Review aid only. docs/technical_design/ux-design.md is the source of truth. "
    "If this mockup and that file disagree, the file wins."
)

UX = subprocess.check_output(["git", "show", f"{UX_COMMIT}:{UX_REL}"], cwd=ROOT)
if (ROOT / UX_REL).read_bytes() != UX:
    raise SystemExit(f"{UX_REL} on disk does not match {UX_COMMIT}")
UX_TEXT = UX.decode("utf-8")


def must(value: str) -> str:
    if value not in UX_TEXT:
        raise SystemExit(f"Not in ux-design.md: {value!r}")
    return value


def esc(value: str) -> str:
    return html.escape(value, quote=True)


def slot(name: str) -> str:
    return (
        f'<span class="slot"><span class="slot-name">{esc(name)}</span>'
        f'<span class="slot-gap">not specified in ux-design.md</span></span>'
    )


def btn(label: str, *, quote: bool = True) -> str:
    if quote:
        must(label)
    return f'<button type="button" class="btn">{esc(label)}</button>'


def inline_btn(label: str) -> str:
    must(label)
    return f'<button type="button" class="inline-btn">{esc(label)}</button>'


def btn_row(*labels: str) -> str:
    return '<div class="btn-row">' + "".join(btn(label) for label in labels) + "</div>"


def app_title(text: str) -> str:
    must(text)
    return f'<p class="app-title">{esc(text)}</p>'


def copy(text: str) -> str:
    must(text)
    return f"<p>{esc(text)}</p>"


def secret_field(label: str) -> str:
    must(label)
    return (
        f'<label class="field">{esc(label)}'
        '<input type="password" autocomplete="off" spellcheck="false" autocapitalize="off">'
        "</label>"
    )


def text_field(label: str) -> str:
    must(label)
    return (
        f'<label class="field">{esc(label)}'
        '<input type="text" autocomplete="off" spellcheck="false">'
        "</label>"
    )


def area_field(label: str) -> str:
    must(label)
    return f'<label class="field">{esc(label)}<textarea></textarea></label>'


SYNC_PAUSED = must(
    "Sync is paused. Lagoon reached Drive and could not read which phones were forgotten. "
    "Nothing on this phone was deleted or uploaded. It will try again."
)
DISCLOSURE = must(
    "The image and the recognized text stay on this phone. They are not sent to Google. "
    "ML Kit sends usage and diagnostic metrics to Google over HTTPS. ML Kit does not transfer "
    "them to third parties. ML Kit may contact Google for model updates and bug fixes. This app "
    "has no switch to turn those metrics off. The metrics are: device information and ML hardware "
    "accelerators, package name and version, per-installation identifiers, performance metrics, "
    "API configuration, input and output size, feature version, event types, and error codes."
)
HARDWARE = must(
    "This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key."
)
TOPIC_KEY = must(
    "The topic key is not copied from the other phone. Type it again on this phone."
)

FORBIDDEN_IN_SCREEN = (
    "Back up now",
    "Check my passphrase",
    "last-backup",
    "Delete forever",
    "Delete now",
    "Empty trash",
    "lorem ipsum",
    "TODO",
    "strength meter",
)
RESTORE_SLUGS = {"trash", "trash-after-deadline", "which-item-trash"}


def ordinary_row() -> str:
    return (
        '<article class="row">'
        f"<p>{slot('title')}</p>"
        f"<p class=\"meta\">{slot('type')} · {slot('saved date')} · {slot('source')}</p>"
        f"<p class=\"meta\">{slot('reminder or expiry marker')}</p>"
        "</article>"
    )


def sensitive_row() -> str:
    must("Sensitive")
    return (
        '<article class="row">'
        "<p>Sensitive</p>"
        f"<p class=\"meta\">{slot('type')} · Sensitive</p>"
        "</article>"
    )


def filters() -> str:
    must("type, tag, source, date")
    return '<div class="region"><p>type · tag · source · date</p></div>'


def banner_block() -> str:
    return f'<p class="banner-copy">{esc(SYNC_PAUSED)}</p>'


def screen(inner: str, footer: str = "") -> str:
    foot = f'<div class="screen-foot">{footer}</div>' if footer else ""
    return f'<div class="screen"><div class="screen-scroll">{inner}</div>{foot}</div>'


def settings_screen(
    sync: str,
    *,
    dynamic: bool = True,
    hardware: bool = False,
    topic_key: bool = True,
    accepted: str = "yes or no",
    banner: bool = False,
) -> str:
    must("Light")
    must("Dark")
    must("System")
    must("Dynamic color")
    must("Unmetered only")
    must("Sign in")
    must("Sign out")
    must("Disconnect and delete Lagoon data from Drive")
    must("Forget this phone")
    must("Reminder default time")
    must("09:00")
    must("Notifications")
    must("App lock")
    must("Topics")
    must("Topics unmetered only")
    must("This month's topic calls")
    must("Text recognition")
    must("Withdraw acceptance")
    must("Export zip")
    must("Import zip")
    must("Trash")
    must("[off]")
    must("[on]")
    must("[ ]")
    if accepted == "yes or no":
        accepted_line = must("Accepted: yes or no")
    elif accepted in ("yes", "no"):
        must("Accepted: yes or no")
        accepted_line = f"Accepted: {accepted}"
    else:
        raise SystemExit(accepted)
    parts = [
        app_title("Settings"),
        '<fieldset class="choice"><legend>Theme</legend>',
        '<label><input type="radio" name="in-app-theme" disabled> Light</label>',
        '<label><input type="radio" name="in-app-theme" disabled> Dark</label>',
        '<label><input type="radio" name="in-app-theme" checked disabled> System</label>',
        "</fieldset>",
    ]
    if dynamic:
        parts.append("<p>Dynamic color [off]</p>")
    parts.append("<p>Sync</p>")
    parts.append(f"<p>{esc(must(sync))}</p>")
    if banner:
        parts.append(banner_block())
    parts.extend(
        [
            "<p>Unmetered only [on]</p>",
            btn("Sign in"),
            btn("Sign out"),
            btn("Disconnect and delete Lagoon data from Drive"),
            btn("Forget this phone"),
            "<p>Reminder default time 09:00</p>",
            f"<p>Notifications {slot('state')}</p>",
            "<p>App lock [off]</p>",
            "<p>Topics [on]</p>",
            "<p>Topics unmetered only [ ]</p>",
            "<p>This month's topic calls N</p>",
        ]
    )
    if topic_key:
        parts.append(f"<p>{esc(TOPIC_KEY)}</p>")
    parts.extend(
        [
            "<p>Text recognition</p>",
            f"<p>{esc(DISCLOSURE)}</p>",
            f"<p>{esc(accepted_line)}</p>",
            btn("Withdraw acceptance"),
            btn("Export zip"),
            btn("Import zip"),
            btn("Trash"),
        ]
    )
    if hardware:
        parts.append(f"<p>{esc(HARDWARE)}</p>")
    return screen("".join(parts))


PAGES: list[dict] = []


def add(
    slug: str,
    title: str,
    state: str,
    group: str,
    anchors: list[str],
    screen_html: str | None,
    gaps: list[str],
    notes: list[str],
    *,
    frame: str = "lagoon",
    below: str = "",
) -> None:
    if screen_html:
        for phrase in FORBIDDEN_IN_SCREEN:
            if phrase.lower() in screen_html.lower():
                raise SystemExit(f"{slug} screen contains {phrase}")
        if "Restore" in screen_html and slug not in RESTORE_SLUGS:
            raise SystemExit(f"{slug} draws Restore")
        if re.search(r"(?i)0f766e", screen_html):
            raise SystemExit(f"{slug} hard-codes the light accent inside the screen")
    PAGES.append(
        {
            "slug": slug,
            "title": title,
            "state": state,
            "group": group,
            "anchors": anchors,
            "screen": screen_html,
            "gaps": gaps,
            "notes": notes,
            "frame": frame,
            "below": below,
        }
    )


# --- Home ---

add(
    "home-list",
    "Home list",
    "Populated rows",
    "Home",
    ["home-list"],
    screen(app_title("Lagoon") + filters() + ordinary_row() + sensitive_row()),
    [
        "Filter control shape is not specified. The four names are the wireframe line.",
        "The sample source text is not specified. Source is included when US-61 has one.",
        "An order control is not specified. Rows are ordered by last-saved, newest first.",
        "A home row does not draw a link preview or the foggy image. None is drawn.",
    ],
    [
        "Rows are ordered by last-saved, newest first.",
        "A trashed item is absent.",
        "A locked sensitive row shows type and Sensitive, with no title, thumbnail, snippet, or code.",
        "Backgrounding the app returns rows to that neutral form.",
    ],
)

add(
    "home-list-empty",
    "Home list",
    "Empty vault",
    "Home",
    ["home-list"],
    screen(app_title("Lagoon") + copy("Nothing is saved yet.")),
    ["Whether the filter row is shown on an empty vault is not specified. It is not drawn."],
    ["The empty sentence is the proposed copy from the Home wireframe."],
)

add(
    "home-list-sync-paused",
    "Home list",
    "Sync-paused banner",
    "Home",
    ["home-list", "sync-paused-banner"],
    screen(app_title("Lagoon") + banner_block() + filters() + ordinary_row() + sensitive_row()),
    ["No dismiss control is specified for the banner. None is drawn."],
    [
        "The banner is on the home list and in the sync area of Settings, and nowhere else, while the condition holds.",
        "It appears after 3 consecutive syncs that reach Drive and fail to read or list a forgotten-installs file.",
        "It clears at the first sync that reads every forgotten-installs file successfully.",
        "An offline sync, when Drive is not reachable, does not count and does not reset the run.",
        "A file under a newer key is not a read failure.",
        "The banner names no item title, no install id, and no passphrase. It is not a system notification.",
    ],
)

add(
    "home-list-filter-empty",
    "Home list",
    "Filter matches nothing",
    "Home",
    ["home-list", "flows"],
    screen(app_title("Lagoon") + filters()),
    ["No empty-filter sentence is specified. No error is drawn."],
    ["A filter that matches nothing shows an empty list, not an error."],
)

# --- Item ---

detail_actions = "".join(btn(label) for label in ("Get topics", "Share", "Open", "Delete"))

add(
    "item-detail",
    "Item detail",
    "Ordinary item",
    "Item",
    ["item-detail"],
    screen(
        '<div class="title-line"><p class="app-title">'
        + slot("title")
        + "</p>"
        + btn("Edit")
        + "</div>"
        + f"<p>{slot('type')} · {slot('last-saved')} · {slot('source')}</p>"
        + f"<p>URL {slot('link')}</p>"
        + f"<p>{slot('body')}</p>"
        + f"<p>personal note, dated YYYY-MM-DD {slot('note text and date')}</p>"
        + f'<div class="attachment">{slot("image or PDF page")}</div>'
        + detail_actions
    ),
    [
        "Fetch preview is omitted because this state is not a skipped preview.",
        "Optional fields that are empty are omitted, not filled with placeholder sentences. The dashed labels are review annotations for sample values, not those sentences.",
        "One tap opens full screen. This static page does not navigate.",
    ],
    [
        "Share and Open are the US-64 actions: share out, or Custom Tabs or ACTION_VIEW for http and https. No WebView.",
        "Delete starts the undo snackbar. That snackbar is a separate page.",
        "Mark sensitive lives on Edit. It is not drawn here.",
    ],
)

add(
    "item-detail-preview",
    "Item detail",
    "Skipped preview",
    "Item",
    ["item-detail", "link-preview-prompt"],
    screen(
        '<div class="title-line"><p class="app-title">'
        + slot("title")
        + "</p>"
        + btn("Edit")
        + "</div>"
        + f"<p>{slot('type')} · {slot('last-saved')} · {slot('source')}</p>"
        + f"<p>URL {slot('link')}</p>"
        + f"<p>{slot('body')}</p>"
        + '<div class="fog" role="img" aria-label="foggy image, no page content"></div>'
        + copy("Enable the preview for this link?")
        + btn_row("Yes", "No")
        + btn("Fetch preview")
        + "".join(btn(label) for label in ("Get topics", "Share", "Open", "Delete"))
    ),
    [
        "Fog pixels are not specified. The area is empty and contains no page text.",
        "The detail wireframe lists the foggy prompt and Fetch preview as regions of this screen. Both are drawn.",
    ],
    [
        "Yes runs one fetch under the US-79 limits. No, or leaving the prompt, keeps the fog and does not fetch.",
        "A sensitive item is not fetched. Home rows never draw the fog.",
        "Opening the item does not fetch. A stored foggy preview is shown when one is stored.",
    ],
)

add(
    "item-detail-enrichment-failed",
    "Item detail",
    "Enrichment failed",
    "Item",
    ["item-detail", "copy"],
    screen(
        '<div class="title-line"><p class="app-title">'
        + slot("title")
        + "</p>"
        + btn("Edit")
        + "</div>"
        + copy("Page details are not available")
        + "".join(btn(label) for label in ("Get topics", "Share", "Open", "Delete"))
    ),
    ["Where on the detail screen the enrichment sentence sits is not specified. It is placed under the title row."],
    ["The Copy table gives this sentence with no full stop."],
)

add(
    "item-detail-locked",
    "Item detail",
    "Sensitive item locked",
    "Item",
    ["item-detail"],
    screen(""),
    ["The US-37 prompt chrome is not specified. The body, title, and thumbnail are not drawn."],
    [
        "A sensitive item that is locked does not show this body. The US-37 prompt is shown first.",
        "Cancelled unlock shows no body.",
        "FLAG_SECURE applies once the content is shown, not before.",
        "Use PIN is the named action on API 26–29. See the system biometric page.",
    ],
)

add(
    "item-detail-sensitive-open",
    "Item detail",
    "Sensitive item after unlock",
    "Item",
    ["item-detail"],
    screen(
        '<div class="title-line"><p class="app-title">'
        + slot("title")
        + "</p>"
        + btn("Edit")
        + "</div>"
        + f"<p>{slot('type')} · {slot('last-saved')} · {slot('source')}</p>"
        + f"<p>{slot('body')}</p>"
        + f"<p>personal note, dated YYYY-MM-DD {slot('note text and date')}</p>"
        + detail_actions
    ),
    ["Whether Get topics is hidden after unlock is not specified. It is drawn. A sensitive item is never sent."],
    [
        "FLAG_SECURE applies once the content is shown. This HTML page cannot set that flag.",
        "Lists show the title after one US-37 unlock in the session. Backgrounding returns rows to the neutral form.",
    ],
)

add(
    "edit-item",
    "Edit item",
    "Fields",
    "Item",
    ["edit-item"],
    screen(
        text_field("Title")
        + area_field("Body")
        + area_field("Why it matters")
        + text_field("Tags")
        + f"<p>Sensitive {slot('control')}</p>"
        + f"<p>Reminder or expiry {slot('date control')}</p>"
        + btn("Save")
    ),
    [
        "No screen title is specified.",
        "The Sensitive control shape is not specified.",
        "The date control is not specified. One reminder or one expiry is specified. No return-by or warranty field is drawn.",
    ],
    [
        "Title cannot be saved empty. Why it matters may stay empty.",
        "Save writes title, body, note, or tags. Created date and last-saved stay. updatedAt moves.",
        "Default time for a date is 09:00 in the phone's current local zone.",
    ],
)

add(
    "edit-item-title-required",
    "Edit item",
    "Empty title refused",
    "Item",
    ["edit-item", "copy"],
    screen(text_field("Title") + copy("A title is required.") + btn("Save")),
    ["What the title field shows after the refusal is not specified. The stored title stays."],
    ["Clearing the title is refused. The KDF is not involved. The stored title stays."],
)

add(
    "edit-item-already-sent",
    "Edit item",
    "Marked sensitive later",
    "Item",
    ["edit-item", "copy"],
    screen(
        f"<p>Sensitive {slot('control')}</p>" + copy("Content already sent cannot be recalled.")
    ),
    ["When the sentence appears, besides marking the item sensitive later, is not specified."],
    ["The Copy table gives this sentence for an item marked sensitive later."],
)

add(
    "search",
    "Search",
    "Match and locked sensitive row",
    "Item",
    ["search"],
    screen(
        text_field("query")
        + '<article class="row"><p>'
        + slot("highlighted title")
        + "</p><p>"
        + slot("snippet")
        + " <mark>match</mark></p></article>"
        + sensitive_row()
    ),
    [
        "No screen title is specified.",
        "The query text, snippet, and highlight style are not specified. The mark is underlined and bold so it is not colour alone.",
        "A locked sensitive item is returned only for a tag word, with no snippet and no highlight.",
    ],
    [
        "Order is the US-50 weights: title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. Those weights are not drawn as numbers.",
    ],
)

add(
    "search-empty",
    "Search",
    "Miss",
    "Item",
    ["search"],
    screen(text_field("query")),
    ["No empty-search sentence is specified. No error is drawn."],
    ["A miss shows no rows."],
)

# --- Add ---

add(
    "quick-add",
    "Quick-add row",
    "With tags",
    "Add",
    ["quick-add-row"],
    screen(
        copy("Saved to Lagoon")
        + f'<p><span class="chip">{slot("tag")}</span><span class="chip">{slot("tag")}</span> … up to 8, newest</p>'
        + btn("Add note")
        + btn("Add date")
    ),
    [
        "Tag names are not specified. The wireframe shows up to 8, newest.",
        "What else is on screen behind the row is not specified. It is not drawn as a sheet.",
        "The Add date control, after the row, is not specified. Add date sets the one reminder or the one expiry.",
    ],
    [
        "Shown after the save, not before it. Not a sheet he must finish.",
        "Back, or a tap outside, closes the row and keeps the item. No Back button is named, so none is drawn.",
        "Skipping Add date leaves no date.",
        "No chips when he has never used a tag. That state is a separate page.",
    ],
)

add(
    "quick-add-no-tags",
    "Quick-add row",
    "No tags yet",
    "Add",
    ["quick-add-row"],
    screen(copy("Saved to Lagoon") + btn("Add note") + btn("Add date")),
    ["What else is on screen behind the row is not specified."],
    ["No chips when he has never used a tag."],
)

add(
    "add-note",
    "Add a note",
    "Fields",
    "Add",
    ["add-a-note"],
    screen(text_field("Title") + area_field("Body") + area_field("Why it matters") + btn("Save")),
    ["No screen title is specified."],
    [
        "Save with a body shows Saved to Lagoon, then the quick-add row.",
        "An empty title uses the first line, at most 120 characters.",
    ],
)

add(
    "add-note-not-saved",
    "Add a note",
    "Empty body",
    "Add",
    ["add-a-note", "copy"],
    screen(area_field("Body") + copy("The note was not saved.") + btn("Save")),
    ["Where the sentence sits is not specified."],
    ["An empty body does not save. Nothing is stored."],
)

add(
    "add-url",
    "Add a URL",
    "Fields",
    "Add",
    ["add-a-url"],
    screen(text_field("URL") + text_field("Title (optional)") + btn("Save")),
    ["No screen title is specified."],
    [
        "The URL is kept even when enrichment has not run.",
        "An empty title follows US-40.",
        "No sentence is specified for an empty URL. None is drawn.",
    ],
)

add(
    "which-item",
    "Which-item picker",
    "Live matches",
    "Add",
    ["which-item-picker"],
    screen(
        copy("Which item?")
        + f"<p>{slot('title')} · {slot('type')}</p>"
        + sensitive_row()
        + btn("dismiss")
    ),
    [
        "The Copy table does not give a separate capitalisation for dismiss. The wireframe writes dismiss.",
        "A sensitive row is neutral until unlock, then shows the title.",
    ],
    [
        "Live matches, when any exist, are the only rows, and there is no restore offer.",
        "Dismiss saves the share as a new item.",
        "Two or more live matches open this picker.",
    ],
)

add(
    "which-item-trash",
    "Which-item picker",
    "Every match is in trash",
    "Add",
    ["which-item-picker"],
    screen(
        copy("Which item?")
        + f"<p>{slot('title')} · {slot('type')} {inline_btn('Restore')}</p>"
        + sensitive_row()
        + btn("dismiss")
    ),
    [
        "The picker does not quote a restore label. The drawn word is the Trash action Restore.",
        "Which row is the chosen row is the one he taps. The sample shows Restore on one row.",
        "A separate dismiss outcome for the trash-only picker is not specified. dismiss is in the wireframe, so it is drawn.",
    ],
    [
        "When every match is in trash, the rows are the trashed items and the chosen one gets the restore offer.",
        "The file also says no screen uses Restore. See the index.",
    ],
)

# --- Settings ---

add(
    "settings",
    "Settings",
    "Sync is not configured",
    "Settings",
    ["settings"],
    settings_screen("Sync is not configured."),
    [
        "Accepted is drawn as the wireframe line Accepted: yes or no.",
        "Notifications state text is not specified.",
        "A sample count for this month's topic calls is not specified. The file uses N.",
        "In-phone theme radios are a picture of the fresh install, with System selected. They do not operate. The preview radios above the phone change the palette.",
        "Whether Sign in is hidden when signed in is not specified. The wireframe lists Sign in and Sign out, so both are drawn.",
        "Export zip and Import zip have no further screen specified.",
    ],
    [
        "One Settings screen. No second settings screen is drawn.",
        "Dynamic color is off. The toggle is shown because this state is not API 30 or below.",
        "Dynamic color does not choose light or dark.",
        "The topic key sentence is shown because this state has no topic key stored.",
        "The hardware warning is absent because this state is not the software-key case.",
        "Absent, and not drawn: Back up now, backup Restore, last-backup time, Check my passphrase, a periodic passphrase reminder, a telemetry off switch, a per-item topics opt-out, and a strength meter.",
    ],
)

for slug, state, sync in (
    ("settings-signed-in", "Signed in", "Signed in"),
    (
        "settings-not-signed-in",
        "Not signed in",
        "Sync is not signed in. Items on this phone are still here.",
    ),
    (
        "settings-revoked",
        "Sign-in revoked",
        "Sign-in was revoked. Items on this phone are still here.",
    ),
    (
        "settings-drive-full",
        "Drive full",
        "Drive is full. The item on this phone is still here.",
    ),
    (
        "settings-upload-failed",
        "Upload failed",
        "The upload failed. The item on this phone is still here.",
    ),
):
    add(
        slug,
        "Settings",
        state,
        "Settings",
        ["settings", "copy"],
        settings_screen(sync),
        ["Only the sync sentence differs from the Sync is not configured page."],
        ["Local save still works. The app does not crash when sync is not configured."],
    )

add(
    "settings-sync-paused",
    "Settings",
    "Sync-paused banner in the sync area",
    "Settings",
    ["settings", "sync-paused-banner"],
    settings_screen("Signed in", banner=True),
    ["The banner is drawn with the Signed in sentence. The file does not tie the banner to one sync sentence."],
    ["The banner is in the sync area of Settings and on the home list, and nowhere else."],
)

add(
    "settings-api-30",
    "Settings",
    "Dynamic color hidden",
    "Settings",
    ["settings"],
    settings_screen("Sync is not configured.", dynamic=False),
    ["This is the API 30 and below state. The dynamic-color row is omitted. No other difference is specified."],
    ["API 30 and below do not show the dynamic-color toggle."],
)

add(
    "settings-hardware-warning",
    "Settings",
    "Key is not hardware-backed",
    "Settings",
    ["settings"],
    settings_screen("Sync is not configured.", hardware=True),
    [],
    [
        "The hardware warning, when shown, is the specification sentence. It is not a second accept gate.",
        "It stays in Settings.",
    ],
)

add(
    "settings-accepted-yes",
    "Settings",
    "Text recognition accepted",
    "Settings",
    ["settings", "ml-kit-disclosure"],
    settings_screen("Sync is not configured.", accepted="yes"),
    ["The wireframe writes Accepted: yes or no. This page shows the yes value."],
    ["Settings shows whether he has accepted, and Withdraw acceptance."],
)

add(
    "settings-accepted-no",
    "Settings",
    "Text recognition not accepted",
    "Settings",
    ["settings", "ml-kit-disclosure"],
    settings_screen("Sync is not configured.", accepted="no"),
    ["The wireframe writes Accepted: yes or no. This page shows the no value."],
    ["Decline, or leaving the disclosure unanswered, does not start ML Kit. There is no telemetry off switch."],
)

add(
    "settings-topic-key-stored",
    "Settings",
    "Topic key stored on this phone",
    "Settings",
    ["settings"],
    settings_screen("Sync is not configured.", topic_key=False),
    [],
    ["The topic key sentence is shown when this phone has no topic key stored. It is omitted here."],
)

add(
    "drive-delete-confirm",
    "Settings",
    "Disconnect and delete confirmation",
    "Settings",
    ["settings", "flows"],
    screen(btn("Disconnect and delete Lagoon data from Drive") + slot("confirmation")),
    [
        "The confirmation sentences are not specified.",
        "The file says the delete warns that it lasts only if every other phone is signed out first, because forgotten-installs files are removed too. Those words are the requirement, not a quoted string, so they are not drawn as the dialog.",
    ],
    [
        "The delete action is separate from Sign out, asks him to confirm, and requires the US-37 prompt even when app lock is off.",
        "Sign out itself has no confirm sentence specified. No sign-out dialog is drawn.",
    ],
)

# --- Trash and forget ---

add(
    "trash",
    "Trash",
    "Rows",
    "Trash and forget",
    ["trash"],
    screen(
        app_title("Trash")
        + f"<p>{slot('type')} · {slot('title')}</p>"
        + f"<p>N days left {inline_btn('Restore')}</p>"
        + "<p>Sensitive</p>"
        + f"<p>{slot('type')} · Sensitive {inline_btn('Restore')}</p>"
    ),
    ["Days left use the proposed words N days left. A sample number is not specified."],
    [
        "Opened from Settings.",
        "Days left are whole days until 30 × 24 hours after trashedAt, rounded up, so the last day shows 1.",
        "Restore is the only action on the row.",
        "A sensitive row is neutral until unlock. After unlock it shows the title.",
        "Screenshots of Trash are blocked while it is shown. This HTML page cannot block them.",
        "No Delete forever. No Delete now. No Empty trash.",
        "The same file says no screen uses Restore. This page follows the Trash wireframe and the Copy table row.",
    ],
)

add(
    "trash-after-deadline",
    "Trash",
    "After the deadline, before removal",
    "Trash and forget",
    ["trash"],
    screen(
        app_title("Trash")
        + f"<p>{slot('type')} · {slot('title')}</p>"
        + f"<p>{inline_btn('Restore')}</p>"
    ),
    [],
    ["The row shows no day count and does not show 0. After removal, the row is gone."],
)

add(
    "trash-read-only",
    "Trash",
    "Read-only open",
    "Trash and forget",
    ["trash", "item-detail"],
    screen(
        slot("title")
        + f"<p>{slot('type')} · {slot('last-saved')} · {slot('source')}</p>"
        + f"<p>{slot('body')}</p>"
        + f"<p>personal note, dated YYYY-MM-DD {slot('note text and date')}</p>"
        + f'<div class="attachment">{slot("image or PDF page")}</div>'
        + btn("Share")
        + btn("Open")
    ),
    ["No screen title for the opened row is specified."],
    [
        "The same fields as detail, with no Edit, no Delete, and no Get topics.",
        "Screenshots of Trash are blocked while it is shown.",
    ],
)

add(
    "forget-this-phone",
    "Forget this phone",
    "Other installs",
    "Trash and forget",
    ["forget-this-phone"],
    screen(
        app_title("Forget this phone")
        + f"<p>{slot('device name')}</p>"
        + f"<p>{slot('model')} · {slot('last check-in')}</p>"
        + f"<p>{slot('device name')}</p>"
        + copy("will be forgotten at its next sync")
        + f'<button type="button" class="btn">Confirm {slot("device name")}</button>'
        + btn("Cancel")
    ),
    ["Device name, model, and last check-in values are not specified. Confirm names the chosen install."],
    [
        "This install is not listed.",
        "The list is shown only after the US-37 unlock.",
        "Cancel changes nothing.",
        "A failed records delete stays pending and uses the sentence will be forgotten at its next sync.",
    ],
)

add(
    "forget-this-phone-empty",
    "Forget this phone",
    "No other phone",
    "Trash and forget",
    ["forget-this-phone"],
    screen(app_title("Forget this phone") + copy("There is no other phone to forget.")),
    [],
    ["The list is shown only after the US-37 unlock. Cancelled unlock changes nothing."],
)

add(
    "notice-nothing-forgotten",
    "Forget this phone",
    "Failed before delete",
    "Trash and forget",
    ["forget-this-phone", "copy"],
    screen(copy("Nothing was forgotten.")),
    ["The host screen for this sentence is not specified."],
    ["A failed write, read-back, or pre-upload tells him and deletes nothing."],
)

# --- Full screen and PDF ---

add(
    "fullscreen",
    "Full-screen image or PDF page",
    "Open",
    "Full screen",
    ["full-screen-image-or-pdf-page"],
    screen(f'<div class="viewer">{slot("image or PDF page")}</div>' + btn("Close")),
    ["The pixels of the image or page are not specified. No page text is drawn."],
    [
        "Brightness override is 1.0f for this window only. The system brightness setting is unchanged. This HTML page cannot set it.",
        "The screen stays on until Close.",
        "Swipe moves to the next or previous page or image in attachment order and does not wrap. It does not leave this view. This static page does not swipe.",
        "Pinch-zoom. This static page does not zoom.",
        "Nothing is written to disk for the decoded image or the rendered page.",
        "A sensitive item uses FLAG_SECURE here.",
        "A PDF that needs a password to open does not open this view.",
        "An owner-password-only PDF opens as a normal PDF.",
    ],
)

add(
    "pdf-password-protected",
    "Full-screen image or PDF page",
    "Password required to open",
    "Full screen",
    ["full-screen-image-or-pdf-page", "copy"],
    screen(copy("This PDF is password-protected.") + slot("open in another app")),
    ["The control that opens it in another app is not named. No password field is drawn."],
    ["He is told it is password-protected and can open it in another app. No password field."],
)

# --- Passphrase ---

def passphrase_setup(*, hardware: bool = False) -> str:
    inner = (
        app_title("Set a passphrase")
        + slot("strong-passphrase guidance")
        + slot("loss warning")
        + secret_field("Passphrase")
        + btn("Accept")
        + btn("Decline")
    )
    if hardware:
        inner = f"<p>{esc(HARDWARE)}</p>" + inner
    return screen(inner)


add(
    "passphrase-setup",
    "Passphrase setup",
    "First run",
    "Passphrase",
    ["passphrase-setup"],
    passphrase_setup(),
    [
        "Strong-passphrase guidance sentences are not specified (open question 3).",
        "The loss warning's exact sentences are not specified. It states that the key is derived from the passphrase, that forgetting it and losing the device means the content is lost, that uninstall or clear storage destroys the Keystore copy, and that losing the signing key forces a reinstall that destroys that copy too.",
    ],
    [
        "FLAG_SECURE. This HTML page cannot set it.",
        "Password input. The browser field sets autocomplete off. IME_FLAG_NO_PERSONALIZED_LEARNING is an Android flag and is not applied here.",
        "No strength meter. No Check my passphrase.",
        "12 to 128 Unicode code points after NFC. The bundled common-password list is refused. Under the minimum, over the maximum, or on the common list: refused, and the KDF does not run. No sentence is specified for that refusal.",
        "Wrong passphrase: nothing changes, and the next try waits 2, 4, 8, 16, then 32 seconds.",
    ],
)

add(
    "passphrase-setup-hardware",
    "Passphrase setup",
    "Key will not be hardware-backed",
    "Passphrase",
    ["passphrase-setup"],
    passphrase_setup(hardware=True),
    ["Guidance and loss-warning sentences are not specified. See Passphrase setup."],
    [
        "The specification warning is on this screen before any vault bytes are written.",
        "Decline deletes the software Keystore entry and writes no vault.",
        "Declining the hardware warning creates no vault.",
    ],
)

add(
    "passphrase-setup-low-memory",
    "Passphrase setup",
    "Cannot allocate 65536 KiB",
    "Passphrase",
    ["passphrase-setup"],
    screen(app_title("Set a passphrase") + slot("why")),
    [
        "The sentence that tells him why is not specified.",
        "Whether the passphrase form is still visible is not specified. Accept is not drawn, because this screen does not create a vault.",
    ],
    ["The parameters are not lowered."],
)

add(
    "passphrase-decline",
    "Passphrase setup",
    "After Decline",
    "Passphrase",
    ["passphrase-setup"],
    screen(slot("decline message")),
    ["The decline sentence is not specified."],
    ["Decline tells him nothing was saved when a share was waiting, and writes nothing."],
)

add(
    "change-passphrase",
    "Change passphrase",
    "Normal change",
    "Passphrase",
    ["change-passphrase"],
    screen(
        app_title("Change passphrase")
        + secret_field("Current passphrase")
        + secret_field("New passphrase")
        + secret_field("New passphrase again")
        + btn("Save")
    ),
    [],
    [
        "A normal change asks for the current passphrase.",
        "FLAG_SECURE is specified for the passphrase screen.",
        "There is no 24-hour wait on this path.",
    ],
)

add(
    "change-passphrase-saved",
    "Change passphrase",
    "After save",
    "Passphrase",
    ["change-passphrase"],
    screen(
        app_title("Change passphrase")
        + slot("exported zip sentence")
    ),
    [
        "The exact sentence is not specified. He is told that zips he already exported still open only with the old passphrase."
    ],
    ["There is no 24-hour wait on this path."],
)

add(
    "passphrase-wrong",
    "Passphrase",
    "Wrong passphrase",
    "Passphrase",
    ["copy", "passphrase-setup"],
    screen(secret_field("Passphrase") + copy("Wait, then try again.")),
    ["The host screen is not named beyond a wrong passphrase. No countdown is specified, so none is drawn."],
    ["Nothing changes. The next try waits 2, 4, 8, 16, then 32 seconds."],
)

add(
    "forgotten-passphrase-reset",
    "Forgotten-passphrase reset",
    "This phone still holds the key",
    "Passphrase",
    ["forgotten-passphrase-reset"],
    screen(
        slot("screen title")
        + secret_field("New passphrase")
        + secret_field("New passphrase again")
        + slot("button")
    ),
    [
        "The reset section does not name a screen title or a button.",
        "New passphrase and New passphrase again are the Change passphrase labels. The reset section says the new passphrase twice and does not repeat the labels.",
    ],
    [
        "US-37 first, then the new passphrase twice, with no current-passphrase field.",
        "On a phone that does not hold the key, this reset is not offered.",
    ],
)

add(
    "passphrase-reset-waiting",
    "Forgotten-passphrase reset",
    "Another phone holds the key",
    "Passphrase",
    ["forgotten-passphrase-reset"],
    screen(copy("A passphrase reset is waiting.") + btn("Cancel reset")),
    ["The host screen of the in-app banner is not named."],
    [
        "The same words are the system notification. No passphrase. No item title. No item body.",
        "Cancel leaves Drive on the old passphrase.",
        "The Drive copy stays on the old passphrase for 24 hours.",
        "One phone, and no other install holds the key: no notice.",
        "Items saved on the resetting phone during the wait are kept.",
    ],
    below=(
        '<aside class="system-card"><h2>System notification</h2>'
        "<p>A passphrase reset is waiting.</p>"
        f"<p>{slot('notification chrome')}</p></aside>"
    ),
)

add(
    "returning-phone",
    "Returning forgotten phone",
    "Passphrase before discard",
    "Passphrase",
    ["flows"],
    screen(slot("heading") + secret_field("Passphrase") + slot("confirm control")),
    [
        "The prompt's heading and confirm control are not named.",
        "The lost-edits sentence is specified separately and is shown only if something was lost.",
    ],
    [
        "He is asked for the passphrase before anything local is discarded.",
        "On accept, the phone rebuilds from Drive, rejoins with a new install id, and shows the lost-edits sentence only if something was lost.",
        "Cancel or a wrong passphrase discards nothing and does not upload. The prompt returns at the next sync.",
    ],
)

# --- ML Kit and topics ---

add(
    "ml-kit-disclosure",
    "ML Kit disclosure",
    "Before the first OCR use",
    "Topics",
    ["ml-kit-disclosure"],
    screen(app_title("Text recognition") + f"<p>{esc(DISCLOSURE)}</p>" + btn_row("Accept", "Decline")),
    [],
    [
        "Shown before the first OCR use, and again after he withdraws acceptance.",
        "Leaving the dialog without Accept or Decline is unanswered: OCR does not run.",
        "There is no off switch for the metrics.",
        "Settings shows the same sentences.",
    ],
)

add(
    "topic-list",
    "Topic list",
    "A topic from a suggestion",
    "Topics",
    ["topic-list"],
    screen(app_title("Topics") + f"<p>{slot('name')} · N items · AI-generated</p>"),
    ["The topic name and the count are not specified. The file uses N."],
    ["The AI-generated mark is on a topic created from a suggestion."],
)

add(
    "topic-list-empty",
    "Topic list",
    "Empty",
    "Topics",
    ["topic-list"],
    screen(app_title("Topics") + copy("There are no topics.")),
    [],
    ["This empty sentence is not the failure sentence No topics."],
)

add(
    "pending-suggestions",
    "Pending suggestions",
    "One suggestion",
    "Topics",
    ["pending-suggestions"],
    screen(
        app_title("Pending suggestions")
        + f"<p>{slot('name')}</p>"
        + f"<p>{slot('quote')}</p>"
        + btn_row("Confirm", "Decline")
    ),
    ["Rename and merge are named as rules and are not drawn. No control labels for them are specified."],
    [
        "A place next to the Inbox, not a prompt on every save.",
        "Confirm, decline, rename, and merge keep the rules in US-43 and US-46.",
        "Declining does not delete the item.",
    ],
)

add(
    "topic-detail",
    "Topic detail",
    "Linked item",
    "Topics",
    ["topic-detail"],
    screen(f"<p>{slot('name')}</p><p>{slot('item title')}</p><p>{slot('note')} · {slot('date')} · AI</p>"),
    ["Sample name, title, note, and date are not specified. AI is the wireframe mark."],
    [],
)

add(
    "topic-detail-empty",
    "Topic detail",
    "No links",
    "Topics",
    ["topic-detail"],
    screen(f"<p>{slot('name')}</p>" + copy("Nothing is linked.")),
    [],
    ["Empty of links: the name, and Nothing is linked."],
)

add(
    "topic-detail-sensitive-hidden",
    "Topic detail",
    "Locked sensitive link",
    "Topics",
    ["topic-detail"],
    screen(f"<p>{slot('name')}</p>"),
    ["How the hidden link is represented, besides not showing the note and quote, is not specified. No title, note, or quote is drawn."],
    ["Notes and quotes from a locked sensitive item stay hidden. A sensitive title is not shown in a row."],
)

add(
    "notice-try-again",
    "Topic failure",
    "Try again",
    "Topics",
    ["copy"],
    screen(btn("Try again")),
    ["Any sentence around the action, other than the empty-result sentence, is not specified."],
    ["Failure keeps the item and shows Try again, unless the credential is blocked. No sentence is specified for the blocked credential."],
)

add(
    "notice-no-topics",
    "Topic failure",
    "Empty result",
    "Topics",
    ["copy", "topic-list"],
    screen(copy("No topics")),
    [],
    ["An empty result says no topics, not failed. This is not the topic-list sentence There are no topics."],
)

add(
    "notice-send-earlier",
    "First topic key",
    "Ask once",
    "Topics",
    ["copy"],
    screen(copy("Send N earlier items for topics?") + btn("No") + slot("other answer")),
    ["The affirmative control is not named. The default answer is No, so No is drawn. Yes is not added."],
    [
        "N is the number of earlier ordinary items on this install that have no topic provenance.",
        "A sensitive item is not counted and is not sent.",
        "Asked once, when the key is first added.",
    ],
)

# --- Upcoming and inbox ---

add(
    "upcoming",
    "Upcoming",
    "Future, expired, and sensitive",
    "Upcoming and inbox",
    ["upcoming"],
    screen(
        app_title("Upcoming")
        + f"<p>{slot('title')} · {slot('date')}</p>"
        + "<p>Expired</p>"
        + f"<p>{slot('title')} · expired</p>"
        + "<p>Sensitive</p>"
        + btn_row("Done", "Snooze")
    ),
    ["Done and Snooze are named in the Copy table and are not placed in the wireframe. They are drawn under the rows."],
    [
        "Future dates are soonest first. Passed dates are in Expired, most recent first.",
        "A locked sensitive row uses Sensitive, not the title.",
        "This screen exists when US-62 is built. US-62 is Should.",
        "Snooze is 1 hour. Done stops further notices for that expiry.",
    ],
)

add(
    "inbox",
    "Inbox",
    "Untagged rows",
    "Upcoming and inbox",
    ["inbox"],
    screen(app_title("Inbox") + ordinary_row() + sensitive_row()),
    ["How pending suggestions are reached from here is not specified. No control is drawn."],
    [
        "Untagged is a view, not a stored Inbox tag.",
        "The rows are the same as Home.",
        "US-71 batch filing is Could and is not drawn.",
    ],
)

# --- Notices ---

add(
    "notice-undo",
    "Undo",
    "After delete",
    "Notices",
    ["notices-that-are-not-screens"],
    screen(app_title("Lagoon") + ordinary_row(), footer=slot("snackbar text") + btn("Undo")),
    [
        "The snackbar message, other than the Undo action, is not specified.",
        "The timeout length is not specified (open question 4). No duration is drawn.",
        "Snackbar offset is not specified. It is drawn at the bottom of the frame.",
    ],
    [
        "After delete, until he dismisses it, leaves that screen, or it times out.",
        "The row leaves the home list. The deleted title is not specified for the snackbar, so it is not drawn.",
        "Undo restores text, attachments, search, reminders, topic links, and the queued topic job.",
    ],
)

add(
    "notice-repeat-link",
    "Repeat link",
    "Snackbar",
    "Notices",
    ["notices-that-are-not-screens"],
    screen(
        "",
        footer=copy("Saved before, added to existing") + btn_row("Undo", "Keep separate"),
    ),
    ["The host screen behind the snackbar is not specified."],
    [
        "Accepting updates the existing item: tags unioned, new note appended under YYYY-MM-DD, text not overwritten.",
        "Undo returns the previous item. Keep separate stores a new item.",
    ],
)

add(
    "notice-dropped-edit",
    "Dropped edit",
    "Before unlock",
    "Notices",
    ["notices-that-are-not-screens", "copy"],
    screen(copy("That edit couldn't be kept.")),
    ["The host screen is not named. It is in-app, not a system notification, so it is not drawn as a snackbar."],
    ["No title is shown before unlock. Not after a restart. Not for OCR, a preview, or another system write."],
)

add(
    "notice-dropped-edit-title",
    "Dropped edit",
    "After unlock",
    "Notices",
    ["notices-that-are-not-screens", "copy"],
    screen(copy("That edit couldn't be kept.") + slot("item title")),
    ["The title value is not specified. It is on a second line."],
    ["Ordinary, or sensitive after unlock: the stem, then the title on a second line."],
)

add(
    "notice-files-removed",
    "Orphan files removed",
    "After rebuild",
    "Notices",
    ["notices-that-are-not-screens", "copy"],
    screen(copy("Files on this phone that are not part of a saved item were removed.")),
    ["The host screen is not named."],
    ["Shown only when attachment files were actually deleted. Neutral. No item title."],
)

add(
    "notice-edits-lost",
    "Unsynced edits lost",
    "After rebuild",
    "Notices",
    ["notices-that-are-not-screens", "copy"],
    screen(copy("Edits on this phone that had not synced were not kept.")),
    ["The host screen is not named."],
    ["Shown only when something was actually lost."],
)

add(
    "notice-reminder",
    "Ordinary reminder",
    "System notification",
    "Notices",
    ["notices-that-are-not-screens"],
    screen(slot("title and codes")),
    ["Notification chrome is not specified. Title and codes come from the item title. No sample title is specified."],
    ["There is no separate code field. A code is text already in the title."],
    frame="system",
)

add(
    "notice-reminder-sensitive",
    "Sensitive reminder or expiry",
    "System notification",
    "Notices",
    ["notices-that-are-not-screens"],
    screen(copy("Lagoon reminder")),
    ["Notification chrome is not specified."],
    ["Public and private text are only Lagoon reminder, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon."],
    frame="system",
)

# --- System surfaces ---

def system_blank(note_name: str) -> str:
    return screen(slot(note_name))


add(
    "system-share",
    "Share in",
    "Android share sheet",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens", "flows"],
    system_blank("share sheet"),
    ["The share sheet is a system surface. Its chrome is not specified and is not drawn."],
    [
        "He chooses Lagoon. The item is stored in one tap. He sees Saved to Lagoon. The quick-add row follows.",
        "A non-PDF sent as a PDF is rejected. No sentence is specified.",
        "A file:// URI or Lagoon's own FileProvider URI is rejected. No sentence is specified.",
        "One image over 20 MiB is refused. No sentence is specified.",
        "Empty plain text stores nothing. No sentence is specified.",
    ],
    frame="system",
)

add(
    "system-sign-in",
    "Google sign-in",
    "System sheet",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens"],
    system_blank("Google sign-in"),
    ["The sign-in sheet and the drive.appdata grant UI are not specified and are not drawn."],
    ["Signed-in state is a sentence on Settings."],
    frame="system",
)

add(
    "system-biometric",
    "Biometric prompt",
    "US-37",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens"],
    screen(slot("prompt chrome") + btn("Use PIN")),
    ["Prompt chrome besides Use PIN is not specified. Use PIN is named for API 26–29."],
    ["Cancelled unlock shows no body and changes nothing on Forget this phone."],
    frame="system",
)

add(
    "system-camera",
    "System camera",
    "Capture",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens", "flows"],
    system_blank("system camera"),
    ["The camera UI is not specified and is not drawn."],
    [
        "System camera via ACTION_IMAGE_CAPTURE. Confirm stores one image item. Lagoon does not request CAMERA.",
        "Cancel, no camera app, or an empty file stores nothing and deletes the leftover file. No sentence is specified.",
    ],
    frame="system",
)

add(
    "system-viewer",
    "Link viewer",
    "Custom Tabs or ACTION_VIEW",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens"],
    system_blank("viewer"),
    ["Custom Tabs and the external viewer are not specified and are not drawn."],
    ["Open uses Custom Tabs or ACTION_VIEW, http or https only. No WebView."],
    frame="system",
)

add(
    "launcher",
    "Launcher",
    "Name and icon",
    "System surfaces",
    ["system-surfaces-that-are-not-lagoon-screens"],
    screen(f'<div class="launcher"><div class="icon-slot">{slot("icon")}</div><p>Lagoon</p></div>'),
    ["The icon drawing is not specified (open question 7)."],
    ["The short name is Lagoon. The icon is made for this app. The drawing is not in the stories."],
    frame="system",
)

add(
    "clock-not-designed",
    "Clock warning",
    "Not designed",
    "Not designed",
    ["notices-that-are-not-screens", "open-questions"],
    screen(slot("warning")),
    ["Threshold and text are not decided. No warning sentence is invented. The dashed label is the gap, not a warning."],
    ["The file says this warning is not designed."],
)

GLOBAL_GAPS = [
    "Navigation among Home, Search, Upcoming, Inbox, Topics, pending suggestions, and Settings is not specified. No bottom bar, drawer, or tab strip is drawn (open question 1).",
    "Settings order follows the wireframe. The file marks that order as open question 2.",
    "Strong-passphrase guidance sentences are not specified (open question 3).",
    "Undo snackbar duration is not specified (open question 4).",
    "Fog image pixels are not specified (open question 5).",
    "On-accent text when dynamic color replaces teal is not specified (open question 6). Dynamic color is off here. Teal is the accent.",
    "Launcher icon art is not specified (open question 7).",
    "No typeface is specified (open question 8). Pages use the browser system-ui font.",
    "The clock warning threshold and text are not specified (open question 9).",
    "Surface, on-surface, and outline colours are not specified. Neutral placeholders are used. Light accent is #0F766E. Dark accent is #2DD4BF. #0F766E is not used on a dark surface.",
    "Text colour on #0F766E is not specified. Light-palette buttons use white. Text on #2DD4BF is #003731. The file also allows black. White on #2DD4BF is not used.",
    "Corner radius, elevation, spacing, and touch-target size are not specified.",
    "The system status bar and the system navigation bar are not specified and are not drawn.",
    "Sample titles, bodies, notes, tag names, dates, sources, URLs, device names, models, check-in times, topic names, quotes, and numeric counts are not specified. Counts the file writes as N stay N. Personal notes use the format token YYYY-MM-DD.",
    "Display words for note, link, image, and PDF are not specified. Rows use the wireframe word type.",
    "Which actions are filled buttons is not specified. Named actions are buttons.",
    "The file says Trash uses Restore, and it also says no screen uses Restore. Restore is drawn only on Trash and on the trash which-item picker. Backup Restore is not drawn.",
    "Proposed Copy-table sentences can be replaced without changing the story. They are drawn as written and are not marked approved.",
]


def github_anchor(heading: str) -> str:
    text = heading.strip().lower()
    text = re.sub(r"[^\w\s-]", "", text)
    text = text.replace("_", "")
    text = re.sub(r"\s+", "-", text)
    return text


def heading_anchors() -> dict[str, str]:
    found: dict[str, str] = {}
    for line in UX_TEXT.splitlines():
        match = re.match(r"^#{1,6} (.+)$", line)
        if match:
            found[github_anchor(match.group(1))] = match.group(1)
    return found


ANCHORS = heading_anchors()


def section_link(anchor: str) -> str:
    if anchor not in ANCHORS:
        raise SystemExit(f"Missing heading anchor #{anchor}")
    url = (
        "https://github.com/mjbvilhena/lagoon/blob/"
        f"{UX_COMMIT}/{UX_REL}#{anchor}"
    )
    return f'<a href="{esc(url)}">{esc(ANCHORS[anchor])}</a>'


def page_html(page: dict) -> str:
    links = " · ".join(section_link(anchor) for anchor in page["anchors"])
    preview = """
<fieldset class="preview">
  <legend>Preview theme</legend>
  <label><input type="radio" name="preview" id="preview-system" checked> System</label>
  <label><input type="radio" name="preview" id="preview-light"> Light</label>
  <label><input type="radio" name="preview" id="preview-dark"> Dark</label>
</fieldset>
<p class="fine">System is the default. This preview follows the browser when System is selected. On the phone, System follows Android. These radios are review controls. They are not a second Settings screen.</p>
<p class="fine">Dashed labels inside the frame are review annotations, not product copy. Empty optional fields are omitted in the product. An annotation marks a sample value or a control the UX file does not give.</p>
"""
    frame_note = ""
    if page["frame"] == "system":
        frame_note = "<p class=\"fine\">The frame is a system surface, not a Lagoon screen.</p>"
    stage = ""
    if page["screen"]:
        stage = f'<div class="stage"><div class="device">{page["screen"]}</div></div>'
    gap_items = "".join(f"<li>{esc(gap)}</li>" for gap in page["gaps"])
    note_items = "".join(f"<li>{esc(note)}</li>" for note in page["notes"])
    gaps_html = f"<h2>Not specified in ux-design.md</h2><ul>{gap_items}</ul>" if gap_items else ""
    notes_html = f"<h2>Specified for this state</h2><ul>{note_items}</ul>" if note_items else ""
    state = f"<p>{esc(page['state'])}</p>" if page["state"] else ""
    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>{esc(page['title'])} — Lagoon review aid</title>
<link rel="stylesheet" href="mockups.css">
</head>
<body>
<header class="review-banner">
<p>{esc(BANNER)}</p>
<p>Built from ux-design.md commit <code>{UX_COMMIT}</code>.</p>
</header>
<main class="wrap">
<p><a href="index.html">All screens</a></p>
<h1>{esc(page['title'])}</h1>
{state}
<p>UX design section: {links}</p>
{preview}
{frame_note}
{stage}
{page['below']}
{gaps_html}
{notes_html}
<p class="fine">Gaps shared by every page are listed on <a href="index.html#gaps">the index</a>.</p>
</main>
</body>
</html>
"""


def index_html() -> str:
    groups: dict[str, list[dict]] = {}
    for page in PAGES:
        groups.setdefault(page["group"], []).append(page)
    blocks = []
    for group, items in groups.items():
        lis = []
        for page in items:
            state = f" — {esc(page['state'])}" if page["state"] else ""
            lis.append(
                "<li>"
                f'<a href="{esc(page["slug"])}.html">{esc(page["title"])}</a>'
                f"{state} — "
                + " · ".join(section_link(anchor) for anchor in page["anchors"])
                + "</li>"
            )
        blocks.append(f"<h2>{esc(group)}</h2><ul class=\"catalog\">{''.join(lis)}</ul>")
    gap_blocks = [
        "<h2 id=\"gaps\">Not specified in ux-design.md</h2>",
        "<h3>On every page</h3>",
        "<ul>" + "".join(f"<li>{esc(gap)}</li>" for gap in GLOBAL_GAPS) + "</ul>",
    ]
    for page in PAGES:
        if not page["gaps"]:
            continue
        gap_blocks.append(f"<h3>{esc(page['title'])} — {esc(page['state'])}</h3>")
        gap_blocks.append("<ul>" + "".join(f"<li>{esc(gap)}</li>" for gap in page["gaps"]) + "</ul>")
    swatches = """
<h2>Colours from ux-design.md</h2>
<ul>
<li><span class="swatch" style="background:#0F766E"></span>Light accent #0F766E. Not used on a dark surface.</li>
<li><span class="swatch" style="background:#2DD4BF"></span>Dark accent #2DD4BF. Text on it is #003731. White on #2DD4BF is not used.</li>
<li><span class="swatch" style="background:#F4F4F2"></span>Light surface in this preview. Not specified in ux-design.md.</li>
<li><span class="swatch" style="background:#121214"></span>Dark surface in this preview. Not specified in ux-design.md.</li>
</ul>
"""
    return f"""<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Lagoon UX review mockups</title>
<link rel="stylesheet" href="mockups.css">
</head>
<body>
<header class="review-banner">
<p>{esc(BANNER)}</p>
<p>Built from ux-design.md commit <code>{UX_COMMIT}</code>.</p>
</header>
<main class="wrap">
<h1>Lagoon UX review mockups</h1>
<p class="lead">Review aid only. <code>docs/technical_design/ux-design.md</code> is the source of truth. If a mockup and that file disagree, the file wins. The UX design status in that file is Proposed. These pages do not approve it.</p>
<p>File: {section_link("ux-design")}</p>
<fieldset class="preview">
  <legend>Preview theme</legend>
  <label><input type="radio" name="preview" id="preview-system" checked> System</label>
  <label><input type="radio" name="preview" id="preview-light"> Light</label>
  <label><input type="radio" name="preview" id="preview-dark"> Dark</label>
</fieldset>
<p class="fine">System is the default and, in this preview, follows the browser. On the phone it follows Android. Each screen is 360 by 800 CSS pixels inside the frame. No network is required.</p>
{swatches}
<h2>Screens and states</h2>
{''.join(blocks)}
{''.join(gap_blocks)}
</main>
</body>
</html>
"""


def readme() -> str:
    lines = [
        "# UX review mockups",
        "",
        "Review aid only. `docs/technical_design/ux-design.md` is the source of truth. If this mockup and that file disagree, the file wins.",
        "",
        f"These pages were built from `docs/technical_design/ux-design.md` at commit `{UX_COMMIT}`.",
        "",
        "The status of that file is Proposed. These pages are not an approval of it.",
        "",
        "## Rebuild",
        "",
        "1. Read `docs/technical_design/ux-design.md` at the commit above.",
        "2. If you are picturing a newer commit, set `UX_COMMIT` in `docs/mockups/build.py` to that commit and update the page definitions so they still match the file. Do not add copy the file does not contain. Where the file leaves a detail unspecified, keep the label `not specified in ux-design.md`.",
        "3. From the repository root, run `python3 docs/mockups/build.py`.",
        "4. Open `docs/mockups/index.html`.",
        "",
        "The script checks that the working copy of `ux-design.md` matches `UX_COMMIT`, and that every product string it writes is contained in that file. It does not edit `ux-design.md`.",
        "",
        "## Preview",
        "",
        "Open `index.html` in a browser. No server and no network are required. Each screen is 360 by 800 CSS pixels inside a device frame. System, Light, and Dark above the frame preview the palette. System is selected by default.",
        "",
        "## Deploy",
        "",
        "These files are static. There is no build step. `.nojekyll` is in this directory.",
        "",
        "`.github/workflows/pages.yml` uploads `docs/mockups`, including hidden files, so `.nojekyll` is in the site root and GitHub Pages does not run Jekyll. The workflow uses the official actions `actions/checkout`, `actions/configure-pages`, `actions/upload-pages-artifact`, and `actions/deploy-pages`. It does not use a secret or a personal access token.",
        "",
        "A push to `master` that changes `docs/mockups/**` or `.github/workflows/pages.yml` runs the workflow. The file also lists `workflow_dispatch`. GitHub offers that manual run only after `.github/workflows/pages.yml` is on the default branch, `master`.",
        "",
        "The `github-pages` environment allows deployments from `master` only, unless its deployment branch rules are widened. The first deployment happens after this workflow reaches `master`. A run on another branch does not publish the site while that rule stands.",
        "",
        "Pages source is GitHub Actions. `GITHUB_TOKEN` cannot change that setting. The one-time repository setting is **Settings > Pages > Build and deployment > Source: GitHub Actions**.",
        "",
        "The `gh-pages` branch is not deleted by this workflow. With GitHub Actions as the source, that branch is optional.",
        "",
        "## Pages",
        "",
    ]
    for page in PAGES:
        state = f" — {page['state']}" if page["state"] else ""
        anchors = ", ".join(f"#{anchor}" for anchor in page["anchors"])
        lines.append(f"- [{page['title']}]({page['slug']}.html){state} (`{anchors}`)")
    lines.append("")
    return "\n".join(lines)


def main() -> None:
    for old in OUT.glob("*.html"):
        old.unlink()
    for page in PAGES:
        (OUT / f"{page['slug']}.html").write_text(page_html(page), encoding="utf-8")
    (OUT / "index.html").write_text(index_html(), encoding="utf-8")
    (OUT / "README.md").write_text(readme(), encoding="utf-8")
    (OUT / ".nojekyll").write_text("", encoding="utf-8")
    html_files = list(OUT.glob("*.html"))
    for path in html_files:
        text = path.read_text(encoding="utf-8")
        if BANNER not in text or UX_COMMIT not in text:
            raise SystemExit(f"banner missing in {path.name}")
        if "<script" in text.lower():
            raise SystemExit(f"script in {path.name}")
        for match in re.findall(r"https?://[^\"'\s>]+", text):
            allowed = f"https://github.com/mjbvilhena/lagoon/blob/{UX_COMMIT}/{UX_REL}#"
            if not match.startswith(allowed):
                raise SystemExit(f"external URL in {path.name}: {match}")
        if "Approved" in text:
            raise SystemExit(f"approval marker in {path.name}")
    css = (OUT / "mockups.css").read_text(encoding="utf-8")
    dark = css.split(":root:has(#preview-dark:checked)", 1)[1]
    if re.search(r"0f766e", dark, re.I):
        raise SystemExit("dark palette uses #0F766E")
    if "#003731" not in dark:
        raise SystemExit("dark on-accent missing")
    print(f"wrote {len(html_files)} html files")


if __name__ == "__main__":
    main()
