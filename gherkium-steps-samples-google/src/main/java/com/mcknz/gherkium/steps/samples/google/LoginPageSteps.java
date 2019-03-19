package com.mcknz.gherkium.steps.samples.google;

import com.mcknz.gherkium.pages.samples.google.AccountPage;
import com.mcknz.gherkium.pages.samples.google.LoginPage;
import com.mcknz.gherkium.pages.samples.google.common.GoogleSettings;
import com.mcknz.gherkium.pages.samples.google.user.GoogleUser;
import com.mcknz.gherkium.pages.samples.google.user.InvalidGoogleUser;
import com.mcknz.gherkium.steps.common.CommonSteps;
import com.mcknz.gherkium.steps.samples.google.common.PageSteps;
import cucumber.api.java8.En;

@SuppressWarnings("unused")
public class LoginPageSteps implements En {

  @SuppressWarnings("unused")
  public LoginPageSteps(PageSteps pageSteps, CommonSteps commonSteps) {

    LoginPage loginPage = pageSteps.getLoginPage();
    AccountPage accountPage = pageSteps.getAccountPage();
    GoogleSettings settings = new GoogleSettings();

    Given("^I am a valid Google user$", ()
      -> loginPage.setUser(new GoogleUser(settings)));

    Given("^I am an invalid Google user$", ()
      -> loginPage.setUser(new InvalidGoogleUser(settings)));

    When("^I submit my credentials through the Google login page$",
      loginPage::login);

    Then("^the Google account page displays$", ()
      -> {
      accountPage.waitForPageToLoad();
      commonSteps.assertIsTrue(accountPage.isOnPage());
    });

    Then("^the message \"([^\"]*)\" is displayed on the Google login page$", (String errorMessage) -> {
      loginPage.setErrorMessage(errorMessage);
      commonSteps.assertIsTrue(loginPage.hasErrorMessage());
    });

  }
}