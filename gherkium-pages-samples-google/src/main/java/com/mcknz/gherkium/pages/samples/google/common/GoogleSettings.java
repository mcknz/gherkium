package com.mcknz.gherkium.pages.samples.google.common;

import com.mcknz.gherkium.Settings;

public class GoogleSettings extends Settings {

  private String username;
  private String password;

  public String getUsername() {
    if(username == null) {
      username = getProperty("username");
    }
    return username;
  }

  public String getPassword() {
    if(password == null) {
      password = getProperty("password");
    }
    return password;
  }
}
