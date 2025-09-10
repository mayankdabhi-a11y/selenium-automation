package com.example.framework.tests;

import com.example.framework.config.Config;
import com.example.framework.driver.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
	protected WebDriver driver;

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		driver = DriverFactory.createDriver();
		driver.manage().deleteAllCookies();
		driver.get(Config.getBaseUrl());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
