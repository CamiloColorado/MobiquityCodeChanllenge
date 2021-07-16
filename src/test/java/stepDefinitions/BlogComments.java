package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utilities.ConfigurationFileReader;

public class BlogComments {
	
	private ContentType contentType;
	private Response response;
	
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
	
}
