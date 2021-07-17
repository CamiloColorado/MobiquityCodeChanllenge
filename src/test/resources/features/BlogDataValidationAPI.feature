#Author: Camilo Colorado
@BlogDataValidation @BlogAPITests
Feature: Blog API Data Validation Test
  as a user i want to validate that the data to be used exists

  @SpecificUserData
  Scenario Outline: the user validates that data exists for a specific user name
    Given I get the list of users
    Then the data for "<username>" must exist

    Examples: 
      | username |
      | Delphine |

  @SpecificUserPostData
  Scenario Outline: the user validates if there are posts made by a specific user name
    Given I get the list of users
    When I get the posts made by "<username>"
    Then the user must have made posts

    Examples: 
      | username |
      | Delphine |
      
  @SpecificPostCommentData
  Scenario Outline: the user validates if there are comments on posts made by a specific user name
    Given I get the list of users
    And I get the posts made by "<username>"
    When I get the comments made on the post 
    Then the posts must have comments

    Examples: 
      | username |
      | Delphine |
