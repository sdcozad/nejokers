package com.nejokers.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nejokers.homepage.HomepagePageObject;
import com.nejokers.main.SLF4JLogging;
import com.nejokers.main.Utils;
import com.nejokers.main.testrails.CRITestRails;

import junit.framework.Assert;

public class ContactUs {

    public WebDriver driver;
    private HomepagePageObject HomePage;
    private ContactPageObject ContactPage;
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

        ContactPage = new ContactPageObject(driver);
        PageFactory.initElements(driver, ContactPage);

        driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

    }


    @Test(priority = 10, groups = {"smokeTests"})
    public void clickCalandarFromEvents(ITestContext context) {
        CriTestRails.setTestCase("17381");
        HomePage.click(HomePage.Contact);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact");

        ContactPage.type(ContactPage.Name, "DEMONSTRATION");
        ContactPage.type(ContactPage.Email, "demo@gmail.com");
        ContactPage.type(ContactPage.Subject, "Selenium Demo");
        ContactPage.type(ContactPage.Message, "Joe Cool is Cool!");



        Utils.sleep(10000);

        ContactPage.Name.clear();
        ContactPage.Email.clear();
        ContactPage.Subject.clear();
        ContactPage.Message.clear();

        ContactPage.SendButton.click();

        Utils.sleep(10000);

        Assert.assertEquals(ContactPage.Notification.getText(), "Please provide a valid email");

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
