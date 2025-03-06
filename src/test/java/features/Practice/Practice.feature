Feature: Practice home page
  @E2E
  Scenario: User is able to access the automate.io practice page
    Given User goes to web page "https://practice-automation.com/"
    When the user waits for text "Welcome to your software automation practice website!" to be visible
    Then the user validates if section "JavaScript Delays" from Practice page is visible
    Then the user validates if section "Form Fields" from Practice page is visible
    Then the user validates if section "Popups" from Practice page is visible