package com.amazon.mob.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.amazon.mob.interfaces.Constants;

import io.appium.java_client.android.AndroidDriver;

public class BaseMobPage {

    private static final String CONSTRUCTOR_INFO = "In BasePage() constructor";
    public static Logger LOGGER = Logger.getLogger(BaseMobPage.class);
    protected AndroidDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in BaseTest.java
     */
    public BaseMobPage(AndroidDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
        LOGGER.info(CONSTRUCTOR_INFO);
    }
    
    /**
     * This method parses the android version to single decimal place
     *
     * @param version Version of the android device
     */
    public static String parseAndroidVersion(String version) {
        LOGGER.info(Constants.PARSE_ANDROID_VERSION_INFO);
        String androidVer = null;
        Matcher versionMatcher = Pattern.compile(Constants.VERSION_REGEX).matcher(version);
        if (versionMatcher.find()) {
            androidVer = versionMatcher.group(0);
        }
        return androidVer;
    }
    
    /**
     * This element verifies the existence of element
     *
     * @param locator locator of the element
     * @return true if element exists
     */
    public boolean doesElementExist(By locator) {
        LOGGER.info(Constants.DOES_ELEMENT_EXIST_INFO + locator);
        return (driver.findElements(locator).size() != 0);
    }

    /**
     * This method enters input in the field specified in the parameters
     *
     * @param identifier locator of the text element
     * @param input      text to be entered in the text element
     */
    protected void enterInput(By identifier, String input) {
        LOGGER.info("Entering " + input + " in the field identified by " + identifier);
        wait.until(ExpectedConditions.presenceOfElementLocated(identifier));
        WebElement element = driver.findElement(identifier);
        if (element.isEnabled()) {
            element.click();
            element.clear();
            element.sendKeys(input);
            try {
                driver.hideKeyboard();
            } catch (WebDriverException exception) {
                LOGGER.info(Constants.KEYBOARD_NOT_FOUND_INFO);
            }
        } else {
            LOGGER.error("Unable to enter input. Text field is disabled");
        }
    }

    /**
     * This method clicks on the element with id given in parameter
     *
     * @param identifier locator of the element
     */
    protected void clickElement(By identifier) {
        LOGGER.info(Constants.CLICK_ELEMENT_INFO + identifier);
        wait.until(ExpectedConditions.visibilityOfElementLocated(identifier));
        WebElement element = driver.findElement(identifier);
        if (element.isEnabled()) {
            element.click();
        } else {
            LOGGER.error(Constants.ELEMENT_DISABLED_ERROR);
        }
    }  
}
