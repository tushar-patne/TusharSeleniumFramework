package tusharorg.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	@Test(dataProvider = "testData", groups = {"Purchase"})
	public void submitOrder(HashMap<String, String> data) throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("username"), data.get("password"));
		
		productCatalogue.addProductToCart(data.get("productName"));
		CartPage cartPage = productCatalogue.gotoCart();
		
		Boolean isProductPresent = cartPage.verifyProductInCart(data.get("productName"));
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
	
//	@DataProvider(name = "testData")
//	public Object[][] getData() {
//		Object[][] data = {
//				{"dummytushar@gmail.com", "Tushar123", "ZARA COAT 3"},
//				{"dummytejas@gmail.com", "Tejas123", "ADIDAS ORIGINAL"}
//		};
//		return data;
//	}
	
//	@DataProvider(name = "testData")
//	public Object[][] getData() {
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username", "dummytushar@gmail.com");
//		map1.put("password", "Tushar123");
//		map1.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map2 = new HashMap<String, String>();
//		map2.put("username", "dummytejas@gmail.com");
//		map2.put("password", "Tejas123");
//		map2.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map1}, {map2}};
//		
//	}
	
	@DataProvider(name = "testData")
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap();
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	} 
	
	public String getScreenshot(String testCaseName) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File sourseFile = ss.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File targetFile = new File(targetFilePath);
		FileUtils.copyFile(sourseFile, targetFile);
		return targetFilePath;
	}
	

}
