Feature: Create new user in Gorest API

  @chau
  Scenario Outline: Create User

    Given User create request data with "<name>" , "<email>" , "<gender>" , "<status>"
    When User sumbits POST request to GOREST api
    And User validates if statusCode is 201
    Then User retrieves userID from response
    And User deletes data with userID
    And User get the above record

    Examples:
      | name     | email             | gender | status |
      | Minhtoan | miuiut8@gmail.com | male   | Active |