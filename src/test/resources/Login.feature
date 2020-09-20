Feature: Login Functionality

  In order to do internet banking
  As a valid Para Bank customer
  I want to login successfully

## Scenario Outline Example - 1
#  @Login
#  Scenario Outline: Login Successful
#
#    Given I am in the login page of the Para Bank Application
#    When I enter valid <username> and <password>
#    Then I should be taken to the Overview page
#
#    Examples:
#      | username    | password   |
#      | "john"      | "demo"     |

## Scenario Outline Example - 2
  @Login
  Scenario Outline: Login Successful

    Given I am in the login page of the Para Bank Application
    When I enter valid <username> and <password> with <userFullName>
    Then I should be taken to the Overview page

    Examples:
      | username    | password   | userFullName  |
      | "john"      | "demo"     | "Auto Tester" |
      | "tautester" | "password" | "TAU Tester"  |

## Scenario for Inline Parameters
#  Scenario: Login Successful - Inline Parameters
#
#    Given I am in the login page of the Para Bank Application
#    When I enter valid credentials
#      | john | demo |
#    Then I should be taken to the Overview page