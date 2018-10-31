package com.retail.webui.webdriverprovider;

import java.net.URL;
import java.nio.file.Paths;

import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Implementation of EdgeDriver Manager
 *
 */
public class EdgeDriverManager extends DriverManager {

    private static final String HUB_URL = "hubUrl";
    private static final String PLATFORM = "platform";
    private static final String WEBDRIVER_EDGE_DRIVER = "webdriver.edge.driver";
    private static final String MICROSOFT_WEBDRIVER_EXE = "MicrosoftWebDriver.exe";
    private static final String EAGER_PAGE_LOAD_STRATEGY = "eager";
    private static DriverManager INSTANCE = null;

    private EdgeDriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new EdgeDriverManager();

        return INSTANCE;
    }

    @Override
    protected void startService(ITestContext context) throws Exception {
        log.info(String.format(Constants.TEXT_STARTING_BROWSER, Constants.TEXT_EDGE));
        if (!Constants.CONFIG_GRID_ENABLED)
            System.setProperty(WEBDRIVER_EDGE_DRIVER,
                    Paths.get(Constants.PATH_SUPPORT, MICROSOFT_WEBDRIVER_EXE).toString());

    }

    @Override
    protected void createDriver(ITestContext context) throws Exception {
        XmlTest xmlTest = context.getCurrentXmlTest();
        try {
            if (Constants.CONFIG_GRID_ENABLED) {
                DesiredCapabilities caps = DesiredCapabilities.edge();
                caps.setBrowserName(DesiredCapabilities.edge().getBrowserName());
                caps.setPlatform(Utilities.getPlatform(xmlTest.getParameter(PLATFORM).toLowerCase()));

                driver = new RemoteWebDriver(new URL(xmlTest.getParameter(HUB_URL)), caps);
            } else {
                EdgeOptions options = new EdgeOptions();
                options.setPageLoadStrategy(EAGER_PAGE_LOAD_STRATEGY);
                driver = new EdgeDriver(options);
            }
        } catch (Exception e) {
            log.fatal(Constants.TEXT_ERROR_MESSAGE);
            log.fatal(e.getMessage());
            throw new Exception(Constants.TEXT_ERROR_MESSAGE + "\n" + e.getMessage());
        }
    }
}
