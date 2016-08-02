package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.HomePage;
import Pages.LoginPage;
import factory.BrowserFactory;
import factory.DataProviderFactory;

public class VerifyLoginPage {
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUP()
	{
		driver=BrowserFactory.getBrowser("chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationURL());
	}
	
	@Test
	public void testLoginPage()
	{

		HomePage home=PageFactory.initElements(driver, HomePage.class);
		String title=home.ApplicationTitle();
		Assert.assertTrue(title.contains("Demo Store"));
		
		home.clickOnSignInLink();
		
		LoginPage login=PageFactory.initElements(driver, LoginPage.class);
		
		//login.logintoApplication("mukesh@selenium.com", "mukesh@selenium.com");
		
		login.logintoApplication(DataProviderFactory.getExcel().getData(0, 0, 0),DataProviderFactory.getExcel().getData(0, 0, 1));
		
				
	}
	
	@AfterMethod
	public void tearDown()
	{
		BrowserFactory.closeBrowser(driver);
	}

}
