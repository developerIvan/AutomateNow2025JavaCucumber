Feature: Window Operations section page

  @E2E @WindowOperationsSection @TC-009
  Scenario: User is able to access to the window operations page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user scrolls to "Calendars"
    When the user clicks on the button "Window Operations" in practice section
    And the user waits for text "multiple open windows" to be visible
    Then the user validates if the string "New Tab" is visible
    Then the user validates if the string "Replace Window" is visible
    Then the user validates if the string "New Window" is visible