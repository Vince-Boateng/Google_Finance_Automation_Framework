package com.google.finance.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;

import com.google.finance.extentReport.ExtentReportManager;
import com.google.finance.utilities.BrowserUtils;
import com.google.finance.utilities.ConfigurationReader;
import com.google.finance.utilities.Driver;

public class TestBase extends ExtentReportManager {

	// private Logger log = LogManager.getLogger(TestBase.class.getName());

	final Logger log = LoggerFactory.getLogger(TestBase.class.getName());

	// 1. open webpage www.google.com/finance on a chrome browser
	@BeforeTest
	public void launchGoogleFinanceApplication() {
		log.info("Launching the Google Finance Application.");

		try {
			Driver.getDriver().get(ConfigurationReader.getProperty("testEnvUrl"));
			log.info("Navigated to the Google Finance page.");
		} catch (NullPointerException e) {
			log.error(e.getMessage());
		}
		BrowserUtils.waitForPageToLoad(600);
		String title = Driver.getDriver().getTitle();
		log.info("The Title of the current page is " + title);

		if (title.contains("503 Service Unavailable") || title.contains("HTTP Status 500 - Internal Error")) {
			log.error("Received " + title + " error message. Exiting the application.");
			Driver.closeDriver();
			System.exit(0);
		} else {
			Driver.getDriver().manage().deleteAllCookies();
			BrowserUtils.waitForPageToLoad(600);
		}
	}

	@AfterSuite
	public void tearDown() {
		Driver.closeDriver();
	}

	// TakesScreenshot

	public String getScreenshotPath(String testCaseName) {
		TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationPath = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		log.info("Test Failed. Screenshot path has been captured.");

		try {
			File file = new File(destinationPath);
			FileUtils.copyFile(source, file);
		} catch (IOException e) {
			e.printStackTrace();
			log.error("File copy error occurred.");
		}

		return destinationPath;
	}

}
