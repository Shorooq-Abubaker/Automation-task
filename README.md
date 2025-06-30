# Automation Testing Task – UI Test Suite
This project contains an automated UI testing suite built using **Java**, **Selenium WebDriver**, and **TestNG**. It is designed to validate core functionality of the review creation workflow in a web application.

## Project Overview

The tests cover:

- Logging into the application
- Create Review
- Handling optional modals like upgrade prompts
- Invite Members
- Verifying expected UI behavior across different test inputs


## Setup Instructions
This README provides installation and configuration instructions for setting up Selenium WebDriver on Windows, and macOS using Java and ChromeDriver.

### Prerequisites

Before you begin, ensure the following are installed:

- Java SDK 11
- Maven 3.6+
- Google Chrome (latest stable)
- ChromeDriver compatible with your Chrome version
- IntillJ IDE

> ChromeDriver path is configured to run locally.

### 1. Install Selenium
### macOS Setup

#### A. Install Java
- Open Terminal
- Install Java (brew install openjdk@11)
- Add Java to your path (1- echo 'export PATH="/usr/local/opt/openjdk@11/bin:$PATH"' >> ~/.zshrc
  2- source ~/.zshrc)
- Verify java (java -version)

#### B. Install Maven
- Install Maven (brew install maven)
- Verify Maven if installed (mvn -v)

#### C. Download ChromeDriver
- Check your Chrome version:Chrome > About Google Chrome 
- Download the matching ChromeDriver:
https://chromedriver.chromium.org/downloads
- Make ChromeDriver Executable: chmod +x /usr/local/bin/chromedriver
- Move it to /usr/local/bin: mv ~/Downloads/chromedriver /usr/local/bin/


### Windows Setup

#### A. Install Java
- Download and install Java JDK 11:
  https://www.oracle.com/java/technologies/javase-jdk11-downloads.html
- Set the JAVA_HOME environment variable:
  - Control Panel > System > Advanced > Environment Variables 
  - Add JAVA_HOME pointing to JDK install path 
  - Add %JAVA_HOME%\bin to PATH
- Verify java (java -version)

#### B. Install Maven
- Download from: https://maven.apache.org/download.cgi
- Unzip and add bin to system PATH

#### C. Download ChromeDriver
- Check Chrome version:Menu > Help > About Google Chrome 
- Download matching ChromeDriver 
- Place it in a known folder (e.g., C:\WebDriver\bin)
- Add that folder to your system PATH

> Edit the Config.properties and set chrome_driver_path equals to
> 
>  For MAC  -->  /usr/local/bin/chromedriver
> 
>  For Windows -> C:\\WebDriver\\bin\\chromedriver.exe


### Troubleshooting

- Chrome version mismatch → Ensure ChromeDriver version matches your Chrome browser
- PATH not set → Add ChromeDriver's location to system PATH
- Permission denied → Run `chmod +x chromedriver` (Linux/macOS)

### How to Run the Tests
   
   from IntelliJ:
   - Open RayyanSystem project 
   - Navigate to config.properties and set your Rayyan (Email, Password) --Should be signed up
   - Navigate to "CreateReviewTest" class 
   - Right-click and select "Run", or open "CreateReview" then click on green triangle at the right top
   - OR Navigate to resources/testng.xml, Then click Run

### Notes
   - If the Upgrade dialog appears, the test will fail to reflect incorrect behavior.
   - A small data-driven component is included to verify behavior across multiple input sets.

### Author

    Shorooq Abubaker


This task was completed as part of a technical evaluation. For any clarifications, feel free to reach out.

