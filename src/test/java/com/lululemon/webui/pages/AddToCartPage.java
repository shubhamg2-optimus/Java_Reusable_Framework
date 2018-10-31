package com.lululemon.webui.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import com.retail.webui.utils.PageUtil;

import io.qameta.allure.Step;

public class AddToCartPage extends PageUtil {

	Logger log = Logger.getLogger(AddToCartPage.class);
	private List<String> listOfProductInCart;
	public AddToCartPage(WebDriver driver) {
        super(driver);
        listOfProductInCart=new ArrayList<>();
    }
	
	@FindBy(xpath="//span[@class='item-count']")
	private WebElement labelAddToCart;
	
	@FindBys({@FindBy(xpath="//div[@class='product-details is-below']")})
	private List<WebElement> linkProductInCart;
	
	@FindBys({@FindBy(xpath="//div[@class='product-details is-below']//a[@id='removeItem']")})
	private List<WebElement> listDeleteButton;
	
	@FindBy (xpath = "//h1[text()='Your Bag is Empty']")
	private WebElement emptyBagMsg;
	
	public void goToCart() {
		log.info("Go To cart");
		clickWebElementFluently(labelAddToCart);
	}
	
	public void deleteProductFromCart() {
		log.info("Deleting product from the cart");
		for(WebElement ele:listDeleteButton) {
				clickWebElementFluently(ele);
			}
	}
	
	@Step("Verify product added to cart")
	public boolean isProductAddedSuccessfullyInCart(String productName) {
		log.info("Verifying "+productName+" is added in cart");
		for(WebElement ele:linkProductInCart) {
			if(ele.getText().contains(productName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isProductRemovedSuccessfullyInCart() {
		log.info("Verifying product is removed from the cart");	
		return(isWebElementDisplayed(emptyBagMsg));
	}
}
