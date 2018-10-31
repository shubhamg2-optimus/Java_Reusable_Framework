package com.retail.webui.webdriverprovider;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.retail.webui.interfaces.Constants;

/**
 * Defines abstract methods for Driver classes
 *
 */
public abstract class DriverManager {
    private static final String WDM_TARGET_PATH = "wdm.targetPath";

    protected WebDriver driver;
    protected Logger log = Logger.getLogger(DriverManager.class);

    /**
     * Configure Logger
     */
    static {
        PropertyConfigurator.configure(Constants.PATH_LOGGER);
        System.setProperty(WDM_TARGET_PATH, Constants.PATH_SUPPORT);
    }

    protected abstract void startService(ITestContext context) throws Exception;

    protected abstract void createDriver(ITestContext context) throws Exception;

    public void quitDriver() {
        if (null != driver) {
            driver.close();
            driver = null;
        }

    }

    public WebDriver getDriver(ITestContext context) throws Exception {
        if (null == driver) {
            startService(context);
            createDriver(context);
        }
        return driver;
    }
}