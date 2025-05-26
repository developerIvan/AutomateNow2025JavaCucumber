Feature: Form Fields confirmation message

  Background:
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    And the user clicks on the button "Form Fields" in practice section

  @E2E @FormFieldsSection @TC-005
  Scenario: User is able to validate the confirmation message is displayed
    Given the user is able to see the section "Form Fields"
    When the user enters "Jon" in the field name
    And the user enter the password "Test" in the password input field
    And the user select the checkbox value "Water" under the label "What is your favorite drink?"
    And the user select the checkbox value "Blue" under the label "What is your favorite color?"
    And the user selects the "yes" option in the automation dropdown
    And the user enters the email "jorgematamoros@gmail.com" in the email input field
    And the user scrolls to "Message"
    And the user enters the message "This is a test message"
    And the user clicks on the submit button
    Then the user should see the following confirmation message "Message received!"

  @E2E @FormFieldsSection @TC-007
  Scenario: User is able to validate the confirmation message is not displayed
    Given the user is able to see the section "Form Fields"
    When the user enters "" in the field name
    And the user scrolls to "Message"
    And the user clicks on the submit button
    Then the user should not see the following confirmation message "Message received!"