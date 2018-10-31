package com.retail.webui.utils;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.retail.webui.interfaces.Action;
import com.retail.webui.interfaces.Constants;

/**
 * Defines methods to extend WebDriver properties
 * 
 */
public class WebDriverUtilities {
    protected static Logger log = Logger.getLogger(WebDriverUtilities.class);
    private static String browserNames;

    /**
     * Wait until the code written in lambda executes successfully
     * 
     * @param element
     *            Accepts WebElement
     * @param action
     *            Execute code inside lambda
     */
    private static void waitUntilElementActionCompletes(WebElement element, Action action) {
        new FluentWait<>(element).withTimeout(Constants.CONFIG_EXPLICIT_WAIT, TimeUnit.SECONDS)
                .pollingEvery(Constants.CONFIG_POLLING_PERIOD, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        try {
                            action.apply();
                            return true;
                        } catch (Exception exception) {

                        }
                        return false;
                    }
                });
    }

    /**
     * Wait until the code written in lambda returns true
     * 
     * @param element
     *            Accepts WebElement
     * @param pred
     *            Accepts lambda expression which returns boolean
     */
    private static void waitUntilBooleanReturned(WebElement element, Predicate<WebElement> pred) {
        new FluentWait<>(element).withTimeout(Constants.CONFIG_EXPLICIT_WAIT, TimeUnit.SECONDS)
                .pollingEvery(Constants.CONFIG_POLLING_PERIOD, TimeUnit.MILLISECONDS).ignoring(WebDriverException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        try {
                            if (pred.test(element))
                                return true;
                        } catch (Exception exception) {


                        }
                        return false;
                    }
                });
    }

    /**
     * Make a click on the button
     * 
     * @param element
     *            WebElement of button
     */
    public static void clickButtonFluently(WebElement element) {
        try {
            log.info(String.format("Click on the Webelement"));
            waitUntilElementActionCompletes(element, () -> {
                element.click();
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to Click the Weblement", exception);
        }
    }

    /**
     * Make a decision
     * 
     * @param element
     *            WebElement of element
     * @param pred
     *            Predicate WebElement
     * @return true/false
     */
    public static boolean makeDecision(WebElement element, Predicate<WebElement> pred) {
        try {
            if (pred.test(element))
                return true;
        } catch (Exception exception) {

        }
        return false;
    }

    /**
     * Upload a file
     * 
     * @param element
     * @param value
     */
    public static void uploadAFile(WebElement element, String value) {
        try {
            log.info(String.format("Uploading a file", value));
            waitUntilElementActionCompletes(element, () -> {
                element.sendKeys(value);
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to upload the file", exception);
        }
    }

    /**
     * Comparison of web elements
     * 
     */
    public static boolean comparisonOfWebelement(WebElement element, String compareTo) {
        final String[] text = { null };
        try {
            log.info(String.format("Comparing the value"));
            waitUntilElementActionCompletes(element, () -> {
                text[0] = element.getText();
            });
            if (text[0].contains(compareTo)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Value is not as expected", exception);
            return false;
        }
    }

    /**
     * Enter keyboard keys
     * 
     * @param element
     * @param key1
     * @param key2
     */
    public static void enterKeyboardKeys(WebElement element, Keys key1, Keys key2) {
        try {
            waitUntilElementActionCompletes(element, () -> {
                element.sendKeys(key1, key2);
            });

        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to enter keys from keyboard", exception);
        }
    }

    /**
     * Enter Keys
     * 
     * @param driver
     * @param element
     * @param keys
     */
    public static void enterKeys(WebDriver driver, WebElement element, String keys) {
        try {
            waitUntilElementActionCompletes(element, () -> {
                new Actions(driver).sendKeys(keys).build().perform();
            });

        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to enter keys from keyboard", exception);
        }
    }

    /**
     * Perform drag and drop operation, need source and destination elements
     * 
     * @param driver
     *            Accepts WebDriver property
     * @param source
     *            Source WebElement
     * @param dest
     *            Destination WebElement
     */
    public static void dragAndDrop(WebDriver driver, WebElement source, WebElement dest) {
        try {
            log.info("Performing drag and drop operation");
            waitUntilElementActionCompletes(source, () -> {
                new Actions(driver).dragAndDrop(source, dest).build().perform();
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to drag and drop the Weblement", exception);
        }
    }

    /**
     * Perform move mouse and click operation
     * 
     * @param driver
     *            Accepts WebDriver property
     * @param element
     *            WebElement of button
     */
    public static void moveMouseAndClick(WebDriver driver, WebElement element) {
        try {
            log.info(String.format("Performing move mouse and click operation on %s button"));
            waitUntilElementActionCompletes(element, () -> {
                new Actions(driver).moveToElement(element).click().build().perform();
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to move the mouse and click the Weblement", exception);
        }

}
        
        public static void actionSendText(WebDriver driver, String value) {
            try {
                log.info(String.format("Performing action send keys operation on %s button"));    
                    new Actions(driver).sendKeys(value).build().perform();
            } catch (Exception exception) {
                Assert.fail("Assertion Failed: Unable to perform send key action", exception);
            }

    }

    /**
     * Clear the text box and send the text provided in 'value' argument
     * 
     * @param element
     *            WebElement of text box
     * @param value
     *            Text to send in text box
     */
    public static void clearTextboxAndFillValue(WebElement element, String value) {
        try {
            log.info(String.format("Entering %s into the textbox", value));
            waitUntilElementActionCompletes(element, () -> {
                element.clear();
                element.sendKeys(value);
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to clear textbox and fill the value", exception);
        }

    }

    /**
     * Click the text box and send the text provided in 'value' argument
     * 
     * @param element
     *            WebElement of text box
     * @param value
     *            Text value to send in text box
     */
    public static void clickAndEnterKeywords(WebElement element, String value) {
        try {
            log.info(String.format("Entering %s into the textbox", value));
            waitUntilElementActionCompletes(element, () -> {
                element.clear();
                element.click();
                element.sendKeys(value);
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to click and enter keyword in the textbox", exception);
        }
    }

    /**
     * Send the text provided in 'value' argument
     * 
     * @param element
     *            WebElement of text box
     * @param value
     *            Text value to send in text box
     */
    public static void fluentEnterKeyword(WebElement element, String value) {
        try {
            log.info(String.format("Entering %s into the textbox", value));
            waitUntilElementActionCompletes(element, () -> {
                element.sendKeys(value);
            });
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to enter the keyword in the textbox", exception);
        }
    }

    /**
     * Fluent wait to check visibility of element
     * 
     * @param element
     *            Wait until the visibility of the WebElement
     */
    public static void fluentWaitToCheckVisible(WebElement element) {
        try {
            log.info(String.format("wait until the visibility of of the WebElement"));
            waitUntilBooleanReturned(element, (ele) -> ele.isDisplayed());
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Element is not visible", exception);
        }
    }

    /**
     * Get the browser name, OS type and Application build number
     * 
     * @param driver
     *            WebDriver Reference
     * @return environmentDetails Array of environment details
     * @throws Exception
     *             If an exception occurs
     */
    public static HashMap<String, String> getBrowserOsAppVersion(WebDriver driver) throws Exception {
        Capabilities cap = ((RemoteWebDriver) driver).getCapabilities();
        HashMap<String, String> environmentDetails = new HashMap<String, String>();
        String currentBrowserName = cap.getBrowserName().toUpperCase();
        if (browserNames == null) {
            browserNames = " ";
            browserNames = browserNames.concat(currentBrowserName);
        } else if (!browserNames.contains(currentBrowserName)) {
            browserNames = browserNames.concat(", ").concat(currentBrowserName);
        }
        environmentDetails.put("Browser", browserNames);
        environmentDetails.put("Platform", Constants.PLATFORM_NAME.toUpperCase());
        return environmentDetails;
    }

    /**
     * Fluent wait to check presence
     * 
     * @param element
     * @return
     */
    public static boolean fluentWaitToCheckpresence(WebElement element) {
        try {
            log.info(String.format("wait until the visibility of of the WebElement"));
            waitUntilBooleanReturned(element, (ele) -> ele.isDisplayed());
            return true;
        } catch (Exception exception) {
            return false;
        }
    }
    
    /**
     * Get text from visible web elements
     * 
     * @param driver
     * @param locator
     * @return
     */
    public static String getTextInElement(WebDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
        String text = null;
        int attempts = 0;
        while (attempts++ < Constants.RETRY_ATTEMPTS) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (driver.findElement(locator).getText() != null) {
                    text = driver.findElement(locator).getText();
                    return text;
                }

            } catch (TimeoutException e) {
                log.info(text + "is not present on Page DOM yet: ");
            }
        }
        return text;
    }
}