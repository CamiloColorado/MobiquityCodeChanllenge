package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.NotFoundException;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationFileReader;

public class BlogDataValidation {

	private ContentType contentType;
	private Response response;

	@BeforeStep
	public void setUp() {
		RestAssured.baseURI = ConfigurationFileReader.getProperty("URI");
		contentType = ContentType.JSON;
	}

	@Given("I get the list of users")
	public void getUserList() {
		response = given().accept(contentType).when().get("users").prettyPeek();
		assertEquals(200, response.getStatusCode());
	}

	@Then("the data for {string} must exist")
	public void validateExistenceUserData(String userName) {
		List<String> userNameList = response.jsonPath().getList("username");
		userNameList.stream().filter(user -> user.equals(userName)).findAny()
				.orElseThrow(() -> new NotFoundException("There is no data for " + userName));
	}

	@When("I get the posts made by {string}")
	public void getPostMadeByUser(String userName) {
		int userId = response.jsonPath().get("find { it.username == '" + userName + "' }.id");
		response = given().accept(contentType).when().get("users/" + userId + "/posts").prettyPeek();
		assertEquals(200, response.getStatusCode());
	}

	@Then("the user must have made posts")
	public void validateExistencePostData() {
		response.then().body("", hasSize(greaterThan(0)));
	}

	@Then("the posts must have comments")
	public void validateExistenceCommentsData() {
		List<Integer> postIds = response.jsonPath().getList("id");
		postIds.stream().forEach(postId -> {
			given().accept(contentType).when().get("posts/" + postId + "/comments").prettyPeek().then().statusCode(200)
					.body("", hasSize(greaterThan(0)));
		});
	}

}
