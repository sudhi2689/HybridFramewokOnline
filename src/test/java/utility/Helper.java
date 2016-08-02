package utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	public static String CaptureScreenshot(WebDriver driver,String screenshotName)
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		
		File src=ts.getScreenshotAs(OutputType.FILE);
		
		String destination="C:\\Selenium Luna Work\\com.learnautomation.hybrid\\Screenshots\\"+screenshotName+System.currentTimeMillis()+".png";
		
		try
		{
			FileUtils.copyFile(src, new File(destination));
		} 
		catch (Exception e) 
		{
			System.out.println("Failed to take screenshot" +e.getMessage());
		}
		
		return destination;
	}

}
