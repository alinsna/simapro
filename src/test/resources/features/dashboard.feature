Feature: Dashboard Functionality on SIMAPRO

  Background:
    Given User is logged in with valid credentials in homepage

  Scenario: User searches for a project
    When User on the homepage and clicks on search bar and enters "Smart Home Automation"
    And User clicks enter
    Then User is redirected to the project list page with the keyword "Smart Home Automation"
    And The project with the keyword "Smart Home Automation" should be displayed

  Scenario: User searches for a project that does not exist
    When User on the homepage and clicks on search bar and enters "abcdefg"
    And User clicks enter
    Then User is redirected to the project list page with the keyword "abcdefg"
    And The project with the keyword "abcdefg" should not be displayed
