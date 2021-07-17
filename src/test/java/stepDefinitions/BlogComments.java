package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.NotFoundException;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationFileReader;

public class BlogComments {

	private ContentType contentType;
	private Response response;
	private List<String> emails = new ArrayList<>();

	@BeforeStep
	public void setUp() {
		RestAssured.baseURI = ConfigurationFileReader.getProperty("URI");
		contentType = ContentType.JSON;
	}

	@Given("I search for user {string}")
	public void searchForUserName(String userName) {
		response = given().accept(contentType).param("username", userName).when().get("users").prettyPeek();
		assertEquals(200, response.getStatusCode());
	}

	@And("I search the posts written by the user")
	public void searchPostByUser() {
		List<Integer> userIdList = response.jsonPath().getList("id");
		Integer userId = userIdList.stream().findFirst()
				.orElseThrow(() -> new NotFoundException("id not found in the response"));
		response = given().accept(contentType).param("userId", userId).when().get("posts").prettyPeek();
		assertEquals(200, response.getStatusCode());
	}

	@When("I get the emails in the comment section by post")
	public void getEmails() {
		List<Integer> postIds = response.jsonPath().getList("id");
		postIds.stream().forEach(postId -> {
			emails.addAll(given().accept(contentType).param("postId", postId).when().get("comments").prettyPeek().then()
					.statusCode(200).extract().jsonPath().getList("email"));
		});
	}

	@Then("The emails must be in the proper format")
	public void validateEmailFormat() {
		List<String> wrongEmails = new ArrayList<>();
		EmailValidator validator = EmailValidator.getInstance();
		emails.stream().forEach(email -> {
			if (!validator.isValid(email)) {
				wrongEmails.add(email);
			}
		});
		assertTrue("There are some emails with wrong format.", wrongEmails.isEmpty());
	}
	
	@Then("The response must have the field {string}")
	public void validateResponseFields(String keyField) {
		response.then().assertThat().body("",everyItem(hasKey(keyField)));
	}
	
	@Then("The get comments response must have the field {string}")
	public void validateCommentResponse(String keyField) {
		List<Integer> postIds = response.jsonPath().getList("id");
		postIds.stream().forEach(postId -> {
			given().accept(contentType).param("postId", postId).when().get("comments").prettyPeek().then()
					.statusCode(200).assertThat().body("", everyItem(hasKey(keyField)));
		});
	}

}
