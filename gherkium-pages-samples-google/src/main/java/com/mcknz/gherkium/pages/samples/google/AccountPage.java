package com.mcknz.gherkium.pages.samples.google;

import com.mcknz.gherkium.web.AbstractSite;
import com.mcknz.gherkium.web.WebPage;

public class AccountPage extends WebPage {

  public AccountPage(AbstractSite site) {
    super(site);
  }

  @Override
  protected String getStartingUrlSuffix() {
    return "?pli=1";
  }

  @Override
  protected String getStartingUrl() {
    return "https://myaccount.google.com/" + getStartingUrlSuffix();
  }

  @Override
  public boolean isOnPage() {
    if(super.isOnPage()) {
      return true;
    } else {
      return getCurrentUrl().equals(getStartingUrl());
    }
  }
}