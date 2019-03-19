package com.mcknz.gherkium.pages.samples.google;

import com.mcknz.gherkium.pages.common.AbstractLoginPage;
import com.mcknz.gherkium.web.AbstractSite;

public class LoginPage extends AbstractLoginPage {

  public LoginPage(AbstractSite site,
                   String userNameInputId,
                   String passwordInputId,
                   String loginButtonId) {
    super(site, userNameInputId, passwordInputId, loginButtonId);
  }

  public boolean hasErrorMessage() {
    return hasText("view_container", getErrorMessage());
  }

  @Override
  public void login() {
    navigateToStartingUrl();
    enterUserName();
    clickLoginButton();
    enterTextByXPath("//input[@name='password']", getPassword());
    click("passwordNext");
  }

  @SuppressWarnings("unused")
  @Override
  protected String getStartingUrlSuffix() {
    return "ServiceLogin/signinchooser?flowName=GlifWebSignIn&flowEntry=ServiceLogin";
  }
}