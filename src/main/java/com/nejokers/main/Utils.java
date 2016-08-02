package com.nejokers.main;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;

public class Utils {

	public static String browserType;

	
	public static WebDriver getDriver(ITestContext context) {

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
	
}
