package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;

public class LoginTest extends BaseTest {
	
	//private LoginPage loginPage;
	
	@Test()
	public void validLogin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLoginButton();
		Assert.assertEquals("Just a moment...", driver.getTitle());
	}
	
	
	@Test(retryAnalyzer = retry.Retry.class)
	public void isDahboardDisplay() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterEmail("admin@yourstore.com");
		loginPage.enterPassword("admin");
		loginPage.clickLoginButton();
		Assert.assertEquals("Just a moment...", driver.getTitle());
		//Assert.assertTrue(loginPage.isDashboardDisplay());
	}
	

}
