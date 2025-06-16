Feature: Add New Team Functionality

    Background:
      Given User is logged in with valid credentials in dashboard

    Scenario: User adds a new team successfully
      When User scrolls down on the homepage and clicks on add new team button
      Then User is redirected to the add team page
      When User fills in team details:
      | Field           | Value                          |
      | Nama Team       | Nama Otomasi Dengan Selenium   |
      | Project Manager | Ahmad Fauzi                    |
      | Front End       | Dewi Lestari                   |
      | Back End        | Budi Santoso                   |
      | UI/UX           | Gita Ananda                    |
      And User submits the team
      Then The team is added successfully

    Scenario: User adds a new team failure
      When User scrolls down on the homepage and clicks on add new team button
      Then User is redirected to the add team page
      When User leaves all team detail fields empty
      And User submits the team
      Then The team is not added successfully