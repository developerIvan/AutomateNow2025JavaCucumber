Feature: Practice home page
  @E2E @TC-002 @PracticeSection
  Scenario: User is able to see the sections from automate.io practice page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    Then the user validates if the string "JavaScript Delays" is visible
    Then the user validates if the string "Form Fields" is visible
    Then the user validates if the string "Popups" is visible