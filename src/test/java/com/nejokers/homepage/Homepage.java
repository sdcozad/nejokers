package com.nejokers.homepage;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nejokers.main.Utils;

public class Homepage {

	public WebDriver driver;


	   @BeforeClass(alwaysRun = true)
	   public void launchBrowser(ITestContext context) throws Exception {


	       driver = Utils.getDriver(context);

	       driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

	   }
  
	@Test(priority = 10, groups = {"smokeTests"})
	   public void verifyHomePageTitle(ITestContext context) {

	     

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
//	       if (result.getStatus() == ITestResult.FAILURE)
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
