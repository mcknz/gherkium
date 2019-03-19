package com.mcknz.gherkium.web;

import com.mcknz.gherkium.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

abstract public class WebPage {

  private final WebDriver driver;
  private final Settings settings;
  private final AbstractSite site;
  private final Screenshot screenshot;

  protected WebPage(AbstractSite site) {
    driver = Driver.get();
    settings = Driver.getSettings();
    this.site = site;
    this.screenshot = new Screenshot(driver, this, settings);
  }

  public boolean isOnPage() { return getStartingUrl().equals(getCurrentUrl()); }

  public void waitForPageToLoad() {
    getWait().until(ExpectedConditions.urlToBe(getStartingUrl()));
  }


  protected String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  protected abstract String getStartingUrlSuffix();

  protected void navigateToStartingUrl() {
    driver.get(getStartingUrl());
  }

  @SuppressWarnings("unused")
  protected void switchToDefaultContent() {
    driver.switchTo().defaultContent();
  }

  @SuppressWarnings("unused")
  protected void switchToFrame(String elementId) {
    getWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementId));
  }

  @SuppressWarnings("unused")
  protected void switchToParentFrame() {
    driver.switchTo().parentFrame();
  }

  @SuppressWarnings("unused")
  protected void switchToNewWindow() {
    String parent = driver.getWindowHandle();
    boolean isLookingForWindow = true;
    int tries = 0;
    while(isLookingForWindow) {
      tries++;
      for (String child : driver.getWindowHandles()) {
        if (!child.equals(parent)) {
          driver.switchTo().window(child);
          isLookingForWindow = false;
        }
      }
      if(tries > 10000) {
        break;
      }
    }
  }


  protected void enterText(String elementId, String text) {
    waitUntilElementClickable(elementId).sendKeys(text);
  }

  protected void enterTextByXPath(String xPath, String text) {
    waitUntilElementClickableByXPath(xPath).sendKeys(text);
  }

  @SuppressWarnings("unused")
  protected void enterTextByClass(String className, String text) {
    waitUntilElementClickableByClass(className).sendKeys(text);
  }

  protected boolean hasText(String elementId, String text) {
    return doesElementContainText(By.id(elementId), text);
  }

  @SuppressWarnings("unused")
  protected void selectText(String selectId, String text) {
    waitUntilSelectElementClickable(selectId).selectByVisibleText(text);
  }

  @SuppressWarnings("unused")
  protected String getText(String elementId) {
    return waitUntilElementClickable(elementId).getText();
  }

  @SuppressWarnings("unused")
  protected String getTextByXPath(String xPath) {
    return waitUntilElementClickableByXPath(xPath).getText();
  }

  @SuppressWarnings("unused")
  protected String getTextByClass(String className) {
    return waitUntilElementClickableByClass(className).getText();
  }


  protected void click(String elementId) {
    waitUntilElementClickable(elementId).click();
  }

  @SuppressWarnings("SameParameterValue")
  protected void clickByXPath(String xPath) {
    waitUntilElementClickableByXPath(xPath).click();
  }

  @SuppressWarnings("unused")
  protected void clickLinkByText(String linkText) {
    Objects.requireNonNull(waitUntilElementClickable(By.linkText(linkText))).click();
  }

  @SuppressWarnings("unused")
  protected void clickLinkByPartialLinkText(String linkText) {
    Objects.requireNonNull(waitUntilElementClickable(By.partialLinkText(linkText))).click();
  }

  @SuppressWarnings("unused")
  protected void takeScreenshot() {
    screenshot.take(Thread.currentThread().getStackTrace()[2].getMethodName());
  }


  protected String getStartingUrl() {
    return site.getUrl() + getStartingUrlSuffix();
  }

  private WebElement waitUntilElementClickableByClass(String className) {
    return waitUntilElementClickable(By.className(className));
  }

  private WebElement waitUntilElementClickableByXPath(String xPath) {
    return waitUntilElementClickable(By.xpath(xPath));
  }

  private Select waitUntilSelectElementClickable(String selectId) {
    return new Select(waitUntilElementClickable(selectId));
  }

  private WebElement waitUntilElementClickable(String elementId) {
    return waitUntilElementClickable(By.id(elementId));
  }

  private WebElement waitUntilElementClickable(By locator)
  {
    try
    {
      return getWait().until(ExpectedConditions.elementToBeClickable(locator));
    }
    catch (NoSuchElementException ex)
    {
      System.out.print("Element with locator: '" + locator.toString() + "' was not found in current context page.");
      return null;
    }
  }

  private boolean doesElementContainText(By locator, String text)
  {
    try
    {
      return getWait().until(ExpectedConditions.attributeContains(locator, "textContent", text));
    }
    catch (NoSuchElementException ex)
    {
      System.out.print("Element with locator: '" + locator.toString() + "' was not found in current context page.");
      return false;
    }
  }

  private WebDriverWait getWait() {
    WebDriverWait wait = new WebDriverWait(driver, settings.getPageTimeout());
    wait.ignoring(StaleElementReferenceException.class);
    return wait;
  }
}