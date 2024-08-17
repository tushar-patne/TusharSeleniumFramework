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
	@FindBy(css = ".toast-message") WebElement errorToast;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue loginApplication(String username, String password) {
		userInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginBtn.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToBeVisible(errorToast);
		return errorToast.getText().trim();
	}

}
