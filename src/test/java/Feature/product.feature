Feature: GoBuild Product Creation

  Background:
    Given user is logged in and navigates to Products page

  @Product
  Scenario: Verify successful product creation
    Given user clicks on Add Product button
    When user enters product name "Samsung S26 Ultra"
    And user selects category "Smart phone"
    And user enters product price "550"
    And user selects package type "small"
    And user clicks on Publish Product button
    Then verify product is created successfully

  @ProductPage
  Scenario: Verify product page visibility
    Then user is able to see Products page

  @InvalidProductName
  Scenario Outline: Verify product creation with invalid product name
    Given user clicks on Add Product button
    When user enters product name "<productname>"
    And user enters product price "550"
    And user selects category "Smart phone"
    And user selects package type "small"
    And user clicks on Publish Product button
    Then verify product creation is rejected

    Examples:
      | productname |
      |             |
      | 1236        |
      | @#$%        |