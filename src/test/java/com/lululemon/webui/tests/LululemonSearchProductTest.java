package com.lululemon.webui.tests;

import java.lang.reflect.Method;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lululemon.webui.interfaces.Constants;
import com.lululemon.webui.pages.LoginPage;
import com.lululemon.webui.pages.SearchItemPage;

import io.qameta.allure.Description;

import org.testng.Assert;

public class LululemonSearchProductTest extends LululemonBaseTest {
	
	private SearchItemPage searchitempage;

    
    @BeforeMethod(alwaysRun = true)
    public void initialSetup(Method method) {
    	
    	new LoginPage(getDriver());
    	searchitempage = new SearchItemPage(getDriver());

}
    @Description("<b>Test Step</b>:To verify the Search item functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be able to search a product and all the related product should be displayed"
			+ "</ul>")
    
    @Test (description = "To verify the Search item functionality")
    public void navigationToProductSearchPage() {
        LululemonBaseTest.startTime = System.currentTimeMillis();
        searchitempage.enterProductName(Constants.TEXT_PRODUCTNAME);
        LululemonBaseTest.endTime = System.currentTimeMillis();
        latency.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
                Long.toString(LululemonBaseTest.endTime - LululemonBaseTest.startTime));
        Assert.assertTrue(searchitempage.isProductDisplayed(Constants.TEXT_PRODUCTNAME), "Unsuccessful Search Item");      
    }
}
