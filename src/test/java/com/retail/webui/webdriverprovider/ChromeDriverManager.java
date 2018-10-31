package com.retail.webui.webdriverprovider;

import java.net.URL;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Implementation of ChromeDriver Manager
 * 
 */
public class ChromeDriverManager extends DriverManager {

    private static final String HUB_URL = "hubUrl";
    private static final String BROWSER = "browser";
    private static final String PLATFORM = "platform";
    private static final String DISABLE_INFOBARS = "disable-infobars";

    private static DriverManager INSTANCE = null;

    private ChromeDriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new ChromeDriverManager();

        return INSTANCE;
    }

    @Override
    public void startService(ITestContext context) throws Exception {
        log.info(String.format(Constants.TEXT_STARTING_BROWSER, Constants.TEXT_CHROME));
        if (!Constants.CONFIG_GRID_ENABLED)
            io.github.bonigarcia.wdm.ChromeDriverManager.getInstance().arch32().setup();
    }

    @Override
    public void createDriver(ITestContext context) throws Exception {
        XmlTest xmlTest = context.getCurrentXmlTest();
        try {
            if (Constants.CONFIG_GRID_ENABLED) {
                DesiredCapabilities caps = DesiredCapabilities.chrome();
                caps.setBrowserName(xmlTest.getParameter(BROWSER).toLowerCase());
                caps.setPlatform(Utilities.getPlatform(xmlTest.getParameter(PLATFORM).toLowerCase()));

                driver = new RemoteWebDriver(new URL(xmlTest.getParameter(HUB_URL)), caps);
            } else {
                ChromeOptions options = new ChromeOptions();
                options.addArguments(DISABLE_INFOBARS);
                
                driver = new ChromeDriver(options);
            }
        } catch (Exception e) {
            log.fatal(Constants.TEXT_ERROR_MESSAGE);
            log.fatal(e.getMessage());
            throw new Exception(Constants.TEXT_ERROR_MESSAGE + "\n" + e.getMessage());
        }
    }
}