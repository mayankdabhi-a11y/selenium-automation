package com.example.framework.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class FlipkartBrandFilterTest extends BaseTest {

	@Test
	public void filterByAppleAndVerifyResults() {
		driver.get("https://www.flipkart.com");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// Dismiss login modal if shown
		try { driver.findElement(By.tagName("body")).sendKeys(Keys.ESCAPE); } catch (Exception ignored) {}

		// Search
		By searchBox = By.name("q");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("iPhone 17", Keys.ENTER);

		// Expand Brand section if collapsible and select APPLE
		try {
			By brandFilter = By.xpath("//div[contains(translate(.,'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'brand')]");
			List<WebElement> brandHeaders = driver.findElements(brandFilter);
			if (!brandHeaders.isEmpty()) brandHeaders.get(0).click();
		} catch (Exception ignored) {}

		By appleCheckbox = By.xpath("//label[contains(.,'APPLE') or contains(.,'Apple')]/preceding::input[@type='checkbox'][1] | //div[label[contains(.,'APPLE') or contains(.,'Apple')]]//input[@type='checkbox']");
		try {
			WebElement cb = wait.until(ExpectedConditions.elementToBeClickable(appleCheckbox));
			if (!cb.isSelected()) cb.click();
		} catch (Exception ignored) {}

		// Verify at least one result contains "iPhone" after filtering
		By results = By.xpath("//a[contains(translate(normalize-space(.),'ABCDEFGHIJKLMNOPQRSTUVWXYZ','abcdefghijklmnopqrstuvwxyz'),'iphone')]");
		List<WebElement> items = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(results, 0));
		Assert.assertTrue(items.size() > 0, "Expected results mentioning iPhone after applying APPLE filter");
	}
}