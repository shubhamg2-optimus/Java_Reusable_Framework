package com.lululemon.webui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.retail.webui.utils.PageUtil;

public class HomePage extends PageUtil {
    /**
     * Initialize WebElements of child class using WebDriver object
     *
     * @param driver
     *            WebDriver instance
     */
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ADMIN")
    protected WebElement adminTab;
}
