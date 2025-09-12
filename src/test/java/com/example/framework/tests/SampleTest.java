package com.example.framework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SampleTest extends BaseTest {

	@Test
	public void openAndCloseChrome() {
		// Open Flipkart homepage
		driver.get("https://www.flipkart.com");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Dismiss potential login modal
		try {
			driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE);
			By closeBtn = By.xpath("//button[@aria-label='Close' or contains(., '✕')]");
			List<WebElement> closes = driver.findElements(closeBtn);
			if (!closes.isEmpty()) {
				WebElement btn = closes.get(0);
				if (btn.isDisplayed()) btn.click();
			}
		} catch (Exception ignored) {}

		// Search for iPhone 17
		By searchBox = By.name("q");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("iPhone 17", Keys.ENTER);
	}
}

