@account
Feature: Login

  @login
  Scenario: Successful Login
    Given I navigate to the 'Login' page
    When I enter the following details on the 'Login' page
      | username      | password     |
      | standard_user | secret_sauce |
    And I click the 'Login' button on the 'Login' page
    Then the 'Home' page is displayed