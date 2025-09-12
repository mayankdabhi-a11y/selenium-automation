package com.example.framework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class SampleTest extends BaseTest {

	@Test
	public void openAndCloseChrome() {
		// Open Amazon homepage
		driver.get("https://www.amazon.com");

		// Search for iPhone 17
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By searchBox = By.id("twotabsearchtextbox");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("iPhone 17", Keys.ENTER);
	}
}

