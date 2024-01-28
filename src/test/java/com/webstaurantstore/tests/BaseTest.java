package com.webstaurantstore.tests;

import java.time.Duration;
import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.webstaurantstore.config.ConfigProperties;
import com.webstaurantstore.utils.DriverManager;

/**
 * Base test class providing common setup and teardown methods.
 */
public class BaseTest {
	protected WebDriver driver;
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	protected static ExtentReports extent;
	protected static ExtentTest test;
	
	/**
	 * Setup method to initialize WebDriver and navigate to the application URL.
	 */
	@Before
	public void setUp() {
		driver = DriverManager.getDriver();
		driver.get(ConfigProperties.getProperty("app.url"));
		driver.manage().window().maximize();
        initializeExtentReports();
		logger.info("Opened application URL: {}", ConfigProperties.getProperty("app.url"));
	}

	/**
	 * Teardown method to quit WebDriver after each test.
	 */
	@After
	public void tearDown() {
		driver.quit();
		extent.flush();
		logger.info("Closed WebDriver");
	}
	
	/**
	 * Capture and Attach Screenshot for test
	 */
	protected void captureAndAttachScreenshot() {
		// Capture screenshot and attach to the report
		if (driver instanceof TakesScreenshot) {
			TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
			test.addScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenshot));
		}
	}
	
	/**
	 * Initialize Extent Reports
	 */
	private void initializeExtentReports() {
		// Step 1: Setup ExtentReports for reporting
        extent = new ExtentReports();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(getExtentReportFileName());
        extent.attachReporter(htmlReporter);
    }

	/**
	 * Initialize Extent Report FileName
	 */
    private String getExtentReportFileName() {
        return "extent-report-" + System.currentTimeMillis() + ".html";
    }
    
    /**
     * Implicitely wait for seconds
     * @param seconds to wait 
     */
    protected void implicitDelay(int seconds) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
    }
    
    /**
     * Explicit thread delay
     * @param seconds
     */
    protected void threadDelay(int seconds) {
    	try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}	
    }
}