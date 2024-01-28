package com.webstaurantstore.pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for all page classes. Provides common functionalities.
 */
public class BasePage {
	protected WebDriver driver;
	protected WebDriverWait wait;
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * Constructor to initialize WebDriver and WebDriverWait.
	 *
	 * @param driver WebDriver instance
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
}
