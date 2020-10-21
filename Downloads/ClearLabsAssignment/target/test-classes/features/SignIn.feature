Feature: Login

  @login
  Scenario: Login as a user
    Given User is on the login page
    When User should be able enter valid username and password
    When User clicks to submit button
    Then User should be able to see the login page