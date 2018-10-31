package com.lululemon.webui.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.lululemon.webui.interfaces.Constants;
import com.retail.webui.utils.WebDriverUtilities;
import com.retail.webui.webdriverprovider.DriverManager;
import com.retail.webui.webdriverprovider.DriverManagerFactory;

/**
 * Defines common mzethods for all test classes
 * 
 */
@Listeners(com.retail.webui.utils.TestListener.class)
public class LululemonBaseTest {
    public static HashMap<String, String> latency = new HashMap<>();
    protected Logger log;
    public static long startTime;
    public static long endTime;

    public LululemonBaseTest() {
        log = Logger.getLogger(LululemonBaseTest.class);
    }

    protected DriverManager driverManager;
    private WebDriver driver;
    private static int environment = 0;
    HashMap<String, String> environmentDetails = new HashMap<String, String>();

    @BeforeClass
    @Parameters({ "browser", "platform", "hubUrl" })

    /**
     * DriverManagerfactory initialization
     * 
     * @param browser
     *            : browser name
     * @param platform
     *            : platform name
     * @param hubUrl
     *            : Url on which hub is running
     */
    public void beforeTest(@Optional String browser, @Optional String platform, @Optional String hubUrl) {
        driverManager = (browser != null) ? DriverManagerFactory.getManager(browser)
                : DriverManagerFactory.getManager(Constants.CONFIG_BROWSER_NAME);
    }

    /**
     * Navigate to application and get the environment details
     * 
     * @param context
     *            Passes control to WebDriver
     * @throws Exception
     *             If an exception occurs
     */
    @BeforeMethod(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        driver = driverManager.getDriver(context);
        log.info(String.format("Navigating to %s", Constants.CONFIG_APP_URL));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.CONFIG_IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.get(Constants.CONFIG_APP_URL);
        if (environment < Constants.CONFIG_MAXIMUM_NUMBER_OF_BROWSERS && driver != null) {
            environmentDetails = WebDriverUtilities.getBrowserOsAppVersion(driver);
            //Utilities.writePropertiesFile(environmentDetails);
            environment++;
        }
        if(driver.findElements(By.xpath("//button[text()='No Thanks']")).size()>0) {
        	driver.findElement(By.xpath("//button[text()='No Thanks']")).click();
        }
    }

    /**
     * Closes all the driver instances
     * 
     * @throws IOException
     *             If an input or output exception occurs
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws IOException {
    	driverManager.quitDriver();
        driver.quit();
    	
    }

    /**
     * Get the driver instance
     * 
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return this.driver;
    }
    
    /**
     * Set Property
     * 
     */
    @AfterSuite
    public static void afterSuite() {
        setProperty();
    }
    
    /**
     * Load properties file
     * 
     * @param fileName
     * @return
     */
    public static Properties loadPropertiesFile(String fileName) {
        Properties properties = new Properties();
        try {
            InputStream in;
            in = new FileInputStream(fileName);
            properties.load(in);
        } catch (FileNotFoundException exception) {
            Assert.fail("File not found");
        } catch (IOException exception) {
            Assert.fail("Input output exception occurred");
        }
        
        return properties;
    }
    
    /**
     * Setting the property
     */
     public static void setProperty() {
            try {
                Properties prop = new Properties(); 
                OutputStream out;
                out = new FileOutputStream(System.getProperty("user.dir")+"//artifacts//"+"latencyReport.properties");
                prop.putAll(latency);
                prop.store(out, "Property Saved");
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                Assert.fail("Input output exception occurred");
            }
     }
}