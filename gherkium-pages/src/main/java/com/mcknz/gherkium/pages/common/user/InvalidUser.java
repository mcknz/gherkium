package com.mcknz.gherkium.pages.common.user;

import com.mcknz.gherkium.Settings;

@SuppressWarnings("unused")
public class InvalidUser extends AbstractUser {

  public InvalidUser(Settings settings) {
    super(settings);
  }

  public String getUserName() {
    return "Invalid";
  }

  public String getPassword() {
    return "12345";
  }
}