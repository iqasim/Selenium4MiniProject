package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
	
	protected WebDriver driver;
	
	@BeforeMethod
	public void setUp() throws MalformedURLException {
	/*	ChromeOptions options = new ChromeOptions();
		//options.addArguments("--headless");
		options.addArguments("--user-data-dir=/tmp/chrome-user-data-" + UUID.randomUUID());
		//driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
		driver = new ChromeDriver(options);*/
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("version", "latest");
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("enableVideo", false);
		capabilities.setCapability("sessionTimeout", "5m");
		// This ensures fresh profile for each session
		capabilities.setCapability("env", new String[]{"HOME=/tmp/selenoid-user-$RANDOM"});
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		driver.manage().window().maximize();
		driver.get("https://admin-demo.nopcommerce.com/login");
	}
	
	
	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		 if (result.getStatus() == ITestResult.FAILURE) { 
				File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
				try {
					FileUtils.copyFile(scrFile, new File("errorScreenshots\\" + result.getName() + "-" 
							+ System.currentTimeMillis() +  ".jpg"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
	}
	
	@AfterMethod
	public void tearDown() {
		if(driver!=null)
			driver.quit();
	}
	
	

}
