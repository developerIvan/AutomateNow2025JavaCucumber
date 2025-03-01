Feature: Login test

  Scenario: Successfull login with valid credentials
    Given User is in login page
    When The user sets the username "admin" and password "password123"
    Then It should see the message "Success"

  Scenario: Login failed with invalid credentials
    Given User is in login page
    When The user sets the username "admin" and password "incorrecta"
    Then It should see the message "invalid credentials"
