package com.lululemon.webui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.lululemon.webui.interfaces.Constants;
import com.retail.webui.utils.PageUtil;
import io.qameta.allure.Step;

public class CreateAccountPage extends PageUtil {

    Logger log = Logger.getLogger(CreateAccountPage.class);
    private static String randomString;
    
    public CreateAccountPage(WebDriver driver) {
        super(driver);
        randomString=getRandomString();
    }
    
    @FindBy(linkText = "SIGN OUT")
    private WebElement homepagetext;
    
    @FindBy(xpath = "//li[@class='profile-section']//p[text()='SIGN IN']")
    private WebElement SignInButton;
    
    @FindBy(xpath="//*[@id='createAccountSubmit']")
    private WebElement buttonCreateAccount;
    
    @FindBy(xpath="//*[@id='ap_customer_name']")
    private WebElement textBoxCustomerName;
    
    @FindBy(xpath="//*[@id=\"email-signup-account\"]")
    private WebElement textBoxCustomerEmail;
    
    @FindBy(xpath="//*[@id=\"new-password\"]")
    private WebElement textBoxCustomerPassword;
    
    @FindBy(xpath="//*[@id='ap_password_check']")
    private WebElement textBoxCustomerConfirmPassword;
    
    @FindBy(xpath="//*[@id=\"signup-submit\"]")
    private WebElement buttonCreatelululemonAccount;
    
    @FindBy(xpath="//*[@id='nav-link-yourAccount']/span[1]")
    private WebElement labelNewCustomerName;
    /**
     * Enter value in CustomerName text box
     * 
     * 
     */
    public void enterCustomername() {
        log.info("Enter value in CustomerName textbox");
        clickAndEnterText(textBoxCustomerName, randomString);
    }
    
    /**
     * Enter value in CustomerEmail text box
     * 
     * 
     */
    @Step("Enter email address")
    public void enterCustomerEmail() {
        log.info("Enter value in Customer Email textbox");
        clickAndEnterText(textBoxCustomerEmail, getRandomEmail());
    }
    
    /**
     * Enter value in CustomerPassword text box
     * 
     * 
     */
    @Step("Enter password")
    public void enterCustomerPassword() {
        log.info("Enter value in Customer Password textbox");
        clickAndEnterText(textBoxCustomerPassword, Constants.CONFIG_NEWACCOUNT_PASSWORD);
    }
    
    /**
     * Enter value in CustomerConfirmPassword text box
     * 
     * 
     */
    public void enterCustomerConfirmPassword() {
        log.info("Enter value in Confirm Customer Password textbox");
        clickAndEnterText(textBoxCustomerConfirmPassword, randomString);
    }
    
    /**
     * CLick create amazon account
     * 
     * 
     */
    public void clickCreatelululemonAccount() {
        log.info("CLick on create your amazon account");
        clickWebElementFluently(buttonCreatelululemonAccount);
    }
    
    public void navigationToSignIn() {
        log.info("Navigating to sign in page");
        clickWebElementFluently(SignInButton);
    }
    
    public void navigationToCreateAccountPage() {
    	log.info("Navigating to create account page");
        clickWebElementFluently(buttonCreateAccount);
    }
    
    @Step("Create new user account")
    public void createAccount() {
    	navigationToSignIn();
    	enterCustomerEmail();
    	enterCustomerPassword();
    	clickCreatelululemonAccount();
    }
    
    /**
     * Verify that HomePage is loaded return True if page is loaded else false
     */
    @Step("Verify Account is created")
    public boolean isAccountCreatedSuccessfully() {
    	log.info("Verify that account is created");
    	if(labelNewCustomerName.getText().contains(randomString)) {
    		return true;
    	}
    	else return false;
    }

@Step("Verify the new account is created")
public boolean isLoginPageLoaded() {
    log.info("Verify that Login Page is loaded");
    if (isWebElementDisplayed(homepagetext))
        return true;
    else
        return false;
}
}
