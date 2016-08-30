package com.nejokers.contact;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.nejokers.homepage.HomepagePageObject;
import com.nejokers.main.Utils;


public class Contact {

    public WebDriver driver;
    private HomepagePageObject HomePage;
    private ContactPageObject ContactPage;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {


        driver = Utils.getDriver(context);


        HomePage = new HomepagePageObject(driver);
        PageFactory.initElements(driver, HomePage);

        ContactPage = new ContactPageObject(driver);
        PageFactory.initElements(driver, ContactPage);

        driver.get(context.getCurrentXmlTest().getParameter("baseURL"));

    }

    @Test(priority = 10, groups = {"smokeTests"})
    public void clickCalandarFromEvents(ITestContext context) {

        HomePage.click(HomePage.Contact);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact");

        ContactPage.Name.sendKeys("Test Name");
        //        ContactPage.Email.sendKeys("test@email.com");
        ContactPage.Subject.sendKeys("Test Subject");
        ContactPage.Message.sendKeys("Test Message");

        ContactPage.Send.click();
        Assert.assertEquals(ContactPage.Error.getText(), "Please provide a valid email");

    }


    @AfterMethod(alwaysRun = true)
    public void sendToTestRails(ITestContext context, ITestResult result) {
        //      log.info("Test Case = [" + CriTestRails.getTestCase() + "]");
        //      if (context.getCurrentXmlTest().getParameter("updateTestRail").contentEquals("yes"))
        //          CriTestRails.updateTestRails(driver,
        //              context.getCurrentXmlTest().getParameter("browse"),
        //              CriTestRails.getTestCase(),
        //              CriTestRails.getTestRun(),
        //              result);
        //
        if (result.getStatus() == ITestResult.FAILURE)
            System.out.println("Failed - Contact Page Error");
        else
            System.out.println("Passed - Contact Page Passed");
        //          Utils.takeScreenShot(driver, context, result);
    }


    @AfterClass(alwaysRun = true)
    public void terminateDriver() {
        //      log.info("Inside " + Utils.getMethodName());
        //      log.info("Terminating");
        //      Dashboard.logout();

        driver.close();
        driver.quit();
    }

}
