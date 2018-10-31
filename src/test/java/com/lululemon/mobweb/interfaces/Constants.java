package com.lululemon.mobweb.interfaces;


import java.util.Properties;

import com.retail.webui.utils.Utilities;

/**
 * Defines all constants
 * 
 */
public interface Constants {

	String PATH_TEST_DATA = "data/lululemon_test_data.properties";
	String PATH_CONFIG_DATA="config/config.properties";
	Properties TEST_DATA_FILE = Utilities.readPropFile(PATH_TEST_DATA);
	Properties CONFIG_DATA_FILE=Utilities.readPropFile(PATH_CONFIG_DATA);

	/**
     * Test data Constants from test_data.properties
     */
    String TEXT_PRODUCT_NAME=Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "productname");
    String CONFIG_USER_NAME = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "username");
    String CONFIG_PASSWORD = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "password");
    String CONFIG_NEWACCOUNT_PASSWORD = Utilities.getValueFromPropertiesFile(TEST_DATA_FILE, "newAccountPassword");
    
    /**
     * Constants from config.properties
     */   
    String CONFIG_APP_URL = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "lululemon_url");
	String CONFIG_DEVICE_NAME = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "devicename");
	String CONFIG_APPIUM_URL = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "appiumurl");
	String CONFIG_BROWSER = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "name");
	String CONFIG_ANDROID_VERSION = Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "androidVersion");
	int CONFIG_IMPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "implicit_wait"));
	int CONFIG_EXPLICIT_WAIT = Integer.parseInt(Utilities.getValueFromPropertiesFile(CONFIG_DATA_FILE, "explicit_wait"));
}