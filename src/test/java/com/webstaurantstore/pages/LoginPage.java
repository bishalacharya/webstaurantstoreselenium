package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page class representing the login page of the application.
 */
public class LoginPage extends BasePage {
	/**
	 * Constructor to initialize WebDriver through the base class.
	 *
	 * @param driver WebDriver instance
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Perform login action.
	 *
	 * @param username User's username
	 * @param password User's password
	 */
	public void login(String username, String password) {
		WebElement usernameInput = driver.findElement(By.id("username"));
		WebElement passwordInput = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.id("loginButton"));

		usernameInput.sendKeys(username);
		passwordInput.sendKeys(password);
		loginButton.click();

		logger.info("Performed login with username: {}", username);
	}
}
