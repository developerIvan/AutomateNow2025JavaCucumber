Feature: Main home page
  @E2E
  Scenario: User access to the main page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the "JavaScript Delays" link
    And the user waits for text "As you write automated tests" to be visible
    Then the user validates if the string "Start" is visible