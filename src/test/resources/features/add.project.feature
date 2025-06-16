Feature: Add New Project Functionality

  Scenario: User adds a new project successfully
    Given User is logged in with valid credentials
    When User scrolls down on the homepage and clicks on add new project button
    Then User is redirected to the add project page
    When User uploads an image and enters the following project details:
      | Field       | Value                          |
      | Project Name| Proyek Otomasi Dengan Selenium |
      | Year        | 2023                           |
      | Stakeholder | PT Maju Jaya                   |
      | Group Name  | Tim Alpha                      |
      | Description | Proyek ini dibuat via automasi.|
    And User selects "Projek Aplikasi Dasar 1" from the dropdown
    And User submits the project
    Then The project is added successfully

  Scenario: Attempt to add a project with all empty fields
    Given User is logged in with valid credentials
    When User scrolls down on the homepage and clicks on add new project button
    Then User is redirected to the add project page
    When User leaves all project detail fields empty
    And User submits the project
    Then a "Project upload failed" message should be displayed in the modal