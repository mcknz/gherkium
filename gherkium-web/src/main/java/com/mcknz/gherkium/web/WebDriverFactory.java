package com.mcknz.gherkium.web;

import com.mcknz.gherkium.Settings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.io.File;

class WebDriverFactory {

  @SuppressWarnings("SameParameterValue")
  WebDriver getDriver(Settings settings) {
    return getWebDriver(settings);
  }

  private WebDriver getWebDriver(Settings settings) {
    switch (settings.getDriverType()) {
      case CHROME:
        return getChromeDriver(settings,false);
      case CHROME_HEADLESS:
        return getChromeDriver(settings,true);
      case EDGE:
        return getEdgeDriver(settings);
      case FIREFOX:
        return getFireFoxDriver(settings);
    }
    throw new IllegalArgumentException("Unable to create Driver for the specified type.");
  }

  private WebDriver getFireFoxDriver(Settings settings) {
    GeckoDriverService service =
      new GeckoDriverService
        .Builder()
        .usingDriverExecutable(
          new File(
            settings.getDriverPath(),
            getDriverFile(settings,"geckodriver")
          )
        )
        .usingAnyFreePort()
        .build();

    return new FirefoxDriver(service);
  }

  private WebDriver getEdgeDriver(Settings settings) {
    EdgeDriverService service =
      new EdgeDriverService
        .Builder()
        .usingDriverExecutable(
          new File(
            settings.getDriverPath(),
            getDriverFile(settings, "MicrosoftWebDriver")
          )
        )
        .usingAnyFreePort()
        .build();

    return new EdgeDriver(service);
  }

  private WebDriver getChromeDriver(Settings settings,
                                    boolean isHeadless) {
    ChromeOptions options = new ChromeOptions();
    if(isHeadless) {
      options.addArguments("--headless");
    }
    ChromeDriverService.Builder builder =
      new ChromeDriverService
        .Builder()
        .usingDriverExecutable(
          new File(
            settings.getDriverPath(),
            getDriverFile(settings,"chromedriver")
          )
        )
        .usingAnyFreePort()
        .withVerbose(settings.getIsLoggingVerbose());

    if(settings.getIsLoggingToFile()) {
      String logFileName = String.format("%s%sdriverLog-%s.log",
        settings.getLogFilePath(),
        File.separator,
        System.currentTimeMillis());
      builder.withLogFile(new File(logFileName));
    }

    return new ChromeDriver(builder.build(), options);
  }

  private String getDriverFile(Settings settings,
                               String name) {
    if(settings.isWindows()) {
      return name + ".exe";
    }
    return name;
  }
}