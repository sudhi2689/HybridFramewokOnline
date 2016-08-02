package dataProvider;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties pro;
	
	public ConfigDataProvider()
	{
		File src=new File("./Configuration/Config.properties");
		
		try {
			FileInputStream fis=new FileInputStream(src);
			pro=new Properties();
			pro.load(fis);
		} 
		catch (Exception e) 
		{
			System.out.println("Exception is "+e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("url");
		return url;
	}
	
	public String getChromePath()
	{
		String chromePath=pro.getProperty("ChromeDriver");
		return chromePath;
	}
	
	public String getIEPath()
	{
		String iePath=pro.getProperty("IEDriver");
		return iePath;
	}

}
