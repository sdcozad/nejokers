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
    private HomepagePageObject HomePage;


    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {


        driver = Utils.getDriver(context);


        HomePage = new HomepagePageObject(driver);
        PageFactory.initElements(driver, HomePage);

        driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

    }

    @Test(priority = 10, groups = {"smokeTests"})
    public void verifyHomePageTitle(ITestContext context) {

        Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball");

        //		Test Comment
        HomePage.click(HomePage.AboutUs);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | About Us");
        //		Assert.assertEquals(driver.getTitle(), "About the Nebraska Jokers 13U AAA USSSA select Baseball Team");
        //		System.out.println("AboutUs = " + driver.getTitle());

        HomePage.click(HomePage.Wanted);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Wanted");
        //		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | *Wanted*");
        //		System.out.println("*Wanted* = " + driver.getTitle());

        HomePage.click(HomePage.Events);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Events");
        //		Assert.assertEquals(driver.getTitle(), "Events");
        //		System.out.println("Events = " + driver.getTitle());

        HomePage.click(HomePage.Calendar);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Calendar");
        //		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | Calendar");
        //		System.out.println("Calendar = " + driver.getTitle());

        HomePage.click(HomePage.Players);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Players");
        //		Assert.assertEquals(driver.getTitle(), "The Players");
        //		System.out.println("Players = " + driver.getTitle());

        HomePage.click(HomePage.Coaches);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Coaches");
        //		Assert.assertEquals(driver.getTitle(), "The Coaches");
        //		System.out.println("Coaches = " + driver.getTitle());

        HomePage.click(HomePage.Shop);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop");
        //		Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball | Digs");
        //		System.out.println("Shop = " + driver.getTitle());

        HomePage.click(HomePage.Contact);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact");
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
            System.out.println("Failed - Home Page Error");
        else
            System.out.println("Passed - Home Page Passed");
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
