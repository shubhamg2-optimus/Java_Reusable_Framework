package com.lululemon.mobweb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.lululemon.mobweb.interfaces.Constants;
import com.lululemon.mobweb.pages.HomePage;
import com.lululemon.mobweb.pages.SearchItemPage;

import io.qameta.allure.Description;

public class LululemonMobWebSearchProductTest extends LululemonMobWebBaseTest {
	
	private HomePage homePage;
	private SearchItemPage searchitempage;
	
	@Description("<b>Test Step</b>:To verify the Search item functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be able to search a product and all the related product should be displayed"
			+ "</ul>")
    
    @Test (description = "To verify the Search item functionality")
    public void navigationToProductSearchPage() {
		
		homePage = new HomePage(getDriver());
		homePage.searchProduct(Constants.TEXT_PRODUCT_NAME);
		searchitempage = new SearchItemPage(getDriver());
        Assert.assertTrue(searchitempage.isProductDisplayed(Constants.TEXT_PRODUCT_NAME), "Unsuccessful Search Item");      
	}
}
