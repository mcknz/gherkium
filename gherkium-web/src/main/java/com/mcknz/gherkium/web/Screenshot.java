package com.mcknz.gherkium.web;

import com.mcknz.gherkium.Settings;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Screenshot {

  private final boolean isScreenshotEnabled;
  private final String screenshotPath;
  private final WebDriver driver;
  private final WebPage page;

  Screenshot(WebDriver driver, WebPage page, Settings settings) {
    this.driver = driver;
    this.page = page;
    if(settings.getIsScreenshotEnabled()) {
      this.isScreenshotEnabled = true;
      String path = settings.getScreenshotPath();
      if(path.length() > 0 && !path.endsWith(File.separator)) {
        path += File.separator;
      }
      this.screenshotPath = path;
    } else {
      this.isScreenshotEnabled = false;
      this.screenshotPath = "";
    }
  }

  void take(String callingMethodName) {
    if (this.isScreenshotEnabled) {
      ByteArrayInputStream screenBytes = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
      File screenFile = new File(getScreenshotFileName(callingMethodName));
      try {
        BufferedImage screenshot = ImageIO.read(screenBytes);
        ImageIO.write(addTimestamp(screenshot), "png", screenFile);
      } catch (IOException ex) {
        throw new RuntimeException("Unable to take screenshot.", ex);
      }
    }
  }

  private String getScreenshotFileName(String callingMethodName) {
    return String.format(
      "%s%s_%s_%s.png",
      this.screenshotPath,
      System.currentTimeMillis(),
      page.getClass().getCanonicalName().replace(".", "-"),
      callingMethodName
    );
  }

  private String getTimeStamp() {
    return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"));
  }

  private BufferedImage addTimestamp(BufferedImage screenshot) {
    BufferedImage stamped = new BufferedImage(screenshot.getWidth(), screenshot.getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics2D graphics = (Graphics2D) stamped.getGraphics();
    graphics.drawImage(screenshot, 0, 0, null);
    graphics.setColor(Color.GRAY);
    graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 36));
    graphics.drawString(getTimeStamp(), 48,48);
    graphics.dispose();
    return stamped;
  }
}