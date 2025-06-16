Feature: Manage Stakeholders on SIMAPRO

  Background:
    Given User is logged in with valid credentials

  Scenario: Successfully add a new stakeholder
    When User clicks on the "Add Profil Stakeholder" button on the homepage
    Then User should be redirected to the add stakeholder page
    When User uploads a profile image and enters the following stakeholder details:
      | Name   | Rifqi Renaldo          |
      | Email  | aldodododo@gmail.com   |
      | Number | 083113241231           |
    And User selects "Internal" as the category
    And User clicks the submit stakeholder button
    Then User should be redirected to the stakeholder list page
    And The new stakeholder should appear in the list

  Scenario: Attempt to add a stakeholder with empty fields
    When User clicks on the "Add Profil Stakeholder" button on the homepage
    Then User should be redirected to the add stakeholder page
    When User leaves the stakeholder form empty
    And User clicks the submit stakeholder button
    Then a "Stakeholder failed to upload!" message should be displayed