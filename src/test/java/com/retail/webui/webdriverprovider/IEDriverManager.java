package com.retail.webui.webdriverprovider;

import java.net.URL;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.xml.XmlTest;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Implementation of IEDriver Manager
 */
public class IEDriverManager extends DriverManager {

    private static final String HUB_URL = "hubUrl";
    private static final String BROWSER = "browser";
    private static final String PLATFORM = "platform";
    private static DriverManager INSTANCE = null;
    private static final String IE_ENSURE_CLEAR_SESSION = "ie.ensureCleanSession";

    private IEDriverManager() {
    }

    public static DriverManager getInstance() {
        if (INSTANCE == null)
            INSTANCE = new IEDriverManager();
        return INSTANCE;
    }

    @Override
    protected void startService(ITestContext context) throws Exception {
        log.info(String.format(Constants.TEXT_STARTING_BROWSER, Constants.TEXT_IE));
        if (!Constants.CONFIG_GRID_ENABLED)
            io.github.bonigarcia.wdm.InternetExplorerDriverManager.getInstance().arch32().setup();
    }

    @Override
    protected void createDriver(ITestContext context) throws Exception {
        XmlTest xmlTest = context.getCurrentXmlTest();
        try {
            if (Constants.CONFIG_GRID_ENABLED) {
                Runtime.getRuntime().exec("RunDll32.exe InetCpl.cpl,ClearMyTracksByProcess 2");
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setBrowserName(xmlTest.getParameter(BROWSER).toLowerCase());
                caps.setPlatform(Utilities.getPlatform(xmlTest.getParameter(PLATFORM).toLowerCase()));
                driver = new RemoteWebDriver(new URL(xmlTest.getParameter(HUB_URL)), caps);
            } else {
                DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
                caps.setCapability(IE_ENSURE_CLEAR_SESSION, true);
                driver = new InternetExplorerDriver(caps);
            }
        } catch (Exception e) {
            log.fatal(Constants.TEXT_ERROR_MESSAGE);
            log.fatal(e.getMessage());
            throw new Exception(Constants.TEXT_ERROR_MESSAGE + "\n" + e.getMessage());
        }
    }
}