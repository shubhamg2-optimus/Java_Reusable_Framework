package com.lululemon.webui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lululemon.webui.pages.CreateAccountPage;

import io.qameta.allure.Description;

public class LululemonCreateAccountTest extends LululemonBaseTest {

	private CreateAccountPage createAccountPage;

	@BeforeMethod(alwaysRun = true)
	public void initialSetup(Method method) {
		createAccountPage = new CreateAccountPage(getDriver());
	}

	/**
	 * To verify that user is able to login to application
	 * 
	 */
	@Description("<b>Test Step</b>:To verify the account creation functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>New user account should get created and user should be naviagted to user's account page"
			+ "</ul>")
	
	@Test(description = "To verify the account creation functionality")
	public void createAccount() {
		LululemonBaseTest.startTime = System.currentTimeMillis();
		createAccountPage.createAccount();
		LululemonBaseTest.endTime = System.currentTimeMillis();
		latency.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
				Long.toString(LululemonBaseTest.endTime - LululemonBaseTest.startTime));
		Assert.assertTrue(createAccountPage.isLoginPageLoaded(), "Account Not created successfully");
	}
}
