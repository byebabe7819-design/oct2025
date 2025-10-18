# Contributing

Top-level PR checklist (required for PRs that touch constitution, model weights, or legal packs):

1. Does the change modify `res/raw/jurisdiction_packs.json`, any model weights, or `core/Constitution.kt`? If yes:
   - Add an accompanying `./gradlew hashConstitution` output in the PR description showing the new SHA-512.
   - Add test vectors and a sample sealed PDF under `docs/` demonstrating the new behavior.
   - Ensure `connectedCheck` passes locally and in CI.

2. No network/telemetry: confirm you didn't add any network calls or telemetry libraries.

3. Determinism: include a unit test that demonstrates byte-identical outputs for a fixed deterministic input (use sample evidence fixtures under `app/src/test/resources`).

4. Review: tag `@core-team` and request a security review for any change touching `security/` or `reporting/`.

Other contribution notes: follow Kotlin style used in `app/src/main/...` and keep brains pure and stateless.
PR checklist — immediate safety gates

Before opening a pull request that touches the Constitution, model weights, jurisdiction packs, or any part of the consensus/quorum logic, ensure the following appear at the top of your PR description:

1. Short summary of change and why it’s needed.
2. Files touched that affect determinism, hashing, or PDF sealing (e.g. `res/raw/*`, `reporting/PDFGenerator.kt`, `core/Constitution.kt`).
3. Repro steps and a small evidence bundle (≤ 5 MB) demonstrating the change.
4. `./gradlew test` output snippet (or CI link) proving unit tests pass.
5. If you updated constitution/model/weights/jurisdiction packs, include the recomputed SHA-512(s) and the name of the task used to compute them (e.g. `hashConstitution`).

Do NOT open PRs that:
- introduce network calls, telemetry, or any runtime hot-update mechanism;
- add non-deterministic operations without canonical seeding (time, random); or
- change PDF sealing schema without updating `docs/sample_report.pdf` and re-running `assembleRelease` locally to validate hashes.

If you're unsure, open an issue first and include a short design note and the intended test vectors.

## Source of Truth (Kotlin)

- Kotlin (`.kt`) files under `app/src/main/java/com/verumomnis/**` are canonical for brain implementations and dispatcher wiring.
- Do **not** introduce duplicate Java and Kotlin versions of the same class.
- If refactoring from Java → Kotlin, delete the Java class in the same PR and keep the package/class name unique in the module.
- If a temporary no-op file is needed, mark it clearly and remove it before merge. We reject PRs containing placeholder no-op classes.

