package com.mcknz.gherkium.pages.common.user;

import com.mcknz.gherkium.Settings;

@SuppressWarnings("unused")
abstract public class AbstractUser {

  private final Settings settings;

  public AbstractUser(Settings settings) {
    this.settings = settings;
  }

  protected Settings getSettings() {
    return settings;
  }

  abstract public String getUserName();
  abstract public String getPassword();
}