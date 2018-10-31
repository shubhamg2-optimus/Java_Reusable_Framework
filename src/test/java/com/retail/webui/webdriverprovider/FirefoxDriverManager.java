package com.retail.webui.webdriverprovider;

import java.net.URL;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Implementation of FirefoxDriver Manager
 * 
 */
public class FirefoxDriverManager extends DriverManager {

    private static final String HUB_URL = "hubUrl";
    private static final String BROWSER = "browser";
    private static final String PLATFORM = "platform";

    private static DriverManager INSTANCE = null;

    private FirefoxDriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new FirefoxDriverManager();

        return INSTANCE;
    }

    @Override
    public void startService(ITestContext context) throws Exception {
        log.info(String.format(Constants.TEXT_STARTING_BROWSER, Constants.TEXT_FIREFOX));
        if (!Constants.CONFIG_GRID_ENABLED)
            io.github.bonigarcia.wdm.FirefoxDriverManager.getInstance().arch64().setup();
    }

    @Override
    public void createDriver(ITestContext context) throws Exception {
        XmlTest xmlTest = context.getCurrentXmlTest();

        try {
            if (Constants.CONFIG_GRID_ENABLED) {
                FirefoxOptions options = new FirefoxOptions();
                DesiredCapabilities caps = DesiredCapabilities.firefox();
                caps.setBrowserName(xmlTest.getParameter(BROWSER).toLowerCase());
                caps.setPlatform(Utilities.getPlatform(xmlTest.getParameter(PLATFORM).toLowerCase()));
                driver = new RemoteWebDriver(new URL(xmlTest.getParameter(HUB_URL)), options.toCapabilities());
            } else {
                driver = new FirefoxDriver();
            }
        } catch (Exception e) {
            log.fatal(Constants.TEXT_ERROR_MESSAGE);
            log.fatal(e);
            throw new Exception(Constants.TEXT_ERROR_MESSAGE + "\n" + e.getMessage());
        }
    }
}