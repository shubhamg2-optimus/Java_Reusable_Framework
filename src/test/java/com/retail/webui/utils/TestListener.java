package com.retail.webui.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.amazon.mob.tests.BaseMobTest;
import com.lululemon.mobweb.tests.LululemonMobWebBaseTest;
import com.lululemon.webui.tests.LululemonBaseTest;

/**
 * Defines the Listener methods 
 */
public class TestListener implements ITestListener {

    Logger logger = Logger.getLogger(TestListener.class);

    private static final String ERROR_SYMBOL = " *********";
    private static final String TEST_CASE_FAILURE = "***** Error test has failed : ";
    private static final String TEST_START = "Starting test case : ";
    private static final String TEST_SUCCESS = "Test Passed : ";
    private static final String TEST_SKIPPED = "Test SKIPPED ";

    /**
     * Captures Screenshot on test failure
     */
    @Override
    public void onTestFailure(ITestResult result) {
        logger.info(TEST_CASE_FAILURE + result.getName() + ERROR_SYMBOL);
        logger.info("In OnTestFailure custom");

        Object currentClass = result.getInstance();
        
        if(currentClass instanceof BaseMobTest)
        {
        	WebDriver webDriver = ((BaseMobTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);          
        }
        
        else if(currentClass instanceof LululemonMobWebBaseTest)
        {
        	WebDriver webDriver = ((LululemonMobWebBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
        
        else if(currentClass instanceof LululemonBaseTest)
        {
        	WebDriver webDriver = ((LululemonBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
        
        else if(currentClass instanceof LululemonMobWebBaseTest)
        {
        	WebDriver webDriver = ((LululemonMobWebBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
    }
    
    /**
     * Logging on Test Start
     */
    @Override
    public void onTestStart(ITestResult result) {
        logger.info(TEST_START + result.getName());
    }

    /**
     * Logging on Test Success
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(TEST_SUCCESS + result.getName());
        
        Object currentClass = result.getInstance();
        
        if(currentClass instanceof BaseMobTest)
        {
        	WebDriver webDriver = ((BaseMobTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);          
        }
        
        else if(currentClass instanceof LululemonMobWebBaseTest)
        {
        	WebDriver webDriver = ((LululemonMobWebBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
        
        else if(currentClass instanceof LululemonBaseTest)
        {
        	WebDriver webDriver = ((LululemonBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
        
        else if(currentClass instanceof LululemonMobWebBaseTest)
        {
        	WebDriver webDriver = ((LululemonMobWebBaseTest) currentClass).getDriver();
            Utilities.takeScreenshot(result, webDriver);
        }
    }

    /**
     * Logging on Test Skip
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        logger.info(TEST_SKIPPED + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}