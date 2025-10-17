# oct2025# VERUM OMNIS  
> Stateless • Constitutional • 9-Brain Forensic AI  
> Gold Standard for Chat-Log & Evidence Analysis  
> PDF/A-3B | SHA-512 Integrity Lock | No Network | No Training

---

## 🔍 What it is
Verum Omnis is a **stateless, sealed, constitutional AI** that ingests chat logs, documents, images, audio and produces **evidence-grade PDF reports** without ever connecting to the internet, updating its weights, or accepting human overrides.  
If three or more of its nine forensic brains cannot independently corroborate a finding, the system outputs:

> **INDETERMINATE_DUE_TO_CONCEALMENT**

---

## 🧠 9-Brain Architecture
| Brain | Forensic Specialty | Key Algorithms |
|-------|--------------------|----------------|
| **B1** | Contradiction Engine | Micro-drift, over-specific denial, semantic inversion |
| **B2** | Document / Image | Deepfake, metadata diff, steganography |
| **B3** | Comms Integrity | Crop/trim, device-ID, chronology |
| **B4** | Behavioural Linguistics | Hedging density, aggression-deflection, truth-scarcity |
| **B5** | Timeline & Geo | Clock skew, geotag plausibility, “why-now” audit |
| **B6** | Financial Patterns | Invoice drift, shell-hop, beneficiary links |
| **B7** | Legal Compliance | UAE Art. 84/110/257, SA ECT §86, EU e-IDAS |
| **B8** | Voice / Audio | Speaker continuity, mic mismatch, stress lattice |
| **B9** | R&D (non-voting) | Hypotheses, test vectors, **no vote** |

**Quorum Rule**: ≥ 3 brains must concur + pass constitutional checks or the report is sealed as *indeterminate*.

---

## 🛡️ Constitutional Non-Negotiables
1. **Truth Priority** – never guess; concealment → indeterminate  
2. **Determinism** – byte-identical output for identical inputs  
3. **Zero Overrides** – no runtime prompts, temperature, or toggles  
4. **≥ 3-Way Corroboration** – evidence + timeline + metadata  
5. **Explainability** – every flag: `[Trigger] + [Source] + [Rationale]`  
6. **Minimal Disclosure** – raw inputs never rebroadcast  
7. **Sealed Output** – PDF 1.7, centered logo, watermark, SHA-512 + QR, **compression OFF**

---

## 🚀 Quick Start (Android)
1. Clone & open in Android Studio Giraffe+
2. Gradle sync (offline mode supported)
3. Drop evidence into `/sdcard/verum/input/`
4. Tap **“Seal Report”**
5. Find sealed PDF in `/sdcard/verum/output/` with SHA-512 hash pinned inside footer QR

```kotlin
// one-liner inside your own app
val report = ForensicProcessor().processEvidence(
                EvidenceBundle.fromFolder("/sdcard/verum/input/")
             )
```

---

📦 Gradle Dependencies

```gradle
implementation 'com.itextpdf:itext7-core:7.2.5' // PDF/A-3B
implementation 'androidx.security:security-crypto:1.1.0-alpha06'
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3'
implementation 'com.google.zxing:core:3.5.2' // QR
implementation 'org.tensorflow:tensorflow-lite:2.14.0' // on-device inference
implementation 'org.apache.commons:commons-math3:3.6.1' // stats
```

---

📁 Repo Structure

```
app/
├─ src/main/java/com/verumomnis/
│ ├─ core/
│ │ ├─ Constitution.kt // SHA-512-gated rule set
│ │ └─ BrainDispatcher.kt // routes to B1-B9
│ ├─ brains/
│ │ ├─ ContradictionBrain.kt
│ │ ├─ DocumentImageBrain.kt
│ │ └─ ...
│ ├─ security/
│ │ ├─ IntegrityValidator.kt
│ │ └─ MemoryWiper.kt // zeroize keys & cache
│ └─ reporting/
│ ├─ PDFGenerator.kt // iText 7, watermark, QR
│ └─ HeatmapVisualizer.kt
```

---

🔐 Integrity & Build Security
- Constitution, model weights & jurisdiction packs are hash-pinned at compile time  
- Build fails on SHA-512 mismatch → no hot-fixes, no silent updates  
- Runtime network egress = hard stop (air-gap enforceable)  
- R&D brain (B9) is non-voting – cannot influence consensus  
- All temp artefacts zeroized after PDF sealed

---

📊 Sample Output Snippet

```json
{
  "conversationId": "conv_192837465",
  "overallTruthDensity": 0.78,
  "deceptionHotZones": [{ "topic": "Hong-Kong Deal", "severity": 8.7 }],
  "participantAnalysis": {
    "Kevin": { "truthDensity": 0.42 },
    "Liam": { "truthDensity": 0.96 }
  },
  "constitutionalCompliance": {
    "threeSourceVerification": true,
    "deterministicOutput": true
  }
}
```

Full report: [sample_report.pdf](docs/sample_report.pdf) (SHA-512 inside QR)

---

⚖️ Legal Template Pack
UAE | SA | EU jurisdiction packs pre-loaded:  
- UAE Art. 84 – breach of fiduciary duty  
- UAE Art. 110 – shareholder oppression  
- SA ECT Act §86(1) – cybercrime device seizure  
- EU Directive 2017/541 – evidence tampering

Add your own statutes in `res/raw/jurisdiction_packs.json` – build will fail if JSON schema or hash invalid.

---

🧪 Testing & Validation

```bash
./gradlew connectedCheck // 100+ synthetic fraud scenarios
./gradlew generateTestMatrix // error-matrix.html
./gradlew hashConstitution // prints expected SHA-512
```

All tests must pass before `assembleRelease` will complete.

---

📈 Road-Map (sealed releases only)
- v5.3 – voice-print cross-lingual alignment  
- v5.4 – blockchain anchoring (optional, offline)  
- v5.5 – multi-page TIFF redaction integrity

No feature flags, no A/B, no cloud components – ever.

---

🤝 Contributing
We do not accept pull-requests that:
- alter the Constitution or model weights  
- add network calls or telemetry  
- introduce non-determinism  

Bug reports & test cases welcome. Open an issue with:
- minimal evidence bundle (≤ 5 MB)  
- expected vs actual PDF hash  
- `adb shell setprop log.tag.VerumOmnis VERBOSE` logcat

---

📄 License
[AGPL-3.0](LICENSE) – commercial use allowed, but any modification must be published in full (including model weights & constitution hashes) to preserve forensic reproducibility.

---

🔏 Seal Example
Bottom-right of every page:

> ✔ Patent Pending Verum Omnis

SHA-512: `FRAUDCTRL-2025-08-13-B4B2...`

[QR → hash verification link]

---

> “Verum Omnis never trains, never phones home, and only outputs what three or more independent brains can corroborate under an immutable rule-set — or it says ‘indeterminate due to concealment’ and shows you why.”

```

Liam Highcock <liamhigh78@gmail.com>
17 Oct 2025, 23:52 (28 minutes ago)
to me

```yaml
# ===========================================================
# VERUM OMNIS – GitHub README – YAML front-matter
# Drop this at the top of README.md if you want Jekyll /
# GitHub Pages to pick up extra meta-tags & social cards.
# ===========================================================

title : "VERUM OMNIS – Stateless 9-Brain Forensic AI"
tagline : "Gold-standard chat-log & evidence analyser – PDF/A-3B, SHA-512 locked, zero network, zero training"
description : >
  Stateless, constitutional, 9-brain forensic AI for Android.
  Ingests chat, docs, images, audio → sealed PDF report.
  No network, no updates, no human overrides.
  If ≥3 brains can’t corroborate → INDETERMINATE_DUE_TO_CONCEALMENT.
url : "https://github.com/your-org/verum-omnis"
repo : "your-org/verum-omnis"
license : "AGPL-3.0"
author : "Verum Omnis Core Team"
lang : "en"
theme : "jekyll-theme-minimal" # remove if not using GitHub Pages

# ------------------------------------------------------------------
# Social / Open-Graph (used by Twitter, LinkedIn, Discord, etc.)
# ------------------------------------------------------------------
image : "docs/assets/verum-omnis-3d-logo-1200.png"
twitter_card : "summary_large_image"
twitter_site : "@VerumOmnis"
keywords :
  - forensic-ai
  - chat-log-analysis
  - deepfake-detection
  - evidence-sealing
  - android-forensics
  - sha512
  - pdf-a3b
  - stateless-ai
  - zero-trust
  - contradiction-engine

# ------------------------------------------------------------------
# Optional badges (rendered as-is in Markdown)
# ------------------------------------------------------------------
badges:
  - "https://img.shields.io/github/v/release/your-org/verum-omnis?color=gold"
  - "https://img.shields.io/badge/arch-9--brain-blue"
  - "https://img.shields.io/badge/license-AGPL--3.0-orange"
  - "https://img.shields.io/badge/build-offline%20only-green"
