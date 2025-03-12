Feature: Javascript Delay section page
  @E2E @JavascriptSection
  Scenario: User is able to trigger the JavaScript Delay section page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the "JavaScript Delays" link in javascript section
    And the user waits for text "As you write automated tests" to be visible
    When the user clicks on the button with id "start" in javascript section
    And the user waits for text "Liftoff" to be visible
    Then the user validates if the string "Liftoff!" is visible