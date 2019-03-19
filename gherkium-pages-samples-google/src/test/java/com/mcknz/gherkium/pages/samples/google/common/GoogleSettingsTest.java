package com.mcknz.gherkium.pages.samples.google.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class GoogleSettingsTest {
  private GoogleSettings settings;

  @BeforeEach
  void setUp() {
    settings = new GoogleSettings();
  }

  @Test
  void getDriverPath() {
    assertThat(settings.getDriverPath(),
      is("c:\\selenium\\drivers"));
  }
  @Test
  void getUsername() {
    assertThat(settings.getUsername(),
      is("test@example.com"));
  }
}