package com.retail.api.interfaces;

import java.util.Properties;

import com.retail.webui.utils.Utilities;

/**
 * Defines all constants
 * 
 */
public interface Constants {
/**
 * Text Constants
 */
	String PATH_TEST_DATA = "data/test_data.properties";
	String PATH_CONFIG = "config/config.properties";	
	String PATH_RESOURCES = "config/apiResources.properties";
	String PATH_END_POINTS = "config/apiEndPoints.properties";
	String PATH_HUB_CONFIG = "gridconfig/hubConfig.json";
	String PATH_NODE_CONFIG = "gridconfig/nodeConfig_1.json";
	
	Properties RESOURCE_FILE = Utilities.readPropFile(PATH_RESOURCES);
	Properties END_POINT_FILE = Utilities.readPropFile(PATH_END_POINTS);
	Properties TEST_DATA_FILE = Utilities.readPropFile(PATH_TEST_DATA);
	Properties CONFIG_FILE = Utilities.readPropFile(PATH_CONFIG);
}
