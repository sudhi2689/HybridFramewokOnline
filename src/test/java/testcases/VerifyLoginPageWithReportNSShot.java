package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utility.Helper;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Pages.HomePage;
import Pages.LoginPage;
import factory.BrowserFactory;
import factory.DataProviderFactory;

public class VerifyLoginPageWithReportNSShot {
	
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
	@BeforeMethod
	public void setUP()
	{
		report=new ExtentReports("./Reports/LoginPageReport.html",true);
		
		logger=report.startTest("Verify Login Page", "This page will veerify Sign Out link");
				
		driver=BrowserFactory.getBrowser("chrome");
		
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
		logger.log(LogStatus.INFO, "Application is up and running");
		
	}
	
	@Test
	public void testLoginPage()
	{

		HomePage home=PageFactory.initElements(driver, HomePage.class);
		String title=home.ApplicationTitle();
		
		Assert.assertTrue(title.contains("Demo Store"));
		logger.log(LogStatus.PASS, "Home Page loaded and verified");
		
		
		home.clickOnSignInLink();
		logger.log(LogStatus.INFO, "Click On Sign In Link");
		
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		
		//login.logintoApplication("mukesh@selenium.com", "mukesh@selenium.com");
		
		login.logintoApplication(DataProviderFactory.getExcel().getData(0, 0, 0),DataProviderFactory.getExcel().getData(0, 0, 1));
		logger.log(LogStatus.INFO, "Login to User Account");
		
		login.verifySignOutLink();
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.CaptureScreenshot(driver, "Validation2")));
		logger.log(LogStatus.PASS, "Sign Out link is present");
				
	}
	
	@AfterMethod
	public void tearDown(ITestResult result)
	{
		
		if(result.getStatus()==ITestResult.FAILURE)
		{
			String path=Helper.CaptureScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}
		
		BrowserFactory.closeBrowser(driver);
		report.endTest(logger);
		report.flush();
	}

}
