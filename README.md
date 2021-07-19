# Mobiquity QA Engineer Code Challenge
## Introduction :page_facing_up:
The main goal is to help the developers in your team write tests for some workflows that might break as they move forward in developing the business logic for the user's blog by testing the already published API.

## Requirements :page_with_curl:
- JDK 8
- Gradle 5.2

## Usage :computer:
From the command line of the project's root folder, run the following command to build project and run the project's test scenarios.

```gradle test```

After the execution is completed the test report can be found in ```/build/reports/tests/test/index.html```

## Aproach :bulb:
Assuming that the API developed is RESTful, the following validations were setup
- Workflow test: Validate the main workflow.
- Expected results test: Validate the existence of response data.
- Key fields test: Validate the existence of key fields in the response data.
- Response status code test: Validate status code when input values are invalid or do not exist.


## Key libraries and tools :wrench:
- Java: Language
- Gradle: Dependency Management
- JUnit: Testing
- Cucumber: BDD Management
- RestAssured: Java library to test REST APIs

## Source code structure :file_folder:
All packages and classes have been defined following SOLID principles and separated concerns.

Package list under ```/src/test/java```

- **runner:** Contains the Runner class that is used to run the tests.
- **stepDefinitions:** Contains the mapping between the test scenarios steps, described  in the feature files, with the functions to be executed.
- **utilities:** Contains the ConfigurationFileReader class that is used to read the configuration properties file.

Package list under ```/src/test/resources```

- **features:** Contains the test scenarios.

## Circle CI
In the following link You can find the Circle CI pipeline

[![CircleCI](https://circleci.com/gh/CamiloColorado/MobiquityCodeChanllenge/tree/main.svg?style=svg&circletoken=5193a6673b19ad75148f0ac846ae0b42938401a3)](https://circleci.com/gh/CamiloColorado/MobiquityCodeChanllenge/tree/main)

## Decisions :arrow_up_down: :left_right_arrow:
- BDD was used to improve the speed of test development and to facilitate test definition and understanding.
- All scenarios were defined as a scenario outline in order to allow modifying the input values or adding more test iterations.
- A configuration properties file was used in order to allow future configurations to be added.
- Different tests were defined to validate what can go wrong in the main workflow.
- Assuming that the API developed is RESTful, some stateful code tests were defined to validate the correct behavior of an RESTful API.
- Hamcrest framework was implemented in order to facilitate the matchers validations.

## Authors :bust_in_silhouette:
- Camilo Colorado - [LinkedIn](https://www.linkedin.com/in/camilo-andres-colorado-ramos)
