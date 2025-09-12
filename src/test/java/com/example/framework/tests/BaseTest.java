package com.example.framework.tests;

import com.example.framework.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		String browser = System.getProperty("browser", "chrome");
		boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
		driver = DriverFactory.createDriver(browser, headless);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}

