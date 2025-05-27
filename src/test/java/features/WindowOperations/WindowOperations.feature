Feature: Window Operations

  Background: The user goes to the Window Operations section
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user scrolls to "Calendars"
    And the user clicks on the button "Window Operations" in practice section

  @E2E @WindowOperationsSection @TC-0010
  Scenario: User is able to open and interact with a new tab
    Given the user is able to see the section "Window Operations"
    When the user clicks on "New Tab"
    And the user switch to a new tab
    And the user waits for text "Start learning" to be visible
    And the user clicks on "Practice"
    And the user waits for text "We have developed this site as a one-stop place to practice web automation." to be visible
    Then the user validates the current url is the same as "https://practice-automation.com/"

