Feature: Iframe section page

  @E2E @IFrameSection @TC-013
  Scenario: User is able to access to the IFrames operations page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user scrolls to "Spinners"
    When the user clicks on the button "Iframes" in practice section
    And the user waits for text "Iframes are commonly used to embed external content such as videos, maps, or other web pages" to be visible
    Then the user validates if the string "Iframes" is visible
    Then the user validates if the string "I’m an iframe" is visible