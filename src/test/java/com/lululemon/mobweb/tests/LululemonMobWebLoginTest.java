package com.lululemon.mobweb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.lululemon.mobweb.interfaces.Constants;
import com.lululemon.mobweb.pages.HomePage;
import com.lululemon.mobweb.pages.LoginPage;
import io.qameta.allure.Description;

public class LululemonMobWebLoginTest extends LululemonMobWebBaseTest {
	
	private HomePage homePage;
	private LoginPage loginPage;
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    
    /**
     * To verify that user is able to login to application
     * 
     */
    @Description("<b>Test Step</b>:To verify the login functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be able to login to the application"
			+ "</ul>")
    
    @Test (description = "To verify the login functionality")
    public void loginToApplication() {
        
    	homePage = new HomePage(getDriver());
    	homePage.clickHamburger();
    	homePage.clickOnMyAccount();
    	loginPage = new LoginPage(getDriver());
    	loginPage.loginApplication(userName, password);  
        Assert.assertTrue(loginPage.isHomePageLoadedBySuccessfulLogin(), "Page not loaded");
    }
}
