package com.lululemon.webui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.retail.webui.utils.PageUtil;
import io.qameta.allure.Step;

public class LoginPage extends PageUtil {

    Logger log = Logger.getLogger(LoginPage.class);

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    
    @FindBy(xpath = "//li[@class='profile-section']//p[text()='SIGN IN']")
    private WebElement Signinbutton;
    
    @FindBy(xpath = "//*[@id=\"signin-submit\"]")
    private WebElement SignInLoginPage;
    
    @FindBy(linkText = "SIGN OUT")
    private WebElement homepagetext;
    
    
    @FindBy(xpath = "//*[@id=\"email-signin-account\"]")
    private WebElement textboxUsername;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement textboxPassword;

    @FindBy(xpath = "//*[@id=\"signin-submit\"]")
    private WebElement buttonSignIn;
    
    @FindBy(xpath = "//*[@id='continue']")
    private WebElement buttonContinue;

    @FindBy(xpath = "//*[@id='nav-link-yourAccount']")
    private WebElement navigationToSignIn;

    @FindBy(xpath = "//*[@id='nav-link-shopall']")
    private WebElement labelShopByDepartment;
    
    @FindBy(xpath="//*[@id='nav-link-yourAccount']/span[1]")
    private WebElement labelUserName;
    
    public void navigationToSignIn() {
        log.info("Navigating to sign in page");
        clickWebElementFluently(Signinbutton);
    }
    
    /**
     * Enter value in Username text box
     * 
     * @param username
     *            Username to login
     */
    public void enterUsername(String username) {
        log.info("Enter value in username textbox");
        clickAndEnterText(textboxUsername, username);
    }

    /**
     * Enter value in password text box
     * 
     * @param password
     *            Password to login
     */
    private void enterPassword(String password) {
        log.info("Enter value in password textbox");
        clickAndEnterText(textboxPassword, password);
    }

    /**
     * Click Login button
     */
    public void clickLoginButton() {
        log.info("Click Login button");
        clickWebElementFluently(buttonSignIn);
    }

    /**
     * Click Continue button
     */
    public void clickContinueButton() {
        log.info("Click Login button");
        clickWebElementFluently(buttonContinue);
    }
    /**
     * Log in to the application as per the passed parameters
     * 
     * @param username
     *            Username to login
     * @param password
     *            Password to login
     */
    @Step("Log in with username and password")
    public void loginApplication(String username, String password) {
        log.info("Log in to the application");
        navigationToSignIn();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        
    }
    
   
    /**
     * Verify that HomePage is loaded return True if page is loaded else false
     */
    @Step("Verify that HomePage is loaded")
    public boolean isLoginPageLoaded() {
        log.info("Verify that Login Page is loaded");
        if (isWebElementDisplayed(SignInLoginPage))
            return true;
        else
            return false;
    }

    /**
     * Verify that HomePage is loaded return True if page is loaded else false
     */
    @Step("Verify home page is displayed after successful login")
    public boolean isHomePageLoadedBySuccessfulLogin() {
        log.info("Verify that Login Page is loaded");
        if (isWebElementDisplayed(homepagetext))
            return true;
        else
            return false;
    }  
}
