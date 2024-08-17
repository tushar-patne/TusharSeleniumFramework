package tusharorg.pageobjects;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import tusharorg.abstractComponents.AbstractComponents;

public class OrderConfirmationPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "h1.hero-primary") WebElement thankyouHeader;
		
	public Boolean confirmThankyouMsg() {
		return thankyouHeader.getText().trim().equalsIgnoreCase("Thankyou for the order.");
	}

}
