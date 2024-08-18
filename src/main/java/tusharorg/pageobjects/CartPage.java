package tusharorg.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import tusharorg.abstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3") List<WebElement> cartProducts;
	@FindBy(css = ".totalRow button") WebElement checkoutBtn;
	
	public Boolean verifyProductInCart(String productName) {
		Boolean isProductPresent = cartProducts.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return isProductPresent;
	}
	
	public CheckoutPage checkoutAllProductsInCart() {
		checkoutBtn.click();
		waitForURLtoContain("https://rahulshettyacademy.com/client/dashboard/order?prop=");
		return new CheckoutPage(driver);
	}

	
}
