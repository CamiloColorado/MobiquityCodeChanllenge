#Author: Camilo Colorado
@BlogKeyFieldsValidation @BlogAPITests
Feature: Blog API Key Fields Validation Test
  As a user i want to validate the things that can possibly go wrong

  @NoKeyFieldUser
  Scenario Outline: the user validates that the user data has the key field to continue with the workflow
    Given I search for user "<username>"
    Then The response must have the field "<keyfield>"

    Examples: 
      | username | keyfield |
      | Delphine | id       |

  @NoKeyFieldPosts
  Scenario Outline: the user validates that the posts data has the key field to continue with the workflow
    Given I search for user "<username>"
    When I search the posts written by the user
    Then The response must have the field "<keyfield>"

    Examples: 
      | username | keyfield |
      | Delphine | id       |

  @NoKeyFieldComments
  Scenario Outline: the user validates that the comments data has the key field to continue with the workflow
    Given I search for user "<username>"
    When I search the posts written by the user
    Then The get comments response must have the field "<keyfield>"

    Examples: 
      | username | keyfield |
      | Delphine | email    |
