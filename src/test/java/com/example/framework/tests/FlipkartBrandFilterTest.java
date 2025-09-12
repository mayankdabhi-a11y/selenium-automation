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
import java.util.Set;

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

		// Click the first iPhone link and verify a new tab opens with title containing 'iphone 17'
		WebElement first = items.get(0);
		((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
		Set<String> beforeHandles = driver.getWindowHandles();
		try {
			wait.until(ExpectedConditions.elementToBeClickable(first)).click();
		} catch (Exception e) {
			((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
		}

		try {
			new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> d.getWindowHandles().size() > beforeHandles.size());
			Set<String> after = driver.getWindowHandles();
			after.removeAll(beforeHandles);
			if (!after.isEmpty()) {
				driver.switchTo().window(after.iterator().next());
			}
		} catch (Exception ignored) {}

		boolean titleHasIphone17 = new WebDriverWait(driver, Duration.ofSeconds(15))
			.until(d -> d.getTitle() != null && d.getTitle().toLowerCase().contains("iphone 17"));
		Assert.assertTrue(titleHasIphone17, "Expected product page title to contain 'iPhone 17'");
	}
}