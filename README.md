# Gherkium
Gherkium is a multi-module Java Maven project for running [Selenium](https://www.seleniumhq.org/) (and other kinds of) tests using [Cucumber](https://cucumber.io/). These frameworks are organized as reasonably distinct, isolated modules to promote abstraction and loose coupling, and provide for the separation of libraries and logic.

## Getting started
---
### Configuration
Gherkium depends on configuration values as listed in `gherkium-web/src/resources/readme.properties`. If running Gherkium as part of a continuous integration (CI) build, you can supply these values on the command line as arguments to the `mvn` command.

#### Local configuration
The configuration values can also be supplied when running locally by creating a `settings.properties` file. This file has been added to `.gitignore` to prevent local configurations from being committed to the repository. To use the configuration locally, make a copy of the `readme.properties` file, change the name of the copied file to `settings.properties`, and add the configuration values as appropriate.

#### Configuration values

##### driverPath
The absolute path to your browser-specific WebDriver implementations: CromeDriver, EdgeDriver, or GeckoDriver. Note that Gherkium has not been fully tested under Edge or Firefox/Gecko.

---

##### logFilePath
The absolute path to the location of the Selenium log. If the setting `isLoggingToFile` is true, a unique log file for each test run will be saved to this location.

---

##### isLoggingToFile
If `true` then a unique log file for each test run will be saved to the location specified in `logFilePath`.

---

##### isLoggingVerbose
If `true` then detailed information will be written to log files.

---

##### isScreenshotEnabled
If `true` then screenshots will be captured during the running of Selenium tests wherever the call to `WebPage.takeScreeshot` is made. Screenshots are watermarked with a date/time stamp.

---

##### screenShotPath
The absolute path to the location of screenshots. If the setting `isScreenshotEnabled` is true, screenshots taken during Selenium test runs will be saved to this location.

---

##### driverType
The active WebDriver type, which corresponds to the `WebDriverType` enum in the `gherkium-web` module: CHROME, CHROME\_HEADLESS, EDGE, or FIREFOX. Use CHROME\_HEADLESS if running as part of CI. Note that Gherkium has not been fully tested under Edge or Firefox/Gecko.

---

##### pageTimeout
The amount of time, in seconds, that a Selenium test should wait to complete a task before timing out. For example, if a certain web element cannot be found on a page, the test will end after the number of seconds specified in this setting.

---

##### operatingSystem
The operating system on which Gherkium is running. If `windows`, Gherkium will look for a WebDriver implementation with an `.exe` extension.

---

## Modules

### gherkium-web
---
Module that contains selenium code

[docs in progress]
