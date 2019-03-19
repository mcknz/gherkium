package com.mcknz.gherkium.steps.samples.google;

import com.mcknz.gherkium.steps.samples.google.common.PageSteps;
import com.mcknz.gherkium.steps.common.CommonSteps;
import com.mcknz.gherkium.pages.samples.google.SearchPage;
import cucumber.api.java8.En;

@SuppressWarnings("unused")
public class SearchSteps implements En {

  public SearchSteps(PageSteps pageSteps, CommonSteps commonSteps) {

    SearchPage page = pageSteps.getSearchPage();

    Given("^I enter the Google search term \"([^\"]*)\"$", page::enterSearchTerm);

    When("^I click the Google search button$", page::search);

    Then("^the Google results page displays$", ()
      -> commonSteps.assertIsTrue(page.hasSearchResults()));

  }
}