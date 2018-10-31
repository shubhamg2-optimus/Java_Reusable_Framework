package com.lululemon.webui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.lululemon.webui.pages.CategoryDetailsPage;

import io.qameta.allure.Description;

public class LululemonCategoryDetailsTest extends LululemonBaseTest {

	private CategoryDetailsPage categoryDetailsPage;
	
	@BeforeMethod(alwaysRun = true)
    public void initialSetup(Method method) {
		categoryDetailsPage=new CategoryDetailsPage(getDriver());
    }
	
	/**
     * To verify that user is able to open department page to application
     * 
     */
	@Description("<b>Test Step</b>:To verify the category detail functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be navigated to category page"
			+ "</ul>")
	
    @Test (description = "To verify the category detail functionality")
    public void navigationToCategoryPage() {
        LululemonBaseTest.startTime = System.currentTimeMillis();
        categoryDetailsPage.clickDepartmentLabel();
        LululemonBaseTest.endTime = System.currentTimeMillis();
        latency.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
                Long.toString(LululemonBaseTest.endTime - LululemonBaseTest.startTime));
        Assert.assertTrue(categoryDetailsPage.isTableDepartmentListDisplayed(), "Unsuccessful navigation to category detail");       
    }
}
