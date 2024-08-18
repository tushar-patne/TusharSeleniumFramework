package tusharorg.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import tusharorg.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener {
	
	  ExtentReports extent = ExtentReporterNG.getReportObject();
	  ExtentTest test;
	  ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread Safe - map java thread to test instance

	  public void onTestStart(ITestResult result) {
		  
		  test = extent.createTest(result.getMethod().getMethodName());
		  extentTest.set(test);
	  }
	  
	  public  void onTestSuccess(ITestResult result) {
	    extentTest.get().log(Status.PASS, "Test Passed Successfully :)");
	  }

	  public void onTestFailure(ITestResult result) {
		  
		extentTest.get().log(Status.FAIL, "Test Failed :(");
		extentTest.get().log(Status.FAIL, result.getThrowable());
			
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		String screenshotFile = null;
		try {
			screenshotFile = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(screenshotFile, result.getMethod().getMethodName());
		
	  }
	  
	  public void onTestSkipped(ITestResult result) {
		  extentTest.get().log(Status.SKIP, "Test Skipped");
	  }

	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	  public void onFinish(ITestContext context) {
	    extent.flush();
	  }
	  
}
