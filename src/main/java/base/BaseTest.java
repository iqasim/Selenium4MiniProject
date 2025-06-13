package base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
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
	public void setUp() throws MalformedURLException, InterruptedException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("browserName", "chrome");
		capabilities.setCapability("browserVersion", "latest");
		capabilities.setCapability("enableVNC", true);
		capabilities.setCapability("enableVideo", false);

		// Chrome-specific options
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-gpu");
		options.addArguments("--window-size=1920,1080");
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// Environment variables
		capabilities.setCapability("env", new ArrayList<String>() {{
			add("HOME=/tmp/chrome-data-" + System.currentTimeMillis());
		}});

		int attempts = 0;
		while (attempts < 3) {
			try {
				//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				driver = new ChromeDriver(options);
				driver.get("https://admin-demo.nopcommerce.com/login");
				break;
			} catch (SessionNotCreatedException e) {
				attempts++;
				Thread.sleep(5000);
			}
		}


		//driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

		//driver.get("https://admin-demo.nopcommerce.com/login");
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
