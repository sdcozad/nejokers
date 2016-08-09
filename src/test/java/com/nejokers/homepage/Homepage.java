package com.nejokers.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nejokers.main.Utils;

public class Homepage {

	public WebDriver driver;
	private HomepagePageObject Homepage;



	   @BeforeClass(alwaysRun = true)
	   public void launchBrowser(ITestContext context) throws Exception {


	       driver = Utils.getDriver(context);
	       
	 
	       Homepage = new HomepagePageObject(driver);
	       PageFactory.initElements(driver, Homepage);

	       driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

	   }
  
	@Test(priority = 10, groups = {"smokeTests"})
	   public void verifyHomePageTitle(ITestContext context) {
		
		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball");
		
		
		Homepage.click(Homepage.AboutUs);
		Homepage.waitFortitleToBePresent("About the Nebraska Jokers 13U AAA USSSA select Baseball Team");
//		Assert.assertEquals(driver.getTitle(), "About the Nebraska Jokers 13U AAA USSSA select Baseball Team");
//		System.out.println("AboutUs = " + driver.getTitle());
		
		Homepage.click(Homepage.Wanted);
		Homepage.waitFortitleToBePresent("Nebraska Jokers Select 13U AAA Baseball | *Wanted*");
//		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | *Wanted*");
//		System.out.println("*Wanted* = " + driver.getTitle());
		
		Homepage.click(Homepage.Events);
		Homepage.waitFortitleToBePresent("Events");
//		Assert.assertEquals(driver.getTitle(), "Events");
//		System.out.println("Events = " + driver.getTitle());
		
		Homepage.click(Homepage.Calendar);
		Homepage.waitFortitleToBePresent("SC Calendar");
//		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | Calendar");
//		System.out.println("Calendar = " + driver.getTitle());
		
		Homepage.click(Homepage.Players);
		Homepage.waitFortitleToBePresent("The Players");
//		Assert.assertEquals(driver.getTitle(), "The Players");
//		System.out.println("Players = " + driver.getTitle());
		
		Homepage.click(Homepage.Coaches);
		Homepage.waitFortitleToBePresent("The Coaches");
//		Assert.assertEquals(driver.getTitle(), "The Coaches");
//		System.out.println("Coaches = " + driver.getTitle());
		
		Homepage.click(Homepage.Digs);
		Homepage.waitFortitleToBePresent("Nebraska Jokers Select 13U AAA Baseball | Digs");
//		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | Digs");
//		System.out.println("Digs = " + driver.getTitle());
		
		Homepage.click(Homepage.Contact);
		Homepage.waitFortitleToBePresent("Contact");
//		Assert.assertEquals(driver.getTitle(), "Contact");
//		System.out.println("Contact = " + driver.getTitle());
	   }

 
	@AfterMethod(alwaysRun = true)
	   public void sendToTestRails(ITestContext context, ITestResult result) {
//	       log.info("Test Case = [" + CriTestRails.getTestCase() + "]");
//	       if (context.getCurrentXmlTest().getParameter("updateTestRail").contentEquals("yes"))
//	           CriTestRails.updateTestRails(driver,
//	               context.getCurrentXmlTest().getParameter("browse"),
//	               CriTestRails.getTestCase(),
//	               CriTestRails.getTestRun(),
//	               result);
//
	       if (result.getStatus() == ITestResult.FAILURE)
	    	   System.out.println("Failed - Page Title Wrong");
	       else
	    	   System.out.println("Passed - Page Title Correct");
//	           Utils.takeScreenShot(driver, context, result);
	   }


	   @AfterClass(alwaysRun = true)
	   public void terminateDriver() {
//	       log.info("Inside " + Utils.getMethodName());
//	       log.info("Terminating");
//	       Dashboard.logout();

	       driver.close();
	       driver.quit();
	   }
	
}
