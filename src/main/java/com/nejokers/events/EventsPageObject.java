package com.nejokers.events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.nejokers.main.BasePage;

public class EventsPageObject extends BasePage {

	public EventsPageObject(WebDriver driver) {
	       super(driver);

	   }
	
	 @FindBy(how = How.LINK_TEXT, using = "View Calendar")
	   public WebElement ViewCalendar;
	  
	
}
