package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationFileReader;

public class BlogStatusCodeValidation {

	private ContentType contentType;
	private Response response;

	@BeforeStep
	public void setUp() {
		RestAssured.baseURI = ConfigurationFileReader.getProperty("URI");
		contentType = ContentType.JSON;
	}

	@Given("I search for an invalid user {string}")
	public void searchInvalidUser(String userName) {
		response = given().accept(contentType).param("username", userName).when().get("users").prettyPeek();
	}

	@Then("the response status code must be {int}")
	public void validateResponseStatusCode(int expectedCode) {
		assertEquals(expectedCode, response.getStatusCode());
	}

	@Given("I search for posts made by an invalid user {int} with a {string}")
	public void searchPostByInvalidUser(int userId, String routeType) {
		if (routeType.equals("normal")) {
			response = given().accept(contentType).param("userId", userId).when().get("posts").prettyPeek();
		} else {
			response = given().accept(contentType).when().get("users/" + userId + "/posts").prettyPeek();
		}
	}

	@Given("I search for comments made in an invalid post {int} with a {string}")
	public void searchCommentsByInvalidPost(int postId, String routeType) {
		if (routeType.equals("normal")) {
			response = given().accept(contentType).param("postId", postId).when().get("comments").prettyPeek();
		} else {
			response = given().accept(contentType).when().get("posts/" + postId + "/comments").prettyPeek();
		}
	}

	@Given("I search for an user with the parameter {string}")
	public void searchUserInvalidParameter(String parameter) {
		response = given().accept(contentType).param(parameter, "Delphine").when().get("users").prettyPeek();
	}

	@Given("I search for posts with the parameter {string}")
	public void searchPostInvalidParameter(String parameter) {
		response = given().accept(contentType).param(parameter, "May-8").when().get("posts").prettyPeek();
	}

	@Given("I search for comments with the parameter {string}")
	public void searchCommentInvalidParameter(String parameter) {
		response = given().accept(contentType).param(parameter, "English").when().get("comments").prettyPeek();
	}
}
