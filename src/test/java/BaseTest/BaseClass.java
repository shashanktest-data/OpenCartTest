package BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;



public class BaseClass {
	
	public WebDriver driver;
	public  Logger logger; //log4j
	public Properties prop;
	@Parameters({"browser","os"})
@BeforeClass(groups= {"sanity","regression","master","datadriven"})
	public void setup(String br,String osname) throws IOException
	
	{
		
		
		//loading config.prop
		prop=new Properties();
		FileInputStream fs=new FileInputStream("./src//test//resources//config.properties");
	prop.load(fs);
		
	logger=LogManager.getLogger(this.getClass());
	
	if(prop.getProperty("execution_env").equalsIgnoreCase("remote")	)
			{
		
		//do grid setup
		DesiredCapabilities dc=new DesiredCapabilities();
		if(osname.equalsIgnoreCase("windows"))
		{
			dc.setPlatform(Platform.WIN11);
		}else if(osname.equalsIgnoreCase("mac"))
		{
			dc.setPlatform(Platform.MAC);
		}else
		{
			System.out.println("no matching os");
			return;
		}
		
		//browser setup
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			
			if(br.equalsIgnoreCase("chrome"))
			{
				dc.setBrowserName("chrome");
				
			}else if(br.equalsIgnoreCase("edge"))
			{
				dc.setBrowserName("edge");
			}else if(br.equalsIgnoreCase("firefox"))
			{
				dc.setBrowserName("firefox");
			}else
			{
				System.out.println("no matching browser");
				return;
			}
		}
		
		driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),dc);
		
			}
	//local env
	if(prop.getProperty("execution_env").equalsIgnoreCase("local"))
	{
			
			if(br.equals("chrome")) {
		driver=new ChromeDriver();
	}else if(br.equals("edge"))
	{
		driver=new EdgeDriver();
	}else if (br.equals("firefox"))
	{
		driver=new FirefoxDriver();
	}
}
	
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.get(prop.getProperty("appURL")); //READING VALUE FROM PROP FILE
		driver.manage().window().maximize();
	}

@AfterClass(groups= {"sanity","regression","master","datadriven"})
public void teardown()
{
	driver.quit();
}

public String captureScreen(String tname)
{
	String timestamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts=(TakesScreenshot)driver;
	File src=ts.getScreenshotAs(OutputType.FILE);
	String targetfilepath=System.getProperty("user.dir")+"\\screenshots"+"_"+tname+ timestamp+".png";
	File targetfile=new File (targetfilepath);
	src.renameTo(targetfile);
	return targetfilepath;
	
	
}
public String randomeString()
{
	String generatedString=RandomStringUtils.randomAlphabetic(5);
	return generatedString;
	}

public String randomeNumber()
{
	String generatedNumber=RandomStringUtils.randomNumeric(5);
	return generatedNumber;
	}

public String randomlphanumeric()
{
	String generatedString=RandomStringUtils.randomAlphabetic(5);
	String generatedNumber=RandomStringUtils.randomNumeric(5);
	return (generatedString+"@"+generatedNumber);
	}


}
