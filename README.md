Basic Continuous Integration with GitHub Actions for Android Developers

https://www.youtube.com/watch?v=SVLyADb8rQc
----------------------------------------------------

- Crate a new Android project
- Create new Repository "GitHubActionDemo" in GitHub
- Enable VCS in Android Studio
- Add SSH Key in Git Hub User settings. SSH Key generated using Git GUI
- Commit Android code
- Push to remote repo. URL will be of your GitHubActionDemo (SSH URL)

- Now for CI/CD. Go to GitHub Repository Page
- Select your Repo (e.g. GitHubActionDemo)
- Selection Action Tab
- Check "Simple workflow" in suggested for this repository
- Click configure.
- You will see blank.yml file under GitHubActionDemo.github/workflows folder
- rename to main.yml file
---------------------------------------
main.yml
------------------------------------------
- Put this file in MyGitActionsDemo-> .github->main.yml
------------------------------------------

# This is a basic workflow to help you get started with Actions

name: Android CI

# Controls when the workflow will run
on:
# Triggers the workflow on push or pull request events but only for the "main" branch
push:
branches: [ "master" ]
pull_request:
branches: [ "master" ]

# Allows you to run this workflow manually from the Actions tab
workflow_dispatch:

# A workflow run is made up of one or more jobs that can run sequentially or in parallel
jobs:
# This workflow contains a single job called "build"
build:
# The type of runner that the job will run on
runs-on: ubuntu-latest

    # Steps represent a sequence of tasks that will be executed as part of the job
    steps:
      # Checks-out your repository under $GITHUB_WORKSPACE, so your job can access it
      - name: Check out
        uses: actions/checkout@v3.3.0

      # Set Up Java JDK
      - name: Set Up Java JDK
        uses: actions/setup-java@v3
        with:
          java-version: '18'
          distribution: 'temurin'
          cache: gradle

      # Build with Gradle
      - name: Build with gradle
        run: ./gradlew build


      # Build APK from given path
      - name: Upload a Build Artifact or APK file
        uses: actions/upload-artifact@v3.1.2
        with:
          name: app
          path: app/build/outputs/apk/debug/app-debug.apk

      # Runs a single command using the runners shell
      - name: Run a one-line script
        run: echo Hello, world!

      # Runs a set of commands using the runners shell
      - name: Run a multi-line script
        run: |
          echo Add other actions to build,
          echo test, and deploy your project.