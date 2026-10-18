# Product vision

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.

## Vision

Miguel keeps what he comes across in one private store on his Android phone. He can search it, read it offline, and have a private copy follow him to another install. The phone is the primary copy. He does not run a backup or a restore.

## Who it is for

Miguel. He is the only person named. The vault is personal, not a shared workspace.

## What the vault does

- He shares a link, plain text, an image, or a PDF in from another app, in one tap, or he adds a note or a URL himself. An item can hold more than one attachment. A title fills from the page or the file when he did not type one.
- Seeing where an item came from, the source domain and the sending app, is a Should (US-61).
- A saved link keeps its URL. When a fetch works, it gains a title, description, preview, readable text, and an offline snapshot of the page.
- English text on images and scanned PDFs is recognized on the device with the bundled model. Text-based PDFs are extracted, not sent through OCR. Before the first OCR use he accepts a disclosure that ML Kit sends usage and diagnostic metrics to Google, because Google's docs do not offer a switch to turn that off. He can withdraw that acceptance. The image and the recognized text stay on the device. He can read that disclosure in Settings.
- He browses newest first, opens an item, edits it, deletes it with undo, and files it with tags. Search is ranked and highlighted. He can filter by type and by tag.
- Sharing an item back out to another app is a Should (US-64). The acceptance criteria are on that story.
- Sync runs in the background to his private Google Drive app space. What Drive stores is ciphertext. There is no Back up now, no Restore, and no last-backup time. A zip on the device remains a fallback. The app still works when sync is not configured.
- The database and the files are encrypted on the phone. A sensitive item stays closed until a biometric succeeds, or the device PIN when there is no biometric. App lock is optional. Amendment approved by mjbvilhena on 2026-10-18. The vault key is derived from a passphrase he chooses, so entering it on any device re-creates the same key. No copy of the key is stored on Drive or anywhere else. Forgetting the passphrase and losing the device still means the content is lost.
- An item can have a reminder or an expiry, with a notification. Alarms stay inexact.
- A cloud AI names topics (E11). Topic extraction runs for a new ordinary item, and he can turn it off in Settings. A sensitive item stays out of that call. The topic job can be limited to an unmetered network. He accepted that readable content leaves the phone for that call. Drive sync stays ciphertext. A new topic is created only after he confirms.
- The app follows Light, Dark, or the system theme. The accent is teal. Dynamic color is optional and off until he turns it on.
- He installs a signed APK himself. The name is Lagoon. The app is not a Play Store product in v1.

## How success would be recognized

No numeric target was set. Success is the outcome above: he can install the signed app, save and search offline, and have the encrypted vault follow him without a backup ritual.

## Non-goals

- No analytics library the app chooses, such as Firebase Analytics or Crashlytics. ML Kit's own metrics are the disclosure above, not a second analytics product.
- No Back up now, no Restore, and no last-backup time.
- No second person in the same vault.
- No image editor and no PDF editor.
- Trip or collection grouping is later (E10), not v1.
- The topic provider, what the call sends, how a key reaches a new phone, and sync conflicts follow from the ADR at the design gate. This vision does not decide them.
