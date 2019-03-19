package com.mcknz.gherkium.pages.samples.google;

import com.mcknz.gherkium.web.AbstractSite;
import com.mcknz.gherkium.web.WebPage;

public class SearchPage extends WebPage {

  public SearchPage(AbstractSite site) {
    super(site);
  }

  @Override
  protected String getStartingUrlSuffix() {
    return "";
  }

  public void enterSearchTerm(String query) {
    navigateToStartingUrl();
    enterTextByXPath("//input[@name='q']", query);
  }

  public void search() {
    clickByXPath("//input[@name='btnK']");
  }

  public boolean hasSearchResults() {
    return hasText("resultStats", " results (");
  }
}
