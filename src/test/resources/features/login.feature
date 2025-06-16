Feature: Login Functionality on SIMAPRO

  Background:
    Given User is on the login page

  Scenario: Successful login with valid credentials
    When User enters email "syafiq.abdillah@ugm.ac.id" and password "adminpassword"
    And User clicks the login button
    Then User should be redirected to the dashboard

  Scenario: Login with invalid email format
    When User enters email "invalidemail" and password "adminpassword"
    And User clicks the login button
    Then An error message containing "Login failed" should be displayed

  Scenario: Login with incorrect password
    When User enters email "syafiq.abdillah@ugm.ac.id" and password "invalidpassword"
    And User clicks the login button
    Then An error message containing "Login failed" should be displayed

  Scenario: Login with empty email and password
    When User enters email "" and password ""
    And User clicks the login button
    Then An error message containing "Login failed" should be displayed

  Scenario: Login with boundary email input length
    When User enters email "a@b.co" and password "adminpassword"
    And User clicks the login button
    Then An error message containing "Login failed" should be displayed