package com.amazon.mob.tests;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.amazon.mob.interfaces.Constants;
import com.amazon.mob.pages.HomeMobPage;
import com.amazon.mob.pages.LoginMobPage;
import com.amazon.mob.pages.MobCategoryPage;
import com.amazon.mob.pages.MobShoppingCartPage;

import io.qameta.allure.Description;

public class AmazonMobProductCartTest extends BaseMobTest {
	
	public static Logger LOGGER = Logger.getLogger(AmazonMobProductCartTest.class);
	public LoginMobPage loginMobPage;
	public HomeMobPage homeMobPage;
	public MobCategoryPage categoryPage;
	public MobShoppingCartPage shoppingCartPage;
	
	/*
	 *  Verify user is able to add products to cart
	 */
	
	@Description("<b>Test Step</b>:Verify user is able to add products to cart<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>Selected product should be added to the cart successfully"
			+ "</ul>")
	
	@Test(description = "Verify user is able to add products to cart")
	public void verifyAddProductToCart() {
		try {
            LOGGER.info("Verify user is able to add products to cart");
            loginMobPage = new LoginMobPage(driver);
            homeMobPage = loginMobPage.clickOnSkipSignInButton();
            Assert.assertTrue(homeMobPage.verifyHomePageIsVisible());
            Assert.assertTrue(homeMobPage.searchProduct(Constants.TEXT_PRODUCT_NAME2,Constants.TEXT_PIN));
            homeMobPage.selectRegion(Constants.TEXT_PIN);
            homeMobPage.addProductToCart();
            shoppingCartPage = homeMobPage.clickOnCartButton();
            Assert.assertTrue(shoppingCartPage.verifyProductAddedToCart());
        } catch (Exception exception) {
            LOGGER.error("Error while adding products to cart");
            // Terminating test case execution because of an unexpected
            // addressPageplication/environment/network error
            Assert.fail(exception.getMessage());
        }
	}
}
