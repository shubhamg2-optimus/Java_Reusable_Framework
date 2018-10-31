package com.lululemon.mobweb.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.retail.webui.utils.PageUtil;

import io.qameta.allure.Step;

public class HomePage extends PageUtil {
	
	public HomePage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(id = "search-icon")
    private WebElement searchIcon;
	
	@FindBy(xpath = "//a[text()='My Account']")
    private WebElement myAccount;
	
	@FindBy(xpath = "//button[@title='Open navigation menu']")
    private WebElement hamburger;
	
	@FindBy(xpath = "//div[@class='search-input']/input")
    private WebElement searchTextBox;
	
	@FindBy(xpath = "//span[text()='men']")
    private WebElement menCategory;
	
	@FindBy(xpath = "//div[@class='site-mobile-content']//a[contains(@href,';men;features;bestsellers')]")
	private WebElement bestsellersIcon;
	
	@Step("Search product")
	public void searchProduct(String productName) {
		log.info("Searching for product :"+productName);
		isWebElementDisplayed(searchIcon);
		searchIcon.click();
		clickAndEnterText(searchTextBox, productName);
		searchTextBox.sendKeys(Keys.ENTER);			
	}
	
	public void clickHamburger() {
		log.info("Clicking on hamburger icon");
		isWebElementClickable(hamburger);
		hamburger.click();
	}
	
	public void clickOnMyAccount() {
		log.info("Clicking on My Account");
		isWebElementClickable(myAccount);
		myAccount.click();
	}
	
	@Step("Navigate to Best Seller category page")
	public void navigateToBestSellerCategoryPage() {
		
		log.info("Navigating to Bestseller category page");
		isWebElementClickable(menCategory);
		menCategory.click();
		isWebElementClickable(bestsellersIcon);
		bestsellersIcon.click();
	}
}
