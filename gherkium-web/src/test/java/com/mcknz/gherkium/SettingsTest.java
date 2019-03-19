package com.mcknz.gherkium;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class SettingsTest {

  private Settings settings;

  @BeforeEach
  void setUp() {
    settings = new Settings();
  }

  @Test
  void getDriverPath() {
    assertThat(settings.getDriverPath(),
      is("c:\\selenium\\drivers"));
  }
}