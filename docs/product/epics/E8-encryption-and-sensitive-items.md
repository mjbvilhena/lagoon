# Epic E8: Encryption and sensitive items

> **Approval status: Draft. Not approved.**
> Approver required: `mjbvilhena`. Sign-off on file: none.
> His words on Q10: "Yes, extra encryption would be good." He also decided fingerprint or biometric lock on items marked sensitive, tied to this work. Neither decision approves this epic. The cipher is an ADR, not this file. Shipped-epic DoD is not claimed.

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
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Choosing algorithms, modes, or key lengths here. A cloud key service. App-wide unlock, which is still an open question for him. Encrypting the zip with a scheme this draft invents. The zip must not become a plaintext hole if the rest of the vault is encrypted; how the zip is protected follows the same at-rest outcome and is not a second design. Treating Drive sync as the only way content can leave the phone. Topic extraction (E11) is his recorded exception: readable content goes to a cloud AI. Drive still receives only ciphertext. This epic does not design that call.

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

- Depends on E4 for the sync path. Sync of readable data on a second device depends on his answer about keys.
- Threat model (`sdlc-threat-modeler`) is required before implementation. Not started.
- Lost key, key transport, and app unlock are his open questions. Stories do not answer them.

## Open Questions

- [ ] How keys reach a new or second device. (@mjbvilhena)
- [ ] What happens if the key or passphrase is lost. (@mjbvilhena)
- [ ] Whether unlocking the whole app is required. (@mjbvilhena)

## Draft stories

- US-35 Encrypt the vault at rest
- US-36 Sync only ciphertext
- US-37 Lock sensitive items with a biometric
