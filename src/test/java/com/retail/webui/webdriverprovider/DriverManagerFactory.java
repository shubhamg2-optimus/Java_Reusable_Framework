package com.retail.webui.webdriverprovider;

/**
 * Decides which browser will be launched
 *
 */
public class DriverManagerFactory {

    private final static String CHROME = "CHROME";
    private final static String FIREFOX = "FIREFOX";
    private final static String INTERNET_EXPLORER = "INTERNET EXPLORER";
    private final static String MICROSOFT_EDGE = "MICROSOFT EDGE";
    private final static String SAFARI = "SAFARI";

    private DriverManagerFactory() {
        
    }

    public static DriverManager getManager(String type) {
        DriverManager driverManager;

        switch (type.toUpperCase()) {
        case CHROME:
            driverManager = ChromeDriverManager.getInstance();
            break;
        case FIREFOX:
            driverManager = FirefoxDriverManager.getInstance();
            break;
        case INTERNET_EXPLORER:
            driverManager = IEDriverManager.getInstance();
            break;
        case MICROSOFT_EDGE:
            driverManager = EdgeDriverManager.getInstance();
            break;
        case SAFARI:
            driverManager = SafariDriverManager.getInstance();
            break;
        default:
            driverManager = ChromeDriverManager.getInstance();
            break;
        }
        return driverManager;
    }
}