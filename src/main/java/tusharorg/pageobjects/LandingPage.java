package tusharorg.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tusharorg.abstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "userEmail") WebElement userInput;
	@FindBy(id = "userPassword") WebElement passwordInput;
	@FindBy(id = "login") WebElement loginBtn;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication() {
		userInput.sendKeys("dummytushar@gmail.com");
		passwordInput.sendKeys("Tushar123");
		loginBtn.click();
		return new ProductCatalogue(driver);
	}

}
