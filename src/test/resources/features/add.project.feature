Feature: Add new project functionality

  Scenario: User adds a new project successfully
    Given User is logged in with valid credentials
    When User scrolls down on the homepage and clicks on add new project button
    Then User is redirected to the add project page
    When User uploads an image and enters project details
    When User selects a project from the dropdown
    And User submits the project
    Then The project is added successfully
