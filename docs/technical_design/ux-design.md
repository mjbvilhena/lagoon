# UX design

**Status: Approved.** Approved by mjbvilhena on 2026-10-31. Do not treat it as approval of a story, an epic, or a specification sentence.

Template shape: `get_sdlc_template("ux design")`. Skill: `sdlc-ux-designer`. Harness commit `398bbcb2eb81e687e7e8e72560c1b7e0de9e2604`.

## Metadata

| Field | Value |
| --- | --- |
| Slice / epic | v1 epics E1–E9 and E11. E10 is later and has no screens here. |
| Stories / AC cited | The 22 approved stories, plus the existing Draft story specs the approved epics already name. Paths are in Evidence. |
| Related spec | `docs/product/vision.md`, `docs/product/specification.md`, `docs/product/epics/E5-look-and-feel.md` |
| Status | Approved by mjbvilhena on 2026-10-31. |
| Skip? | no |

The harness template lists Draft / Review / Agreed. This file is approved. Approved by mjbvilhena on 2026-10-31.

## Evidence

Stories and product claims below were opened. Draft story files are existing specs. Their approval lines still say Draft. This design does not approve them.

Approved stories: US-15, US-31, US-34, US-35, US-40, US-41, US-49, US-50, US-55, US-61, US-62, US-64, US-65, US-66, US-75, US-78, US-79, US-80, US-81, US-85, US-86, US-87. Index: `docs/product/stories/INDEX.md`.

Draft story specs used for screens the approved epics already require: US-01, US-02, US-03, US-04, US-05, US-06, US-07, US-08, US-09, US-10, US-11, US-12, US-13, US-14, US-16, US-17, US-18, US-19, US-20, US-21, US-22, US-23, US-28, US-29, US-30, US-32, US-33, US-37, US-38, US-39, US-42, US-43, US-44, US-45, US-46, US-47, US-48, US-51, US-52, US-53, US-54, US-56, US-58, US-59, US-60, US-77. Could stories (US-67, US-70, US-71, US-72) are out of the screens below unless a flow says otherwise. Placeholders US-73, US-74, and US-76 have no acceptance criteria. Their accepted answers are ADR-0002, ADR-0001, and ADR-0003. This design does not invent criteria for them.

Product claims used: `docs/product/vision.md`, `docs/product/specification.md`, epics E1–E9 and E11. E5 is the look-and-feel source.

Domain and layers fetched with `get_domain_consultant("vault")` and `get_layer_consultant` for `ui`, `domain`, and `data`. MUST and NEVER below are those files, not new rules.

Accepted records: ADR-0001, ADR-0002, ADR-0003, ADR-0004, ADR-0005, ADR-0006, and `docs/technical_design/threat-model.md`.

Definition of Done `ui change` was read. It is implement and verify work. It is not permission to add screens. Keyboard, accessible names, errors on fields, no color-only meaning, and reduced motion are constraints on the screens the stories already name.

No earlier UX artefact exists. Several stories say the exact layout or the exact text waits for this file. Those texts are in this file.

### Appearance tokens

E5 and the specification fix these. This proposal records them and does not adjust them.

| Token | Rule | Source |
| --- | --- | --- |
| Toolkit | Material 3. No second component library. | E5, `docs/layers/ui/LAYER.md` |
| Theme choices | Light, Dark, System. System is the default and follows Android. A choice applies immediately and survives process death. | US-22, specification |
| Light accent | `#0F766E` | specification, US-53, E5 ("about" that value; no other value is proposed) |
| Dark accent | `#2DD4BF` | specification, US-53, E5 |
| Text on the dark accent | Black or `#003731`. White on `#2DD4BF` is not allowed. | E5, US-53 |
| Dark surfaces | Never use `#0F766E` on a dark surface. | specification, E5, US-53 |
| Dynamic color | Optional. Off on a fresh install. Teal is the fallback. The toggle is hidden on API 30 and below. On API 31 or newer, turning it on uses system dynamic colors when the system supplies them, otherwise teal. Dynamic color does not choose light or dark. | US-53, E5, specification |
| Motion | When the system animator duration scale is 0, every action on these screens completes with no motion, and no information is only motion. | US-53, E5, `ui change` DoD |
| Type | Calm, generous whitespace, refined type. The typeface is system-ui. | E5 |
| Clutter | No lorem ipsum and no "TODO" sentences in the shipped UI. | E5, US-53 |
| Launcher | A launcher icon made for this app, and the short name Lagoon. The icon is a teal rounded square and a simple wave. | US-23, vision |

Light uses the light palette. Dark uses the dark palette. That holds when he chose the mode and when System followed the phone. When Dynamic color is on, the light accent is `#6750A4` with white on it, and the dark accent is `#D0BCFF` with black `#000000` on it. Teal stays the accent while Dynamic color is off.

### Layer constraints on every screen

From `docs/layers/ui/LAYER.md` and `docs/domain/vault/DOMAIN.md`:

- A click calls a domain use case. Composables do not encrypt, sync, or query the database.
- `FLAG_SECURE` on the passphrase screen and on a screen that shows unlocked sensitive content. Not app-wide.
- The passphrase field is a password input with `IME_FLAG_NO_PERSONALIZED_LEARNING`.
- Links open with Custom Tabs or `ACTION_VIEW`, `http` or `https` only. No WebView.
- The full-screen page sets `WindowManager.LayoutParams.screenBrightness = 1.0f` and does not request `WRITE_SETTINGS`.
- One Settings screen hosts theme, sync, the reminder default, app lock, topic controls, and the ML Kit disclosure.
- No Back up now, no Restore, no last-backup time, no "Check my passphrase", and no periodic passphrase reminder.
- Proposed amendment. Needs approval by mjbvilhena. Original: "No Back up now, no Restore, no last-backup time, no "Check my passphrase", and no periodic passphrase reminder." Replacement: "No Back up now, no backup Restore, and no last-backup time. Restore appears only on Trash and the trash which-item picker. There is no backup Restore in v1. No "Check my passphrase", and no periodic passphrase reminder."
- The UI does not show a sensitive title, snippet, thumbnail, or code in a row. Notification text is "Lagoon reminder" for a sensitive item. The data layer posts it.
- No analytics events.

## Flows

Actor in every row is Miguel, the only person named in the vision. Draft means the story file is still Draft. Approved means the story file is approved. Epic text for these journeys is already approved.

| Flow | Trigger | Success path | Unhappy path | AC mapping |
| --- | --- | --- | --- | --- |
| Share in | Android share sheet: link, plain text, image, or PDF | 1. He chooses Lagoon. 2. The item is stored in one tap. 3. He sees "Saved to Lagoon". 4. The quick-add row offers last-used tags, Add note, and Add date. 5. Dismissing the row keeps the item. | A non-PDF sent as a PDF is rejected (US-04, Draft). A `file://` URI or Lagoon's own FileProvider URI is rejected (US-01, Draft). One image over 20 MiB is refused. Empty plain text stores nothing. | US-01–US-04 Draft, US-28 Draft, US-32 Draft, US-40 Approved, E1 |
| Repeat link | He shares a URL that matches a saved link after normalisation | Snackbar "Saved before, added to existing", with Undo and "Keep separate". Accepting updates the existing item: tags unioned, new note appended under `YYYY-MM-DD`, text not overwritten. | Undo returns the previous item. "Keep separate" stores a new item. A match that is only in trash offers restore. Two or more live matches open the which-item picker. Sensitive rows in that picker are neutral until unlock. | US-49 Approved, ADR-0002 |
| Add by hand | He adds a note or a URL inside the app | Note: body and optional title, then "Saved to Lagoon". Empty title uses the first line, at most 120 characters. URL: the URL is kept. | Empty note body: nothing stored, "The note was not saved." | US-05 Draft, US-06 Draft, US-40 Approved |
| Camera | He starts a capture | System camera via `ACTION_IMAGE_CAPTURE`. Confirm stores one image item. Lagoon does not request `CAMERA`. | Cancel, no camera app, or an empty file stores nothing and deletes the leftover file. | US-65 Approved |
| Home | He opens the home list | Rows ordered by last-saved, newest first. An ordinary row shows title, type, saved date, source when present, and a reminder or expiry marker when set. No preview image on the row. | Empty vault: "Nothing is saved yet." A trashed item is absent. | US-10 Draft, US-66 Approved |
| Read | He opens a row | Detail shows title, body, note, attachments, last-saved, source, and the original URL for a link. A stored foggy preview is shown and opening the item does not fetch. | Optional fields that are empty are omitted, not filled with placeholder sentences. | US-11 Draft, US-79 Approved |
| Edit | He edits title, body, note, or tags | Save writes those fields. Created date and last-saved stay. `updatedAt` moves. | Clearing the title is refused. The stored title stays. "A title is required." | US-12 Draft |
| Delete and trash | He deletes an item | The row leaves the home list. An undo snackbar offers Undo. When the snackbar is dismissed, he leaves that screen, or it times out, the item is in trash. Trash is opened from Settings. | Undo restores text, attachments, search, reminders, topic links, and the queued topic job. Trash has Restore and a read-only open. No Delete forever, Delete now, or Empty trash. | US-13 Draft, US-66 Approved |
| Search | He searches | Matches are ranked and the match is highlighted. Order is the US-50 weights: title 10, tags 5, personal note 3, body and extracted text 1, OCR text 1. | A miss shows no rows. A locked sensitive item matches a tag word only, as a neutral row, with no snippet and no highlight. | US-15 Approved, US-50 Approved, US-75 Approved |
| Filter | He filters the list | Type, tag, source (US-61), and date saved (US-39). | A filter that matches nothing shows an empty list, not an error. | US-16 Draft, US-39 Draft, US-61 Approved |
| Unsafe preview | A saved link is token-looking, or he chooses "Fetch preview" | Detail shows a foggy placeholder and asks whether to enable the preview. Yes runs one fetch under the US-79 limits. | No, or leaving the prompt, keeps the fog and does not fetch. A sensitive item is not fetched. Home rows never draw the fog. | US-79 Approved, US-08 Draft, US-10 Draft, US-11 Draft |
| Full screen | He taps an image or a PDF page once | Full screen, window brightness `1.0f`, pinch-zoom, screen kept on, offline. Close restores brightness and ends keep-on. Swipe moves to the next or previous page or image in attachment order and does not wrap. | Sensitive and locked: US-37 first; cancel shows nothing; `FLAG_SECURE` on success. A PDF that needs a password to open does not open full screen. He is told it is password-protected and can open it in another app. No password field. An owner-password-only PDF opens as a normal PDF. | US-85 Approved, ADR-0005 |
| Sensitive item | He marks an item sensitive, or opens one | Lists show type and "Sensitive" until one US-37 unlock in this session, then the title. Backgrounding the app returns rows to the neutral form. Body waits for the prompt. | Cancelled unlock shows no body. Both notification versions are only "Lagoon reminder". Export waits for unlock. Topic calls exclude the item. | US-37 Draft, US-75 Approved, US-34 Approved |
| Reminder and expiry | He sets a date on the quick-add row or on the item | Default time 09:00 in the phone's current local zone. One reminder or one expiry. `POST_NOTIFICATIONS` is asked at the first reminder, not at launch. | Skipping Add date leaves no date. Alarms stay inexact. Upcoming, if built, lists soonest first, then an Expired group. Done stops further notices for that expiry. Snooze is 1 hour. | US-34 Approved, US-54 Draft, US-55 Approved, US-62 Approved, US-77 Draft |
| Topics | An ordinary item is saved with the Settings switch on | After enrich, snapshot, and OCR, a topic call may run. Pending suggestions is a button on Inbox. He confirms before a new topic exists. | The switch off skips the call. "Get topics" sends one ordinary item. A sensitive item is never sent. Failure keeps the item and shows "Try again", unless the credential is blocked. Empty result says no topics, not failed. | US-41 Approved, US-42–US-48 Draft, US-60 Draft, ADR-0003 |
| Settings and sync | He opens the one Settings screen | Controls in the Settings wireframe. Sign-in is the Google sheet. Signed-in state is a sentence on Settings. | No client ID: "Sync is not configured." The app does not crash and local save still works. Revoked sign-in, Drive full, and upload failure use the sentences in Copy. No Back up now and no Restore. | US-59 Draft, US-17 Draft, US-18 Draft, US-19 Draft, US-30 Draft, US-51 Draft, US-22 Draft, US-53 Draft |
| Forget this phone | He chooses "Forget this phone" | US-37 unlock, then a list of other installs (device name, model, last check-in). Confirm names that install. This phone uploads its merged file, writes and reads back its forgotten-installs file, then deletes that other records file. | Cancelled unlock changes nothing. No other phone: "There is no other phone to forget." A failed write, read-back, or pre-upload tells him and deletes nothing. A failed records delete stays pending: "will be forgotten at its next sync". | US-87 Approved, US-59 Draft, ADR-0002 |
| Sync paused | A sync reaches Drive and cannot read or list a forgotten-installs file | After 3 such syncs in a row, the banner in Copy is shown. The first sync that reads every forgotten-installs file successfully removes it. The local vault stays. This phone does not upload while a read or listing has failed. | An offline sync, when Drive is not reachable, does not count and does not reset the run. A newer-key file is not a read failure. | US-66 Approved, US-87 Approved, ADR-0002 |
| Returning forgotten phone | This install's id is in a forgotten-installs file that was read successfully, and every such file was read | He is asked for the passphrase before anything local is discarded. On accept, the phone rebuilds from Drive, rejoins with a new install id, and shows the lost-edits sentence only if something was lost. | Cancel or a wrong passphrase discards nothing and does not upload. The prompt returns at the next sync. | US-87 Approved, US-66 Approved |
| Passphrase | First run, a change, or a forgotten-passphrase reset | Setup shows the warning and the strong-passphrase guidance. 12 to 128 Unicode code points after NFC. The bundled common-password list is refused. No strength meter. A normal change asks for the current passphrase, then the new one twice. A phone that still holds the key can set a new passphrase after US-37, without the old passphrase. | Under the minimum, over the maximum, or on the common list: refused, and the KDF does not run. Wrong passphrase: nothing changes, and the next try waits 2, 4, 8, 16, then 32 seconds. A phone that cannot allocate 65536 KiB refuses setup. Declining the hardware warning creates no vault. | US-35 Approved, US-86 Approved, ADR-0001 |
| Reset notice on another phone | A pending-reset marker is on Drive and this phone holds the key | A system notification and an in-app banner, with no passphrase and no item content. He can cancel. The Drive copy stays on the old passphrase for 24 hours. | One phone, and no other install holds the key: no notice. Cancel keeps Drive on the old passphrase. Items saved on the resetting phone during the wait are kept. | US-86 Approved, ADR-0001 |
| Sign out | He chooses Sign out, or "Disconnect and delete Lagoon data from Drive" | Sign out writes the US-55 release marker, then clears credentials. The local vault stays. The Drive copy stays. The delete action is separate, asks him to confirm, and requires the US-37 prompt even when app lock is off. | The delete warns that it lasts only if every other phone is signed out first, because forgotten-installs files are removed too. | US-78 Approved |
| OCR disclosure | The first OCR use, or a withdrawn acceptance | The disclosure in Copy is shown. Accept stores acceptance and then ML Kit may start. The same text is on Settings, with whether he has accepted, and withdraw. | Decline, or leaving it unanswered, does not start ML Kit. There is no telemetry off switch. | US-07 Draft, US-31 Approved, ADR-0004 |
| Theme | He opens Settings | System is selected on a fresh install. Light, Dark, or System applies immediately. Dynamic color is off. Reduced motion follows the system scale. | API 30 and below do not show the dynamic-color toggle. | US-22 Draft, US-53 Draft, E5 |

Proposed amendment. Needs approval by mjbvilhena. Original, in the Settings and sync unhappy path: "No Back up now and no Restore." Replacement: "No Back up now and no backup Restore." Trash Restore stays on the Delete and trash flow. There is no backup Restore in v1.

Out of scope for these flows: E10 trip grouping, a second person, an image editor, a PDF editor, a Play Store listing, a component library other than Material 3, a second accent, a per-item topics opt-out, Delete forever, a strength meter, "Check my passphrase", Back up now, and Restore.

Proposed amendment. Needs approval by mjbvilhena. Original: "Back up now, and Restore." Replacement: "Back up now, and a backup Restore." Restore on Trash and on the trash which-item picker stays in scope. There is no backup Restore in v1.

## Wireframes

Regions only. Pixel mockups are not in the stories.

A Material 3 navigation bar has Home, Search, Inbox, Topics, and Settings. Upcoming is a button on the Home app bar. Pending suggestions is a button on Inbox. Back is the app-bar arrow.

### Home list

```text
+----------------------------------+
| Lagoon              [Upcoming]   |
|----------------------------------|
| [sync-paused banner, only when   |
|  the rule below is true]         |
|----------------------------------|
| [filter: type, tag, source, date]|
|----------------------------------|
| title                            |
| type · saved date · source       |
| reminder or expiry marker        |
|----------------------------------|
| Sensitive                        |
| type · Sensitive                 |
| (no title, thumbnail, snippet)   |
+----------------------------------+
```

Empty: "Nothing is saved yet." A home row does not draw a link preview or the foggy image. A trashed item is not in this list. Source is the domain or the sending app when US-61 has one. The banner is the sync-paused notice and is absent until that rule is met.

### Item detail

```text
+----------------------------------+
| title                     [Edit] |
| type · last-saved · source       |
| URL (link items)                 |
|----------------------------------|
| body                             |
| personal note, dated YYYY-MM-DD  |
|----------------------------------|
| [image or PDF page]              |
|  or foggy preview + prompt       |
|----------------------------------|
| Fetch preview                    |
| Get topics                       |
| Share                            |
| Open                             |
| Delete                           |
+----------------------------------+
```

One tap on an image or a PDF page opens the full-screen view. "Fetch preview" is shown when a preview was skipped. "Get topics" is the named per-item control. Share and Open are US-64 (Should): share out, or Custom Tabs / `ACTION_VIEW` for `http` and `https`. Delete starts the undo snackbar. Mark sensitive lives on Edit, because US-37 says he marks the item and does not name a second screen.

A sensitive item that is locked does not show this body. The US-37 prompt is shown first. `FLAG_SECURE` applies once the content is shown.

### Edit item

```text
+----------------------------------+
| Title                            |
| Body                             |
| Why it matters                   |
| Tags                             |
| Sensitive                        |
| Reminder or expiry               |
| [Save]                           |
+----------------------------------+
```

Title cannot be saved empty. "Why it matters" may stay empty. Sensitive is the mark from US-37. One reminder or one expiry, not both as two dates, and no return-by or warranty field.

### Search

```text
+----------------------------------+
| [query]                          |
|----------------------------------|
| highlighted title                |
| snippet with the match marked    |
|----------------------------------|
| Sensitive                        |
| type · Sensitive                 |
| (no snippet, no highlight)       |
+----------------------------------+
```

Order is the US-50 rank. A miss is an empty list. A locked sensitive item is returned only for a tag word.

### Quick-add row

Shown after the save, not before it. Not a sheet he must finish.

```text
+----------------------------------+
| Saved to Lagoon                  |
| [tag] [tag] … up to 8, newest    |
| Add note                         |
| Add date                         |
+----------------------------------+
```

No chips when he has never used a tag. Back, or a tap outside, closes the row and keeps the item. Add date sets the one reminder or the one expiry. Skipping it leaves none.

### Add a note

```text
+----------------------------------+
| Title                            |
| Body                             |
| Why it matters                   |
| [Save]                           |
+----------------------------------+
```

Save with a body shows "Saved to Lagoon", then the quick-add row. An empty body does not save.

### Add a URL

```text
+----------------------------------+
| URL                              |
| Title (optional)                 |
| [Save]                           |
+----------------------------------+
```

The URL is kept even when enrichment has not run. An empty title follows US-40.

### Which-item picker

```text
+----------------------------------+
| Which item?                      |
| title · type                     |
| Sensitive                        |
| [dismiss]                        |
+----------------------------------+
```

Live matches, when any exist, are the only rows, and there is no restore offer. When every match is in trash, the rows are the trashed items and the chosen one gets the restore offer. A sensitive row is neutral until unlock, then shows the title. Dismiss saves the share as a new item.

### Settings

One screen. The order is this wireframe. Change passphrase and Set a new passphrase open from the rows under Trash. No second settings screen.

```text
+----------------------------------+
| Settings                         |
|----------------------------------|
| Theme                            |
| ( ) Light ( ) Dark ( ) System    |
| Dynamic color          [off]     |
| (hidden on API 30 and below)     |
|----------------------------------|
| Sync                             |
| Sync is not configured.          |
|  or: Signed in                   |
|  or: an error sentence           |
| [sync-paused banner, if showing] |
| Unmetered only         [on]      |
| Sign in                          |
| Sign out                         |
| Disconnect and delete Lagoon     |
|   data from Drive                |
| Forget this phone                |
|----------------------------------|
| Reminder default time    09:00   |
| Notifications            state   |
| App lock                 [off]   |
|----------------------------------|
| Topics                   [on]    |
| Topics unmetered only    [ ]     |
| This month's topic calls  N      |
| Topic key: type it on this phone |
|----------------------------------|
| Text recognition                 |
| [the US-07 disclosure]           |
| Accepted: yes or no              |
| Withdraw acceptance              |
|----------------------------------|
| Export zip                       |
| Import zip                       |
| Trash                            |
| Change passphrase                |
| Set a new passphrase             |
|----------------------------------|
| [hardware warning, only when     |
|  the key is not hardware-backed] |
+----------------------------------+
```

Absent on this screen and on every other screen: Back up now, last-backup time, "Check my passphrase", a periodic passphrase reminder, a telemetry off switch, a per-item topics opt-out, and a strength meter. Restore appears only on Trash and the trash which-item picker. There is no backup Restore in v1.

The hardware warning, when shown, is the specification sentence. It is not a second accept gate.

"This month's topic calls" is a count, not a cap, and it is not synced. The topic key sentence is shown when this phone has no topic key stored: the key is not copied from the other phone and must be typed again.

### Trash

Opened from Settings.

```text
+----------------------------------+
| Trash                            |
|----------------------------------|
| type · title                     |
| N days left          [Restore]   |
|----------------------------------|
| Sensitive                        |
| type · Sensitive     [Restore]   |
+----------------------------------+
```

Days left are whole days until 30 × 24 hours after `trashedAt`, rounded up, so the last day shows 1. After that deadline, and before this phone removes the row, the row shows no day count and does not show 0. After removal, the row is gone. Restore is the only action on the row. Opening the row is read-only: the same fields as detail, with no Edit, no Delete, and no "Get topics" that would send a held job. A sensitive row is neutral until unlock. After unlock it shows the title. Screenshots of Trash are blocked while it is shown.

No Delete forever. No Delete now. No Empty trash.

### Forget this phone

```text
+----------------------------------+
| Forget this phone                |
|----------------------------------|
| device name                      |
| model · last check-in            |
|----------------------------------|
| device name                      |
| will be forgotten at its next    |
| sync                             |
|----------------------------------|
| [Confirm, naming that install]   |
| [Cancel]                         |
+----------------------------------+
```

This install is not listed. The list is shown only after the US-37 unlock. Confirm names the chosen install. Cancel changes nothing. Empty: "There is no other phone to forget."

### Full-screen image or PDF page

```text
+----------------------------------+
|                                  |
|        image or PDF page         |
|                                  |
| swipe: previous / next           |
| pinch: zoom                      |
| [Close]                          |
+----------------------------------+
```

Brightness override is `1.0f` for this window only. The system brightness setting is unchanged. The screen stays on until Close. Swipe does not wrap and does not leave this view. Nothing is written to disk for the decoded image or the rendered page. A sensitive item uses `FLAG_SECURE` here.

### Passphrase setup

```text
+----------------------------------+
| Set a passphrase                 |
| Choose a passphrase of 12 to 128 |
| characters that you do not use   |
| anywhere else.                   |
| [loss warning]                   |
| Passphrase                       |
| [Accept]                         |
| [Decline]                        |
+----------------------------------+
```

`FLAG_SECURE`. Password input. No strength meter. No "Check my passphrase". Decline tells him nothing was saved when a share was waiting, and writes nothing. The loss warning states that the key is derived from the passphrase, that forgetting it and losing the device means the content is lost, that uninstall or clear storage destroys the Keystore copy, and that losing the signing key forces a reinstall that destroys that copy too. The guidance sentence is: Choose a passphrase of 12 to 128 characters that you do not use anywhere else.

If the key will not be hardware-backed, the specification warning is on this screen before any vault bytes are written. Decline deletes the software Keystore entry and writes no vault.

If the phone cannot allocate 65536 KiB for Argon2id, this screen does not create a vault. He is told why. The parameters are not lowered.

### Change passphrase

```text
+----------------------------------+
| Change passphrase                |
| Current passphrase               |
| New passphrase                   |
| New passphrase again             |
| [Save]                           |
+----------------------------------+
```

A normal change asks for the current passphrase. After it, he is told that zips he already exported still open only with the old passphrase. There is no 24-hour wait on this path.

### Forgotten-passphrase reset

On a phone that still holds the key: US-37 first, then the new passphrase twice, with no current-passphrase field. On a phone that does not hold the key, this reset is not offered.

On every other phone that holds the key, while the marker is on Drive:

```text
+----------------------------------+
| A passphrase reset is waiting.   |
| [Cancel reset]                   |
+----------------------------------+
```

The same words are the system notification. No passphrase. No item title. No item body. Cancel leaves Drive on the old passphrase.

### ML Kit disclosure

```text
+----------------------------------+
| Text recognition                 |
| [sentences in Copy]              |
| [Accept]                         |
| [Decline]                        |
+----------------------------------+
```

Shown before the first OCR use, and again after he withdraws acceptance. Leaving the dialog without Accept or Decline is unanswered: OCR does not run. Settings shows the same sentences, whether he has accepted, and Withdraw acceptance. There is no off switch for the metrics.

### Topic list

```text
+----------------------------------+
| Topics                           |
| name · N items · AI-generated    |
+----------------------------------+
```

Empty: "There are no topics." The AI-generated mark is on a topic created from a suggestion.

### Pending suggestions

A place next to the Inbox, not a prompt on every save.

```text
+----------------------------------+
| Pending suggestions              |
| name                             |
| quote                            |
| [Confirm] [Decline]              |
+----------------------------------+
```

Confirm, decline, rename, and merge keep the rules in US-43 and US-46. Declining does not delete the item.

Proposed amendment. Needs approval by mjbvilhena. This screen also cites specification Core Workflows (Topics): a topic that does not exist yet is created only after he confirms. US-43 and US-46 stay Draft.

### Topic detail

```text
+----------------------------------+
| name                             |
| item title                       |
| note · date · AI                 |
+----------------------------------+
```

Empty of links: the name, and "Nothing is linked." Notes and quotes from a locked sensitive item stay hidden.

### Upcoming

```text
+----------------------------------+
| Upcoming                         |
| title · date                     |
|----------------------------------|
| Expired                          |
| title · expired                  |
|----------------------------------|
| Sensitive                        |
+----------------------------------+
```

Future dates are soonest first. Passed dates are in Expired, most recent first. A locked sensitive row uses "Sensitive", not the title. This screen exists when US-62 is built. US-62 is Should.

### Inbox

```text
+----------------------------------+
| Inbox      [Pending suggestions] |
| [untagged rows, same as Home]    |
+----------------------------------+
```

Untagged is a view, not a stored Inbox tag. Pending suggestions is a button on Inbox. US-71 batch filing is Could and is not drawn.

Proposed amendment. Needs approval by mjbvilhena. This screen is US-38. Specification Core Workflows names the Untagged / Inbox view. US-38 stays Draft.

### Link preview prompt

On item detail, in place of a preview image. Not on the home row.

```text
+----------------------------------+
| [foggy image, no page content]   |
| Enable the preview for this link?|
| [Yes] [No]                       |
+----------------------------------+
```

The fog is an image, not a sentence of filler copy. It is a grey-green blur with no text and no page content. Yes fetches once. No, or leaving, keeps the fog.

### Sync-paused banner

Not its own screen. A text banner on the home list and in the sync area of Settings, and nowhere else, while the condition holds.

```text
+----------------------------------+
| Sync is paused. Lagoon reached   |
| Drive and could not read which   |
| phones were forgotten. Nothing   |
| on this phone was deleted or     |
| uploaded. It will try again.     |
+----------------------------------+
```

The rule, from ADR-0002, US-66, and US-87:

- The banner appears after 3 consecutive syncs that reach Drive and fail to read or list a forgotten-installs file.
- It clears at the first sync that reads every forgotten-installs file successfully.
- An offline sync, when Drive is not reachable, does not count toward those 3 and does not reset the run.
- Only a sync that reaches Drive and fails to read or list a forgotten-installs file counts.
- A file under a newer key is not a read failure.
- The banner names no item title, no install id, and no passphrase.
- It is in the app. It is not a system notification.

Those stories deferred the exact text to this artefact. The sentence in the banner is the proposed text.

### Notices that are not screens

| Notice | When | Form |
| --- | --- | --- |
| Undo | After delete, until he dismisses it, leaves that screen, or it times out | Snackbar. The words beside Undo are "In trash". It times out after 8 seconds. Action: Undo. |
| Repeat link | A normalised URL matches a saved item | Snackbar: "Saved before, added to existing". Actions: Undo, "Keep separate". |
| Dropped edit | Once, when a dropped user edit is merged. Not after a restart. Not for OCR, a preview, or another system write. | In-app. Not a system notification. |
| Orphan files removed | After a returning phone rebuilds, and only when attachment files were actually deleted | In-app. Neutral. No item title. |
| Unsynced edits lost | After a returning phone rebuilds, and only when something was actually lost | In-app. |
| Passphrase reset waiting | Another phone holds the key and a pending-reset marker is on Drive | System notification and the in-app banner above. |
| Ordinary reminder | The install US-55 picks posts it | System notification. Title and codes from the item title. No separate code field. |
| Sensitive reminder or expiry | Same install | Public and private text are only "Lagoon reminder". |
| Clock | This phone's clock does not match Drive | The text is "This phone's clock does not match Drive." The threshold is open. |

Proposed amendment. Needs approval by mjbvilhena. The Clock row's "The threshold is open" is replaced by the 24-hour rule in open question 1. The warning text stays.

The undo snackbar times out after 8 seconds. The words beside Undo are "In trash".

### System surfaces that are not Lagoon screens

- Google sign-in and the `drive.appdata` grant (US-18, US-78).
- The biometric prompt, and "Use PIN" on API 26–29 (US-37).
- The system camera (US-65).
- Custom Tabs or the external viewer (US-64).
- The launcher icon and the name Lagoon (US-23). The icon is a teal rounded square and a simple wave.

## Copy

Strings the stories already fix are marked Story. Strings this file fills, because a story or ADR deferred the exact text, are marked Proposed. The strings in this table are the text of this file.

| Screen | Element | Copy | Source |
| --- | --- | --- | --- |
| Launcher | Name | Lagoon | Story. Vision, US-23 |
| Quick-add row, add note | Confirmation | Saved to Lagoon | Story. US-05, US-28 |
| Repeat link | Snackbar | Saved before, added to existing | Story. Specification, US-49 |
| Repeat link | Actions | Undo, Keep separate | Story. Specification |
| Delete | Snackbar action | Undo | Story. US-13 |
| Home | Empty | Nothing is saved yet. | Proposed, from the US-10 Then |
| Add note | Empty body | The note was not saved. | Proposed, from the US-05 Then |
| Edit | Empty title | A title is required. | Proposed, from the US-12 Then |
| Detail | Enrichment failed | Page details are not available | Story. US-09 |
| Detail | Skipped preview | Enable the preview for this link? | Proposed. US-79 says he is asked whether to enable the preview for that link. |
| Detail | Preview actions | Yes, No | Proposed. The outcomes are US-79. |
| Detail | Later fetch | Fetch preview | Story. US-79 |
| Detail | Topics | Get topics | Story. US-41, US-75 |
| Topic failure | Action | Try again | Story. US-47 |
| Topic failure | Empty result | No topics | Proposed, from the US-47 Then "it says no topics, not failed" |
| Topic list | Empty | There are no topics. | Proposed, from the US-60 Then |
| Topic detail | Empty | Nothing is linked. | Proposed, from the US-45 Then |
| First topic key | Prompt | Send N earlier items for topics? | Story. Specification, ADR-0003. Default answer No. N is the count in that sentence. |
| Sensitive row | Mark | Sensitive | Story. US-75 |
| Notification | Sensitive | Lagoon reminder | Story. US-34, US-75 |
| Picker | Title | Which item? | Proposed. ADR-0002 names the picker and does not name the heading. |
| Settings | Theme | Light, Dark, System | Story. US-22 |
| Settings | Dynamic color | Dynamic color | Proposed label. US-53 names the toggle and not the label. |
| Settings | Sync absent | Sync is not configured. | Proposed, from the US-17 Then |
| Settings | Not signed in | Sync is not signed in. Items on this phone are still here. | Proposed, from the US-18 Then |
| Settings | Signed in | Signed in | Proposed, from the US-18 Then "Settings shows that he is signed in" |
| Settings | Revoked | Sign-in was revoked. Items on this phone are still here. | Proposed, from the US-51 Then |
| Settings | Drive full | Drive is full. The item on this phone is still here. | Proposed, from the US-51 Then |
| Settings | Upload failed | The upload failed. The item on this phone is still here. | Proposed, from the US-51 Then |
| Settings | Preference | Unmetered only | Story. US-19, US-44 |
| Settings | Sign out | Sign out | Story. US-78 |
| Settings | Drive delete | Disconnect and delete Lagoon data from Drive | Story. US-78 |
| Settings | Forget | Forget this phone | Story. US-87 |
| Forget | Empty | There is no other phone to forget. | Proposed, from the US-87 Then |
| Forget | Pending | will be forgotten at its next sync | Story. US-87 |
| Forget | Failed before delete | Nothing was forgotten. | Proposed. US-87 says he is told, and does not fix the sentence, when the upload, the write, or the read-back fails. |
| Settings | Topics | Topics | Proposed label for the US-41 switch. |
| Settings | Call count | This month's topic calls | Proposed label. The count is required. The label is not fixed. |
| Settings | Missing key | The topic key is not copied from the other phone. Type it again on this phone. | Proposed, from the US-59 Then |
| Settings | App lock | App lock | Proposed label. US-37 names the toggle. |
| Settings | Zip | Export zip, Import zip | Proposed labels. US-21 and US-52 name the actions. |
| Settings | Trash | Trash | Story. US-66 |
| Trash | Action | Restore | Story. US-66 |
| Trash | Days | N days left | Proposed. US-66 requires the days left and does not fix the words. |
| Home and Settings | Sync paused | Sync is paused. Lagoon reached Drive and could not read which phones were forgotten. Nothing on this phone was deleted or uploaded. It will try again. | Proposed. ADR-0002, US-66, and US-87 deferred this text. |
| Dropped edit | Ordinary, or sensitive after unlock | That edit couldn't be kept. | Proposed stem. US-66 and ADR-0002 require "couldn't be kept" and, after unlock, the title. |
| Dropped edit | Title after unlock | The item title follows the stem, on a second line. | Story requires the title after unlock. US-66. |
| Dropped edit | Sensitive before unlock | That edit couldn't be kept. | Proposed. No title. US-66. |
| Rebuild | Files removed | Files on this phone that are not part of a saved item were removed. | Proposed. Neutral, no title. US-66, ADR-0002. |
| Rebuild | Edits lost | Edits on this phone that had not synced were not kept. | Proposed. US-87 says he sees that those unsynced local edits were lost, only when something was lost. |
| Reset | Other phone | A passphrase reset is waiting. | Proposed. ADR-0001 requires a notice with no passphrase and no item content, and does not fix the words. |
| Reset | Action | Cancel reset | Proposed label for the cancel ADR-0001 already requires. |
| Hardware | Warning | This phone cannot keep the vault key in secure hardware. Someone with this phone could extract the key. | Story. Specification, ADR-0001, US-35 |
| PDF | Password to open | This PDF is password-protected. | Proposed, from the US-85 Then |
| Marked sensitive later | Already sent | Content already sent cannot be recalled. | Proposed, from the US-75 Then |
| Upcoming | Group | Expired | Story. US-62 |
| Upcoming | Actions | Done, Snooze | Story. US-62. Snooze is 1 hour. |
| Older unlock | Action | Use PIN | Story. US-37 |
| Disclosure | Body | The image and the recognized text stay on this phone. They are not sent to Google. ML Kit sends usage and diagnostic metrics to Google over HTTPS. ML Kit does not transfer them to third parties. ML Kit may contact Google for model updates and bug fixes. This app has no switch to turn those metrics off. The metrics are: device information and ML hardware accelerators, package name and version, per-installation identifiers, performance metrics, API configuration, input and output size, feature version, event types, and error codes. | Proposed sentences. The facts are US-07. US-07 deferred the sentences. |
| Disclosure | Actions | Accept, Decline | Proposed. US-07 names accept and decline and does not name the buttons. |
| Disclosure | Settings | Withdraw acceptance | Proposed label. US-07 requires withdraw. |
| Passphrase | Retry | Wait, then try again. | Proposed. ADR-0001 fixes the delays and says a wrong passphrase is reported. It does not fix the sentence. |
| Passphrase | Guidance | Choose a passphrase of 12 to 128 characters that you do not use anywhere else. | US-35 requires the guidance. |
| Delete | Snackbar | In trash | The action is Undo. The snackbar times out after 8 seconds. |
| Clock | Warning | This phone's clock does not match Drive. | The threshold is open. |

Proposed amendment. Needs approval by mjbvilhena. Citations added. The copy strings and the flow sentences above stay. Each added source already states the fact.

- Edit flow also cites ADR-0002. User fields named there are the title, body, personal notes, and a tag he changes. Specification Product rules: an empty title fills from the page title or the file name. US-12 stays Draft.
- Theme flow also cites specification Product rules (light accent, dark accent, dynamic color) and E5 (Light, Dark, and System). US-22 and US-53 stay Draft.
- Settings and sync flow also cites the approved stories for controls on the Settings wireframe: US-34 (reminder default), US-35 (passphrase controls and no "Check my passphrase"), US-41 (topic switch), US-66 (Trash), US-78 (Sign out and the Drive delete), and US-87 (Forget this phone). US-59 stays Draft.
- Launcher name also cites specification Product rules: the name is Lagoon.
- Theme labels Light, Dark, and System also cite E5.
- "Saved to Lagoon" also cites US-01, US-02, US-03, US-04, and US-06. Those stories stay Draft. No approved story states that sentence.
- Repeat-link actions also cite US-49, which is approved, and specification Product rules (Undo and "Keep separate").
- Undo also cites ADR-0002. Undo writes nothing remote. US-13 stays Draft.
- Dynamic color also cites specification Product rules and E5.
- "Unmetered only" also cites specification Product rules (unmetered network) and E11 (the topic job's own setting). US-19 and US-44 stay Draft.
- "This month's topic calls" also cites ADR-0003. The count is on this install and is not a cap.
- The missing topic key also cites ADR-0003. The key is not in the synced records. US-59 stays Draft.
- App lock also cites specification Product rules. App lock is optional. US-37 stays Draft.
- Export zip and Import zip also cite specification Core Workflows. He can export and import a zip. US-21 and US-52 stay Draft.
- Disclosure body also cites specification Product rules (the disclosure facts), US-31, which is approved, and ADR-0004. US-07 stays Draft.
- "Use PIN" cites US-37. No approved story, accepted ADR, or specification sentence names that button. US-37 stays Draft.
- Empty home, empty note, empty title, enrichment failure, "Try again", empty topic list, and empty topic detail keep their Draft story sources. Those strings are not in an approved story, an accepted ADR, or the specification, so no further citation was added.

Proposed amendment. Needs approval by mjbvilhena. The Clock copy row's "The threshold is open" is replaced by the 24-hour rule in open question 1. The warning text stays.

No screen uses "Check my passphrase" or "Back up now". Restore appears only on Trash and the trash which-item picker. There is no backup Restore in v1.

## Open questions

1. **Clock-warning threshold.** The warning text is "This phone's clock does not match Drive." How far out the clock must be before that warning shows is not set. That threshold is still open.

Proposed amendment. Needs approval by mjbvilhena. The warning shows when the absolute difference between this phone's clock and Drive's server time is 24 hours or more. The server time is the Drive `modifiedTime` of a records file this phone uploaded on that sync and read back. ADR-0002 already assumes the Drive v3 files API, and Drive sets `modifiedTime` from its clock. Another install's `modifiedTime` is not this comparison. If this sync has no such read-back, the warning stays as it was. A later comparison under 24 hours clears it. Merges, `trashChangedAt`, and the tombstone do not change (ADR-0002, T-28). The 24 hours are the forgotten-passphrase wait in accepted ADR-0001, and one displayed day of the US-66 trash window (30 × 24 hours, whole days). ADR-0002 and T-28 accept that any skew can make an older edit win or can tombstone early, and they choose no merge guard, so this warning does not change a merge. A threshold near one millisecond would warn on ordinary drift. 24 hours is also longer than the seconds a Drive upload spends before `modifiedTime` is read back, so the comparison does not warn on that latency.

## Relationship

Upstream claims are the vision, the specification, the epics, and the story files cited above. This file does not change those claims.

Parallel records are ADR-0001 through ADR-0006 and `docs/technical_design/threat-model.md`. Where a story and a layer file use the same rule, both are cited. No conflict was found that this file had to pick a side for. US-57 is a spike about FTS5 and SQLCipher. It has no screen.

`docs/technical_design/test-plan.md` plans how these screens and the domain rules are proved. It is approved. Approved by mjbvilhena on 2026-10-30.

Keyboard access, accessible names, and the `ui change` checks are implement and verify work. This file does not claim a conformance result.

This file is approved. Approved by mjbvilhena on 2026-10-31. Implementation has not started. A merged pull request is not that approval.
