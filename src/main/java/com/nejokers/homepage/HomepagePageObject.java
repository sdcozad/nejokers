package com.nejokers.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.nejokers.main.BasePage;

public class HomepagePageObject extends BasePage {

    public HomepagePageObject(WebDriver driver) {
        super(driver);

    }

    @FindBy(how = How.LINK_TEXT, using = "About Us")
    public WebElement AboutUs;
    @FindBy(how = How.LINK_TEXT, using = "Scores")
    public WebElement Scores;
    @FindBy(how = How.LINK_TEXT, using = "*Wanted*")
    public WebElement Wanted;
    @FindBy(how = How.LINK_TEXT, using = "Events")
    public WebElement Events;
    @FindBy(how = How.LINK_TEXT, using = "Calendar")
    public WebElement Calendar;
    @FindBy(how = How.LINK_TEXT, using = "Players")
    public WebElement Players;
    @FindBy(how = How.LINK_TEXT, using = "Coaches")
    public WebElement Coaches;
    @FindBy(how = How.LINK_TEXT, using = "Shop")
    public WebElement Shop;
    @FindBy(how = How.LINK_TEXT, using = "Contact")
    public WebElement Contact;

    public void type(WebElement element, String keys) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).clear();
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keys);
    }

}
