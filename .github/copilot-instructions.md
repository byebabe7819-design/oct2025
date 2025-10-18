## Quick orientation for AI coding agents

This repository implements "Verum Omnis": a sealed, stateless Android forensic engine (9-brain architecture) that produces evidence-grade, SHA-512-locked PDF/A-3B reports. The guidance below focuses on immediately actionable knowledge an automated coding agent needs to be productive in this codebase.

### High-level architecture (the why)
- App is Android-first (Kotlin). Key packages live under `app/src/main/java/com/verumomnis/`.
- Major components:
  - `core/` — constitutional rules, dispatcher, and orchestration. See `Constitution.kt` and `BrainDispatcher.kt`.
  - `brains/` — B1..B9 modular analyzers (e.g. `ContradictionBrain.kt`, `DocumentImageBrain.kt`). Each brain returns structured findings; quorum logic is in `core`.
  - `security/` — integrity checks, zeroization utilities (e.g. `IntegrityValidator.kt`, `MemoryWiper.kt`).
  - `reporting/` — PDF generation and sealing (e.g. `PDFGenerator.kt`). Uses iText7 and QR/hash footer conventions.

### What matters for code changes
- Determinism: outputs must be byte-identical for identical inputs. Avoid non-deterministic APIs, random seeds, or time-based values unless explicitly canonicalised in `core/Constitution.kt`.
- No network, no runtime config toggles, no feature flags. Any change that introduces runtime network calls or hot-updated hashes will break build/time validation and likely be rejected.
- Constitution & model weights are hash-pinned at compile time. Builds fail if pinned SHA-512s or jurisdiction pack schemas change. See `res/raw/jurisdiction_packs.json` and the `hashConstitution` gradle task referenced in README.

### Build, test and debug (project-specific commands)
- Gradle is the canonical build: use offline mode when appropriate. Example tasks referenced in README:
  - `./gradlew assembleDebug` or `assembleRelease` (release will enforce test + hash gates)
  - `./gradlew connectedCheck` — runs 100+ synthetic fraud scenarios on device/emulator
  - `./gradlew generateTestMatrix` — produces `error-matrix.html` used by QA
  - `./gradlew hashConstitution` — prints expected SHA-512s; used to validate pinned artefacts
- Debugging on device: use `adb logcat` with tag `VerumOmnis` and setprop as suggested in README: `adb shell setprop log.tag.VerumOmnis VERBOSE`.

### Patterns and conventions to follow
- Brain contract: each brain returns a deterministic, serializable findings object (see sample JSON in README). New brains must be pure functions of inputs and must not mutate global state.
- Quorum enforcement lives in `core/BrainDispatcher.kt` and constitutional checks in `core/Constitution.kt`. Changes to quorum logic must be accompanied by tests showing determinism and quorum acceptance/rejection scenarios.
- PDF sealing: `reporting/PDFGenerator.kt` produces PDF/A-3B, places a SHA-512 in the footer and encodes it as a QR. Do not change the PDF/A profile or footer schema without updating tests and sample PDFs under `docs/`.
- Zeroization: all temporary artefacts must be wiped via `security/MemoryWiper.kt`. Add/remove temporary files only through provided helpers.

### Integration points & external deps
- iText7 for PDF/A-3B (see README gradle snippet). Modifying PDF library versions requires revalidating sample_report.pdf and the QR hash.
- TensorFlow Lite used for on-device inference. Models are pinned at build; weights are read-only and hash-verified.
- Jurisdiction packs are JSON files under `res/raw/` and are hash-validated at build-time. Schema changes require an accompanying update to the `hashConstitution` pipeline.

### Concrete examples (where to look)
- To add a new analyzer: follow structure under `brains/` — implement the findings DTO, unit tests, and register in `core/BrainDispatcher.kt`.
- Example stubs are provided under `app/src/main/java/com/verumomnis/brains/`:
  - `BrainContract.kt` — minimal `Findings` DTO and `Brain` interface.
  - `SampleContradictionBrain.kt` — deterministic example implementation (use as a template).
  - Unit test scaffold: `app/src/test/java/com/verumomnis/brains/SampleContradictionBrainTest.kt`.

### Registering a brain (example)
When adding a new brain, register it in `core/BrainDispatcher.kt`. Minimal registration pattern:

```kotlin
// in BrainDispatcher.kt (pseudocode)
val brains: List<Brain> = listOf(
    SampleContradictionBrain(),
    // add NewBrain() here
)

// dispatch
val findings = brains.map { it.analyze(inputs) }
```

Keep registration static and deterministic (no runtime discovery or reflection-based plugin loading).
- To add a new sealed field to the PDF: update `reporting/PDFGenerator.kt` and `docs/sample_report.pdf` and run `./gradlew assembleRelease` locally to ensure hash gates pass.
- To debug a failing constitution hash: run `./gradlew hashConstitution` and compare the output against pinned values in build config.

### Tests and CI expectations
- All local tests referenced by `connectedCheck` and `generateTestMatrix` must pass for release builds. The project uses strict gates: builds fail on SHA mismatch and on any network/telemetry additions.

### Quick-verify checklist (Gradle)

If you're running verification locally or in CI, here's a tight, copy-paste friendly checklist (PowerShell first, then bash) and a short failure triage.

## Windows (PowerShell — paste as one block)

```powershell
# 1) JDK + PATH
$env:JAVA_HOME = (Get-Command javac).Source | Split-Path -Parent | Split-Path -Parent
java -version

# 2) (Optional) Android SDK for connectedCheck
# sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

# 3) From your Android/Kotlin project root (NOT this .NET repo)
./gradlew --version
./gradlew clean

# 4) Unit tests (no device needed)
./gradlew test --no-build-cache --warning-mode all

# 5) Device/emulator tests (needs running device: `adb devices`)
./gradlew connectedCheck

# 6) (If you added a hash task) Constitution integrity
./gradlew hashConstitution
```

## macOS / Linux (bash)

```bash
# 1) JDK
export JAVA_HOME="$(dirname "$(dirname "$(readlink -f "$(command -v javac)")")")"
java -version

# 2) (Optional) Android SDK for connectedCheck
# yes | sdkmanager "platform-tools" "platforms;android-34" "build-tools;34.0.0"

# 3) From your Android/Kotlin project root
./gradlew --version
./gradlew clean

# 4) Unit tests (no device needed)
./gradlew test --no-build-cache --warning-mode all

# 5) Device/emulator tests
./gradlew connectedCheck

# 6) (If present) Constitution integrity task
./gradlew hashConstitution
```

### What “good” looks like

* **`./gradlew test`**

  * Tail of output:

    ```
    > Task :app:testDebugUnitTest
    BUILD SUCCESSFUL in 10s
    42 actionable tasks: 42 executed
    ```
  * Test summary appears under `app/build/test-results/testDebugUnitTest/` and HTML at `app/build/reports/tests/testDebugUnitTest/index.html`.

* **`./gradlew connectedCheck`**

  * Device listed via `adb devices`
  * Tail of output:

    ```
    > Task :app:connectedDebugAndroidTest
    BUILD SUCCESSFUL in 1m 12s
    ```
  * Report: `app/build/reports/androidTests/connected/index.html`.

* **`./gradlew hashConstitution`** (if you wire it)

  * Prints the computed SHA-512 that must match the pinned value; non-zero exit on mismatch.

### Fast failure triage

* **`Could not find com.android.tools.build:gradle:…`**
  → Gradle can’t download. Ensure internet or use your local mirror; for CI, add Gradle cache + Google Maven.

* **`No connected devices!`** on `connectedCheck`
  → Start an emulator (`AVD Manager`) or plug in a device with USB debugging.

* **`Compilation failed: Unresolved reference …`** in brain stubs
  → The package names or module deps in your project don’t match the stub. Ensure:

  * `com.verumomnis.brains` and `com.verumomnis.core` exist,
  * `api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.x")` if you use `suspend`,
  * module with `BrainDispatcher.kt` depends on module with brain classes.

* **Constitution hash mismatch** (if you port the .NET guard to Kotlin)
  → Recompute SHA-512 of `constitution_manifest.json` and update the pinned constant before building.

### Optional: tiny Gradle task to print the constitution hash (drop into your `build.gradle.kts`)

```kotlin
import java.security.MessageDigest

tasks.register("hashConstitution") {
    group = "verification"
    doLast {
        val f = file("app/src/main/res/raw/constitution_manifest.json") // adjust path
        val bytes = f.readBytes()
        val sha = MessageDigest.getInstance("SHA-512").digest(bytes)
        val hex = sha.joinToString("") { "%02x".format(it) }
        println("Constitution SHA-512: $hex")
    }
}
```

### Safety rules for AI edits
- Never introduce network calls, telemetry, or external telemetry libraries.
- Preserve determinism: seed any randomness deterministically, or avoid randomness entirely.
- Do not modify constitution hashes, model weights, or jurisdiction packs unless the change includes updated pinned hash files and verified sample PDFs.

If anything in the instructions is unclear or you need file-level examples (e.g. a brain implementation stub), tell me which area to expand and I will iterate.
