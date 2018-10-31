package com.amazon.mob.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class MobShoppingCartPage extends BaseMobPage {
	
    private static final String CONSTRUCTOR_INFO = "In ShoppingCartPage() constructor";
    public static Logger LOGGER = Logger.getLogger(MobShoppingCartPage.class);

    private By deleteButtonXpath = By.xpath("//android.widget.Button[contains(@text,'Delete')]");
    private By cartCountId = By.id("in.amazon.mShop.android.shopping:id/action_bar_cart_count");
    private By emptyCartTextXpath = By.xpath("//android.view.View[@text='Your Shopping Cart is empty.']");
    private By proceedToCheckoutButtonId = By.xpath("//android.widget.Button[@text='Proceed to Checkout']");
    
	/**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseMobTest.java
     */
	public MobShoppingCartPage(AndroidDriver driver) {
		super(driver);
		LOGGER.info(CONSTRUCTOR_INFO);
	}
	
	@Step("Verify product added to cart")
	public boolean verifyProductAddedToCart() {
		LOGGER.info("Verifying the product added to cart");
    	wait.until(ExpectedConditions.presenceOfElementLocated(cartCountId));
		return(!driver.findElement(cartCountId).getText().equalsIgnoreCase("0"));
	}
	
	public String getCartCount() {
    	wait.until(ExpectedConditions.presenceOfElementLocated(proceedToCheckoutButtonId));
		String cartCount = driver.findElement(cartCountId).getText();
		return cartCount;
	}
	
	public boolean deleteProductFromCart() throws InterruptedException {
		LOGGER.info("Deleting product from the cart");
        while(!doesElementExist(emptyCartTextXpath)) {
    		clickElement(deleteButtonXpath);
    		Thread.sleep(3000);
        }
		return(doesElementExist(emptyCartTextXpath));
	}
}
