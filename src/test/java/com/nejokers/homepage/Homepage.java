package com.nejokers.homepage;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.cri.main.utils.DriverFactory;
import com.cri.main.utils.LocalDriverManager;

public class Homepage {

    //    public WebDriver driver;
    private HomepagePageObject HomePage;


    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {

        //        driver = Utils.getDriver(context);
        DriverFactory.createInstance(context);

        HomePage = new HomepagePageObject(LocalDriverManager.getDriver());
        PageFactory.initElements(LocalDriverManager.getDriver(), HomePage);

        LocalDriverManager.getDriver().get(context.getCurrentXmlTest().getParameter("baseURL"));

    }

    @Test(priority = 10, groups = {"smokeTests"})
    public void verifyHomePageTitle(ITestContext context) {

        Assert.assertEquals(LocalDriverManager.getDriver().getTitle(), "Nebraska Jokers Select 13U AAA Baseball");

        HomePage.click(HomePage.AboutUs);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | About Us"));

        HomePage.click(HomePage.Events);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Events"));

        HomePage.click(HomePage.Calendar);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Calendar"));

        HomePage.click(HomePage.Players);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Players"));

        HomePage.click(HomePage.Coaches);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Coaches"));

        HomePage.click(HomePage.Gallery);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Gallery"));

        HomePage.click(HomePage.Shop);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop"));

        HomePage.click(HomePage.Contact);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Contact"));
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

        LocalDriverManager.destroyLocalDriver();

    }

}
