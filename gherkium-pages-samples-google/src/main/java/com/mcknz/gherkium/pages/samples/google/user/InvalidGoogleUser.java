package com.mcknz.gherkium.pages.samples.google.user;

import com.mcknz.gherkium.Settings;
import com.mcknz.gherkium.pages.common.user.AbstractUser;

public class InvalidGoogleUser extends AbstractUser {
  public InvalidGoogleUser(Settings settings) {
    super(settings);
  }

  @SuppressWarnings("unused")
  @Override
  public String getUserName() {
    return "test@example.com";
  }

  @SuppressWarnings("unused")
  @Override
  public String getPassword() {
    return "12345";
  }
}
