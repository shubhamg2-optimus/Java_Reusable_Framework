package com.lululemon.webui.tests;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.lululemon.webui.interfaces.Constants;
import com.lululemon.webui.pages.AddToCartPage;
import com.lululemon.webui.pages.CategoryDetailsPage;
import com.lululemon.webui.pages.ProductDetailsPage;

import io.qameta.allure.Description;

public class LululemonAddProductToCartTest extends LululemonBaseTest{
	private CategoryDetailsPage categoryDetailsPage;
	private ProductDetailsPage productDetailsPage;
	private AddToCartPage addToCartPage;
	private String productName=Constants.TEXT_PRODUCTNAME;
	
	@BeforeMethod(alwaysRun = true)
    public void initialSetup(Method method) {
		categoryDetailsPage = new CategoryDetailsPage(getDriver());
		productDetailsPage = new ProductDetailsPage(getDriver());
		addToCartPage = new AddToCartPage(getDriver());
    }
	
	/**
     * To verify that user is able to login to application
	 * @throws InterruptedException 
     * 
     */
	@Description("<b>Test Step</b>:To verify the product added to cart functionality<br>"
			+ "<b>Expected Result</b>:<br>"
			+ "<ul><li>Selected product should be added to the cart successfully"
			+ "</ul>")
	
    @Test (description = "To verify the product added to cart functionality")
    public void addProductToCart() {
    	LululemonBaseTest.startTime = System.currentTimeMillis();
    	categoryDetailsPage.clickDepartmentLabel();
    	categoryDetailsPage.selectingProductCategory(Constants.TEXT_PRODUCT_CATEGORY);
    	productDetailsPage.selectProductName(productName);
    	productDetailsPage.addProductToCart();
    	addToCartPage.goToCart();
        LululemonBaseTest.endTime = System.currentTimeMillis();
        latency.put(Thread.currentThread().getStackTrace()[1].getMethodName(),
                Long.toString(LululemonBaseTest.endTime - LululemonBaseTest.startTime));
        Assert.assertTrue(addToCartPage.isProductAddedSuccessfullyInCart(productName), "Product not found in cart");
    }
}
