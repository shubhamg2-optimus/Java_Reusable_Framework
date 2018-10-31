package com.lululemon.webui.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import com.retail.webui.utils.PageUtil;
import io.qameta.allure.Step;

public class SearchItemPage extends PageUtil{

	public SearchItemPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@type='text']")
	private WebElement textBoxSearch;
	
	@FindBy(xpath="//*[@id=\"search-icon\"]")
	private WebElement buttonSearch;
	
	@FindBy(xpath="//span[@class='all-text search-text']")
	private WebElement searchbutton;
	
	@FindBys({@FindBy(xpath="//div[@class='product-display-name']/a")})
	private List<WebElement> listProductNameLink;
	
	@Step("Search product")
	public void enterProductName(String productName) {
		log.info("Entering the product name");
		
		clickWebElementFluently(buttonSearch);
		clickAndEnterText(textBoxSearch, productName);
		clickWebElementFluently(searchbutton);
	}
	
	@Step("Verify product is displayed")
	public boolean isProductDisplayed(String productName) {
		log.info("Verifiying product is displayed");
		for(WebElement ele :listProductNameLink) {
			if(ele.getText().contains(productName)) {
				return true;
			}else
			{
				if(!isWebElementDisplayed(ele)||ele.equals(listProductNameLink.get(listProductNameLink.size()-1))) {
					return false;
				}
			}
		}
		if(listProductNameLink.isEmpty()) {
			log.info("No product list found");
			return false;
		}
		return true;
	}
}

