package com.lululemon.mobweb.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.retail.webui.utils.PageUtil;

import io.qameta.allure.Step;

public class SearchItemPage extends PageUtil {
	
	
	@FindBy(xpath = "//div[@class='product-display-name']//h3")
	private List<WebElement> productDisplayName;
	
	public SearchItemPage(WebDriver driver) {
        super(driver);
    }
	
	@Step("Verify product is displayed")
	public boolean isProductDisplayed(String productName) {
		
		log.info("Verifiying product is displayed");
		
		for(WebElement product : productDisplayName) {
			
			if(product.getText().equalsIgnoreCase(productName)) {
				return true;
			}
		}
		return false;
	}
}
