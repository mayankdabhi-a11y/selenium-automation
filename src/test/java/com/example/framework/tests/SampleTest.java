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
		// Open Amazon India homepage
		driver.get("https://www.amazon.in");

		// Search for iPhone 17
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		By searchBox = By.id("twotabsearchtextbox");
		wait.until(ExpectedConditions.visibilityOfElementLocated(searchBox)).sendKeys("iPhone 17", Keys.ENTER);

		// Dismiss cookie banner if it appears
		try {
			By acceptCookies = By.id("sp-cc-accept");
			wait.until(ExpectedConditions.elementToBeClickable(acceptCookies)).click();
		} catch (Exception ignored) {}

		// Click first search result link
		By resultLinks = By.cssSelector("div.s-main-slot div[data-component-type='s-search-result'] h2 a");
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(resultLinks, 0));
		List<WebElement> links = driver.findElements(resultLinks);
		WebElement first = links.get(0);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", first);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(first)).click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", first);
		}
	}
}

