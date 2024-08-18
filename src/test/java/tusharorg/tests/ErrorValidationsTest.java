package tusharorg.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import tusharorg.TestComponents.BaseTest;
import tusharorg.TestComponents.Retry;
import tusharorg.pageobjects.CartPage;
import tusharorg.pageobjects.OrderConfirmationPage;
import tusharorg.pageobjects.CheckoutPage;
import tusharorg.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups = {"ErrorHandling"}, retryAnalyzer = Retry.class)
	public void LoginErrorValidation() throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("dummytushar@gmail.com", "Tushar123");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
//		Thread.sleep(1500);
		
	}
	
	@Test
	public void ProductErrorValidation() throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("dummytushar@gmail.com", "Tushar123");
		
		String productName = "ZARA COAT 3";
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.gotoCart();
		
		Boolean isProductPresent = cartPage.verifyProductInCart("ZARA COAT 33");
		Assert.assertFalse(isProductPresent);
		
//		Thread.sleep(1500);
		
	}

}
