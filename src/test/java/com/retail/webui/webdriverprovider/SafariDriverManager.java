package com.retail.webui.webdriverprovider;

import java.net.URL;
import java.nio.file.Paths;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Implementation of SafariDriver Manager
 *
 */
public class SafariDriverManager extends DriverManager {

    private static final String HUB_URL = "hubUrl";
    private static final String BROWSER = "browser";
    private static final String PLATFORM = "platform";
    private static final String WEBDRIVER_SAFARI_DRIVER = "webdriver.safari.driver";
    private static final String SAFARI_WEBDRIVER_DRIVER = "SafariDriver.safariextz";
    private static DriverManager INSTANCE = null;

    private SafariDriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new SafariDriverManager();

        return INSTANCE;
    }

    @Override
    protected void startService(ITestContext context) {
        log.info(String.format(Constants.TEXT_STARTING_BROWSER, Constants.TEXT_SAFARI));
        System.setProperty(WEBDRIVER_SAFARI_DRIVER,
                Paths.get(Constants.PATH_SUPPORT, SAFARI_WEBDRIVER_DRIVER).toString());
    }

    @Override
    protected void createDriver(ITestContext context) throws Exception {
        XmlTest xmlTest = context.getCurrentXmlTest();
        try {
            if (Constants.CONFIG_GRID_ENABLED) {
                DesiredCapabilities caps = DesiredCapabilities.safari();
                caps.setBrowserName(xmlTest.getParameter(BROWSER).toLowerCase());
                caps.setPlatform(Utilities.getPlatform(xmlTest.getParameter(PLATFORM).toLowerCase()));
                driver = new RemoteWebDriver(new URL(xmlTest.getParameter(HUB_URL)), caps);
            } else {
                driver = new SafariDriver();
            }
        } catch (Exception e) {
            log.fatal(Constants.TEXT_ERROR_MESSAGE);
            throw new Exception(Constants.TEXT_ERROR_MESSAGE + "\n" + e.getMessage());
        }
    }
}