# Story review pack

> **Draft. Not approval.** Listing a story here is not a sign-off.
> Compact list of US-01 through US-47 on 2026-10-17. Must counts are scenarios marked Must. A Nice scenario is named in the open-questions column and is not in the Must count.
> E10 has no stories. Nothing below is implemented.

| ID | Title | Epic | Summary | Must | Open questions |
|---|---|---|---|---|---|
| US-01 | Share a link into the vault | E1 | A shared link is saved, and a normalised repeat updates that item. | 8 | None |
| US-02 | Share plain text into the vault | E1 | Shared plain text is saved as its own item. | 3 | None |
| US-03 | Share an image into the vault | E1 | A shared image is saved, with optional words. | 3 | None |
| US-04 | Share a PDF into the vault | E1 | A shared PDF is saved, including text the file already contains. | 3 | None |
| US-05 | Add a note by hand | E1 | He can type a note and save it inside the app. | 3 | None |
| US-06 | Add a URL by hand | E1 | He can type a URL and save it without waiting for a fetch. | 3 | None |
| US-07 | Use the vault offline, with no analytics | E1 | Saved items stay usable with no network, and the app sends no analytics. | 3 | None |
| US-08 | Enrich a link when the page can be fetched | E2 | A saved link gains the page details the fetch actually returns. | 3 | None |
| US-09 | Keep the URL when enrichment does not run | E2 | A failed or offline fetch still keeps the URL. | 2 | One Nice scenario: a later success may fill gaps. It does not block the story. |
| US-10 | Browse items, newest first | E3 | The home list shows items newest first, including an empty vault. | 3 | None |
| US-11 | Read an item | E3 | Opening an item shows what was saved, including empty optional fields. | 3 | None |
| US-12 | Edit an item, including why it matters | E3 | He can change the title and the personal note. | 3 | None |
| US-13 | Delete an item and undo it | E3 | Delete can be undone until he dismisses it or leaves. | 4 | None. The undo window is closed by dismiss or leave. |
| US-14 | Manage tags | E3 | He can add a tag, remove it from one item, or remove it everywhere. | 4 | None |
| US-15 | Search with ranked, highlighted matches | E3 | Search ranks and highlights matches across the saved fields. | 9 | FTS5 / Room 3 / SQLCipher spike stays a design-gate risk, not a decision. |
| US-16 | Filter by type and by tag | E3 | The list and search can be limited by type and by tag. | 4 | None |
| US-17 | See that sync is not configured | E4 | Missing OAuth is explained, and the vault still works. | 4 | None |
| US-18 | Sign in and let the vault sync | E4 | After sign-in, background sync uses Drive app data, with no Back up now button. | 5 | None |
| US-19 | Keep background sync on unmetered networks | E4 | Background sync prefers an unmetered network, and that preference defaults on. | 4 | None |
| US-20 | Receive the vault on a new install | E4 | Another install receives the vault and merges one-sided items. | 6 | Concurrent edits, and how a deletion propagates. |
| US-21 | Export and import a zip | E4 | A zip export and import merges, and is not a plaintext hole. | 6 | The same conflict rules as sync are still open. |
| US-22 | Choose light, dark, or the system theme | E5 | Light, Dark, or System, with System as the default. | 10 | Visual specifics beyond his words wait for the UX artefact. |
| US-23 | Show a launcher icon and the name Lagoon | E5 | The launcher and the share sheet show Lagoon. | 3 | None. The name recommendation is not approval. |
| US-24 | Install a signed release APK | E6 | A signed release APK can be sideloaded. | 4 | Android developer verification for sideloaded APKs. |
| US-25 | Run the tests he named | E6 | Automated tests cover search, sync and zip packaging, and one Compose screen. | 4 | None |
| US-26 | Follow a README to build, sideload, and configure OAuth | E6 | Written steps cover build, sideload, and the OAuth client. | 4 | None. The SHA-1 is still unknown because there is no app. |
| US-27 | Review screenshots of the main screens | E6 | Screenshots of the main screens, including settings, when an emulator exists. | 3 | None |
| US-28 | File a capture with last-used tags | E1 | One tap saves, then last-used tag chips are optional. | 5 | None |
| US-29 | Rename a tag, and merge when the name exists | E3 | A rename updates every item, and a name clash merges only after he confirms. | 5 | None |
| US-30 | No manual backup or restore | E4 | Settings do not offer backup or restore controls. | 3 | None |
| US-31 | Recognize text on the device | E7 | English text in images and scanned PDFs is recognized on the device. | 6 | None. English-only for v1 is a recommendation, not approval. |
| US-32 | Keep more than one attachment on an item | E1 | An item can hold more than one file. | 3 | None |
| US-33 | Keep an offline snapshot of a saved page | E2 | A successful fetch keeps one readable offline archive and the original URL. | 7 | None |
| US-34 | Set a reminder or expiry and get notified | E9 | An optional reminder or expiry notifies him, including after reboot. | 11 | None |
| US-35 | Encrypt the vault at rest | E8 | The database and attachment files are encrypted, with keys in Android Keystore. | 4 | What happens if the key or passphrase is lost. |
| US-36 | Sync only ciphertext | E8 | Drive receives ciphertext only. | 3 | How keys reach a second device, and key loss. |
| US-37 | Lock sensitive items with a biometric | E8 | A sensitive item opens only after a biometric check. | 5 | Whether the whole app must unlock, separate from the sensitive-item lock. |
| US-38 | See untagged items in an inbox | E3 | Untagged items appear in an inbox view until he tags them. | 3 | None |
| US-39 | Sort and filter by the date saved | E3 | The list can be ordered and limited by the date saved. | 6 | None |
| US-40 | Fill the title from the page or the file | E1 | An empty title fills from the page title or the file name. | 4 | None |
| US-41 | Extract topics when an item is saved | E11 | After a save, a cloud AI call stores the main ideas and topic names it returns. | 5 | Provider and retention; credential storage; sensitive-item exclusion; payload slice. |
| US-42 | Link an item to a topic the vault already has | E11 | A case-insensitive exact name links the item and extends that topic's notes. | 4 | The same four design-gate questions as US-41. |
| US-43 | Confirm a topic before it is created | E11 | A topic the vault does not have is created only after he confirms it. | 4 | The same four design-gate questions as US-41. |
| US-44 | Queue topic processing while offline | E11 | A save with no network finishes, and topic processing runs later. | 3 | The same four design-gate questions as US-41. What the later call sends stays open. |
| US-45 | Read a topic | E11 | A topic page shows its name, linked items, and the notes as the summary. | 4 | The same four design-gate questions as US-41. |
| US-46 | Reject, rename, or merge a suggested topic | E11 | He can reject a suggestion, rename it, or fold it into a topic he already has. | 4 | The same four design-gate questions as US-41. Merging two topics that are already saved is out of scope. |
| US-47 | Keep the item when the topic call fails | E11 | A failed call leaves the item, creates no topic, and can be tried again. | 4 | The same four design-gate questions as US-41. No automatic retry schedule was set. |
