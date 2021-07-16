package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)

@CucumberOptions(glue = "stepDefinitions", features = "src/test/resources/features", dryRun = false, tags = "@EmailFormatValidation", plugin = {
		"json:target/cucumber.json", "pretty", "html:target/cucumber-reports" })

public class Runner {

}
