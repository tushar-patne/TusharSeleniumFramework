package tusharorg.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tusharorg.abstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {
	
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".container .mb-3") List<WebElement> products;
	@FindBy(css = "ngx-spinner .ng-trigger") WebElement loadingSpinner;
	
	By productsLocator = By.cssSelector(".container .mb-3");
	By addToCartBtnBy = By.cssSelector("button:last-of-type");
	By successToastMsgBy = By.id("toast-container");
	
	public List<WebElement> getProductsList() {
		waitForElementToBeVisible(productsLocator);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement expectedProduct = getProductsList().stream().filter(p->p.findElement(By.tagName("b"))
				.getText().equalsIgnoreCase(productName))
				.findFirst().orElse(null);
		return expectedProduct;
	}
	
	public CartPage addProductToCart(String productName) {
		getProductByName(productName).findElement(addToCartBtnBy).click();
		waitForElementToBeVisible(successToastMsgBy);
		waitForElementToBeInvisible(loadingSpinner);
		return new CartPage(driver);
	}

	
}
