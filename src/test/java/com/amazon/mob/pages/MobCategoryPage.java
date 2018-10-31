package com.amazon.mob.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class MobCategoryPage extends BaseMobPage {
	
	private static final String CONSTRUCTOR_INFO = "In CategoryPage() constructor";
    public static Logger LOGGER = Logger.getLogger(MobCategoryPage.class);

	private By categoryListId = By.id("sbdCategory1");
	
	/**
     * Constructor
     *
     * @param driver Reference to the android driver which is instantiated in
     *               BaseMobTest.java
     */
	public MobCategoryPage(AndroidDriver driver) {
		super(driver);
		LOGGER.info(CONSTRUCTOR_INFO);
	}
	
	@Step("Verify category page is visible")
	public String verifyCategoryPageIsVisible() {
		LOGGER.info("Verifying 'Shop by Category' page is visible");
        wait.until(ExpectedConditions.presenceOfElementLocated(categoryListId));
		if(doesElementExist(categoryListId)) {
			return ("Category page displayed");
		}
		else {
			return ("Category page not displayed");
		}
	}
}
