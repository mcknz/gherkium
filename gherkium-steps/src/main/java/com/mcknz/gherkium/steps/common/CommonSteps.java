package com.mcknz.gherkium.steps.common;

import com.mcknz.gherkium.web.Driver;
import cucumber.api.Scenario;
import cucumber.api.java8.En;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommonSteps implements En {

  @SuppressWarnings("unused")
  public CommonSteps() {
    After((Scenario scenario) -> Driver.quit());
  }

  public void assertIsTrue(boolean expression) {
    assertThat(expression, is(true));
  }
}
