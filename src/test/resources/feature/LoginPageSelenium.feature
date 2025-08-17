Feature: Testing Login Page

  Background:
    Given Open browser

  Scenario: Testing positive authorization scenario

    When Opening Login Page
    And Set valid email
    And Set valid Password
    And Click on Sign in button
    Then Check name on Profile Page
    Then Close browser

  Scenario: Testing when Sign in button activates

    When Opening Login Page
    And Check activation status without valid input
    And Set valid email
    And Set valid Password
    Then Check activation status
    Then Close browser

  Scenario: Testing negative scenario with entering invalid password

    When Opening Login Page
    And Set valid email
    And Set invalid Password
    And Click on Sign in button
    Then Check if not valid password error message appears
    Then Close browser