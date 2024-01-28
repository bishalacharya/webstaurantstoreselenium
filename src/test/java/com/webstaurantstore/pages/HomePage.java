package com.webstaurantstore.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * HomePage class representing the home page of the application.
 */
public class HomePage extends BasePage {

	private final By searchBar = By.id("searchval");
	private final By signInMenu = By.id("signInMenu");
	private final By cartButton = By.id("cartButton");
	private final By nextPageLink = By.cssSelector(".rounded-r-md");
	private final By results = By.cssSelector(".page-header.search--title");
    private final By overlayLocator = By.className("ReactModal__Content");
    private final By addToCartPopup = By.id("Regency30");

	/**
	 * Constructor to initialize WebDriver through the base class.
	 *
	 * @param driver WebDriver instance
	 */
	public HomePage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Navigate to the login page.
	 */
	public void navigateToLoginPage() {
		WebElement loginLink = driver.findElement(By.id("loginLink"));
		loginLink.click();
		logger.info("Clicked on login link");
	}

	public void setSearchQuery(String query) {
		driver.findElement(searchBar).sendKeys(query);
	}

	public void submitSearch() {
		driver.findElement(searchBar).submit();
	}

	public void clickSignInMenu() {
		driver.findElement(signInMenu).click();
	}

	public void clickCartButton() {
		driver.findElement(cartButton).click();
	}

    public void nextPageClick() {
        driver.findElement(nextPageLink).click();
    }

	/**
	 * @return the results
	 */
	public By getResults() {
		return results;
	}

	/**
	 * @return the overlayLocator
	 */
	public By getOverlayLocator() {
		return overlayLocator;
	}

	/**
	 * @return the addToCartPopup
	 */
	public By getAddToCartPopup() {
		return addToCartPopup;
	}
    
}
