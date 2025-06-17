Feature: Add Mahasiswa Functionality

  Scenario: Successfully add new mahasiswa
    Given User is logged in with valid credentials
    When User scrolls down and clicks Add Profil Mahasiswa button
    Then User should be redirected to the add mahasiswa page
    When User fills in valid nama and nim and uploads photo
    And User clicks submit button on add mahasiswa page for positive case
    Then User should be redirected to the mahasiswa list page

  Scenario: Add mahasiswa without uploading photo
    Given User is logged in with valid credentials
    When User scrolls down and clicks Add Profil Mahasiswa button
    Then User should be redirected to the add mahasiswa page
    When User fills in nama and nim without photo
    And User clicks submit button on add mahasiswa page for negative case
    Then A "foto" error message should appear
