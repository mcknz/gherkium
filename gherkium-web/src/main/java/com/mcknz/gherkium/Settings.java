package com.mcknz.gherkium;

import com.mcknz.gherkium.web.WebDriverType;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

public class Settings {

  private final Properties props;
  private String driverPath;
  private WebDriverType driverType;
  private int pageTimeout = -1;
  private String operatingSystem;
  private String screenshotPath;
  private Boolean isScreenshotEnabled = null;
  private Boolean isLoggingVerbose = null;
  private Boolean isLoggingToFile = null;
  private String logFilePath;

  public Settings() {
    props = new Properties();
    loadResourceSettings(Thread.currentThread().getContextClassLoader());
  }

  public String getDriverPath() {
    if(driverPath == null) {
      driverPath = getProperty("driverPath");
    }
    return driverPath;
  }

  public WebDriverType getDriverType() {
    if(driverType == null) {
      driverType = WebDriverType.valueOf(getProperty("driverType"));
    }
    return driverType;
  }

  public int getPageTimeout() {
    if(pageTimeout == -1) {
      pageTimeout = Integer.parseInt(getProperty("pageTimeout"));
    }
    return pageTimeout;
  }

  public boolean isWindows() {
    return getOperatingSystem().toLowerCase().contains("windows");
  }

  private String getOperatingSystem() {
    if(operatingSystem == null) {
      operatingSystem = getProperty("operatingSystem");
    }
    return operatingSystem;
  }

  public String getScreenshotPath() {
    if(screenshotPath == null) {
      screenshotPath = getProperty("screenshotPath");
      if(screenshotPath == null) {
        screenshotPath = "";
      }
    }
    return screenshotPath;
  }

  public boolean getIsScreenshotEnabled() {
    if(isScreenshotEnabled == null) {
      String enabled = getProperty("isScreenshotEnabled");
      try {
        isScreenshotEnabled = Boolean.parseBoolean(enabled);
      } catch(Exception ex) {
        isScreenshotEnabled = false;
      }
    }
    return isScreenshotEnabled;
  }

  public String getLogFilePath() {
    if(logFilePath == null) {
      logFilePath = getProperty("logFilePath");
      if(logFilePath == null) {
        logFilePath = "";
      }
    }
    return logFilePath;
  }

  public boolean getIsLoggingVerbose() {
    if(isLoggingVerbose == null) {
      String verbose = getProperty("isLoggingVerbose");
      try {
        isLoggingVerbose = Boolean.parseBoolean(verbose);
      } catch(Exception ex) {
        isLoggingVerbose = false;
      }
    }
    return isLoggingVerbose;
  }

  public boolean getIsLoggingToFile() {
    if(isLoggingToFile == null) {
      String loggingToFile = getProperty("isLoggingToFile");
      try {
        isLoggingToFile = Boolean.parseBoolean(loggingToFile);
      } catch(Exception ex) {
        isLoggingToFile = false;
      }
    }
    return isLoggingToFile;
  }

  private void loadResourceSettings(ClassLoader loader) {
    Properties p = new Properties();
    try {
      final Enumeration<URL> systemResources =
        (loader == null ? ClassLoader.getSystemClassLoader() : loader)
          .getResources("settings.properties");
      while (systemResources.hasMoreElements()) {
        p.load(systemResources.nextElement().openStream());
      }
    } catch (IOException ex) {
      // ignore if running as part of CI process
      //  because all properties will be supplied externally
      System.out.println(
        "WARN: if you are working in a local environment, make sure to copy/rename " +
          "the properties file name from 'readme.properties' to 'settings.properties'. " +
          "This error can be ignored when running from a CI environment."
      );
    }
    props.putAll(p);
  }

  protected String getProperty(String key) {
    String property = System.getProperty(key);
    if(property == null) {
      return props.getProperty(key);
    }
    return property;
  }
}