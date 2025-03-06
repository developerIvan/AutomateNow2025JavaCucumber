Feature: Login test
  @cucumberDemo
  Scenario: Successfull login with valid credentials
    Given User is in login page
    When The user sets the username "admin" and password "password123"
    Then It should see the message "Success"

