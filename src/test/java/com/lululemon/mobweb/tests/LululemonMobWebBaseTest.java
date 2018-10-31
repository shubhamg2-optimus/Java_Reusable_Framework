package com.lululemon.mobweb.tests;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import com.amazon.mob.tests.BaseMobTest;
import com.lululemon.mobweb.interfaces.Constants;

import io.appium.java_client.android.AndroidDriver;

@Listeners(com.retail.webui.utils.TestListener.class)
public class LululemonMobWebBaseTest {
	
	public static Logger LOGGER = Logger.getLogger(BaseMobTest.class);
	public static DesiredCapabilities capabilities;
	public static AndroidDriver driver;
	
	static {
		PropertyConfigurator.configure("log4j.properties");
	}
	
	/**
	 * Instantiates AndroidDriver
	 * @throws IOException 
	 */
	@BeforeClass
	public void instantiateDriver() throws IOException {
		LOGGER.info("Instantiating android driver");
		capabilities = new DesiredCapabilities();
		capabilities.setCapability("noReset", "true");
		try {
			capabilities.setCapability("deviceName", Constants.CONFIG_DEVICE_NAME);
			capabilities.setCapability("platformVersion", Constants.CONFIG_ANDROID_VERSION);
            capabilities.setCapability("browserName",Constants.CONFIG_BROWSER);
			driver = new AndroidDriver(new URL(Constants.CONFIG_APPIUM_URL), capabilities);
		} catch (Exception exception) {
			LOGGER.error("Something wrong happened while starting the test case session");
		}
	}
	
	@AfterClass
	public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
	
	@BeforeMethod
	public void beforeTest() {
        try {
            driver.manage().timeouts().implicitlyWait(Constants.CONFIG_IMPLICIT_WAIT, TimeUnit.SECONDS);
            driver.get(Constants.CONFIG_APP_URL); 
        } catch (Exception exception) {
        	LOGGER.error("Something wrong happened while starting the test case session");
        }
    }
	
	/**
     * Quit AndroidDriver
     */
    @AfterMethod
    public void tearDown() throws InterruptedException {
        try {
        	LOGGER.info("Quitting driver");        	
        } catch (Exception exception) {
        	LOGGER.error("Something wrong happened while ending the test case session");
        }
    }
    
    public WebDriver getDriver() {
		return LululemonMobWebBaseTest.driver;
	}
}