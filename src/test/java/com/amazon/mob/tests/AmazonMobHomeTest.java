package com.amazon.mob.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.mob.interfaces.Constants;
import com.amazon.mob.pages.HomeMobPage;
import com.amazon.mob.pages.LoginMobPage;
import com.amazon.mob.pages.MobCategoryPage;
import io.qameta.allure.Description;


public class AmazonMobHomeTest extends BaseMobTest {
	
    public static Logger LOGGER = Logger.getLogger(AmazonMobHomeTest.class);
	public LoginMobPage loginMobPage;
	public HomeMobPage homeMobPage;
	public MobCategoryPage categoryPage;	
	
	/*
	 *  Verify user is able to view category details page
	 */
	@Description("<b>Test Step</b>:Verify user is able to view category details page<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be navigated to category page"
			+ "</ul>")

	@Test(description = "Verify user is able to view category details page")
	public void verifyCategoryDetailsPage() {
		try {
            LOGGER.info("Verify user is able to view category details page");
            loginMobPage = new LoginMobPage(driver);
            homeMobPage = loginMobPage.clickOnSkipSignInButton();
            Assert.assertTrue(homeMobPage.verifyHomePageIsVisible());
            homeMobPage.clickOnHamburger();
            categoryPage = homeMobPage.clickOnShopByCategoryButton();
            Assert.assertEquals("Category page not displayed", categoryPage.verifyCategoryPageIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while viewing category details page");
            // Terminating test case execution because of an unexpected
            // addressPageplication/environment/network error
            Assert.fail(exception.getMessage());
        }
	}
	
	/*
	 *  Verify user is able to view product details page
	 */
	@Description("<b>Test Step</b>:Verify user is able to view product details page<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>User should be navigated to product details page"
			+ "</ul>")
	
	@Test(description = "Verify user is able to view product details page")
	public void verifyProductDetailsPage() {
		try {
            LOGGER.info("Verify user is able to view product details page");
            loginMobPage = new LoginMobPage(driver);
            homeMobPage = loginMobPage.clickOnSkipSignInButton();
            Assert.assertTrue(homeMobPage.verifyHomePageIsVisible());
            Assert.assertTrue(homeMobPage.searchProduct(Constants.TEXT_PRODUCT_NAME2, Constants.TEXT_PIN));
            homeMobPage.selectRegion(Constants.TEXT_PIN);
            Assert.assertTrue(homeMobPage.productDetailPageIsVisible());
        } catch (Exception exception) {
            LOGGER.error("Error while viewing product details page");
            // Terminating test case execution because of an unexpected
            // addressPageplication/environment/network error
            Assert.fail(exception.getMessage());
        }
	}
}
