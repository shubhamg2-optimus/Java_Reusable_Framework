package com.amazon.mob.pages;

import java.time.Duration;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.qameta.allure.Step;

public class HomeMobPage extends BaseMobPage {
	
    private static final String CONSTRUCTOR_INFO = "In HomeMobPage() constructor";
    public static Logger LOGGER = Logger.getLogger(HomeMobPage.class);

    private By searchTextId = By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text");
    private By productTitleId = By.id("in.amazon.mShop.android.shopping:id/item_title");
    private By hamburgerId = By.id("in.amazon.mShop.android.shopping:id/action_bar_burger_icon");
    private By shopByCategoryButtonXpath = By.xpath("//android.widget.TextView[@text='Shop by Category']");
    private By addToCartButtonXpath = By.xpath("//android.widget.Button[@text='Add to Cart']");
    private By cartButtonId = By.id("in.amazon.mShop.android.shopping:id/action_bar_cart");
    private By productDetailPageTitleId = By.id("title");
    private By homeButtonId = By.id("in.amazon.mShop.android.shopping:id/action_bar_home_logo");
    private By enterPincodeId =By.id("in.amazon.mShop.android.shopping:id/loc_ux_gps_enter_pincode");
    private By enterPincodeButtonId = By.id("in.amazon.mShop.android.shopping:id/loc_ux_pin_code_button");
    private By pincodeTextBoxId = By.id("in.amazon.mShop.android.shopping:id/loc_ux_pin_code_text");
    private By applyButtonId = By.id("in.amazon.mShop.android.shopping:id/loc_ux_update_pin_code");
    private By locationId = By.id("in.amazon.mShop.android.shopping:id/rs_location_data");
    private By locationPinTextId = By.id("in.amazon.mShop.android.shopping:id/rs_location_data_city_pincode");
    private By noThanksButtonId = By.id("in.amazon.mShop.android.shopping:id/no_thanks");

    
    /**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseMobTest.java
     */
	public HomeMobPage(AndroidDriver driver) {
		super(driver);
		LOGGER.info(CONSTRUCTOR_INFO);
	}
	
	@Step("Verify home page is visible")
	public boolean verifyHomePageIsVisible() {
		LOGGER.info("Verifying home page is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(searchTextId));
		return(doesElementExist(searchTextId));
	}
	
	@Step("Search product")
	public boolean searchProduct(String searchItem, String pincode) throws InterruptedException {
		LOGGER.info("Searching for product :"+searchItem);
		driver.findElement(searchTextId).clear();
		driver.findElement(searchTextId).sendKeys(searchItem);
        driver.pressKeyCode(AndroidKeyCode.ENTER);
		Thread.sleep(4000);
		
        if(driver.findElements(enterPincodeId).size()>0) {
        	clickElement(enterPincodeId);
    		enterInput(pincodeTextBoxId, pincode);
    		clickElement(applyButtonId);
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(productTitleId));
		return(driver.findElement(productTitleId).getText().contains(searchItem));
	}
	
	@Step("Verify product details page is visible")
	public boolean productDetailPageIsVisible() throws InterruptedException {
		LOGGER.info("Verifying the product detail page is visible");
		clickElement(productTitleId);
		Thread.sleep(3000);
		return(doesElementExist(productDetailPageTitleId));
	}
	
	public void clickOnHamburger() {
		LOGGER.info("Clicking on hamburger menu button");
		clickElement(hamburgerId);
	}
	
	public MobCategoryPage clickOnShopByCategoryButton() {
		LOGGER.info("Clicking on 'Shop by Category' button");
		clickElement(shopByCategoryButtonXpath);
		return (new MobCategoryPage(driver));
	}
	
	@Step("Add product to cart")
	public void addProductToCart() throws InterruptedException {
		LOGGER.info("Adding a product to cart");
		clickElement(productTitleId);
		Thread.sleep(3000);
		TouchAction touchAction = new TouchAction(driver);
	    touchAction.press(540, 1500).waitAction(Duration.ofSeconds(2)).moveTo(540, 300).release().perform();
	    touchAction.press(540, 1500).waitAction(Duration.ofSeconds(2)).moveTo(540, 300).release().perform();
		Thread.sleep(3000);
		clickOnAddToCartButton();
	}
	
	public void clickOnAddToCartButton() throws InterruptedException  {
		LOGGER.info("Clicking on 'Add to cart' button");
        driver.findElement(addToCartButtonXpath).click();
        Thread.sleep(3000);
	}
	
	public MobShoppingCartPage clickOnCartButton() {
		LOGGER.info("Clicking on cart button");
		clickElement(cartButtonId);
		return(new MobShoppingCartPage(driver));
	}
	
	public void clickOnHomeButton() {
		LOGGER.info("Clicking on Home button");
		clickElement(homeButtonId);
		if(driver.findElements(noThanksButtonId).size()>0)
			driver.findElement(noThanksButtonId).click();
	}
	
	@Step("Select region")
	public boolean selectRegion(String pincode) throws InterruptedException {
		LOGGER.info("Selecting a region");
		if(driver.findElements(enterPincodeId).size()>0) {
        	clickElement(enterPincodeId);
    		enterInput(pincodeTextBoxId, pincode);
    		clickElement(applyButtonId);
    		Thread.sleep(5000);
            wait.until(ExpectedConditions.presenceOfElementLocated(locationPinTextId));
    		return(driver.findElement(locationPinTextId).getText().contains(pincode));
        }
		else {
			if(driver.findElement(locationPinTextId).getText().contains(pincode)) {
				return true;
			}
			else {
				wait.until(ExpectedConditions.presenceOfElementLocated(locationId));
				clickElement(locationId);
				clickElement(enterPincodeButtonId);
				enterInput(pincodeTextBoxId, pincode);
				clickElement(applyButtonId);
				Thread.sleep(5000);
		        wait.until(ExpectedConditions.presenceOfElementLocated(locationPinTextId));
				return(driver.findElement(locationPinTextId).getText().contains(pincode));
			}
		}    
	}	
}
