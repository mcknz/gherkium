package com.mcknz.gherkium.pages.common;

import com.mcknz.gherkium.pages.common.user.AbstractUser;
import com.mcknz.gherkium.web.AbstractSite;
import com.mcknz.gherkium.web.WebPage;

@SuppressWarnings("unused")
abstract public class AbstractLoginPage extends WebPage {

  private AbstractUser user;
  private final String userNameId;
  private final String passwordId;
  private final String loginId;
  private String errorMessage;

  @SuppressWarnings("unused")
  protected AbstractLoginPage(AbstractSite site,
                              String userNameInputId,
                              String passwordInputId,
                              String loginButtonId) {
    super(site);
    this.userNameId = userNameInputId;
    this.passwordId = passwordInputId;
    this.loginId = loginButtonId;
  }

  public void login() {
    navigateToStartingUrl();
    enterUserName();
    enterPassword();
    clickLoginButton();
  }

  protected void enterUserName() {
    enterText(this.userNameId, user.getUserName());
  }

  private void enterPassword() {
    enterText(this.passwordId, user.getUserName());
  }

  protected void clickLoginButton() {
    click(this.loginId);
  }

  protected String getPassword() {
    return user.getPassword();
  }

  public void setUser(AbstractUser user) {
    this.user = user;
  }

  public void setErrorMessage(String message) {
    errorMessage = message;
  }

  protected String getErrorMessage() {
    return errorMessage;
  }

  abstract public boolean hasErrorMessage();
}