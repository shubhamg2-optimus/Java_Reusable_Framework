package com.retail.webui.interfaces;

import java.nio.file.Paths;
import java.util.Properties;

import com.retail.webui.interfaces.Constants;
import com.retail.webui.utils.Utilities;

/**
 * Defines all constants
 * 
 */
public interface Constants {

    /**
     * Text Constants
     */
    String TEXT_CHROME = "chrome";
    String TEXT_IE = "ie";
    String TEXT_EDGE = "edge";
    String TEXT_SAFARI = "safari";
    String TEXT_FIREFOX = "firefox";
    String TEXT_SUPPORT = "support";
    String TEXT_PHANTOM = "phantom";
    String TEXT_CHROME_EXE = "chromedriver.exe";
    String TEXT_GECKO_EXE = "geckodriver.exe";
    String TEXT_EDGE_EXE = "MicrosoftWebDriver.exe";
    String TEXT_IE_EXE = "IEDriverServer.exe";
    String TEXT_SCREENSHOTS = "screenshots";
    String TEXT_BROWSER = "Browser";
    String TEXT_PLATFORM = "Platform";
    String TEXT_APP_VERSION = "AppVersion";
    String TEXT_TEST_SCRIPT = "Test Script";
    String TEXT_ASSERTION_ERROR = "AssertionError";
    String TEXT_ERROR_CAUSE = "ErrorCause";
    String TEXT_STARTING_BROWSER = "Starting browser: %s";
    String TEXT_STARTING_TESTCASE = "********************** Starting test cases *********************";
    String TEXT_FAILED = "failed";
    String TEXT_TRUE = "true";
    String TEXT_ERROR_MESSAGE = "Error occured while creating browser instance \n"
            + "Please make sure browser name is: 'CHROME', 'FIREFOX', 'INTERNET EXPLORER', 'MICROSOFT EDGE' or 'SAFARI' \n"
            + "Platform name is: 'WINDOWS', 'LINUX' or 'MAC'";
    String XMAPI = "/api/xm/1/";
    String REAPI = "/reapi/2015-04-01/";
    String PERSON_ENDPOINT = "people/";
    String USERS_ENDPOINT = "users/";
    String TRIGGER_ENDPOINT = "triggers/"; 
    String PLAN_ENDPOINT = "plans/";
    String FORMS_ENDPOINT = "forms/";
    String EVENTS_ENDPOINT ="events/";
    
    /**
     * Path of Constants
     */
    String PATH_ROOT = System.getProperty("user.dir");
    String PATH_CONFIG = "config/config.properties";
    String PATH_TEST_DATA = "data/test_data.properties";
    String PATH_HUB_CONFIG = "gridconfig/hubConfig.json";
    String PATH_NODE_CONFIG = "gridconfig/nodeConfig_1.json";
    String PATH_LOGGER = Paths.get(PATH_ROOT, "log4j.properties").toString();
    String PATH_SUPPORT = Paths.get(PATH_ROOT, TEXT_SUPPORT).toString();
    String PATH_SCREENSHOT_FOLDER = Paths.get(PATH_ROOT, TEXT_SCREENSHOTS).toString();
    String PATH_CHROME_EXE = Paths.get(PATH_SUPPORT, TEXT_CHROME_EXE).toString();
    String PATH_GECKO_EXE = Paths.get(PATH_ROOT, "node_modules", "geckodriver", TEXT_GECKO_EXE).toString();
    String PATH_EDGE_EXE = Paths.get(PATH_ROOT, "node_modules", "edgedriver", "lib", "edgedriver", TEXT_EDGE_EXE)
            .toString();
    String PATH_IE_EXE = Paths.get(PATH_SUPPORT, TEXT_IE_EXE).toString();
    String PATH_JAVA_EXE = Paths.get(PATH_ROOT, "src", "test", "java", "com", "optimus", "testcases").toString();
    String PATH_UPLOAD_USER_FILE = "/src/test/resources/data/";
    Properties CONFIG_FILE = Utilities.readPropFile(PATH_CONFIG);
    Properties TEST_DATA_FILE = Utilities.readPropFile(PATH_TEST_DATA);

    /**
     * Read constants from config file
     */
	String CONFIG_USERID = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "userId");
    String CONFIG_USER_NAME = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "username");
    String CONFIG_PASSWORD = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "password");
    
    
    /**
	 * Read constants from config file
	 */
	String CONFIG_APP_URL = Utilities.getValueFromPropertiesFile(CONFIG_FILE, "app_url");
	String CONFIG_BROWSER_NAME = Utilities.getValueFromPropertiesFile(CONFIG_FILE, "name");
	String CONFIG_API_URL = Utilities.getValueFromPropertiesFile(CONFIG_FILE, "api_url");

	int CONFIG_MAXIMUM_NUMBER_OF_BROWSERS = Integer
			.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "number"));
	int CONFIG_IMPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "implicit_wait"));
	int CONFIG_EXPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "explicit_wait"));
	int CONFIG_SLEEP_TIME = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "sleep_time"));
	int CONFIG_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "normal_wait"));
	int CONFIG_PAGE_LOAD = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "pageload_wait"));
	int CONFIG_POLLING_PERIOD = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "polling_interval"));
	int TIMEOUT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "timeout"));
	int RETRY_ATTEMPTS = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "retry_attempts"));
	int START_VALUE = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_FILE, "start_value"));
	boolean CONFIG_GRID_ENABLED = Boolean
			.valueOf(Utilities.getValueFromPropertiesFile(Constants.CONFIG_FILE, "enabled"));
	String PLATFORM_NAME = System.getProperty("os.name");

    /**
     * Pattern constants
     */
    String PATTERN_UTC = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    String PATTERN_FILE = "yyyy_MM_dd'T'HH_mm_ss_SSS'Z'";
}