package com.lululemon.mobweb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lululemon.mobweb.interfaces.Constants;
import com.retail.webui.utils.PageUtil;

import io.qameta.allure.Step;

public class LoginPage extends PageUtil {
	
	public LoginPage(WebDriver driver) {
        super(driver);
    }
	
	 @FindBy(id = "email-signin-account")
	 private WebElement userEmail;
	 
	 @FindBy(id = "password")
	 private WebElement userPassword;
	 
	 @FindBy(id = "signin-submit")
	 private WebElement buttonSignIn;
	 
	 @FindBy(xpath = "//div[@class='my-account-content dashboard']")
	 private WebElement homepagetext;
	 
	 @FindBy(id = "email-signup-account")
	 private WebElement newUserEmail;
	 
	 @FindBy(id = "new-password")
	 private WebElement newUserPassword;
	 
	 @FindBy(id = "signup-submit")
	 private WebElement createAccountButton;
	 
	 @FindBy(xpath = "//span[@class='replace-checkbox-label']")
	 private WebElement checkBox;

	 @Step("Login with username and password")
	 public void loginApplication(String username, String password) {
	        log.info("Log in to the application");
	        isWebElementDisplayed(userEmail);
	        userEmail.click();
	        userEmail.sendKeys(username);
	        userPassword.click();
	        userPassword.sendKeys(password);
	        buttonSignIn.click();       
	 }
	 
	 @Step("Verify home page is visible")
	 public boolean isHomePageLoadedBySuccessfulLogin() {
	        log.info("Verify that user's account page is loaded");
	        if (isWebElementDisplayed(homepagetext))
	            return true;
	        else
	            return false;
	  } 
	 
	 @Step("Create new user account")
	 public void createAccount() {
	        log.info("Creating new user account");
	        isWebElementDisplayed(newUserEmail);
	        newUserEmail.click();
	        newUserEmail.sendKeys(getRandomEmail());
	        newUserPassword.click();
	        newUserPassword.sendKeys(Constants.CONFIG_NEWACCOUNT_PASSWORD);
	        checkBox.click();
	        createAccountButton.click();
	 } 	 
}
