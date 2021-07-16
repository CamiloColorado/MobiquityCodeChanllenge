#Author: Camilo COlorado
@BlogComments
Feature: Blog Comments API Test
  As a user i want to validate the Blog Comments API

  @EmailFormatValidation
  Scenario Outline: the user validates the proper format of the emails in the comments section.
    Given I search for user "<username>"
    And I search the posts written by the user
    When I get the emails in the comment section by post
    Then The emails must be in the proper format

    Examples: 
      | username |
      | Delphine |
