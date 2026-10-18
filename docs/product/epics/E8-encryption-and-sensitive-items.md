# Epic E8: Encryption and sensitive items

> **Approval status: Approved.**
> Approved by mjbvilhena on 2026-10-17.
> Amendment approved by mjbvilhena on 2026-10-17.
> Epic amendments approved by mjbvilhena on 2026-10-17.

## Background & Value Proposition

App-private storage is not enough. The database and the attachment files are encrypted at rest, with keys in Android Keystore. Drive receives only ciphertext that this app encrypted. An item he marks sensitive does not open until a biometric succeeds.

No success metric was given.

## Target Audience

Miguel, including on a second device that should see the same vault and not a plaintext copy in Drive.

## Scope & Phasing

- **In Scope (MVP):**
  - Encrypted database and encrypted attachment files on the device.
  - Keys held in Android Keystore.
  - Client-side end-to-end encryption of everything synced to Drive. Drive stores ciphertext only.
  - Fingerprint or biometric lock on items marked sensitive, tied to this encryption work.
  - App lock is optional. The device PIN is the fallback when there are no biometrics.
  - Before unlock, a list, a search result, a screenshot, and a cache do not show a sensitive item's title, body, note, OCR text, or thumbnail (A-E8-1, US-75). A sensitive notification's public version and private version show only "Lagoon reminder", with no title and no code, even when the phone is unlocked or set to show all content. Tapping it opens Lagoon. The item's details appear after the US-37 unlock. Export of a sensitive item waits for the US-37 unlock. The rest of the handling is the criteria on US-75. Sensitive items are excluded from the topic call (E11). There is no per-item topics control in v1. Sensitive items are always excluded, and the Settings switch covers everything else.
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Choosing algorithms, modes, or key lengths here. A cloud key service. App lock is optional. Encrypting the zip with a scheme this draft invents. The zip must not become a plaintext hole if the rest of the vault is encrypted; how the zip is protected follows the same at-rest outcome and is not a second design. Treating Drive sync as the only way content can leave the phone. Topic extraction (E11) sends readable content to a cloud AI. Drive still receives only ciphertext. This epic does not design that call.

## Core Workflows

1. **The phone holds ciphertext keys apart from the files**
   - He saves items as usual.
   - The database and attachments are encrypted. He can still open ordinary items in the app.
2. **Drive cannot read the vault**
   - Sync runs (E4).
   - What arrives in `appDataFolder` is ciphertext.
3. **A sensitive item**
   - He marks an item sensitive.
   - Opening it asks for a biometric. Cancelling or failing leaves the contents hidden.

## Risks & Dependencies

- Depends on E4 for the sync path. Sync of readable data on a second device depends on how keys move.
- Threat model (`sdlc-threat-modeler`) is required before implementation. Not started.
- Key transport is US-74. Criteria follow from the ADR at the design gate. A lost key means the content is lost, with no recovery. App lock is optional, with a device-PIN fallback. The vault key does not require user authentication.

## Open Questions

- [ ] How keys reach a new or second device. US-74. Criteria follow from the ADR at the design gate.
- If the key is lost, the content is lost, and there is no recovery.
- App lock is optional. The device PIN is the fallback when there are no biometrics.

## Draft stories

- US-35 Encrypt the vault at rest
- US-36 Sync only ciphertext
- US-37 Lock sensitive items with a biometric
- US-74 Decide how the vault key reaches another device. Criteria follow from the ADR at the design gate.
- US-75 Limit what a sensitive item reveals.
