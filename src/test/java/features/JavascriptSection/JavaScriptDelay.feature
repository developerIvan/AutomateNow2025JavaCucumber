Feature: Javascript Delay section page

  @E2E @JavascriptSection @TC-004
  Scenario: User is able to trigger the JavaScript Delay section page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the button "JavaScript Delays" in practice section
    And the user waits for text "As you write automated tests" to be visible
    When the user clicks on the button with id "start" in javascript section
    And the user waits for text "Liftoff" is visible in the input field after some seconds
    Then the user validates if the delay input contains the value "Liftoff!"