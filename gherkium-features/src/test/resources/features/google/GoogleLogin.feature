@google @functional @login
Feature: Google Login

  @smoke
  Scenario: Validate successful Google login
    Given I am a valid Google user
    When I submit my credentials through the Google login page
    Then the Google account page displays

  @regression
  Scenario: Validate unsuccessful Google login
    Given I am an invalid Google user
    When I submit my credentials through the Google login page
    Then the message "Wrong password. Try again or click Forgot password to reset it" is displayed on the Google login page
