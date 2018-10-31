package com.amazon.mob.interfaces;


import java.util.Properties;

import com.retail.webui.utils.Utilities;

/**
 * Defines all constants
 * 
 */
public interface Constants {

	String PATH_TEST_DATA = "data/test_data.properties";
	String PATH_CONFIG_DATA="config/config.properties";
	Properties TEST_DATA_FILE = Utilities.readPropFile(PATH_TEST_DATA);
	Properties CONFIG_DATA_FILE=Utilities.readPropFile(PATH_CONFIG_DATA);
	
	String DOES_ELEMENT_EXIST_INFO = "Verifying that the element exists identified by - ";
    String CLICK_ELEMENT_INFO = "Clicking on the element identified by ";
    String KEYBOARD_NOT_FOUND_INFO = "The keyboard is not present, hence handling the exception and continuing with the scripts";
    String ELEMENT_DISABLED_ERROR = "Unable to click on specified element. Element is disabled";
    String PARSE_ANDROID_VERSION_INFO = "Parsing the android version of the device to single decimal place";
    String VERSION_REGEX = "\\d.\\d"; 
    
	/**
     * Test data Constants from test_data.properties
     */
    String TEXT_PRODUCT_NAME1=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "searchproductname1");
    String TEXT_PRODUCT_NAME2=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "searchproductname2");
    String TEXT_USERFULLNAME=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "fullusername");
    String TEXT_PHONENUMBER=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "phonenumber");
    String TEXT_PIN=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "pincode");
    String TEXT_ADDRESS_LINE1=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "addressline1");
    String TEXT_ADDRESS_LINE2=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "addressline2");
    String CONFIG_USER_NAME = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "username");
    String CONFIG_PASSWORD = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "password");
    
    /**
     * Constants from config.properties
     */
    String CONFIG_APP_ACTIVITY = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "appActivity");
	String CONFIG_APP_PACKAGE = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "appPackage");
	String CONFIG_DEVICE_NAME = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "devicename");
	String CONFIG_APPIUM_URL = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "appiumurl");
	String CONFIG_APP_ID = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "appid");
	String CONFIG_NORESET = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "noReset");
	int CONFIG_IMPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "implicit_wait"));
	int CONFIG_EXPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "explicit_wait"));
}