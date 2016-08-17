package com.nejokers.homepage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nejokers.main.SLF4JLogging;
import com.nejokers.main.Utils;
import com.nejokers.main.testrails.CRITestRails;

public class Homepage {

    public WebDriver driver;
    private HomepagePageObject HomePage;
    private Logger log;

    SLF4JLogging Log = new SLF4JLogging();
    CRITestRails CriTestRails = new CRITestRails();


    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {

        log = Log.initLogger(context, this.getClass().getName());
        log.info("Starting");

        if (context.getCurrentXmlTest().getParameter("updateTestRail").contentEquals("yes")) {
            CriTestRails.InitTestRail(
                context.getCurrentXmlTest().getParameter("testrailURL"),
                context.getCurrentXmlTest().getParameter("testrailUserName"),
                context.getCurrentXmlTest().getParameter("testRailPassword"));

            CriTestRails.setTestRun(CriTestRails.returnTestRun(context));
        }

        driver = Utils.getDriver(context);

        HomePage = new HomepagePageObject(driver);
        PageFactory.initElements(driver, HomePage);

        driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

    }

    @Test(priority = 10, groups = {"smokeTests"})
    public void verifyHomePageTitle(ITestContext context) {

        CriTestRails.setTestCase("17367");
        Assert.assertEquals(driver.getTitle(), "Nebraska Jokers Select 13U AAA Baseball");
    }

    @Test(priority = 20, groups = {"smokeTests"})
    public void verifyAboutUsTitle(ITestContext context) {

        CriTestRails.setTestCase("17368");
        HomePage.click(HomePage.AboutUs);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | About Us");
    }

    @Test(priority = 30, groups = {"smokeTests"})
    public void verifyWantedTitle(ITestContext context) {

        CriTestRails.setTestCase("17369");
        HomePage.click(HomePage.Wanted);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Wanted");
    }

    @Test(priority = 40, groups = {"smokeTests"})
    public void verifyEventsTitle(ITestContext context) {

        CriTestRails.setTestCase("17370");
        HomePage.click(HomePage.Events);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Events");
    }

    @Test(priority = 50, groups = {"smokeTests"})
    public void verifyCalendarTitle(ITestContext context) {

        CriTestRails.setTestCase("17371");
        HomePage.click(HomePage.Calendar);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Calendar");
    }

    @Test(priority = 60, groups = {"smokeTests"})
    public void verifyPlayerTitle(ITestContext context) {

        CriTestRails.setTestCase("17372");
        HomePage.click(HomePage.Players);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Players");
    }

    @Test(priority = 70, groups = {"smokeTests"})
    public void verifyCoachesTitle(ITestContext context) {

        CriTestRails.setTestCase("17373");
        HomePage.click(HomePage.Coaches);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Coaches");
    }

    @Test(priority = 80, groups = {"smokeTests"})
    public void verifyShopTitle(ITestContext context) {

        CriTestRails.setTestCase("17374");
        HomePage.click(HomePage.Shop);
        System.out.println("Digs = " + driver.getTitle());
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop");
    }

    @Test(priority = 90, groups = {"smokeTests"})
    public void verifyContactTitle(ITestContext context) {

        CriTestRails.setTestCase("17375");
        HomePage.click(HomePage.Contact);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact");
    }

    @AfterMethod(alwaysRun = true)
    public void sendToTestRails(ITestContext context, ITestResult result) {
        log.info("Test Case = [" + CriTestRails.getTestCase() + "]");
        if (context.getCurrentXmlTest().getParameter("updateTestRail").contentEquals("yes"))
            CriTestRails.updateTestRails(driver,
                context.getCurrentXmlTest().getParameter("browse"),
                CriTestRails.getTestCase(),
                CriTestRails.getTestRun(),
                result);

        if (result.getStatus() == ITestResult.FAILURE)
            Utils.takeScreenShot(driver, context, result);
    }


    @AfterClass(alwaysRun = true)
    public void terminateDriver() {
        log.info("Inside " + Utils.getMethodName());
        log.info("Terminating");

        driver.close();
        driver.quit();
    }

}
