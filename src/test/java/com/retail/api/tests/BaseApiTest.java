package com.retail.api.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.retail.api.utils.RestTemplate;
import com.retail.webui.interfaces.Constants;
import com.retail.webui.webdriverprovider.DriverManager;
import com.retail.webui.webdriverprovider.DriverManagerFactory;

public class BaseApiTest extends RestTemplate {
	String oauth_access_token = "https://login.microsoftonline.com/SPUD365.onmicrosoft.com/oauth2/authorize?client_id=0dad2b01-80c7-48ba-ad76-bd205787a050&response_type=code";
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;
    protected DriverManager driverManager;
    private WebDriver driver;
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
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.CONFIG_IMPLICIT_WAIT, TimeUnit.SECONDS);
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
    }

    /**
     * Get the driver instance
     * 
     * @return WebDriver instance
     */
    public WebDriver getDriver() {
        return this.driver;
    }
}
