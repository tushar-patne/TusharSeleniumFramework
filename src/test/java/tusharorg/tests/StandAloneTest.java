package tusharorg.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import tusharorg.TestComponents.BaseTest;
import tusharorg.pageobjects.CartPage;
import tusharorg.pageobjects.OrderConfirmationPage;
import tusharorg.pageobjects.OrderPage;
import tusharorg.pageobjects.ProductCatalogue;

public class StandAloneTest extends BaseTest {

	@Test
	public void submitOrder() throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("dummytushar@gmail.com", "Tushar123");
		
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
		
		Thread.sleep(1500);
		
	}

}
