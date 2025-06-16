Feature: Manage Stakeholders on SIMAPRO

  Background:
    Given User is logged in with valid credentials

  Scenario: Successfully add a new stakeholder
    Given User is on the stakeholder list page
    When User clicks the "Add Stakeholder" button
    Then User should be redirected to the add stakeholder page
    When User fills the stakeholder form with unique valid data
    And User clicks the submit button
    Then User should be redirected back to the stakeholder list page
    And The new stakeholder should appear in the list

  Scenario: Successfully search for the newly added stakeholder
    Given User is on the stakeholder list page
    When User searches for the newly added stakeholder
    Then Only the searched stakeholder should be displayed in the results