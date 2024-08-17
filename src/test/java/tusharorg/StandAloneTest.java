package tusharorg;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import tusharorg.pageobjects.CartPage;
import tusharorg.pageobjects.LandingPage;
import tusharorg.pageobjects.OrderConfirmationPage;
import tusharorg.pageobjects.OrderPage;
import tusharorg.pageobjects.ProductCatalogue;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.manage().window().maximize();
		
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		ProductCatalogue productCatalogue = landingPage.loginApplication();
		
		String productName = "ZARA COAT 3";
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.gotoCart();
		
		Boolean isProductPresent = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(isProductPresent);
		OrderPage orderPage = cartPage.checkoutAllProductsInCart();
		
		String selectCountry = "India";
		orderPage.selectCountry(selectCountry);
		OrderConfirmationPage orderConfirmationPage = orderPage.placeOrder();
	
		Assert.assertTrue(orderConfirmationPage.confirmThankyouMsg());
		
		Thread.sleep(3000);
		driver.quit();

	}

}
