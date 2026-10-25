# Releasing (PFC fork)

This fork is VentureChat built for the PointForCraft server (Paper 1.20.6).
Versions are `<upstream version>-pfc.<N>`, for example `3.8.0-pfc.1`.

## Builds

- Every push to a branch other than `master` runs `.github/workflows/ci.yml`. It builds the plugin with the
  version `<revision>+<run number>.<short commit>` and uploads the jar as a workflow artifact, kept for 30 days.
  Pull requests show the check from the push to their branch; pull requests from forks are not built here.
- Pushes to `master` run `.github/workflows/dev-build.yml` instead, which does the same build and also
  refreshes the `dev-build` pre-release on the Releases page.

## Making a release

1. Make sure `revision` in `pom.xml` is `<version>-SNAPSHOT` of the version you are releasing.
2. Tag the commit and push the tag:
   ```
   git tag v3.8.0-pfc.1
   git push origin v3.8.0-pfc.1
   ```
3. The **Release** workflow builds `VentureChat-3.8.0-pfc.1.jar` and publishes a GitHub Release with generated notes.
   Tags containing `-rc`, `-alpha`, `-beta` or `-pre` are published as pre-releases.
4. Bump `revision` in `pom.xml` to the next snapshot (e.g. `3.8.0-pfc.2-SNAPSHOT`).
