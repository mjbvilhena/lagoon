# Epic E8: Encryption and sensitive items

> **Approval status: Approved.**
> Approver: `mjbvilhena`. Date and time: 2026-10-17 13:29 IST.
> His words, after a 3-part walkthrough of every epic E1–E11 (including the E5 Light/Dark/System theme change at `98275b3`): "All makes sense", then "Yes, approved" when asked to confirm that as approval of E1 to E11.
> Approved revision: commit `98275b3` (`98275b3c8b696542b71b66da88ff101e56542a56`). This record does not change the product text of that revision.
> This approval does not approve the child stories. Stories stay Draft. Shipped-epic DoD is not claimed. Nothing in this epic is implemented.
> His words on Q10: "Yes, extra encryption would be good." He also decided fingerprint or biometric lock on items marked sensitive, tied to this work. Both are part of the specification approved by `mjbvilhena` on 2026-10-17. The cipher is an ADR, not this file.
> Amendment `docs/product/amendments/2026-10-17-security-delegation.md`, section 2, applied after his approval.
> Approver: `mjbvilhena`. Date and time: 2026-10-17 15:55 IST.
> His words, after a 4-part walkthrough the same day. Part 1, 15:48 IST: "Yes, that's what I meant". Part 2: "Happy with both". Part 3: "Yes". Final approval at 15:55 IST: "Yes".
> Approved revision of this application: commit `86573af` (`86573afe32a946b761f74c354acc389f2a7bac96`). This record does not change the product text of that revision.

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
- **Future Scope (v2+):** Nothing named.
- **Out of Scope / Non-Goals:** Choosing algorithms, modes, or key lengths here. A cloud key service. App-wide unlock is optional, per Miguel's decision of 2026-10-17 at 14:56 IST. It is no longer an open question. Encrypting the zip with a scheme this draft invents. The zip must not become a plaintext hole if the rest of the vault is encrypted; how the zip is protected follows the same at-rest outcome and is not a second design. Treating Drive sync as the only way content can leave the phone. Topic extraction (E11) is his recorded exception: readable content goes to a cloud AI. Drive still receives only ciphertext. This epic does not design that call.

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
- Key transport is his open question. Stories do not answer it. On 2026-10-17 at 14:56 IST he decided that a lost key means the content is lost, with no recovery, and that app lock is optional with a device-PIN fallback.

## Open Questions

- [ ] How keys reach a new or second device. (@mjbvilhena)
- Decided 2026-10-17 at 14:56 IST: if the key is lost, the content is lost, and there is no recovery.
- Decided 2026-10-17 at 14:56 IST: app lock is optional. The device PIN is the fallback when there are no biometrics.

## Draft stories

- US-35 Encrypt the vault at rest
- US-36 Sync only ciphertext
- US-37 Lock sensitive items with a biometric
