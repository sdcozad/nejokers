package com.nejokers.main;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	public WebDriver driver;
	   public WebDriverWait wait;
	   public static String browserType;

	   public BasePage(WebDriver driver) {
	       this.driver = driver;
	       wait = new WebDriverWait(driver, 30);
	   }


	   public void click(WebElement element) {
	       wait.until(ExpectedConditions.elementToBeClickable(element)).click();

	   }	
	
	   public boolean waitFortitleToBePresent(String value) {
	       try {
	           wait.until(ExpectedConditions.titleContains(value));
	           return true;
	       } catch (NoSuchElementException e) {
	           return false;
	       }
	   }
	   
}
