package com.lululemon.webui.pages;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import com.retail.webui.utils.PageUtil;
import io.qameta.allure.Step;

public class ProductDetailsPage extends PageUtil {
	
	Logger log = Logger.getLogger(ProductDetailsPage.class);
	public ProductDetailsPage(WebDriver driver) {
        super(driver);
    } 
	
	@FindBys({@FindBy(xpath="//div[@class='product-display-name']//a")})
	private List<WebElement> listProductNameLink;
	
	@FindBy(xpath="//span[@class='select-menu__selected']")
	private WebElement path;
	
	@FindBy(xpath="//div[text()='ONE SIZE']")
	private WebElement sizeselect;
	
	@FindBy(xpath="//button[text()='Add to Bag']")
	private WebElement buttonAddToCart;
	
	@FindBy(xpath="//*[contains(@class,'label with-children')]")
	private WebElement labelProductPageHeading;
	
	@FindBy(xpath="//h1[contains(text(),'Added to Cart')]")
	private WebElement labelProductAddedSuccessfullyToCart;
	
	@FindBy(xpath="//*[@class='a-popover-wrapper']")
	private WebElement popUpCoverage;
	
	@FindBy(xpath="//button[@id='siNoCoverage-announce']")
	private WebElement optionPopUpCoverageNo;
	
	@FindBy(xpath="//*[@id='a-autoid-1']")
	private WebElement buttonNextProduct;
	
	@FindBy(xpath="//button[@aria-label='Close']")
	private WebElement buttonClose;
	
	@FindBy(xpath="//h2[@class='added-to-bag']")
	private WebElement addedToBagMsg;
	
	@Step("Select product")
	public void selectProductName(String productName) {
		log.info("Selecting the "+ productName+" product");
		for(WebElement ele :listProductNameLink) {
			if(ele.getText().contains(productName)) {
				clickWebElementFluently(ele);
				break;
			}else
			{
				if(ele.equals(listProductNameLink.get(listProductNameLink.size()-1))) {
					clickWebElementFluently(buttonNextProduct);
				}
			}
		}				
	}
	
	@Step("Add product to cart")
	public void addProductToCart() {
		log.info("Adding Product to the cart");
		clickWebElementFluently(path);
		clickWebElementFluently(sizeselect);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickWebElementFluently(buttonAddToCart);
		while(true) {
				if(isWebElementDisplayed(addedToBagMsg)) {
					break;
			}else{
				clickWebElementFluently(buttonAddToCart);
			}
		}
	 }
	
	@Step("Verify product added to cart is displayed on screen")
	public boolean isProductAddedToCart() {
		log.info("Verify if product is added to cart");
		if(isWebElementDisplayed(labelProductAddedSuccessfullyToCart))
			return true;
		else 
			return false;
	}
	 @Step("Verify Product category page is dispalyed")
	public boolean isProductCategoryPageDisplayed() {
		log.info("Verifying product category page loaded");
		if(isWebElementDisplayed(labelProductPageHeading)) {
			return true;
		}
		else return false;
	}
}
