package com.mcknz.gherkium.steps.samples.google.common;

import com.mcknz.gherkium.pages.common.PageFactory;
import com.mcknz.gherkium.pages.samples.google.AccountPage;
import com.mcknz.gherkium.pages.samples.google.LoginPage;
import com.mcknz.gherkium.pages.samples.google.SearchPage;
import com.mcknz.gherkium.pages.samples.google.common.GoogleAccountSite;
import com.mcknz.gherkium.pages.samples.google.common.GoogleSite;
import com.mcknz.gherkium.web.AbstractSite;

import static org.junit.Assert.fail;

public class PageSteps  {

  private final AbstractSite site = new GoogleSite();

  private LoginPage loginPage;
  private AccountPage accountPage;

  private SearchPage searchPage;

  @SuppressWarnings("unused")
  public PageSteps() {
    try {
      PageFactory<LoginPage, AccountPage> accountFactory =
        new PageFactory<>(
          new GoogleAccountSite(),
          LoginPage.class,
          AccountPage.class);

      loginPage = accountFactory.getLoginPage("identifierId", "password", "identifierNext");
      accountPage = accountFactory.getMainPage();

    } catch (Exception ex) {
      fail(ex.getMessage());
    }
  }

  public LoginPage getLoginPage() {
    return loginPage;
  }

  public AccountPage getAccountPage() {
    return accountPage;
  }

  public SearchPage getSearchPage() {
    if (searchPage == null) {
      searchPage = new SearchPage(site);
    }
    return searchPage;
  }
}