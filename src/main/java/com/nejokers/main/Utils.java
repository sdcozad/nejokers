package com.nejokers.main;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class Utils {

    public static String browserType;


    public static WebDriver getDriver(ITestContext context) {
        //Good Start
        File file;
        WebDriver driver = null;

        switch (context.getCurrentXmlTest().getParameter("browse")) {
            case "CHROME":
                file = new File(
                    context.getCurrentXmlTest().getParameter("chromedriver"));
                System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
                driver = new ChromeDriver();
                break;
            case "IE":
                file = new File(
                    context.getCurrentXmlTest().getParameter("iedriver"));
                System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
                DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                capabilities.setCapability("nativeEvents", true);
                capabilities.setCapability("ignoreProtectedModeSettings", true);
                capabilities.setCapability("requireWindowFocus", true);
                capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                driver = new InternetExplorerDriver(capabilities);
                break;
            case "HUBCHROME":
                try {
                    driver = new RemoteWebDriver(
                        new URL(context.getCurrentXmlTest().getParameter("docker_address")),
                        DesiredCapabilities.chrome());
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            case "HUBFIREFOX":
                try {
                    driver = new RemoteWebDriver(
                        new URL(context.getCurrentXmlTest().getParameter("docker_address")),
                        DesiredCapabilities.firefox());
                } catch (MalformedURLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }

        setBrowserType(context.getCurrentXmlTest().getParameter("browse"));
        driver.manage().window().maximize();
        return driver;
    }

    public static String getBrowserType() {
        return browserType;
    }

    public static void setBrowserType(String appType) {
        browserType = appType;
    }

    public static void sleep(int value) {
        try {
            Thread.sleep(value);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void takeScreenShot(WebDriver driver, ITestContext context, ITestResult result) {
        Date date = new Date(System.currentTimeMillis());
        String dateString = date.toString();
        String screenShotName =
            context.getCurrentXmlTest().getParameter("ScreenShotDirectory") + dateString + result.getName() + ".png";
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(screenShotName));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String getMethodName() {
        StackTraceElement[] stacktrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stacktrace[2];
        return e.getMethodName();
    }


}
