Feature: Login

  @positiveLogin
  Scenario: Login as a user
    Given User is on the login page
    When User should be able enter valid username and password
    When User clicks to submit button
    Then User should be able to see the login page

  @negativeLogin
  Scenario: When user enters correct username and incorrect password user should be able to see error message
    Given User is on the login page
    When User enter valid username and invalid password
    When User clicks to submit button
    Then User should be able to see the error message displayed on the page