package com.lululemon.mobweb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.lululemon.mobweb.pages.HomePage;
import com.lululemon.mobweb.pages.LoginPage;
import io.qameta.allure.Description;

public class LululemonMobWebCreateAccountTest extends LululemonMobWebBaseTest  {
	
	private HomePage homePage;
	private LoginPage loginPage;
	
	/**
	 * To verify that user is able to login to application
	 * 
	 */
	@Description("<b>Test Step</b>:To verify the account creation functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>New user account should get created and user should be naviagted to user's account page"
			+ "</ul>")
	
	@Test(description = "To verify the account creation functionality")
	public void createAccount() {
		
		homePage = new HomePage(getDriver());
    	homePage.clickHamburger();
    	homePage.clickOnMyAccount();
    	loginPage = new LoginPage(getDriver());
    	loginPage.createAccount();
		Assert.assertTrue(loginPage.isHomePageLoadedBySuccessfulLogin(), "Account Not created successfully");
	}
}
