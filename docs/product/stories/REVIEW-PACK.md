# Story review pack

> **Draft. Not approval.** Listing a story or a verdict here is not a sign-off.
> Status of every story stays Draft until all five bots re-review.
> Revised 2026-10-17. The per-story verdicts are from the review of commit `9e7de63`.

## Decisions by Miguel

Miguel (`mjbvilhena`) confirmed these in chat on 2026-10-17 at 14:56 IST. They are not story approval.

- If the vault key is lost, the content is lost. No recovery. US-35 requires a clear warning before the key is created, and no recovery flow.
- App lock is optional. The device PIN is the fallback when there are no biometrics. US-37.
- Lock-screen notifications may show the item title and codes. US-34. Sensitive-item notification content is delegated, below.
- The zip export is encrypted with the vault key. US-21.

## Open questions for Miguel

Not decided. Not delegated.

- Sync conflicts, and how a deletion propagates. Options recorded on US-73, not chosen. One option from Scout is tombstones, per-field last-writer-wins, and merged notes.
- How the vault key reaches a new device. US-74.
- Exact alarms. That would amend E9. US-77. Stories keep inexact alarms.
- Topic provider, its retention and training terms, how the credential is supplied in a sideloaded APK, and what is sent. US-76. The release APK must not contain that key in plaintext.
- Whether 09:00 should follow a fixed zone, such as Europe/Dublin, instead of the phone. Stories use the device local zone.
- Full-screen QR and barcode display. No story.

## Open questions delegated to bots

On 2026-10-17 at 14:56 IST Miguel delegated these to his bots. He did not decide them.

- Sensitive-item handling in lists, search, notifications, export, the cloud call, and screenshots. US-75.
- Drive scope and sign-out. US-78. The approved specification still names `appDataFolder`.
- Link-preview skipping. US-79.
- Sharing out. US-64.
- Permissions. US-80. US-34 still asks for `POST_NOTIFICATIONS` at the first reminder, which is the recorded specification text.
- Sideloading. US-81. US-24 still requires a signed APK because epic E6 does.

## Doc drift, not edited

- `docs/product/specification.md` line 27 still says "E11 has no stories yet". The approved specification was not edited.
- `docs/product/vision.md` still lists OCR under "Not in the request" and still describes Back up now. That file is not accepted and was not edited. The approved specification supersedes it.

## Definition of Done, artefact shape

`sdlc-dod-checker` used `get_definition_of_done("user story")`. This is artefact shape only. Implementation DoD fails for every story: there is no app and no automation, so no story is Done.

Fail: US-64, US-73, US-74, US-75, US-76, US-77, US-78, US-79, US-80, US-81. Each holds an open question and has no asserting Must. They are blocked.

Pass artefact shape: every other story. Each has the actor, a value, asserting Must or an explicit Should or Could, and out of scope. Open questions are moved to the lists above. US-56 and US-57 are chores the review asked for.

## Review verdicts at 9e7de63

Dev Bot, Scout, Mikey's Assistant, Household, and Leisure. Only US-09 and US-27 were Approve from all five. Scout's Approve verdicts, under Miguel's delegation, also cover US-03, US-04, US-07, US-10, US-11, US-16, US-17, US-19, US-24, US-26, US-28, US-30, US-36, US-38, and US-39. Where the consolidated review did not give a separate word, the cell says so. A verdict is not an approval.

| ID | Dev Bot | Scout | Assistant | Household | Leisure |
|---|---|---|---|---|---|
| US-01 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-02 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-03 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-04 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-05 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-06 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-07 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-08 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-09 | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval |
| US-10 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-11 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-12 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-13 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-14 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-15 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-16 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-17 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-18 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-19 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-20 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-21 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-22 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-23 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-24 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-25 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-26 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-27 | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval | Approve, verdict at 9e7de63, not an approval |
| US-28 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-29 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-30 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-31 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-32 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-33 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-34 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-35 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-36 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-37 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-38 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-39 | Not separately recorded | Approve, verdict at 9e7de63, not an approval | Not separately recorded | Not separately recorded | Not separately recorded |
| US-40 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-41 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-42 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-43 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-44 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-45 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-46 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |
| US-47 | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded | Not separately recorded |

US-48 through US-81 were written after that review. They have no verdict from it.

## Stories

Must counts are scenarios marked Must. Should and Could are named when present.

| ID | Title | Epic | Summary | MoSCoW | Must | Open |
|---|---|---|---|---|---|---|
| US-01 | Share a link into the vault | E1 | the page is kept on my phone without me copying the address | Must | 5 | None on this story |
| US-02 | Share plain text into the vault | E1 | a passage I want to keep is stored without me retyping it | Must | 5 | None on this story |
| US-03 | Share an image into the vault | E1 | the picture and any words I attached are kept on the phone | Must | 6 | None on this story |
| US-04 | Share a PDF into the vault | E1 | the file and the text it already contains are searchable later | Must | 5 | None on this story |
| US-05 | Add a note by hand | E1 | I can keep something that did not come from a share | Must | 4 | None on this story |
| US-06 | Add a URL by hand | E1 | I can keep a page when I am not in the app that showed it | Must | 4 | None on this story |
| US-07 | Use the saved vault with no network and no analytics | E1 | I can read and search it offline, and the app does not report my use | Must | 3 | None on this story |
| US-08 | Enrich a saved link from the page | E2 | I can recognise the page later without opening it | Must | 4 | None on this story |
| US-09 | Keep the link when the page cannot be fetched | E2 | a failed fetch does not lose the link (1 Should) | Must | 2 | None on this story |
| US-10 | Browse saved items, newest first | E3 | the thing I just kept is the first row | Must | 4 | None on this story |
| US-11 | Read a saved item | E3 | I can use it without going back to the app it came from | Must | 5 | None on this story |
| US-12 | Edit a saved item | E3 | I can correct it and say why it matters | Must | 5 | None on this story |
| US-13 | Delete an item and undo that delete | E3 | a mistake does not destroy the item, and a finished delete really removes it | Must | 5 | None on this story |
| US-14 | Manage tags on saved items | E3 | I can file items without inventing a second spelling of the same tag | Must | 6 | None on this story |
| US-15 | Search the vault and see highlighted matches | E3 | I can find an item and see why it matched | Must | 5 | None on this story |
| US-16 | Filter items by type and by tag | E3 | I can look at one kind of item without reading the rest | Must | 4 | None on this story |
| US-17 | See that sync is not configured | E4 | the app stays usable and does not pretend it can sync | Must | 4 | None on this story |
| US-18 | Sign in so the vault can sync | E4 | background sync is allowed to use my private Drive app space | Must | 4 | None on this story |
| US-19 | Prefer an unmetered network for background sync | E4 | sync does not use mobile data by default | Must | 4 | None on this story |
| US-20 | Receive the vault on another install | E4 | the vault follows me without a restore button | Must | 4 | None on this story |
| US-21 | Export a zip to a place he chooses | E4 | I have a file fallback that is not a Drive button | Must | 4 | None. Zip encryption is decided |
| US-22 | Choose light, dark, or the system theme | E5 | the vault follows my phone unless I choose otherwise | Must | 4 | None on this story |
| US-23 | Show a launcher icon and the name Lagoon | E5 | I can find the app and share into it | Must | 3 | None on this story |
| US-24 | Install a signed release APK without the Play Store | E6 | I can install Lagoon without a store listing | Must | 5 | None on this story |
| US-25 | Cover search, sync and zip packaging, and a basic screen with automated tests | E6 | those parts cannot change silently before I install a build | Must | 4 | None on this story |
| US-26 | Follow written steps to build, sideload, and create the OAuth client | E6 | I can install a build and turn sync on without guessing | Must | 4 | None on this story |
| US-27 | Review screenshots of the main screens | E6 | I can see those screens without installing a build first | Must | 3 | None on this story |
| US-28 | Optionally tag a save from the last-used tags | E1 | I can file the item without a second form | Must | 6 | None on this story |
| US-29 | Rename a tag, merging only after he confirms a clash | E3 | I do not have to retag each item, and a clash does not happen by accident | Must | 5 | None on this story |
| US-30 | Do not offer backup or restore controls | E4 | sync stays in the background and I am not asked to run a backup | Must | 3 | None on this story |
| US-31 | Recognize English text on the device | E7 | I can search that text without sending the image to a cloud OCR service | Must | 6 | None on this story |
| US-32 | Keep more than one attachment on an item | E1 | a receipt and a photo of the same thing stay together | Must | 5 | None on this story |
| US-33 | Keep an offline snapshot of a saved page | E2 | I can read the page when the network is gone, and still keep the original URL | Must | 8 | None on this story |
| US-34 | Set a reminder | E9 | I do not forget the item | Must | 8 | Exact alarms and a fixed time zone stay open |
| US-35 | Encrypt the vault at rest | E8 | a copy of the phone's storage does not contain the vault in the clear, and I am not promised a recovery that does not exist | Must | 5 | Key transport stays open. Loss is decided |
| US-36 | Sync only ciphertext | E8 | Google cannot read the vault | Must | 3 | None on this story |
| US-37 | Lock sensitive items, and optionally lock the app | E8 | private items stay closed, and I can still lock the app when the phone has no biometric | Must | 7 | Sensitive display is delegated |
| US-38 | File new captures from an Untagged inbox | E3 | new captures do not disappear into the full list before I tag them | Must | 5 | None on this story |
| US-39 | Sort and filter the list by when it was saved | E3 | I can limit the list to a day or a range | Must | 6 | None on this story |
| US-40 | Fill an empty title from the page or the file | E1 | a one-tap save is not called Untitled | Must | 5 | None on this story |
| US-41 | Extract topics when an item is saved | E11 | I do not have to type the subject by hand | Must | 8 | Provider, credential, and payload stay open |
| US-42 | Link an item to a topic the vault already has | E11 | the subject I already have grows instead of starting over | Must | 5 | None on this story |
| US-43 | Confirm a topic before it is created | E11 | a subject I do not want is never created, and one-tap capture stays one tap | Must | 6 | None on this story |
| US-44 | Queue topic processing while offline | E11 | capturing something never waits on the cloud | Must | 6 | None on this story |
| US-45 | Read a topic | E11 | I can look across what I saved on that subject | Must | 5 | None on this story |
| US-46 | Reject, rename, or merge a suggested topic | E11 | the subjects stay the ones I mean | Must | 3 | None on this story |
| US-47 | Keep the item when the topic call fails | E11 | a cloud problem does not lose what I saved or invent a topic | Must | 6 | None on this story |
| US-48 | Manage a topic that is already saved | E11 | I can correct a subject after I have confirmed it | Must | 4 | None on this story |
| US-49 | Update a link that was saved before, and undo that update | E1 | one page does not become two items, and a mistake is reversible | Must | 7 | None on this story |
| US-50 | Rank search matches | E3 | the item I meant is not buried under a weak match | Must | 6 | None on this story |
| US-51 | Sync in the background and show errors | E4 | I do not have to press a backup button and I can see a real failure | Must | 3 | None on this story |
| US-52 | Import a zip and merge it | E4 | a file fallback can fill this phone without wiping it | Must | 3 | None on this story |
| US-53 | Use the teal palette, optional dynamic color, and reduced motion | E5 | I can read the app in light and dark and with reduced motion | Must | 6 | None on this story |
| US-54 | Notify before an expiry | E9 | I can act before the date, and a close date does not schedule a notice that would already have passed | Must | 4 | None on this story |
| US-55 | Reschedule reminders after a restart or a clock change | E9 | a notification is not lost because the phone restarted | Must | 3 | None on this story |
| US-56 | Keep a stable identity for each item | Cross-cutting | an edit or a second device does not fork the item or lose it | Must | 3 | None on this story |
| US-57 | Prove that search and encryption can run together | Cross-cutting | search and encryption are not built on a combination that cannot run | Must | 2 | None on this story |
| US-58 | Run enrichment, snapshot, OCR, and topics in that order | Cross-cutting | search and topics see the text that actually exists | Must | 3 | None on this story |
| US-59 | Use one Settings screen | Cross-cutting | I do not hunt through the app for those controls | Must | 2 | None on this story |
| US-60 | Browse topics and pending suggestions | E11 | I can open a subject and answer suggestions without a prompt on every save | Must | 3 | None on this story |
| US-61 | See and filter by where an item came from | E1 | I can find what I saved from one site or one app | Must | 3 | None on this story |
| US-62 | See what is coming due | E9 | I can see what is due without opening each item (3 Should) | Should | 0 | None on this story |
| US-63 | Copy a code he marked | E9 | I can copy a code without selecting it by hand each time (2 Should) | Should | 0 | None on this story |
| US-64 | Share or open an item in another app | Cross-cutting | I can use the item outside Lagoon | Not set | 0 | Sharing out, delegated to bots |
| US-65 | Add a photo from the camera | E1 | I can add a page without leaving the item (2 Could) | Could | 0 | None on this story |
| US-66 | Keep a trash beyond undo | Cross-cutting | I can recover it after the undo snackbar has gone (2 Could) | Could | 0 | None on this story |
| US-67 | Notice a duplicate image or PDF | E1 | I do not keep two copies of the same file (1 Could) | Could | 0 | None on this story |
| US-68 | Record a return date, a warranty, and a purchase | Cross-cutting | I can type them when I want them (1 Could) | Could | 0 | None on this story |
| US-69 | Pin a shortcut or a widget | Cross-cutting | I can save without hunting for the app (1 Could) | Could | 0 | None on this story |
| US-70 | See what is not ready for offline | Cross-cutting | I know what will be missing before I go offline (1 Could) | Could | 0 | None on this story |
| US-71 | Triage the inbox in a batch | E3 | filing does not mean opening each row (1 Could) | Could | 0 | None on this story |
| US-72 | Score whether topics are useful | E11 | I can judge the topic feature without sending those counts off the phone (2 Should) | Should | 0 | None on this story |
| US-73 | Decide sync conflicts and deletions | Cross-cutting | two phones do not invent a winner | Not set | 0 | Conflicts and deletions, for Miguel |
| US-74 | Decide how the vault key reaches another device | E8 | ciphertext on Drive is not a dead end | Not set | 0 | Key transport, for Miguel |
| US-75 | Decide what a sensitive item reveals | E8 | a sensitive mark actually limits what is shown | Not set | 0 | Sensitive-item handling, delegated to bots |
| US-76 | Decide the topic call's provider, credential, and payload | E11 | readable content does not leave the phone under a rule nobody chose | Not set | 0 | Provider, credential, payload, for Miguel |
| US-77 | Decide whether reminders may use exact alarms | E9 | I do not get a permission the epic did not ask for | Not set | 0 | Exact alarms, for Miguel |
| US-78 | Decide Drive scope and sign-out | E4 | sync does not grow a control I did not ask for | Not set | 0 | Drive scope and sign-out, delegated to bots |
| US-79 | Decide whether a link preview can be skipped | E2 | a save does not drop a preview under a rule nobody chose | Not set | 0 | Link-preview skipping, delegated to bots |
| US-80 | Decide the permission set | Cross-cutting | the install does not request a permission nobody chose | Not set | 0 | Permissions, delegated to bots |
| US-81 | Decide the sideload policy | E6 | the 2027 developer-verification change is not guessed | Not set | 0 | Sideloading, delegated to bots |
