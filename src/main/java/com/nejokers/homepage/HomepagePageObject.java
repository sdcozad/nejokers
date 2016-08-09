package com.nejokers.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.nejokers.main.BasePage;

public class HomepagePageObject extends BasePage {

	public HomepagePageObject(WebDriver driver) {
	       super(driver);

	   }

	   @FindBy(how = How.LINK_TEXT, using = "About Us")
	   public WebElement AboutUs;
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
	   @FindBy(how = How.LINK_TEXT, using = "Digs")
	   public WebElement Digs;
	   @FindBy(how = How.LINK_TEXT, using = "Contact")
	   public WebElement Contact;
	
}