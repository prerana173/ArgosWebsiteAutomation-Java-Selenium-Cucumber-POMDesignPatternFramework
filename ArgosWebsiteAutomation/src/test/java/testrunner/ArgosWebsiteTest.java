package testrunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, 
				 glue = { "stepDefinitions", "apphooks" },
//				 tags="not @Skip",
				 plugin = {"pretty",
					 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
						 ,"timeline:test-output-thread/",
						 "rerun:target/failedrerun.txt"})
public class ArgosWebsiteTest {

}
