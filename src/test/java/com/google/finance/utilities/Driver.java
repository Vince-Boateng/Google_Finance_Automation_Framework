package com.google.finance.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private Driver() {
	}

	private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();

	// private static Logger log = LogManager.getLogger(Driver.class.getName());

	final static Logger log = LoggerFactory.getLogger(Driver.class.getName());

	public static WebDriver getDriver() {
		try {
			if (driverPool.get() == null) {
				synchronized (Driver.class) {
					String browser = ConfigurationReader.getProperty("browser");

					switch (browser) {

					case "chrome":
						log.info("Setting up Chrome driver.");
						WebDriverManager.chromedriver().setup();
						ChromeOptions options = new ChromeOptions();
						options.addArguments("--incognito");
						options.addArguments("disable-infobars");
						driverPool.set(new ChromeDriver(options));
						log.info("New Chrome driver is set.");
						driverPool.get().manage().window().maximize();
						driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						break;

					case "headless":
						log.info("Setting up Headless driver.");
						WebDriverManager.chromedriver().setup();
						ChromeOptions hOptions = new ChromeOptions();
						hOptions.addArguments("headless");
						hOptions.addArguments("--allow-insecure-localhost");
						driverPool.set(new ChromeDriver(hOptions));
						hOptions.setAcceptInsecureCerts(true);
						log.info("New headless driver is set.");
						driverPool.get().manage().window().maximize();
						driverPool.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						break;

					default:
						log.error("Invalid browser selection. Enter \'chrome\' browser.");
						break;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Driver Launch error.");
		}
		// This same driver will be returned everytime we call the Driver.getDriver()
		// method.
		return driverPool.get();
	}

	public synchronized static void closeDriver() {
		log.info("Closing the driver.");
		if (driverPool.get() != null) {
			driverPool.get().quit();
			driverPool.remove();
		}
	}

}
