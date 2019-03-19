package com.mcknz.gherkium.pages.samples.google.user;

import com.mcknz.gherkium.pages.common.user.AbstractUser;
import com.mcknz.gherkium.pages.samples.google.common.GoogleSettings;

public class GoogleUser extends AbstractUser {

  private final GoogleSettings settings;

  public GoogleUser(GoogleSettings settings) {
    super(settings);
    this.settings = settings;
  }

  @SuppressWarnings("unused")
  @Override
  public String getUserName() { return settings.getUsername(); }

  @SuppressWarnings("unused")
  @Override
  public String getPassword() {
    return settings.getPassword();
  }
}
