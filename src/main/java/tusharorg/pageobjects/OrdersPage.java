package tusharorg.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import tusharorg.abstractComponents.AbstractComponents;

public class OrdersPage extends AbstractComponents {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-of-type(2)") List<WebElement> orderedProductsNames;
	
	public Boolean isProductInOrderedList(String productName) {
		Boolean isPresent = orderedProductsNames.stream().anyMatch(p->p.getText().equalsIgnoreCase(productName));
		return isPresent;
	}
	
	
	
}
