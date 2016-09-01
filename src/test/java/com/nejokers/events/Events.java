package com.nejokers.events;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cri.main.utils.Utils;
import com.nejokers.homepage.HomepagePageObject;

public class Events {

    public WebDriver driver;
    private HomepagePageObject HomePage;
    private EventsPageObject EventsPage;



    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {


        driver = Utils.getDriver(context);


        HomePage = new HomepagePageObject(driver);
        PageFactory.initElements(driver, HomePage);

        EventsPage = new EventsPageObject(driver);
        PageFactory.initElements(driver, EventsPage);

        driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

    }


    @Test(priority = 10, groups = {"smokeTests"})
    public void clickCalandarFromEvents(ITestContext context) {

        HomePage.click(HomePage.Events);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Events");

        EventsPage.click(EventsPage.ViewCalendar);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Calendar");


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
            System.out.println("Failed - Events Page Error");
        else
            System.out.println("Passed - Events Page Passed");
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
