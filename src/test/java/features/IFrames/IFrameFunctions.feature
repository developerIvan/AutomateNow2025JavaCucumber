Feature: Iframe section page interactions

  Background:
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user scrolls to "Click Events"
    And the user clicks on the button "Iframes" in practice section

  @E2E @IFrameSection @TC-0014
  Scenario: User is able to interact with Playwright iframe
    Given the user is able to see the section "Iframes"
    When the user switch to a a Iframe with name "top-iframe"
    And the user waits for text "Get started" to be visible
    And the user clicks on text like "Docs"
    And the user waits for text "Playwright Test" to be visible
    Then the user validates if the string "Installation" is visible


  @E2E @IFrameSection @TC-0015
  Scenario: User is able to interact with Selenium iframe
    Given the user is able to see the section "Iframes"
    And the user scrolls to "Learn More"
    When the user switch to a a Iframe with name "bottom-iframe"
    And the user waits for text "What you do with that power is entirely up to you." to be visible
    And the user clicks on text like "Documentation"
    Then the user validates if the string "The Selenium Browser Automation Project" is visible