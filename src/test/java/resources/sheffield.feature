Feature: Log into Sheffield University website



  Scenario: Scroll down to bottom of page
    Given I have navigated to the Sheffield University homepage
    When I scroll down to bottom of page
    Then the copyright text is displayed


  Scenario Outline: Login to MUSE
    Given I have navigated to the Sheffield University homepage
    And I click on Login to MUSE link
    And the login page is displayed
    When I enter credentials "<username>" amd "<password>"
    Then the welcome message is displayed
    Examples:
      | username | password |
      | user1    | pass1    |


