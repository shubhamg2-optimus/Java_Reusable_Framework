package com.lululemon.webui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lululemon.webui.interfaces.Constants;
import com.lululemon.webui.pages.LoginPage;

import io.qameta.allure.Description;

/**
 * Test for Login
 * 
 */
public class LululemonLoginTest extends LululemonBaseTest {

    private LoginPage loginPage;
    String userName = Constants.CONFIG_USER_NAME;
    String password = Constants.CONFIG_PASSWORD;

    @BeforeMethod(alwaysRun = true)
    public void initialSetup(Method method) {
        loginPage = new LoginPage(getDriver());
    }

    /**
     * To verify that user is able to login to application
     * 
     */
    @Description("<b>Test Step</b>:To verify the login functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be able to login to the application"
			+ "</ul>")
    
    @Test (description = "To verify the login functionality")
    public void loginToApplication() {
        LululemonBaseTest.startTime = System.currentTimeMillis();
        loginPage.loginApplication(userName, password);
        LululemonBaseTest.endTime = System.currentTimeMillis();
        latency.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
                Long.toString(LululemonBaseTest.endTime - LululemonBaseTest.startTime));
        Assert.assertTrue(loginPage.isHomePageLoadedBySuccessfulLogin(), "Page not loaded");
    }
}