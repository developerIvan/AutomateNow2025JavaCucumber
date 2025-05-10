Feature: Practice home page
  @E2E @TC-001 @PracticeSection
  Scenario: User is able to access the automate.io practice page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "We have developed this site as a one-stop place to practice web automation" to be visible
    Then the user validates if the string "Welcome to your software automation practice website! " is visible



