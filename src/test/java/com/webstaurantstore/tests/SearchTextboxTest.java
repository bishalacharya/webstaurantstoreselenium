package com.webstaurantstore.tests;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.Status;
import com.webstaurantstore.pages.HomePage;
import com.webstaurantstore.utils.Utility;

/**
 * Test class to test SearchTextBox Results
 */
public class SearchTextboxTest extends BaseTest {
	private static final Logger logger = LoggerFactory.getLogger(SearchTextboxTest.class);
	private static String WEBSITE_URL = "https://www.webstaurantstore.com";
	private static final String SEARCHQUERY = "stainless work table";

	private int totalSearchResults = 0;
	private static final int ITEMSPERPAGE = 60;
	private HomePage homePage;

	@Test
	public void performSearchAddEmptyCart() {

		// Step 2: Start the test
		test = extent.createTest("Webstaurantestore Automation Test", "Automating Webstaurantestore");

		try {

			test.log(Status.INFO, "Navigated to the website");

			// Step 3: Create an instance of the HomePage using POM
			homePage = new HomePage(driver);

			// Step 4: Search for a generic query in the search bar
			homePage.setSearchQuery(SEARCHQUERY);
			homePage.submitSearch();
			test.log(Status.INFO, "Performed search for: " + SEARCHQUERY);

			// Step 5: Check search results and add the last item with 'Table' in its title
			// to cart

			WebElement lastItem = getLastItemWithTitle("Table");

			test.log(Status.INFO, "LastItem: " + lastItem.getText());

			if (lastItem != null) {
				// Step 6: Add the last item to cart
				addToCart();
				// wait 10 seconds after addTocart Click.
				threadDelay(5);
				
				test.log(Status.INFO, "Added item to cart");

				// Step 7: Empty the cart
				test.log(Status.INFO, "Redirect to URL cart");
				driver.navigate().to(WEBSITE_URL + "/cart");
				threadDelay(5);
				emptyCart();
				test.log(Status.INFO, "Emptied the cart");
				implicitDelay(10);
			} else {
				logger.warn("No products with 'Table' in the title found.");
				test.log(Status.WARNING, "No products with 'Table' in the title found.");
			}

			// wait for 5 seconds after calling add and empty cart
			threadDelay(5);

		} catch (Exception e) {
			// Step 8: Log and report errors
			logger.error("An error occurred: " + e.getMessage());
			test.log(Status.FAIL, "An error occurred: " + e.getMessage());

			// Step 9: Capture and attach a screenshot
			captureAndAttachScreenshot();
		} finally {
			// Step 10: Close the browser
			if (driver != null) {
				driver.quit();
				test.log(Status.INFO, "Closed the browser");
			}

		}
	}

	private WebElement getLastItemWithTitle(String titleKeyword) {
		WebElement lastElement = null;
		totalSearchResults = Integer.parseInt(getTotalResults());

		int totalPages = (int) Math.ceil((double) totalSearchResults / ITEMSPERPAGE);

		// Locate and click the last page button
		for (int i = 0; i < totalPages; i++) {
			// Check search Result has keyword Table
			implicitDelay(5);
			List<WebElement> divElements = driver.findElements(
					By.xpath("//span[contains(@class, 'font-semibold') and contains(@class, 'text-sm')]"));

			for (WebElement divElement : divElements) {
				String divText = divElement.getText();

				if (divText.toLowerCase().contains("table")) {
					test.log(Status.INFO, "Text within span contains 'table':" + divText);
				} else {
					logger.error("Item does not contain text table:" + divText);
					test.log(Status.FAIL, "Item does not contain text table: " + divText);
				}

				lastElement = divElement;
			}

			if (i < totalPages - 1) {
				homePage.nextPageClick();
			}
		}

		return lastElement;
	}

	/**
	 * Adds Last Item in Cart
	 */
	private void addToCart() {
		List<WebElement> addToCartButtons = driver
				.findElements(By.cssSelector("div.add-to-cart input[data-action='addToCart']"));

		if (!addToCartButtons.isEmpty()) {
			WebElement lastAddToCartButton = addToCartButtons.get(addToCartButtons.size() - 1);
			lastAddToCartButton.click();
			logger.info("Clicked the last Add to Cart button.");

		} else {
			logger.info("No 'Add to Cart' buttons found on the page.");
		}
	}

	/**
	 * Empties Cart
	 */
	private void emptyCart() {
		test.log(Status.INFO, "Empty Cart Action: ");
		// Find and click the "Empty Cart" button
		WebElement emptyCartButton = driver.findElement(By.cssSelector("button.emptyCartButton"));
		emptyCartButton.click();
		logger.info("Empty Cart Button Clicked");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		WebElement overlay = wait.until(ExpectedConditions.presenceOfElementLocated(homePage.getOverlayLocator()));
		threadDelay(5);
		// Find and click the "Empty Cart" button on the popup
		WebElement emptyCartPopupButton = overlay.findElement(By.cssSelector("button[class*='bg-green-500'][class*='text-white']"));
		emptyCartPopupButton.click();

		logger.info("Empty Cart Button Clicked in Overlay");
	}

	/**
	 * Gives total number of Search results
	 * 
	 * @return Total result Count
	 */
	private String getTotalResults() {
		WebElement resultsElement = driver.findElement(homePage.getResults());

		// Extract the text from the element
		String resultsText = resultsElement.getText();

		// Use regular expression to extract the numeric value
		String numericValue = Utility.extractNumericValue(resultsText);
		return numericValue;
	}

}