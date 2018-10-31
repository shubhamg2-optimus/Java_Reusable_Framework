package com.retail.webui.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.retail.webui.interfaces.Constants;

public class PageUtil {

	protected Logger log = Logger.getLogger(PageUtil.class);
	public WebDriver driver;

	/**
	 * Initialize WebElements of child class using WebDriver object
	 * 
	 * @param driver
	 *            WebDriver instance
	 */
	public PageUtil(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
	/**
     * Generate random passowrd
     * 
     * @return
     */
    public String getRandomPassowrd(String password) {
    	String myString3 = password.replaceAll("[^A-Za-z]+", "");
        return myString3+"1234";
    }

	/**
	 * Get the driver instance
	 * 
	 * @return WebDriver instance
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * Verify that the element is displayed
	 * 
	 * @param element
	 *            WebElement of page in browser
	 * @return True if WebElement of page in browser is displayed
	 */
	public boolean isWebElementDisplayed(WebElement element) {
		try {
			WebDriverWait waiting = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
			waiting.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}
	
	public static void paused(double seconds) {
    	long start = System.currentTimeMillis();
    	while (System.currentTimeMillis() < (start + (seconds * 1000))) {
          }
    }

	public boolean isWebElementClickable(WebElement element) {
		try {
			WebDriverWait waiting = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
			waiting.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/**
	 * Wait until either of element is available
	 * 
	 * @param element1
	 * @param element2
	 * @return
	 */
	public WebElement waitUntilEitherOfTheWebElementDisplayed(WebElement element1, WebElement element2) {
		WebDriverWait waiting = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
		waiting.until(ExpectedConditions.or(ExpectedConditions.visibilityOf(element1),
				ExpectedConditions.visibilityOf(element2)));
		try {
			WebDriverWait waiting2 = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
			waiting2.until(ExpectedConditions.visibilityOf(element1));
			return element1;
		} catch (Exception exception) {
			return element2;
		}

	}

	/**
	 * Select all
	 * 
	 * @param element1
	 * @param element2
	 */
	public void selectAll(By element1, WebElement element2) {
		int totalheckbox = driver.findElements(element1).size();
		for (int i = 0; i < totalheckbox; i++) {
			driver.findElement(element1).click();
			isWebElementDisplayed(element2);
			try {
				Thread.sleep(Constants.CONFIG_SLEEP_TIME);
			} catch (Exception ex) {

			}
		}
	}

	/**
	 * Check if element is present by path
	 * 
	 * @param path
	 * @return
	 */
	public boolean isWebElementPresent(By path) {
		if (driver.findElements(path).size() > 0) {
			return true;
		} else
			return false;
	}

	/**
	 * Check if web element is present
	 * 
	 * @param element
	 * @return
	 */
	public boolean isWebElementPresent(WebElement element) {
		log.info("Verify that the element is displayed");
		WebDriverUtilities.fluentWaitToCheckpresence(element);
		return true;
	}

	/**
	 * Click on the input element and enter text in the input element
	 * 
	 * @param element
	 *            WebElement of page in browser
	 * @param text
	 *            to be entered in input element
	 */
	public void clickAndEnterText(WebElement element, String text) {
		log.info("Click on the element identified by "+element+" and entering"+text+" in the element");
		WebDriverUtilities.clickAndEnterKeywords(element, text);
	}

	/**
	 * Click on the WebElement
	 * 
	 * @param element
	 *            WebElement of page in browser which needs to be clicked
	 */
	public void clickWebElementFluently(WebElement element) {
		log.info("Click on the element identified by "+element);
		WebDriverUtilities.clickButtonFluently(element);
	}
     /**
      * pageLoad wait
      */
	public void letThePageLoad(int timeout)
	{
		driver.manage().timeouts().pageLoadTimeout(timeout, TimeUnit.SECONDS);
	}
	/**
	 * Get the text displayed in WebElements
	 * 
	 * @param elements
	 *            WebElement of page in browser
	 * @return List of text displayed in WebElements
	 */
	public List<String> getStringList(List<WebElement> elements) {
		List<String> list = new ArrayList<String>();

		for (WebElement element : elements)
			list.add(element.getText());
		return list;
	}

	/**
	 * Send "Enter key" command to browser
	 */
	public void pressEnterKey() {
		try {
			log.info("Press the enter key");
			Actions action = new Actions(driver);
			action.sendKeys(Keys.ENTER).build().perform();
		} catch (Exception exception) {
			Assert.fail("Assertion Failed: Unable to press the Enter key", exception);
		}
	}

	/**
	 * Select from drop down
	 * 
	 * @param id
	 * @param value
	 */
	public void selectFromDropDown(String id, String value) {
		Select item = new Select(driver.findElement(By.xpath(id)));
		item.selectByValue(value);
	}

	/**
	 * Navigate to page
	 * 
	 * @param pageName
	 */
	public void navigateToPage(String pageName) {
		driver.get(pageName);
	}

	/**
	 * Handle alert
	 * 
	 */
	public void handleAlert() {
		WebDriverWait waiting = new WebDriverWait(driver, Constants.CONFIG_EXPLICIT_WAIT);
		waiting.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
	}

	/**
	 * Compare weblements
	 * 
	 * @param element
	 * @param compareTo
	 * @return
	 */
	public boolean compareWebelement(WebElement element, String compareTo) {
		log.info("Compare the value to WebElement");
		return WebDriverUtilities.comparisonOfWebelement(element, compareTo);
	}

	/**
	 * Upload Files
	 * 
	 * @param element
	 * @param fileName
	 */
	public void uploadFiles(WebElement element, String fileName) {
		File fileToUpload = new File(fileName);
		WebDriverUtilities.uploadAFile(element, fileToUpload.getAbsolutePath());
	}

	/**
	 * Enter keys from keyboard
	 * 
	 * @param element
	 * @param key1
	 * @param key2
	 */
	public void enterKeysFromKeyboard(WebElement element, Keys key1, Keys key2) {
		log.info("Delete value from searchBox");
		WebDriverUtilities.enterKeyboardKeys(element, key1, key2);
	}

	/**
	 * Enter text
	 * 
	 * @param element
	 * @param text
	 */
	public void enterText(WebElement element, String text) {
		WebDriverUtilities.enterKeys(driver, element, text);
	}
	
	public String getRandomString() {
    	
		String alphabet= "abcdefghijklmnopqrstuvwxyz";
        String s = "";
        Random random = new Random();
        int randomLen = 1+random.nextInt(15);
        for (int i = 0; i < randomLen; i++) {
            char c = alphabet.charAt(random.nextInt(26));
            s+=c;
            }
		return s;  	
    }

	/**
	 * Generate random number
	 * 
	 * @return
	 */
	public int getRandomNumberInRange() {
		Random r = new Random();
		return r.nextInt(99999);
	}
	
	
    /**
     * Get the attribute value of the element
     * @param element 
     * */
    public String getAttributeValue(WebElement element,String attribute) {
    	isWebElementDisplayed(element);
    	String elementValue=element.getAttribute(attribute);
    	return elementValue;
    }
    

	/**
	 * Generate random first name
	 * 
	 * @return
	 */
	public String getRandomFirstName(String firstName) {
		String myString1 = firstName.replaceAll("[^A-Za-z]+", "");
	    return myString1;
	}
	
	/**
	 * Generate random last name
	 * 
	 * @return
	 */
	public String getRandomLastName(String lastName) {
		String myString2 = lastName.replaceAll("[^A-Za-z]+", "");
	    return myString2;
	}  

    /**
     * Send "Enter key" command to browser
     */
    public void pressEnterKey(WebElement element) {
        try {
            log.info("Press the enter key");
            Actions action = new Actions(driver);
            action.moveToElement(element).doubleClick().build().perform();
        } catch (Exception exception) {
            Assert.fail("Assertion Failed: Unable to press the Enter key", exception);
        }
    }

    /**
     * Select from drop down
     * 
     * @param id
     * @param value
     */
    public void selectFromDropDownRandom(String id) {
        Select item = new Select(driver.findElement(By.xpath(id)));
        for (int i = 0; i < 1; i++) {
        	List<WebElement> itemsInDropdown = item.getOptions();
        	int size = itemsInDropdown.size();
        	int randomNumber = ThreadLocalRandom.current().nextInt(1, size);
        	itemsInDropdown.get(randomNumber).click();   
        }
    }
    
    /**
     * Generate random Alphanumericc
     * 
     * @return
     */
    public String getRandomAlphaNumeric() {
    	String name = UUID.randomUUID().toString();
        return name;
    }
    
    public String getRandomEmail(String randomAlphanumeric) {
    	String myString = randomAlphanumeric.replaceAll("[^A-Za-z]+", "");
    	return randomAlphanumeric+"@"+myString+".com";
    }
    
    public String getRandomEmail() {
   	 int value = (int) Math.floor(Math.random() * 90000 + 10000);
        String emailAddress = "autotest" + value + "@autotest.com";
        return emailAddress;
   }
	
}