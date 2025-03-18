Feature: Form Fields confirmation message
  @E2E @FormFieldsSection @TC-005
  Scenario: User is able to validate the confirmation message of Form Fields section
    Given User goes to web page "https://practice-automation.com/"
    And the user waits for text "Welcome to your software automation practice website!" to be visible
    When the user clicks on the button "Form Fields" in practice section
    And the user waits for text "Filling out a web form is one of the most fundamental things to learn" to be visible
    When the user enters "Jon" in the field name
    And the user enter the password "Test" in the password input field
    And the user select the checkbox value "Water" under the label "What is your favorite drink?"
    And the user select the checkbox value "Blue" under the label "What is your favorite color?"
    Then the user scrolls to "Do you like automation?" text
    When the user selects the "yes" option in the automation dropdown
    And the user enters the email "jorgematamoros@gmail.com" in the email input field
    Then the user scrolls to "Message" text
    When the user enters the message "This is a test message" in the message input field
    Then the user scrolls to "Submit" text
    And the user waits for 2 seconds
    And the user clicks on the submit button
    Then the user should see the following confirmation message "Message received!"