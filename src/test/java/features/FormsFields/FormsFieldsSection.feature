Feature: Form Fields section page
  @E2E @FormFieldsSection @TC-006
  Scenario: User is able to access to the javascript page
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for the text "Welcome to your software automation practice website!" to be displayed in form section
    When the user clicks on the button "Form Fields" in practice section
    And the user waits for the text "Filling out a web form is one of the most fundamental things to learn" to be displayed in form section
    Then the user validates if the form page displays the expected text "Form Fields"
    Then the user validates if the form page displays the expected text " * Required"

