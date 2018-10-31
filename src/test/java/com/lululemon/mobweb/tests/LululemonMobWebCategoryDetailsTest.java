package com.lululemon.mobweb.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.lululemon.mobweb.pages.BestSellersCategoryPage;
import com.lululemon.mobweb.pages.HomePage;
import io.qameta.allure.Description;

public class LululemonMobWebCategoryDetailsTest extends LululemonMobWebBaseTest  {
	
	private HomePage homePage;
	private BestSellersCategoryPage bestSellersCategoryPage;
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
       
		homePage = new HomePage(getDriver());
		homePage.clickHamburger();
		homePage.navigateToBestSellerCategoryPage();
		bestSellersCategoryPage = new BestSellersCategoryPage(getDriver());
        Assert.assertTrue(bestSellersCategoryPage.isBestSellersCategoryPageDisplayed(), "Unsuccessful navigation to category detail page");        
    }
}
