package tusharorg.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tusharorg.TestComponents.BaseTest;
import tusharorg.pageobjects.CartPage;
import tusharorg.pageobjects.OrderConfirmationPage;
import tusharorg.pageobjects.OrdersPage;
import tusharorg.pageobjects.CheckoutPage;
import tusharorg.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	
	// To verify order placement
	@Test(dataProvider = "loginData", groups = {"Purchase"})
	public void submitOrder(String username, String password, String productName) throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(username, password);
		
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.gotoCart();
		
		Boolean isProductPresent = cartPage.verifyProductInCart(productName);
		Assert.assertTrue(isProductPresent);
		CheckoutPage checkoutPage = cartPage.checkoutAllProductsInCart();
		
		String selectCountry = "India";
		checkoutPage.selectCountry(selectCountry);
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();
	
		Assert.assertTrue(orderConfirmationPage.confirmThankyouMsg());
		
//		Thread.sleep(1500);
		
	}
	
	// To verify placed order is present in the orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("dummytushar@gmail.com", "Tushar123");
		OrdersPage ordersPage = productCatalogue.gotoOrders();
		Assert.assertTrue(ordersPage.isProductInOrderedList(productName));
	}
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
		Object[][] data = {
				{"dummytushar@gmail.com", "Tushar123", "ZARA COAT 3"},
				{"dummytejas@gmail.com", "Tejas123", "ADIDAS ORIGINAL"}
		};
		return data;
	}
	

}
