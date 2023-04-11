package com.google.finance.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import com.google.finance.tests.TestBase;
import com.google.finance.utilities.BrowserUtils;
import com.google.finance.utilities.Driver;

public class BasePage extends TestBase {

//	private Logger log = LogManager.getLogger(BasePage.class.getName());
	final org.slf4j.Logger log = LoggerFactory.getLogger(BasePage.class.getName());

	public BasePage() {
		PageFactory.initElements(Driver.getDriver(), this);
	}

	@FindBy(xpath = "(//input[@class='Ax4B8 ZAGvjd' and contains(@aria-label, 'Search for stocks')])[2]")
	private WebElement stockSearchInputBox;

	public void searchForStock(String stockName) {
		try {
			stockSearchInputBox.sendKeys(stockName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getTitle() {
		String title = null;
		try {
			log.info("Waiting for page to load...");
			BrowserUtils.waitForPageToLoad(600);
			title = Driver.getDriver().getTitle();
			log.info("The Title of the current page is " + title);
			System.out.println("The actual title of the current page is: " + title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return title;
	}

}
