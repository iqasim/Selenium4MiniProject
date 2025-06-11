package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.CustomWait;

public class LoginPage {
	
	private WebDriver driver;
	
	public CustomWait customWait;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By inputEmail = By.cssSelector("#Email");
	private By inputPassword = By.xpath("//input[@id='Password']");
	private By buttonLogin = By.xpath("//button[contains(text(),'Log in')]");
	private By headingDashboard = By.xpath("//h1[contains(text(),'Dashboard')]");
	
	
	public LoginPage enterEmail(String email) {
		customWait = new CustomWait(driver);
		customWait.customWaitIsElementVisible(inputEmail, 20);
		System.out.println("element is visible");
		driver.findElement(inputEmail).clear();
		driver.findElement(inputEmail).sendKeys(email);
		return this;		
	}
	
	public LoginPage enterPassword(String password) {
		driver.findElement(inputPassword).clear();
		driver.findElement(inputPassword).sendKeys(password);
		return this;		
	}
	
	public LoginPage clickLoginButton() {
		driver.findElement(buttonLogin).click();
		return this;		
	}
	
	public boolean isDashboardDisplay() {
		return driver.findElement(headingDashboard).isDisplayed();		
	}
	
	
	
	
	

}
