
Feature: GoBuild Login

  Background:
    Given user navigates to login page

  @Login
  Scenario: Verify successful login
    Given user enters valid login credentials
    When user clicks on Login button
    And user selects organization "Vyper cloths"
    Then verify user is on dashboard

  @LoginPage
  Scenario: Verify login page visibility
    Then user is able to see login page

  @InvalidLogin
  Scenario Outline: Verify unsuccessful login
    Given user enters username "<username>" and password "<password>"
    When user clicks on Login button
    Then verify error message is displayed

    Examples:
      | username | password  |
      | Admin    | admin1423 |
      | ad       | aa        |
