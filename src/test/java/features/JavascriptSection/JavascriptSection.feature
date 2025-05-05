Feature: Javascript section page

  @E2E @JavascriptSection @TC-003
  Scenario: User is able to access to the javascript page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the button "JavaScript Delays" in practice section
    And the user waits for text "There will be times when you will need to wait for certain conditions or events as you write automated tests." to be visible
    Then the user validates if the string "Start" is visible