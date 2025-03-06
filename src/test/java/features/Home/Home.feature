Feature: Main home page
  @E2E
  Scenario: User access to the main page
    Given User goes to web page "https://automatenow.io/"
    When the user waits for text "Start" to be visible
    Then the user validates if the string "Start learning" is visible

