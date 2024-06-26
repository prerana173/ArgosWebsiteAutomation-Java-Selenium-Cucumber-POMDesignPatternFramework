package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class BasketPage {

	private WebDriver driver;
	private String itemInTheTrolley;
	private int quantity;
	private String subTotalWithCurrencySymbol;
	private String subTotalWithoutCurrencySymbol;
	private Float calculatedSubTotal;
	private Float updatedSubTotal;

	private By productInTheTrolleyLocator = By.xpath("//a[@data-e2e='product-name']");
	private By quantityDropdownLocator = By.xpath("//*[@data-e2e=\"product-quantity\"]");
	private By subTotalLocator = By.xpath("//*[@data-e2e=\"basket-total-price\"]");

	public BasketPage(WebDriver driver) {
		this.driver = driver;
	}

	public void verifyProductNameInBasket(String productName) {
		itemInTheTrolley = driver.findElement(productInTheTrolleyLocator).getAttribute("innerHTML");
		Assert.assertTrue(itemInTheTrolley.contains(productName));
	}

	public void increaseProductQuantity(int quantity) {
		this.quantity = quantity;
        Select quantityDropdown = new Select(driver.findElement(quantityDropdownLocator));
        quantityDropdown.selectByVisibleText(String.valueOf(quantity));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void subTotalUpdate() {
		subTotalWithCurrencySymbol = driver.findElement(subTotalLocator).getText();
		subTotalWithoutCurrencySymbol = subTotalWithCurrencySymbol.replaceAll("[^\\d.]", "");
		updatedSubTotal = Float.parseFloat(subTotalWithoutCurrencySymbol);
		calculatedSubTotal = (SearchResultsPage.costPrice) * quantity;
		Assert.assertEquals(updatedSubTotal, calculatedSubTotal);
	}

}
