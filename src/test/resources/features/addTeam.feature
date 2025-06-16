Feature: Add New Team Functionality

    Background:
      Given User is logged in with valid credentials

    Scenario: User adds a new team successfully
      When User on the homepage and clicks on add new team button
      Then User is redirected to the add team page
      When User fills in team details
      And User submits the team
      Then The team is added successfully

    Scenario: User adds a new team failure
      When User on the homepage and clicks on add new team button
      Then User is redirected to the add team page
      When User not fills in team details
      And User submits the team
      Then The team is not added successfully