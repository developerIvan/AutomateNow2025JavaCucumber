Feature: Window Operations

  Background: The user goes to the Window Operations section
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user scrolls to "Calendars"
    And the user clicks on the button "Window Operations" in practice section

  @E2E @WindowOperationsSection @TC-0010-1
  Scenario: User is able to open and interact with a new tab that validates the site redirects to https://practice-automation.com/
    Given the user is able to see the section "Window Operations"
    When the user clicks on "New Tab"
    And the user switch to a new tab or window with url "https://automatenow.io/"
    And the user waits for text "Start learning" to be visible
    And the user clicks on "Practice"
    And the user waits for text "We have developed this site as a one-stop place to practice web automation." to be visible
    Then the user validates the current url is the same as "https://practice-automation.com/"

  @E2E @WindowOperationsSection @TC-0010-2
  Scenario: User is able to open and interact with a new tab but validates the site does not redirects to https://practice-automation.com/
    Given the user is able to see the section "Window Operations"
    When the user clicks on "New Tab"
    And the user switch to a new tab or window with url "https://automatenow.io/"
    And the user waits for text "Start learning" to be visible
    Then the user validates the current url is not the same as "https://practice-automation.com/"


  @E2E @WindowOperationsSection @TC-0010-3
  Scenario: User is able to open and interact with a new tab but validates the site is the same as to https://practice-automation.com/window-operations/
    Given the user is able to see the section "Window Operations"
    When the user clicks on "New Tab"
    And the user switch to a new tab or window with url "https://automatenow.io/"
    And the user waits for text "Start learning" to be visible
    But the user closes the current tab or window
    Then the user validates the current url is the same as "https://practice-automation.com/window-operations/"

  @E2E @WindowOperationsSection @TC-0011
  Scenario: User is able to replace the current window and return to the practice site
    Given the user is able to see the section "Window Operations"
    When the user clicks on "Replace Window"
    And the user waits for text "Start learning" to be visible
    And the user clicks on "Practice"
    And the user waits for text "We have developed this site as a one-stop place to practice web automation." to be visible
    Then the user validates the current url is the same as "https://practice-automation.com/"


  @E2E @WindowOperationsSection @TC-0012
  Scenario: User is able to interact with a new window and validate the home page site
    Given the user is able to see the section "Window Operations"
    When the user clicks on "New Window"
    And the user switch to a new tab or window with url "automatenow.io"
    And the user waits for text "Start learning" to be visible
    Then the user validates the current url is the same as "https://automatenow.io/"
