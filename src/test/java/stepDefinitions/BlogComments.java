package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.NotFoundException;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
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
		RestAssured.baseURI = ConfigurationFileReader.getProperty("URI");
		response = given().accept(contentType).param("username", userName).when().get("users").prettyPeek();
		assertEquals(200, response.getStatusCode());
	}
	
	@And("I search the post written by the user")
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
	
}
