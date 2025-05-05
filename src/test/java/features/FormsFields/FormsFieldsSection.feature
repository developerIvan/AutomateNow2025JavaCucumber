Feature: Form Fields section page
  @E2E @FormFieldsSection @TC-006
  Scenario: User is able to access to the javascript page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the button "Form Fields" in practice section
    And the user waits for text "Filling out a web form is one of the most fundamental things to learn" to be visible
    Then the user validates if the string "Form Fields" is visible
    Then the user validates if the string " * Required" is visible


