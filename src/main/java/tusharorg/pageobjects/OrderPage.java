package tusharorg.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import tusharorg.abstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "input[placeholder=\"Select Country\"]") WebElement countryInput;
	@FindBy(css = ".ta-results span") List<WebElement> autoSuggestCountryDropdown;
	@FindBy(css = ".action__submit") WebElement submitBtn;
	
	public void selectCountry(String countryString) {
		countryInput.sendKeys(countryString);
		for (WebElement webElement : autoSuggestCountryDropdown) {
			if (webElement.getText().trim().equalsIgnoreCase(countryString)) {
				webElement.click();
				break;
			}
		}
	}
	
	public OrderConfirmationPage placeOrder() {
		submitBtn.click();
		waitForURLtoContain("https://rahulshettyacademy.com/client/dashboard/thanks?prop=");
		return new OrderConfirmationPage(driver);
	}

	
}
