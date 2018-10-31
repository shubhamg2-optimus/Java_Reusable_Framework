package com.amazon.mob.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class LoginMobPage extends BaseMobPage {
    
    private static final String CONSTRUCTOR_INFO = "In LoginMobPage() constructor";
    public static Logger LOGGER = Logger.getLogger(LoginMobPage.class);

    private By signinButton = By.id("in.amazon.mShop.android.shopping:id/sign_in_button");
    private By loginPageId = By.id("outer-accordion-signin-signup-page");
    private By loginEmailTextId = By.id("ap_email_login");
    private By continueButtonId = By.id("continue");
    private By loginPasswordTextId = By.id("ap_password");
    private By loginButtonId = By.id("signInSubmit");
    private By skipSigninButtonId = By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button");

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseMobTest.java
     */
	public LoginMobPage(AndroidDriver driver) {
		super(driver);
		LOGGER.info(CONSTRUCTOR_INFO);
	}
	
	public void clickOnSignInButton() {
		LOGGER.info("Clicking on Sign-in button");
		clickElement(signinButton);
	}
	
	public HomeMobPage clickOnSkipSignInButton() {
		LOGGER.info("Clicking on Skip Sign-in button");
		clickElement(skipSigninButtonId);
		return(new HomeMobPage(driver));
	}
	
	@Step("Verify login page is visible")
	public boolean verifyLoginPageIsVisible() {
		LOGGER.info("Verifying login page is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(loginPageId));
        return(doesElementExist(loginPageId));
	}
	
	public HomeMobPage loginToApp(String emailId, String password) {
		LOGGER.info("Login to the application");
		clickElement(signinButton);
		enterInput(loginEmailTextId, emailId);
		clickElement(continueButtonId);
		enterInput(loginPasswordTextId, password);
		clickElement(loginButtonId);
		return(new HomeMobPage(driver));
	}
}
