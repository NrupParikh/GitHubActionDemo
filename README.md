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
--------------------------------------
- To invite the SlackCICDApp in Slack type /invite @SlackCICDApp in slack channel.
- TO add scope go to slack api [api.slack.com/apps/SlackCICDApp]
- Add required permission and scope in OAuth & Permissions under Features section
- Select scopes for Bot Token and User Token

- For Bot Token -> enable below scopes
  - chat:write
  - files:read
  - files:write
  
- For User Token-> enable below scopes
  - channels:history
  - channels:read
  - channels:write
  - chat:write
  - files:read
  - files:write
  - groups:history
  - groups:read
  - groups:write
  - identify
  - im:read
  - mpim:history
  - mpim:read
  - reminders:write
  - search:read
  
# Now in https://api.slack.com/
- We required SLACK_BOT_TOKEN, SLACK_WEBHOOK_URL and SLACK_CHANNEL_ID which we put in our Git Repo's Secret for communicate between Git and Slack app.

- generate Bot User OAuth Token
- generate User OAuth Token [SLACK_BOT_TOKEN]
- get SLACK_WEBHOOK_URL : For that got to slack app directory-> search Incoming Webhook -> select-> Request config-> select channel Ex. #developement -> Generate Webhook URL->Save
- get SLACK_CHANNEL_ID : For that go to your Slack channel [https://app.slack.com/] under your channel ex. #developement in dropdown you find Channel ID
- Define all 3 KEYS in your main.yml file to configure with slack channel and app.


- For set Keystore in Github follow below steps.
- Go to repository->Settings->Secrets and Variables->Action. Add below secrets
- KEYSTORE : Base64 String of Your Key-store file.
- RELEASE_KEY_ALIAS : Your KEY ALIAS
- RELEASE_KEY_PASSWORD : Your KEY PASSWORD
- RELEASE_STORE_PASSWORD : Your STORE PASSWORD
- So, this parameter [KEYSTORE] used in our main.yml file to Decode the keystore.
---------------------------------------
main.yml
------------------------------------------
- Put this file in MyGitActionsDemo-> .github->main.yml
------------------------------------------
```
# This is a basic workflow to help you get started with Actions

name: Android CI for Master Branch

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
      # Decode Keystore
      - name: Decode Keystore
        id: decode_keystore
        uses: timheuer/base64-to-file@v1.2
        with:
          fileName: 'cert/my_github_action_keystore.jks'
          encodedString: ${{ secrets.KEYSTORE }}

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

      - name: Grant execute permission for gradlew
        run: chmod +x gradlew

      # Build with Gradle
      - name: Build prod
        run: ./gradlew assembleRelease
        env:
            SIGNING_KEY_ALIAS: ${{ secrets.SIGNING_KEY_ALIAS }}
            SIGNING_KEY_PASSWORD: ${{ secrets.SIGNING_KEY_PASSWORD }}
            SIGNING_STORE_PASSWORD: ${{ secrets.SIGNING_STORE_PASSWORD }}


      # Build APK from given path
      - name: Upload
        uses: actions/upload-artifact@v3.1.2
        with:
          name: app-myGitActionsDemo-release.apk
#          path: app/build/outputs/apk/debug/app-debug.apk
          path: app/build/outputs/apk/myGitActionsDemo/release/app-myGitActionsDemo-release.apk
      
      # Sending Push message in Slack
      - name: Send GitHub Action trigger data to Slack workflow
        id: slack
        uses: act10ns/slack@v2.0.0
        with:
            status: ${{ job.status }}
            message: 'Push update in Slack from master branch'
        env:
          SLACK_WEBHOOK_URL: ${{ secrets.SLACK_WEBHOOK_URL }}
      
      # Send APK on slack
      - name: Slack File Upload
        uses: MeilCli/slack-upload-file@v3
        with:
          slack_token: ${{ secrets.SLACK_BOT_TOKEN }}
          channel_id: ${{ secrets.SLACK_CHANNEL_ID }}
#          file_path: app/build/outputs/apk/debug/app-debug.apk
          file_path: app/build/outputs/apk/myGitActionsDemo/release/app-myGitActionsDemo-release.apk
          initial_comment: 'Release APK for MyGitActionDemo'
    

    
```

# Error: ./gradlew: Permission denied
------------------------------------------
- If you find issue like gradlew: Permission denied such as
  env:
  JAVA_HOME: /opt/hostedtoolcache/Java_Temurin-Hotspot_jdk/18.0.2-101/x64
  JAVA_HOME_18_X64: /opt/hostedtoolcache/Java_Temurin-Hotspot_jdk/18.0.2-101/x64
  /home/runner/work/_temp/287033bc-1abb-4124-9ede-76484a1c7fa2.sh: line 1: ./gradlew: Permission denied
  Error: Process completed with exit code 126.
------------------------------------------
# Solution is Put below code in main.yml file

```
- name: Grant execute permission for gradlew
  run: chmod +x gradlew
```

# Useful Links

### Slack
https://app.slack.com/

### Slack API
https://api.slack.com/

### Slack App Directory
https://androidchat.slack.com/intl/en-in/apps

### Webhook URL
https://www.svix.com/resources/guides/how-to-get-slack-webhook-url/#:~:text=In%20the%20%22Slack%20App%20Directory,Bar%20and%20click%20on%20it.

## Build Variants based on productFalvors

![image](https://user-images.githubusercontent.com/108717119/224921098-a8e1136a-4ea8-4c20-879f-28d5fff30fe9.png)

![image](https://user-images.githubusercontent.com/108717119/224922377-6ad5284c-4ac5-470a-8cb7-aa369ac61f8f.png)

![image](https://user-images.githubusercontent.com/108717119/224922932-bce45ecd-2117-46ee-8836-170108df44ee.png)

