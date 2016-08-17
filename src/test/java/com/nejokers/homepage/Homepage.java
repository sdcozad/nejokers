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

        HomePage.click(HomePage.AboutUs);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | About Us");


        HomePage.click(HomePage.Wanted);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Wanted");

        HomePage.click(HomePage.Events);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Events");

        HomePage.click(HomePage.Calendar);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Calendar");

        HomePage.click(HomePage.Players);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Players");

        HomePage.click(HomePage.Coaches);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Coaches");

        HomePage.click(HomePage.Shop);
        System.out.println("Digs = " + driver.getTitle());
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop");

        HomePage.click(HomePage.Contact);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact");

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
