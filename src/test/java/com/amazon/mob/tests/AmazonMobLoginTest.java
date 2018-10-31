package com.amazon.mob.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.mob.pages.HomeMobPage;
import com.amazon.mob.pages.LoginMobPage;
import com.amazon.mob.pages.MobCategoryPage;

import io.qameta.allure.Description;

public class AmazonMobLoginTest extends BaseMobTest {
    
	public static Logger LOGGER = Logger.getLogger(AmazonMobLoginTest.class);
	public LoginMobPage loginMobPage;
	public HomeMobPage homeMobPage;
	public MobCategoryPage categoryPage;
	
	/*
	 *  Verify user is able to open the Sign-in/Login page
	 */
	 @Description("<b>Test Step</b>:Verify user is able to open the Sign-in/Login page<br>"
				+ "<b>Expected Result</b>:<br>"
				+ "<ul><li>User should be navigated to login page"
				+ "</ul>")
	
	@Test(description = "Verify user is able to open the Sign-in/Login page")
	public void verifyLoginPage() {
		try {
            LOGGER.info("Verify user is able to open the Sign-in/Login page");
            loginMobPage = new LoginMobPage(driver);
            loginMobPage.clickOnSignInButton();
            Assert.assertTrue(loginMobPage.verifyLoginPageIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while opening the Sign-in/Login page");
            // Terminating test case execution because of an unexpected
            // addressPageplication/environment/network error
            Assert.fail(exception.getMessage());
        }
	}
}
