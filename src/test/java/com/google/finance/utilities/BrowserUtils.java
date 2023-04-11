package com.google.finance.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BrowserUtils {

//	 private static Logger log = LogManager.getLogger(BrowserUtils.class.getName());
	final static Logger log = LoggerFactory.getLogger(BrowserUtils.class.getName());

	public static void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				log.info("Waiting for page to load...");
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			log.info("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			log.info("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
		}
	}

//	public static void waitForPageToLoad(long timeOutInSeconds) {
//		WebDriverWait wait = new WebDriverWait(Driver.getDriver(), timeOutInSeconds);
//		wait.until(new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				return ((JavascriptExecutor) Driver.getDriver()).executeScript("return document.readyState")
//						.equals("complete");
//			}
//		});
//	}
}
