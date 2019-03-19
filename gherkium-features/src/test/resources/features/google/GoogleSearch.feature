@google @functional
Feature: Google Search

  @smoke
  Scenario: Perform a Google search
    Given I enter the Google search term "google"
    When I click the Google search button
    Then the Google results page displays