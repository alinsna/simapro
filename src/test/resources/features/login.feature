Feature: Login Functionality on SIMAPRO

  Background:
    Given User is on the login page

  Scenario: Successful login with valid credentials
    When User enters valid email and password
    And User clicks the login button
    Then User should be redirected to the dashboard

  Scenario: Login with invalid email format
    When User enters invalid email "invalidemail" and valid password
    And User clicks the login button
    Then An email format error should be displayed

  Scenario: Login with incorrect password
    When User enters valid email and incorrect password "wrongpassword"
    And User clicks the login button
    Then An invalid credential error should be displayed

  Scenario: Login with empty email and password
    When User leaves email and password fields empty
    And User clicks the login button
    Then Required field validation should be displayed

  Scenario: Login with boundary email input length
    When User enters boundary email "a@b.co" and valid password
    And User clicks the login button
    Then An invalid credential error should be displayed
