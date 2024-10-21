package tusharorg.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import tusharorg.pageobjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws FileNotFoundException, IOException {
		
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\tusharorg\\resources\\GlobalData.properties"));
//		read browser parameter from maven command or else read it from property file
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : properties.getProperty("browser");
		
		

		if (browserName.contains("chrome")) {
			ChromeOptions o = new ChromeOptions();
			o.addArguments("--incognito");
			if(browserName.contains("headless")) {
				o.addArguments("--headless");
//				o.addArguments("window-size=1440,900");
				o.addArguments("--start-maximized");
//				.maximize() method might not always work for headless mode
//				so to avoid the flakiness in the test results it's better to do it using ChromeOptions class which takes precedence over .maximize()
			}
			driver = new ChromeDriver(o);
		} else if (browserName.contains("edge")) {
			EdgeOptions o = new EdgeOptions();
			o.addArguments("--incognito");
			if(browserName.contains("headless")) {
				o.addArguments("--headless");
				o.addArguments("--start-maximized");
			}
			driver = new EdgeDriver(o);
		} else if (browserName.contains("firefox")) {
			FirefoxOptions o = new FirefoxOptions();
			o.addArguments("--incognito");
			if(browserName.contains("headless")) {
				o.addArguments("--headless");
				o.addArguments("--start-maximized");
			}
			driver = new FirefoxDriver(o);
		} else if (browserName.equalsIgnoreCase("safari")) {
//			safari driver works only on maxOs and it has many limitations like no support for .addArguments()
			driver = new SafariDriver();
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(5000));
		driver.manage().window().maximize();
		return driver;
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void launchApplication() throws FileNotFoundException, IOException  {
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
	}
	
	@AfterMethod(alwaysRun = true)
	public void quitApplication() {
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		//convert json to string
		String jsonData = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\tusharorg\\data\\PurchaseOrder.json") , StandardCharsets.UTF_8);
		
		//convert string to hashmap using JACKSON DATABIND maven depenndency
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData, new TypeReference<List<HashMap<String,String>>>() {
		});
		return data;
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ss = (TakesScreenshot) driver;
		File sourceFile = ss.getScreenshotAs(OutputType.FILE);
		String targetFilePath = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		File targetFile = new File(targetFilePath);
		FileUtils.copyFile(sourceFile, targetFile);
		return targetFilePath;
	}
	
}
