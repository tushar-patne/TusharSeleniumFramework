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

public class ErrorValidations extends BaseTest {

	@Test
	public void submitOrder() throws FileNotFoundException, IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("dummytushar@gmail.com", "Tushar1234");
		
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		
		Thread.sleep(1500);
		
	}

}
