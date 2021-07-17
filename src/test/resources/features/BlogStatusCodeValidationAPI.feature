#Author: Camilo Colorado
@BlogStatusCodeValidation @BlogAPITests
Feature: Blog API Response Status Code With Invalid Data Eest
  as a user i want to validate the response status codes when the input data is invalid

  @UserInvalidData
  Scenario Outline: the user validates response status code when user name input data is invalid
    Given I search for an invalid user "<username>"
    Then the response status code must be "404"

    Examples: 
      | username |
      | Camilo   |

  @PostsInvalidData
  Scenario Outline: the user validates response status code when user id input data is invalid
    Given I search for posts made by an invalid user "<userid>" with a "<routetype>"
    Then the response status code must be "404"

    Examples: 
      | routetype | userid |
      | normal    |    100 |
      | nested    |    100 |

  @CommentsInvalidData
  Scenario Outline: the user validates response status code when post id input data is invalid
    Given I search for comments made in an invalid post "<postid>" with a "<routetype>"
    Then the response status code must be "404"

    Examples: 
      | routetype | postid |
      | normal    |    500 |
      | nested    |    500 |

  @UserInvalidParameterData
  Scenario Outline: the user validates the response code when a parameter is invalid for the user request
    Given I search for an user with the parameter "<parameter>"
    Then the response status code must be "400"

    Examples: 
      | parameter |
      | lastname  |

  @PostsInvalidParameterData
  Scenario Outline: the user validates the response code when a parameter is invalid for the posts request
    Given I search for posts with the parameter "<parameter>"
    Then the response status code must be "400"

    Examples: 
      | parameter |
      | date      |

  @CommentsInvalidParameterData
  Scenario Outline: the user validates the response code when a parameter is invalid for the comments request
    Given I search for comments with the parameter "<parameter>"
    Then the response status code must be "400"

    Examples: 
      | parameter |
      | language  |
