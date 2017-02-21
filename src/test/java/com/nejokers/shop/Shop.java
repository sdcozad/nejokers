package com.nejokers.shop;

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
import com.nejokers.homepage.HomepagePageObject;

public class Shop {

    //    public WebDriver driver;
    private HomepagePageObject HomePage;
    private ShopPageObject ShopPage;

    @BeforeClass(alwaysRun = true)
    public void launchBrowser(ITestContext context) throws Exception {


        //        driver = Utils.getDriver(context);
        DriverFactory.createInstance(context);



        HomePage = new HomepagePageObject(LocalDriverManager.getDriver());
        PageFactory.initElements(LocalDriverManager.getDriver(), HomePage);

        ShopPage = new ShopPageObject(LocalDriverManager.getDriver()

        );
        PageFactory.initElements(LocalDriverManager.getDriver(), ShopPage);

        LocalDriverManager.getDriver().get(context.getCurrentXmlTest().getParameter("baseURL"));

    }

    @Test(priority = 10, groups = {"smokeTests"})
    public void clickCalandarFromEvents(ITestContext context) {

        HomePage.click(HomePage.Shop);
        HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop");

        ShopPage.click(ShopPage.GearUp);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Jokers Apparel Store | Prep Sportswear"));
        LocalDriverManager.getDriver().navigate().back();
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Baseball | Shop"));
        ShopPage.click(ShopPage.StoreImage);
        Assert.assertTrue(HomePage.waitFortitleToBePresent("Nebraska Jokers Jokers Apparel Store | Prep Sportswear"));

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
            System.out.println("Failed - Shop Page Error");
        else
            System.out.println("Passed - Shop Page Passed");
        //          Utils.takeScreenShot(driver, context, result);
    }


    @AfterClass(alwaysRun = true)
    public void terminateDriver() {
        //      log.info("Inside " + Utils.getMethodName());
        //      log.info("Terminating");
        //      Dashboard.logout();

        LocalDriverManager.destroyLocalDriver();
    }

}
