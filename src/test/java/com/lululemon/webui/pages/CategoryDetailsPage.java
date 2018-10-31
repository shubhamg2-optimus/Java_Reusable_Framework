package com.lululemon.webui.pages;



import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.retail.webui.utils.PageUtil;
import io.qameta.allure.Step;

public class CategoryDetailsPage extends PageUtil {
	
	Logger log = Logger.getLogger(CategoryDetailsPage.class);
	
	public CategoryDetailsPage(WebDriver driver) {
        super(driver);
    }
	
	@FindBy(xpath="//ul[@class='primary']//a[text()='men']")
	private WebElement labelDepartment;
	
	@FindBy(xpath="//div[@class='category-nav-section']/h1")
	private WebElement tableDepartmentPage;
	
	@FindBy(xpath="//div[@class='category-nav-wrapper']//a[text()='Accessories']")
	private WebElement listProductCategory;
	
	@Step("Navigate to department page")
	public void clickDepartmentLabel() {
		log.info("Clicking on the men's department");
		clickWebElementFluently(labelDepartment);
		
	}
	
	@Step("Select product category")
	public void selectingProductCategory(String productCategoryName) {
		log.info("Selecting the "+ productCategoryName+" product");
		if(isWebElementClickable(listProductCategory)) {
			listProductCategory.click();
		}	
	}
	
	/**
     * Verify that Department page is loaded return True if page is loaded else false
     */
    @Step("Verify department page is dispalyed")
	public boolean isTableDepartmentListDisplayed() {
		log.info("Verify department page is displayed");
		
		if(isWebElementDisplayed(tableDepartmentPage)) 
			return true;
		
		else return false;
		
	}

}
