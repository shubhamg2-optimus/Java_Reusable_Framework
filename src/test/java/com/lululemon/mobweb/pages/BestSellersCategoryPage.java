package com.lululemon.mobweb.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.retail.webui.utils.PageUtil;

import io.qameta.allure.Step;

public class BestSellersCategoryPage extends PageUtil {
	
	
	@FindBy(xpath = "//h1[@class='collapse-header menu-title']")
    private WebElement menBestSellersText;
	
	public BestSellersCategoryPage(WebDriver driver) {
        super(driver);
    }
	
	@Step("Verify Best Seller category page is displayed")
	public boolean isBestSellersCategoryPageDisplayed() {
		
		log.info("Verifying Bestsellers category page is displayed");
		return(isWebElementDisplayed(menBestSellersText));
	}
}
