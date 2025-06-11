package utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseTest;

public class CustomWait  extends BaseTest{
	
	private WebDriver driver;
	
	public CustomWait(WebDriver driver) {
		this.driver = driver;
	}
	
	
	
	public void customWaitIsElementVisible(By locator, int timeout) {
		 new WebDriverWait(driver, Duration.ofSeconds(timeout))
		.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}



	/*
	 * public static void customWaitIsElementVisible(By inputEmail, int timeout) {
	 * // TODO Auto-generated method stub
	 * 
	 * }
	 */
	

}
