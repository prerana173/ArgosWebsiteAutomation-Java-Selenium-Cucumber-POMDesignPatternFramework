package apphooks;

import java.util.Properties;

import org.junit.Assume;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import factory.DriverFactory;
import utlilities.ConfigReader;

public class ApplicationHooks {

	private DriverFactory driverFactory;
	private WebDriver driver;
	private ConfigReader configReader;
	Properties prop;

	@Before(value="@Skip", order=0)
	public void skipScenario(Scenario scenario) {
		System.out.println("Skipped Scenario is : "+scenario.getName());
		Assume.assumeTrue(false);
	}
	
	@Before(order = 1)
	public void getProperty() {
		configReader = new ConfigReader();
		prop = configReader.initProp();
	}

	@Before(order = 2)
	public void launchBrowser() {
		String browserName = prop.getProperty("browser");
		String url=prop.getProperty("url");
		driverFactory = new DriverFactory();
		driver = driverFactory.initDriver(browserName);
		driver.get(url);
	}

	@After(order = 0)
	public void quitBrowser() {
		driver.quit();
	}

	@After(order = 1)
	public void tearDown(Scenario scenario) {
		if (scenario.isFailed()) {
			// Method to take screenshot
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(sourcePath, "image/png", screenshotName);
		}
	}

}
