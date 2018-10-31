package com.retail.webui.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import com.retail.webui.interfaces.Constants;

import io.qameta.allure.Allure;

/**
 * Defines methods to extend common functionalities of framework
 *
 */
public class Utilities {

	static Logger log = Logger.getLogger(Utilities.class);

	/**
	 * Get current time stamp based on the pattern passed in method argument
	 * 
	 * @param pattern
	 *            Accepts DateTimeFormatter pattern
	 * @return Current time stamp
	 */
	public static String getCurrentTimeStamp(String pattern) {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
	}

	/**
	 * It captures screenshot and returns the path of the newly saved screenshot
	 * 
	 * @param webDriver
	 *            WebDriver reference
	 * @param className
	 *            Class name of the test that is in execution
	 * @param methodName
	 *            Method name of the test that is execution
	 */
	public static void takeScreenshot(ITestResult result, WebDriver webDriver) {
		String className = result.getTestClass().getRealClass().getSimpleName();
		String methodName = result.getMethod().getMethodName();
		String path = Paths
				.get(Constants.PATH_SCREENSHOT_FOLDER,
						String.format("%s_%s.png", methodName, Utilities.getCurrentTimeStamp(Constants.PATTERN_FILE)))
				.toString();

		if (webDriver != null) {
			try {
				File f = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(f, new File(path));
				Path content = Paths.get(path);

				try (InputStream is = Files.newInputStream(content)) {
					Allure.addAttachment(className + " - " + methodName, is);
				}
			} catch (IOException e) {
				log.error("Error occured while taking screenshots");
				log.error(e.getMessage());
			}
		}

	}

	/**
	 * Read the properties file
	 * 
	 * @param Path
	 *            of the property file
	 * @return Property file
	 */
	public static Properties readPropFile(String filePath) {
		Properties properties = new Properties();
		try {
			InputStream inputStream = Utilities.class.getClassLoader().getResourceAsStream(filePath);
			properties.load(inputStream);
		} catch (Exception exception) {
			log.error("Unable to read the properties file");
			log.error(exception.getMessage());
		}
		return properties;
	}

	/**
	 * Get the value from property file
	 * 
	 * @param Property
	 *            file
	 * @param Key
	 *            to get the value
	 * @return Value attached to the key
	 */
	public static String getValueFromPropertiesFile(Properties File, String key) {
		String value = File.getProperty(key);
		return value;
	}

	/**
	 * Executes batch and shell scripts
	 * 
	 * @param path
	 *            Accepts path of batch and shell scripts
	 * @param args
	 *            Arguments to be passed into the batch and shell scripts
	 * @throws Exception
	 *             If an exception occurs
	 */
	public static void executeScript(String path, String... args) throws Exception {
		try {
			StringBuilder sb = new StringBuilder(10);
			for (String s : args) {
				sb.append(String.format("\"%s\" ", s));
			}
			String cmd = "cmd /c start /wait call " + "\"" + path + "\" " + sb.toString().trim() + "";
			Process process = Runtime.getRuntime().exec(cmd);
			log.info("Executing batch file...");
			process.waitFor();
			log.info("Executed Successfully");
		} catch (IOException exception) {
			log.error("IO Exception occured while taking screenshots");
			log.error(exception.getMessage());
			throw exception;
		} catch (Exception exception) {
			log.error("Error occured while executing shell file");
			log.error(exception.getMessage());
			throw exception;
		}
	}

	/**
	 * Get the data for the API URL
	 * 
	 * @param url
	 *            URL of the API
	 * @return data Return data From API
	 * @throws Exception
	 *             If an Input or Output exception occurs
	 */
	public static String getApiData(String url) throws Exception {

		String data = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			request.addHeader("User-Agent", "Mozilla/5.0");
			HttpResponse response = client.execute(request);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";

			while ((line = rd.readLine()) != null) {
				result.append(line + "\n");
			}

			data = result.toString();
		} catch (Exception exception) {
			log.error(exception.getMessage());
			throw exception;
		}
		return data;
	}

	/**
	 * Get the required platform
	 * 
	 * @param osName
	 *            Name of the operating system
	 * @return Returns name of the operating system
	 */
	public static Platform getPlatform(String osName) {
		switch (osName) {
		case "win10":
			return Platform.WIN10;
		case "linux":
			return Platform.LINUX;
		case "mac":
			return Platform.MAC;
		case "mavericks":
			return Platform.MAVERICKS;
		case "mountain_lion":
			return Platform.MOUNTAIN_LION;
		case "sierra":
			return Platform.SIERRA;
		case "snow_leopard":
			return Platform.SNOW_LEOPARD;
		case "unix":
			return Platform.UNIX;
		case "vista":
			return Platform.VISTA;
		case "win8":
			return Platform.WIN8;
		case "win8.1":
			return Platform.WIN8_1;
		case "windows":
			return Platform.WINDOWS;
		case "xp":
			return Platform.XP;
		case "yosemite":
			return Platform.YOSEMITE;
		default:
			return Platform.ANY;
		}
	}
}