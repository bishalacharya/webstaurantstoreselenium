package com.webstaurantstore.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Singleton class to manage the WebDriver using the WebDriverManager.
 */
public class DriverManager {
	private static WebDriver driver;

	private DriverManager() {
	}

	/**
	 * Retrieves the singleton instance of the WebDriver.
	 *
	 * @return The WebDriver instance.
	 */
	public static WebDriver getDriver() {
		if (driver == null) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		return driver;
	}
}
