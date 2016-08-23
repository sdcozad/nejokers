package com.nejokers.shop;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.nejokers.main.BasePage;

public class ShopPageObject extends BasePage {

    public ShopPageObject(WebDriver driver) {
        super(driver);

    }

    @FindBy(how = How.LINK_TEXT, using = "GEAR UP")
    public WebElement GearUp;

    @FindBy(how = How.XPATH, using = "//img[@id='comp-iryf5094imgimage']")
    public WebElement StoreImage;

}
