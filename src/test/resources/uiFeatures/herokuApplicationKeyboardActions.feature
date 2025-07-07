Feature: Verify Keyboard actons are working correctly

  @c
  Scenario Outline: Keyboard actions within Heroku Application with "<key>" button
    Given user navigates to heroku application
    Then user switch to "keyPresses" page
    Then user interacts with keyboard "<key>" actions
    Then user is able to see the message "<text>"
    Examples:
    | key                | text        |
    | ENTER              |  ENTER      |
    | TAB                |  TAB        |
    | SPACE              |  SPACE      |
    | BACK_SPACE         |  BACK_SPACE |


#    Negative scenario