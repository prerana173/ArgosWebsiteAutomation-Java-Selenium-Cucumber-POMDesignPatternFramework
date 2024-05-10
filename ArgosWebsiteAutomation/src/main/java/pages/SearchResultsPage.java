package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import org.junit.Assert;

public class SearchResultsPage {

	private WebDriver driver;
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	public static float costPrice;

	private By resultsTextTitleLocator = By.xpath("//div[@data-el='search-title']/h4");
	private By productTitleLocator = By.xpath("//*[@id=\"product-title-8473127\"]/div[1]");
	private By addToTrolleyBtnLocator = By.xpath("//div[8]/div[1]/div/div[1]/div/div[2]/button");
	private By productPriceLocator = By.xpath(
			"//div[@data-test='product-list'][1]//div[@data-test='product-group-card-0']//div[@data-test='component-product-card-valueMessagingContainer']//strong");
	private By addToTrolleyPopUpHeaderLocator = By.xpath("//*[@id=\"component-att-modal-drawer-title\"]");
	private By addToTrolleyBtnOnPopUpLocator = By.xpath("//*[@class='xs-row']/div[2]/a");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifySearchResultsPage(String productName) {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(resultsTextTitleLocator)));
		Assert.assertTrue((driver.findElement(productTitleLocator).getText()).contains(productName));
		Assert.assertTrue(driver.findElement(resultsTextTitleLocator).getText().toUpperCase()
				.contains(productName.toUpperCase()));
	}

	public void addProductToBasket() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(addToTrolleyBtnLocator)));
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.PAGE_DOWN);
		actions.moveToElement(driver.findElement(addToTrolleyBtnLocator));
		captureUnitPrice();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		driver.findElement(addToTrolleyBtnLocator).click();
		addToTrolleyPopUp();
	}

	public void captureUnitPrice() {
		String PriceWithCurrencySymbol = driver.findElement(productPriceLocator).getText();
		String PriceWithoutCurrencySymbol = PriceWithCurrencySymbol.replaceAll("[^\\d.]", "");
		costPrice = Float.parseFloat(PriceWithoutCurrencySymbol);
	}

	public void addToTrolleyPopUp() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(addToTrolleyPopUpHeaderLocator)));
		driver.findElement(addToTrolleyBtnOnPopUpLocator).click();
	}

}
